package com.keys.api.controller;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.keys.api.exceptions.ObjectNotFoundException;
import com.keys.api.exceptions.UserAlreadyException;


//controller to customize the api's exception

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Resource
    private MessageSource messageSource;

    @ExceptionHandler( { UserAlreadyException.class } )
    public ResponseEntity< Object > handleUserAlreadyExistException ( UserAlreadyException ex, WebRequest request ) {
        return handleExceptionInternal( ex, new Error( ex.getMsg() ), new HttpHeaders(), HttpStatus.BAD_REQUEST,
            request );
    }

    @ExceptionHandler( { ObjectNotFoundException.class } )
    public ResponseEntity< Object > objectNotFoundException ( ObjectNotFoundException ex, WebRequest request ) {
        return handleExceptionInternal( ex, ex.getMsg(), new HttpHeaders(), HttpStatus.NOT_FOUND, request );
    }

    @Override
    protected ResponseEntity< Object > handleMethodArgumentNotValid ( MethodArgumentNotValidException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request ) {
        String errorMessage = messageSource.getMessage(
            ex.getBindingResult().getFieldErrors().get( 0 ), LocaleContextHolder.getLocale()
        );
        return handleExceptionInternal( ex, errorMessage, headers, status, request );
    }

    public static class Error {
        private String error;

        public Error ( String error ) {
            this.error = error;
        }

        public String getError () {
            return error;
        }

        public void setError ( String error ) {
            this.error = error;
        }

    }

}
