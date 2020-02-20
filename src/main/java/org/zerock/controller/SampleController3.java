package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zerock.vo.ProductVO;

@Controller
public class SampleController3 {

	@RequestMapping("/nameprice")
	public String nameprice(Model m) {
		ProductVO p=new ProductVO("신발",1200000);
		m.addAttribute("p1",p);// p를 p1에 저장
		return "shop/pro_name";//web-inf/views/shop/pro_name.jsp
		
	}
}
