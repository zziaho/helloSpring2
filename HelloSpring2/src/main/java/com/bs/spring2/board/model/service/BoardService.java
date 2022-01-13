package com.bs.spring2.board.model.service;

import java.util.List;

import com.bs.spring2.board.model.vo.Board;

public interface BoardService {

	List<Board> selectBoardList();
	
}
