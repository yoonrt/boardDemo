package com.example.demo.service;

import java.util.List;

import com.example.demo.domain.BoardDTO;

public interface BoardService {
	public boolean registerBoard(BoardDTO parmas);
	public BoardDTO getBoardDetail(Long idx);
	public List<BoardDTO> getBoardList();
	/*public boolean deleteBoard(Long idx);*/
	public void deleteBoard(Long Idx);
}