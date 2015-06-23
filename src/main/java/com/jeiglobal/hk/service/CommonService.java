package com.jeiglobal.hk.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.common.Comcode;
import com.jeiglobal.hk.repository.CommonRepository;
import com.jeiglobal.hk.utils.JeiCommonUtils;

@Service
public class CommonService {
	
	@Autowired
	private CommonRepository commonRepository;
	
	/**
	 * 입회 퇴회 가능날자
	 * @param authMemberInfo
	 * @return
	 */
	public List<String> getAvailableDateList(String jisaCD,String empKeyLvCD) {
		// TODO Auto-generated method stub
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", jisaCD);
		map.put("empKeyLvCD", empKeyLvCD);
		map.put("yyyy", sdf.format(cal.getTime()).substring(0, 4));
		map.put("mm", sdf.format(cal.getTime()).substring(5));
		return commonRepository.findDateSetting(map);
	}
	/**
	 * 교육원 정보 가져오기 depid,deptNM
	 * @param authMemberInfo
	 * @return
	 */
	public List<Map<String,Object>> getDepInfoList(String empKeyLvCD,String jisaCD,String depid1){
		Map<String, Object> map = new HashMap<>();
		map.put("empKeyLvCD", empKeyLvCD);
		map.put("jisaCD", jisaCD);
		map.put("depid1", depid1);
		
		return commonRepository.findDepart(map);
	}
	
	
	/**
	 * 교육원에서 사용할수있는 과목리스트 subj
	 * @param jisaCD
	 * @param depid1
	 * @return
	 */
	public List<String> getKwamokList(String loginLang,String jisaCD,String depid1,String depid2,String empKeyLvCD) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", jisaCD);
		map.put("depid1", depid1);
		map.put("depid2", depid2);
		map.put("empKeyLvCD", empKeyLvCD);
		map.put("lang", loginLang);
		return commonRepository.selectKwamokList(map);
	}

	/**
	 * 교육원 교실정보 리스트 empKey, empName
	 * @param jisaCD
	 * @param depid1
	 * @return
	 */
	public List<Map<String,Object>> getClassList(String jisaCD,String depid1) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", jisaCD);
		map.put("depid1", depid1);
		return commonRepository.selectClassList(map);
	}
	
	/**
	 * 컴코드 읽어오는 거 CODE_CodeLib_LST
	 * @param jisaCD
	 * @param mstCD
	 * @param stateCD
	 * @param loginLang
	 * @return
	 */
	public List<Comcode> selectCodeDtl(String jisaCD,String mstCD,String stateCD,String loginLang){
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD",jisaCD);
		map.put("mstCD",mstCD);
		map.put("stateCD",stateCD);
		map.put("loginLang",loginLang);
		return commonRepository.selectCodeDtl(map);
	}
	/**
	 * 첫관리 정보 FST_MNG_DAY
	 * @param pIpheiday 
	 * @return
	 */
	public List<String> selectMngDay(String pIpheiday){
		Map<String,Object> map = new HashMap<>();
		map.put("firstDay",JeiCommonUtils.getDiffDay("yyyyMMdd", pIpheiday,-15));
		map.put("lastDay",JeiCommonUtils.getDiffDay("yyyyMMdd", pIpheiday,15));
		List<String> sdateList = commonRepository.selectFstDay(map);
		List<String> fstDayList = new ArrayList<String>();
		
		for (String sdate : sdateList) {
			StringBuilder str = new StringBuilder();
			str.append(sdate.substring(0,4));
			str.append("-");
			str.append(sdate.substring(4,6));
			str.append("-");
			str.append(sdate.substring(6,8));
			fstDayList.add(str.toString());
		}
		
		return fstDayList;
	}
	/**
	 * 첫관리 방문일에 해당하는 주차 IPHEI_FSTDAY_LST
	 * @param pFstDay
	 * @return
	 */
	public String selectFstDay(String pFstDay){
		String fstDay[] =  pFstDay.split("-");
		
		Map<String,Object> map = new HashMap<>();
		map.put("pYear",fstDay[0]);
		map.put("pMonth",fstDay[1]);
		map.put("pDay",fstDay[2]);
		
		return commonRepository.selectHuheiYMW(map);
	}
	
	/**
	 * 입회에서 주차 정보 IPHEI_JUCHA_LST
	 * @param ipheiDay
	 * @return
	 */
	public List<Map<String,Object>> selectJucha(String ipheiDay){
		String days[] = ipheiDay.split("-");
		
		String ipheiyy = days[0];
		String ipheimm = days[1];
		String ipheidd = days[2];
		String ipheidw = String.valueOf(JeiCommonUtils.getWeekNum(ipheiDay));
		
		String nextDays[] = JeiCommonUtils.getDiffDay("yyyy-MM-dd",ipheiDay,7).split("-");
		String nextyy = nextDays[0];
		String nextmm = nextDays[1];
		String nextdd = nextDays[2];
		
		Map<String,Object> currMap = new HashMap<>();
		
		currMap.put("pYear",ipheiyy);
		currMap.put("pWol",ipheimm);
		currMap.put("pDay",ipheidd);
		currMap.put("pYoil",ipheidw);
		
		long curwkCnt = commonRepository.countJndate(currMap);
		
		long nxtwkCnt = 0;
		
		if(ipheimm.equals(nextmm)){
			Map<String,Object> nextMap = new HashMap<>();
			nextMap.put("pYear", nextyy);
			nextMap.put("pWol", nextmm);
			nextMap.put("pDay", nextdd);
			nextMap.put("pYoil",ipheidw);
			
			nxtwkCnt = commonRepository.countJndate(nextMap);
		}
		
		List<Map<String,Object>> resultList = new ArrayList<Map<String,Object>>();
		
		if(curwkCnt > nxtwkCnt){
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("restyy", ipheiyy);
			resultMap.put("restmm", ipheimm);
			resultMap.put("restwk", curwkCnt);
			resultMap.put("restymw",ipheiyy+ipheimm+curwkCnt);
			
			resultList.add(resultMap);
			
			Map<String,Object> resultMap1 = new HashMap<>();
			resultMap1.put("restyy", ipheiyy);
			resultMap1.put("restmm", ipheimm);
			resultMap1.put("restwk", nxtwkCnt);
			resultMap1.put("restymw",ipheiyy+ipheimm+nxtwkCnt);
			
			resultList.add(resultMap1);
		}else{
			Map<String,Object> resultMap = new HashMap<>();
			resultMap.put("restyy", ipheiyy);
			resultMap.put("restmm", ipheimm);
			resultMap.put("restwk", nxtwkCnt);
			resultMap.put("restymw",ipheiyy+ipheimm+nxtwkCnt);
			
			resultList.add(resultMap);
			
			Map<String,Object> resultMap1 = new HashMap<>();
			resultMap1.put("restyy", ipheiyy);
			resultMap1.put("restmm", ipheimm);
			resultMap1.put("restwk", curwkCnt);
			resultMap1.put("restymw",ipheiyy+ipheimm+curwkCnt);
			
			resultList.add(resultMap1);
		}
		
		return resultList;
	}
}
