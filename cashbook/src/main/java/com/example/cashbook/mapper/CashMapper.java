package com.example.cashbook.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.Cash;
import com.example.cashbook.vo.DayAndPrice;

@Mapper
public interface CashMapper {
   //로그인 사용자의 오늘날짜 Cash 목록      
   public List<Cash> selectCashListByDate(Cash cash);
   public void deleteCash(int cashNo);
   public int selectCashKindSum(Cash cash);
   public List<DayAndPrice> selectDayAndPrice(Map<String, Object> map);
}