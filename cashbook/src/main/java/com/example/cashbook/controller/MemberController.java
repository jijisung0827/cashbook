
package com.example.cashbook.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.cashbook.service.MemberService;
import com.example.cashbook.vo.LoginMember;
import com.example.cashbook.vo.Member;
import com.example.cashbook.vo.MemberForm;

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
	public String addMember(MemberForm memberForm, HttpSession session) { 
		//로그인상태
		if(session.getAttribute("loginMember") != null){	
			return "redirect:/";
		}
		// 파일 .jpg .png .gif 만 업로드 가능
		if(memberForm.getMemberPic() != null) {
			if(!memberForm.getMemberPic().getContentType().equals("image/jpg") && !memberForm.getMemberPic().getContentType().equals("image/png") && !memberForm.getMemberPic().getContentType().equals("image/gif") && !memberForm.getMemberPic().getContentType().equals("image/jpeg")) {
				return "redirect:/addMember";
			}
		}
		memberService.insertMember(memberForm);
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
	public String LoginMember(Model model,LoginMember loginMember, HttpSession session, @RequestParam("memberId") String memberId) {
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
		//System.out.println(member);
		model.addAttribute("member", member);
		return "memberInfo";
	}
	
	//member 삭제 form
	@GetMapping("/removeMember")
	public String deleteMember(HttpSession session) {
		//비로그인 상태
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		return "removeMember";
	}
	
	//member 삭제 action
	@PostMapping("/removeMember")
	public String deleteMember(HttpSession session, @RequestParam("memberPw") String memberPw, Model model) {
		//비로그인 상태
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		
		LoginMember loginMember = (LoginMember)(session.getAttribute("loginMember"));
		loginMember.setMemberPw(memberPw);
		int result = memberService.deleteMember(loginMember);
	
		if(result == 1) {
			session.invalidate();
			return "redirect:/";
		}
		model.addAttribute("msg", "비밀번호가 틀립니다");
		
		return "removeMember";
		
	}
	//아이디 찾기
	@GetMapping("/findMemberId")
	public String findMemberId(HttpSession session) {
		//로그인상태
		if(session.getAttribute("loginMember") != null){	
			return "redirect:/";
		}
		return "findMemberId";
	}
	
	@PostMapping("/findMemberId")
	public String findMemberId(HttpSession session, Model model, Member member) {
		//로그인상태
		if(session.getAttribute("loginMember") != null){	
			return "redirect:/";
		}
		String findMemberId = memberService.findMemberId(member);
		//System.out.println(findMemberId+"<----------findMemberId");
		model.addAttribute("findMemberId",findMemberId);
		return "memberIdView";
	}
	
	//비밀번호 찾기
	@GetMapping("/findMemberPw")
	public String findMemberPw(HttpSession session) {
		//로그인상태
		if(session.getAttribute("loginMember") != null){	
			return "redirect:/";
		}
		return "findMemberPw";
	}
	@PostMapping("/findMemberPw")
	public String findMemberPw(HttpSession session, Model model, Member member) {
		int row = memberService.updateMemberPw(member);
		String msg = "아이디와 메일을 확인하세요";
		if(row ==1) {
			msg = "비밀번호를 입력한 메일로 전송하였습니다";
		} 
		model.addAttribute("msg", msg);
		return "memberPwView";
	}
	// modifyMember 회원 수정 form
	@GetMapping("/modifyMember")
	public String updateMemberInfo(HttpSession session, Model model) {
		if(session.getAttribute("loginMember") == null) {
			return "redirect:/";
		}
		Member member = memberService.getMemberOne((LoginMember)(session.getAttribute("loginMember")));
		//System.out.println(member);
		model.addAttribute("member", member);
		return "modifyMember";
	}
	// modifyMember 회원 수정 action
	@PostMapping("/modifyMember")
	public String updateMemberInfoAction(HttpSession session, MemberForm memberForm) {
		if(session.getAttribute("loginMember") == null){	
			return "redirect:/";
		}
		// 파일 .jpg .png .gif 만 업로드 가능
		System.out.println(memberForm.getMemberPic()+"<---------------------------memberController");
		if(memberForm.getMemberPic() != null) {
			if(!memberForm.getMemberPic().getContentType().equals("image/jpg") && !memberForm.getMemberPic().getContentType().equals("image/png") && !memberForm.getMemberPic().getContentType().equals("image/gif") && !memberForm.getMemberPic().getContentType().equals("image/jpeg")) {
				return "redirect:/modifyMember";
			}
		}
		System.out.println(memberForm+"<--------------------------- modifyMemberInfo Action");
		memberService.updateMember(memberForm);
		System.out.println(memberForm+"<--------------------------- modifyMemberInfo Action");
		return "redirect:/memberInfo";
	}
	
	//관리자 계정
	@GetMapping("/adminHome")
	public String selectAdminHome() {
		
		return "adminHome";
	}
}

