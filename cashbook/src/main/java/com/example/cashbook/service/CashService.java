package com.example.cashbook.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cashbook.mapper.CashMapper;
import com.example.cashbook.vo.Cash;

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
}
