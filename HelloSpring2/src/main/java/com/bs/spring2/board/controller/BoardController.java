package com.bs.spring2.board.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring2.board.model.service.BoardService;
import com.bs.spring2.board.model.vo.Board;
import com.bs.spring2.common.PageFactory;

@Controller
@RequestMapping("/board")
public class BoardController {

	@Autowired
	private BoardService service;
	
	// 게시글 전체 목록 불러오기 + 페이징처리
//	@RequestMapping("/boardList.do")
//	public String selectBoardList(@RequestParam(value="cPage", defaultValue="1") int cPage, @RequestParam(value="numPerpage", defaultValue="5") int numPerpage, Model model) {
//		List<Board> list = service.selectBoardList();
//		model.addAttribute("list", list);
//		return "board/boardList";
//	}
	// 게시글 전체 목록 불러오기 + 페이징처리
	@RequestMapping("/boardList.do")
	public ModelAndView selectBoardList(@RequestParam(value="cPage", defaultValue="1") int cPage, @RequestParam(value="numPerpage", defaultValue="5") int numPerpage, ModelAndView mv) {
		List<Board> list = service.selectBoardList(cPage, numPerpage);
		
		int totalData = service.countBoardList();
		mv.addObject("totalContents", totalData);
		mv.addObject("pageBar", PageFactory.getPageBar(totalData, cPage, numPerpage, 5, "boardList.do"));
		
		mv.addObject("list", list);
		mv.setViewName("board/boardList");
		return mv;
	}
	
//	@RequestMapping("/boardCount.do")
//	public String countBoardList(Model model) {
//		int result = service.countBoardList();
//		model.addAttribute("totalContents", result);
//		return "board/boardList";
//	}
	
	@RequestMapping("/boardView.do")
	public String selectBoard(@RequestParam int boardNo, Model model) {
		Board b = service.selectBoard(boardNo);
		model.addAttribute("board", b);
		return "board/boardView";
	}
	
	// 게시글 쓰기 화면으로 전환만 시켜준다
	@RequestMapping("/boardInsert.do")
	public String insertBoardView() {
		return "board/boardInsert";
	}
	
	// 게시글 쓰기 등록
	@RequestMapping("/boardInsertEnd.do")
	public String insertBoard(Board b, Model model) {
		int result = service.insertBoard(b);
		if(result > 0) {
			
		}else {
			
		}
		return "board/boardList";
	}
	
}










