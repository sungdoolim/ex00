	package org.zerock.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.zerock.dao.AndDAO;
import org.zerock.vo.SampleVO;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	@Autowired
	 private AndDAO andtest;
	HttpSession session;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
	
	
	//아작스 댓글 목록
	@RequestMapping("/test")
	public void test() {//반환 타입 없는 void 라면 라면 매핑 주소(/test)가 뷰페이지 파일명이 되는 것 -> /web-inf/views/test.jsp
		
		
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@RequestMapping("/andMain3")
	public @ResponseBody JSONObject getSid(HttpServletRequest request) {
		session=request.getSession();
		String id="1";
		session.setAttribute("id", id);
		JSONObject jobj=new JSONObject();
		
		
		return jobj;
	}
	
	@RequestMapping("/androidtest")
	public @ResponseBody JSONObject andr(String id,String pw) {
		System.out.println("오오ㅗ오오오오ㅗ오오");
		System.out.println(id+pw);
        // json-simple 라이브러리 추가 필요(JSON 객체 생성)
        JSONObject jsonMain = new JSONObject(); // json 객체
        // {변수명:값, 변수명:값}
        // {sendData:[{변수명:값},{변수명:값},...]}
        List<SampleVO> items = new ArrayList<>();
        JSONArray jArray = new JSONArray(); // json배열
        
        
    	for(int i=0;i<10;i++) {
			SampleVO vo=new SampleVO();
			vo.setMno(i);
			vo.setFirstName("세종");
			vo.setLastName("대왕");
			items.add(vo);
		}
    	
    	
        for(int i=0; i<items.size(); i++){
            SampleVO dto = items.get(i);
            JSONObject row = new JSONObject();
            // json객체.put("변수명",값)
       
            row.put("f", dto.getFirstName());
            row.put("l", dto.getLastName());
            row.put("mno", dto.getMno());
            // 배열에 추가
            // json배열.add(인덱스,json객체)
            jArray.add(i,row);
        }
        // json객체에 배열을 넣음
        jsonMain.put("sendData", jArray);
        return jsonMain;
	}
	@RequestMapping("/AgetPassword")
	public @ResponseBody JSONObject getpw(String id) {
	System.out.println("getpw");
	System.out.println(id);
		 JSONObject jsonMain = new JSONObject(); // json 객체

		 String pw=andtest.getpw(id);
		 jsonMain.put("pw", pw);
		 System.out.println(pw);
		 
		 
		return jsonMain;
		
	}
	@RequestMapping("/Crawling")
	public void crawl() throws MalformedURLException, IOException {
		while(true) {
		String target="https://www.naver.com/";
		HttpURLConnection con=(HttpURLConnection) new URL(target).openConnection();
		BufferedReader br=new BufferedReader(new InputStreamReader(con.getInputStream(),"UTF-8"));
		String temp;
		int i=0;
		
		while((temp=br.readLine())!=null) {
			//원하는 데이터는 if 문으로!
			if(temp.contains("class=\"an_txt\"")) {
				// System.out.println(i+" "+temp);
				String tem=temp.split("txt\">")[1].split("<")[0];
			System.out.println(i+" "+tem);
			i++;
			}
		}
		con.disconnect();
		br.close();
		try {
			Thread.sleep(10000);// 10초마다 전체코드를 반복 하겠습니다
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}
		
		
		
	}
	
	
}
