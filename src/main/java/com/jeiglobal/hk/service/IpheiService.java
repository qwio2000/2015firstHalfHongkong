package com.jeiglobal.hk.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.base.Strings;
import com.jeiglobal.hk.domain.common.MemIpgumMst;
import com.jeiglobal.hk.domain.common.MemIpheiAgree;
import com.jeiglobal.hk.domain.common.MemIpheiMst;
import com.jeiglobal.hk.domain.common.MemMst;
import com.jeiglobal.hk.domain.common.MemMstHis;
import com.jeiglobal.hk.domain.common.MemSubjMst;
import com.jeiglobal.hk.domain.common.MemSubjMstHis;
import com.jeiglobal.hk.domain.common.OmrGichoMujin;
import com.jeiglobal.hk.repository.CommonRepository;
import com.jeiglobal.hk.repository.IpheiRepository;
import com.jeiglobal.hk.utils.JeiCommonUtils;

@Service
public class IpheiService {
	
	@Autowired
	private CommonRepository commonRepository;
	
	@Autowired
	private IpheiRepository ipheiRepository;
	
	/**
	 * 기존회원 검색 결과:mKey,subj,jisaCD,stateCD,mFstName,mLstName,sexCD,birthYMD,tel,addr 
	 * @param jisaCD
	 * @param mkey
	 * @param mName
	 * @param birthYY
	 * @return
	 */
	public List<Map<String,Object>> findExistsMember(String jisaCD,String mkey,String mName,String birthYY){
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD",jisaCD);
		map.put("mkey",mkey);
		map.put("mName",mName);
		map.put("birthYY",birthYY);
		return ipheiRepository.findExistsMember(map);
	}
	
	/**
	 * 무료진단 회원 검색 결과:omrDate,hkey,kwamok,skey,sname,omrBirth
		,omrKind,omrGrd,omrDay,omrAddr,mFstName,mLstName
	 * @param jisaCD
	 * @param mkey
	 * @param mName
	 * @param birthYY
	 * @return
	 */
	public List<Map<String,Object>> findMujinMember(String jisaCD,String mkey,String mName,String birthYY){
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD",jisaCD);
		map.put("mkey",mkey);
		map.put("mName",mName);
		map.put("birthYY",birthYY);
		return ipheiRepository.findMujinMember(map);
	}
	
