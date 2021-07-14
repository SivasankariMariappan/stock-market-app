package com.org.fse.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyNotFoundException  extends  RuntimeException{
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CompanyNotFoundException(String message){
        super(message);
        logger.error("CompanyNotFound exception thrown"+message);
    }
}
