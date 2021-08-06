package com.example.demo.service;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.BoardDTO;
import com.example.demo.mapper.BoardMapper;
@Service
public class BoardServiceImpl implements BoardService {
	@Autowired
	private BoardMapper boardMapper;
	
	@Override
	public boolean registerBoard(BoardDTO params) {
		int queryResult = 0;
		if (params.getIdx() == null) {
			queryResult = boardMapper.insertBoard(params);
		}else {
			queryResult = boardMapper.updateBoard(params);
		}
		return (queryResult == 1) ? true : false;
	}
	@Override
	public BoardDTO getBoardDetail(Long Idx) {
		return boardMapper.selectBoardDetail(Idx);
	}

	/*@Override
	public boolean deleteBoard(Long Idx) {
		int queryResult = 0;
		
		BoardDTO board = boardMapper.selectBoardDetail(Idx);
		
		if(board != null && "N".equals(board.getDeleteYN())) {
			queryResult = boardMapper.deleteBoard(Idx);
		}
		return (queryResult == 1) ? true : false;
	}*/
	@Override
	public List<BoardDTO> getBoardList(){
		List<BoardDTO> boardList = Collections.emptyList();
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		
		if(boardTotalCount > 0) {
			boardList = boardMapper.selectBoardList();
		}
		return boardList;
	}
	@Override
	public void deleteBoard(Long Idx) {
		boardMapper.deleteBoard(Idx);
	}
}