package com.example.cashbook.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.cashbook.mapper.MemberMapper;
import com.example.cashbook.mapper.MemberidMapper;
import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;
import com.example.cashbook.vo.Memberid;

@Service
@Transactional
public class MemberService {
	@Autowired private MemberMapper memberMapper;
	@Autowired private MemberidMapper memberidMapper;
	@Autowired private JavaMailSender javaMailSender;
	
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
	//memberinfo
	public Member getMemberOne(LoginMember loginMember) {
		return memberMapper.selectMemberOne(loginMember);
	}
	//회원삭제
	public void deleteMember(LoginMember loginmember){
		Memberid memberid = new Memberid();
		memberid.setMemberId(loginmember.getMemberId());
		memberidMapper.insertMemberid(memberid);
		
		memberMapper.deleteMember(loginmember);
	}
	//id 찾기
	public String findMemberId(Member member) {
		return memberMapper.findMemberId(member);
	}
	//pw 찾기
	public int updateMemberPw(Member member) { //id email
		//pw 추가
		UUID uuid = UUID.randomUUID();
		String memberPw= uuid.toString().substring(0,8);
		member.setMemberPw(memberPw);
		int row = memberMapper.updateMemberPw(member);
		if(row ==1) { //메일로 업데이트 성공한 랜덤 pw를 전송 new javaMailSender();
			System.out.println(memberPw+"<---------update memberPW");
			SimpleMailMessage mm = new SimpleMailMessage();
			System.out.println(member.getMemberEmail()+"<---------------------------email");
			mm.setTo(member.getMemberEmail());
			mm.setFrom("jisung5449@gmail.com");
			mm.setSubject("cashbook 비밀번호 찾기메일");
			mm.setText("변경된 비밀번호"+ memberPw+"입니다");
			javaMailSender.send(mm);
			}
			return row;
	}
}
