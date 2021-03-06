package com.bridgelabz.atmsystem.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Purpose : To implement CustomGlobalExceptionHandler in atm-system
 *
 * @author : DAMINI CHANDRAKAR
 * @since : 03-12-2021
 */
@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Purpose : To handle Method Argument Not Valid.
     *
     * @param ex      : ex is used for Method Argument Not Valid Exception.
     * @param headers : headers is used for HTTP headers of the HTTP message.
     * @param status  : status used to describe HTTP status code is a server response to a browser's request.
     * @param request : request is used to describe web request that is a communicative message,
     *                that is transmitted between the client or web browsers, to the servers.
     * @return : errors, headers and status of response entity.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status,
                                                                  WebRequest request) {
        Map<String, Object> errors = new HashMap<>();
        errors.put("timestamp", new Date());
        errors.put("status", HttpStatus.BAD_REQUEST.value());

        List<String> error = ex.getBindingResult()
                .getFieldErrors()
                .stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList());
        errors.put("errors", error);
        return new ResponseEntity<Object>(errors, headers, status);
    }

    /**
     * Purpose : To handle Http Request Method Not Supported Exception.
     *
     * @param ex      : ex used for Http Request Method Not Supported Exception.
     * @param headers : headers is used for HTTP headers of the HTTP message.
     * @param status  : status used to describe HTTP status code is a server response to a browser's request.
     * @param request : request is used to describe web request that is a communicative message,
     *                that is transmitted between the client or web browsers, to the servers.
     * @return : Please Check http Method Type for the bad request http status.
     */
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        return new ResponseEntity<Object>("Please Check http Method Type", HttpStatus.BAD_REQUEST);
    }

    /**
     * Purpose : To handle method level exception
     *
     * @param e : e used for AtmCustom Exception
     * @return : response entity of object type and status
     */
    @ExceptionHandler
    public ResponseEntity<Object> handleAtmCustomException(AtmCustomException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.NOT_FOUND.value());
        errorResponse.setErrors(e.getMessage());
        errorResponse.setTimestamp(new Date());
        return new ResponseEntity<Object>(errorResponse, HttpStatus.NOT_FOUND);
    }

    /**
     * Purpose : To handle all exception
     *
     * @param e : e used for Exception
     * @return : response entity of object type and status
     */
    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorResponse.setErrors("Card Number should be uniqe");
        errorResponse.setTimestamp(new Date());
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
