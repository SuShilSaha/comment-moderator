package com.sushil.moderator.customexceptions;

import com.sushil.moderator.model.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.List;

/**
 * @author Sushil Saha
 *
 * created on 2019-09-19
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AppException.class)
    public ResponseEntity<Object> handleAppException(AppException ex, WebRequest request) {
        return new ResponseEntity<>(new ApiError("error " + ex.getMessage(), request.getDescription(false), HttpStatus.BAD_REQUEST), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleAllExceptionMethod(Exception ex) {

        ApiError exceptionMessageObj = new ApiError();

        if(ex instanceof MethodArgumentNotValidException) {
            StringBuilder sb = new StringBuilder();
            List<FieldError> fieldErrors = ((MethodArgumentNotValidException) ex).getBindingResult().getFieldErrors();
            for(FieldError fieldError: fieldErrors){
                sb.append(fieldError.getDefaultMessage());
                sb.append(";");
            }
            exceptionMessageObj.setMessage(sb.toString());
        } else {
            exceptionMessageObj.setMessage(ex.getLocalizedMessage());
        }

        exceptionMessageObj.setStatus(HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(exceptionMessageObj, HttpStatus.OK);
    }

}