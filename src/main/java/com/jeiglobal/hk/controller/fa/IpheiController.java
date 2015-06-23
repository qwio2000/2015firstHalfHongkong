package com.jeiglobal.hk.controller.fa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.base.Strings;
import com.jeiglobal.hk.domain.common.AuthMemberInfo;
import com.jeiglobal.hk.domain.common.Comcode;
import com.jeiglobal.hk.domain.common.MemMst;
import com.jeiglobal.hk.domain.common.OmrGichoMujin;
import com.jeiglobal.hk.service.CommonService;
import com.jeiglobal.hk.service.IpheiService;
import com.jeiglobal.hk.utils.JeiCommonUtils;

@Controller
@RequestMapping(value="/iphei")
public class IpheiController {
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private IpheiService ipheiService;
	
	@RequestMapping
	public String index(Model model,@CookieValue(value="LoginLang",defaultValue="K") String loginLang
			,@RequestParam(value="jisaCD",defaultValue="") String jisaCD
			,@RequestParam(value="depid1",defaultValue="") String depid1
			,@RequestParam(value="mujinDepid",defaultValue="") String mujinDepid
			,@RequestParam(value="ipKind",defaultValue="") String ipKind
			){
		
		AuthMemberInfo authMemberInfo = (AuthMemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String pJisaCD = "";
		String pDepid1 = "";
		
		if(!Strings.isNullOrEmpty(jisaCD)){
			pJisaCD = jisaCD;
			
			if(Strings.isNullOrEmpty(depid1)){
				pDepid1 = mujinDepid;
			}else{
				pDepid1 = depid1;
			}
		}else{
			pJisaCD = authMemberInfo.getJisaCD();
			pDepid1 = authMemberInfo.getDepid1();
		}
		
		
		List<String> availableDateList = commonService.getAvailableDateList(pJisaCD,authMemberInfo.getEmpKeyLvCD());
		List<Map<String,Object>> depInfoList = commonService.getDepInfoList(authMemberInfo.getEmpKeyLvCD(),pJisaCD,pDepid1);
		List<String> kwamokList = commonService.getKwamokList(loginLang,pJisaCD,pDepid1,"",authMemberInfo.getEmpKeyLvCD());
		List<Comcode> ipheiKindList = commonService.selectCodeDtl(authMemberInfo.getJisaCD(),"0060","1",loginLang);
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("iphei");
		
		model.addAttribute("title","입복회페이지");
		model.addAttribute("availableDateList",availableDateList);
		model.addAttribute("depInfoList",depInfoList);
		model.addAttribute("kwamokList",kwamokList);
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("mujinDepid",mujinDepid);
		model.addAttribute("ipKind",ipKind);
		model.addAttribute("depid1",depid1);
		model.addAttribute("ipheiKindList",ipheiKindList);
		
		return "iphei/index";
	}
	
	@RequestMapping(value="/join")
	public String join(Model model,@CookieValue(value="LoginLang",defaultValue="K") String loginLang
			,@RequestParam(value="pIpheiDepid",defaultValue="") String pIpheiDepid
			,@RequestParam(value="pSubj",defaultValue="") String pSubj
			,@RequestParam(value="pIpheiday",defaultValue="") String pIpheiday
			,@RequestParam(value="pIpheiKind",defaultValue="01") String pIpheiKind
			,@RequestParam(value="pJisaCD",defaultValue="") String pJisaCD
			,@RequestParam(value="pMkey",defaultValue="") String pMkey
			,@RequestParam(value="pOmrdate",defaultValue="") String pOmrdate
			,@RequestParam(value="pMujinSubj",defaultValue="") String pMujinSubj
			){
		
		AuthMemberInfo authMemberInfo = (AuthMemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		if(Strings.isNullOrEmpty(pJisaCD)){
			pJisaCD = authMemberInfo.getJisaCD();
		}
		
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("join");
		
		MemMst existsMemInfo = null;
		OmrGichoMujin mujinMemInfo = null;
		
		Map<String,Object> memInfo = new HashMap<>();
		memInfo.put("mFirstName","");
		memInfo.put("mBirthYMD","");
		memInfo.put("mSex","");
		memInfo.put("gradeCD","");
		memInfo.put("mSchool","");
		memInfo.put("mAddr","");
		memInfo.put("mTel1","");
		memInfo.put("mTel2","");
		memInfo.put("mTel3","");
		memInfo.put("gPhone1","");
		memInfo.put("gPhone2","");
		memInfo.put("gPhone3","");
		memInfo.put("ePhone1","");
		memInfo.put("ePhone2","");
		memInfo.put("ePhone3","");
		memInfo.put("gFirstName","");
		memInfo.put("email1","");
		memInfo.put("email2","");
		memInfo.put("gEmail1","");
		memInfo.put("gEmail2","");
		memInfo.put("specialComment","");
		
		//기존,무료회원 정보 가져오기
		if("02".equals(pIpheiKind)){
			existsMemInfo = ipheiService.findMemMstById(pMkey);
			
			memInfo.put("mFirstName",existsMemInfo.getmFstName());
			memInfo.put("mBirthYMD",existsMemInfo.getBirthYMD());
			memInfo.put("gradeCD",existsMemInfo.getGradeCD());
			memInfo.put("mSex",existsMemInfo.getSexCD());
			memInfo.put("mSchool",existsMemInfo.getSchoolName());
			memInfo.put("mAddr",existsMemInfo.getAddr());
			
			String tels[] = null;
			if(!Strings.isNullOrEmpty(existsMemInfo.getTel()) && existsMemInfo.getTel().indexOf("-") != -1){
				tels = existsMemInfo.getTel().split("-");
				
				if(tels.length == 3){
					memInfo.put("mTel1",tels[0]);
					memInfo.put("mTel2",tels[1]);
					memInfo.put("mTel3",tels[2]);
				}else if(tels.length == 2){
					memInfo.put("mTel1",tels[0]);
					memInfo.put("mTel2",tels[1]);
				}else if(tels.length == 1){
					memInfo.put("mTel1",tels[0]);
				}
			}
			
			String phones[] = null;
			String ePhones[] = null;
			String mEmail[] = null;
			String gEmail[] = null;
			
			if(!Strings.isNullOrEmpty(existsMemInfo.getgPhone()) && existsMemInfo.getgPhone().indexOf("-") != -1){
				phones = existsMemInfo.getgPhone().split("-");
				
				if(phones.length == 3){
					memInfo.put("gPhone1",phones[0]);
					memInfo.put("gPhone2",phones[1]);
					memInfo.put("gPhone3",phones[2]);
				}else if(phones.length == 2){
					memInfo.put("gPhone1",phones[0]);
					memInfo.put("gPhone2",phones[1]);
				}else if(phones.length == 1){
					memInfo.put("gPhone1",phones[0]);
				}
			}
			
			if(!Strings.isNullOrEmpty(existsMemInfo.getePhone()) && existsMemInfo.getePhone().indexOf("-") != -1){
				ePhones = existsMemInfo.getePhone().split("-");
				
				if(ePhones.length == 3){
					memInfo.put("ePhone1",ePhones[0]);
					memInfo.put("ePhone2",ePhones[1]);
					memInfo.put("ePhone3",ePhones[2]);
				}else if(ePhones.length == 2){
					memInfo.put("ePhone1",ePhones[0]);
					memInfo.put("ePhone2",ePhones[1]);
				}else if(ePhones.length == 1){
					memInfo.put("ePhone1",ePhones[0]);
				}
			}
			
			if(!Strings.isNullOrEmpty(existsMemInfo.getmEmail()) && existsMemInfo.getmEmail().indexOf("-") != -1){
				mEmail = existsMemInfo.getgEmail().split("@");
			
				memInfo.put("email1",mEmail[0]);
				memInfo.put("email2",mEmail[1]);
			}
			
			if(!Strings.isNullOrEmpty(existsMemInfo.getgEmail()) && existsMemInfo.getgEmail().indexOf("-") != -1){
				gEmail = existsMemInfo.getgEmail().split("@");
			
				memInfo.put("gEmail1",gEmail[0]);
				memInfo.put("gEmail2",gEmail[1]);
			}
			
			memInfo.put("specialComment",existsMemInfo.getSpecialComment());

		}else if("03".equals(pIpheiKind)){
			mujinMemInfo = ipheiService.findOmrGichoMujin(pOmrdate,pMkey,pMujinSubj);
			memInfo.put("gradeCD",mujinMemInfo.getOmrHak());
			memInfo.put("mFirstName",mujinMemInfo.getmFirstName());
			memInfo.put("mBirthYMD",mujinMemInfo.getOmrBirth());
			memInfo.put("mAddr",mujinMemInfo.getOmrAddr());
			
			String tels[] = null;
			if(!Strings.isNullOrEmpty(mujinMemInfo.getOmrTel()) && mujinMemInfo.getOmrTel().indexOf("-") != -1){
				tels = mujinMemInfo.getOmrTel().split("-");
				
				if(tels.length == 3){
					memInfo.put("mTel1",tels[0]);
					memInfo.put("mTel2",tels[1]);
					memInfo.put("mTel3",tels[2]);
				}else if(tels.length == 2){
					memInfo.put("mTel1",tels[0]);
					memInfo.put("mTel2",tels[1]);
				}else if(tels.length == 1){
					memInfo.put("mTel1",tels[0]);
				}
			}
		}
		
		if(pIpheiday.isEmpty()){
			pIpheiday = JeiCommonUtils.getToday("yyyymmdd");
		}
		
		List<Map<String,Object>> subjInfoList = new ArrayList<>();
		
		String subjs[] = pSubj.split(",");
		
		int i = 1;
		
		for (String subject : subjs) {
			Map<String,Object> tempMap = new HashMap<>();
			tempMap.put("mjGrade", ipheiService.selectMJgrade(authMemberInfo.getJisaCD(), subject, pIpheiday,pMkey,loginLang));
			tempMap.put("ipheiSubj",ipheiService.ipheiSubjCheck(authMemberInfo.getJisaCD(),subject, pIpheiKind,i,pMkey, loginLang));
			subjInfoList.add(tempMap);
			i++;
		}
		
		List<Map<String,Object>> classList = commonService.getClassList(authMemberInfo.getJisaCD(),pIpheiDepid);
		
		List<Comcode> ipheiComCodeList = commonService.selectCodeDtl(authMemberInfo.getJisaCD(),"0009","1",loginLang);
		List<Comcode> gradeComCodeList = commonService.selectCodeDtl(authMemberInfo.getJisaCD(),"0003","1",loginLang);
		List<String> fstDayList = commonService.selectMngDay(pIpheiday);
		
		model.addAttribute("title","입복회페이지");
		model.addAttribute("subjInfoList",subjInfoList);
		model.addAttribute("ipheiComCodeList",ipheiComCodeList);
		model.addAttribute("gradeComCodeList",gradeComCodeList);
		model.addAttribute("classList",classList);
		model.addAttribute("fstDayList",fstDayList);
		model.addAttribute("pIpheiday",pIpheiday);
		model.addAttribute("pIpheiKind",pIpheiKind);
		model.addAttribute("headerScript",headerScript);
		model.addAttribute("pIpheiDepid",pIpheiDepid);
		model.addAttribute("pJisaCD",pJisaCD);
		
		model.addAttribute("pMkey",pMkey);
		
		model.addAttribute("memInfo",memInfo);
		
		return "iphei/join";
	}
	
	
	@RequestMapping(value="/depositPop")
	public String depositPop(Model model,@CookieValue(value="LoginLang",defaultValue="K") String loginLang
			,@RequestParam(value="pIpheiday") String pIpheiday
			,@RequestParam(value="pMkucode") String pMkucode
			,@RequestParam(value="pJisaCD") String pJisaCD
			,@RequestParam(value="pDepid1") String pDepid1
			,@RequestParam(value="pSubj") String pSubj
			,@RequestParam(value="pMjGrade") String pMjGrade
			,@RequestParam(value="pMemGrade") String pMemGrade
			,@RequestParam(value="pFstDay") String pFstDay
			,@RequestParam(value="seqval") String seqval
			){
		
		AuthMemberInfo authMemberInfo = (AuthMemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String fstYMW = null;
		
		if(!Strings.isNullOrEmpty(pMjGrade) && "X".equals(pMjGrade)){
			fstYMW = commonService.selectFstDay(pFstDay);
			if(Strings.isNullOrEmpty(fstYMW)){
				//에러처리 하자 페이지하나만들어서
				return null;
			}else{
				model.addAttribute("fstYMW",fstYMW);
			}
		}
		
		if(Strings.isNullOrEmpty(pJisaCD)){
			pJisaCD = authMemberInfo.getJisaCD();
		}
		
		//판매구분
		List<Comcode> sellList = commonService.selectCodeDtl(pJisaCD, "0055","1", loginLang);
		//입회비면제
		List<Comcode> ipmSayuList = commonService.selectCodeDtl(pJisaCD, "0056","1", loginLang);
		//월회비면제
		List<Comcode> monthSayuList = commonService.selectCodeDtl(pJisaCD, "0057","1", loginLang);
		//월회비면제+입회비면제
		List<Comcode> bothSayuList = commonService.selectCodeDtl(pJisaCD, "0058","1", loginLang);
		//월회비할인
		List<Comcode> saleSayuList = commonService.selectCodeDtl(pJisaCD, "0059","1", loginLang);
		//잔여주차 리스트
		List<Map<String,Object>> juchaList = commonService.selectJucha(pIpheiday);
				
		//해더에 스크립트 추가
		List<String> headerScript = new ArrayList<>();
		headerScript.add("depositPop");
				
		model.addAttribute("sellList", sellList);
		model.addAttribute("ipmSayuList", ipmSayuList);
		model.addAttribute("monthSayuList", monthSayuList);
		model.addAttribute("bothSayuList", bothSayuList);
		model.addAttribute("saleSayuList", saleSayuList);
		model.addAttribute("pIpheiday", pIpheiday);
		model.addAttribute("pMkucode", pMkucode);
		model.addAttribute("juchaList", juchaList);
		model.addAttribute("seqval",seqval);
		model.addAttribute("pJisaCD",pJisaCD);
		model.addAttribute("pDepid1",pDepid1);
		model.addAttribute("pMjGrade",pMjGrade);
		model.addAttribute("pSubj",pSubj);
		model.addAttribute("pMemGrade",pMemGrade);
		
		model.addAttribute("headerScript",headerScript);
		
		return "iphei/depositPop";
	}
	
	
	
	@RequestMapping(value="/existsMember.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String,Object> existsMemberJson(Model model,@RequestParam(value="mkey",defaultValue="") String mkey
			,@RequestParam(value="mName",defaultValue="") String mName
			,@RequestParam(value="birthYY",defaultValue="") String birthYY){
		
		AuthMemberInfo authMemberInfo = (AuthMemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>>  existsMemberList = ipheiService.findExistsMember(authMemberInfo.getJisaCD(),mkey,mName,birthYY);
		map.put("existsMemberList", existsMemberList);
		return map;
	}
	
	@RequestMapping(value="/mujinMember.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String,Object> mujinMemberJson(Model model,@RequestParam(value="mkey",defaultValue="") String mkey
			,@RequestParam(value="mName",defaultValue="") String mName
			,@RequestParam(value="birthYY",defaultValue="") String birthYY){
		
		AuthMemberInfo authMemberInfo = (AuthMemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		Map<String,Object> map = new HashMap<>();
		List<Map<String,Object>>  mujinMemberList = ipheiService.findMujinMember(authMemberInfo.getJisaCD(),mkey,mName,birthYY);
		map.put("mujinMemberList", mujinMemberList);
		return map;
	}
	
	@RequestMapping(value="/depositSet.json",method=RequestMethod.GET,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String,Object> depositSetJson(Model model,@RequestParam(value="seqval",defaultValue="") String seqval
			,@RequestParam(value="jisaCD",defaultValue="") String jisaCD,@RequestParam(value="depid1",defaultValue="") String depid1
			,@RequestParam(value="bulsu",defaultValue="") String bulsu,@RequestParam(value="pSubj",defaultValue="") String pSubj
			,@RequestParam(value="grade",defaultValue="") String grade,@RequestParam(value="restymw",defaultValue="") String restymw
			,@RequestParam(value="mGubun",defaultValue="") String mGubun,@RequestParam(value="mSayu",defaultValue="") String mSayu){
		
		List<Map<String,Object>> resultList = ipheiService.selectDepositSet(jisaCD, depid1, bulsu, pSubj, grade, mGubun, mSayu, restymw);

		Map<String,Object> map = new HashMap<>();
		map.put("resultList", resultList);
		return map;
	}
	
	@RequestMapping(value="/ipheiOk.json",method=RequestMethod.POST,produces="application/json;charset=UTF-8;")
	@ResponseBody
	public Map<String,Object> ipheiOk(Model model,
			@RequestParam(value="pIpheiday",defaultValue="") String pIpheiday
			,@RequestParam(value="pIpkind",defaultValue="01") String pIpkind
			,@RequestParam(value="omrdate",defaultValue="") String omrdate
			,@RequestParam(value="pMkey",defaultValue="") String pMkey
			,@RequestParam(value="jisaCD",defaultValue="08") String jisaCD
			,@RequestParam(value="pIpheiDepid",defaultValue="") String pIpheiDepid
			,@RequestParam(value="pIpGuide",defaultValue="") String pIpGuide
			,@RequestParam(value="pFirstName",defaultValue="") String pFirstName
			,@RequestParam(value="pBirthDay",defaultValue="") String pBirthDay
			,@RequestParam(value="pSex",defaultValue="M") String pSex
			,@RequestParam(value="pSchool",defaultValue="") String pSchool
			,@RequestParam(value="pMemGrade",defaultValue="") String pMemGrade
			,@RequestParam(value="pAddr",defaultValue="") String pAddr
			,@RequestParam(value="pGfirstname",defaultValue="") String pGfirstname
			,@RequestParam(value="tel1",defaultValue="") String tel1
			,@RequestParam(value="tel2",defaultValue="") String tel2
			,@RequestParam(value="tel3",defaultValue="") String tel3
			,@RequestParam(value="phone1",defaultValue="") String phone1
			,@RequestParam(value="phone2",defaultValue="") String phone2
			,@RequestParam(value="phone3",defaultValue="") String phone3
			,@RequestParam(value="ePhone1",defaultValue="") String ePhone1
			,@RequestParam(value="ePhone2",defaultValue="") String ePhone2
			,@RequestParam(value="ePhone3",defaultValue="") String ePhone3
			,@RequestParam(value="email1",defaultValue="") String email1
			,@RequestParam(value="email2",defaultValue="") String email2
			,@RequestParam(value="gEmail1",defaultValue="") String gEmail1
			,@RequestParam(value="gEmail2",defaultValue="") String gEmail2
			,@RequestParam(value="specialComment",defaultValue="") String specialComment
			,@RequestParam(value="subj") String subj[]
			,@RequestParam(value="rejectSubj") String rejectSubj[]
			,@RequestParam(value="fstClass") String fstClass[]
			,@RequestParam(value="fstDay") String fstDay[]
			,@RequestParam(value="mjGrade") String mjGrade[]
			,@RequestParam(value="ipheibi") String ipheibi[]
			,@RequestParam(value="wolheibi") String wolheibi[]
			,@RequestParam(value="orgIpheibi") String orgIpheibi[]		
			,@RequestParam(value="orgWolheibi") String orgWolheibi[]	
			,@RequestParam(value="mGubun") String mGubun[]	
			,@RequestParam(value="mSayu") String mSayu[]	
			,@RequestParam(value="restymw") String restymw[]	
			,@RequestParam(value="enterwol") String enterwol[]
			,@RequestParam(value="mkucode") String mkucode[]
			,@RequestParam(value="ipheiType") String ipheiType
			){
		AuthMemberInfo authMemberInfo = (AuthMemberInfo)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		String tel = tel1+"-"+tel2+"-"+tel3;
		String email = email1+"@"+email2;
		String gEmail = gEmail1+"@"+gEmail2;
		String phone = phone1+"-"+phone2+"-"+phone3;
		String ePhone = ePhone1+"-"+ePhone2+"-"+ePhone3;
		
		Map<String,Object> resultMap = ipheiService.registIphei(authMemberInfo.getEmpKeyLvCD(), pIpheiday, pIpkind, pMkey, omrdate, pIpGuide
				, jisaCD, pIpheiDepid, pFirstName, pSchool, pMemGrade, pBirthDay, pSex, tel
				, pAddr, pGfirstname, email, gEmail, phone, ePhone,specialComment, subj
				, rejectSubj, fstClass, fstDay, mjGrade, ipheibi
				, wolheibi, orgIpheibi, orgWolheibi, mGubun, mSayu
				, restymw, enterwol, mkucode, ipheiType, authMemberInfo.getMemberId());
		
		return resultMap;
	}
}
