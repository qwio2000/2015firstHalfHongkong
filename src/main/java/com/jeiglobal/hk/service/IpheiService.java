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
import com.jeiglobal.hk.domain.common.*;
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
		//임의로 넣음 처방연결때문에
		String gotoSkey = null;
				
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
					//임시로넣음 처방연결
					gotoSkey = fstClass[i];
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
									mujinIpheiJindoSetting(jisaCD,fstDay[i],ipheiDay,omrDate,mkey,subj[i]);
								}else{
									if("X".equals(mjGrade[i])){
										//EXECUTE MUJIN_INSERT  @MKEY ,@SUBJ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
										mujinInsert(mkey,subj[i],ipheiDay,fstDay[i],"1",jisaCD,regID);
									}
								}
							}else{
								//EXECUTE MUJIN_PS_INSERT  @MKUCODE, @MKEY ,@SUBJ,@MJDGRD ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
								mujinPsInsert(mkucode[i],mkey,subj[i],mjGrade[i],ipheiDay,fstDay[i],"1",jisaCD,regID);
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
									mujinIpheiJindoSetting(jisaCD,fstDay[i],ipheiDay,omrDate,mkey,subj[i]);
								}else{
									if("X".equals(mjGrade[i])){
										//EXECUTE BOKHEI_MUJIN_INSERT  @MKEY,@SUBJ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@HUHEIDAY,@WORKID
										bokheiMujinInsert(mkey,subj[i],ipheiDay,fstDay[i],"1",jisaCD,memBeforSubjInfo.getHuheiYMD(), regID);
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
										Map<String,Object> jindoMap = new HashMap<>();
										jindoMap.put("mkey",mkey);
										jindoMap.put("subj",subj);
										
										MemJindoMst memJindoMst = ipheiRepository.findOneMemJindoMst(jindoMap);
											
										if(memJindoMst != null){
											MemJindoCngMst memJindoCngMst = new MemJindoCngMst();
											memJindoCngMst.setCngYMD(ipheiDay);
											memJindoCngMst.setCngGubunCD("3");
											memJindoCngMst.setCngOptCD("2");
											memJindoCngMst.setCngSayuCD("");
											memJindoCngMst.setMkey(mkey);
											memJindoCngMst.setSubj(subj[i]);
											memJindoCngMst.setCngJset1("");
											memJindoCngMst.setCngJset2("");
											memJindoCngMst.setCngJset3("");
											memJindoCngMst.setCngJset4("");
											memJindoCngMst.setCngJset5("");
											memJindoCngMst.setSetCnt("1");
											memJindoCngMst.setYy(memJindoMst.getYy());
											memJindoCngMst.setMm(memJindoMst.getMm());
											memJindoCngMst.setWk(memJindoMst.getWk());
											memJindoCngMst.setSort(memJindoMst.getSort());
											memJindoCngMst.setJset(memJindoMst.getJset());
											memJindoCngMst.setYoil(memJindoMst.getYoil());
											memJindoCngMst.setRegID(regID);
											
											ipheiRepository.insertMemJindoCngMst(memJindoCngMst);
											
											List<MemJindoMst> memJindoList = ipheiRepository.findMemJindoMst(jindoMap);
											
											for (MemJindoMst memJindoMstInfo : memJindoList) {
												MemJindoCngDtlBefore memJindoCngDtlBefore = new MemJindoCngDtlBefore();
												memJindoCngDtlBefore.setCngYMD(ipheiDay);
												memJindoCngDtlBefore.setCngGubunCD("3");
												memJindoCngDtlBefore.setCngOptCD("2");
												memJindoCngDtlBefore.setCngSayuCD("");
												memJindoCngDtlBefore.setMkey(mkey);
												memJindoCngDtlBefore.setSubj(subj[i]);
												memJindoCngDtlBefore.setYy(memJindoMstInfo.getYy());
												memJindoCngDtlBefore.setMm(memJindoMstInfo.getMm());
												memJindoCngDtlBefore.setWk(memJindoMstInfo.getWk());
												memJindoCngDtlBefore.setSort(memJindoMstInfo.getSort());
												memJindoCngDtlBefore.setJset(memJindoMstInfo.getJset());
												memJindoCngDtlBefore.setYoil(memJindoMstInfo.getYoil());
												memJindoCngDtlBefore.setInd(memJindoMstInfo.getInd());
												
												ipheiRepository.insertMemJindoCngDtlBefore(memJindoCngDtlBefore);
											}
											
											if(Strings.isNullOrEmpty(memBeforSubjInfo.getHuheiYMD())){
												jindoMap.put("hYY",memBeforSubjInfo.getHuheiYMD().substring(0,4));
												jindoMap.put("hmm",memBeforSubjInfo.getHuheiYMD().substring(4,6));
												
												ipheiRepository.deleteMemJindoMstWhereYyMm(jindoMap);
											}
										}
										
									}
								}
							}else{
								//EXECUTE MUJIN_PS_INSERT  @MKUCODE, @MKEY ,@SUBJ,@MJDGRD ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
								mujinPsInsert(mkucode[i],mkey,subj[i],mjGrade[i],ipheiDay,fstDay[i],"1",jisaCD,regID);
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
									mujinIpheiJindoSetting(jisaCD,fstDay[i],ipheiDay,omrDate,mkey,subj[i]);
								}else{
									if("X".equals(mjGrade[i])){
										//EXECUTE MUJIN_INSERT  @MKEY ,@SUBJ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
										mujinInsert(mkey,subj[i],ipheiDay,fstDay[i],"1",jisaCD,regID);
									}
								}
							}else{
								//EXECUTE MUJIN_PS_INSERT  @MKUCODE, @MKEY ,@SUBJ,@MJDGRD ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
								mujinPsInsert(mkucode[i],mkey,subj[i],mjGrade[i],ipheiDay,fstDay[i],"1",jisaCD,regID);
							}
							
							successCnt++;
						}
					}
					
				}
			}
		}
		
		if(Strings.isNullOrEmpty(gotoSubj) && Strings.isNullOrEmpty(gotoSkey)){
			gotoSubj = subj[0];
			gotoSkey = fstClass[0];
		}
		
		resultMap.put("successCnt", successCnt);
		resultMap.put("mkey", mkey);
		resultMap.put("gotoSubj", gotoSubj);
		resultMap.put("gotoMjGrade", gotoMjGrade);
		resultMap.put("gotoSkey",gotoSkey);
		
		return resultMap;
		
	}
	/**
	 *  MUJIN_PS_INSERT  @MKUCODE, @MKEY ,@SUBJ,@MJDGRD ,@IPHEIDAY,@FSTDAY1,@FSTDAY2,@BULSU,@JISA,@WORKID
	 * @param mkucode
	 * @param mkey
	 * @param subj
	 * @param mjdgrd
	 * @param ipheiDay
	 * @param fstDay
	 * @param bulsu
	 * @param jisaCD
	 * @param regID
	 */
	private void mujinPsInsert(String mkucode,String mkey,String subj,String mjdgrd
			,String ipheiDay,String fstDay,String bulsu,String jisaCD,String regID){
		
		
		String nDate = JeiCommonUtils.convertDay(fstDay);
		String omrDay = String.valueOf(JeiCommonUtils.getWeekNum(nDate));
		
		String startSet = "";
		String huheiDay = "";
		
		if("PS".equals(subj)){
			if("01".equals(mkucode)){
				if("X".equals(mjdgrd)){
					startSet = "D";
				}else{
					startSet = mjdgrd;
				}
			}else if("02".equals(mkucode)){
				Map<String,Object> map = new HashMap<>();
				map.put("mkey",mkey);
				map.put("subj",subj);
				MemSubjMst memSubjMst = ipheiRepository.findMemSubjMst(map);
				if(memSubjMst != null && Strings.isNullOrEmpty(memSubjMst.getHuheiYMD())){
					huheiDay = memSubjMst.getHuheiYMD();
				}
			}else if("03".equals(mkucode)){
				if("X".equals(mjdgrd)){
					startSet = "D";
				}else{
					startSet = mjdgrd;
				}
			}
		}else{
			if("01".equals(mkucode)){
				if("X".equals(mjdgrd)){
					startSet = "C";
				}else{
					startSet = mjdgrd;
				}
			}else if("02".equals(mkucode)){
				Map<String,Object> map = new HashMap<>();
				map.put("mkey",mkey);
				map.put("subj",subj);
				MemSubjMst memSubjMst = ipheiRepository.findMemSubjMst(map);
				if(memSubjMst != null && Strings.isNullOrEmpty(memSubjMst.getHuheiYMD())){
					huheiDay = memSubjMst.getHuheiYMD();
				}
			}else if("03".equals(mkucode)){
				if("X".equals(mjdgrd)){
					startSet = "C";
				}else{
					startSet = mjdgrd;
				}
			}
		}
		
		if("01".equals(mkucode)  || "03".equals(mkucode)){
			Map<String,Object> map = new HashMap<>();
			map.put("jisaCD",jisaCD);
			map.put("subj",subj);
			map.put("startSet",startSet);
			List<String> casNSetList = ipheiRepository.selectCAS_NSETbyNSys2(map);

			int cnt = 1;
			
			for (String casNSet : casNSetList) {
				
				Map<String,Object> map1 = new HashMap<>();
				map1.put("nDate",nDate);
				map1.put("omrDay",omrDay);
				
				JNDate jnDate = commonRepository.selectJNDate(map1);
				
				MemJindoMst memJindoMst = new MemJindoMst();
				memJindoMst.setSubj(subj);
				memJindoMst.setMkey(mkey);
				memJindoMst.setYy(jnDate.getPYEAR());
				memJindoMst.setMm(jnDate.getPWOL());
				memJindoMst.setWk(jnDate.getWEEK());
				memJindoMst.setSort("1");
				memJindoMst.setYoil(omrDay);
				memJindoMst.setJset(casNSet);
				memJindoMst.setYymmwk(jnDate.getPYEAR()+jnDate.getPWOL()+jnDate.getWEEK());
				
				if(cnt<= 2){
					memJindoMst.setInd("R");
				}else{
					memJindoMst.setInd("");
				}
				
				ipheiRepository.insertMemJindoMst(memJindoMst);
				
				nDate = JeiCommonUtils.getDiffDay("yyyyMMdd",nDate,7);
				cnt++;
			}
			
			map.clear();
			map.put("mkey",mkey);
			map.put("subj",subj);
			
			MemJindoMst memJindoMst =  ipheiRepository.findOneMemJindoMst(map);
			
			Map<String,Object> delMap = new HashMap<>();
			delMap.put("mkey",mkey);
			delMap.put("subj",subj);
			delMap.put("cngYMD",ipheiDay);
			delMap.put("cngGubunCD","3");
			delMap.put("cngOptCD","2");
			
			ipheiRepository.deleteMemJindoCngMst(delMap);
			ipheiRepository.deleteMemJindoCngDtlAfter(delMap);
			
			MemJindoCngMst memJindoCngMst = new MemJindoCngMst();
			memJindoCngMst.setCngYMD(ipheiDay);
			memJindoCngMst.setCngGubunCD("3");
			memJindoCngMst.setCngOptCD("2");
			memJindoCngMst.setCngSayuCD("");
			memJindoCngMst.setMkey(mkey);
			memJindoCngMst.setSubj(subj);
			memJindoCngMst.setCngJset1("");
			memJindoCngMst.setCngJset2("");
			memJindoCngMst.setCngJset3("");
			memJindoCngMst.setCngJset4("");
			memJindoCngMst.setCngJset5("");
			memJindoCngMst.setSetCnt(bulsu);
			memJindoCngMst.setYy(memJindoMst.getYy());
			memJindoCngMst.setMm(memJindoMst.getMm());
			memJindoCngMst.setWk(memJindoMst.getWk());
			memJindoCngMst.setSort(memJindoMst.getSort());
			memJindoCngMst.setJset(memJindoMst.getJset());
			memJindoCngMst.setYoil(memJindoMst.getYoil());
			memJindoCngMst.setRegID(regID);
			
			ipheiRepository.insertMemJindoCngMst(memJindoCngMst);
			
			MemJindoCngDtlAfter memJindoCngDtlAfter = new MemJindoCngDtlAfter();
			memJindoCngDtlAfter.setCngYMD(ipheiDay);
			memJindoCngDtlAfter.setCngGubunCD("3");
			memJindoCngDtlAfter.setCngOptCD("2");
			memJindoCngDtlAfter.setCngSayuCD("");
			memJindoCngDtlAfter.setMkey(mkey);
			memJindoCngDtlAfter.setSubj(subj);
			memJindoCngDtlAfter.setYy(memJindoMst.getYy());
			memJindoCngDtlAfter.setMm(memJindoMst.getMm());
			memJindoCngDtlAfter.setWk(memJindoMst.getWk());
			memJindoCngDtlAfter.setSort(memJindoMst.getSort());
			memJindoCngDtlAfter.setYoil(memJindoMst.getYoil());
			memJindoCngDtlAfter.setJset(memJindoMst.getJset());
			memJindoCngDtlAfter.setInd(memJindoMst.getInd());
			
			ipheiRepository.insertMemJindoCngDtlAfter(memJindoCngDtlAfter);
			
		}else if("02".equals(mkucode)){
			Map<String,Object> map = new HashMap<>();
			map.put("mkey",mkey);
			map.put("subj",subj);
			map.put("cngYMD",ipheiDay);
			map.put("cngGubunCD","3");
			map.put("cngOptCD","6");
			
			MemJindoCngMst memJindoCngMst = ipheiRepository.findMemJindoCngMst(map);
			
			if(memJindoCngMst != null && !Strings.isNullOrEmpty(memJindoCngMst.getCngYMD())){
				ipheiRepository.deleteMemJindoCngMst(map);
				ipheiRepository.deleteMemJindoCngDtlAfter(map);
			}
			
			ipheiRepository.deleteMemJindoCngDtlBefore(map);
			
			
			Map<String,Object> map1 = new HashMap<>();
			map1.put("mkey",mkey);
			map1.put("subj",subj);
			
			List<MemJindoMst> memJindoMList = ipheiRepository.findMemJindoMst(map1);
			
			for (MemJindoMst memJindoMst2 : memJindoMList) {
				
				MemJindoCngDtlBefore memJindoCngDtlBefore = new MemJindoCngDtlBefore();
				memJindoCngDtlBefore.setCngYMD(ipheiDay);
				memJindoCngDtlBefore.setCngGubunCD("3");
				memJindoCngDtlBefore.setCngOptCD("6");
				memJindoCngDtlBefore.setCngSayuCD("");
				memJindoCngDtlBefore.setMkey(mkey);
				memJindoCngDtlBefore.setSubj(subj);
				memJindoCngDtlBefore.setYy(memJindoMst2.getYy());
				memJindoCngDtlBefore.setMm(memJindoMst2.getMm());
				memJindoCngDtlBefore.setWk(memJindoMst2.getWk());
				memJindoCngDtlBefore.setSort(memJindoMst2.getSort());
				memJindoCngDtlBefore.setYoil(memJindoMst2.getYoil());
				memJindoCngDtlBefore.setJset(memJindoMst2.getJset());
				memJindoCngDtlBefore.setInd(memJindoMst2.getInd());
				
				ipheiRepository.insertMemJindoCngDtlBefore(memJindoCngDtlBefore);
			}
			
			String hYY = "";
			String hmm = "";
			String hWeek = "";
			
			if(!Strings.isNullOrEmpty(huheiDay)){
				if(huheiDay.indexOf("-") != -1){
					String t[] = huheiDay.split("-");
					hYY = t[0];
					hmm = t[1];
					hWeek = t[2];
				}
			}
			
			Map<String,Object> map2 = new HashMap<>();
			
			map2.put("mkey",mkey);
			map2.put("subj",subj);
			
			String bYoil = "";
			
			MemSubjMst memSubjMst = ipheiRepository.findMemSubjMst(map2);
			if(memSubjMst != null && !Strings.isNullOrEmpty(memSubjMst.getFstYoil()) && Strings.isNullOrEmpty(memSubjMst.getBfFstYoil())){
				bYoil = memSubjMst.getFstYoil();
			}else if(memSubjMst != null && !Strings.isNullOrEmpty(memSubjMst.getFstYoil()) && !Strings.isNullOrEmpty(memSubjMst.getBfFstYoil())){
				bYoil = memSubjMst.getBfFstYoil();
			}
			
			map2.clear();
			
			map2.put("hYY",hYY);
			map2.put("hmm",hmm);
			map2.put("bYoil",bYoil);
			
			String chkYY = "";
			String chkmm = "";
			
			JNDate jnDate1 = commonRepository.findOneJNDateOrderByYearPwolWeek(map2);
			
			if(jnDate1 != null){
				chkYY = jnDate1.getPYEAR();
				chkmm = jnDate1.getPWOL();
			}
			
			map2.clear();
			
			map2.put("chkYY",chkYY);
			map2.put("chkmm",chkmm);
			
			Map<String,Object> map3 = commonRepository.selectOneJNDateGroupByPYearPWolWeek(map2);
			
			if(map3 != null && !map3.isEmpty()){
				hYY = map3.get("PYEAR").toString();
				hmm = map3.get("PWOL").toString();
				hWeek = map3.get("WEEK").toString();
			}
			
			map2.clear();
			
			map2.put("mkey",mkey);
			map2.put("subj",subj);
			map2.put("hYY",hYY);
			map2.put("hmm",hmm);
			map2.put("hWeek",hWeek);
			
			List<MemJindoMst> memJindoMstList = ipheiRepository.findMemJindoMstOrderByYyMmWkSort(map2);
			
			ipheiRepository.deleteMemJindoMst(map2);
			
			for (MemJindoMst memJM : memJindoMstList) {
				
				map2.clear();
				map2.put("nDate",nDate);
				
				JNDate jnDate2 = commonRepository.selectJNDate(map2);
				
				nDate = JeiCommonUtils.getDiffDay("yyyyMMdd",nDate,7);
				
				map2.clear();
				map2.put("rYY",jnDate2.getPYEAR());
				map2.put("rmm",jnDate2.getPWOL());
				map2.put("rWeek",jnDate2.getWEEK());
				map2.put("rSort","1");
				
				ipheiRepository.deleteMemJindoMstWhereYyMmWeekSort(map2);
				
				MemJindoMst memJindoMstInput = new MemJindoMst();
				memJindoMstInput.setSubj(subj);
				memJindoMstInput.setMkey(mkey);
				memJindoMstInput.setYy(jnDate2.getPYEAR());
				memJindoMstInput.setMm(jnDate2.getPWOL());
				memJindoMstInput.setWk(jnDate2.getWEEK());
				memJindoMstInput.setSort("1");
				memJindoMstInput.setYoil(jnDate2.getYOIL());
				memJindoMstInput.setJset(memJM.getJset());
				memJindoMstInput.setInd("");
				memJindoMstInput.setYymmwk(jnDate2.getPYEAR()+jnDate2.getPWOL()+jnDate2.getWEEK());
				
				ipheiRepository.insertMemJindoMst(memJindoMstInput);
				
			}
			
			map2.clear();
			map2.put("mkey",mkey);
			map2.put("subj",subj);
			
			MemJindoMst memJindoInfo =  ipheiRepository.findOneMemJindoMst(map2);
			
			
			Map<String,Object> delMap = new HashMap<>();
			delMap.put("mkey",mkey);
			delMap.put("subj",subj);
			delMap.put("cngYMD",ipheiDay);
			delMap.put("cngGubunCD","3");
			delMap.put("cngOptCD","6");
			
			ipheiRepository.deleteMemJindoCngMst(delMap);
			ipheiRepository.deleteMemJindoCngDtlAfter(delMap);
			
			MemJindoCngMst memJindoCngMstInput = new MemJindoCngMst();
			memJindoCngMstInput.setCngYMD(ipheiDay);
			memJindoCngMstInput.setCngGubunCD("3");
			memJindoCngMstInput.setCngOptCD("6");
			memJindoCngMstInput.setCngSayuCD("");
			memJindoCngMstInput.setMkey(mkey);
			memJindoCngMstInput.setSubj(subj);
			memJindoCngMstInput.setCngJset1("");
			memJindoCngMstInput.setCngJset2("");
			memJindoCngMstInput.setCngJset3("");
			memJindoCngMstInput.setCngJset4("");
			memJindoCngMstInput.setCngJset5("");
			memJindoCngMstInput.setSetCnt(bulsu);
			memJindoCngMstInput.setYy(memJindoInfo.getYy());
			memJindoCngMstInput.setMm(memJindoInfo.getMm());
			memJindoCngMstInput.setWk(memJindoInfo.getWk());
			memJindoCngMstInput.setSort(memJindoInfo.getSort());
			memJindoCngMstInput.setJset(memJindoInfo.getJset());
			memJindoCngMstInput.setYoil(memJindoInfo.getYoil());
			memJindoCngMstInput.setRegID(regID);
			
			ipheiRepository.insertMemJindoCngMst(memJindoCngMstInput);
			
			
			MemJindoCngDtlAfter memJindoCngDtlAfter = new MemJindoCngDtlAfter();
			memJindoCngDtlAfter.setCngYMD(ipheiDay);
			memJindoCngDtlAfter.setCngGubunCD("3");
			memJindoCngDtlAfter.setCngOptCD("6");
			memJindoCngDtlAfter.setCngSayuCD("");
			memJindoCngDtlAfter.setMkey(mkey);
			memJindoCngDtlAfter.setSubj(subj);
			memJindoCngDtlAfter.setYy(memJindoInfo.getYy());
			memJindoCngDtlAfter.setMm(memJindoInfo.getMm());
			memJindoCngDtlAfter.setWk(memJindoInfo.getWk());
			memJindoCngDtlAfter.setSort(memJindoInfo.getSort());
			memJindoCngDtlAfter.setYoil(memJindoInfo.getYoil());
			memJindoCngDtlAfter.setJset(memJindoInfo.getJset());
			memJindoCngDtlAfter.setInd(memJindoInfo.getInd());
			
			ipheiRepository.insertMemJindoCngDtlAfter(memJindoCngDtlAfter);
			
		}
		
	}
	
	private void mujinInsert(String mkey,String subj,String ipheiDay,String fstDay,String bulsu,String jisaCD,String regID){
		int cnt = 1;
		String startSet = "";
		
		String nDate = JeiCommonUtils.convertDay(fstDay);
		String omrDay = String.valueOf(JeiCommonUtils.getWeekNum(nDate));
		
		if("KC".equals(subj)){
			startSet = "C";
		}else if("KJ".equals(subj)){
			startSet = "A";
		}else if("KS".equals(subj)){
			startSet = "A";
		}else if("CP".equals(subj)){
			startSet = "C";
		}else if("EP".equals(subj)){
			startSet = "E";
		}else{
			startSet = "A";
		}
		
		Map<String,Object> map = new HashMap<>();
		map.put("jisaCD",jisaCD);
		map.put("subj",subj);
		map.put("startSet",startSet);
		
		List<String> casNSetList = ipheiRepository.selectCAS_NSETbyNSys2UseMujinInsert(map);
		
		for (String casNSet : casNSetList) {
			
			Map<String,Object> map1 = new HashMap<>();
			map1.put("nDate",nDate);
			map1.put("omrDay",omrDay);
			
			JNDate jnDate = commonRepository.selectJNDate(map1);
			
			MemJindoMst memJindoMst = new MemJindoMst();
			memJindoMst.setSubj(subj);
			memJindoMst.setMkey(mkey);
			memJindoMst.setYy(jnDate.getPYEAR());
			memJindoMst.setMm(jnDate.getPWOL());
			memJindoMst.setWk(jnDate.getWEEK());
			memJindoMst.setSort("1");
			memJindoMst.setYoil(omrDay);
			memJindoMst.setJset(casNSet);
			memJindoMst.setYymmwk(jnDate.getPYEAR()+jnDate.getPWOL()+jnDate.getWEEK());
			
			if(cnt<= 2){
				memJindoMst.setInd("R");
			}else{
				memJindoMst.setInd("");
			}
			
			ipheiRepository.insertMemJindoMst(memJindoMst);
			
			nDate = JeiCommonUtils.getDiffDay("yyyyMMdd",nDate,7);
			cnt++;
		}
		
		if(!"KC".equals(subj) && !"KJ".equals(subj)){
			map.clear();
			map.put("nDate",nDate);
			map.put("omrDay",omrDay);
			
			JNDate jnDate = commonRepository.selectJNDate(map);
			MemJindoMst memJindoMst = new MemJindoMst();
			memJindoMst.setSubj(subj);
			memJindoMst.setMkey(mkey);
			memJindoMst.setYy(jnDate.getPYEAR());
			memJindoMst.setMm(jnDate.getPWOL());
			memJindoMst.setWk(jnDate.getWEEK());
			memJindoMst.setSort("1");
			memJindoMst.setYoil(omrDay);
			memJindoMst.setYymmwk(jnDate.getPYEAR()+jnDate.getPWOL()+jnDate.getWEEK());
			memJindoMst.setInd("");
			
			if("KS".equals(subj)){
				memJindoMst.setJset("A999");
			}else if("CP".equals(subj)){
				memJindoMst.setJset("C999");
			}else if("EP".equals(subj)){
				memJindoMst.setJset("E999");
			}else{
				memJindoMst.setJset("A999");
			}
			
			ipheiRepository.insertMemJindoMst(memJindoMst);
		}
		
		nDate = JeiCommonUtils.getDiffDay("yyyyMMdd",nDate,7);
		
		if(!"KC".equals(subj) && !"KJ".equals(subj) && !"KS".equals(subj)){
			map.clear();
			map.put("nDate",nDate);
			map.put("omrDay",omrDay);
			
			JNDate jnDate = commonRepository.selectJNDate(map);
			
			MemJindoMst memJindoMst = new MemJindoMst();
			memJindoMst.setSubj(subj);
			memJindoMst.setMkey(mkey);
			memJindoMst.setYy(jnDate.getPYEAR());
			memJindoMst.setMm(jnDate.getPWOL());
			memJindoMst.setWk(jnDate.getWEEK());
			memJindoMst.setSort("1");
			memJindoMst.setYoil(omrDay);
			memJindoMst.setYymmwk(jnDate.getPYEAR()+jnDate.getPWOL()+jnDate.getWEEK());
			memJindoMst.setInd("");
			
			if("CP".equals(subj)){
				memJindoMst.setJset("C992");
			}else if("EP".equals(subj)){
				memJindoMst.setJset("E992");
			}else if(!"KG".equals(subj)){
				memJindoMst.setJset("A992");
			}
			
			ipheiRepository.insertMemJindoMst(memJindoMst);
		}
		
		map.clear();
		map.put("mkey",mkey);
		map.put("subj",subj);
		
		MemJindoMst memJindoMst = ipheiRepository.findOneMemJindoMst(map);
		
		Map<String,Object> delMap = new HashMap<>();
		delMap.put("mkey",mkey);
		delMap.put("subj",subj);
		delMap.put("cngYMD",ipheiDay);
		delMap.put("cngGubunCD","3");
		delMap.put("cngOptCD","2");
		
		ipheiRepository.deleteMemJindoCngMst(delMap);
		ipheiRepository.deleteMemJindoCngDtlAfter(delMap);
		
		
		MemJindoCngMst memJindoCngMst = new MemJindoCngMst();
		memJindoCngMst.setCngYMD(ipheiDay);
		memJindoCngMst.setCngGubunCD("3");
		memJindoCngMst.setCngOptCD("2");
		memJindoCngMst.setCngSayuCD("");
		memJindoCngMst.setMkey(mkey);
		memJindoCngMst.setSubj(subj);
		memJindoCngMst.setCngJset1("");
		memJindoCngMst.setCngJset2("");
		memJindoCngMst.setCngJset3("");
		memJindoCngMst.setCngJset4("");
		memJindoCngMst.setCngJset5("");
		memJindoCngMst.setSetCnt(bulsu);
		memJindoCngMst.setYy(memJindoMst.getYy());
		memJindoCngMst.setMm(memJindoMst.getMm());
		memJindoCngMst.setWk(memJindoMst.getWk());
		memJindoCngMst.setSort(memJindoMst.getSort());
		memJindoCngMst.setJset(memJindoMst.getJset());
		memJindoCngMst.setYoil(memJindoMst.getYoil());
		memJindoCngMst.setRegID(regID);
		
		ipheiRepository.insertMemJindoCngMst(memJindoCngMst);
		
		MemJindoCngDtlAfter memJindoCngDtlAfter = new MemJindoCngDtlAfter();
		memJindoCngDtlAfter.setCngYMD(ipheiDay);
		memJindoCngDtlAfter.setCngGubunCD("3");
		memJindoCngDtlAfter.setCngOptCD("2");
		memJindoCngDtlAfter.setCngSayuCD("");
		memJindoCngDtlAfter.setMkey(mkey);
		memJindoCngDtlAfter.setSubj(subj);
		memJindoCngDtlAfter.setYy(memJindoMst.getYy());
		memJindoCngDtlAfter.setMm(memJindoMst.getMm());
		memJindoCngDtlAfter.setWk(memJindoMst.getWk());
		memJindoCngDtlAfter.setSort(memJindoMst.getSort());
		memJindoCngDtlAfter.setYoil(memJindoMst.getYoil());
		memJindoCngDtlAfter.setJset(memJindoMst.getJset());
		memJindoCngDtlAfter.setInd(memJindoMst.getInd());
		
		ipheiRepository.insertMemJindoCngDtlAfter(memJindoCngDtlAfter);
	}
	
	private void mujinIpheiJindoSetting(String jisaCD,String fstDay,String ipheiDay,String omrDate,String mkey,String subj){
		
		String omrYmd = JeiCommonUtils.convertDay(ipheiDay);
		String date02 = JeiCommonUtils.getDiffDay("yyyyMMdd",omrYmd,2);
		Map<String,Object> map = new HashMap<>();
		map.put("mkey",mkey);
		map.put("subj",subj);
		map.put("mngSortCD","1");
		map.put("stateCD","1");
		
		MemSubjMst memSubjMst = ipheiRepository.findMemSubjMst(map);
		
		String fstYmd = "";
		int dateYoil02 = JeiCommonUtils.getWeekNum(date02);
		int fstYoil = Integer.parseInt(memSubjMst.getFstYoil());
		
		if(memSubjMst != null && (fstYoil <= dateYoil02 )){
			fstYmd =  JeiCommonUtils.getDiffDay("yyyyMMdd",date02,fstYoil-dateYoil02+7);
		}else{
			fstYmd =  JeiCommonUtils.getDiffDay("yyyyMMdd",date02,fstYoil-dateYoil02);
		}
		
		map.clear();
		map.put("nDate",fstYmd);
		
		JNDate jnDate = commonRepository.selectJNDate(map);
		
		map.clear();
		map.put("hkey",mkey);
		map.put("kwamok",subj);
		map.put("omrDate",omrDate);
		
		List<OmrJinSet> omrJinSetList =  ipheiRepository.findOmrJinSet(map);
		int cnt = 0;
		
		for (OmrJinSet omrJinSet : omrJinSetList) {
			map.clear();
			map.put("nDate",fstYmd);
			
			JNDate jnDate1 = commonRepository.selectJNDate(map);
			
			omrJinSet.setrYY(jnDate1.getPYEAR());
			omrJinSet.setrMM(jnDate1.getPWOL());
			omrJinSet.setrWeek(jnDate1.getWEEK());
			omrJinSet.setrSort("1");
			omrJinSet.setrYoil(String.valueOf(fstYoil));
			
			omrJinSetList.set(cnt,omrJinSet);
			
			fstYmd = JeiCommonUtils.getDiffDay("yyyyMMdd",fstYmd,7);
			cnt++;
		}
		
		
		int checkCnt = 0;
		for (OmrJinSet omrJinSet : omrJinSetList) {
			if(!Strings.isNullOrEmpty(omrJinSet.getrYY()) && !Strings.isNullOrEmpty(omrJinSet.getrMM()) 
					&& !Strings.isNullOrEmpty(omrJinSet.getrWeek()) && !Strings.isNullOrEmpty(omrJinSet.getrYoil())
					&& !Strings.isNullOrEmpty(omrJinSet.getrSet2()) ){
				checkCnt++;
			}
		}
		
		if(cnt == checkCnt){
			map.clear();
			map.put("mkey",mkey);
			map.put("subj",subj);
			map.put("hYY", jnDate.getPYEAR());
			map.put("hmm", jnDate.getPWOL());
			map.put("hWeek", jnDate.getWEEK());
			ipheiRepository.deleteMemJindoMst(map);
			
			for (OmrJinSet omrJinSet : omrJinSetList) {
				MemJindoMst memJindoMst = new MemJindoMst();
				memJindoMst.setMkey(mkey);
				memJindoMst.setSubj(subj);
				memJindoMst.setYy(omrJinSet.getrYY());
				memJindoMst.setMm(omrJinSet.getrMM());
				memJindoMst.setWk(omrJinSet.getrWeek());
				memJindoMst.setSort(omrJinSet.getrSort());
				memJindoMst.setYoil(omrJinSet.getrYoil());
				memJindoMst.setJset(omrJinSet.getrSet2());
				memJindoMst.setYymmwk(omrJinSet.getrYY()+omrJinSet.getrMM()+omrJinSet.getrWeek());
				memJindoMst.setInd("");
				
				ipheiRepository.insertMemJindoMst(memJindoMst);
			}
		}
	}
	
	private void bokheiMujinInsert(String mkey,String subj,String ipheiDay,String fstDay
			,String bulsu,String jisaCD,String huheiDay,String regID){
		
		
		Map<String,Object> map = new HashMap<>();
		map.put("mkey",mkey);
		map.put("subj",subj);
		map.put("cngYMD",ipheiDay);
		map.put("cngGubunCD","3");
		map.put("cngOptCD","6");
		
		MemJindoCngMst memJindoCngMst = ipheiRepository.findMemJindoCngMst(map);
		
		if(memJindoCngMst != null && !Strings.isNullOrEmpty(memJindoCngMst.getCngYMD())){
			ipheiRepository.deleteMemJindoCngMst(map);
			ipheiRepository.deleteMemJindoCngDtlBefore(map);
			ipheiRepository.deleteMemJindoCngDtlAfter(map);
		}
		
		map.clear();
		map.put("mkey",mkey);
		map.put("subj",subj);
		
		List<MemJindoMst> memJindoMstList = ipheiRepository.findMemJindoMst(map);
		
		for (MemJindoMst memJindoMst : memJindoMstList) {
			MemJindoCngDtlBefore memJindoCngDtlBefore = new MemJindoCngDtlBefore();
			memJindoCngDtlBefore.setCngYMD(ipheiDay);
			memJindoCngDtlBefore.setCngGubunCD("3");
			memJindoCngDtlBefore.setCngOptCD("6");
			memJindoCngDtlBefore.setCngSayuCD("");
			memJindoCngDtlBefore.setMkey(mkey);
			memJindoCngDtlBefore.setSubj(subj);
			memJindoCngDtlBefore.setYy(memJindoMst.getYy());
			memJindoCngDtlBefore.setMm(memJindoMst.getMm());
			memJindoCngDtlBefore.setWk(memJindoMst.getWk());
			memJindoCngDtlBefore.setSort(memJindoMst.getSort());
			memJindoCngDtlBefore.setJset(memJindoMst.getJset());
			memJindoCngDtlBefore.setYoil(memJindoMst.getYoil());
			memJindoCngDtlBefore.setInd(memJindoMst.getInd());
			
			ipheiRepository.insertMemJindoCngDtlBefore(memJindoCngDtlBefore);
		}
		
		String nDate = JeiCommonUtils.convertDay(fstDay);
		
		map.clear();
		map.put("nDate",nDate);
		
		String hYmd[] = huheiDay.split("-");
		
		map.clear();
		map.put("mkey",mkey);
		map.put("subj",subj);
		
		MemSubjMst memSubjMst = ipheiRepository.findMemSubjMst(map);
		
		if(memSubjMst != null && Strings.isNullOrEmpty(memSubjMst.getBfFstYoil())){
			memSubjMst.setBfFstYoil(memSubjMst.getFstYoil());
		}
		
		map.clear();
		map.put("hYY", hYmd[0]);
		map.put("hmm", hYmd[1]);
		map.put("bYoil",memSubjMst.getBfSYoil());
		
		JNDate jnDate  = commonRepository.findOneJNDateOrderByYearPwolWeek(map);
		
		map.clear();
		map.put("chkYY",jnDate.getPYEAR());
		map.put("chkmm",jnDate.getPWOL());
		map.put("chkWeek",jnDate.getWEEK());
		
		Map<String,Object> jnDateMap = commonRepository.selectOneJNDateWhereYWWGroupByPYearPWolWeek(map);
		
		map.clear();
		map.put("mkey", mkey);
		map.put("subj", subj);
		map.put("hYY", jnDateMap.get("PYEAR"));
		map.put("hmm", jnDateMap.get("PWOL"));
		map.put("hWeek", jnDateMap.get("WEEK"));
		
		List<MemJindoMst> tempMemJindoMstList = ipheiRepository.findMemJindoMstOrderByYyMmWkSort(map);
		
		ipheiRepository.deleteMemJindoMst(map);
		
		for (MemJindoMst tempMemJindoMst : tempMemJindoMstList) {
			map.clear();
			map.put("nDate",nDate);
			
			JNDate jNDateR = commonRepository.selectJNDate(map);
			
			nDate = JeiCommonUtils.getDiffDay("yyyyMMdd",nDate,7);
			
			map.clear();
			map.put("rYY",jNDateR.getPYEAR());
			map.put("rmm",jNDateR.getPWOL());
			map.put("rWeek",jNDateR.getWEEK());
			map.put("rSort","1");
			
			ipheiRepository.deleteMemJindoMstWhereYyMmWeekSort(map);
			
			MemJindoMst memJindoMstInput = new MemJindoMst();
			memJindoMstInput.setSubj(subj);
			memJindoMstInput.setMkey(mkey);
			memJindoMstInput.setYy(jNDateR.getPYEAR());
			memJindoMstInput.setMm(jNDateR.getPWOL());
			memJindoMstInput.setWk(jNDateR.getWEEK());
			memJindoMstInput.setSort("1");
			memJindoMstInput.setYoil(jNDateR.getYOIL());
			memJindoMstInput.setJset(tempMemJindoMst.getJset());
			memJindoMstInput.setInd("");
			memJindoMstInput.setYymmwk(jNDateR.getPYEAR()+jNDateR.getPWOL()+jNDateR.getWEEK());
			
			ipheiRepository.insertMemJindoMst(tempMemJindoMst);
		}
		
		
		map.clear();
		map.put("mkey",mkey);
		map.put("subj",subj);
		
		MemJindoMst memJindoMst = ipheiRepository.findOneMemJindoMst(map);
		
		MemJindoCngMst memJindoCngMst1 = new MemJindoCngMst();
		memJindoCngMst1.setCngYMD(ipheiDay);
		memJindoCngMst1.setCngGubunCD("3");
		memJindoCngMst1.setCngOptCD("6");
		memJindoCngMst1.setCngSayuCD("");
		memJindoCngMst1.setMkey(mkey);
		memJindoCngMst1.setSubj(subj);
		memJindoCngMst1.setCngJset1("");
		memJindoCngMst1.setCngJset2("");
		memJindoCngMst1.setCngJset3("");
		memJindoCngMst1.setCngJset4("");
		memJindoCngMst1.setCngJset5("");
		memJindoCngMst1.setSetCnt(bulsu);
		memJindoCngMst1.setYy(memJindoMst.getYy());
		memJindoCngMst1.setMm(memJindoMst.getMm());
		memJindoCngMst1.setWk(memJindoMst.getWk());
		memJindoCngMst1.setSort(memJindoMst.getSort());
		memJindoCngMst1.setJset(memJindoMst.getJset());
		memJindoCngMst1.setYoil(memJindoMst.getYoil());
		memJindoCngMst1.setRegID(regID);
		
		ipheiRepository.insertMemJindoCngMst(memJindoCngMst1);
		
		MemJindoCngDtlAfter memJindoCngDtlAfter = new MemJindoCngDtlAfter();
		memJindoCngDtlAfter.setCngYMD(ipheiDay);
		memJindoCngDtlAfter.setCngGubunCD("3");
		memJindoCngDtlAfter.setCngOptCD("6");
		memJindoCngDtlAfter.setCngSayuCD("");
		memJindoCngDtlAfter.setMkey(mkey);
		memJindoCngDtlAfter.setSubj(subj);
		memJindoCngDtlAfter.setYy(memJindoMst.getYy());
		memJindoCngDtlAfter.setMm(memJindoMst.getMm());
		memJindoCngDtlAfter.setWk(memJindoMst.getWk());
		memJindoCngDtlAfter.setSort(memJindoMst.getSort());
		memJindoCngDtlAfter.setYoil(memJindoMst.getYoil());
		memJindoCngDtlAfter.setJset(memJindoMst.getJset());
		memJindoCngDtlAfter.setInd(memJindoMst.getInd());
		
		ipheiRepository.insertMemJindoCngDtlAfter(memJindoCngDtlAfter);
	}

	public List<MemberIpheiList> findIpheiList(String empKey, String kwamok,
			String startDate, String endDate, String type, String ipheiGubun,
			String loginLang, String jisaCD, String depid1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("empKey", empKey);
		map.put("kwamok", kwamok);
		map.put("startDate", startDate);
		map.put("endDate", endDate);
		map.put("type", type);
		map.put("ipheiGubun", ipheiGubun);
		map.put("loginLang", loginLang);
		map.put("jisaCD", jisaCD);
		map.put("depid1", depid1);
		return ipheiRepository.findIpheiList(map);
	}
}
