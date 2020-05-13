package com.example.cashbook.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;

@Mapper
public interface MemberMapper {
	public String seleceMemberId(String memberIdCheck);
	public void insertMember(Member member);
	public LoginMember selectLoginMember(LoginMember loginMember);
}
