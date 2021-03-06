package com.jeiglobal.hk.controller.common;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PreliminaryController {
	@RequestMapping(value={"/classChange","/jindo","/misu","/manageFA","/agree","/subul","/inventory","/siljuk","/monthSiljuk"
			,"/saleResult","/board","/qna"})
	public String index(HttpServletRequest requst,Model model){
		String url = requst.getRequestURI();
		model.addAttribute("title","사전준비페이지");
		model.addAttribute("contents","요청url:"+url);
		return "preliminary";
	}
}
