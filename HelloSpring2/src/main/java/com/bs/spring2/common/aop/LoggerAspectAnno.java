package com.bs.spring2.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

// Aspect클래스로 등록하기
@Component
@Aspect // 이렇게 두개 등록하면 Aspect객체로 등록된다
@Slf4j
public class LoggerAspectAnno {

	@Pointcut("execution(* com.bs.spring2.member..*(..))") // com.bs.spring2.member 패키지 밑의 모든 클래스에서 모든 메소드를 선택
	public void memberCheck() {}
	
	@Before("memberCheck()")
	public void loggerPrint(JoinPoint jp) {
		Signature sig = jp.getSignature();
		
		log.debug("===== 어노테이션 aop =====");
		log.debug(sig.getName() + " : " + sig.getDeclaringTypeName());
		log.debug("===== 어노테이션 aop 끝 =====");
	}
	
}
