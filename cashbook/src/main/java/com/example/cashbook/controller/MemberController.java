package com.example.cashbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cashbook.service.MemberService;
import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	//addMember form
	@GetMapping("/addMember")
	public String addMember(){
		return "addMember";
	}
	//addMember action
	@PostMapping("/addMember")
	public String addMember(Member member) { 
		memberService.insertMember(member);
		return "redirect:/index";
	}
	
	//login form
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	//login action
	@PostMapping("/login")
	public String LoginMember(LoginMember loginMember, HttpSession session) {
		LoginMember returnLoginMember = memberService.login(loginMember);
		if(returnLoginMember == null) { //로그인 실패시
			return "redirect:/login";
		}else { //로그인 성공시
			session.setAttribute("loginMember", returnLoginMember);
			return "redirect:/";
		}
		
	}
	
	//logout
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
}
	
