package com.example.demo.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.domain.BoardDTO;
import com.example.demo.service.BoardService;
import com.example.demo.service.BoardServiceImpl;

@Controller
public class BoardController {
	@Autowired
	private BoardService boardService;
	@GetMapping(value = "/board/write.do")
	public String openBoardWrite(@RequestParam(value="Idx", required = false)Long Idx, Model model) {
		if(Idx == null) {
			model.addAttribute("board", new BoardDTO());
		}else {
			BoardDTO board = boardService.getBoardDetail(Idx);
			if(board == null) {
				return "redirect:/board/list.do";
			}
			model.addAttribute("board", board);
		}
		return "board/write";
	}
	@PostMapping(value = "/board/register.do")
	public String registerBoard(final BoardDTO params) {
		try {
			boolean isRegistered = boardService.registerBoard(params);
			if (isRegistered == false) {
			}
		} catch (DataAccessException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "redirect:/board/list.do";
	}
	@GetMapping(value = "/board/list.do")
	public String openBoardList(Model model) {
		List<BoardDTO> boardList = boardService.getBoardList();
		model.addAttribute("boardList", boardList);
		
		return "board/list";
	}
	@GetMapping(value = "/board/view.do")
	public String openBoardDetail(@RequestParam(value = "Idx", required = false) Long Idx, Model model) {
		if(Idx == null) {
			return "redirect:/board/list.do";
		}
		BoardDTO board = boardService.getBoardDetail(Idx);
		if(board == null || "Y".equals(board.getDeleteYN())) {
			return "redirect:/board/list.do";
		}
		model.addAttribute("board", board);
		return "board/view";
	}
	/*@GetMapping(value = "/board/delete.do")
	public String deleteBoard(@RequestParam(value = "Idx", required = false) Long Idx, Model model) {
		if(Idx == null) {
			return "redirect:/board/list.do";
		} try {
			boolean isDeleted = boardService.deleteBoard(Idx);
			if(isDeleted == false) {
				System.out.println("삭제실패");
				}
			} catch (DataAccessException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return "redirect:/board/list.do";
		}*/
	@GetMapping(value="/board/delete.do")
	public String deleteBoard(@RequestParam("Idx")Long Idx) {
		boardService.deleteBoard(Idx);
		return "redirect:/board/list.do";
		}
	}