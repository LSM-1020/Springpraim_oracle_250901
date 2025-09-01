package com.LSM.oracle.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemberDto {

	private int memberum;
	private String memberid;
	private String memberpw;
	private String membername;
	private String memberdate; //가입일
	
	//private List<BoardDto> boardDtos;
}
