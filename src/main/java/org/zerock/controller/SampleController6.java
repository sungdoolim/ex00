package org.zerock.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	@RequestMapping("/sendVO")//http://localhost:8052/controller/sample/sendVO.json         .json이 있어야 key:value
	public SampleVO sendVO() {// 리턴타입이 json객체의 키이름이 된다     , vo의 변수 선언 순서대로 출력
		SampleVO vo=new SampleVO();
		vo.setFirstName("홍");
		vo.setLastName("길동");
		vo.setMno(13);
		
		return vo;
	}
	@RequestMapping("/sendList")
	public List<SampleVO> sendList(){
		List<SampleVO> list=new ArrayList<>();
		for(int i=0;i<10;i++) {
			SampleVO vo=new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕");
			list.add(vo);
		}
		return list;
	}
	
	@GetMapping("/sendMap")
	public Map<Integer,SampleVO> sendMap(){// 얘는 꼭 .json해줘야 출력되네..?
		
		//가변적인 복수개의 자료를 키:값 으로 저장- 검색속도가 상당히 빠르다, 중복키 불가 , 중복 값 가능
		 Map<Integer,SampleVO> map=new HashMap<>();
		 // Map 자체적인 객체생성 불가
		 for(int i=0;i<10;i++) {
			 SampleVO vo=new SampleVO();
			 vo.setMno(i);
			 vo.setFirstName("이");
			 vo.setLastName("순신");
			 map.put(i, vo);
		 }
		return map;
		
	}
	@GetMapping("/sendError")
	public ResponseEntity<Void> sendError(){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		/*
		 * @RestController는 별도의 jsp를 만들지 않고도 Rest 서비스를 실행하기 때문에, 결과 데이터에 예외적인 상황에서 문제가 발생할수 있다!
		 * 때문에 스프링에서 제공하는 ResponsseEntity 타입은 개발자가 문제가 되는 나쁜 상태코드 404,500같은 http 나쁜상태 코드를 
		 * 데이터와 함꼐 브라우저로 전송할수 있기 때문에 좀더 세밀한 제어가 필요한 경우 사용한다
		 * 400 나쁜 상태 코드는 BAD_REQUEST가 브라우저로 전송된다
		 * 
		 * => 결국은 에러나는거를 잡을라고 쓴다 , 페이지 막는 용도로 쓰지 않을까..??
		 */
	}
	
	//정상적인 json데이터와 404 상태코드를 함께 브라우저로 전송  --- 쓰면 안될것 같대~
	@RequestMapping("/sendErrorNot")
	public ResponseEntity<List<SampleVO>> sendErrorNot(){
		List<SampleVO> list=new ArrayList<>();
		for(int i=0;i<10;i++) {
			SampleVO vo=new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕");
			list.add(vo);
		}
		return new ResponseEntity<List<SampleVO>>(list,HttpStatus.NOT_FOUND);// 이거 하면 자료가 정상적으로 뜨는데 f12개발툴 -> console에서는 not found뜨고있대...
		
	}
	
	
}
