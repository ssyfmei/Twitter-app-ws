package com.yifengblog.twitter.ws.tweet.security;

import com.yifengblog.twitter.ws.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 6000000;
    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String HEADER_STRING = "AUthorization";
    public static final String SIGN_UP_URL = "/users";
    
    public static String getTokenSecret() {
        AppProperties appProperties = 
                SpringApplicationContext.getBean("appProperties", AppProperties.class);
        return appProperties.getTokenSecret();
    }
}
