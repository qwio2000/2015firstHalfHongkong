package com.jeiglobal.hk.controller.fa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jeiglobal.hk.domain.study.RsOmrPrint20Gicho;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab11Left;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab11Right;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab12;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab4;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Program;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Schedule;
import com.jeiglobal.hk.domain.study.RsOmrPrint20StartYM;
import com.jeiglobal.hk.service.IPPRService;

@Controller
public class IPPRController {

	@Autowired
	private IPPRService ipprService;

	// AuthMemberInfo authMemberInfo = (AuthMemberInfo)
	// SecurityContextHolder.getContext().getAuthentication().getPrincipal();

	/*
	 * @RequestMapping(value="/memberKwamokInfo") public ModelAndView
	 * kwamokInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest
	 * request){ AuthMemberInfo authMemberInfo = (AuthMemberInfo)
	 * SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	 * memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
	 * List<MemberKwamokInfo> mki =
	 * memberInfoService.getMemberKwamokInfo(memberDetailInfo, request); String
	 * url = request.getRequestURI(); ModelAndView mav = new ModelAndView();
	 * mav.addObject("popTitle", "회원정보"); mav.addObject("url", url);
	 * mav.addObject("memberKwamokInfo", mki); mav.addObject("memberDetailInfo",
	 * memberDetailInfo); mav.setViewName("/memberCard/memberKwamokInfo");
	 * return mav; }
	 */

	// @RequestMapping(value = "/study/ippr20EM")
	// public ModelAndView ippr20EM(RqIPPR infoIPPR) {
	//
	// RsOmrPrint20Gicho rsOmrPrint20Gicho =
	// ipprService.getOmrPrint20Gicho(infoIPPR);
	// List<RsOmrPrint20Odab11Left> rsOmrPrint20Odab11Left =
	// ipprService.getOmrPrint20Odab11Left(infoIPPR);
	// String omrGrd = rsOmrPrint20Gicho.getOmrGrd();
	// String omrKind = rsOmrPrint20Gicho.getOmrKind();
	//
	// List<RsOmrPrint20Odab11Right> rsOmrPrint20Odab11Right =
	// ipprService.getOmrPrint20Odab11Right(infoIPPR);
	//
	// List<RsOmrPrint20Odab12> rsOmrPrint20Odab12 =
	// ipprService.getOmrPrint20Odab12(infoIPPR, omrGrd, omrKind);
	//
	// ModelAndView mav = new ModelAndView();
	// mav.addObject("rsOmrPrint20Gicho", rsOmrPrint20Gicho);
	// mav.addObject("rsOmrPrint20Odab11Left", rsOmrPrint20Odab11Left);
	// mav.addObject("rsOmrPrint20Odab11Right", rsOmrPrint20Odab11Right);
	// mav.addObject("rsOmrPrint20Odab12", rsOmrPrint20Odab12);
	// mav.setViewName("/study/ippr20EM");
	// // mav.addObject("hkey", omrPrint20Gicho.getHkey());
	// return mav;
	// }

	@RequestMapping(value = "/study/ippr20EM")
	public String ippr20EM(Model model,
			@RequestParam(value = "jisaCD", defaultValue = "") String jisaCD,
			@RequestParam(value = "omrDate", defaultValue = "") String omrDate,
			@RequestParam(value = "hkey", defaultValue = "") String hkey,
			@RequestParam(value = "kwamok", defaultValue = "") String kwamok,
			@RequestParam(value = "omrPath", defaultValue = "") String omrPath,
			@RequestParam(value = "langCD", defaultValue = "") String langCD) 
	{
		RsOmrPrint20Gicho rsOmrPrint20Gicho = ipprService.getOmrPrint20Gicho(jisaCD, omrDate, hkey, kwamok, omrPath, langCD);
		String omrGrd = rsOmrPrint20Gicho.getOmrGrd();
		String omrKind = rsOmrPrint20Gicho.getOmrKind();
		
		List<RsOmrPrint20Odab11Left> rsOmrPrint20Odab11Left = ipprService.getOmrPrint20Odab11Left(jisaCD, omrDate, hkey, kwamok, omrPath, langCD);

		List<RsOmrPrint20Odab11Right> rsOmrPrint20Odab11Right = ipprService.getOmrPrint20Odab11Right(jisaCD, omrDate, hkey, kwamok, omrPath, langCD);

		List<RsOmrPrint20Odab12> rsOmrPrint20Odab12 = ipprService.getOmrPrint20Odab12(jisaCD, omrDate, hkey, kwamok, omrGrd, omrKind, omrPath, langCD);
		
		List<RsOmrPrint20Odab4> rsOmrPrint20Odab4 = ipprService.getOmrPrint20Odab4(jisaCD, omrDate, hkey, kwamok, omrPath, langCD);
		
		RsOmrPrint20StartYM rsOmrPrint20StartYM = ipprService.getOmrPrint20StartYM(jisaCD, omrDate, hkey, kwamok, omrPath, langCD);
		
		String rWeek1 = "1";
		String rWeek2 = "2";
		String rWeek3 = "3";
		String rWeek4 = "4";
		String rWeek5 = "5";
		
		List<RsOmrPrint20Program> rsOmrPrint20Program1 = ipprService.getOmrPrint20Program(jisaCD, omrDate, hkey, kwamok, rWeek1, omrPath, langCD);
		List<RsOmrPrint20Program> rsOmrPrint20Program2 = ipprService.getOmrPrint20Program(jisaCD, omrDate, hkey, kwamok, rWeek2, omrPath, langCD);
		List<RsOmrPrint20Program> rsOmrPrint20Program3 = ipprService.getOmrPrint20Program(jisaCD, omrDate, hkey, kwamok, rWeek3, omrPath, langCD);
		List<RsOmrPrint20Program> rsOmrPrint20Program4 = ipprService.getOmrPrint20Program(jisaCD, omrDate, hkey, kwamok, rWeek4, omrPath, langCD);
		List<RsOmrPrint20Program> rsOmrPrint20Program5 = ipprService.getOmrPrint20Program(jisaCD, omrDate, hkey, kwamok, rWeek5, omrPath, langCD);
		
		List<RsOmrPrint20Schedule> rsOmrPrint20Schedule = ipprService.getOmrPrint20Schedule(jisaCD, omrDate, hkey, kwamok, omrPath, langCD);

		model.addAttribute("rsGicho", rsOmrPrint20Gicho);
		model.addAttribute("rsOdab11Left", rsOmrPrint20Odab11Left);
		model.addAttribute("rsOdab11Right", rsOmrPrint20Odab11Right);
		model.addAttribute("rsOdab12", rsOmrPrint20Odab12);
		model.addAttribute("rsOdab4", rsOmrPrint20Odab4);
		
		model.addAttribute("rsStartYM", rsOmrPrint20StartYM);
		model.addAttribute("rsProgram1", rsOmrPrint20Program1);
		model.addAttribute("rsProgram2", rsOmrPrint20Program2);
		model.addAttribute("rsProgram3", rsOmrPrint20Program3);
		model.addAttribute("rsProgram4", rsOmrPrint20Program4);
		model.addAttribute("rsProgram5", rsOmrPrint20Program5);
		
		model.addAttribute("rsSchedule", rsOmrPrint20Schedule);
		// mav.addObject("hkey", omrPrint20Gicho.getHkey());
		return "study/ippr20EM";
	}
}
