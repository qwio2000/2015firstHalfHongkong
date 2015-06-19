package com.jeiglobal.hk.service;

import java.text.*;
import java.util.*;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.domain.common.*;
import com.jeiglobal.hk.domain.member.*;
import com.jeiglobal.hk.repository.*;

@Service
public class MemberInfoService {
	
	@Autowired
	private MemberInfoRepository memberInfoRepository;
	
	/**
	 * 회원 상세정보 불러오는 메서드
	 * @param memberDetailInfo
	 * @param loginLang
	 * @return MemberDetailInfo
	 */
	public MemberDetailInfo getMemberDetailInfo(MemberDetailInfo memberDetailInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("lang", loginLang);
		MemberDetailInfo mdi = memberInfoRepository.selectMemberDetailInfo(map);
		return mdi;
	}
	
	/**
	 * 학년과 입회경로 DtlCDNM 가져오는 메서드
	 * @param jisaCD
	 * @return List<DtlCD>
	 */
	public List<DtlCD> getDtlCode(String jisaCD) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectDtlCodeList(jisaCD);
	}
	
	/**
	 * 회원의 과목정보를 가져오는 메서드
	 * @param memberDetailInfo
	 * @param loginLang
	 * @return List<MemberKwamokInfo>
	 */
	public List<MemberKwamokInfo> getMemberKwamokInfo(MemberDetailInfo memberDetailInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("lang", loginLang);
		return memberInfoRepository.selectMemberKwamokInfo(map);
	}
	
	/**
	 * 관리카드 회원정보 업데이트
	 * @param memberDetailInfo
	 * @param authMemberInfo
	 * @return count(변경 건수)
	 */
	public int updateMemberDetailInfo(MemberDetailInfo memberDetailInfo, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("ami", authMemberInfo);
		return memberInfoRepository.updateMemberDetailInfo(map);
	}
	
	/**
	 * 회원 정보 중 전화번호, 이메일의 데이터를 붙이는 메서드
	 * @param memberDetailInfo
	 * @param cd
	 * @return MemberDetailInfo
	 */
	public MemberDetailInfo concatData(MemberDetailInfo memberDetailInfo, ConcatData cd) {
		// TODO Auto-generated method stub
		memberDetailInfo.setTel((cd.getTel1().equals("")||cd.getTel1()==null)?"":cd.getTel1()+"-"+cd.getTel2()+"-"+cd.getTel3());
		memberDetailInfo.setgPhone((cd.getgPhone1().equals("")||cd.getgPhone1()==null)?"":cd.getgPhone1()+"-"+cd.getgPhone2()+"-"+cd.getgPhone3());
		memberDetailInfo.setePhone((cd.getePhone1().equals("")||cd.getePhone1()==null)?"":cd.getePhone1()+"-"+cd.getePhone2()+"-"+cd.getePhone3());
		memberDetailInfo.setmEmail((cd.getmEmail1().equals("")||cd.getmEmail1()==null)?"":cd.getmEmail1()+"@"+cd.getmEmail2());
		memberDetailInfo.setgEmail((cd.getgEmail1().equals("")||cd.getgEmail1()==null)?"":cd.getgEmail1()+"@"+cd.getgEmail2());
		return memberDetailInfo;
	}
	
	/**
	 * 회원의 입복회 정보를 가져오는 메서드
	 * @param memberDetailInfo
	 * @param searchKwamok
	 * @param authMemberInfo
	 * @param loginLang
	 * @return List<MemberIpheiInfo>
	 */
	public List<MemberIpheiInfo> getMemberIpheiInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", loginLang);
		return memberInfoRepository.selectMemberIpheiInfoList(map);
	}
	
	/**
	 * 회원의 퇴회정보를 가져오는 메서드
	 * @param memberDetailInfo
	 * @param searchKwamok
	 * @param authMemberInfo
	 * @param loginLang
	 * @return List<MemberHuheiInfo>
	 */
	public List<MemberHuheiInfo> getMemberHuheiInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", loginLang);
		return memberInfoRepository.selectMemberHuheiInfoList(map);
	}
	
	/**
	 * 회원 입금정보를 가져오는 메서드
	 * @param memberDetailInfo
	 * @param searchKwamok
	 * @param authMemberInfo
	 * @param loginLang
	 * @return List<MemberIpgumInfo>
	 */
	public List<MemberIpgumInfo> getMemberIpgumInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", loginLang);
		return memberInfoRepository.selectMemberIpgumInfoList(map);
	}
	
	/**
	 * 회원의 진도정보를 가져오는 메서드
	 * @param memberDetailInfo
	 * @param searchKwamok
	 * @param authMemberInfo
	 * @param loginLang
	 * @return List<MemberJindoInfo>
	 */
	public List<MemberJindoInfo> getMemberJindoInfo(
			MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
		Map<String, Object> map = new HashMap<>();
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("curYYYYMM", sdf.format(today));
		map.put("searchKwamok", searchKwamok);
		map.put("lang", loginLang);
		return memberInfoRepository.selectMemberJindoInfoList(map);
	}
	
	/**
	 * 회원의 진도검색 정보를 가져오는 메서드
	 * @param memberDetailInfo
	 * @param searchYY
	 * @param searchMM
	 * @param searchKwamok
	 * @param authMemberInfo
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getMemberJindoSearch(
			MemberDetailInfo memberDetailInfo, String searchYY, String searchMM, String searchKwamok, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Map<String, Object> map = new HashMap<>();
		if(searchYY != null && searchMM != null){
			cal.set(Integer.parseInt(searchYY), Integer.parseInt(searchMM)-1, 1);
		}else{
			searchYY = sdf.format(cal.getTime()).substring(0, 4);
			searchMM = sdf.format(cal.getTime()).substring(5);
		}
		cal.add(Calendar.MONTH, -5);
		String startYYYY = sdf.format(cal.getTime()).substring(0, 4);
		String startMM = sdf.format(cal.getTime()).substring(5);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("startYYYY", startYYYY);
		map.put("startMM", startMM);
		map.put("searchYY", searchYY);
		map.put("searchMM", searchMM);
		String[][][] bsArray = new String[12][5][2];
		String[][][] indArray = new String[12][5][2];
		for (int i = 0; i < 12; i++) {
			map.put("mKey", memberDetailInfo.getmKey());
			map.put("kwamok", memberDetailInfo.getKwamok());
			map.put("searchKwamok", searchKwamok);
			map.put("yyyy", startYYYY);
			map.put("mm", startMM);
			List<MemberJindoSearchInfo> mjsi = memberInfoRepository.selectMemberJindoSearchInfo(map);
			for (int j = 0; j < mjsi.size(); j++) {
				bsArray[i][Integer.parseInt(mjsi.get(j).getWk())-1][0] = mjsi.get(j).getBs();
				indArray[i][Integer.parseInt(mjsi.get(j).getWk())-1][0] = mjsi.get(j).getInd();
			}
			cal.add(Calendar.MONTH, 1);
			startYYYY = sdf.format(cal.getTime()).substring(0, 4);
			startMM = sdf.format(cal.getTime()).substring(5);
		}
		map.put("bsArray", bsArray);
		map.put("indArray", indArray);
		return map;
	}
	
	/**
	 * 회원 과목 별 요일 정보 가져오는 메서드
	 * @param memberDetailInfo
	 * @param searchKwamok
	 * @param authMemberInfo
	 * @param loginLang
	 * @return MemberJindoSearch
	 */
	public MemberJindoSearch getMemberInfo(MemberDetailInfo memberDetailInfo, String searchKwamok, AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("searchKwamok", searchKwamok);
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", loginLang);
		return memberInfoRepository.selectMemberJindoSearch(map);
	}
	
	/**
	 * 회원의 진도처방 기록을 가져오는 메서드
	 * @param memberDetailInfo
	 * @param searchYY
	 * @param searchKwamok
	 * @param authMemberInfo
	 * @param loginLang
	 * @return Map<String, Object>
	 */
	public Map<String, Object> getOmrGichoList(MemberDetailInfo memberDetailInfo,
			String searchYY, String searchKwamok, AuthMemberInfo authMemberInfo, String loginLang) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
		Map<String, Object> map = new HashMap<>();
		map.put("hkey", memberDetailInfo.getmKey());
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("lang", loginLang);
		if(searchYY != null){
			map.put("searchYY", searchYY);
		}else{
			map.put("searchYY", sdf.format(cal.getTime()));
		}
		if(searchKwamok != null){
			map.put("kwamok", searchKwamok);
		}else{
			map.put("kwamok", memberDetailInfo.getKwamok());
		}
		map.put("omrGichoList", memberInfoRepository.selectOmrGichoList(map));
		return map;
	}
	
	/**
	 * 회원의 진단 처방 가능 여부를 가져오는 메서드
	 * @param memberDetailInfo
	 * @return OmrInfo
	 */
	public OmrInfo getMemberJindanCheck(MemberDetailInfo memberDetailInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectMemberJindanCheck(memberDetailInfo);
	}
	
	/**
	 * 진단 처방 시 해당 과목, 등급에 맞는 정답리스트를 가져오는 메서드
	 * @param kwamok
	 * @param dung
	 * @param authMemberInfo
	 * @return List<JungDabInfo>
	 */
	public List<JungDabInfo> getJungDabList(String kwamok, String dung, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwamok", kwamok);
		map.put("dung", dung);
		map.put("jisa", authMemberInfo.getJisaCD());
		return memberInfoRepository.selectJungDabList(map);
	}
	
	/**
	 * 진단 처방 시 해당 과목, 등급에 맞는 문항 수를 가져오는 메서드
	 * @param kwamok
	 * @param dung
	 * @param authMemberInfo
	 * @return count(문항 수)
	 */
	public int getTotMunCount(String kwamok, String dung, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("kwamok", kwamok);
		map.put("dung", dung);
		map.put("jisa", authMemberInfo.getJisaCD());
		return memberInfoRepository.selectTotMunCount(map);
	}
	
	/**
	 * 회원의 이전 처방 정보를 가져오는 메서드
	 * @param memberDetailInfo
	 * @return insta(이전 처방정보)
	 */
	public String getMemberOmrCheck(MemberDetailInfo memberDetailInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectMemberOmrCheck(memberDetailInfo);
	}
	
	/**
	 * 진단 처방 시 OmrGicho 정보를 넣는 메서드
	 * @param memberDetailInfo
	 * @param dung
	 * @param authMemberInfo
	 */
	public void insertMemberOmrGicho(MemberDetailInfo memberDetailInfo,
			String dung, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("dung", dung);
		map.put("ami", authMemberInfo);
		memberInfoRepository.insertOmrGicho(map);
	}
	
	/**
	 * 회원의 오답을 저장하는 메서드
	 * @param memberDetailInfo
	 * @param dung
	 * @param errLst
	 * @param errTot
	 * @param authMemberInfo
	 */
	public void insertOdabInfo(MemberDetailInfo memberDetailInfo, String dung,
			String errLst, int errTot, AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Date today = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String odabNo ="";
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jisa", authMemberInfo.getJisaCD());
		map.put("omrDate", sdf.format(today));
		map.put("hKey", memberDetailInfo.getmKey());
		map.put("kwamok", memberDetailInfo.getKwamok());
		map.put("dung", dung);
		String[] splitErrList = errLst.split("\\$");
		for (int i = 1; i < splitErrList.length+1; i++) {
			if(splitErrList[i].equals("#"))break;
			String munNo = splitErrList[i].trim();
			if((memberDetailInfo.getKwamok().equals("EM")|| memberDetailInfo.getKwamok().equals("KM"))&& dung.compareTo("C")>0){
				String[] splitMunNo = munNo.split("\\|");
				munNo = splitMunNo[0].trim();
				odabNo = splitMunNo[1].trim();
			}
			map.put("munNo", munNo);
			map.put("odabNo", odabNo);
			memberInfoRepository.insertOdabInfo(map);
		}
	}
	
	/**
	 * 회원의 오답정보를 이용하여 처방하는 메서드
	 * @param authMemberInfo
	 * @param memberDetailInfo
	 * @param dung
	 * @return
	 */
	public String omrBan(AuthMemberInfo authMemberInfo,
			MemberDetailInfo memberDetailInfo, String dung) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("mdi", memberDetailInfo);
		map.put("ami", authMemberInfo);
		map.put("dung", dung);
		String result = memberInfoRepository.omrBan(map);
		return result;
	}
	
	/**
	 * 해당 과목의 이름을 가져오는 메서드
	 * @param jisa
	 * @param kwamok
	 * @param loginLang
	 * @return kwamokName(String)
	 */
	public String getKwamokName(String jisa, String kwamok,
			String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisa", jisa);
		map.put("kwamok", kwamok);
		map.put("lang", loginLang);
		return memberInfoRepository.selectKwamokName(map);
	}

	/**
	 * 퇴회 사유를 가져오는 메서드
	 * @param authMemberInfo
	 * @return List<DtlCD>
	 */
	public List<DtlCD> getHuheiSayuList(AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectHuheiSayuList(authMemberInfo.getJisaCD());
	}
	
	/**
	 * 당일 휴회 여부를 체크하는 메서드
	 * @param memberDetailInfo
	 * @param huheiDay
	 * @return check(String)
	 */
	public String getTodayHuheiCheck(MemberDetailInfo memberDetailInfo,
			String huheiDay) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("huheiDay", huheiDay);
		return memberInfoRepository.selectTodayHuheiCheck(map);
	}
	
	/**
	 * 휴회 대기 상태를 알아보는 메서드
	 * @param memberDetailInfo
	 * @return check(String)
	 */
	public String getIsHuheiAgreeState(MemberDetailInfo memberDetailInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectIsHuheiAgreeState(memberDetailInfo);
	}
	
	/**
	 * 회원의 퇴회 정보를 저장하는 메서드
	 * @param memberDetailInfo
	 * @param authMemberInfo
	 * @param huGubun
	 * @param huSayu
	 * @param huheiDay
	 * @return check(String)
	 */
	public String insertMemberHuheiInfo(MemberDetailInfo memberDetailInfo,
			AuthMemberInfo authMemberInfo, String huGubun, String huSayu,
			String huheiDay) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("huGubun", huGubun);
		map.put("huSayu", huSayu);
		map.put("huheiDay", huheiDay);
		map.put("ami", authMemberInfo);
		return memberInfoRepository.insertMemberHuheiInfo(map);
	}

	public List<String> getJindoUpdateDtlCDList(AuthMemberInfo authMemberInfo,
			String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("ami", authMemberInfo);
		map.put("lang", loginLang);
		return memberInfoRepository.selectJindoUpdateDtlCodeList(map);
	}

	public MemberInfoCheck getMemberInfoCheck(AuthMemberInfo authMemberInfo,
			MemberDetailInfo memberDetailInfo, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("ami", authMemberInfo);
		map.put("mdi", memberDetailInfo);
		map.put("lang", loginLang);
		return memberInfoRepository.selectMemberInfoCheck(map);
	}

	public JindoAdjustCheck getJindoAdjustCheck(
			MemberDetailInfo memberDetailInfo, AuthMemberInfo authMemberInfo,
			String cngGubun, String cngOpt, String loginLang) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("cngGubun", cngGubun);
		map.put("cngOpt", cngOpt);
		map.put("mKey", memberDetailInfo.getmKey());
		map.put("subj", memberDetailInfo.getKwamok());
		map.put("lang", loginLang);
		return memberInfoRepository.selectJindoAdjustCheck(map);
	}

	public String getChangeYoilYMD(MemberDetailInfo memberDetailInfo) {
		// TODO Auto-generated method stub
		return memberInfoRepository.selectChangeYoilYMD(memberDetailInfo);
	}

	public Map<String, Object> getJindoUpdateInputInfo(
			MemberDetailInfo memberDetailInfo, String updateYM,
			AuthMemberInfo authMemberInfo, String cngOpt) {
		// TODO Auto-generated method stub
		String sSet = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar cal = Calendar.getInstance();
		cal.set(Integer.parseInt(updateYM.substring(0,4)),Integer.parseInt(updateYM.substring(5))-1,1);
		Map<String, Object> map = new HashMap<>();
		String startYYYY = sdf.format(cal.getTime()).substring(0, 4);
		String startMM = sdf.format(cal.getTime()).substring(5);
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("startYYYY", startYYYY);
		map.put("startMM", startMM);
		map.put("cngOpt", cngOpt);
		String[][][] bsArray = new String[6][5][2];
		String[][][] bkArray = new String[6][5][2];
		String[][][] chkArray = new String[6][5][2];
		for (int i = 0; i < 6; i++) {
			map.put("mKey", memberDetailInfo.getmKey());
			map.put("kwamok", memberDetailInfo.getKwamok());
			map.put("yyyy", startYYYY);
			map.put("mm", startMM);
			List<MemberJindoSearchInfo> mjsi = memberInfoRepository.selectJindoUpdateInputInfo(map);
			for (int j = 0; j < mjsi.size(); j++) {
				bsArray[i][Integer.parseInt(mjsi.get(j).getWk())-1][0] = mjsi.get(j).getBs();
				bkArray[i][Integer.parseInt(mjsi.get(j).getWk())-1][0] = mjsi.get(j).getBk();
				chkArray[i][Integer.parseInt(mjsi.get(j).getWk())-1][0] = mjsi.get(j).getChk();
				if(mjsi.get(j).getBs() != null && !(mjsi.get(j).getBs().substring(0, 1).equals("Z")) && 
						sSet.compareTo(mjsi.get(j).getBs())<0){
					sSet = mjsi.get(j).getBs();
				}
			}
			cal.add(Calendar.MONTH, 1);
			startYYYY = sdf.format(cal.getTime()).substring(0, 4);
			startMM = sdf.format(cal.getTime()).substring(5);
		}
		map.put("sSet",sSet);
		map.put("bsArray", bsArray);
		map.put("bkArray", bkArray);
		map.put("chkArray", chkArray);
		System.out.println(sSet);
		return map;
	}

	public Map<String, Object> getSetList(AuthMemberInfo authMemberInfo,
			MemberDetailInfo memberDetailInfo, Object sSet) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		String dung = "";
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("subj", memberDetailInfo.getKwamok());
		map.put("dung", ((String)sSet).substring(0, 1));
		for (int i = 0; i < 3; i++) {
			map.put("chk", i);
			List<JindoUpdateSet> jusList = memberInfoRepository.selectSetList(map);
			map.put("set"+(i+1), jusList);
			if (jusList != null && jusList.size() >0) {
				dung = jusList.get(0).getDung();
			}
			map.put("set"+(i+1)+"dung", dung);
		}
		return map;
	}

	public int getBokSetCount(AuthMemberInfo authMemberInfo,
			JindoUpdateInfo jindoUpdateInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jisaCD", authMemberInfo.getJisaCD());
		map.put("subj", jindoUpdateInfo.getKwamok());
		map.put("sets1", jindoUpdateInfo.getSets1());
		map.put("sets2", jindoUpdateInfo.getSets2());
		return memberInfoRepository.selectBokSetCount(map);
	}

	public void updateJindoInfo(JindoUpdateInfo jindoUpdateInfo,
			AuthMemberInfo authMemberInfo) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("jui", jindoUpdateInfo);
		map.put("ami", authMemberInfo);
		memberInfoRepository.updateJindoInfo(map);
	}

}
