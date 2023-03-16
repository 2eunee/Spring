package com.ezen.ex01;

public interface BlogMapper {
	
	public BoardVO selectOne(int id);
	public int insert(BoardVO vo); //오라클에 값 넣어주기
	
	

}
