package com.bs.spring2.board.model.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.bs.spring2.board.model.vo.Attachment;
import com.bs.spring2.board.model.vo.Board;

public interface BoardDao {

	// 전체 게시글 불러오기
	List<Board> selectBoardList(SqlSessionTemplate session, int cPage, int numPerpage);
	
	int countBoardList(SqlSessionTemplate session);
	
	// 게시글 가져오기(상세보기)
	Board selectBoard(SqlSessionTemplate session, int boardNo);
	
	// 게시글 등록
	int insertBoard(SqlSessionTemplate session, Board b);
	
	int insertAttachment(SqlSessionTemplate session, Attachment file);
}
