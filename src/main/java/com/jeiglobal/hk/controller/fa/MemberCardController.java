package com.jeiglobal.hk.controller.fa;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.domain.member.*;
import com.jeiglobal.hk.service.CommonService;
import com.jeiglobal.hk.service.MemberInfoService;

@Controller
@RequestMapping(value="/memberCard")
public class MemberCardController {
	
	@Autowired
	private MemberInfoService memberInfoService;
	
	@Autowired
	private CommonService commonService;
	
	@RequestMapping
	public ModelAndView memberCard(){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/index");
		mav.addObject("title", "관리카드");
		mav.addObject("headerScript", headerScript);
		mav.addObject("authMemberInfo", authMemberInfo);
		return mav;
	}
	@RequestMapping(value="/memberInfo")
	public ModelAndView memberInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, request);
		List<DtlCD> dtlCD = memberInfoService.getDtlCode(memberDetailInfo.getJisa());
		String url = request.getRequestURI();
		ModelAndView mav = new ModelAndView("/memberCard/memberInfo");
		mav.addObject("title", "회원정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("url", url);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("dtlCD", dtlCD);
		return mav;
	}
	@RequestMapping(value="/memberKwamokInfo")
	public ModelAndView kwamokInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		List<MemberKwamokInfo> mki = memberInfoService.getMemberKwamokInfo(memberDetailInfo, request);
		String url = request.getRequestURI();
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "과목정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("url", url);
		mav.addObject("memberKwamokInfo", mki);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.setViewName("/memberCard/memberKwamokInfo");
		return mav;
	}
	@RequestMapping(value="/memberInfoUpdate")
	public ModelAndView memberInfoUpdate(MemberDetailInfo memberDetailInfo,ConcatData cd){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo = memberInfoService.concatData(memberDetailInfo,cd);
		int count = memberInfoService.updateMemberDetailInfo(memberDetailInfo,authMemberInfo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/alertAndRedirect");
		if(count>0){
			mav.addObject("memberDetailInfo", memberDetailInfo);
			mav.addObject("message", count+"건의 정보가 변경되었습니다.");
			mav.addObject("url", "/memberCard/memberInfo?mKey="+memberDetailInfo.getmKey()+"&sKey="+memberDetailInfo.getsKey()+"&kwamok="+memberDetailInfo.getKwamok());
		}else{
			mav.addObject("message", "회원 정보를 변경하는 도중 문제가 발생하였습니다.");
			mav.addObject("url", "/memberCard/memberInfo?mKey="+memberDetailInfo.getmKey()+"&sKey="+memberDetailInfo.getsKey()+"&kwamok="+memberDetailInfo.getKwamok());
		}
		return mav;
	}
	@RequestMapping(value="/memberIpheiInfo")
	public ModelAndView ipheiInfo(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String firstIpheiDate = memberInfoService.getMemberFirstIpheiDate(memberDetailInfo, searchKwamok);
		List<MemberIpheiInfo> ipheiList = memberInfoService.getMemberIpheiInfo(memberDetailInfo,searchKwamok, authMemberInfo, request);
		List<String> kwamokList = memberInfoService.getKwamokList(authMemberInfo.getJisaCD(),authMemberInfo.getDepid1());
		String url = request.getRequestURI();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberIpheiInfo");
		mav.addObject("title", "입복회정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("url", url);
		mav.addObject("headerScript", headerScript);
		mav.addObject("searchKwamok",searchKwamok);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("firstIpheiDate", firstIpheiDate);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("ipheiList", ipheiList);
		return mav;
	}
	@RequestMapping(value="/memberIpheiInfo.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public HashMap<String, Object> ipheiInfoSearch(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberIpheiInfo> ipheiList = memberInfoService.getMemberIpheiInfo(memberDetailInfo,searchKwamok, authMemberInfo, request);
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchKwamok", searchKwamok);
		map.put("ipheiList", ipheiList);
		return map;
	}
	
	@RequestMapping(value="/memberHuheiInfo")
	public ModelAndView huheiInfo(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberHuheiInfo> huheiList = memberInfoService.getMemberHuheiInfo(memberDetailInfo, searchKwamok, authMemberInfo, request);
		List<String> kwamokList = memberInfoService.getKwamokList(authMemberInfo.getJisaCD(),authMemberInfo.getDepid1());
		String url = request.getRequestURI();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberHuheiInfo");
		mav.addObject("title", "퇴회정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("url", url);
		mav.addObject("searchKwamok",searchKwamok);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("huheiList", huheiList);
		return mav;
	}
	@RequestMapping(value="/memberHuheiInfo.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public HashMap<String, Object> huheiInfoSearch(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberHuheiInfo> huheiList = memberInfoService.getMemberHuheiInfo(memberDetailInfo, searchKwamok, authMemberInfo, request);
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchKwamok", searchKwamok);
		map.put("huheiList", huheiList);
		return map;
	}
	@RequestMapping(value="/memberIpgumInfo")
	public ModelAndView ipgumInfo(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberIpgumInfo> ipgumList = memberInfoService.getMemberIpgumInfo(memberDetailInfo,searchKwamok,authMemberInfo,request);
		List<String> kwamokList = memberInfoService.getKwamokList(authMemberInfo.getJisaCD(),authMemberInfo.getDepid1());
		String url = request.getRequestURI();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberIpgumInfo");
		mav.addObject("title", "입금정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("url", url);
		mav.addObject("searchKwamok",searchKwamok);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("ipgumList", ipgumList);
		return mav;
	}
	@RequestMapping(value="/memberIpgumInfo.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public HashMap<String, Object> ipgumInfoSearch(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberIpgumInfo> ipgumList = memberInfoService.getMemberIpgumInfo(memberDetailInfo,searchKwamok,authMemberInfo,request);
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchKwamok", searchKwamok);
		map.put("ipgumList", ipgumList);
		return map;
	}
	@RequestMapping(value="/memberJindoInfo")
	public ModelAndView jindoInfo(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberJindoInfo> jindoList = memberInfoService.getMemberJindoInfo(memberDetailInfo,searchKwamok,authMemberInfo, request);
		List<String> kwamokList = memberInfoService.getKwamokList(authMemberInfo.getJisaCD(),authMemberInfo.getDepid1());
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		String url = request.getRequestURI();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberJindoInfo");
		mav.addObject("title", "진도정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("popTitle", "회원정보");
		mav.addObject("url", url);
		mav.addObject("searchKwamok", searchKwamok);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("currentMM", sdf.format(new Date()));
		mav.addObject("jindoList", jindoList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		return mav;
	}
	@RequestMapping(value="/memberJindoInfo.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public HashMap<String, Object> jindoInfoSearch(MemberDetailInfo memberDetailInfo, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberJindoInfo> jindoList = memberInfoService.getMemberJindoInfo(memberDetailInfo,searchKwamok,authMemberInfo, request);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		HashMap<String, Object> map = new HashMap<>();
		map.put("searchKwamok", searchKwamok);
		map.put("huheiList", jindoList);
		map.put("currentMM", sdf.format(new Date()));
		return map;
	}
	@RequestMapping(value="/jindoSearch")
	public ModelAndView jindoSearch(MemberDetailInfo memberDetailInfo,String searchYY, String searchMM, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberJindoSearch mjs = memberInfoService.getMemberInfo(memberDetailInfo, searchKwamok, authMemberInfo, request);
		HashMap<String, Object> map = new HashMap<>();
		map = memberInfoService.getMemberJindoSearch(memberDetailInfo,searchYY,searchMM, searchKwamok,authMemberInfo);
		List<String> kwamokList = memberInfoService.getKwamokList(authMemberInfo.getJisaCD(),authMemberInfo.getDepid1());
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/jindoSearch");
		mav.addObject("title", "진도검색");
		mav.addObject("searchKwamok", searchKwamok);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("memberJindoSearch", mjs);
		mav.addObject("bsArray", map.get("bsArray"));
		mav.addObject("indArray", map.get("indArray"));
		mav.addObject("startYYYY", map.get("startYYYY"));
		mav.addObject("startMM", map.get("startMM"));
		mav.addObject("searchYY", map.get("searchYY"));
		mav.addObject("searchMM", map.get("searchMM"));
		return mav;
	}
	@RequestMapping(value="/memberOmrInfo")
	public ModelAndView memberOmrInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, request);
		OmrInfo omrInfo = memberInfoService.getMemberJindanCheck(memberDetailInfo);
		String url = request.getRequestURI();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberOmrInfo");
		mav.addObject("title", "진단처방");
		mav.addObject("popTitle", "진단처방");
		mav.addObject("headerScript", headerScript);
		mav.addObject("url", url);
		mav.addObject("omrInfo", omrInfo);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		return mav;
	}
	@RequestMapping(value="/memberOmrOdabInput")
	public ModelAndView memberOmrOdabInput(MemberDetailInfo memberDetailInfo, String dung, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, request);
		String url = request.getRequestURI();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "오답입력");
		mav.addObject("popTitle", "진단처방");
		mav.addObject("url", url);
		mav.addObject("headerScript", headerScript);
		if(dung.compareTo("C")>0){
			List<JungDabInfo> jungDabList = memberInfoService.getJungDabList(memberDetailInfo.getKwamok(), dung, authMemberInfo);
			if(jungDabList==null){
				mav.addObject("message", "진도 처방 테이블에 과목과 등급에 맞는 레코드가 없습니다!");
				mav.addObject("url", "/memberCard/memberOmrInfo");
				mav.setViewName("alertAndRedirect");
				return mav;
			}else{
				mav.addObject("jungDabList", jungDabList);
			}
		}
		int tot = memberInfoService.getTotMunCount(memberDetailInfo.getKwamok(), dung, authMemberInfo);
		if(tot == -1){
			mav.addObject("message", "진도 처방 테이블에 과목과 등급에 맞는 레코드가 없습니다!");
			mav.addObject("url", "/memberCard/memberOmrInfo");
			mav.setViewName("alertAndRedirect");
			return mav;
		}else{
			mav.addObject("tot",tot);
		}
		mav.addObject("dung", dung);
		mav.addObject("isGtC", dung.compareTo("C")>0);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.setViewName("/memberCard/memberOmrInput");
		return mav;
	}
	@RequestMapping(value="/memberOmrView")
	public ModelAndView memberOmrView(MemberDetailInfo memberDetailInfo, String searchYY, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> kwamokList = memberInfoService.getKwamokList(authMemberInfo.getJisaCD(),authMemberInfo.getDepid1());
		HashMap<String, Object> map = memberInfoService.getOmrGichoList(memberDetailInfo,searchYY,searchKwamok,authMemberInfo, request);
		String url = request.getRequestURI();
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberOmrView");
		mav.addObject("title", "진단처방검색");
		mav.addObject("popTitle", "진단처방");
		mav.addObject("headerScript", headerScript);
		mav.addObject("url", url);
		mav.addObject("lang", memberInfoService.getCookieValue(request, "LoginLang"));
		mav.addObject("searchKwamok", map.get("kwamok"));
		mav.addObject("searchYY", map.get("searchYY"));
		mav.addObject("omrGichoList", map.get("omrGichoList"));
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		return mav;
	}
	@RequestMapping(value="/memberOmrView.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public HashMap<String, Object> memberOmrViewSearch(MemberDetailInfo memberDetailInfo, String searchYY, String searchKwamok, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		HashMap<String, Object> map = memberInfoService.getOmrGichoList(memberDetailInfo,searchYY,searchKwamok,authMemberInfo, request);
		map.put("searchKwamok", searchKwamok);
		return map;
	}
	@RequestMapping(value="/memberOmrSave")
	public ModelAndView memberOmrSave(MemberDetailInfo memberDetailInfo, String dung, String ErrLst, String ErrTot,
			HttpServletRequest request){
		ModelAndView mav = new ModelAndView();
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, request);
		String check = memberInfoService.getMemberOmrCheck(memberDetailInfo);
		if(check != null && !check.isEmpty()){
			if(check.equals("1")){
				mav.addObject("message", "회원의 이전 처방이 분석이 되지 않았습니다.");
				mav.addObject("url", "/memberCard/memberOmrInfo?mKey="+memberDetailInfo.getmKey()+"&sKey="+memberDetailInfo.getsKey()+"&kwamok="+memberDetailInfo.getKwamok());
				mav.setViewName("alertAndRedirect");
				return mav;
			}else{
				mav.addObject("message", "당일 처방이 존재 합니다.");
				mav.addObject("url", "/memberCard/memberOmrInfo?mKey="+memberDetailInfo.getmKey()+"&sKey="+memberDetailInfo.getsKey()+"&kwamok="+memberDetailInfo.getKwamok());
				mav.setViewName("alertAndRedirect");
				return mav;
			}
		}
		memberInfoService.insertMemberOmrGicho(memberDetailInfo, dung, authMemberInfo);
		if (Integer.parseInt(ErrTot)>0) {
			memberInfoService.insertOdabInfo(memberDetailInfo, dung, ErrLst, Integer.parseInt(ErrTot),authMemberInfo);
		}
		memberInfoService.omrBan(authMemberInfo, memberDetailInfo, dung);
		mav.addObject("message", memberDetailInfo.getmKey()+"회원의 분석이 완료 되었습니다.");
		mav.addObject("url", "/memberCard/memberOmrView?mKey="+memberDetailInfo.getmKey()+"&sKey="+memberDetailInfo.getsKey()+"&kwamok="+memberDetailInfo.getKwamok());
		mav.setViewName("alertAndRedirect");
		return mav;
	}
	@RequestMapping(value="/memberHuhei")
	public ModelAndView memberHuhei(MemberDetailInfo memberDetailInfo, HttpServletRequest request){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, request);
		String kwamokName = memberInfoService.getKwamokName(memberDetailInfo.getJisa(), memberDetailInfo.getKwamok(), request);
		List<String> huheiDayList = commonService.getAvailableDateList(authMemberInfo); 
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		List<DtlCD> huheiSayuList = memberInfoService.getHuheiSayuList(authMemberInfo);
		String url = request.getRequestURI();
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "퇴회정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("popTitle", "퇴회입력");
		mav.addObject("url", url);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("kwamokName", kwamokName);
		mav.addObject("huheiDayList", huheiDayList);
		mav.addObject("huheiSayuList", huheiSayuList);
		mav.setViewName("/memberCard/memberHuhei");
		return mav;
	}
	
	@RequestMapping(value="/memberHuhei.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String memberHuheiSave(MemberDetailInfo memberDetailInfo, HttpServletRequest request, String huheiDay){
		//AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String todayHuheicheck = memberInfoService.getTodayHuheiCheck(memberDetailInfo, huheiDay);
		String huheiAgreeState = memberInfoService.getIsHuheiAgreeState(memberDetailInfo);
		return ("false".equals(todayHuheicheck)?"todayHuhei":("false".equals(huheiAgreeState)?"huheiAgree":""));
	}
	
	@RequestMapping(value="/memberHuheiSave")
	public ModelAndView memberHuheiSave(MemberDetailInfo memberDetailInfo, HttpServletRequest request, String huGubun, 
			String huSayu, String huheiDay, HttpServletResponse response) throws IOException{
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String check = memberInfoService.insertMemberHuheiInfo(memberDetailInfo, authMemberInfo, huGubun, huSayu, huheiDay);
		ModelAndView mav = new ModelAndView();
		if("false".equals(check)){
			mav.addObject("message", "퇴회 처리가 정상적으로 완료되지 않았습니다.");
			mav.addObject("url", "/memberCard/memberHuhei?mKey="+memberDetailInfo.getmKey()+"&sKey="+memberDetailInfo.getsKey()+"&kwamok="+memberDetailInfo.getKwamok());
			mav.setViewName("alertAndRedirect");
			return mav;
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter writer = response.getWriter();
		writer.println("<script>");
		writer.println("alert('"+memberDetailInfo.getmKey()+"님의 퇴회처리가 완료 되었습니다.');");
		writer.println("self:close();");
		writer.println("</script>");
		writer.close();
		return null;
	}
}
