package com.yifengblog.twitter.ws.tweet.security;

import com.yifengblog.twitter.ws.SpringApplicationContext;

public class SecurityConstants {
    public static final long EXPIRATION_TIME = 1000*60*20;
    public static final long PASSWORD_RESET_EXPIRATION_TIME = 1000*60*20;
    public static final String TOKEN_PREFIX ="Bearer ";
    public static final String HEADER_STRING = "AUthorization";
    public static final String SIGN_UP_URL = "/users";
    public static final String VERIFICATION_EMAIL_URL = "/users/email-verification";
    public static final String PASSWORD_RESET_REQUEST_URL = "/users/password-reset-request";
    
    public static String getTokenSecret() {
        AppProperties appProperties = 
                SpringApplicationContext.getBean("appProperties", AppProperties.class);
        return appProperties.getTokenSecret();
    }
}
