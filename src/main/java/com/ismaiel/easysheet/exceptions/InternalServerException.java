
package com.ismaiel.easysheet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException
{
    
    public InternalServerException(String msg,Exception e) {
        super(msg);
    }
    
}
