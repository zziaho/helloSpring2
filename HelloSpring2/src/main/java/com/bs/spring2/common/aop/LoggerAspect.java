package com.bs.spring2.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {

	public void loggerBefore(JoinPoint jp) { // JoinPoint는 호출할때 알아서 넘어온다
		// joinPoint(적용 가능 시점) : joinPoint에서 사용할 수 있는 각종 정보가 들어있다.
		// 호출된 메소드정보 확인 : Signature클래스를 사용한다
		Signature sig = jp.getSignature();
		
		log.debug("===== AOP before 적용 =====");
		log.debug(sig.getDeclaringType() + " : " + sig.getModifiers() + " : " +  sig.getName() + " : " + sig.getDeclaringTypeName());
		log.debug("===== AOP before 끝 =====");
	}
	
}
