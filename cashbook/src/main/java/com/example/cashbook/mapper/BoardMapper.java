package com.example.cashbook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.Board;

@Mapper
public interface BoardMapper {
	public List<Board> selectBoardList(int qnaNo);
	public void insertBoard(Board board);
	public Board selectBoardOne(int boardNo);
	public void updateBoard(Board board);
	public void deleteBoard(int boardNo);
}
