package com.example.cashbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cashbook.service.MemberService;
import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	//addMember form
	@GetMapping("/addMember")
	public String addMember(HttpSession session){
		//로그인상태
		if(session.getAttribute("loginMember") != null){	
			return "redirect:/";
		}
		return "addMember";
	}
	//addMember action
	@PostMapping("/addMember")
	public String addMember(Member member, HttpSession session) { 
		//로그인상태
		if(session.getAttribute("loginMember") != null){	
			return "redirect:/";
		}
		memberService.insertMember(member);
		return "redirect:/index";
	}
	
	//login form
	@GetMapping("/login")
	public String login(HttpSession session) {
	//로그인상태
	if(session.getAttribute("loginMember") != null){	
		return "redirect:/";
	}
		return "login";
	}
	//login action
	@PostMapping("/login")
	public String LoginMember(Model model,LoginMember loginMember, HttpSession session) {
		LoginMember returnLoginMember = memberService.login(loginMember);
		if(returnLoginMember == null) { //로그인 실패시
			model.addAttribute("msg", "아이디와 비밀번호를 확인하세요");
			return "login";
		}else { //로그인 성공시
			session.setAttribute("loginMember", returnLoginMember);
			return "redirect:/home";
		}
		
	}
	
	//logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		//비로그인상태
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		session.invalidate();
		return "redirect:/";
	}
	
	//id 중복확인
	@PostMapping("/checkMemberId")
	public String ckeckMemberId(@RequestParam("memberIdCheck") String memberIdCheck, Model model, HttpSession session) {
		//로그인상태
		if(session.getAttribute("loginMember") != null){	
			return "redirect:/";
		}
		String memberId = memberService.memberIdCheck(memberIdCheck);
		if(memberId != null) {
			//아이디 사용불가
			model.addAttribute("msg", "사용중인 아이디입니다");
		}else {
			//아이디 사용가능
			model.addAttribute("memberIdCheck",memberIdCheck);
		}
		return "addMember";
	}
	//member 정보
	@GetMapping("/memberInfo")
	public String memberInfo(HttpSession session, Model model) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		Member member = memberService.getMemberOne((LoginMember)(session.getAttribute("loginMember")));
		System.out.println(member);
		model.addAttribute("member", member);
		return "memberInfo";
	}

}
	
