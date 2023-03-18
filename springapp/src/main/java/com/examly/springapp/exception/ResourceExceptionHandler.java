package com.examly.springapp.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



@ControllerAdvice
public class ResourceExceptionHandler {
    private static final Logger logger = LoggerFactory.getLogger(ResourceExceptionHandler.class);
    @ExceptionHandler(value=ResourceNotFoundException.class)
    public ResponseEntity<Object> exception(ResourceNotFoundException ex){
        logger.error("Resource is Unidentfied : {}", ex.getMessage());
        return new ResponseEntity<>("Resource is not in existence ",HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler({ AccessDeniedException.class })
    public ResponseEntity<Object> handleAccessDeniedException(Exception ex) {
       // logger.error("Access is Denied for unauthorised usage : {}", ex.getMessage(), ex);
       logger.error(ex.toString());
        return new ResponseEntity<>(
          "ACCESS DENIED for requested resource",HttpStatus.FORBIDDEN);
    }
    @ExceptionHandler({Exception.class})
    public ResponseEntity<Object> handleUnknownException(Exception ex){
        logger.error("Ran into Exception : {}", ex.getMessage());
        return new ResponseEntity<>(
          "Exception has Occured",HttpStatus.SEE_OTHER);
    }
    
}
