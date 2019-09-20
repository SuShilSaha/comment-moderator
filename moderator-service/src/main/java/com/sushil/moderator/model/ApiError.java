package com.sushil.moderator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.util.Date;

/**
 * @author Sushil Saha
 *
 * created on 2019-09-20
 */

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiError {

    private Date timestamp;
    private int status;
    private String message;
    private String details;

    public ApiError(String message, String details, HttpStatus statusCode) {
        this.message = message;
        this.details = details;
        this.status = statusCode.value();
    }

}