	/**
	 * 진단 등급 셀렉트 IPHEI_MJDGRD_SET
	 * @param jisaCD
	 * @param subj
	 * @param ipheiDay
	 * @param mkey
	 * @param loginLang
	 * @return
	 */
	public List<String> selectMJgrade(String jisaCD,String subj,String ipheiDay,String mkey,String loginLang){
		Map<String,Object> map = new HashMap<>();
		map.put("jisaCD",jisaCD);
		map.put("subj",subj);
		map.put("ipheiDay",ipheiDay);
		map.put("mkey",mkey);
		map.put("loginLang",loginLang);
		
		if(mkey != null && !mkey.isEmpty()){
			MemSubjMst memSubjMst = ipheiRepository.findMemSubjMst(map);
			
			if(memSubjMst == null || memSubjMst.getMkey().isEmpty() || Strings.isNullOrEmpty(memSubjMst.getHuheiYMD())){
				return ipheiRepository.selectMJgrade(map);
			}else{
				
				String huheiYMD[] = memSubjMst.getHuheiYMD().split("-");
				Map<String,Object> huheiMap = new HashMap<>();
				
				huheiMap.put("pYear",huheiYMD[0]);
				huheiMap.put("pMonth",huheiYMD[1]);
				huheiMap.put("pDay",huheiYMD[2]);
				
				String huheiYMW = commonRepository.selectHuheiYMW(huheiMap);
				
				map.put("huheiYMW", huheiYMW);
				
				long jsSetcnt = ipheiRepository.countJSET(map);
				
				int diffMonth = JeiCommonUtils.getMonthDiff(ipheiDay, memSubjMst.getHuheiYMD(),"-");
				
				if("PS".equals(subj) || "ER".equals(subj)){
					List<String> list = new ArrayList<String>();
					list.add("X");
					return list;
				}
				
				if("KC".equals(subj) || "KJ".equals(subj) || ( !"KC".equals(subj) && "2".equals(memSubjMst.getStateCD())  && diffMonth <4 && jsSetcnt > 5 )){
						return ipheiRepository.selectMJgrade(map);
				}else{
						map.put("grade","X");
						return ipheiRepository.selectMJgrade(map);
				}
			}
		}else{
			return ipheiRepository.selectMJgrade(map);
		}
	}
	/**
	 * 입화가능한 과목인지 체크하는 로직 [IPHEI_SUBJNM_SET]
	 * @param jisaCD
	 * @param subj
	 * @param ipheiKind
	 * @param ipheiSeq
	 * @param mkey
	 * @param loginLang
	 * @return
	 */
	public Map<String,Object> ipheiSubjCheck(String jisaCD,String subj,String ipheiKind,int ipheiSeq,String mkey,String loginLang){
		Map<String,Object> map = new HashMap<>();
		Map<String,Object> resultMap = new HashMap<>();
		map.put("mkey",mkey);
		map.put("subj",subj);
		
		String mkucode= "";
		String dongsubj = "";
		String daegisubj = "";
		long agreeNum = 0;
		
		//신입 기존 무료진단
		if("01".equals(ipheiKind)){
			if(ipheiSeq == 1){
				mkucode = "01";
			}else{
				mkucode = "03";
			}
				
		}else if("02".equals(ipheiKind)){
			if(ipheiRepository.isExistsMemSubjMst(map)){
				String stateCD = ipheiRepository.selectStateCDMemSubjMst(map);
				if("1".equals(stateCD)){
					mkucode = "";
				}else{
					mkucode = "02";
				}
			}else{
				mkucode = "03";
			}
		}else if("03".equals(ipheiKind)){
			if(ipheiRepository.isExistsMemMst(map)){
				if(ipheiRepository.isExistsMemSubjMst(map)){
					String stateCD = ipheiRepository.selectStateCDMemSubjMst(map);
					if("1".equals(stateCD)){
						mkucode = "";
					}else{
						mkucode = "02";
					}
				}else{
					mkucode = "03";
				}
			}else{
				if(ipheiSeq == 1){
					mkucode = "01";
				}else{
					mkucode = "03";
				}
			}
		}
		
		//입회허용 과목 체크
		Map<String,Object> checkMap = new HashMap<>();
		checkMap.put("mkey",mkey);
		checkMap.put("stateCD","1");
		
		if("KK".equals(subj)){
			checkMap.put("subj","KG");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "KG";
				mkucode = "";
			}
		}else if("KG".equals(subj)){
			checkMap.put("subj","KK");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "KK";
				mkucode = "";
			}
		}else if("KE".equals(subj)){
			checkMap.put("subj","KL");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "KL";
				mkucode = "";
			}
		}else if("KL".equals(subj)){
			checkMap.put("subj","KE");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "KE";
				mkucode = "";
			}
		}else if("KC".equals(subj)){
			checkMap.put("subj","KJ");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "KJ";
				mkucode = "";
			}
		}else if("KJ".equals(subj)){
			checkMap.put("subj","KC");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "KC";
				mkucode = "";
			}
		}else if("CE".equals(subj)){
			checkMap.put("subj","CL");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "CL";
				mkucode = "";
			}
		}else if("CL".equals(subj)){
			checkMap.put("subj","CE");
			if(ipheiRepository.isExistsMemSubjMst(checkMap)){
				dongsubj = "CE";
				mkucode = "";
			}
		}
		
		//입회대기 체크
		map.put("agreeCD","00");
		if(ipheiRepository.isMemIpheiAgree(map)){
			agreeNum = ipheiRepository.findMemIpheiAgree(map);
			mkucode = "";
		}else{
			Map<String,Object> daegiMap = new HashMap<>();
			daegiMap.put("mkey",mkey);
			daegiMap.put("agreeCD","00");
			
			if("KK".equals(subj)){
				daegiMap.put("subj","KG");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "KG";
					mkucode = "";
				}
			}else if("KG".equals(subj)){
				daegiMap.put("subj","KK");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "KK";
					mkucode = "";
				}
			}else if("KE".equals(subj)){
				daegiMap.put("subj","KL");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "KG";
					mkucode = "";
				}
			}else if("KL".equals(subj)){
				daegiMap.put("subj","KE");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "KE";
					mkucode = "";
				}
			}else if("KC".equals(subj)){
				daegiMap.put("subj","KJ");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "KJ";
					mkucode = "";
				}
			}else if("KJ".equals(subj)){
				daegiMap.put("subj","KC");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "KC";
					mkucode = "";
				}
			}else if("CE".equals(subj)){
				daegiMap.put("subj","CL");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "CL";
					mkucode = "";
				}
			}else if("CL".equals(subj)){
				daegiMap.put("subj","CE");
				if(ipheiRepository.isMemIpheiAgree(daegiMap)){
					daegisubj = "CE";
					mkucode = "";
				}
			}
		}
		
		resultMap.put("subj",subj);
		resultMap.put("mkucode",mkucode);
		resultMap.put("dongsubj",dongsubj);
		resultMap.put("daegisubj",daegisubj);
		resultMap.put("agreeNum",agreeNum);
		
		return resultMap;
	}
	
	/**
	 * 입회시 월회비 구간 테이블 데이터 IPHEI_DEPOSIT_SET
	 * @param jisaCD
	 * @param depid1
	 * @param ipheiDay
	 * @param bulsu
	 * @param subj
	 * @param grade
	 * @param ipGuide
	 * @param mGubun
	 * @param mSayu
	 * @param jucha
	 * @return
	 */
	public List<Map<String,Object>> selectDepositSet(String jisaCD,String depid1
			,String bulsu,String subj,String grade
			,String mGubun,String mSayu,String restymw){
		
		
		String setType = "";
		
		if(isDepositSet(jisaCD,depid1,subj)){
			String gradeCheck[] = {"07","08","09","10","11","12","13","77"};
			if(Arrays.asList(gradeCheck).contains(grade)){
				setType = "11";
			}else{
				setType = "10";
			}
		}else{
			setType = "10";
		}
		
		int jucha = Integer.parseInt(restymw.substring(6));
		
		Map<String,Object> map = new HashMap<>();
		
		map.put("jisaCD",jisaCD);
		map.put("depid1",depid1);
		map.put("bulsu",bulsu);
		map.put("subj",subj);
		map.put("jucha",jucha);
		map.put("setType",setType);
		
		if("05".equals(mGubun) && "02".equals(mSayu) ){
			map.put("mGubun",mGubun);	
			map.put("mSayu",mSayu);	
		}else{
			map.put("mGubun","");	
			map.put("mSayu","");	
		}
		
		return ipheiRepository.selectDepositSet(map);
	}
	
	private boolean isDepositSet(String jisaCD,String depid1,String subj){
		Map<String,Object> map = new HashMap<>();
		
		map.put("jisaCD", jisaCD);
		map.put("depid1", depid1);
		map.put("subj", subj);
		
		List<Map<String,Object>> list = ipheiRepository.isDepositSet(map);
		
		if(list == null || list.size() == 0){
			return false;
		}else{
			return true;
		}
	}
	
	/**
	 * 기존회원 검색 IPHEI_EXISTS_MKEY_CHK
	 * @param pMkey
	 * @return
	 */
	public MemMst findMemMstById(String pMkey) {
		Map<String,Object> map = new HashMap<>();
		map.put("mkey", pMkey);
		
		return ipheiRepository.findMemMstById(map);
	}
	
	/**
	 * 무료진단 회원 정보 IPHEI_MUJIN_MKEY_CHK
	 * @param pOmrdate
	 * @param pMkey
	 * @param pMujinSubj
	 * @return
	 */
	public OmrGichoMujin findOmrGichoMujin(String pOmrdate, String pMkey,
			String pMujinSubj) {
		Map<String,Object> map = new HashMap<>();
		map.put("omrDate", pOmrdate);
		map.put("hkey", pMkey);
		map.put("subj", pMujinSubj);
		
		return ipheiRepository.findOmrGichoMujin(map);
	}
	/**
	 * 회원번호 생성 NewSKey_INS
	 * @param ipKind
	 * @return
	 */
	private String selectNewKey(String ipKind){
		
		Map<String,Object> map = new HashMap<>();
		map.put("ipKind",ipKind);
		
		return commonRepository.selectNewKey(map);
	}
	
	/**
	 * MemIpheiAgree 테이블 agreenum+1 가져오기 IPHEI_AGNUM_SET
	 * @return
	 */
	private long selectMemIpheiAgreeAgnum(){
		return ipheiRepository.selectMemIpheiAgreeAgnum();
	}
	
	/**
	 * 입회 처방 로직
	 * @param empKeyLvCD
	 * @param ipheiDay
	 * @param ipKind
	 * @param mkey
	 * @param omrDate
	 * @param pIpGuide
	 * @param jisaCD
	 * @param depid1
	 * @param pFirstName
	 * @param pSchool
	 * @param pMemGrade
	 * @param pBirthDay
	 * @param pSex
	 * @param tel
	 * @param pAddr
	 * @param pGfirstname
	 * @param email
	 * @param gEmail
	 * @param phone
	 * @param ePhone
	 * @param subj
	 * @param rejectSubj
	 * @param fstClass
	 * @param fstDay
	 * @param mjGrade
	 * @param ipheibi
	 * @param wolheibi
	 * @param orgIpheibi
	 * @param orgWolheibi
	 * @param mGubun
	 * @param mSayu
	 * @param restymw
	 * @param enterwol
	 * @param mkucode
	 * @param ipheiType
	 * @param regID
	 * @return
	 */
	@Transactional(value="primaryTransactionManager")
	public Map<String,Object> registIphei(String empKeyLvCD,String ipheiDay,String ipKind,String mkey,String omrDate,String pIpGuide,String jisaCD
			,String pIpheiDepid,String pFirstName,String pSchool,String pMemGrade,String pBirthDay,String pSex,String tel,String pAddr
			,String pGfirstname,String email,String gEmail,String phone,String ePhone,String specialComment
			,String subj[],String rejectSubj[],String fstClass[],String fstDay[],String mjGrade[]
			,String ipheibi[],String wolheibi[],String orgIpheibi[],String orgWolheibi[],String mGubun[]
			,String mSayu[],String restymw[],String enterwol[],String mkucode[],String ipheiType,String regID){
		
		Map<String,Object> resultMap = new HashMap<>();
		
		if("01".equals(ipKind) && Strings.isNullOrEmpty(mkey)){
			mkey = selectNewKey(ipKind);
		}
		
		long agreeNum = 0;
		int successCnt = 0;
		boolean agvalFlag = false;
		boolean memInsertCheckFlag = false;
		
		String gotoSubj = null;
		String gotoMjGrade = null;
				
		for (int i = 0; i < subj.length; i++) {
			if(!Strings.isNullOrEmpty(subj[i])){
				
				if(agvalFlag || ("01".equals(ipKind) && "01".equals(mkucode[i]) && !"01".equals(mGubun[i]) && !"03".equals(mSayu[i]))){
					agvalFlag = true;
					
					if(agreeNum == 0){
						agreeNum = selectMemIpheiAgreeAgnum();
					}
				}else if(!"01".equals(mGubun[i]) && !"03".equals(mSayu[i])){
					agreeNum = selectMemIpheiAgreeAgnum();
				}
				
				//처방연결업무시 필요
				if(agreeNum == 0 && Strings.isNullOrEmpty(gotoSubj) && !"X".equals(mjGrade[i])){
					gotoSubj = subj[i];
					gotoMjGrade = mjGrade[i];
				}
				
				String pRestYMW = restymw[i];
				int pIpheibi = Integer.parseInt(ipheibi[i]);
				int pWolheibi = Integer.parseInt(wolheibi[i]);
				int pOrgIpheibi = Integer.parseInt(orgIpheibi[i]);
				int pOrgWolheibi = Integer.parseInt(orgWolheibi[i]);
				int pEnterwol = Integer.parseInt(enterwol[i]); 
				
				//승인입회
				if("FA".equals(empKeyLvCD) && (agvalFlag || (!"01".equals(mGubun[i]) && !"03".equals(mSayu[i]))) ){

					Map<String,Object> empMap = ipheiRepository.selectComEmpMst(fstClass[i]);
					
					MemIpheiAgree memIpheiAgree = new MemIpheiAgree();
					memIpheiAgree.setAgreeNum(agreeNum);
					memIpheiAgree.setIpheiYMD(ipheiDay);
					memIpheiAgree.setMkey(mkey);
					memIpheiAgree.setSubj(subj[i]);
					memIpheiAgree.setIpheiGubunCD(mkucode[i]);
					memIpheiAgree.setIpheiKindMstCD(ipKind);
					memIpheiAgree.setIpheiKindDtlCD("00");
					memIpheiAgree.setmFstName(pFirstName);
					memIpheiAgree.setmLstName("");
					memIpheiAgree.setSchoolName(pSchool);
					memIpheiAgree.setGradeCD(pMemGrade);
					memIpheiAgree.setBirthYMD(pBirthDay);
					memIpheiAgree.setSexCD(pSex);
					memIpheiAgree.setTel(tel);
					memIpheiAgree.setZip("");
					memIpheiAgree.setAddr(pAddr);
					memIpheiAgree.setFstYMD(fstDay[i]);
					memIpheiAgree.setFstYoil(String.valueOf(JeiCommonUtils.getWeekNum(fstDay[i])));
					memIpheiAgree.setFstEmpKey(fstClass[i]);
					memIpheiAgree.setFstEmpName(empMap.get("empName").toString());
					memIpheiAgree.setFstDepid1(empMap.get("depid1").toString());
					memIpheiAgree.setFstDepid2("00");
					memIpheiAgree.setSndYMD("");
					memIpheiAgree.setSndYoil("");
					memIpheiAgree.setSndEmpKey("");
					memIpheiAgree.setSndEmpName("");
					memIpheiAgree.setSndDepid1("");
					memIpheiAgree.setSndDepid2("");
					memIpheiAgree.setJisaCD(jisaCD);
					memIpheiAgree.setPayKindCD("D");
					memIpheiAgree.setIpheiPathCD(pIpGuide);
					memIpheiAgree.setjGradeCD(mjGrade[i]);
					memIpheiAgree.setSetCnt("1");
					memIpheiAgree.setgFstName(pGfirstname);
					memIpheiAgree.setgLstName("");
					memIpheiAgree.setmEmail(email);
					memIpheiAgree.setgEmail(gEmail);
					memIpheiAgree.setmPhone(phone);
					memIpheiAgree.setgPhone("");
					memIpheiAgree.setePhone(ePhone);
					memIpheiAgree.setIpgumGubunCD(mkucode[i]);
					memIpheiAgree.setIpgumKindMstCD(ipKind);
					memIpheiAgree.setIpgumKindDtlCD("00");
					memIpheiAgree.setRestYMW(pRestYMW);
					memIpheiAgree.setIpheibiFree(pIpheibi);
					memIpheiAgree.setWolheibiFree(pWolheibi);
					memIpheiAgree.setIpheibi(pOrgIpheibi);
					memIpheiAgree.setWolheibi(pOrgWolheibi);
					memIpheiAgree.setEnterwol(pEnterwol);
					memIpheiAgree.setOmrDate(omrDate);
					memIpheiAgree.setAgreeCD("00");
					memIpheiAgree.setAgreeSayuCD("");
					memIpheiAgree.setAgreeYMD("");
					memIpheiAgree.setAgreeDepid1("");
					memIpheiAgree.setAgreeDepid2("");
					memIpheiAgree.setAgreeID("");
					memIpheiAgree.setAgreeDate(null);
					memIpheiAgree.setBranchCD(jisaCD+pIpheiDepid);
					memIpheiAgree.setBanyungCD(0);
					memIpheiAgree.setBanyungDate(null);
					memIpheiAgree.setBanyungID("");
					memIpheiAgree.setRegID(regID);
					
					ipheiRepository.insertMemIpheiAgree(memIpheiAgree);
					
					successCnt++;
				}else{
					if("01".equals(mkucode[i])){
					//신입
						int yoil1 = JeiCommonUtils.getWeekNum(fstDay[i]);
						
						Map<String,Object> map = new HashMap<>();
						map.put("mkey",mkey);
						map.put("subj",subj[i]);
						
						boolean memCheckFlag = ipheiRepository.isExistsMemSubjMst(map);
						
						String exday = JeiCommonUtils.getLastDateOfMonth(ipheiDay);
						
						Map<String,Object> empMap = ipheiRepository.selectComEmpMst(fstClass[i]);
						
						int mIpheibi = Integer.parseInt(orgIpheibi[i]) - Integer.parseInt(ipheibi[i]);
						int mWolheibi = Integer.parseInt(orgWolheibi[i]) - Integer.parseInt(wolheibi[i]);
						
						if(!memCheckFlag){
							
							MemSubjMst memSubjMst = new MemSubjMst();
							memSubjMst.setMkey(mkey);
							memSubjMst.setSubj(subj[i]);
							memSubjMst.setFstEmpKey(fstClass[i]);
							memSubjMst.setFstDepid1(empMap.get("depid1").toString());
							memSubjMst.setFstDepid2("00");
							memSubjMst.setFstYoil(String.valueOf(yoil1));
							memSubjMst.setSndEmpKey("");
							memSubjMst.setSndDepid1("");
							memSubjMst.setSndDepid2("");
							memSubjMst.setSndYoil("");
							memSubjMst.setBfFstEmpKey("");
							memSubjMst.setBfFstDepid1("");
							memSubjMst.setBfFstDepid2("");
							memSubjMst.setBfFstYoil("");
							memSubjMst.setBfSndEmpKey("");
							memSubjMst.setBfSDepid1("");
							memSubjMst.setBfSDepid2("");
							memSubjMst.setBfSYoil("");
							memSubjMst.setJisaCD(jisaCD);
							memSubjMst.setStateCD("1");
							memSubjMst.setIpheiYMD(ipheiDay);
							memSubjMst.setBokheiYMD("");
							memSubjMst.setHuheiYMD("");
							memSubjMst.setExpireYMD(exday);
							memSubjMst.setIpgumYMD("");
							memSubjMst.setjGradeCD(mjGrade[i]);
							memSubjMst.setTaGubunCD("");
							memSubjMst.setMkeyKindCD("01");
							memSubjMst.setIpheiPathCD(pIpGuide);
							memSubjMst.setPayKindCD("D");
							memSubjMst.setSetCnt("1");
							memSubjMst.setBfSetCnt("");
							memSubjMst.setWeekCnt("1");
							memSubjMst.setBfWeekCnt("");
							memSubjMst.setMngSortCD("1");
							memSubjMst.setRegID(regID);
							memSubjMst.setUpdID(regID);
							
							ipheiRepository.insertMemSubjMst(memSubjMst);
							
							MemMst memMst = new MemMst();
							memMst.setMkey(mkey);
							memMst.setmFstName(pFirstName);
							memMst.setmLstName("");
							memMst.setSchoolName(pSchool);
							memMst.setGradeCD(pMemGrade);
							memMst.setBirthYMD(pBirthDay);
							memMst.setSexCD(pSex);
							memMst.setZip("");
							memMst.setAddr(pAddr);
							memMst.setTel(tel);
							memMst.setgFstName(pGfirstname);
							memMst.setgLstName("");
							memMst.setmEmail(email);
							memMst.setgEmail(gEmail);
							memMst.setmPhone(phone);
							memMst.setgPhone("");
							memMst.setePhone(ePhone);
							memMst.setSpecialComment(specialComment);
							memMst.setRegID(regID);
							memMst.setUpdID(regID);
							
							ipheiRepository.insertMemMst(memMst);
							memInsertCheckFlag = true;
							
							
							MemIpheiMst memIpheiMst = new MemIpheiMst();
							memIpheiMst.setIpheiYMD(ipheiDay);
							memIpheiMst.setMkey(mkey);
							memIpheiMst.setSubj(subj[i]);
							memIpheiMst.setIpheiGubunCD(mkucode[i]);
							memIpheiMst.setIpheiKindMstCD(ipKind);
							memIpheiMst.setIpheiKindDtlCD("00");
							memIpheiMst.setmFstName(pFirstName);
							memIpheiMst.setmLstName("");
							memIpheiMst.setSchoolName(pSchool);
							memIpheiMst.setGradeCD(pMemGrade);
							memIpheiMst.setBirthYMD(pBirthDay);
							memIpheiMst.setSexCD(pSex);
							memIpheiMst.setTel(tel);
							memIpheiMst.setZip("");
							memIpheiMst.setAddr(pAddr);
							memIpheiMst.setFstYMD(fstDay[i]);
							memIpheiMst.setFstYoil(String.valueOf(JeiCommonUtils.getWeekNum(fstDay[i])));
							memIpheiMst.setFstEmpKey(fstClass[i]);
							memIpheiMst.setFstEmpName(empMap.get("empName").toString());
							memIpheiMst.setFstDepid1(empMap.get("depid1").toString());
							memIpheiMst.setFstDepid2("00");
							memIpheiMst.setSndYMD("");
							memIpheiMst.setSndYoil("");
							memIpheiMst.setSndEmpKey("");
							memIpheiMst.setSndEmpName("");
							memIpheiMst.setSndDepid1("");
							memIpheiMst.setSndDepid2("");
							memIpheiMst.setJisaCD(jisaCD);
							memIpheiMst.setPayKindCD("D");
							memIpheiMst.setIpheiPathCD(pIpGuide);
							memIpheiMst.setjGradeCD(mjGrade[i]);
							memIpheiMst.setSetCnt("1");
							memIpheiMst.setgFstName(pGfirstname);
							memIpheiMst.setgLstName("");
							memIpheiMst.setmEmail(email);
							memIpheiMst.setgEmail(gEmail);
							memIpheiMst.setmPhone(phone);
							memIpheiMst.setgPhone("");
							memIpheiMst.setePhone(ePhone);
							memIpheiMst.setBranchCD(jisaCD+pIpheiDepid);
							memIpheiMst.setBanyungCD(0);
							memIpheiMst.setBanyungDate(null);
							memIpheiMst.setBanyungID("");
							memIpheiMst.setRegID(regID);
							
							ipheiRepository.insertMemIpheiMst(memIpheiMst);
							
							MemIpgumMst memIpgumMst = new MemIpgumMst();
							memIpgumMst.setIpgumYMD(ipheiDay);
							memIpgumMst.setMkey(mkey);
							memIpgumMst.setSubj(subj[i]);
							memIpgumMst.setIpgumGubunCD(mkucode[i]);
							memIpgumMst.setIpgumKindMstCD(ipKind);
							memIpgumMst.setIpgumKindDtlCD("00");
							memIpgumMst.setmFirstName(pFirstName);
							memIpgumMst.setmLastName("");
							memIpgumMst.setExistCD("1");
							memIpgumMst.setPayKindCD("D");
							memIpgumMst.setEmpKey(fstClass[i]);
							memIpgumMst.setEmpName(empMap.get("empName").toString());
							memIpgumMst.setDepid1(empMap.get("depid1").toString());
							memIpgumMst.setDepid2("00");
							memIpgumMst.setIpheibiFree(mIpheibi);
							memIpgumMst.setWolheibiFree(mWolheibi);
							memIpgumMst.setIpheibi(pOrgIpheibi);
							memIpgumMst.setWolheibi(pOrgWolheibi);
							memIpgumMst.setEnterwol(pEnterwol);
							memIpgumMst.setExpireYMD(exday);
							memIpgumMst.setBranchCD(jisaCD+pIpheiDepid);
							memIpgumMst.setBanyungCD(0);
							memIpgumMst.setBanyungDate(null);
							memIpgumMst.setBanyungID("");
							memIpgumMst.setRegID(regID);
							
							ipheiRepository.insertMemIpgumMst(memIpgumMst);						
							
							
							if(!"PS".equals(subj[i]) && !"ER".equals(subj[i])){
								if("03".equals(ipKind)){
									//EXEC GBMUJIN..MUJIN_IPHEI_JINDO_SETTING @JISA,@FSTDAY1,@IPHEIDAY,@OMRDATE,@MKEY,@SUBJ
								}else{
									if("X".equals(mjGrade[i])){
										//EXECUTE MUJIN_INSERT  @MKEY ,@SUBJ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
									}
								}
							}else{
								//EXECUTE MUJIN_PS_INSERT  @MKUCODE, @MKEY ,@SUBJ,@MJDGRD ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
							}
							
							successCnt++;
						}
					}else if("02".equals(mkucode[i])){
					//복회	
						int yoil1 = JeiCommonUtils.getWeekNum(fstDay[i]);
						
						Map<String,Object> map = new HashMap<>();
						map.put("mkey",mkey);
						map.put("subj",subj[i]);
						map.put("stateCD","2");
						
						boolean memCheckFlag = ipheiRepository.isExistsMemSubjMst(map);
						
						if(memCheckFlag){
							String exday = JeiCommonUtils.getLastDateOfMonth(ipheiDay);

							map.remove("stateCD");
							map.put("mngSortCD","1");
							
							int mIpheibi = Integer.parseInt(orgIpheibi[i]) - Integer.parseInt(ipheibi[i]);
							int mWolheibi = Integer.parseInt(orgWolheibi[i]) - Integer.parseInt(wolheibi[i]);
							
							MemSubjMst memBeforSubjInfo = ipheiRepository.findMemSubjMst(map);
							
							Map<String,Object> empMap = ipheiRepository.selectComEmpMst(fstClass[i]);
							
							//백업
							MemSubjMstHis memSubjMstHis =  new MemSubjMstHis();
							memSubjMstHis.setMkey(memBeforSubjInfo.getMkey());
							memSubjMstHis.setSubj(memBeforSubjInfo.getSubj());
							memSubjMstHis.setFstEmpKey(memBeforSubjInfo.getFstEmpKey());
							memSubjMstHis.setFstDepid1(memBeforSubjInfo.getFstDepid1());
							memSubjMstHis.setFstDepid2(memBeforSubjInfo.getFstDepid2());
							memSubjMstHis.setFstYoil(memBeforSubjInfo.getFstYoil());
							memSubjMstHis.setSndEmpKey(memBeforSubjInfo.getSndEmpKey());
							memSubjMstHis.setSndDepid1(memBeforSubjInfo.getSndDepid1());
							memSubjMstHis.setSndDepid2(memBeforSubjInfo.getSndDepid2());
							memSubjMstHis.setSndYoil(memBeforSubjInfo.getSndYoil());
							memSubjMstHis.setBfFstEmpKey(memBeforSubjInfo.getBfFstEmpKey());
							memSubjMstHis.setBfFstDepid1(memBeforSubjInfo.getBfFstDepid1());
							memSubjMstHis.setBfFstDepid2(memBeforSubjInfo.getBfFstDepid2());
							memSubjMstHis.setBfFstYoil(memBeforSubjInfo.getBfFstYoil());
							memSubjMstHis.setBfSndEmpKey(memBeforSubjInfo.getBfSndEmpKey());
							memSubjMstHis.setBfSDepid1(memBeforSubjInfo.getBfSDepid1());
							memSubjMstHis.setBfSDepid2(memBeforSubjInfo.getBfSDepid2());
							memSubjMstHis.setBfSYoil(memBeforSubjInfo.getBfSYoil());
							memSubjMstHis.setJisaCD(memBeforSubjInfo.getJisaCD());
							memSubjMstHis.setStateCD(memBeforSubjInfo.getStateCD());
							memSubjMstHis.setIpheiYMD(memBeforSubjInfo.getIpheiYMD());
							memSubjMstHis.setBokheiYMD(memBeforSubjInfo.getBokheiYMD());
							memSubjMstHis.setHuheiYMD(memBeforSubjInfo.getHuheiYMD());
							memSubjMstHis.setExpireYMD(memBeforSubjInfo.getExpireYMD());
							memSubjMstHis.setIpgumYMD(memBeforSubjInfo.getIpgumYMD());
							memSubjMstHis.setjGradeCD(memBeforSubjInfo.getjGradeCD());
							memSubjMstHis.setTaGubunCD(memBeforSubjInfo.getTaGubunCD());
							memSubjMstHis.setMkeyKindCD(memBeforSubjInfo.getMkeyKindCD());
							memSubjMstHis.setIpheiPathCD(memBeforSubjInfo.getIpheiPathCD());
							memSubjMstHis.setPayKindCD(memBeforSubjInfo.getPayKindCD());
							memSubjMstHis.setSetCnt(memBeforSubjInfo.getSetCnt());
							memSubjMstHis.setBfSetCnt(memBeforSubjInfo.getBfSetCnt());
							memSubjMstHis.setWeekCnt(memBeforSubjInfo.getWeekCnt());
							memSubjMstHis.setMngSortCD(memBeforSubjInfo.getMngSortCD());
							memSubjMstHis.setRegDate(memBeforSubjInfo.getRegDate());
							memSubjMstHis.setRegID(memBeforSubjInfo.getRegID());
							memSubjMstHis.setUpdID(regID);
							memSubjMstHis.setUpdCD("2");
							
							ipheiRepository.insertMemSubjMstHis(memSubjMstHis);
							
							MemSubjMst memSubjMst = new MemSubjMst();
							memSubjMst.setFstEmpKey(fstClass[i]);
							memSubjMst.setFstDepid1(empMap.get("depid1").toString());
							memSubjMst.setFstDepid2("00");
							memSubjMst.setFstYoil(String.valueOf(yoil1));
							memSubjMst.setSndEmpKey("");
							memSubjMst.setSndDepid1("");
							memSubjMst.setSndDepid2("");
							memSubjMst.setSndYoil("");
							memSubjMst.setBfFstEmpKey(memBeforSubjInfo.getFstEmpKey());
							memSubjMst.setBfFstDepid1(memBeforSubjInfo.getFstDepid1());
							memSubjMst.setBfFstDepid2(memBeforSubjInfo.getFstDepid2());
							memSubjMst.setBfFstYoil(memBeforSubjInfo.getFstYoil());
							memSubjMst.setBfSndEmpKey(memBeforSubjInfo.getSndEmpKey());
							memSubjMst.setBfSDepid1(memBeforSubjInfo.getSndDepid1());
							memSubjMst.setBfSDepid2(memBeforSubjInfo.getSndDepid2());
							memSubjMst.setBfSYoil(memBeforSubjInfo.getSndYoil());
							memSubjMst.setJisaCD(jisaCD);
							memSubjMst.setStateCD("1");
							memSubjMst.setIpheiYMD(memBeforSubjInfo.getIpheiYMD());
							memSubjMst.setBokheiYMD(ipheiDay);
							memSubjMst.setHuheiYMD(memBeforSubjInfo.getHuheiYMD());
							memSubjMst.setExpireYMD(exday);
							memSubjMst.setIpgumYMD(memBeforSubjInfo.getIpgumYMD());
							memSubjMst.setjGradeCD(mjGrade[i]);
							memSubjMst.setTaGubunCD(memBeforSubjInfo.getTaGubunCD());
							memSubjMst.setMkeyKindCD(memBeforSubjInfo.getMkeyKindCD());
							memSubjMst.setIpheiPathCD(pIpGuide);
							memSubjMst.setPayKindCD("D");
							memSubjMst.setSetCnt("1");
							memSubjMst.setBfSetCnt(memBeforSubjInfo.getBfSetCnt());
							memSubjMst.setWeekCnt("1");
							memSubjMst.setMngSortCD("1");
							memSubjMst.setRegDate(memBeforSubjInfo.getRegDate());
							memSubjMst.setRegID(memBeforSubjInfo.getRegID());
							memSubjMst.setUpdID(regID);
							
							ipheiRepository.updateMemSubjMst(memSubjMst);
							
							if(!memInsertCheckFlag){
								//백업
								Map<String,Object> memMap = new HashMap<>();
								memMap.put("mkey",mkey);
								
								MemMst memInfo = ipheiRepository.findMemMstById(memMap);
															
								MemMstHis memMstHis = new MemMstHis();
								memMstHis.setMkey(memInfo.getMkey());
								memMstHis.setmFstName(memInfo.getmFstName());
								memMstHis.setmLstName(memInfo.getmLstName());
								memMstHis.setSchoolName(memInfo.getSchoolName());
								memMstHis.setGradeCD(memInfo.getGradeCD());
								memMstHis.setBirthYMD(memInfo.getBirthYMD());
								memMstHis.setSexCD(memInfo.getSexCD());
								memMstHis.setZip(memInfo.getZip());
								memMstHis.setAddr(memInfo.getAddr());
								memMstHis.setTel(memInfo.getTel());
								memMstHis.setgFstName(memInfo.getgFstName());
								memMstHis.setgLstName(memInfo.getgLstName());
								memMstHis.setmEmail(memInfo.getmEmail());
								memMstHis.setgEmail(memInfo.getgEmail());
								memMstHis.setmPhone(memInfo.getmPhone());
								memMstHis.setgPhone(memInfo.getgPhone());
								memMstHis.setePhone(memInfo.getePhone());
								memMstHis.setSpecialComment(memInfo.getSpecialComment());
								memMstHis.setRegDate(memInfo.getRegDate());
								memMstHis.setRegID(memInfo.getRegID());
								memMstHis.setUpdID(regID);
								memMstHis.setUpdCD("10");
								
								ipheiRepository.insertMemMstHis(memMstHis);
								//memMst 업데이트
								MemMst memMst = new MemMst();
								memMst.setmFstName(pFirstName);
								memMst.setmLstName("");
								memMst.setSchoolName(pSchool);
								memMst.setGradeCD(pMemGrade);
								memMst.setBirthYMD(pBirthDay);
								memMst.setSexCD(pSex);
								memMst.setZip("");
								memMst.setAddr(pAddr);
								memMst.setTel(tel);
								memMst.setgFstName(pGfirstname);
								memMst.setgLstName("");
								memMst.setmEmail(email);
								memMst.setgEmail(gEmail);
								memMst.setmPhone(phone);
								memMst.setgPhone("");
								memMst.setePhone(ePhone);
								memMst.setSpecialComment(specialComment);
								memMst.setUpdID(regID);
								
								ipheiRepository.updateMemMst(memMst);
							}
							
							MemIpheiMst memIpheiMst = new MemIpheiMst();
							memIpheiMst.setIpheiYMD(ipheiDay);
							memIpheiMst.setMkey(mkey);
							memIpheiMst.setSubj(subj[i]);
							memIpheiMst.setIpheiGubunCD(mkucode[i]);
							memIpheiMst.setIpheiKindMstCD(ipKind);
							memIpheiMst.setIpheiKindDtlCD("00");
							memIpheiMst.setmFstName(pFirstName);
							memIpheiMst.setmLstName("");
							memIpheiMst.setSchoolName(pSchool);
							memIpheiMst.setGradeCD(pMemGrade);
							memIpheiMst.setBirthYMD(pBirthDay);
							memIpheiMst.setSexCD(pSex);
							memIpheiMst.setTel(tel);
							memIpheiMst.setZip("");
							memIpheiMst.setAddr(pAddr);
							memIpheiMst.setFstYMD(fstDay[i]);
							memIpheiMst.setFstYoil(String.valueOf(JeiCommonUtils.getWeekNum(fstDay[i])));
							memIpheiMst.setFstEmpKey(fstClass[i]);
							memIpheiMst.setFstEmpName(empMap.get("empName").toString());
							memIpheiMst.setFstDepid1(empMap.get("depid1").toString());
							memIpheiMst.setFstDepid2("00");
							memIpheiMst.setSndYMD("");
							memIpheiMst.setSndYoil("");
							memIpheiMst.setSndEmpKey("");
							memIpheiMst.setSndEmpName("");
							memIpheiMst.setSndDepid1("");
							memIpheiMst.setSndDepid2("");
							memIpheiMst.setJisaCD(jisaCD);
							memIpheiMst.setPayKindCD("D");
							memIpheiMst.setIpheiPathCD(pIpGuide);
							memIpheiMst.setjGradeCD(mjGrade[i]);
							memIpheiMst.setSetCnt("1");
							memIpheiMst.setgFstName(pGfirstname);
							memIpheiMst.setgLstName("");
							memIpheiMst.setmEmail(email);
							memIpheiMst.setgEmail(gEmail);
							memIpheiMst.setmPhone(phone);
							memIpheiMst.setgPhone("");
							memIpheiMst.setePhone(ePhone);
							memIpheiMst.setBranchCD(jisaCD+pIpheiDepid);
							memIpheiMst.setBanyungCD(0);
							memIpheiMst.setBanyungDate(null);
							memIpheiMst.setBanyungID("");
							memIpheiMst.setRegID(regID);
							
							ipheiRepository.insertMemIpheiMst(memIpheiMst);
							
							
							MemIpgumMst memIpgumMst = new MemIpgumMst();
							memIpgumMst.setIpgumYMD(ipheiDay);
							memIpgumMst.setMkey(mkey);
							memIpgumMst.setSubj(subj[i]);
							memIpgumMst.setIpgumGubunCD(mkucode[i]);
							memIpgumMst.setIpgumKindMstCD(ipKind);
							memIpgumMst.setIpgumKindDtlCD("00");
							memIpgumMst.setmFirstName(pFirstName);
							memIpgumMst.setmLastName("");
							memIpgumMst.setExistCD("1");
							memIpgumMst.setPayKindCD("D");
							memIpgumMst.setEmpKey(fstClass[i]);
							memIpgumMst.setEmpName(empMap.get("empName").toString());
							memIpgumMst.setDepid1(empMap.get("depid1").toString());
							memIpgumMst.setDepid2("00");
							memIpgumMst.setIpheibiFree(mIpheibi);
							memIpgumMst.setWolheibiFree(mWolheibi);
							memIpgumMst.setIpheibi(pOrgIpheibi);
							memIpgumMst.setWolheibi(pOrgWolheibi);
							memIpgumMst.setEnterwol(pEnterwol);
							memIpgumMst.setExpireYMD(exday);
							memIpgumMst.setBranchCD(jisaCD+pIpheiDepid);
							memIpgumMst.setBanyungCD(0);
							memIpgumMst.setBanyungDate(null);
							memIpgumMst.setBanyungID("");
							memIpgumMst.setRegID(regID);
							
							ipheiRepository.insertMemIpgumMst(memIpgumMst);
							
							if(!"PS".equals(subj[i]) && !"ER".equals(subj[i])){
								if("03".equals(ipKind)){
									//EXEC GBMUJIN..MUJIN_IPHEI_JINDO_SETTING @JISA,@FSTDAY1,@IPHEIDAY,@OMRDATE,@MKEY,@SUBJ
								}else{
									if("X".equals(mjGrade[i])){
										//EXECUTE BOKHEI_MUJIN_INSERT  @MKEY,@SUBJ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@HUHEIDAY,@WORKID
									}else{
										/*
										 * --복회진단일 경우 퇴회월 이후의 진도 삭제
										 *	INSERT JINDO_BOK_C(IPHEIDAY, SUBJ, MKEY, YY, MM, WEEK, SORT, CYOIL, JSET, IND, WORKDAY)
										 *	SELECT @IPHEIDAY,SUBJ, MKEY, YY, MM, WEEK, SORT, CYOIL, JSET, IND,GETDATE()
									 	 *	FROM JINDO WITH(NOLOCK) WHERE MKEY=@MKEY AND SUBJ=@SUBJ 
										 *
										 * DELETE JINDO WHERE MKEY=@MKEY AND SUBJ=@SUBJ AND YY+MM>@HUHEIYY+@HUHEIMM
										 * 
										 * 
										 */
									}
								}
							}else{
								//EXECUTE MUJIN_PS_INSERT  @MKUCODE, @MKEY ,@SUBJ,@MJDGRD ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
							}
							
							successCnt++;
						}
					}else{
					//타과목 신입
						
						int yoil1 = JeiCommonUtils.getWeekNum(fstDay[i]);
						
						Map<String,Object> map = new HashMap<>();
						map.put("mkey",mkey);
						map.put("subj",subj[i]);
						
						boolean memCheckFlag = ipheiRepository.isExistsMemSubjMst(map);
						
						if(!memCheckFlag){
							
							String exday = JeiCommonUtils.getLastDateOfMonth(ipheiDay);
							
							Map<String,Object> empMap = ipheiRepository.selectComEmpMst(fstClass[i]);
							
							int mIpheibi = Integer.parseInt(orgIpheibi[i]) - Integer.parseInt(ipheibi[i]);
							int mWolheibi = Integer.parseInt(orgWolheibi[i]) - Integer.parseInt(wolheibi[i]);
							
							MemSubjMst memSubjMst = new MemSubjMst();
							memSubjMst.setMkey(mkey);
							memSubjMst.setSubj(subj[i]);
							memSubjMst.setFstEmpKey(fstClass[i]);
							memSubjMst.setFstDepid1(empMap.get("depid1").toString());
							memSubjMst.setFstDepid2("00");
							memSubjMst.setFstYoil(String.valueOf(yoil1));
							memSubjMst.setSndEmpKey("");
							memSubjMst.setSndDepid1("");
							memSubjMst.setSndDepid2("");
							memSubjMst.setSndYoil("");
							memSubjMst.setBfFstEmpKey("");
							memSubjMst.setBfFstDepid1("");
							memSubjMst.setBfFstDepid2("");
							memSubjMst.setBfFstYoil("");
							memSubjMst.setBfSndEmpKey("");
							memSubjMst.setBfSDepid1("");
							memSubjMst.setBfSDepid2("");
							memSubjMst.setBfSYoil("");
							memSubjMst.setJisaCD(jisaCD);
							memSubjMst.setStateCD("1");
							memSubjMst.setIpheiYMD(ipheiDay);
							memSubjMst.setBokheiYMD("");
							memSubjMst.setHuheiYMD("");
							memSubjMst.setExpireYMD(exday);
							memSubjMst.setIpgumYMD("");
							memSubjMst.setjGradeCD(mjGrade[i]);
							memSubjMst.setTaGubunCD("");
							memSubjMst.setMkeyKindCD("01");
							memSubjMst.setIpheiPathCD(pIpGuide);
							memSubjMst.setPayKindCD("D");
							memSubjMst.setSetCnt("1");
							memSubjMst.setBfSetCnt("");
							memSubjMst.setWeekCnt("1");
							memSubjMst.setBfWeekCnt("");
							memSubjMst.setMngSortCD("1");
							memSubjMst.setRegID(regID);
							memSubjMst.setUpdID(regID);
							
							ipheiRepository.insertMemSubjMst(memSubjMst);
							
							
							if(!memInsertCheckFlag){
								//백업
								Map<String,Object> memMap = new HashMap<>();
								memMap.put("mkey",mkey);
								
								MemMst memInfo = ipheiRepository.findMemMstById(memMap);
															
								MemMstHis memMstHis = new MemMstHis();
								memMstHis.setMkey(memInfo.getMkey());
								memMstHis.setmFstName(memInfo.getmFstName());
								memMstHis.setmLstName(memInfo.getmLstName());
								memMstHis.setSchoolName(memInfo.getSchoolName());
								memMstHis.setGradeCD(memInfo.getGradeCD());
								memMstHis.setBirthYMD(memInfo.getBirthYMD());
								memMstHis.setSexCD(memInfo.getSexCD());
								memMstHis.setZip(memInfo.getZip());
								memMstHis.setAddr(memInfo.getAddr());
								memMstHis.setTel(memInfo.getTel());
								memMstHis.setgFstName(memInfo.getgFstName());
								memMstHis.setgLstName(memInfo.getgLstName());
								memMstHis.setmEmail(memInfo.getmEmail());
								memMstHis.setgEmail(memInfo.getgEmail());
								memMstHis.setmPhone(memInfo.getmPhone());
								memMstHis.setgPhone(memInfo.getgPhone());
								memMstHis.setePhone(memInfo.getePhone());
								memMstHis.setSpecialComment(memInfo.getSpecialComment());
								memMstHis.setRegDate(memInfo.getRegDate());
								memMstHis.setRegID(memInfo.getRegID());
								memMstHis.setUpdID(regID);
								memMstHis.setUpdCD("10");
								
								ipheiRepository.insertMemMstHis(memMstHis);
								//memMst 업데이트
								MemMst memMst = new MemMst();
								memMst.setmFstName(pFirstName);
								memMst.setmLstName("");
								memMst.setSchoolName(pSchool);
								memMst.setGradeCD(pMemGrade);
								memMst.setBirthYMD(pBirthDay);
								memMst.setSexCD(pSex);
								memMst.setZip("");
								memMst.setAddr(pAddr);
								memMst.setTel(tel);
								memMst.setgFstName(pGfirstname);
								memMst.setgLstName("");
								memMst.setmEmail(email);
								memMst.setgEmail(gEmail);
								memMst.setmPhone(phone);
								memMst.setgPhone("");
								memMst.setePhone(ePhone);
								memMst.setSpecialComment(specialComment);
								memMst.setUpdID(regID);
								
								ipheiRepository.updateMemMst(memMst);
							}
							
							MemIpheiMst memIpheiMst = new MemIpheiMst();
							memIpheiMst.setIpheiYMD(ipheiDay);
							memIpheiMst.setMkey(mkey);
							memIpheiMst.setSubj(subj[i]);
							memIpheiMst.setIpheiGubunCD(mkucode[i]);
							memIpheiMst.setIpheiKindMstCD(ipKind);
							memIpheiMst.setIpheiKindDtlCD("00");
							memIpheiMst.setmFstName(pFirstName);
							memIpheiMst.setmLstName("");
							memIpheiMst.setSchoolName(pSchool);
							memIpheiMst.setGradeCD(pMemGrade);
							memIpheiMst.setBirthYMD(pBirthDay);
							memIpheiMst.setSexCD(pSex);
							memIpheiMst.setTel(tel);
							memIpheiMst.setZip("");
							memIpheiMst.setAddr(pAddr);
							memIpheiMst.setFstYMD(fstDay[i]);
							memIpheiMst.setFstYoil(String.valueOf(JeiCommonUtils.getWeekNum(fstDay[i])));
							memIpheiMst.setFstEmpKey(fstClass[i]);
							memIpheiMst.setFstEmpName(empMap.get("empName").toString());
							memIpheiMst.setFstDepid1(empMap.get("depid1").toString());
							memIpheiMst.setFstDepid2("00");
							memIpheiMst.setSndYMD("");
							memIpheiMst.setSndYoil("");
							memIpheiMst.setSndEmpKey("");
							memIpheiMst.setSndEmpName("");
							memIpheiMst.setSndDepid1("");
							memIpheiMst.setSndDepid2("");
							memIpheiMst.setJisaCD(jisaCD);
							memIpheiMst.setPayKindCD("D");
							memIpheiMst.setIpheiPathCD(pIpGuide);
							memIpheiMst.setjGradeCD(mjGrade[i]);
							memIpheiMst.setSetCnt("1");
							memIpheiMst.setgFstName(pGfirstname);
							memIpheiMst.setgLstName("");
							memIpheiMst.setmEmail(email);
							memIpheiMst.setgEmail(gEmail);
							memIpheiMst.setmPhone(phone);
							memIpheiMst.setgPhone("");
							memIpheiMst.setePhone(ePhone);
							memIpheiMst.setBranchCD(jisaCD+pIpheiDepid);
							memIpheiMst.setBanyungCD(0);
							memIpheiMst.setBanyungDate(null);
							memIpheiMst.setBanyungID("");
							memIpheiMst.setRegID(regID);
							
							ipheiRepository.insertMemIpheiMst(memIpheiMst);
							
							MemIpgumMst memIpgumMst = new MemIpgumMst();
							memIpgumMst.setIpgumYMD(ipheiDay);
							memIpgumMst.setMkey(mkey);
							memIpgumMst.setSubj(subj[i]);
							memIpgumMst.setIpgumGubunCD(mkucode[i]);
							memIpgumMst.setIpgumKindMstCD(ipKind);
							memIpgumMst.setIpgumKindDtlCD("00");
							memIpgumMst.setmFirstName(pFirstName);
							memIpgumMst.setmLastName("");
							memIpgumMst.setExistCD("1");
							memIpgumMst.setPayKindCD("D");
							memIpgumMst.setEmpKey(fstClass[i]);
							memIpgumMst.setEmpName(empMap.get("empName").toString());
							memIpgumMst.setDepid1(empMap.get("depid1").toString());
							memIpgumMst.setDepid2("00");
							memIpgumMst.setIpheibiFree(mIpheibi);
							memIpgumMst.setWolheibiFree(mWolheibi);
							memIpgumMst.setIpheibi(pOrgIpheibi);
							memIpgumMst.setWolheibi(pOrgWolheibi);
							memIpgumMst.setEnterwol(pEnterwol);
							memIpgumMst.setExpireYMD(exday);
							memIpgumMst.setBranchCD(jisaCD+pIpheiDepid);
							memIpgumMst.setBanyungCD(0);
							memIpgumMst.setBanyungDate(null);
							memIpgumMst.setBanyungID("");
							memIpgumMst.setRegID(regID);
							
							ipheiRepository.insertMemIpgumMst(memIpgumMst);						
							
							
							if(!"PS".equals(subj[i]) && !"ER".equals(subj[i])){
								if("03".equals(ipKind)){
									//EXEC GBMUJIN..MUJIN_IPHEI_JINDO_SETTING @JISA,@FSTDAY1,@IPHEIDAY,@OMRDATE,@MKEY,@SUBJ
								}else{
									if("X".equals(mjGrade[i])){
										//EXECUTE MUJIN_INSERT  @MKEY ,@SUBJ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
									}
								}
							}else{
								//EXECUTE MUJIN_PS_INSERT  @MKUCODE, @MKEY ,@SUBJ,@MJDGRD ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
							}
							
							successCnt++;
						}
					}
					
				}
			}
		}
		
		resultMap.put("successCnt", successCnt);
		resultMap.put("mkey", mkey);
		resultMap.put("gotoSubj", gotoSubj);
		resultMap.put("gotoMjGrade", gotoMjGrade);
		
		return resultMap;
		
	}
	
}
