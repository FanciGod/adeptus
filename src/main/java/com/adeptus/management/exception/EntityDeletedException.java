package com.adeptus.management.exception;

public class EntityDeletedException extends RuntimeException {
    public EntityDeletedException(String name) {
        super("Entity '" + name + "' already deleted. Please contact with your boss");
    }
}
