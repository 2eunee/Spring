package org.zerock.controller;

import java.io.File;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.log4j.Log4j;

@Controller
@Log4j
public class UploadController {
	
	
		
	
	@GetMapping("/uploadForm")
	public void uploadForm() {
		
		log.info("uploadForm");
	}
	
	@PostMapping("/uploadFormAction")
	public void uploadFormPost(MultipartFile[] uploadFile, Model model) {
		
		String uploadFolder = "C:\\upload";
		
		for(MultipartFile multipartFile : uploadFile) {
			
			log.info("---------------------------------");
			
			log.info("upload File Name : " + multipartFile.getOriginalFilename());
			log.info("upload File Size : " + multipartFile.getSize());
			
			File saveFile = new File(uploadFolder, multipartFile.getOriginalFilename());
			
			try {
				multipartFile.transferTo(saveFile);
			}catch (Exception e) {
				log.error(e.getMessage());
			}
			
		}
	}
		
	@GetMapping("/uploadAjax")
	public void uploadAjax() {
			
			log.info("upload ajax");
	}
	
	@PostMapping("/uploadAjaxAction")
	   public void uploadAjaxPost(MultipartFile[] uploadFile) {
	         
	      String uploadFolder="C:\\upload";
	      
	      for(MultipartFile multipartFile:uploadFile) {
	         log.info("----------------------------------");
	         log.info("Upload File Name: "+multipartFile.getOriginalFilename());
	         log.info("Upload File Size: "+multipartFile.getSize());
	         
	         String uploadFileName=multipartFile.getOriginalFilename();
	         //IE는 전체 파일경로가 전송되므로, 마지막\를 기준으로 잘라낸다.
	         uploadFileName=uploadFileName.substring(uploadFileName.lastIndexOf("\\")+1);
	         //log.info("only file name: "+uploadFileName);
	         
	         File saveFile = new File(uploadFolder,uploadFileName);
	         
	         try {
	            multipartFile.transferTo(saveFile);
	         }catch(Exception e) {
	            log.error(e.getMessage());
	         }
	      }//end for   
		
	}
		
}

