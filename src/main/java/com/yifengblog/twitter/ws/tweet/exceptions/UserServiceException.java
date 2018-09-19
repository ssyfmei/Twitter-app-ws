package com.yifengblog.twitter.ws.tweet.exceptions;

public class UserServiceException extends RuntimeException {
    
    private static final long serialVersionUID = -4423131175780481074L;
    
    public UserServiceException(String msg) {
        super(msg);
    }
}
