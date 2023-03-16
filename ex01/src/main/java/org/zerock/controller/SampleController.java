package org.zerock.controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.TodoDTO;

import lombok.extern.log4j.Log4j;

@Controller
//@ResponseBody
@RequestMapping("/sample/*")
@Log4j
public class SampleController {
			
		@PostMapping("/exUploadPost")
		public void exUploadPost(ArrayList<MultipartFile> files) {
			files.forEach(file -> {
				log.info("--------------");
				log.info("name:" + file.getOriginalFilename());
				log.info("size: " +	file.getSize());
			});
		}
		
		
		
		@GetMapping("/exUpload")
		public void exUpload() {
			log.info("exUpload==========>");
		}
	
		
	
	
		@GetMapping("/ex07")
		public ResponseEntity<String> ex07() {
			
			String msg = "{\"name\" : \"홍길동\"}";
			//log.info("/ex07");
			HttpHeaders header = new HttpHeaders();
			header.add("Content-Type", "application/json;charset=utf-8");
			
			return new ResponseEntity<>(msg, header, HttpStatus.OK);
		}
	
	
	
		@GetMapping("/ex06")
		public @ResponseBody SampleDTO ex06() {
			SampleDTO dto = new SampleDTO();
			dto.setName("홍길동");
			dto.setAge(20);
			return dto;
		}
		
		@GetMapping("/ex06_1")
		public @ResponseBody String ex06_1() {
			
			return "ok";
		}
	
	
		
		@GetMapping("/ex05")
		public String ex05(RedirectAttributes rttr) {
			
			rttr.addAttribute("name","AAA");
			rttr.addAttribute("age",10);
			return "redirect:/";
		
		}
		
		
	
		@GetMapping("/ex04")
		public String ex04(SampleDTO dto,
				@ModelAttribute ("page") int page) {
			log.info("dto :" + dto);
			log.info("page" + page);
			
			//WEB-INF/views/sample/ex04.jsp
			return "/sample/ex04";
		}
		
	
		
		@InitBinder
		public void initBinder(WebDataBinder binder) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");
			binder.registerCustomEditor(java.util.Date.class,new
				CustomDateEditor(dateFormat, false));
		}
		
		@GetMapping("/ex03")
		public String ex03(TodoDTO todo) {
			log.info("todo" + todo);
			return "ex03";
		}
	
		@GetMapping("/todo")
		public String todo(TodoDTO dto) {
			log.info(dto);
			return "todo";
		}
	
		
		
		@GetMapping("/ex02List")
		public String ex02List(@RequestParam("ids") ArrayList<String> ids) {
			
			log.info("ids : " + ids);
			
			return "ex02List";
		}
	
			
	
	
		@GetMapping("/ex02")
		public String ex02 (@RequestParam("name") String name, 
				@RequestParam("age") int age) {
			
			log.info("name : " + name);
			log.info("age : " + age);
			return "ex02";
		
		}

		@GetMapping("/ex01")
		public String ex01(SampleDTO dto) {
			
			log.info(" " + dto);
			return "ex01";
		}
		
	
		
