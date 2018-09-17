package com.yifengblog.twitter.ws.tweet.exceptions;

public class TweetServiceException extends RuntimeException{
    
    private static final long serialVersionUID = -8164426591405438370L;

    public TweetServiceException(String msg) {
        super(msg);
    }
}
