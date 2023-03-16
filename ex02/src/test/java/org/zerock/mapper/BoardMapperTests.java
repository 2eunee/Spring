package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {

	@Autowired
	private BoardMapper mapper;
	
	@Test
	public void testGetList() {
//		List<BoardVO> list = mapper.getList();
//		for (BoardVO list : lists) {
//			log.info(list);
//		}
		mapper.getList().forEach(board-> log.info(board)); 
	}

	@Test
	public  void testInsert() {
		BoardVO vo = new BoardVO();
		vo.setTitle("새로 작성함");
		vo.setContent("새로 작성하는 내용");
		vo.setWriter("newbie");
		
		int result = mapper.insert(vo);
		
		log.info("reselt :" +result);
				
	}

	@Test
	public  void testinsertSelectKey() {
		BoardVO vo = new BoardVO();
		vo.setTitle("새로 작성함2");
		vo.setContent("새로 작성하는 내용2");
		vo.setWriter("newbie2");
		
		int result = mapper.insertSelectKey(vo);
		
		log.info("reselt :" +result);
				
	}
	
	@Test
	public void testRead() {
		BoardVO vo = mapper.read(4L);
		log.info(vo);
	}
	
	
	@Test
	public void testDelete() {
		int result = mapper.delete(4L);
		
		log.info("result : " + result);
	}
	
	
	@Test
	public void testupdate() {
		BoardVO vo = new BoardVO();
		vo.setContent("수정한 글 내용");
		vo.setTitle("수정한 글 제목");
		vo.setBno(1L);
		
		int result = mapper.update(vo);
		log.info("update result : " + result);
		
	}
	
	@Test
	public void testPasing() {
		
		Criteria cri = new Criteria();
		
		//게시글 10개씩 3페이지로 보임
		cri.setPageNum(3);
		cri.setAmount(10);
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board->log.info(board.getBno()));
	
	}

	@Test
	public void testSearch( ) {
		Criteria cri = new  Criteria();
		cri.setKeyword("윤아");
		cri.setType("CT");
		
		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board-> log.info(board));
	}
	
}


