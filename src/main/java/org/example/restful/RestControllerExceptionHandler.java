package org.example.restful;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@ControllerAdvice
@RestController
public class RestControllerExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result handlerMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        FieldError fieldError = e.getBindingResult().getFieldErrors().get(0);
        return Result.fail(ResultCode.PARAM_IS_INVALID,
                "the parameter filed: " + fieldError.getField() + "-" + fieldError.getDefaultMessage());
    }

    @ExceptionHandler(Exception.class)
    public Result commonExceptionHandler(Exception e) {
        return Result.fail(ResultCode.SERVER_INTERNAL_ERROR, ResultCode.SERVER_INTERNAL_ERROR.getMessage() + " " +  e.getMessage());
    }
}
