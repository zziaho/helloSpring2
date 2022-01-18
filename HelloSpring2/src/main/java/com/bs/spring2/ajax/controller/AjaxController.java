package com.bs.spring2.ajax.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bs.spring2.board.model.service.BoardService;
import com.bs.spring2.board.model.vo.Board;
import com.bs.spring2.member.model.service.MemberService;
import com.bs.spring2.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class AjaxController {

	@Autowired
	private MemberService service;
	
	@Autowired
	private BoardService boardService;
	
	@RequestMapping("/ajax/checkId")
	public void checkIdBasic(String userId, HttpServletResponse response) throws IOException {
		log.debug(userId);
		Member m = service.login(Map.of("userId", userId));
		response.setContentType("application/json;charset=utf-8");
		
		response.getWriter().print(m != null ? false : true);
	}
	
	@RequestMapping("/ajax/jacksonCheckId")
	@ResponseBody
//	public boolean jacksonCheckId(String userId) {
//	public Member jacksonCheckId(String userId) {
	public Map jacksonCheckId(String userId) {
		Member m = service.login(Map.of("userId", userId));
		List<Board> boards = boardService.selectBoardList(1, 10);
		
//		return m != null ? false : true;
//		return m;
		return Map.of("member", m, "list", boards);
	}
	
}
