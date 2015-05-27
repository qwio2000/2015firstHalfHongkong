package com.jeiglobal.hk.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
//	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	/**
	 * 로그인 페이지
	 * @param returl : 로그인성공되면 리턴될 url
	 * @return
	 */
	@RequestMapping(value={"/login","/"})
	public String login(Model model,@RequestParam(value="returl",required=false) String returl){
//      로그찍을때
//		logger.trace("trace");
//		logger.debug("debug");
//		logger.info("info");
//		logger.warn("warn");
//		logger.error("error");
		
		model.addAttribute("title", "로그인페이지");
		model.addAttribute("requrl",returl);
	    return "login";
	}
}
