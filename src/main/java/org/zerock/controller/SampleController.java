package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller//@Controller 에너테이션을 사용하면 해당 클래스는 스프링에서 인식함
public class SampleController {

	@RequestMapping("doA")//get or post 로 접근하는 doA매핑 주소를  웹주소창에서 실행하게 함
	//	http://localhost:8052/controller/doA주소로 들어가면 controller실행
	public void doA() {
		//리턴 타입이 없는 void 형이면 메핑주소인 doA가 jsp파일명 doA.jsp가된다.
		System.out.println("doA메핑 주소가 실행 됨");
	}
	

	@RequestMapping("doB")// 메핑주소는 같은 주소로 등록할수 없음 , 다른 클래스 포함 마찬가지
	// notfound가 뜨는데 return .jsp파일이 없기때문
	public void doB() {
		System.out.println("doB메핑 주소가 실행 됨");
	}
	
	
	
	
	
}
