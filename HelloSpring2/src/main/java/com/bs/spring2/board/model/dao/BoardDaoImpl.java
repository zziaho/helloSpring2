package com.bs.spring2.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.bs.spring2.board.model.vo.Board;

@Repository
public class BoardDaoImpl implements BoardDao {

	@Override
	public List<Board> selectBoardList(SqlSessionTemplate session) {
		return session.selectList("board.selectBoardList");
	}
	
	@Override
	public int countBoardList(SqlSessionTemplate session) {
		return session.selectOne("board.countBoardList");
	}
	
	@Override
	public Board selectBoard(SqlSessionTemplate session, int boardNo) {
		return session.selectOne("board.selectBoard", boardNo);
	}
}
