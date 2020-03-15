package org.zerock.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.zerock.service.AdminService;
import org.zerock.vo.AdminVO;

@Controller
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	//관리자 로그인 폼
	@GetMapping("/admin_login")
	public ModelAndView admin_login() {
		return new ModelAndView("admin/admin_login");// viewresolve 경로 : /webinf/view
	}
	
	
	//관리자 로그인 인증
	@PostMapping("/admin_login_ok")
	public String admin_login_ok(AdminVO ab,HttpServletRequest request, HttpServletResponse response,HttpSession session) throws Exception{
		
		
		response.setContentType("text/html;charset=UTF-8");
		//웹브라우저에 출력되는 문자/파일형태,언어코딩 타입 설정
		PrintWriter out=response.getWriter();
		AdminVO admin_pwd=this.adminService.adminLogin(ab.getAdmin_id());// 관리자 아이디를 기준으로 관리자 비번을 가져옴
		if(admin_pwd==null) {
			out.println("<script>");
			out.println("alert('관리자 정보가 없습니다!');");
			out.println("history.go(-1);");//페이지 뒤로!

			out.println("</script>");
			
		}
		else {
			if(!admin_pwd.getAdmin_pwd().equals(ab.getAdmin_pwd())) {
				out.println("<script>");
				out.println("alert('관리자 비번이 다릅니다');");
				out.println("history.back();");//페이지 뒤로!go(-1)과 같음
				out.println("</script>");
			}else {
				session.setAttribute("admin_id", ab.getAdmin_id());
				session.setAttribute("admin_name", admin_pwd.getAdmin_name());
				return "redirect:/admin_main";//get방식으로 컨트롤러에 이동
			}
		}
		
		
		
		return null;
		
	}
	
	
	@GetMapping("/admin_main")
	public ModelAndView admin_main(HttpServletResponse response, HttpSession session) throws Exception {
		
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		String admin_id=(String)session.getAttribute("admin_id");//request로 getsession안해도 되나..?
		if(admin_id==null) {
			out.println("<script>");
			out.println("alert('다시 로그인 하세요');");
			out.println("location='admin_login';");//페이지 뒤로!go(-1)과 같음
			out.println("</script>");
		}else {

				ModelAndView m=new ModelAndView();
				m.setViewName("admin/admin_main");// 뷰단으로 이동
				return m;
		}
		
		return null;// null이 아니면 이상해지더라고...
	}
	
	
	//관리자 로그아웃
	@PostMapping("/admin_logout")
	public ModelAndView admin_logout(HttpServletResponse response,HttpSession session) throws Exception{
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out=response.getWriter();
		
		session.invalidate();
		out.println("<script>");
		out.println("alert('관리자 로그아웃 되었습니다!');");
		out.println("location='admin_login';");//페이지 뒤로!go(-1)과 같음
		out.println("</script>");
		
		return null;
	}
	
	
	
}
