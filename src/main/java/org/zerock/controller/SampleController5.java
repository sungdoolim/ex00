package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.vo.ProductVO;

@Controller
public class SampleController5 {

	@RequestMapping("/doJSON")
	public @ResponseBody ProductVO doJSON() {
		
		//responsebody : jsp 파일을 만들지 않고도 웹 브라우저에서 키 : 값  쌍의 데이터를 출력 가능
		//json파일을 키 : 값  으로 표현해주는 것!
	ProductVO p=new ProductVO("사과",1400);
	return p;//json 데이터의 키 이름이 productvo 빈 클래스의 변수명이 된다.
	// 이러면 데이터 뜨는데 더 깔끔히 보기위해 주소에 .json 까지 붙이면 됨
	
		
		
	}
	
	
	
}
