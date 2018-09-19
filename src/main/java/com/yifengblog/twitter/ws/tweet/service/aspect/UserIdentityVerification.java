package com.yifengblog.twitter.ws.tweet.service.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.yifengblog.twitter.ws.tweet.exceptions.UserServiceException;
import com.yifengblog.twitter.ws.tweet.io.entity.UserEntity;
import com.yifengblog.twitter.ws.tweet.io.repository.UserRepository;
import com.yifengblog.twitter.ws.user.ui.response.ErrorMessageEnum;

@Component
@Aspect
public class UserIdentityVerification {
    
    @Autowired
    UserRepository userRepository;

    @Before("execution(* "
         + "com.yifengblog.twitter.ws.tweet.service.impl.UserServiceImpl.*User(..)) && args(userId,..)")
    public void authenticateUserOp(JoinPoint joinPoint, String userId) {
        String userEmail = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(!userEntity.getEmail().equals(userEmail)) {
            throw new UserServiceException( ErrorMessageEnum.COULD_NOT_ACCESS_RECORD.getErrorMessage()); 
        }
    }
//    public void authenticateTweetOp() {
//    
//    }
    
    
//    public void authenticateFriendOp() {
//        
//    }
    
}
