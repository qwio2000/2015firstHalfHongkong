package com.jeiglobal.hk.controller.fa;

import java.io.*;
import java.text.*;
import java.util.*;

import javax.servlet.http.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
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
	public ModelAndView memberInfo(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, loginLang);
		List<DtlCD> dtlCD = memberInfoService.getDtlCode(memberDetailInfo.getJisa());
		ModelAndView mav = new ModelAndView("/memberCard/memberInfo");
		mav.addObject("title", "회원정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("dtlCD", dtlCD);
		return mav;
	}
	@RequestMapping(value="/memberKwamokInfo")
	public ModelAndView kwamokInfo(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		List<MemberKwamokInfo> mki = memberInfoService.getMemberKwamokInfo(memberDetailInfo, loginLang);
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "과목정보");
		mav.addObject("popTitle", "회원정보");
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
	public ModelAndView ipheiInfo(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberIpheiInfo> ipheiList = memberInfoService.getMemberIpheiInfo(memberDetailInfo, null, authMemberInfo, loginLang);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberIpheiInfo");
		mav.addObject("title", "입복회정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("ipheiList", ipheiList);
		return mav;
	}
	@RequestMapping(value="/memberIpheiInfo.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> ipheiInfoSearch(MemberDetailInfo memberDetailInfo, String searchKwamok,
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberIpheiInfo> ipheiList = memberInfoService.getMemberIpheiInfo(memberDetailInfo,searchKwamok, authMemberInfo, loginLang);
		Map<String, Object> map = new HashMap<>();
		map.put("searchKwamok", searchKwamok);
		map.put("ipheiList", ipheiList);
		return map;
	}
	
	@RequestMapping(value="/memberHuheiInfo")
	public ModelAndView huheiInfo(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberHuheiInfo> huheiList = memberInfoService.getMemberHuheiInfo(memberDetailInfo, null, authMemberInfo, loginLang);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberHuheiInfo");
		mav.addObject("title", "퇴회정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("huheiList", huheiList);
		return mav;
	}
	@RequestMapping(value="/memberHuheiInfo.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> huheiInfoSearch(MemberDetailInfo memberDetailInfo, String searchKwamok, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberHuheiInfo> huheiList = memberInfoService.getMemberHuheiInfo(memberDetailInfo, searchKwamok, authMemberInfo, loginLang);
		Map<String, Object> map = new HashMap<>();
		map.put("searchKwamok", searchKwamok);
		map.put("huheiList", huheiList);
		return map;
	}
	@RequestMapping(value="/memberIpgumInfo")
	public ModelAndView ipgumInfo(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberIpgumInfo> ipgumList = memberInfoService.getMemberIpgumInfo(memberDetailInfo,null,authMemberInfo,loginLang);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberIpgumInfo");
		mav.addObject("title", "입금정보");
		mav.addObject("popTitle", "회원정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("ipgumList", ipgumList);
		return mav;
	}
	@RequestMapping(value="/memberIpgumInfo.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> ipgumInfoSearch(MemberDetailInfo memberDetailInfo, String searchKwamok,
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberIpgumInfo> ipgumList = memberInfoService.getMemberIpgumInfo(memberDetailInfo,searchKwamok,authMemberInfo,loginLang);
		Map<String, Object> map = new HashMap<>();
		map.put("searchKwamok", searchKwamok);
		map.put("ipgumList", ipgumList);
		return map;
	}
	@RequestMapping(value="/memberJindoInfo")
	public ModelAndView jindoInfo(MemberDetailInfo memberDetailInfo, String searchKwamok,
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<MemberJindoInfo> jindoList = memberInfoService.getMemberJindoInfo(memberDetailInfo,searchKwamok,authMemberInfo, loginLang);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		SimpleDateFormat sdf = new SimpleDateFormat("MM");
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberJindoInfo");
		mav.addObject("title", "진도정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("popTitle", "회원정보");
		mav.addObject("searchKwamok", searchKwamok);
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("currentMM", sdf.format(new Date()));
		mav.addObject("jindoList", jindoList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		return mav;
	}

	@RequestMapping(value="/jindoSearch")
	public ModelAndView jindoSearch(MemberDetailInfo memberDetailInfo,String searchYY, String searchMM, String searchKwamok, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberJindoSearch mjs = memberInfoService.getMemberInfo(memberDetailInfo, searchKwamok, authMemberInfo, loginLang);
		Map<String, Object> map = new HashMap<>();
		map = memberInfoService.getMemberJindoSearch(memberDetailInfo,searchYY,searchMM, searchKwamok,authMemberInfo);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
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
	public ModelAndView memberOmrInfo(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, loginLang);
		OmrInfo omrInfo = memberInfoService.getMemberJindanCheck(memberDetailInfo);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberOmrInfo");
		mav.addObject("title", "진단처방");
		mav.addObject("popTitle", "진단처방");
		mav.addObject("headerScript", headerScript);
		mav.addObject("omrInfo", omrInfo);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		return mav;
	}
	@RequestMapping(value="/memberOmrOdabInput")
	public ModelAndView memberOmrOdabInput(MemberDetailInfo memberDetailInfo, String dung, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, loginLang);
		System.out.println(memberDetailInfo);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "오답입력");
		mav.addObject("popTitle", "진단처방");
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
	public ModelAndView memberOmrView(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		Map<String, Object> map = memberInfoService.getOmrGichoList(memberDetailInfo,null,null,authMemberInfo, loginLang);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberOmrView");
		mav.addObject("title", "진단처방검색");
		mav.addObject("popTitle", "진단처방");
		mav.addObject("headerScript", headerScript);
		mav.addObject("lang", loginLang);
		mav.addObject("searchKwamok", map.get("kwamok"));
		mav.addObject("searchYY", map.get("searchYY"));
		mav.addObject("omrGichoList", map.get("omrGichoList"));
		mav.addObject("kwamokList", kwamokList);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		return mav;
	}
	@RequestMapping(value="/memberOmrView.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String, Object> memberOmrViewSearch(MemberDetailInfo memberDetailInfo, String searchYY, String searchKwamok, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Map<String, Object> map = memberInfoService.getOmrGichoList(memberDetailInfo,searchYY,searchKwamok,authMemberInfo, loginLang);
		map.put("searchKwamok", searchKwamok);
		return map;
	}
	@RequestMapping(value="/memberOmrSave")
	public ModelAndView memberOmrSave(MemberDetailInfo memberDetailInfo, String dung, String ErrLst, String ErrTot,
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		ModelAndView mav = new ModelAndView();
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, loginLang);
		String check = memberInfoService.getMemberOmrCheck(memberDetailInfo);
		System.out.println(check);
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
	public ModelAndView memberHuhei(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, loginLang);
		String kwamokName = memberInfoService.getKwamokName(memberDetailInfo.getJisa(), memberDetailInfo.getKwamok(), loginLang);
		List<String> huheiDayList = commonService.getAvailableDateList(authMemberInfo); 
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		List<DtlCD> huheiSayuList = memberInfoService.getHuheiSayuList(authMemberInfo);
		ModelAndView mav = new ModelAndView();
		mav.addObject("title", "퇴회정보");
		mav.addObject("headerScript", headerScript);
		mav.addObject("popTitle", "퇴회입력");
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("kwamokName", kwamokName);
		mav.addObject("huheiDayList", huheiDayList);
		mav.addObject("huheiSayuList", huheiSayuList);
		mav.setViewName("/memberCard/memberHuhei");
		return mav;
	}
	
	@RequestMapping(value="/memberHuhei.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public String memberHuheiSave(MemberDetailInfo memberDetailInfo, String huheiDay){
		String todayHuheicheck = memberInfoService.getTodayHuheiCheck(memberDetailInfo, huheiDay);
		String huheiAgreeState = memberInfoService.getIsHuheiAgreeState(memberDetailInfo);
		return ("false".equals(todayHuheicheck)?"todayHuhei":("false".equals(huheiAgreeState)?"huheiAgree":""));
	}
	
	@RequestMapping(value="/memberHuheiSave")
	public ModelAndView memberHuheiSave(MemberDetailInfo memberDetailInfo, String huGubun, 
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
	@RequestMapping(value="/memberJindoUpdateInfo")
	public ModelAndView memberJindoUpdateInfo(MemberDetailInfo memberDetailInfo, 
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		memberDetailInfo.setJisa(authMemberInfo.getJisaCD());
		System.out.println(memberDetailInfo);
		memberDetailInfo = memberInfoService.getMemberDetailInfo(memberDetailInfo, loginLang);
		List<String> dtlCDList = memberInfoService.getJindoUpdateDtlCDList(authMemberInfo, loginLang);
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberJindoUpdateInfo");
		mav.addObject("title", "진도조정");
		mav.addObject("popTitle", "진도조정");
		mav.addObject("dtlCDList", dtlCDList);
		mav.addObject("headerScript", headerScript);
		mav.addObject("memberDetailInfo", memberDetailInfo);
		return mav;
	}
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/memberJindoUpdateInput")
	public ModelAndView memberJindoUpdateInput(MemberDetailInfo memberDetailInfo, String cngOpt, String updateYM,
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang) throws ParseException{
		List<String> headerScript = new ArrayList<String>();
		headerScript.add("memberCard");
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView();
		mav.addObject("url", "/memberCard/memberJindoUpdateInfo?mKey="+memberDetailInfo.getmKey()+"&sKey="+memberDetailInfo.getsKey()+"&kwamok="+memberDetailInfo.getKwamok());
		mav.setViewName("alertAndRedirect");
		MemberInfoCheck mic = memberInfoService.getMemberInfoCheck(authMemberInfo, memberDetailInfo, loginLang);
		if(mic == null){
			mav.addObject("message", "입력조건에 대한 회원이 없습니다. 확인 후 다시 하십시오.");
			return mav;
		}
		String cngGubun = "1";
		System.out.println("cngGubun : "+cngGubun);
		System.out.println("cngOpt : "+cngOpt);
		JindoAdjustCheck jac = memberInfoService.getJindoAdjustCheck(memberDetailInfo, authMemberInfo, cngGubun, cngOpt, loginLang);
		if(jac != null){
			if(cngOpt.equals("1") && jac.getDayCnt().equals("1")){
				mav.addObject("message", "금일 "+jac.getCngOptNM()+"을 이미 처리 하였습니다.");
			}else if(cngOpt.equals("1") && Integer.parseInt(jac.getCngCnt())>2){
				mav.addObject("message", jac.getCngOptNM()+"은 월 2회까지만 가능합니다.");
			}else if(cngOpt.equals("4") && Integer.parseInt(jac.getCngCnt())>=1){
				mav.addObject("message", jac.getCngOptNM()+"은 월 1회까지만 가능합니다.");
			}
			return mav;
		}
		if(!mic.getStateCD().equals("1")){
			mav.addObject("message", "현재 유지회원이 아닙니다. 유지 회원에 한 해 조정이 가능합니다.");
			return mav;
		}
		String cngYMD = memberInfoService.getChangeYoilYMD(memberDetailInfo);
		String chkDate = mic.getBokheiYMD().length()>1?mic.getBokheiYMD():mic.getIpheiYMD();
		Calendar threeWeeksAgo = Calendar.getInstance();
		threeWeeksAgo.add(Calendar.DATE, -21);
		Calendar twoWeeksAgo = Calendar.getInstance();
		twoWeeksAgo.add(Calendar.DATE, -14);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if("4".equals(cngOpt)){
			if(!"".equals(cngYMD)){
				if(!(format.parse(chkDate).compareTo(threeWeeksAgo.getTime())>=0 || 
						format.parse(cngYMD).compareTo(twoWeeksAgo.getTime())>=0)){
					mav.addObject("message", "입복회일로부터 3주 이내이거나 관리요일 변경 후 2주이내 당김이 가능합니다.");
					return mav;
				}
			}else{
				if(!(format.parse(chkDate).compareTo(threeWeeksAgo.getTime())>=0)){
					mav.addObject("message", "입복회일로부터 3주 이내이거나 관리요일 변경 후 2주이내 당김이 가능합니다.");
					return mav;
				}
			}
		}
		Map<String, Object> map = new HashMap<>();
		map = memberInfoService.getJindoUpdateInputInfo(memberDetailInfo, updateYM, authMemberInfo, cngOpt);
		mav.addObject("startYYYY", map.get("startYYYY"));
		mav.addObject("startMM", map.get("startMM"));
		mav.addObject("bsArray", map.get("bsArray"));
		mav.addObject("bkArray", map.get("bkArray"));
		mav.addObject("chkArray", map.get("chkArray"));
		map = memberInfoService.getSetList(authMemberInfo, memberDetailInfo, map.get("sSet"));
		
		mav.addObject("set1",(List<JindoUpdateSet>)map.get("set1"));
		mav.addObject("set2",(List<JindoUpdateSet>)map.get("set2"));
		mav.addObject("set3",(List<JindoUpdateSet>)map.get("set3"));
		mav.addObject("set1dung",(String)map.get("set1dung"));
		mav.addObject("set2dung",(String)map.get("set2dung"));
		mav.addObject("set3dung",(String)map.get("set3dung"));
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("cngOpt", cngOpt);
		mav.addObject("memberInfoCheck", mic);
		mav.addObject("headerScript",headerScript);
		mav.setViewName("/memberCard/memberJindoUpdateInput");
		return mav;
	}
	@RequestMapping(value="/memberJindoUpdate")
	public ModelAndView memberJindoUpdate(JindoUpdateInfo jindoUpdateInfo){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		ModelAndView mav = new ModelAndView();
		mav.setViewName("alertAndRedirect");
		if ("B".equals(jindoUpdateInfo.getCngSayu())) {
			jindoUpdateInfo.setSets1(jindoUpdateInfo.getSetb1());
			jindoUpdateInfo.setSets2(jindoUpdateInfo.getSetb2());
			jindoUpdateInfo.setSets3("");
			jindoUpdateInfo.setSets4("");
			jindoUpdateInfo.setSets5("");
			int setCount = memberInfoService.getBokSetCount(authMemberInfo,jindoUpdateInfo);
			if ("KC".equals(jindoUpdateInfo.getKwamok())) {
				if (setCount > 45) {
					mav.addObject("message", "복습 세트수가 45세트를 넘었습니다.");
					return mav;
				}
			}else{
				if (setCount > 15) {
					mav.addObject("message", "복습 세트수가 15세트를 넘었습니다.");
					return mav;
				}
			}
		}
		memberInfoService.updateJindoInfo(jindoUpdateInfo, authMemberInfo);
		String fstMsg = ("1".equals(jindoUpdateInfo.getCngOpt())?"복습":"당김");
		mav.addObject("message", jindoUpdateInfo.getmKey()+"님의 "+fstMsg+" 조정이 끝났습니다.");
		mav.addObject("url", "/memberCard/memberJindoUpdateView?mKey="+jindoUpdateInfo.getmKey()+"&kwamok="+jindoUpdateInfo.getKwamok());
		mav.setViewName("alertAndRedirect");
		return mav;
	}
	@RequestMapping(value="/memberJindoUpdateView")
	public ModelAndView memberJindoUpdateView(MemberDetailInfo memberDetailInfo,
			@CookieValue(value="LoginLang",defaultValue="E") String loginLang){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<Map<String, Object>> classList = commonService.getClassList(authMemberInfo);
		List<String> dtlCDList = memberInfoService.getJindoUpdateDtlCDList(authMemberInfo, loginLang);
		List<String> kwamokList = commonService.getKwamokList(loginLang, authMemberInfo);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/memberCard/memberJindoUpdateView");
		mav.addObject("memberDetailInfo", memberDetailInfo);
		mav.addObject("classList",classList);
		mav.addObject("dtlCDList",dtlCDList);
		mav.addObject("kwamokList",kwamokList);
		return mav;
	}
}
