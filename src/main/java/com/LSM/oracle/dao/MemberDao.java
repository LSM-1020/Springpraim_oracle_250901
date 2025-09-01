package com.LSM.oracle.dao;

public interface MemberDao {

	public int memberjoinDao(String memberid, String memberpw, String membername);
	
	public int memberidCheckDao(String memberid); //아이디 존재여부 확인 메소드
	
	public int memberloginDao(String memberid, String memberpw);
}
