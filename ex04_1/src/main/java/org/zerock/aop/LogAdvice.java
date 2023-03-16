package org.zerock.aop;

import java.util.Arrays;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j;

@Component
@Log4j
@Aspect
public class LogAdvice {
	
	@Before("execution(* org.zerock.service.SampleService*.*(..))")
	
	public void logBefore() {
		log.info("===========SampleServiceImple 메소드 실행==================");
		
	}
	
	@Before( "execution(* org.zerock.service.SampleService*.doAdd(String, String)) && args(str1,str2)")
		
	public void logBeforeWithParam(String str1, String str2) {
		log.info("str1 : " + str1);
		log.info("str2 : " + str2);
	}
	
	@AfterThrowing(pointcut = "execution(* org.zerock.service.SampleService*.*(..))", throwing="exception")
	
		public void logException(Exception exception) {
		
		log.info("exception...............");
		log.info("exception : " + exception);
	}
	
	@After("execution(* org.zerock.service.SampleService*.*(..))")
	public void logAfter() {
		log.info("===========SampleServiceImple 메소드 종료 후==========================");
	}
	
	@Around("execution(* org.zerock.service.SampleService*.*(..))")
	
	public Object logTime( ProceedingJoinPoint pjp) {
		
		long start = System.currentTimeMillis();
		
		log.info("Target" + pjp.getTarget());
		
		log.info("Name" + pjp.getClass().getName());
		
		log.info("Param" + Arrays.toString(pjp.getArgs()));
		
		Object result = null;
		
		try {
			result = pjp.proceed();
		} catch (Throwable e) {
			e.printStackTrace();
		}
		
		long end = System.currentTimeMillis();
		
		log.info("TIME : " +(end - start));
		
		return result;
		
	}

}
