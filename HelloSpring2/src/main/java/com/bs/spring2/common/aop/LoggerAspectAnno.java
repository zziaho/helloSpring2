package com.bs.spring2.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import lombok.extern.slf4j.Slf4j;

// Aspect클래스로 등록하기
@Component
@Aspect // 이렇게 두개 등록하면 Aspect객체로 등록된다
@Slf4j
public class LoggerAspectAnno {

//	@Pointcut("execution(* com.bs.spring2.member..*(..))") // com.bs.spring2.member 패키지 밑의 모든 클래스에서 모든 메소드를 선택
//	@Pointcut("execution(* com.bs.spring2.member.dao..*(..))") // dao패키지 밑에 있는 애들만 선택하고 싶으면!!!
	@Pointcut("execution(* com.bs.spring2.memo.model.service..insert*(..))") // insert로 시작하는 메소드!!
	public void memberCheck() {}
	
//	@Before("memberCheck()")
//	public void loggerPrint(JoinPoint jp) {
//		Signature sig = jp.getSignature();
//		
//		log.debug("===== 어노테이션 aop =====");
//		log.debug(sig.getName() + " : " + sig.getDeclaringTypeName());
//		log.debug("===== 어노테이션 aop 끝 =====");
//	}
//	
//	@After("memberCheck()")
//	public void loggerAfter(JoinPoint jp) {
//		Signature sig = jp.getSignature();
//		
//		log.debug("===== 후처리 어노테이션 aop =====");
//		log.debug(sig.getName() + " : " + sig.getDeclaringTypeName());
//		log.debug("===== 후처리 어노테이션 aop 끝 =====");
//	}
	
	
	// 전 후 처리를 동시에 하는 Around
//	@Pointcut("execution(* com.bs.spring2.memo.controller..*(..))") // controller패키지에 있는 모든 메소드
//	public void aroundTest() {};
//	@Around("aroundTest()")
	@Around("execution(* com.bs.spring2.memo.controller..*(..))") // pointcut을 따로 안주고 바로 Around에 넣어서 사용도 가능
	public Object checkTest(ProceedingJoinPoint join) throws Throwable {
		// Around의 PorceedingJoinPoint객체를 이용해서
		// 전, 후를 나누어서 처리한다. => 전, 후를 나누는 기준 : PorceedingJoinPoint.proceed()메소드를 사용 
//		log.debug("===== Around 전처리 =====");
//		StopWatch stop = new StopWatch();
//		stop.start();
		Object obj = join.proceed();
//		return join.proceed();
		log.debug("===== Around 후처리 =====");
//		stop.stop();
		Signature sig = join.getSignature();
//		log.debug(sig.getName() + " 메소드 소요시간 : " + stop.getTotalTimeMillis() + "ms");
		
		return obj;
	}
	
}



















