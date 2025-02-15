package com.adeptus.management.exception;

public class EntityDuplicateException extends RuntimeException{
    public EntityDuplicateException(String name) {
        super("Entity '" + name + "' already exists.");
    }
}
