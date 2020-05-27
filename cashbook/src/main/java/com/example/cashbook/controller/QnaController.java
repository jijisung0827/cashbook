package com.example.cashbook.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cashbook.service.QnaService;
import com.example.cashbook.vo.Board;
import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Qna;

@Controller
public class QnaController {
	@Autowired private QnaService qnaService;
	
	//qna List
	@GetMapping("/qnaList")
	public String selectQnaList(HttpSession session,Model model) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		List<Qna> list = qnaService.selectQnaList();
		model.addAttribute("list", list);
		
		return "qnaList";
	}
	
	//qna one List
	@GetMapping("/qnaListOne")
	public String selectQnaOne(HttpSession session, Model model, @RequestParam("qnaNo") int qnaNo) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		String loginMemberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		
		Qna qna = qnaService.selectQnaOne(qnaNo);
		model.addAttribute("qna", qna);
		model.addAttribute("memberLogin", loginMemberId);
		
		List<Board> board = qnaService.selectBoard(qnaNo);
		model.addAttribute("board", board);
		
		return "qnaListOne";
	}
	
	//qna delect
	@GetMapping("/qnaDelete")
	public String deleteQna(HttpSession session, @RequestParam("qnaNo") int qnaNo) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		qnaService.deleteQna(qnaNo);
		
		return "qnaList";
	}
	
	//qna Update폼
	@GetMapping("/qnaUpdate")
	public String updateQna(HttpSession session, Model model, @RequestParam("qnaNo") int qnaNo) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		Qna qna = qnaService.selectQnaOne(qnaNo);
		model.addAttribute("qna", qna);
		
		return "qnaUpdate";
	}
	
	//qna Update 액션
	@PostMapping("/qnaUpdate")
	public String updateQnaAction(HttpSession session, Qna qna) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		qnaService.updateQna(qna);
		
		return "redirect:/qnaList";
	}
	
	//board insert
	@PostMapping("/insertBoard")
	public String insertBoard(HttpSession session, Board board, @RequestParam("qnaNo") int qnaNo ) {
		
		//System.out.println(qnaNo+"<----------------------------------------qnaNo");
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		String memberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		
		board.setQnaNo(qnaNo);
		board.setMemberId(memberId);
		
		qnaService.insertBoard(board);
		
		return "redirect:/qnaListOne?qnaNo="+qnaNo;
	}
	
	//board select one
	@GetMapping("/updateBoard")
	public String updateBoard(HttpSession session, Model model,@RequestParam("boardNo") int boardNo ) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		Board board = qnaService.selectBoardOne(boardNo);
		
		model.addAttribute("board", board);
		
		return "updateBoard";
	}
	
	//board update
	@PostMapping("/updateBoard")
	public String updateBoardAction(HttpSession session, Board board) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}

		qnaService.updateBoard(board);
		
		return "redirect:/qnaListOne?qnaNo="+board.getQnaNo();
	}
	
	//board delete
	@GetMapping("/deleteBoard")
	public String deleteBoard(HttpSession session, @RequestParam("boardNo") int boardNo, @RequestParam("qnaNo") int qnaNo) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		qnaService.deleteBoard(boardNo);
		
		return "redirect:/qnaListOne?qnaNo="+qnaNo;
	}
	
}
