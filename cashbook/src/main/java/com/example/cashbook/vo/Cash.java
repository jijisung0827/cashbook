package com.example.cashbook.vo;

import java.time.LocalDate;

public class Cash {
	private int cashNo;
	private String memberId;
	private String cashKind;
	private String categoryName;
	private int cashPrice;
	private String cashPlace;
	private String cashMemo;
	private LocalDate cashDate;
	public int getCashNo() {
		return cashNo;
	}
	public void setCashNo(int cashNo) {
		this.cashNo = cashNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getCashKind() {
		return cashKind;
	}
	public void setCashKind(String cashKind) {
		this.cashKind = cashKind;
	}
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public int getCashPrice() {
		return cashPrice;
	}
	public void setCashPrice(int cashPrice) {
		this.cashPrice = cashPrice;
	}
	public String getCashPlace() {
		return cashPlace;
	}
	public void setCashPlace(String cashPlace) {
		this.cashPlace = cashPlace;
	}
	public String getCashMemo() {
		return cashMemo;
	}
	public void setCashMemo(String cashMemo) {
		this.cashMemo = cashMemo;
	}
	public LocalDate getCashDate() {
		return cashDate;
	}
	public void setCashDate(LocalDate cashDate) {
		this.cashDate = cashDate;
	}
	@Override
	public String toString() {
		return "Cash [cashNo=" + cashNo + ", memberId=" + memberId + ", cashKind=" + cashKind + ", categoryName="
				+ categoryName + ", cashPrice=" + cashPrice + ", cashPlace=" + cashPlace + ", cashMemo=" + cashMemo
				+ ", cashDate=" + cashDate + "]";
	}
}
