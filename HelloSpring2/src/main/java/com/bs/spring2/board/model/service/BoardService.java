package com.bs.spring2.board.model.service;

import java.util.List;

import com.bs.spring2.board.model.vo.Board;

public interface BoardService {

	// 전체 리스트 불러오기
	List<Board> selectBoardList();
	
	// 전체 게시글 개수
	int countBoardList();
	
	// 게시글 번호에 맞는 게시글 가져오기
	Board selectBoard(int boardNo);
}
