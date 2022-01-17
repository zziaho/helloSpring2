package com.bs.spring2.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring2.board.model.dao.BoardDao;
import com.bs.spring2.board.model.vo.Board;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements BoardService {

	@Autowired
	private BoardDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Board> selectBoardList(int cPage, int numPerpage) {
		return dao.selectBoardList(session, cPage, numPerpage);
	}
	
	@Override
	public int countBoardList() {
		return dao.countBoardList(session);
	}
	
	@Override
	public Board selectBoard(int boardNo) {
		return dao.selectBoard(session, boardNo);
	}
	
	// 게시글 등록
	@Override
	public int insertBoard(Board b) {
		log.debug("전 boardNo : {}", b.getBoardNo());
		int result = dao.insertBoard(session, b);
		log.debug("후 boardNo : {}", b.getBoardNo());
		if(result > 0) {
			b.getFiles().get(0).setBoardNo(b.getBoardNo());
			result = dao.insertAttachment(session, b.getFiles().get(0));
//			if -> spring 트렌젝션 매니저가 담당한다.
		}
		
		return result;
	}
	
}












