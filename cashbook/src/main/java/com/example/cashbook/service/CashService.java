package com.example.cashbook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cashbook.mapper.CashMapper;
import com.example.cashbook.vo.Cash;
import com.example.cashbook.vo.Category;
import com.example.cashbook.vo.DayAndPrice;

@Service
@Transactional
public class CashService {
	@Autowired private CashMapper cashMapper;
	
	//select Cash
	public Map<String, Object> selectCash(Cash cash){
		List<Cash> cashList = cashMapper.selectCashListByDate(cash);
		int cashKindSum = cashMapper.selectCashKindSum(cash);
		Map<String, Object> map = new HashMap<>();
		map.put("cashList", cashList);
		map.put("cashKindSum", cashKindSum);
		return map;
	}
	
	//delete Cash
	public void delectCash(int cashNo) {
		cashMapper.deleteCash(cashNo);
	}
	
	//month sum
	public List<DayAndPrice> getCashAndPriceList(String memberId, int year, int month){
		
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("year", year);
		map.put("month", month);
		
		
		return cashMapper.selectDayAndPrice(map);
	}
	
	//month price sum
	public DayAndPrice getCashApnPriceSum(String memberId, int month) {
		Map<String, Object> map = new HashMap<>();
		map.put("memberId", memberId);
		map.put("month", month);
		
		return cashMapper.selectDayAndPriceSum(map);
	}
	
	//day List Update
	
	public Cash selectCashListOne(Cash cash) {
		return cashMapper.selectCashListOne(cash);
	}
	
	//update cash
	public void updateCash(Cash cash){
		cashMapper.updateCash(cash);
	}
	//select category
	
	public List<Category> selectCategoryName() {
		return cashMapper.selectCategoryName();
	}
	
	//insert cash
	public void insertCashList(Cash cash) {
		cashMapper.insertCashList(cash);
	}
}
