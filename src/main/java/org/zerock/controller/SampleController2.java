package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller// 컨트롤러로 등록
public class SampleController2 {

	@RequestMapping("doC")//웹 주소로 접근시 사용
	public String doC(@ModelAttribute("msg") String message) {
		//@modelattribute("msg") : msg파라미터 이름에 인자값을 문자열로 전달한다
		//웹 주소창에서 doC?msg=문자열  : 형태의 노출되는 get 방식으로 전달됨 - 보안 취약/ 요거 post로 전달되더라도 받아줌
		//System.out.println(msg);
		System.out.println(message);
		// 받아온 msg를 message에 저장 하지만 프론트로 전달은 아님/ 전달시 model 이용
		
		
		return "/result";// 뷰리졸브 경로 : web-inf/views/result.jsp
		
		//return "result";
	}
	
}
