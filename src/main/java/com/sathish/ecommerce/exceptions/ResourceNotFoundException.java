package com.sathish.ecommerce.exceptions;

public class ResourceNotFoundException extends Exception{
    String fieldName;
    Long fieldId;

    public ResourceNotFoundException(){

    }

    public ResourceNotFoundException(String fieldName, Long fieldId) {
        super(String.format("%s with id: %d not found",fieldName,fieldId));
        this.fieldName = fieldName;
        this.fieldId = fieldId;
    }
}
