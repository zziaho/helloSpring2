package com.bs.spring2.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring2.board.model.vo.Board;

public interface BoardDao {

	List<Board> selectBoardList(SqlSessionTemplate session);
	
	int countBoardList(SqlSessionTemplate session);
	
	Board selectBoard(SqlSessionTemplate session, int boardNo);
}
