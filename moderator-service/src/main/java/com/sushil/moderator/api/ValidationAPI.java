package com.sushil.moderator.api;

import com.sushil.moderator.constants.APIConstant;
import com.sushil.moderator.model.APIResponse;
import com.sushil.moderator.model.ValidationRequest;
import com.sushil.moderator.services.ValidationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @author Sushil Saha
 *
 * created on 2019-09-20
 */

@RestController
@Api(value = APIConstant.VALIDATE)
@RequestMapping(value = APIConstant.VALIDATE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ValidationAPI {

    @Autowired
    private ValidationService validationService;

    @PostMapping
    @ApiOperation(value = "validate a input against blocked words")
    private APIResponse validateInput(@NotNull @Valid @RequestBody ValidationRequest validationRequest) {
        return validationService.isValidContent(validationRequest);
    }

}