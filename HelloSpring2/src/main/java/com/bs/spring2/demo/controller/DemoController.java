package com.bs.spring2.demo.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bs.spring2.demo.model.service.DemoService;
import com.bs.spring2.demo.model.vo.Demo;

@Controller
public class DemoController {
	
	@Autowired
	private DemoService service;
	
	// log4j를 이용해서 로그를 출력하려면 Logger클래스를 생성해서 이용
	private Logger logger = LoggerFactory.getLogger(DemoController.class);
	

	// 요청을 처리하는 메소드를 등록
	// 기본적으로 String을 반환하는 메소드 -> springBean으로 등록된 InternalResourceViewResolver에 전달되어 view파일을 찾아서 응답함.
	// controller에 등록된 메소드는 요청에 대한 응답을 처리하는 것으로, 요청(주소)을 받을 수 있는 설정 -> 주소매핑을 해주는 설정을 해야한다.
	// => @RequestMapping("주소값") || @RequestMapping(name="" 등 속성)
	@RequestMapping("/demo/demo.do")
	public String demoView() {
		logger.debug("debug");
		logger.info("info");
		logger.warn("warn");
		logger.error("error");
		
		// 화면을 출력해주는 응답을 하자
//		return "view이름";
//		return "demo"; -> /WEB-INF/views/ + demo + .jsp => 폴더를 다 안찾아가니까 jsp파일을 못찾음
		return "demo/demo"; // /WEB-INF/views/ + demo/demo + .jsp
	}
	
	/*
	 * controller에서 선언한 메소드는 서블릿에서의 doGet, doPost()메소드와 동일한 기능을 한다.
	 * -> 하나의 메소드가 하나의 서블릿과 같은 역할을 한다고 보면 돼~
	 *  controller에서 선언한 메소드도 매개변수로 프론트(화면)에서 보낸 데이터를 가져 올 수 있다.
	 *  => 매개변수를 사용해서
	 *  선언이 가능한 매개변수는 정해져있다.
	 *  1. HttpServletRequest
	 *  2. HttpServletResponse
	 *  3. HttpSession
	 *  4. java.util.Locale -> 지역정보
	 *  5. InputStream/Reader
	 *  6. OutputStream/Writer
	 *  ------- 데이터를 저장하는 매개변수 선언 -------
	 *  7. Vo객체 선언 -> 파라미터의 name값과 일치하는 멤버변수에 값을 대입해서 생성
	 *  8. Map선언 -> key:value형식으로 파라미터의 name:value값을 그대로 받아온다.
	 *  
	 *  9. Model -> 프론트(jsp)로 전달할 데이터를 보관하는 객체
	 *  10. ModelAndView -> jsp에 전달할 데이터와 연결할 view이름을 저장하는 객체
	 *  
	 *  ----- 어노테이션을 선언하여 값을 받는 매개변수 -----
	 *  @RequestParam(value="parameterName값") name값을 저장할 자료형 변수명
	 *  @RequestHeader(value="header의 key값") header데이터 저장할 자료형 변수명
	 *  @CookieValue(value="cookie key값")
	 *  
	 *  ----- 추가 어노테이션 -----
	 *  @PathValiable("값") 자료형 변수명 -> 요청주소에 있는 값을 불러올 때 사용
	 *  	-> REST방식으로 주소가 설계되어있을 때, 주소의 데이터르 가져올 때 사용
	 *  		일반방식 : boardView.do?no=1 =>  REST방식 : board/1
	 *  메소드 선언부에 선언
	 *  @ResponseBody : 반환하는 데이터를 JSON으로 반환해준다. -> viewResolver를 통과하지 않고 json데이터로 반환하게 되는 메소드
	 *  	-> ajax 처리할때 주로 사용
	 *  @RequestBody : 화면에서 json방식으로 전달된 데이터를 객체로 연결해서 받을 때 
	 */
	
