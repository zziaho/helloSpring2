package com.bs.spring2.member.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.bs.spring2.member.model.service.MemberService;
import com.bs.spring2.member.model.vo.Member;

@Controller // 어노테이션으로 springBean으로 등록
@SessionAttributes({"loginMember"}) // session scope에 올리고 싶은애를 지정해놓고 사용이 가능하다.
public class MemberController {
	
	@Autowired // 의존성이 있는 객체들은 직접생성 X -> @Autowired 사용
	private MemberService service;
	
	// 암호화처리클래스 불러오기
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	private Logger logger = LoggerFactory.getLogger(MemberController.class);

	@RequestMapping("/member/memberLogin.do") // 메소드별 작동 하도록 mapping -> 핸들러매퍼에 의해서 해당 매핑주소를 찾아서 실행
//	public String login(@RequestParam Map param, HttpSession session) { // 프론트에서 보내는 값을 매개변수로 받아온다.
	// HttpSession객체를 이용하지 않고 session scope데이터 관리하기
	// @SessionAttribute({모델저장데이터})어노테이션 이용
	public String login(@RequestParam Map param, Model model) {
		Member m = service.login(param);
		
		// 암호화 된 비밀번호와 입력한 비밀번호 비교하기
		// matches(암호화되지 않은 문자열, 암호화 된 문자열)
		encoder.matches((String)param.get("password"), m.getPassword());
		
//		System.out.println(m);
		logger.debug("{}", m); // debug()에는 String밖에 못들어감
		
//		if(m != null) session.setAttribute("loginMember", m);
		if(m != null && encoder.matches((String)param.get("password"), m.getPassword())) {
			model.addAttribute("loginMember", m);
		}
		
		// RequestDispatcher.forward방식으로 전환되어 url이 변경되지 않음!
		// sendRedirect방식으로 보내야한다.
//		return "index"; // index 화면으로 전환
		// 반환값(String)에 "redirect:주소" -> 이렇게 하면 viewResolver에 전달되지 않아!!
		return "redirect:/";
		
	}
	
	@RequestMapping("/member/logout.do")
	public String logout(HttpSession session, SessionStatus status) {
		// model의 데이터를 @Session Attributes어노테이션으로 session scope에 올렸으면
		// SessionStatus클래스를 이용해서 관리한다.
		// session값을 제거할때는 SessionStatus의 setComplete()메소드를 사용한다.
		if(!status.isComplete()) {
			status.setComplete();
		}
//		session.invalidate();
		return "redirect:/";
	}
	
	@RequestMapping("/member/enrollMember.do")
	public String enrollMember() {
		return "member/enrollMember";
	}
	
	@RequestMapping(value="/member/insertMember.do", method=RequestMethod.POST)
	public String enrollMemberEnd(Member m, Model model) {
		// 패스워드 암호화 하려면
		// 클래스가 제공하는 encode()메소드를 이용
		// encode(암호화 할 문자열)
//		System.out.println(m.getPassword());
		logger.debug("변경전 패스워드 : {}", m.getPassword());
//		System.out.println(encoder.encode(m.getPassword()));
		logger.debug("변경후 패스워드 : {}", encoder.encode(m.getPassword()));
		
		m.setPassword(encoder.encode(m.getPassword()));
		
		int result = service.insertMember(m);
		String msg = "";
		String loc = "";
		if(result > 0) {
			msg = "회원가입 성공";
			loc = "/";
		}else {
			msg = "회원가입 실패";
			loc = "member/enrollMember.do";
		}
		
		model.addAttribute("msg", msg);
		model.addAttribute("loc", loc);
		return "common/msg";
	}
	
//	@RequestMapping("/member/memberInfo.do")
//	public String selectOneMember() {
//		Member m = service.selectOneMember();
//	}
}






















