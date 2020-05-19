package com.example.cashbook.controller;

import java.time.LocalDate;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.cashbook.service.CashService;
import com.example.cashbook.vo.Cash;
import com.example.cashbook.vo.LoginMember;

@Controller
public class CashController {
	@Autowired private CashService cashService;
	
	//select Cash
	@GetMapping("/cashList")
	public String selectCash(HttpSession session,Model model, @RequestParam(value="day", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate day) {
		
		if(day == null) {
			day = LocalDate.now();
		}
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		//아이디
		String loginMemberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		//오늘날짜구하기
		//Date date = new Date();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//String strToday = sdf.format(date);
		
		Cash cash = new Cash();
		cash.setMemberId(loginMemberId);
		cash.setCashDate(day.toString());
		
		Map<String, Object> map = cashService.selectCash(cash);
		model.addAttribute("cashList", map.get("cashList"));
		model.addAttribute("cashKindSum", map.get("cashKindSum"));
		model.addAttribute("day", day);
		
		return "cashList";
	}
	
	//delete Cash
	@GetMapping("/deleteCash")
	public String delectCash(HttpSession session, @RequestParam("cashNo") int cashNo) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		cashService.delectCash(cashNo);
		
		return "redirect:/cashList";
	}
}
