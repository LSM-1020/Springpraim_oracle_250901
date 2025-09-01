package com.LSM.oracle.dao;

import java.util.List;

import com.LSM.oracle.dto.BoardDto;

public interface BoardDao {

	public void boardWriteDao(String btitle, String bcontent,String bwriter);
	public List<BoardDto> boardListDao(); //게시판 글 목록보기
	public int AllBoardCountDao(); //게시판 모든글 갯수 가져오기
	public void boardDeleteDao(String bnum);

}
