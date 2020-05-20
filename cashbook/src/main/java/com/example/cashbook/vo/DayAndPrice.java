package com.example.cashbook.vo;

public class DayAndPrice {
	private int day;
	private int price;
	private String memberId;
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	@Override
	public String toString() {
		return "DayAndPrice [day=" + day + ", price=" + price + ", memberId=" + memberId + "]";
	}
}
