package com.osho.restBasics.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// (Optional) Exception class for throwing customized exceptions;
// Not working as of 221015 (not displaying exception in browser or intellij console)
// TODO: fix this so it gives accurate messages that are displayed in console (Postman)

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L; // Version control of objects

    private String name;
    private String field;
    private Object value;

    public ResourceNotFoundException() {
    }

    public ResourceNotFoundException(String name, String field, Object value) {
        super(String.format("%s not found with %s : '%s'", name, field, value));
        this.name = name;
        this.field = field;
        this.value = value;
    }

//    public ResourceNotFoundException(String message) {
//        super(message);
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
