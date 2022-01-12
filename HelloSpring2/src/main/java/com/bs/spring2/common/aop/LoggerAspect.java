package com.bs.spring2.common.aop;

import org.aspectj.lang.JoinPoint;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LoggerAspect {

	public void loggerBefore(JoinPoint jp) { // JoinPoint는 호출할때 알아서 넘어온다
		log.debug("===== AOP before 적용 =====");
		log.debug("===== 첫 AOP =====");
		log.debug("===== AOP before 끝 =====");
	}
	
}
