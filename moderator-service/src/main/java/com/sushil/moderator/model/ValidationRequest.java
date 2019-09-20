package com.sushil.moderator.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * @author Sushil Saha
 *
 * created on 2019-09-20
 */

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ValidationRequest {

    @NotBlank(message = "input message cannot be empty")
    @ApiModelProperty(notes = "string to be validated")
    private String message;

}