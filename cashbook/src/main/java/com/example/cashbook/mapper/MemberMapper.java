package com.example.cashbook.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;
import com.example.cashbook.vo.MemberForm;

@Mapper
public interface MemberMapper {
	public Member selectMemberOne(LoginMember loginMember);
	public String seleceMemberId(String memberIdCheck);
	public int insertMember(Member member);
	public LoginMember selectLoginMember(LoginMember loginMember);
	public int deleteMember(LoginMember loginmember);
	public String findMemberId(Member member);
	public int updateMemberPw(Member member);
	public String selectMemberPic(String memberId);
	public void updateMemberInfo(Member member);
	//admin
	public String selectAdminId(String adminId);
}
