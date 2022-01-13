package com.bs.spring2.board.model.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bs.spring2.board.model.dao.BoardDao;
import com.bs.spring2.board.model.vo.Board;

@Service
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
		return dao.insertBoard(session, b);
	}
	
}
