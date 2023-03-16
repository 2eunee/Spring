package org.zerock.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.mapper.BoardMapper;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;


@Log4j
@Service
@AllArgsConstructor

public class BoardServicelmpl implements BoardService {
	
	
	private final BoardMapper boardMapper;
	
	
	public void register(BoardVO vo) {
		int result = boardMapper.insertSelectKey(vo);
		
	}
		
	public BoardVO get(Long bno) {
		return boardMapper.read(bno);
	}
	
	public boolean modify(BoardVO vo) {
		return boardMapper.update(vo) ==1 ? true: false;
	}
	
	public boolean remove(Long bno) {
		return boardMapper.delete(bno)==1 ? true: false;
	}
	public List<BoardVO> getList(){
		return boardMapper.getList();
		
	}

	@Override
	public List<BoardVO> getList(Criteria cri) {
		log.info("get List whth cirteria =======>" + cri);
		
		return  boardMapper.getListWithPaging(cri);
	}
	
	public int getTotal(Criteria cri) {
		
		log.info("get total count =====>");
		return boardMapper.getTotalCount(cri);
				
			
		}
	

}
