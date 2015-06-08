package com.jeiglobal.hk.controller.fa;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.jeiglobal.hk.domain.common.*;
import com.jeiglobal.hk.domain.ipgum.*;
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
	public ModelAndView ipgumList(@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> classList = commonService.getClassList(authMemberInfo);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		List<String> headerScript = new ArrayList<>();
		headerScript.add("ipgum");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/ipgum/ipgumList");
		mav.addObject("title", "입금내역");
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("classList", classList);
		mav.addObject("headerScript", headerScript);
		return mav;
	}
	@RequestMapping(value="/ipgumList.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> ipgumListJson(String empKey, String kwamok, String existCD, String mKey, 
			String chkVal, String startDay, String endDay, @CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		TotalHeibi totHeibi = ipgumService.getTotHeibi(empKey, kwamok, mKey, existCD, chkVal, startDay, endDay, authMemberInfo);
		List<IpgumInfo> ipgumInfoList = ipgumService.getIpgumList(empKey, kwamok, mKey, existCD, chkVal, startDay, endDay, authMemberInfo, loginLang);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("totHeibi", totHeibi);
		map.put("ipgumInfoList", ipgumInfoList);
		return map;
	}
}
