package org.zerock.service;

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
public class BoardserviceTests {
	
	// board<apper.xml에서 필요한 값을 확인함
	// BoardServicelmpl에서 반환타입을 확인함
	
	
	@Autowired
	private BoardService boardService;
	
	@Test
	public void testRegister() {
		BoardVO vo = new BoardVO();
		//vo.setBno
		vo.setTitle("서비스 제목1");
		vo.setContent("서비스 내용");
		vo.setWriter("서비스1");
		boardService.register(vo);
		
	}
	
	@Test
	public void testGet() {
		BoardVO vo = boardService.get(7L);
		log.info("vo=========>" + vo);
	}
	
	@Test
	public void testModify() {
		BoardVO vo = new BoardVO();
		
		vo.setBno(21L);
		vo.setTitle("서비스 수정1");
		vo.setContent("서비스 수정 내용1");
		boolean bool = boardService.modify(vo);
		//반환타입 boolean
		log.info(bool);
		
	}
	
	@Test
	public void testDelete() {
		boardService.remove(21L);
		//삭제로 반환타입 필요 X
	}
	
//	@Test
//	public void testGetList() {
//		List<BoardVO> boardVO = boardService.getList();
//		boardVO.forEach(n->log.info(n));
//		
//		boardVO.forEach(n->log.info(n.getTitle()));
		
//		boardService.getList().forEach(y->log.info(y));
		//위와 값이 동일하게 나옴..

	@Test
	public void testGetList() {
		
		Criteria cri = new Criteria(3,10);
		boardService.getList(cri).forEach(n->log.info(n));

	}
	
	

}
