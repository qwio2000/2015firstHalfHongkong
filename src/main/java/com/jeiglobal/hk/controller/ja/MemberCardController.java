package com.jeiglobal.hk.controller.ja;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jeiglobal.hk.domain.member.MemberInfo;

@Controller
@RequestMapping(value="/memberCard")
public class MemberCardController {

	@RequestMapping(value="/memberInfo")
	public ModelAndView memberInfo(MemberInfo memberInfo){
		
		
		ModelAndView mav = new ModelAndView("memberInfoPopup");
		return mav;
	}

	
}
