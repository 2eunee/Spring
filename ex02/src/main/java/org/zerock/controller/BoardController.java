package org.zerock.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;
import org.zerock.domain.PageDTO;
import org.zerock.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import oracle.jdbc.proxy.annotation.Post;

@Controller
@RequestMapping("/board/*")
@Log4j
@AllArgsConstructor


//BoardController -> interface BoardService -> interface BoardMapper -> BoardMapper.xml순으로 간다
//최종 BoardMapper.xml를 통해서 데이터베이스로 보내기 때문
public class BoardController {

	private BoardService service;
	
	/*
	private final BoardMapper BoardMapper;
	public BoardController(BoardService boardService,
			boardMapper boardMapper) {
	this.boardService = boardService;
	this.boardMapping = boardMapping;
	*/	
		
	
	
//	@GetMapping("/list")
//	public void list(Model model) {
//		List<BoardVO> list = service.getList();
//	
//		model.addAttribute("list", list);
//		log.info(list);
//		
//	}
	
		//위의 list는 파라미터가 없이 처리 되었기 때문에 
		//pageNum,amount를 처리하기 위해서 같이 수정함
	
	@GetMapping("/list") 
	public void list(Criteria cri, Model model) {
		
		log.info("list=====>" + cri);
		model.addAttribute("list", service.getList(cri));
		model.addAttribute("pageMaker", new PageDTO(cri,123));
		//현재페이지, 페이지당 화면상 보여지는 레코드 수, 전체 카운트 pageMaker에 담긴다
		
		int total = service.getTotal(cri);
		
		log.info("total:======> " + total);
		
		model.addAttribute("pageMaker", new PageDTO(cri, total));
		
	}
	
	
	@GetMapping("/register")
	public void register() {
	// WEB-INF/Views/board/register.jsp를 찾아간다
	//register는 2개지만 get방식이기 때문에 실행됨	
	}
	
	@PostMapping("/register")
	public String register (BoardVO board, RedirectAttributes rttr) {
		log.info("register : " + board);
		service.register(board);
		rttr.addFlashAttribute("result", board.getBno());
		
		//addFlashAttribute는 딱 한번만 전송함, 새로고침을 해도 재전송 불가하다
		
		return "redirect:/board/list"; 
		//"board/list" ==> PRG방식..//포워드방식
	
	}
	
//	@GetMapping("/get")
//	public void get(@RequestParam("bno") Long bno, Model model) {
//		log.info("/get..");
//		model.addAttribute("board", service.get(bno));
//		// WEB-INF/Views/board/get.jsp를 찾아간다
//	}
	
	@GetMapping({"/get","/modify"})
	public void get(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, Model model) {
		log.info("/get or modify=============");
		
		model.addAttribute("board", service.get(bno));
		// WEB-INF/Views/board/get.jsp를 찾아간다
		//동일한 get방식이면 get/modify처리 방식이 동일하기 때문에 배열로 
		//({"/get","/modify"}) 하나로 만들어줄 수 있다 ====> 위의 방식과 동일
		//@ModelAttribute는 자동으로 Model에 지정한 데이터를 담아줌
	}
	
	@PostMapping("/modify")
	public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		//수정과 삭제처리는 POST로 @ModelAttribute("cri") Criteria cri를 추가해준다
		log.info("/modify=====>" + board);
		
		if(service.modify(board)) { //데이터 수정 성공시 result 값을 가져감
			rttr.addFlashAttribute("result", "success");
			
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list"; //PRG방식
		// 성공시 board/list창에 success를 띄어준다
	}

	@PostMapping("/remove")
	public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri, RedirectAttributes rttr) {
		log.info("remove.." + bno);
		
		if(service.remove(bno)) {
			rttr.addAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum", cri.getPageNum());
		rttr.addAttribute("amount", cri.getAmount());
		
		return "redirect:/board/list";
	}

	



}



//getList(); 순서

//board의 list호출
//1. BoardController getList(); 호출되며 
//2. BoardServicelmpl의 List<BoardVO> getList()로 호출함
//3. 호출받은 List<BoardVO>는  public interface BoardMapper public List<BoardVO> getList();를 호출함
//4. BoardMapper.xml의  
//<select id="getList" resultType="org.zerock.domain.BoardVO">
//select * from tbl_board where bno > 0
//</select>
//이 호출되며 sql에서 데이터를 받아와서 public interface BoardMapper public List<BoardVO>에 담음
//5.BoardServicelmpl의 return boardMapper.getList();로 list값을 리턴받아옴
//6.BoardController의 model.addAttribute("list")에 담김
//7.list.jsp에 ${list}에도 담겨있다

