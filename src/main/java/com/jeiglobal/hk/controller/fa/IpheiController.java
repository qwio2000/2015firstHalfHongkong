package com.jeiglobal.hk.controller.fa;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.service.CommonService;


@Controller
@RequestMapping(value="/iphei")
public class IpheiController {
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping
	public String index(HttpServletRequest requst,Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) authentication.getPrincipal();
		
		List<String> availableDateList = commonService.getAvailableDateList(authMemberInfo);
		List<Map<String,Object>> depInfoList = commonService.getDepInfoList(authMemberInfo);
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		model.addAttribute("title","입복회페이지");
		model.addAttribute("availableDateList",availableDateList);
		model.addAttribute("nowDate",sdf.format(cal.getTime()));
		model.addAttribute("depInfoList",depInfoList);
		
		return "common/iphei";
	}
}
