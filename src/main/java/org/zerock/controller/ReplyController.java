package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.service.ReplyService;
import org.zerock.vo.ReplyVO;

@RestController
@RequestMapping("/replies")
public class ReplyController {

	@Autowired
	private ReplyService replyService;
	
	//댓글 추가
	@RequestMapping(value="",method=RequestMethod.POST)// 포스트 방식으로 오는 주소 처리 , 주소를 안주면 replies만 해도 이거 실행함
	public ResponseEntity<String> register(@RequestBody ReplyVO vo){
		
		/*
		 * @Requestbody는 전송된 json데이터를 객체로 변환한다!!!!!!!!!!!!!!!!!!!!
		 * 데이터 전송 방식을 json을 이용한다
		 * json => replyVO
		 */
		ResponseEntity<String> entity=null;
		try {
			this.replyService.addReply(vo);// 댓글 추가
			entity=new ResponseEntity<String>("success",HttpStatus.OK);// 댓글 저장 성공시 정상코드 200이 반환됨
		}catch (Exception e) { // 예외시 에러코드 반환
					e.printStackTrace();
					entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		
		return entity;
		
	}
	@RequestMapping(value="/all/{bno}",method=RequestMethod.GET)/// all/1123처럼 숫자들어오면 됨
	public ResponseEntity<List<ReplyVO>> list(@PathVariable("bno") int bno){
		/*
		 * @pathvariable("bno") : 매핑주소의 게시물 번호값을 추출하는 용도로 활용됨 ---- 필요한가...?
		 *  
		 */
		ResponseEntity<List<ReplyVO>> entity=null;
		try {
			entity=new ResponseEntity<>(this.replyService.listReply(bno),HttpStatus.OK);// 댓글 목록을 가져오는 것! listreply의 리턴 타입은 list<replyVO>
		} catch (Exception e) {
		e.printStackTrace();
		entity=new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		}	
		return entity;
	}
	
	@RequestMapping(value="/{rno}",method= {RequestMethod.PUT,RequestMethod.PATCH})// put은 전체 자료를 수정, patch는 일부 자료를 수정
	public ResponseEntity<String> update(@PathVariable("rno") int rno,@RequestBody ReplyVO vo){
	/*
	 * requestbody : json을 객체로!
	 */
		ResponseEntity<String> entity=null;
		try {
			vo.setRno(rno);
			this.replyService.updateReply(vo);
			entity=new ResponseEntity<String>("success",HttpStatus.OK);
			
		} catch (Exception e) {

			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
		}
		return entity;
	}
	
	@RequestMapping(value="{rno}",method=RequestMethod.DELETE)// 이게 툴에 버튼이 있던데?? put,patch,get,post,delete,.....
	public ResponseEntity<String> del(@PathVariable("rno") int rno){
		ResponseEntity<String> entity=null;
		try {
			this.replyService.remove(rno);
			entity=new ResponseEntity<String>("SUCCESS",HttpStatus.OK);
			
		} catch (Exception e) {

			e.printStackTrace();
			entity=new ResponseEntity<String>(e.getMessage(),HttpStatus.BAD_REQUEST);
			return entity;
		
		}
		
		return entity;
	}
	
	

	
}
