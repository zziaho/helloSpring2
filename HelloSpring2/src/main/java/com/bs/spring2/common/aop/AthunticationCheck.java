package com.bs.spring2.common.aop;

import javax.servlet.http.HttpSession;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.bs.spring2.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Component
@Aspect
@Slf4j
public class AthunticationCheck {
	
	@Before("execution(* com.bs.spring2..insert*(..))") // spring2 패키지 밑에 insert가 들어간 모든 메소드를 실행하기 전에!
	public void check(JoinPoint jp) {
		// 로그인 여부를 확인 후 insert메소드를 실행하기
		log.debug("===== 로그인 체크하기 =====");
		
		// springContainer가 제공하는 객체인데 request에 대한 값들을 가져올 수 있게 해준다 : RequestContextHolder
		HttpSession session = (HttpSession)RequestContextHolder.currentRequestAttributes().resolveReference(RequestAttributes.REFERENCE_SESSION);
		Member loginMember = (Member)session.getAttribute("loginMember");
		
		// 메소드 실행시 전달되는 매개변수 확인하기
		Object[] args = jp.getArgs();
		for(Object p : args) {
			log.debug(p.toString());
		}
		
		if(loginMember == null) {
			// aop에서 로직을 중단하고 싶을 때
			// Exception을 발생시킨다.
			try {
				throw new Exception("사용자 이용권한이 없습니다.");
			}catch(Exception e) {
				throw new RuntimeException(e.getMessage());
			}
		}
	}
	
}









