package com.bs.spring2;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bs.spring2.test.Department;
import com.bs.spring2.test.Employee;
import com.bs.spring2.test.Person;
import com.bs.spring2.test.Student;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	
//	@Autowired // spring bean에 등록되어있는 클래스를 생성해서 넣어라!(DI)
	// 등록된 타입이 두개 이상일때는 구분을 해서 적용을 해주어야 한다.
	/*
	 * @Qualifier(value = "p2") private Person person;
	 * 
	 * @Autowired private Employee emp;
	 * 
	 * @Autowired private Department dept;
	 * 
	 * @Autowired private Student s;
	 */
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session) {
		
		// 메소드 안에서는 안쓴다!
//		@Autowired
//		private Student s;
		
		session.setAttribute("userId", "admin");
		session.setAttribute("password", "1234");
		
		return "index";
	}
	
}
