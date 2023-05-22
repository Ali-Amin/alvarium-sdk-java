package com.alvarium.annotators;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Instant;

import com.alvarium.contracts.Annotation;
import com.alvarium.contracts.AnnotationType;
import com.alvarium.hash.HashType;
import com.alvarium.sign.SignatureInfo;
import com.alvarium.utils.PropertyBag;

class ManifestAnnotator extends AbstractAnnotator implements Annotator {
    
    private final HashType hash;
    private final AnnotationType kind;
    private final SignatureInfo signature;

    protected ManifestAnnotator(HashType hash, SignatureInfo signature) {
        this.hash = hash;
        this.kind = AnnotationType.MANIFEST;
        this.signature = signature;
    }

    // takes the manifest file e.g., package.json, go.mod, etc. as 
    // a byte array input
    //
    // expects pipelineId from ctx
    @Override
    public Annotation execute(PropertyBag ctx, byte[] data) throws AnnotatorException {
        final String key = super.deriveHash(hash, data);
        
        String host;

        try {
            host = InetAddress.getLocalHost().getHostName();
        } catch (UnknownHostException e) {
            throw new AnnotatorException("Cannot get host name", e);
        }

        final byte[] manifestFile = ctx.getProperty(
            AnnotationType.MANIFEST.name(),
            byte[].class
        );

        // TODO(Ali Amin): Add some logic depending on the manifest file
        final Boolean isSatisfied = true;

        final Annotation annotation = new Annotation(
            key, 
            hash, 
            host, 
            kind, 
            null, 
            isSatisfied, 
            Instant.now()
        );

        final String annotationSignature = super.signAnnotation(
            signature.getPrivateKey(),
            annotation
        );

        annotation.setSignature(annotationSignature);
        return annotation;
    }
}