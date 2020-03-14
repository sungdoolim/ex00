package org.zerock.controller;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.service.MessageService;
import org.zerock.vo.MessageVO;

@RestController
@RequestMapping("/message")// controller자체의 매핑 주소 등록
public class MessageController {
	
	@Inject
	private MessageService messageService;
	
	//메시지 추가
	@RequestMapping(value="/",method=RequestMethod.POST)//포스트로 접근하는 주소를 처리
	public ResponseEntity<String> addMessage(@RequestBody MessageVO vo){
		//ResponseEntity는 <>의 값과 에러코드를 같이 반환 한다!
		ResponseEntity<String> entity=null;
		try {
			
		this.messageService.addMessage(vo);
		entity=new ResponseEntity<>("SUCCESS",HttpStatus.OK);//200 코드가 함께 반환됨
		
			
		}catch(Exception e) {
			e.printStackTrace();
		entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);//<>안쪽 생략가능
		return entity;
		
		}

		return entity;
	}
	
	
	
	

}
