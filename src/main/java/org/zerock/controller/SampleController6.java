package org.zerock.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.vo.SampleVO;

@RestController // 스프링 4.0이후부터 지원하는 에노테이션 : jsp뷰 페이지를 만들지 않고도 ,Rest방식의 데이터 처리를 위해 사용함 : 만들어지는 객체는 String,JSON,XML

@RequestMapping("/sample")// 컨트롤러 자체의 매핑 주소 등록
public class SampleController6 {

	
	@GetMapping("/hello")//get으로 접근하는 메핑주소 처리      http://localhost:8052/controller/sample/hello
	public String hello() {
		return "rest service begin";// 문자열 객체를 반환!
	}
	@RequestMapping("/sendVO")
	public SampleVO sendVO() {// 리턴타입이 json객체의 키이름이 된다
		
		return null;
	}
	
	
}
