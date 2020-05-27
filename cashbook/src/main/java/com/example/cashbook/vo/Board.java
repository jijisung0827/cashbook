package com.example.cashbook.vo;

public class Board {
	private int boardNo;
	private String memberId;
	private String boardText;
	private int qnaNo;
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getBoardText() {
		return boardText;
	}
	public void setBoardText(String boardText) {
		this.boardText = boardText;
	}
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	@Override
	public String toString() {
		return "Board [boardNo=" + boardNo + ", memberId=" + memberId + ", boardText=" + boardText + ", qnaNo=" + qnaNo
				+ "]";
	}
}
