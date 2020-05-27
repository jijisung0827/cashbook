package com.example.cashbook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.Qna;

@Mapper
public interface QnaMapper {
	public List<Qna> selectQnaList();
	public int selectCount();
	public Qna selectQnaOne(int qnaNo);
	public void updateQna(Qna qna);
	public void deleteQna(int qnaNO);
}
