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
		 model.addAttribute("boardCount",boardDao.AllBoardCountDao());
		return "boardlist";
	}
//	@RequestMapping (value="/boarddelete")
//	public String boarddelete(HttpServletRequest request, Model model) {
//		String bnum = request.getParameter("bnum");
//		
//	BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
//	
//		 boardDao.boardDeleteDao(bnum);
//		
//	            return "redirect:/blist";
//	     
//	}
	@RequestMapping (value="/boarddelete")
	public String boarddelete(HttpServletRequest request, Model model) {
		String bnum = request.getParameter("bnum");
		
	BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
	
		int result = boardDao.boardDeleteDao(bnum);
		if(result == 0) { //삭제 실패
			model.addAttribute("msg","글 삭제가 실패하였습니다. 다시확인해 주세요");
			model.addAttribute("url","blist"); //blist로 보냄
			
		} else {
			model.addAttribute("msg","글이 삭제되었습니다.");
			model.addAttribute("url","blist"); //blist로 보냄
		}
		return "alert/alert";	
	     
	}
	@RequestMapping (value="/contentview")
	public String contentview(HttpServletRequest request, Model model) {
		String bnum = request.getParameter("bnum");
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		BoardDto boardDto = boardDao.contentviewDao(bnum);
		boardDao.updateHitDao(bnum);
		model.addAttribute("boardDto",boardDto);
	
		return "boardContent";
	}
	@RequestMapping(value = "/boardmodify")
	public String boardmodify(HttpServletRequest request, Model model) {
		
		String bnum = request.getParameter("bnum");
		String btitle = request.getParameter("btitle");
		String bcontent = request.getParameter("bcontent");
		
		BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		int result = boardDao.boardModifyDao(bnum, btitle, bcontent);
		
		if(result == 1) { //수정 성공->1, 실패->0
			model.addAttribute("msg", "글 수정이 성공 하였습니다.");
			model.addAttribute("url", "blist");			
		} else {
			model.addAttribute("msg", "글 수정이 실패 하였습니다.");
			model.addAttribute("url", "blist");			
		}
		return "alert/alert";
	}
	@RequestMapping (value="/pagelist")
	public String pagelist(HttpServletRequest request, Model model) {
		
		 int pageSize = 10; //게시판 목록 한 페이지당 출력될 글 수
		 int pageNum = 1; //유저가 클릭한 페이지의 번호 ->처음에 게시판 리스트 링크로 들어오면 무조건 1페이지 출력
		 int blockSize = 5; //게시글 하단 페이지 블럭 페이지수
		 
		 if(request.getParameter("pageNum")!=null) {
			 pageNum = Integer.parseInt( request.getParameter("pageNum"));
		 }
		 int startRow = (pageNum * pageSize)-9 ;//페이징 되었을때 시작 행의 번호 (1->1, 2->11, 3->21)
		 //(pageNum -1) * pageSize) + 1
		 int endRow =  pageNum * pageSize;//1->10, 2->20,3->30		
		 
		 BoardDao boardDao = sqlSession.getMapper(BoardDao.class);
		 List<BoardDto> boardDtos = boardDao.pageBoardListDao(startRow, endRow);
		 int totalCount = boardDao.AllBoardCountDao();
		 
		 int startPage = (((pageNum-1)/blockSize)*blockSize)+1;
		 int endPage = startPage + blockSize -1;
		 int totalPage = (int) Math.ceil((double) totalCount / pageSize); //전체 글수로 만든 총 페이지 (글153->16, 178->18) ,mate로 올림처리
		 if(endPage > totalPage) {
			 endPage = totalPage;
		 } //실제 모든 글 갯수 totalpage로 만든 총 페이지 수, 없는 페이지는 출력되지 않게하기 위해서
		 
		
		 model.addAttribute("boardList",boardDtos);	 
		 model.addAttribute("pageNum",pageNum);//유저가 클릭한 현재 페이지
		 model.addAttribute("startPage",startPage);
		 model.addAttribute("endPage",endPage);
		 model.addAttribute("totalPage",totalPage);
		 model.addAttribute("boardCount",totalCount);
		return "pagelist";
		}
		@RequestMapping (value="/test")
		public String test() {
			return "test";
		}
		@RequestMapping (value="/maptest")
		public String maptest() {
			return "maptest";
		}
		@RequestMapping (value="/kakaomaptest")
		public String kakaomaptest() {
			return "kakaomaptest";
		}
	
	
}
