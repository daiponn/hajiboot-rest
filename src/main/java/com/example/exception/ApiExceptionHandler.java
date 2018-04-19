package com.example.exception;

import com.example.exception.message.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;


@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{



    @ExceptionHandler
    public ResponseEntity<Object> handleCustomerNotFoundException(CustomerNotfoundException ex, WebRequest request){
        return handleExceptionInternal(ex, null, null, HttpStatus.NOT_FOUND, request);
    }

    //パスパラメーターの値が不正(バリデーションエラー)な場合
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage("パラメーターが不正です。");
        return handleExceptionInternal(ex, message, null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler
    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request){
        ErrorMessage message = new ErrorMessage("Internal Server Error");
        return handleExceptionInternal(ex, message, null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorMessage message = new ErrorMessage("Jsonが不正です");
        return handleExceptionInternal(
                exception,
                message,
                null,
                HttpStatus.BAD_REQUEST,
                request);
    }






}
