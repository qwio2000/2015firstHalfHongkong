package com.jeiglobal.hk.controller.fa;

import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.context.*;
import org.springframework.stereotype.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.*;

import com.jeiglobal.hk.domain.common.*;
import com.jeiglobal.hk.domain.manageInfo.*;
import com.jeiglobal.hk.service.*;

@Controller
public class ManageInfoController {
	
	@Autowired
	public ManageInfoService manageInfoService;
	
	@Autowired
	public CommonService commonService;

	@RequestMapping(value="/manageInfo")
	public ModelAndView memberSearch(){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> headerScript = new ArrayList<>();
		headerScript.add("manageInfo");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/manageInfo/foreignMemberSearch");
		mav.addObject("headerScript",headerScript);
		mav.addObject("title", "학적관리");
		mav.addObject("authMemberInfo", authMemberInfo);
		return mav;
	}
	@RequestMapping(value="/manageInfo/manageInfo.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> memberSearchJson(String type, String searchWord, String birthDay, String check,
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberSearchInfo> memberSearchInfoList = manageInfoService.getMemberSearchInfo(type, searchWord, birthDay, check, loginLang, authMemberInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("memberSearchInfoList", memberSearchInfoList);
		return map;
	}
	@RequestMapping(value="/manageInfo/korMemberSearch")
	public ModelAndView korMemberSearch(){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> headerScript = new ArrayList<>();
		headerScript.add("manageInfo");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/manageInfo/koreaMemberSearch");
		mav.addObject("title", "학적관리");
		mav.addObject("headerScript",headerScript);
		mav.addObject("authMemberInfo", authMemberInfo);
		return mav;
	}
	@RequestMapping(value="/manageInfo/korMemberSearch.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> korMemberSearchJson(String type, String searchWord, String birthDay, String state){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<KoreaMemberInfo> korMemberSearchInfoList = manageInfoService.getKorMemberSearchInfo(type, searchWord, birthDay, state, authMemberInfo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("korMemberSearchInfoList", korMemberSearchInfoList);
		return map;
	}
	@RequestMapping(value="/emptyHakjuk")
	public ModelAndView emptyHakjuk(){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> classList = commonService.getClassList(authMemberInfo);
		List<String> headerScript = new ArrayList<>();
		headerScript.add("manageInfo");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/manageInfo/emptyHakjuk");
		mav.addObject("title", "학적관리");
		mav.addObject("classList",classList);
		mav.addObject("headerScript",headerScript);
		mav.addObject("authMemberInfo", authMemberInfo);
		return mav;
	}
	@RequestMapping(value="/huheiList")
	public ModelAndView huheiList(@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> classList = commonService.getClassList(authMemberInfo);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		List<String> headerScript = new ArrayList<>();
		headerScript.add("manageInfo");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/manageInfo/huheiList");
		mav.addObject("title", "학적관리");
		mav.addObject("classList",classList);
		mav.addObject("kwamokList",kwamokList);
		mav.addObject("headerScript",headerScript);
		mav.addObject("authMemberInfo", authMemberInfo);
		return mav;
	}
	@RequestMapping(value="/manageInfo/huheiList.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> huheiListJson(String empKey, String kwamok, String startBirthDate, String endBirthDate,
			String hu_skey, String startHuheiDate, String endHuheiDate,@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<HuheiMemberInfo> huheiMemberList = manageInfoService.getHuheiMemberList(empKey, kwamok, startBirthDate, endBirthDate, hu_skey, startHuheiDate, endHuheiDate, authMemberInfo,loginLang);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("huheiMemberList", huheiMemberList);
		return map;
	}
}