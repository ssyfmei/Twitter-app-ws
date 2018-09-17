package com.yifengblog.twitter.ws.tweet.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.yifengblog.twitter.ws.user.ui.response.ErrorMessage;

@ControllerAdvice
public class AppExceptionHandler {
    
    @ExceptionHandler(value = {UserServiceException.class})
    public ResponseEntity<Object> handleUserServiceException(
            UserServiceException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), 
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
    @ExceptionHandler(value = {TweetServiceException.class})
    public ResponseEntity<Object> handleTweetServiceException(
            TweetServiceException ex, WebRequest request) {
        ErrorMessage errorMessage = new ErrorMessage(new Date(), ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(), 
            HttpStatus.INTERNAL_SERVER_ERROR);
    }
    
}
