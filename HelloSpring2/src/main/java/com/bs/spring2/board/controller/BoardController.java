package com.bs.spring2.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@RequestMapping("/boardCount.do")
	public String countBoardList(Model model) {
		int result = service.countBoardList();
		model.addAttribute("totalContents", result);
		return "board/boardList";
	}
	
	@RequestMapping("/boardView.do")
	public String selectBoard(@RequestParam int boardNo, Model model) {
		Board b = service.selectBoard(boardNo);
		model.addAttribute("board", b);
		return "board/boardView";
	}
	
}










