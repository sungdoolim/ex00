package org.zerock.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.domain.BbsVO;
import org.zerock.service.BbsService;

import com.oreilly.servlet.MultipartRequest;

@Controller
public class BbsController {

	@Autowired
	private BbsService bbsService;
	
	//자료실 글쓰기 폼
	@RequestMapping("/bbs_write")
    public String bbs_write(HttpServletRequest request,
    		Model wm) {
		int page=1;
		if(request.getParameter("page") != null) {
      page=Integer.parseInt(request.getParameter("page"));			
		}
		wm.addAttribute("page",page);
		return "bbs/bbs_write";
	}//bbs_write()
	
	//자료실 저장
	@RequestMapping("/bbs_write_ok")
	public String bbs_write_ok(@ModelAttribute BbsVO b,
			HttpServletRequest request)
	throws Exception{
String saveFolder="D:\\Spring_Work\\Project\\src\\main\\webapp\\resources\\upload";
//이진파일 업로드 서버경로
int fileSize=5*1024*1024;//이진파일 업로드 최대크기
MultipartRequest multi=null;//이진파일을 받을 참조변수

        multi=new MultipartRequest(request,saveFolder,
        		fileSize,"UTF-8");
        String bbs_name=multi.getParameter("bbs_name");
        String bbs_title=multi.getParameter("bbs_title");
        String bbs_pwd=multi.getParameter("bbs_pwd");
        String bbs_cont=multi.getParameter("bbs_cont");
        
        File UpFile=multi.getFile("bbs_file");//첨부한 이진파일
        //을 받아옴.
       if(UpFile != null) {//첨부한 이진파일이 있다면
    	   String fileName=UpFile.getName();//첨부한 파일명
    	   Calendar c=Calendar.getInstance();//칼렌더는 추상
//클래스로 new로 객체 생성을 못함. 년월일 시분초 값을 반환
           int year=c.get(Calendar.YEAR);//년도값
           int month=c.get(Calendar.MONTH)+1;//월값. +1을 한
           //이유가 1월이 0으로 반환 되기 때문이다.
           int date=c.get(Calendar.DATE);//일값
String homedir=saveFolder+"/"+year+"-"+month+"-"+date;//오늘
//날짜 폴더 경로 저장
           File path1=new File(homedir);
           if(!(path1.exists())) {
        	   path1.mkdir();//오늘날짜 폴더경로를 생성
           }
          Random r=new Random();
          int random=r.nextInt(100000000);
          
          /*첨부 파일 확장자 구함*/
          int index=fileName.lastIndexOf(".");//마침표 위치
          //번호를 구함
  String fileExtension=fileName.substring(index+1);//마침표
  //이후부터 마지막 문자까지 구함.첨부파일 확장자를 구함
  String refileName="bbs"+year+month+date+random+"."+
  fileExtension;//새로운 이진파일명 저장
  String fileDBName="/"+year+"-"+month+"-"+date+"/"+
  refileName;//디비에 저장될 레코드값
  UpFile.renameTo(new File(homedir+"/"+refileName));
  //바뀌어진 이진파일로 업로드
  b.setBbs_file(fileDBName);
       }else {//mybatis에서는 컬럼에 null을 insert하지 못함.
  String fileDBName="";
  b.setBbs_file(fileDBName);//첨부하지 않았을때 빈공백을 저장
       }
       b.setBbs_name(bbs_name); b.setBbs_title(bbs_title);
       b.setBbs_pwd(bbs_pwd); b.setBbs_cont(bbs_cont);
       
       this.bbsService.insertBbs(b);//자료실 저장
       /*문제풀이) 1. 파일을 첨부한 경우,그렇지 않은 경우 모두
        * 저장되게 처리한다.해당 테이블로 부터 저장된 레코드값도
        * 확인하고,upload폴더로 부터 오늘날짜 생성된 폴더안에
        * 바뀌어진 이진파일로 업로드 되었는지 확인한다. 
        */
		return "redirect:/bbs_list";
	}//bbs_write_ok()
	
