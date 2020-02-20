package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SampleController4 {

	@RequestMapping("/doE")
	public String doE(RedirectAttributes rttr) {
		rttr.addFlashAttribute("msg","this is seven");

		System.out.println(rttr.getFlashAttributes());
		//msg에 담기, 서버단의 다른 주소로 전달! => 보안성 우수
		return "redirect:/doF"; // doE => doF메핑 주소로 전달 = 프론트가 아님~!~!!!
		
	}
	@RequestMapping("/doF")
	public void doF(@ModelAttribute("msg") String name) {
		// void형이면 메핑주소가 jsp파일명이 된다
		//@modelattribute : 서버단 <> model : 프론트 단..???

		System.out.println("전달된 값 :"+name);
		
	}
	
}