//		@RequestMapping("")
//		private void basicGet() {
//			
//			log.info("basic get...");
//		
//		}
		
		@ResponseBody 
	 	@RequestMapping(value ="/basic", method = {RequestMethod.GET, RequestMethod.POST})
		private String basicGet() {
			
			log.info("basic get..."); 
		
			return "ok";
		}
		
		//반환타입이 String일 경우 @RequestMapping으로 받으면 ok.jsp 페이지를 뷰에서 찾는다. 
		//페이지가 없음으로 오류
		//반환타입이 String이 아닌 void일 경우 sample/basicGet.jsp로 찾아감
		//@ResponseBody //ResponseBody로 받으면 반환되는 ok라는 String 자체를 받음으로 ok가 출력됨!
			

		
		@ResponseBody
		@GetMapping("/basicOnlyGet/{userID}")
		public String basicGet2(@PathVariable ("userID") String userid) {
			
			log.info("basic get only get..." + userid);
			return "abc";
		}
		
		@GetMapping("/basicOnlyGet")
		public void basicPost() {
			
			log.info("basic get only get...");
		}
		
		@ResponseBody
		@GetMapping("/abc/{num}")
		public String abc(@PathVariable int num) {
			return "ok-get";
		}
		
		@ResponseBody
		@PostMapping("/abc/{num}")
		public String abc2(@PathVariable int num) {
			return "ok-post";
		}
		
		@ResponseBody
		@DeleteMapping("/abc/{num}")
		public String abc3(@PathVariable int num) {
			return "ok-delete";
		}
		 
		
		//요청헤더
		@ResponseBody
		@RequestMapping("/hearder")
		public String headers(HttpServletRequest request,
							  HttpServletResponse response,
							  HttpMethod httpMethod,
							  Locale locale,
							  @RequestHeader MultiValueMap <String, String> headerMap,
							  @RequestHeader("host") String host,
							  @RequestHeader(value="myCookie", required = false) String cookie) {
			
			log.info("---------------------------");
			log.info(request);
			log.info(response);
			log.info(httpMethod);
			log.info(locale);
			log.info(headerMap);
			log.info(host);
			log.info(cookie);
			

			return "ok";
		}
		
	//	@ResponseBody
		@RequestMapping("/request-param1")
		public void requestParam1 (HttpServletRequest request, HttpServletResponse response) throws IOException {
			String userid =request.getParameter("userid");
			int age = Integer.parseInt(request.getParameter("age"));
			
			response.getWriter().write("ok");
			
		}

		@ResponseBody
		@RequestMapping("/request-param2")
		public String requestParam2(@RequestParam("userid")String userid,
								    @RequestParam("age") int age) {

			log.info(userid + " : " + age);
			return "ok";
		
		}
		
		@ResponseBody
		@RequestMapping("/request-param3")
		public String requestParam3(String userid, int age) {

			log.info(userid + " : " + age);
			return "ok";
		
		}
		
		@ResponseBody
		@RequestMapping("/request-param4")
		public String requestParam4(@RequestParam(defaultValue = "noUser") String userid,
								    @RequestParam(defaultValue = "20") int age) {

			log.info(userid + " : " + age);
			return "ok";
		//defaultValue 기본Value값이 들어감
		}
		
		@ResponseBody
		@RequestMapping("/request-param5")
		public String requestParam5(@ModelAttribute SampleDTO dto) {
				
			log.info(dto.getName() + " : " + dto.getAge());
		
			return "ok";
		//sample DTO 생성 후 post 방식으로 바디에 name, age 입력 후 send
		//sample DTO에 name,age를 찾아가서 값을 넣어줌 
		}
		
		
		@ResponseBody
		@RequestMapping("/request-param6")
		public String requestParam6(SampleDTO dto) {
				
			log.info(dto.getName() + " : " + dto.getAge());
		
			return "ok";
		
		//@ModelAttribute가 없어도 DTO에 setter를 통해서 값을 넣어준다.
		}	
		
		
		
		
		
		//http body형태로 왔을 때 예제!(아래 3가지)
		
		@PostMapping("/request-body1")
		public void requestBody1(HttpServletRequest request, HttpServletResponse response) throws IOException {
			ServletInputStream inputStrema = request.getInputStream();
			String message = StreamUtils.copyToString(inputStrema, StandardCharsets.UTF_8);
			log.info("message : " + message);
			
			response.getWriter().write(message);
			
			//body를 post로 받아와서 inputStrema에 저장 후 StandardCharsets.UTF_8로 변환하여 출력해줌
		}
		
		
		@PostMapping("/request-body2")
		public HttpEntity<String> requestBody2(HttpEntity<String> entity) {
				
			String message = entity.getBody();
			HttpHeaders headers = entity.getHeaders();
			
			
			log.info("headers : " + headers);
			log.info("message : " + message);
			
			return new HttpEntity<String>(message);
		//위에 방식보다는 현재의 방식을 많이 씀!(대다수가 쓴다)
		}
		
		@ResponseBody
		@PostMapping("/request-body3")
		public String requestBody3(@RequestBody String message) {
			
			log.info("message : " + message);
			
			return message;
			//한글은 깨짐
		}
		
		
		
}