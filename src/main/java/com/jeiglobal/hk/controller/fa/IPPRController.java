package com.jeiglobal.hk.controller.fa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.jeiglobal.hk.domain.studyProgress.IPPRInfo;
import com.jeiglobal.hk.domain.studyProgress.OmrPrint20Gicho;
import com.jeiglobal.hk.service.IPPRService;

@Controller
public class IPPRController {
	
	@Autowired
	private IPPRService ipprService;
	
//	AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
/*	@RequestMapping(value="/memberKwamokInfo")
	public ModelAndView kwamokInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		List<MemberKwamokInfo> mki = memberInfoService.getMemberKwamokInfo(memberDetailInfo, request);
		String url = request.getRequestURI();
		ModelAndView mav = new ModelAndView();
		mav.addObject("popTitle", "회원정보");
		mav.addObject("url", url);
		mav.addObject("memberKwamokInfo", mki);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.setViewName("/memberCard/memberKwamokInfo");
		return mav;
	}*/
	
	@RequestMapping(value="/ippr20EM")
	public ModelAndView ippr20EM(IPPRInfo ipprInfo){
		System.out.println("zz");
		OmrPrint20Gicho omrPrint20Gicho = ipprService.getOmrPrint20Gicho(ipprInfo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("ippr20EM");
		mav.addObject("omrPrint20Gicho", omrPrint20Gicho);
		mav.addObject("hkey", omrPrint20Gicho.getHkey());
		return mav;
	}	
}
