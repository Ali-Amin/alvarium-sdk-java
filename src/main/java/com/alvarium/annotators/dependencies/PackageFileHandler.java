package com.alvarium.annotators.dependencies;

import java.util.Map;


public interface PackageFileHandler {
    String getFileName();
    Map<String, String> getPackages() throws DependenciesException;
    boolean exists();
}

