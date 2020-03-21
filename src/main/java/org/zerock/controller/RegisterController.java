package org.zerock.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.dao.AdminDAO;
import org.zerock.vo.AdminVO;

@Controller
public class RegisterController {

	
	
	@Autowired
	AdminDAO adao;
	
	@RequestMapping("/register")
	public String register() {

		return "/register";
	}
	@RequestMapping("/register_ok")//회원 가입 -> 암호화 비번이 들어가게됨
	public String register_ok(AdminVO a) {

		adao.adminRegister(a);
		return "/register_ok";
	}
	
	@RequestMapping("/fileio")
	public String fileio() {
		return "/fileio";
	}
	@RequestMapping("/uploadFile")
	public String uploadFile(HttpServletRequest request, @RequestParam("imgFile") MultipartFile imgFile , Model model) {
		String savePath="C:\\Users\\bohee\\git\\ex00\\src\\main\\webapp\\resources\\upload";
		System.out.println("uploadFile");
		String originalFilename = imgFile.getOriginalFilename(); // fileName.jpg
	    String onlyFileName = originalFilename.substring(0, originalFilename.indexOf(".")); // fileName
	    String extension = originalFilename.substring(originalFilename.indexOf(".")); // .jpg
	    String rename = onlyFileName + "_" + extension; // fileName_20150721-14-07-50.jpg
	    String fullPath = savePath + "\\" + rename;
	    if (!imgFile.isEmpty()) {
	        try {
	            byte[] bytes = imgFile.getBytes();
	            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(new File(fullPath)));
	            stream.write(bytes);
	            stream.close();
	            model.addAttribute("resultMsg", "파일을 업로드 성공!");
	        } catch (Exception e) {
	            model.addAttribute("resultMsg", "파일을 업로드하는 데에 실패했습니다.");
	        }
	    } else {
	        model.addAttribute("resultMsg", "업로드할 파일을 선택해주시기 바랍니다.");
	    }
		
		return "/uploadFile";
	}
	@RequestMapping("/downloadFile")
	public String downloadFile(HttpServletRequest request,HttpServletResponse response) throws UnsupportedEncodingException {
String fileName = request.getParameter("filename");
		
		// ② 경로 가져오기
		String saveDir = "C:\\Users\\bohee\\git\\ex00\\src\\main\\webapp\\resources\\upload";
		String a="city.jpg";
		File file = new File(saveDir + "\\"+a);

		response.setContentType("application/octet-stream");
		String filename = new String(a.getBytes("UTF-8"), "8859_1");
			
			response.setHeader("Content-Disposition", "attachment;filename=" + filename);
			try {
				BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));

				
				try {
					BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
					int data;
					while((data=bis.read()) != -1){
						bos.write(data);
						bos.flush();
					}

					// 8] 스트림 닫기
					bis.close();
					bos.close();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 6] 웹브라우저에 연결할 출력 스트림 생성 
		
	
		return "downloadFile";
		
		
		
	}
	
}
