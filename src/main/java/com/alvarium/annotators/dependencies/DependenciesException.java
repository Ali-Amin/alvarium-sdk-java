package com.alvarium.annotators.dependencies;

public class DependenciesException extends Exception {
    public DependenciesException(String msg) {
        super(msg);
    }
    
    public DependenciesException(String msg, Exception e) {
      super(msg, e);
    }
}
