package org.zerock.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.service.BoardService;
import org.zerock.vo.BoardVO;

@Controller
@RequestMapping("/board/*")
public class BoardController {
HttpSession session;
	
	@Autowired
	private BoardService boardService;
	// get 으로 접근하는 매핑 주소 board_list      주소는 http://localhost:8052/controller/board/board_list
	@RequestMapping(value="/board_list",method=RequestMethod.GET)
	public String board_list(Model m, HttpServletRequest request,@ModelAttribute BoardVO b) throws Exception{

		session=request.getSession();
		//System.out.println(session.getAttribute("test"));/// session 성공
		
		
		//페이징
		int page=1;// 현재 쪽 번호
		int limit=10;//한 페이지에 보여지는 목록 개수
		System.out.println("list");
		if(request.getParameter("page")!=null) {
			//전달된 page가 존재한다면
			page=Integer.parseInt(request.getParameter("page"));// 내가봤을때는 좀더 효율적으로 바꿀 수 있음
			System.out.println("list 받은 값 : "+page);
			
		}
		b.setStartrow((page-1)*10+1);//시작행 번호    1이면 1 2이면 11 3이면 21
		b.setEndrow(b.getStartrow()+limit-1);     //1이면 10 2이면 20 3이면 30
		
		int totalCount=this.boardService.getCount();// 총 게시물 개수

		List<BoardVO> blist=this.boardService.getList(b);//List : 지정해주면 모든 타입을 저장 가능! , db에서 가져오는 갯수 제한
		
		// 총 페이지수 total=68이면 maxpage=7
		int maxpage=(int)((double)totalCount/limit+0.95);
		
		//현재 페이지에 보여질 시작 페이지                               page=5라면  1.4 -1  0.4*10+1=5
		int startpage=(((int)((double)page/10+0.9))-1)*10+1;      
		
		//현재 페이지에 보여질 마지막 페이지     total=68이고 page=5라면 일단 end=7인데 7>41+10-1                      
		int endpage=maxpage;
		if(endpage>startpage+10-1) {
			endpage=startpage+10-1;
		}
		
		
		m.addAttribute("totalCount",totalCount);
		m.addAttribute("blist",blist);
		m.addAttribute("startpage",startpage);
		m.addAttribute("endpage",endpage);
		m.addAttribute("maxpage", maxpage);
		m.addAttribute("page", page);
		
		return "board/board_list";
	}
	
	
	@RequestMapping(value="/board_write",method=RequestMethod.GET)//get 방식으로 접근하는 매핑주소를 처리하자
	public String board_write(HttpServletRequest request) {
		session=request.getSession();
		session.setAttribute("test", "test성공???");
		int page=1;
		if(request.getParameter("page")!=null) {
			page=Integer.parseInt(request.getParameter("page"));
		}request.setAttribute("page", page);
	//	HttpSession session=request.getSession();
		
		return "board/board_write";//  뷰리졸브 : web-inf/view/ 까지는 자동으로 잡혀있음
	}
	
	
	
	//게시판 저장
	@RequestMapping(value="/board_write_ok",method=RequestMethod.POST)//post로 접근하는 매핑 주소를 처리
	 public String board_write_ok(BoardVO b,RedirectAttributes rttr) 
			 throws Exception{
		/*
		 * b는 넘어오는 파라미터 이름과 vo의 변수명이 일치하면 b객체에 set함
		 * 
		 */
		this.boardService.insertBoard(b); 
		
		rttr.addFlashAttribute("msg","SUCCESS"); // redirect 매핑 주소에 값을 전달     
		//addAttribute - 영구성 / addFlashAttribute- 휘발성 
		
		return "redirect:/board/board_list"; // redirect는 get 방식을 사용함 
	}
	
	//게시물 조회수 증가 + 내용보기   , getmapping은 requestmapping과 동일 기능 , 최신기능
	@GetMapping("/board_cont")// get만! post는 postmapping
	public ModelAndView board_cont(@RequestParam("bno") int bno,HttpServletRequest request,int page) 
	throws Exception{
	//request.setAttribute("page", page);
		
	//session.setAttribute("page", page);
	
		/*
		 * requestparam  :: bno를 가져와서 bno에 저장 근데 전부 다 지우고 int bno만 했어도 전달 받을수 있음!!!!!!!!!
		 * @RequestParam("bno")을 지워도 된다고...
		 */
	
		BoardVO b=this.boardService.getCont(bno);
		//1)게시물 내용을 가져오고, 2)조회수도 증가시킴
		ModelAndView cm=new ModelAndView();// Model m 쓰는것과 같은 기능인 듯
		cm.addObject("b",b);
		cm.addObject("page",page);
		cm.setViewName("board/board_cont");// 뷰리졸브 경로 설정 => /web-inf/view/board/board_cont
		
		return cm;
		
	}
	
	//게시물 수정
	@GetMapping("/board_edit")
	public String board_edit(int bno, Model m,int page) {
		// bno와 같은 이름의 값을 저장 받은 것임!!!!
		BoardVO eb=this.boardService.getCont2(bno);
		// 번호에 해당하는 내용을 오라클로부터 가져옴   , getCont는 카운트 증가 하니까...
		
		m.addAttribute("eb",eb);
		m.addAttribute("page",page);
		//ModelAndView와 같은 기능인데  string반환 타입으로 바꿈
		return "board/board_edit";
		
		
	}
	@RequestMapping("/board_edit_ok")//get or post 둘다 가능 
	public String board_edit_ok(@ModelAttribute BoardVO eb,
			RedirectAttributes rttr,int page,HttpServletRequest request)throws Exception {
		/*
		 *board_edit.jsp의 입력된 값이 저장된 eb
		 *
		 **/
	
		this.boardService.editBoard(eb);
		rttr.addFlashAttribute("msg", "SUCCESS");
	
		//request.setAttribute("page", page);
		
		
		return "redirect:/board/board_list?page="+page;// 얘도 controller로
	}
	
	@RequestMapping("/board_del")
	public ModelAndView board_del(HttpServletRequest request,RedirectAttributes rttr) throws Exception{
		
		
		int bno=Integer.parseInt(request.getParameter("bno"));//board_cont에서 넘어옴.... 잘 안쓸듯....
		this.boardService.delBoard(bno);
		rttr.addFlashAttribute("msg", "SUCCESS");
		
		return new ModelAndView("redirect:/board/board_list");
		
		
	}
	
	

}
