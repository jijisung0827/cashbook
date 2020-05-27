package com.example.cashbook.controller;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.List;
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
import com.example.cashbook.vo.Category;
import com.example.cashbook.vo.DayAndPrice;
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
	//월별 리스트
	@GetMapping("/cashListMonth")
	public String selectCashList(HttpSession session,Model model, @RequestParam(value="day", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate day) {
		
		Calendar cDay = Calendar.getInstance(); //오늘
		
		if(day == null) {
			day = LocalDate.now();
		}else {
			cDay.set(day.getYear(), day.getMonthValue()-1, day.getDayOfMonth());
		}
		//System.out.println(cDay.get(Calendar.MONTH)+1+"<----------------------------------month");
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		//일별 수입 지출
		String memberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		int year = cDay.get(Calendar.YEAR);
		int month = cDay.get(Calendar.MONTH)+1;
		
		List<DayAndPrice> list = cashService.getCashAndPriceList(memberId, year, month);
		DayAndPrice priceSum = cashService.getCashApnPriceSum(memberId, month);
		//System.out.println(priceSum.getPrice()+"<--------------------------------------DayAndPrice");
		//for(DayAndPrice li : list) {
		//	System.out.println(li+"<--------------------------------------List");
		//}
		//System.out.println(memberId+"<------------------memberId");
		model.addAttribute("priceSum", priceSum);
		model.addAttribute("list", list);
		model.addAttribute("day", day);
		model.addAttribute("cDay", cDay.get(Calendar.MONTH)+1); //월
		model.addAttribute("lastDay", cDay.getActualMaximum(Calendar.DATE)); //마지막일
		
		Calendar firstDay = cDay;
		
		firstDay.set(Calendar.DATE, 1);
		model.addAttribute("firstDay", firstDay.get(Calendar.DAY_OF_WEEK));
		//firstDay.get(Calendar.DAY_OF_WEEK); //0 > 일요일.... 6 토요일
		
		return "cashListMonth";
	}
	
	//일별 수입 지출 수정 폼
	@GetMapping("/updateCash")
	public String updateListForm(HttpSession session,Model model, @RequestParam(value="day", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd")LocalDate day,
								@RequestParam("cashNo") int cashNo, @RequestParam("date") String date) {
		
		if(day == null) {
			day = LocalDate.now();
		}
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		String loginMemberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		
		List<Category> category = cashService.selectCategoryName();
		
		Cash cash = new Cash();
		cash.setCashNo(cashNo);
		cash.setMemberId(loginMemberId);
		cash.setCashDate(date);
		
		cash = cashService.selectCashListOne(cash);
		model.addAttribute("category", category);
		model.addAttribute("cash", cash);
		model.addAttribute("memberId", loginMemberId);
		model.addAttribute("day", date);
		return "updateCash";
	}
	
	//일별 수입 지줄 수정 액션
	@PostMapping("/updateCash")
	public String updateListAction(HttpSession session, Cash cash) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		System.out.println(cash+"<--------------------------UpdateCash");
		cashService.updateCash(cash);
		
		return "redirect:/cashList";
	}
	
	//일별 수입 지출 삽입 폼
	@GetMapping("/insertCashList")
	public String insertCashListForm(HttpSession session,Model model) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		List<Category> category = cashService.selectCategoryName();
		
		model.addAttribute("category", category);
		
		return "insertCashList";
	}
	
	//일별 수입 지출 삽입 액션
	@PostMapping("/insertCashList")
	public String insertCashListAction(HttpSession session, Cash cash) {
		
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		
		String memberId = ((LoginMember)session.getAttribute("loginMember")).getMemberId();
		cash.setMemberId(memberId);
		
		cashService.insertCashList(cash);
		
		return "redirect:/cashList";
	}
}
