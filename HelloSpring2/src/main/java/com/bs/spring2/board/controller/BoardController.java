package com.bs.spring2.board.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.bs.spring2.board.model.service.BoardService;
import com.bs.spring2.board.model.vo.Attachment;
import com.bs.spring2.board.model.vo.Board;
import com.bs.spring2.common.PageFactory;
import com.bs.spring2.member.model.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/board")
@Slf4j
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
	
	// 게시글 상세보기
//	@RequestMapping("/boardView.do")
//	public String selectBoard(@RequestParam int boardNo, Model model) {
//		Board b = service.selectBoard(boardNo);
//		model.addAttribute("board", b);
//		return "board/boardView";
//	}
	
	// 게시글 상세보기
	@RequestMapping("/boardView.do")
	public ModelAndView selectBoard(int boardNo, ModelAndView mv) {
		mv.addObject("board", service.selectBoard(boardNo));
		mv.setViewName(null);
		return mv;
	}
	
	
	// 게시글 쓰기 화면으로 전환만 시켜준다
	@RequestMapping("/boardInsert.do")
	public String insertBoardView() {
		return "board/boardInsert";
	}
	
	// 게시글 쓰기 등록
	@RequestMapping(value="/boardInsertEnd.do", method=RequestMethod.POST)
	public ModelAndView insertBoard(Board b, String writer,ModelAndView mv, @RequestParam(value="upFile", required=false) MultipartFile[] upFile, HttpServletRequest req) {
		
	      log.debug(upFile[0].getOriginalFilename());
	      log.debug("{}",upFile[0].getSize());
	      log.debug(upFile[1].getOriginalFilename());
	      log.debug("{}",upFile[1].getSize());
	      
	      b.setBoardWriter(new Member());
	      b.getBoardWriter().setUserId(writer);
	      
	      //전달된 파일 업로드처리하기
	      //1. 저장경로 불러오기 -> HttpServletRequest 필요
	      String path=req.getServletContext().getRealPath("/resources/upload/board/");
	      File f=new File(path);
	      if(!f.exists()) f.mkdirs();
	      b.setFiles(new ArrayList<Attachment>());
	      for(MultipartFile mf : upFile) {
	    	  if(!mf.isEmpty()) {
	    		  //파일 리네임처리를 직접처리
	    		  String originalFileName = mf.getOriginalFilename();
	    		  String ext = originalFileName.substring(originalFileName.lastIndexOf("."));
	    		  
	    		  //리네임규칙설정
	    		  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd_HHmmsssss");
	    		  int rndNum=(int)(Math.random()*1000);
	    		  String renameFile=sdf.format(System.currentTimeMillis()) + "_" + rndNum + ext;
	    		  
	    		  //리네임명으로 파일저장하기
	    		  //multipartFile클래스에서 파일을 저장하는 메소드를 제공함. -> transferTo(파일객체);
	    		  try {
	    			  mf.transferTo(new File(path+renameFile));
//	    			  b.setFiles(new ArrayList<Attachment>());
	    			  Attachment file=new Attachment();
	    			  file.setOriginalFilename(originalFileName);
	    			  file.setRenamedFilename(renameFile);
	    			  b.getFiles().add(file);
	    		  }catch(IOException e) {
	    			  e.printStackTrace();
	    		  }
	     	}
	      
	      }
	      String msg = "";
	      String loc = "";
	      try {
	    	  int result = service.insertBoard(b);
	    	  msg = "등록성공";
	    	  loc = "/board/boardList.do";
	      }catch(RuntimeException e) {
	    	  msg = "등록실패 : " + e.getMessage();
	    	  loc = "/board/boardInsert.do";
	      }
	      
//	      if(result > 0) {
//	    	  msg = "등록성공";
//	    	  loc = "/board/boardList.do";
//	      }else {
//	    	  msg = "등록실패";
//	    	  loc = "/board/boardInsert.do";
//	      }
	      
	      mv.addObject("loc","/board/boardList.do");
	      mv.setViewName("common/msg");
	      return mv;
	}
	
	@RequestMapping("/download.do")
	public void fileDownload(String oriName, String reName, HttpServletRequest req, HttpServletResponse res, @RequestHeader(value="User-agent") String header) {
		String path = req.getServletContext().getRealPath("/resources/upload/board/");
		File saveFile = new File(path + reName);
		BufferedInputStream bis = null;
		ServletOutputStream sos = null;
		try {
			bis = new BufferedInputStream(new FileInputStream(saveFile));
			sos = res.getOutputStream();
			boolean isMS = header.contains("Trident") || header.contains("MSIE");
			String encodeStr = "";
			if(isMS) {
				encodeStr = URLEncoder.encode(oriName, "UTF-8");
				encodeStr = encodeStr.replaceAll("\\+", "%20");
			}else {
				encodeStr = new String(oriName.getBytes("UTF-8"), "ISO-8859-1");
			}
			res.setContentType("application/octet-stream;charset=utf-8");
			res.setHeader("Content-Disposition", "attachment;filename=\"" + encodeStr + "\"");
			int read = -1;
			while((read = bis.read()) != -1) {
				sos.write(read);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				bis.close();
				sos.close();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}




















