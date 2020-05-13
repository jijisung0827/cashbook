package com.example.cashbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cashbook.mapper.MemberMapper;
import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;

@Service
@Transactional
public class MemberService {
	@Autowired
	private MemberMapper memberMapper;
	//insert
	public void insertMember(Member member) {
		memberMapper.insertMember(member);
	}
	//login
	public LoginMember login(LoginMember loginMember) {
		return memberMapper.selectLoginMember(loginMember);
	}
	//id중복체크
	public String memberIdCheck(String memberIdCheck) {
		return memberMapper.seleceMemberId(memberIdCheck); //null 또는 member_id가 리턴됨
	}
}
