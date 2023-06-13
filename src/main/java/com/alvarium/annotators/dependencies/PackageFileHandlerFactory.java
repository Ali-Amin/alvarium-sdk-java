package com.alvarium.annotators.dependencies;

public class PackageFileHandlerFactory {
    
    public PackageFileHandler getHandler(String dir) throws DependenciesException {
        final PackageFileHandler[] handlers = {
            new MavenHandler(dir)
        };

        for (PackageFileHandler h : handlers) {
            if(h.exists()) {
                return h;
            }
        }

        throw new DependenciesException("Could not find a supported package file");
    }
}