	//자료실 목록(페이징+검색)
	@RequestMapping("/bbs_list")
	public String bbs_list(Model listM,
			HttpServletRequest request,
			@ModelAttribute BbsVO b) throws Exception{
		int page=1;
		int limit=10;//한페이지에 보여지는 목록개수
		if(request.getParameter("page") != null) {
page=Integer.parseInt(request.getParameter("page"));			
		}
		String find_name=request.getParameter("find_name");
		String find_field=request.getParameter("find_field");
        b.setFind_field(find_field);
        b.setFind_name("%"+find_name+"%");
        //%는 검색에서 하나이상의 임의의 문자와 매핑 대응하는
        //와일드 카드문자
        
		int totalCount=this.bbsService.getListCount(b);
		//총레코드 개수,검색후 레코드 개수
		
		b.setStartrow((page-1)*10+1);//시작행번호
	    b.setEndrow(b.getStartrow()+limit-1);//끝행 번호
		
		List<BbsVO> blist=this.bbsService.getBbsList(b);
		//목록
		
		//총 페이지수
		int maxpage=(int)((double)totalCount/limit+0.95);
		//시작페이지(1,11,21 ..)
		int startpage=(((int)((double)page/10+0.9))-1)*10+1;
		//현재 페이지에 보여질 마지막 페이지(10,20 ..)
		int endpage=maxpage;
		if(endpage>startpage+10-1) endpage=startpage+10-1;
		
		listM.addAttribute("blist",blist);
		listM.addAttribute("page",page);
		listM.addAttribute("startpage",startpage);
		listM.addAttribute("endpage",endpage);
		listM.addAttribute("maxpage",maxpage);
		listM.addAttribute("listcount",totalCount);
		listM.addAttribute("find_field",find_field);
		listM.addAttribute("find_name",find_name);
	
		return "bbs/bbs_list";
	}//bbs_list()
	
	//자료실 내용보기+답변폼+수정폼+삭제폼
	@RequestMapping("/bbs_cont")
	public ModelAndView bbs_cont(
			@RequestParam("bbs_no") int bbs_no,
			@RequestParam("state") String state,
			@RequestParam("page") int page,
			@ModelAttribute BbsVO b) {
		
		if(state.equals("cont")) {//내용보기 일때만 조회수
			//증가(aop를 통한 트랜잭션 처리)
			b=this.bbsService.getBbsCont(bbs_no);
		}else {//답변폼,수정폼,삭제폼일때는 조회수 증가를 안함
			//이부분은 트랜잭션 적용안함.
			b=this.bbsService.getBbsCont2(bbs_no);
		}
	String bbs_cont=b.getBbs_cont().replace("\n","<br/>");
//textarea 영역에서 엔터키 친부분을 개행 줄바꿈
	
	     ModelAndView cm=new ModelAndView();
	     cm.addObject("b",b);
	     cm.addObject("bbs_cont",bbs_cont);
	     cm.addObject("page",page);
	     
	     if(state.equals("cont")) {//내용보기
	    	 cm.setViewName("bbs/bbs_cont");
	     }else if(state.equals("reply")) {//답변폼
	    	 cm.setViewName("bbs/bbs_reply");
	     }else if(state.equals("edit")) {//수정폼
	    	 cm.setViewName("bbs/bbs_edit");
	     }else if(state.equals("del")) {//삭제폼
	    	 cm.setViewName("bbs/bbs_del");
	     }
	     return cm;
	}//bbs_cont()
	
	//자료실 관리자 답변저장
	@RequestMapping("/bbs_reply_ok")
	public String bbs_reply_ok(BbsVO rb,
			HttpServletRequest request) {
		int page=1;
		if(request.getParameter("page") != null) {
    page=Integer.parseInt(request.getParameter("page"));			
		}
		this.bbsService.replyBBs(rb);//답변저장
		return "redirect:/bbs_list?page="+page;
	}//bbs_reply_ok()
	
