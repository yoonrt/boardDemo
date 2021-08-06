package com.example.demo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.CollectionUtils;

import com.example.demo.domain.BoardDTO;
import com.example.demo.mapper.BoardMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
public class MapperTest {
	@Autowired
	private BoardMapper boardMapper;
	
	@Test
	public void testOfInsert() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 제목");
		params.setContent("1번 내용");
		
		int result = boardMapper.insertBoard(params);
		System.out.println("결과: "+result);
	}
	
	@Test
	public void testOfUpdate() {
		BoardDTO params = new BoardDTO();
		params.setTitle("1번 제목 수정");
		params.setContent("1번 내용 수정");
		params.setIdx((long)2);
		
		int result = boardMapper.updateBoard(params);
		if(result == 2) {
			BoardDTO board = boardMapper.selectBoardDetail((long)2);
			try {
				String boradJson = new ObjectMapper().writeValueAsString(board);
				System.out.println(boradJson);
			}catch(JsonProcessingException e){
				e.printStackTrace();
			}
		}
	}
	@Test
	public void testOfselectDetail() {
		BoardDTO board = boardMapper.selectBoardDetail((long)2);
		try {
			String boardJson = new ObjectMapper().writeValueAsString(board);
			System.out.println(boardJson);
		}catch(JsonProcessingException e){
			e.printStackTrace();
		}
	}
	
	@Test
	public void testOfdelete() {
		int result = boardMapper.deleteBoard((long)2);
		if(result == 2) {
			BoardDTO board = boardMapper.selectBoardDetail((long)2);
			try {
				String boardJson = new ObjectMapper().writeValueAsString(board);
				System.out.println("=========");
				System.out.println(boardJson);
				System.out.println("=========");
			}catch(JsonProcessingException e) {
				e.printStackTrace();
			}
		}
	}
	
	@Test
	public void testOfMultipleInsert() {
		for(int i = 1 ; i < 50 ; i++) {
			BoardDTO params = new BoardDTO();
			params.setTitle("번 제목");
			params.setContent("번 내용");
			boardMapper.insertBoard(params);
		}
	}
	
	@Test
	public void testOfSelectList() {
		int boardTotalCount = boardMapper.selectBoardTotalCount();
		if(boardTotalCount > 0) {
			List<BoardDTO> boardList = boardMapper.selectBoardList();
			if(CollectionUtils.isEmpty(boardList) == false) {
				for(BoardDTO board : boardList) {
					System.out.println("==========");
					System.out.println(board.getIdx());
					System.out.println(board.getTitle());
					System.out.println(board.getContent());
					System.out.println("==========");
				}
			}
		}
	}
}