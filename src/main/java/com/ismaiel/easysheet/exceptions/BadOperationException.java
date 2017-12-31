
package com.ismaiel.easysheet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus (HttpStatus.BAD_REQUEST)
public class BadOperationException extends RuntimeException
{

    public BadOperationException() {
    }
    public BadOperationException(String msg,Exception e){
    super(msg);
    }
    
}