	//자료실 수정
	@RequestMapping("/bbs_edit_ok")
	public ModelAndView bbs_edit_ok(
			HttpServletRequest request,
			HttpServletResponse response,
			@ModelAttribute BbsVO b) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
String saveFolder="D:\\Spring_Work\\Project\\src\\main\\webapp\\resources\\upload";
//이진파일 업로드 서버 경로
int fileSize=5*1024*1024;//이진파일 최대크기

MultipartRequest multi=null;//이진파일 받을 참조변수
multi=new MultipartRequest(request,saveFolder,fileSize,
		"UTF-8");
int bbs_no=Integer.parseInt(multi.getParameter("bbs_no"));
int page=1;
if(multi.getParameter("page") != null) {
	page=Integer.parseInt(multi.getParameter("page"));
}
String bbs_name=multi.getParameter("bbs_name");
String bbs_title=multi.getParameter("bbs_title");
String bbs_pwd=multi.getParameter("bbs_pwd");
String bbs_cont=multi.getParameter("bbs_cont");

BbsVO db_pwd=this.bbsService.getBbsCont2(bbs_no);
if(!db_pwd.getBbs_pwd().equals(bbs_pwd)) {
	out.println("<script>");
	out.println("alert('비번이 다릅니다!');");
	out.println("history.back();");
	out.println("</script>");
}else {
	File UpFile=multi.getFile("bbs_file");
	if(UpFile != null) {
		String fileName=UpFile.getName();//첨부한 파일명
File DelFile=new File(saveFolder+db_pwd.getBbs_file());
//삭제할 파일 객체를 생성
if(DelFile.exists()) {
	DelFile.delete();//기존 이진파일을 삭제
}
Calendar c=Calendar.getInstance();
int year=c.get(Calendar.YEAR);//년도값
int month=c.get(Calendar.MONTH)+1;//월값
int date=c.get(Calendar.DATE);
String homedir=saveFolder+"/"+year+"-"+month+"-"+date;
File path1=new File(homedir);
if(!(path1.exists())) {
	path1.mkdir();
}
Random r=new Random();
int random=r.nextInt(100000000);

/*확장자 구함*/
int index=fileName.lastIndexOf(".");
String fileExtension=fileName.substring(index+1);//확장자
String refileName="bbs"+year+month+date+random+"."+
fileExtension;
String fileDBName="/"+year+"-"+month+"-"+date+"/"+
refileName;
UpFile.renameTo(new File(homedir+"/"+refileName));
b.setBbs_file(fileDBName);
	}else {//파일을 첨부하지 않은 경우
String fileDBName="";
if(db_pwd.getBbs_file() != null) {
	b.setBbs_file(db_pwd.getBbs_file());
}else {
	b.setBbs_file(fileDBName);
}
	}
	b.setBbs_no(bbs_no); b.setBbs_name(bbs_name);
	b.setBbs_title(bbs_title); b.setBbs_cont(bbs_cont);
	this.bbsService.editBbs(b);//자료실 수정
	/* 문제풀이) 번호를 기준으로 이름,제목,내용,첨부파일을
	 * 수정되게 한다.(테스트 까지 완료) 
	 */
	ModelAndView em=new ModelAndView("redirect:/bbs_cont");
	em.addObject("bbs_no",bbs_no);
	em.addObject("page",page);
	em.addObject("state","cont");
	return em;//  bbs_cont?bbs_no=번호&page=쪽번호&state=cont
	//3개의 인자값이 get방식으로 리다이렉트 한다.
}
		return null;
	}//bbs_edit_ok()
	
	//자료실 삭제완료
	@RequestMapping("/bbs_del_ok")
	public String bbs_del_ok(
			@RequestParam("bbs_no") int bbs_no,
			@RequestParam("del_pwd") String del_pwd,
			HttpServletResponse response,
			HttpServletRequest request)
	throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
String up="D:\\Spring_Work\\Project\\src\\main\\webapp\\resources\\upload";		

int page=1;
if(request.getParameter("page") != null) {
page=Integer.parseInt(request.getParameter("page"));	
}
BbsVO db_pwd=this.bbsService.getBbsCont2(bbs_no);//디비로 부터
//비번을 가져옴
if(!db_pwd.getBbs_pwd().equals(del_pwd)) {
	out.println("<script>");
	out.println("alert('비번이 다릅니다!');");
	out.println("history.go(-1);");
	out.println("</script>");
}else {
	this.bbsService.delBbs(bbs_no);//자료실 삭제
	/*문제풀이)번호를 기준으로 디비로 부터 자료를 삭제되게 
	 * 한다.(테스트까지 완료)
	 */
	if(db_pwd.getBbs_file() != null) {//첨부 파일이 있는 경우
		File file=new File(up+db_pwd.getBbs_file());
		//삭제할 파일객체 생성
		file.delete();//기존 파일을 폴더로 부터 삭제
	}
	return "redirect:/bbs_list?page="+page;
}
return null;
	}//bbs_del_ok()
}
































































































































