package com.example.cashbook.service;

import java.io.File;
import java.util.UUID;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.example.cashbook.mapper.MemberMapper;
import com.example.cashbook.mapper.MemberidMapper;
import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;
import com.example.cashbook.vo.MemberForm;
import com.example.cashbook.vo.Memberid;

@Service
@Transactional
public class MemberService {
	@Autowired private MemberMapper memberMapper;
	@Autowired private MemberidMapper memberidMapper;
	@Autowired private JavaMailSender javaMailSender;
	
	//insert
	public int insertMember(MemberForm memberForm) {
		
		MultipartFile mf = memberForm.getMemberPic();
		String originName = mf.getOriginalFilename();
		System.out.println(originName + "<---------------- MemberSeriver.addmember.originName");
		int lastDot = originName.lastIndexOf(".");	//.을 찾는다
		String extension = originName.substring(lastDot); //확장자명 구하기
		
		//새로운 이름을 생성 : UUID
		String memberPic = memberForm.getMemberId()+extension;
		//1. db 저장
		Member member= new Member();
		member.setMemberId(memberForm.getMemberId());
		member.setMemberPw(memberForm.getMemberPw());
		member.setMemberAddr(memberForm.getMemberAddr());
		member.setMemberEmail(memberForm.getMemberEmail());
		member.setMemberName(memberForm.getMemberName());
		member.setMemberPhone(memberForm.getMemberPhone());
		member.setMemberPic(memberPic);
		System.out.println(member +"<---------------- MemberSeriver.addmember.member");
		//int row = memberMapper.insertMember(member);
		//2. 파일 저장
		String path = "D:\\sts-4.6.1.RELEASE\\maven.1590371256831\\cashbook\\src\\main\\resources\\static\\upload\\";
		
		//Exception
		//1. 예외처리를 해야만 문법적으로 이상없는 예외
		//2. 예외처리를 코드에서 구현하지 않아도 아무런문제없는 예외 RuntiomException();
		
		File file = new File(path+memberPic);
		try {
			mf.transferTo(file);	//예외처리가 꼭 필요한 코드
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(); 	//예외처리를 없앤다.
		} 
		return memberMapper.insertMember(member);
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
	public int deleteMember(LoginMember loginmember){

		// 1. 멤버 이미지 파일 삭제
		// 1_1 파일 이름 select member_pic from member
		String memberPic = memberMapper.selectMemberPic(loginmember.getMemberId());
		String path = "D:\\sts-4.6.1.RELEASE\\maven.1590371256831\\cashbook\\src\\main\\resources\\static\\upload\\";
		File file = new File(path+memberPic);
		// 1_2 파일 삭제
		
		//2.
		Memberid memberid = new Memberid();
		memberid.setMemberId(loginmember.getMemberId());
		//System.out.println(loginmember+"<-------------------------loginMember");
		int result = memberMapper.deleteMember(loginmember);
		int result1 = 0;
		if( result == 1) {
			result1 = memberidMapper.insertMemberid(memberid);
			if(file.exists()) {
				file.delete();
			}
		}
		//System.out.println(result1+"<------------------------------------insert true or false");
		return result1;
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
	//회원 수정
	public Member updateMemberInfo(LoginMember loginMember) {
		return memberMapper.selectMemberOne(loginMember);
	}
	//회원 수정 액션
	public void updateMember(MemberForm memberForm) {
		
		MultipartFile mf = memberForm.getMemberPic();
		String originName = mf.getOriginalFilename();
		//System.out.println(originName + "<---------------- MemberSeriver.addmember.originName");
		int lastDot = originName.lastIndexOf(".");	//.을 찾는다
		String extension = originName.substring(lastDot); //확장자명 구하기
		
		//새로운 이름을 생성 : UUID
		String memberPic = memberForm.getMemberId()+extension;
		String path = "D:\\sts-4.6.1.RELEASE\\maven.1590371256831\\cashbook\\src\\main\\resources\\static\\upload\\";
		
		Member member= new Member();
		
		member.setMemberId(memberForm.getMemberId());
		member.setMemberPw(memberForm.getMemberPw());
		member.setMemberAddr(memberForm.getMemberAddr());
		member.setMemberEmail(memberForm.getMemberEmail());
		member.setMemberName(memberForm.getMemberName());
		member.setMemberPhone(memberForm.getMemberPhone());
		member.setMemberPic(memberPic);
		File file = new File(path+memberPic);
		if(file.exists()) {
			file.delete();
			
		}
		file = new File(path+memberPic);
		
		memberMapper.updateMemberInfo(memberForm);
	}
}
