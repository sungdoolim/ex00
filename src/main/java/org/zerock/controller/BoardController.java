package org.zerock.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	
	@Autowired
	private BoardService boardService;
	// get 으로 접근하는 매핑 주소 board_list      주소는 http://localhost:8052/controller/board/board_list
	@RequestMapping(value="/board_list",method=RequestMethod.GET)
	public String board_list(Model m) throws Exception{
		
		int totalCount=this.boardService.getCount();// 총 게시물 개수

		List<BoardVO> blist=this.boardService.getList();//List : 지정해주면 모든 타입을 저장 가능!
		m.addAttribute("totalCount",totalCount);
		m.addAttribute("blist",blist);
		return "board/board_list";
	}
	
	
	@RequestMapping(value="/board_write",method=RequestMethod.GET)//get 방식으로 접근하는 매핑주소를 처리하자
	public String board_write() {
		return "board/board_write";//  뷰리졸브 : web-inf/view/ 까지는 자동으로 잡혀있음
	}
	
	
	
	
	

}
