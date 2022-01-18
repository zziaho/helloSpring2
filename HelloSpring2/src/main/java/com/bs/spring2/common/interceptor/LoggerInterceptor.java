package com.bs.spring2.common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

// 인터셉터로 등록한다.
// 인터셉터로 등록하기 위해서는 HandlerInterceptor인터페이스나 HandlerInterceptorAdaptor클래스를 구현, 상속해야 한다.
// public class LoggerInterceptor implements HandlerInterceptor {
@Slf4j
 public class LoggerInterceptor extends HandlerInterceptorAdapter {
	 
//	 private Logger logger = LoggerFactory... 매번 이렇게 쓰기 귀찮. => lombok이 해준다

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.debug("----- 전처리 인터셉터 실행! -----");
		log.debug("요청주소 : {}", request.getRequestURI());
		log.debug("----- 전처리 끝 -----");
		
		return super.preHandle(request, response, handler); // super로 인해서 무조건 넘어감
//		return false; // 응답 안넘어감
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// 매핑 메소드가 실행이 끝난 후 실행되는 로직을 구현
		log.debug("----- 매핑메소드 종료 후 호출 -----");
		log.debug("요청주소 : {}", request.getRequestURI());
		log.debug("----- 인터셉터 종료 -----");
		
//		super.postHandle(request, response, handler, modelAndView);
	}
	
	
	
	

}
