package com.sushil.moderator.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * @author Sushil Saha
 *
 * created on 2019-09-20
 */

@Data
@Builder
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class APIResponse {

    private int status;
    private String message;

    public APIResponse(HttpStatus code) {
        this.status = code.value();
    }

    public APIResponse(HttpStatus status, String message) {
        this.status = status.value();
        this.message = message;
    }

}