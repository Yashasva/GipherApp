package com.stackroute.giphy.aspect;

//public class LoggingAspect {
//
//}

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component 
public class LoggingAspect {
	Logger mylogger=LoggerFactory.getLogger(LoggingAspect.class);
	
		 @Before("execution(* com.stackroute.giphy.controller.FeedbackController.*(..))")
	    public void logBefore(JoinPoint point) {
			 mylogger.info(point.getSignature().getName() + " before called...");
	    }
	 
	 @After("execution(* com.stackroute.giphy.controller.FeedbackController.*(..))")
	    public void logAfter(JoinPoint point) {
		 mylogger.info(point.getSignature().getName() + " after called...");
	    }
	 
	 @AfterReturning("execution(* com.stackroute.giphy.controller.FeedbackController.*(..))")
	    public void logAfterReturning(JoinPoint point) {
		 mylogger.info(point.getSignature().getName() + " after returning called...");
	    }
	 
	 @AfterThrowing("execution(* com.stackroute.giphy.controller.FeedbackController.*(..))")
	    public void afterThrowing(JoinPoint point) {
		 mylogger.info(point.getSignature().getName() + " afterThrowing called...");
	      
	    }
}	

