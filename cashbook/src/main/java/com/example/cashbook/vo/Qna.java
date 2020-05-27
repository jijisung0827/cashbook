package com.example.cashbook.vo;

public class Qna {
	private int qnaNo;
	private String qnaTitle;
	private String qnaText;
	private String memberId;
	private String qnaDate;
	public int getQnaNo() {
		return qnaNo;
	}
	public void setQnaNo(int qnaNo) {
		this.qnaNo = qnaNo;
	}
	public String getQnaTitle() {
		return qnaTitle;
	}
	public void setQnaTitle(String qnaTitle) {
		this.qnaTitle = qnaTitle;
	}
	public String getQnaText() {
		return qnaText;
	}
	public void setQnaText(String qnaText) {
		this.qnaText = qnaText;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getQnaDate() {
		return qnaDate;
	}
	public void setQnaDate(String qnaDate) {
		this.qnaDate = qnaDate;
	}
	@Override
	public String toString() {
		return "Qna [qnaNo=" + qnaNo + ", qnaTitle=" + qnaTitle + ", qnaText=" + qnaText + ", memberId=" + memberId
				+ ", qnaDate=" + qnaDate + "]";
	}
}
