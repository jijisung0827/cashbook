package com.example.cashbook.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.Memberid;

@Mapper
public interface MemberidMapper {
	int insertMemberid(Memberid memberid);
}
