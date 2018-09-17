package com.yifengblog.twitter.ws.tweet.io.repository.aspect; 

import org.aspectj.lang.ProceedingJoinPoint; 
import org.aspectj.lang.annotation.Around; 
import org.aspectj.lang.annotation.Aspect; 
import org.springframework.stereotype.Component; 
import com.yifengblog.twitter.ws.tweet.exceptions.UserServiceException; 
import com.yifengblog.twitter.ws.user.ui.response.ErrorMessageEnum; 

@Aspect 
@Component 
public class EntityReadAspect { 
    
//    //@Around("execution(* com.yifengblog.twitter.ws.tweet.io.entity.UserEntity.findByUserId(..))")
//    @Around("execution(* findBy*(..))")
//    public Object afterReadQuery(ProceedingJoinPoint joinPoint) throws Throwable { 
//        Object result = joinPoint.proceed(); 
//        if(result == null) { 
//            throw new UserServiceException( ErrorMessageEnum.NO_RECORD_FOUND.getErrorMessage()); 
//        } 
//        return result; 
//    } 
}
