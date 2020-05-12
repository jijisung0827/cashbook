package com.example.cashbook.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.cashbook.service.MemberService;
import com.example.cashbook.vo.Member;

@Controller
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("/addMember")
	public String addMember(){
		return "addMember";
	}
	
	@PostMapping("/addMember")
	public String addMember(Member member) { 
		memberService.insertMember(member);
		return "redirect:/index";
	}
}
	
