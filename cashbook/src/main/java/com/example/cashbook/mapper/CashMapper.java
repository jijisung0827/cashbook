package com.example.cashbook.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.Cash;

@Mapper
public interface CashMapper {
   //로그인 사용자의 오늘날짜 Cash 목록      
   public List<Cash> selectCashListByDate(Cash cash);
   public void deleteCash(int cashNo);
   
}