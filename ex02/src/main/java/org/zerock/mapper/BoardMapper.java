package org.zerock.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

public interface BoardMapper {
	
	//@Select("select * from tbl_board where bno > 0")
	public List<BoardVO> getList();
	public int insert(BoardVO vo);
	public int insertSelectKey(BoardVO vo);
	public BoardVO read(Long bno);
	public int delete(Long bno);
	public int update(BoardVO vo);
	
	
	public List<BoardVO> getListWithPaging(Criteria cri); //페이징처리
	public int getTotalCount(Criteria cri); //전체데이터의 개수 처리 
	
	
}
