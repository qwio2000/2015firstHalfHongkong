package com.jeiglobal.hk.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping(value="/iphei")
public class IpheiController {
	
	@RequestMapping
	public String index(HttpServletRequest requst,Model model){
		String url = requst.getRequestURI();
		model.addAttribute("title","입복회페이지");
		model.addAttribute("contents","요청url:"+url);
		return "common/iphei";
	}
}
