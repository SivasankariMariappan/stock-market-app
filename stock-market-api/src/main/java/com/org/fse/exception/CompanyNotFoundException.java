package com.org.fse.exception;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CompanyNotFoundException  extends  RuntimeException{
    private final Logger logger = LogManager.getLogger(this.getClass());

    public CompanyNotFoundException(String message){
        super(message);
        logger.error("CompanyNotFound exception thrown"+message);
    }
}
