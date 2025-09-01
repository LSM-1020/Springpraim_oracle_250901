package com.LSM.oracle.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LSM.oracle.dao.MemberDao;

@Controller
public class MemberController {

	@Autowired
	private SqlSession sqlSession;
	
	@RequestMapping(value = "/join")
	public String join() {		
		
		return "memberjoin";
	}
	@RequestMapping(value="/joinOk")
	public String joinOk(HttpServletRequest request, Model model) {
		
		String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		String mname = request.getParameter("membername");
		
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		int checkFlag = dao.memberidCheckDao(mid); //아이디가 이미 DB에 존재하는지 확인->존재하면1, 아니면 0
		
		if(checkFlag ==1) {
			model.addAttribute("msg","이미 가입된 아이디입니다.");
			model.addAttribute("url","join");
			return "alert/alert";
		} else {
			int result = dao.memberjoinDao(mid, mpw, mname);
			model.addAttribute("mid",mid);
		}

		return"memberjoinOk";
	}
	
	@RequestMapping(value = "/login")
	public String login(HttpServletRequest request, Model model) {		
		
		String error = request.getParameter("memberid");
		
		if(error!=null) {
			model.addAttribute("error",error);
		}
		return "login";
	}
	
	@RequestMapping(value = "/loginOk")
	public String loginOk(HttpServletRequest request, Model model,HttpSession session) {		
		
		String mid = request.getParameter("memberid");
		String mpw = request.getParameter("memberpw");
		
		MemberDao dao = sqlSession.getMapper(MemberDao.class);
		int checkFlag = dao.memberloginDao(mid,mpw);
		if(checkFlag ==1) {
			session.setAttribute("sessionId",mid);
			model.addAttribute("msg","로그인 성공");
			model.addAttribute("url","loginsucess");
			return "alert/alert";
		}  else { //로그인 실패		
			model.addAttribute("msg", "로그인 실패. 아이디 또는 비밀번호가 잘못 되었습니다.");
			model.addAttribute("url", "login");
			return "alert/alert";
		}
		
		
	}
	@RequestMapping(value = "/loginsucess")
	public String loginSuccess() {
		return "loginsucess";
	}
}