	// 서블릿 방식으로 데이터를 받아보자
	@RequestMapping("/demo/demo1.do")
//	public void demo1(HttpServletRequest req, HttpServletResponse res) throws IOException{
//		System.out.println(req);
//		System.out.println(res);
//		System.out.println(req.getParameter("devName"));
//		System.out.println(req.getParameter("devAge"));
//		System.out.println(req.getParameter("devEmail"));
//		System.out.println(req.getParameter("devGender"));
//		for(String l : req.getParameterValues("devLang")) {
//			System.out.println(l);
//		}
//		
//		res.setContentType("text/html;charset=utf-8");
//		res.getWriter().write("전송완료");
	public String demo1(HttpServletRequest req, HttpServletResponse res, HttpSession session) {
		Demo d = Demo.builder()
				.devName(req.getParameter("devName"))
				.devAge(Integer.parseInt(req.getParameter("devAge")))
				.devEmail(req.getParameter("devEmail"))
				.devGender(req.getParameter("devGender"))
//				.devLang(req.getParameterValues("devLang"))
				.build();
		
		req.setAttribute("demo", d);
		
		System.out.println("session출력하기");
		System.out.println(session.getAttribute("userId"));
		System.out.println(session.getAttribute("password"));
		
		return "demo/demoResult"; // 화면전환 -> RequestDispatcher.forward()
	}
	
	// 프론트에서 보낸 데이터를 1:1로 매칭해서 데이터 받기
	// @RequestParam 어노테이션을 이용한다.
	@RequestMapping("/demo/demo2.do")
	public String demo2(
			@RequestParam(value="devName") String name,
			@RequestParam(value="devAge") int age,
			@RequestParam(value="devGender") String gender,
			@RequestParam(value="devEmail") String email,
			@RequestParam(value="devLang") String[] dlang,
			Model m) {
		
		System.out.println(name + age + gender + email);
		for(String l : dlang) {
			System.out.println(l);
		}
		
		Demo d = Demo.builder()
				.devName(name)
				.devAge(age)
				.devGender(gender)
				.devEmail(email)
//				.devLang(dlang)
				.build();
		// request클래스를 사용하지 않고 Model클래스를 이용해서 필요한 데이터를 view에 전달할 수 있음. ==> request와 같은 scope범위를 가짐 
		m.addAttribute("demo", d);
		
		return "demo/demoResult";
	}
	
	// 1:1로 전송된 데이터를 받을때
	// 매개변수에 전송데이터의 key값과 일치하게 변수명을 설정하면
	// @RequestParam어노테이션 없이 자동으로 대입됨
	@RequestMapping(value="/demo/autoParam.do")
	public String autoParam(String devName, String devEmail, int devAge, String devGender, String[] devLang, Model model) {
		System.out.println(devName + devAge + devEmail + devGender);
		for(String l : devLang) {
			System.out.println(l);
		}
		
		Demo d = Demo.builder()
				.devName(devName)
				.devEmail(devEmail)
				.devAge(devAge)
				.devGender(devGender)
//				.devLang(devLang)
				.build();
		
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
	}

	
	// Command로 전송된 데이터 받기
	// vo객체로 데이터 직접받기
	// 멤버변수명과 전달되는 데이터의 key명칭이 동일해야함.
	@RequestMapping(value="/demo/demo3.do")
	public String demo3(Demo d, Model model) {
		System.out.println(d);
		model.addAttribute("demo", d);
		
		return "demo/demoResult";
		
	}
	
	// Vo없이 모든 전달된 데이터 받아오기
	// Map으로 데이터 받기
	@RequestMapping(value="/demo/demo4.do")
	public String demo4(@RequestParam Map param, Model model) {
		System.out.println(param);
		
		model.addAttribute("demo", param);
		
		return "demo/demoResult";
	}
	
	@RequestMapping(value="/insertDemo.do", method=RequestMethod.POST)
	public String insertDemo(Demo dev) {
		int result = service.insertDemo(dev);
		System.out.println(result > 0 ? "입력성공" : "입력실패");
		return "demo/demo"; 
	}
	
	@RequestMapping("/demoList.do")
	public String selectDemoList(Model model) {
		
		List<Demo> list = service.selectDemoList();
		model.addAttribute("list", list);
		
		return "demo/demoList.do";
		
	}
	
	
}

















































