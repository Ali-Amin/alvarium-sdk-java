package com.alvarium.annotators.sca;

import java.io.File;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

public class PackageFileHandlerFactoryTest {
    @Rule
    public TemporaryFolder dir = new TemporaryFolder();

    @Test
    public void shouldReturnMavenHandler() throws Exception {
        File pom = dir.newFile("pom.xml");
        PackageFileHandlerFactory factory = new PackageFileHandlerFactory();
        PackageFileHandler handler = factory.getHandler(pom.toPath().getParent().toString());
        assert handler instanceof MavenHandler;
    }
}
