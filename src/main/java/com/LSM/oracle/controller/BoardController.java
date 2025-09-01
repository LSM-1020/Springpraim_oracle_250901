package com.LSM.oracle.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.LSM.oracle.dao.BoardDao;
import com.LSM.oracle.dao.MemberDao;
import com.LSM.oracle.dto.BoardDto;


@Controller
public class BoardController {
@Autowired
private SqlSession sqlSession;

	@RequestMapping(value="/bwrite") 
	public String bwrite(HttpSession session, Model model) {
		String sid = (String)session.getAttribute("sessionId");
		if(sid==null) {
			model.addAttribute("msg","로그인한 회원만 글쓰기 가능합니다");
			model.addAttribute("url","login");
			return "alert/alert";
		}
		
		return "write_form";
	}
	@RequestMapping(value="/bwriteOk") 
	public String bwriteOk(HttpServletRequest request, Model model) {
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		String bwriter = request.getParameter("bwriter");
		
		BoardDao boarddao = sqlSession.getMapper(BoardDao.class);
		boarddao.boardWriteDao(btitle, bcontent, bwriter);
		
		
		return "redirect:blist";
	}
	@RequestMapping (value="/blist")
	public String blist(Model model) {
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		 List<BoardDto> boardDtos = boardDao.boardListDao();
		 model.addAttribute("boardList",boardDtos);
		return "boardlist";
	}
	
}
