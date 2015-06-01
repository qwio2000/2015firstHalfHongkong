package com.jeiglobal.hk.controller.fa;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.jeiglobal.hk.domain.common.*;
import com.jeiglobal.hk.service.*;

@Controller
@RequestMapping(value="/ipgum")
public class IpgumController {
	
	@Autowired
	private IpgumService ipgumService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping
	public ModelAndView ipgum(){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ipgum/ipgumIndex");
		mav.addObject("title", "입금");
		mav.addObject("authMemberInfo", authMemberInfo);
		return mav;
	}
	@RequestMapping(value="/ipgumList")
	public ModelAndView ipgumList(){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> depList = commonService.getDepInfoList(authMemberInfo);
		for (Map<String, Object> map : depList) {
			Set<String> keySet = map.keySet();
			for (String string : keySet) {
				System.out.println(string +" : " + map.get(string));
			}
		}
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ipgum/ipgumList");
		mav.addObject("title", "입금내역");
		return mav;
	}
}
