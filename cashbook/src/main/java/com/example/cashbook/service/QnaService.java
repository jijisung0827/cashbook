package com.example.cashbook.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cashbook.mapper.BoardMapper;
import com.example.cashbook.mapper.QnaMapper;
import com.example.cashbook.vo.Board;
import com.example.cashbook.vo.Qna;

@Service
@Transactional
public class QnaService {
	@Autowired private QnaMapper qnaMapper;
	@Autowired private BoardMapper boardMapper;
	
	//Qna List
	public List<Qna> selectQnaList(){
		return qnaMapper.selectQnaList();
	}
	
	//Qna One List
	public Qna selectQnaOne(int qnaNo) {
		return qnaMapper.selectQnaOne(qnaNo);
	}
	
	// Update Qna
	public void updateQna(Qna qna) {
		qnaMapper.updateQna(qna);
	}
	
	//Delete Qna
	public void deleteQna(int qnaNo) {
		qnaMapper.deleteQna(qnaNo);
	}
	
	//select board
	public List<Board> selectBoard(int qnaNo){
		return boardMapper.selectBoardList(qnaNo);
	}
	
	//insert board
	public void insertBoard(Board board) {
		boardMapper.insertBoard(board);
	}
	
	//select one board
	public Board selectBoardOne(int boardNo) {
		return boardMapper.selectBoardOne(boardNo);
	}
	
	//update board
	public void updateBoard(Board board) {
		boardMapper.updateBoard(board);
	}
	
	//delete board
	public void deleteBoard(int boardNo) {
		boardMapper.deleteBoard(boardNo);
	}
}
