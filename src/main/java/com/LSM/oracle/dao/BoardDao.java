package com.LSM.oracle.dao;

import java.util.List;

import com.LSM.oracle.dto.BoardDto;

public interface BoardDao {

	public void boardWriteDao(String btitle, String bcontent,String bwriter);
	public List<BoardDto> boardListDao(); //게시판 글 목록보기-> 페이징 처리 안됨
	public int AllBoardCountDao(); //게시판 모든글 갯수 가져오기
	//public void boardDeleteDao(String bnum);
	public int boardDeleteDao(String bnum);
	public BoardDto contentviewDao(String bnum);//글 번호로 해당글 레코드 가져오기
	public int boardModifyDao(String bnum, String btitle,String bcontent );
	public void updateHitDao(String bnum); //해당 글의 조회수 1증가
	public List<BoardDto> pageBoardListDao(int startRow, int endRow); //페이징된 게시판 글목록보기
}
