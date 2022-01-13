package com.bs.spring2.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bs.spring2.board.model.service.BoardService;
import com.bs.spring2.board.model.vo.Board;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	@RequestMapping("/boardList.do")
	public String selectBoardList(Model model) {
		List<Board> list = service.selectBoardList();
		model.addAttribute("list", list);
		return "board/boardList";
	}
	
}
