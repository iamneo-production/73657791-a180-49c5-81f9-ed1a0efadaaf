package com.examly.springapp.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class ResourceNotFoundException  extends RuntimeException{
    private static final long serialVersionUID = 1L;
    private static final Logger logger = LoggerFactory.getLogger(ResourceNotFoundException.class);
    public ResourceNotFoundException(String message){
        super(message);
        //logger.error("Resource is Unidentfied : {}", message);
    }
}
