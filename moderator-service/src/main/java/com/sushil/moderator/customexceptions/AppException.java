package com.sushil.moderator.customexceptions;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Sushil Saha
 *
 */

@Data
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AppException extends RuntimeException {

    int status;

    /** application specific error code */
    String code;

    /** property documenting the exception */
    String property;

    /** detailed error description for developers*/
    String message;

    @Override
    public synchronized Throwable initCause(Throwable cause) {
        return super.initCause(cause);
    }

    public AppException(String message){
        super(message);
        this.message = message;
    }

    public AppException(String property, String message){
        this.property = property;
        this.message = message;
    }

}