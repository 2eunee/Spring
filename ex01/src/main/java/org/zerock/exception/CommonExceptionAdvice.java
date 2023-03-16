package org.zerock.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.log4j.Log4j;

@ControllerAdvice
@Log4j
public class CommonExceptionAdvice {
	
	@ExceptionHandler(Exception.class)
	public String except(Exception ex, Model model) {
		
		log.error("exception..." +ex.getMessage());
		model.addAttribute("exception",ex); 
		//"exception"는 속성명으로 jsp에서 사용함 대소문자 구분 필수
		log.error(model);
		return "error_page";
	}

}
