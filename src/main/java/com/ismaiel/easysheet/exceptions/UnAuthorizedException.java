
package com.ismaiel.easysheet.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedException extends RuntimeException
{

    public UnAuthorizedException(String x,Exception e) {
        super (x);
    }

}
