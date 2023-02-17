package com.example.Blog.App.Api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND)
public class ResourceNotfoundException extends RuntimeException{
    private String resouceName;
    private String fieldName;
    private Long fieldValue;
    public ResourceNotfoundException(String resourceName,Long fieldValue,String fieldName)
    {
        super(String.format("%s not found with %s : '%s'", resourceName,fieldName,fieldValue));
        this.resouceName=resourceName;
        this.fieldName=fieldName;
        this.fieldValue=fieldValue;
    }

    public String getResouceName() {
        return resouceName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public Long getFieldValue() {
        return fieldValue;
    }
}
