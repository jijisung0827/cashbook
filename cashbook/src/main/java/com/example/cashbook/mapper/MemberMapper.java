package com.example.cashbook.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;

@Mapper
public interface MemberMapper {
	public Member selectMemberOne(LoginMember loginMember);
	public String seleceMemberId(String memberIdCheck);
	public void insertMember(Member member);
	public LoginMember selectLoginMember(LoginMember loginMember);
	public void deleteMember(LoginMember loginmember);
	public String findMemberId(Member member);
	public int updateMemberPw(Member member);
}
