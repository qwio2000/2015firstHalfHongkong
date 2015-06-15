package com.jeiglobal.hk.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.study.RqIPPR;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Gicho;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab11Left;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab11Right;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Range;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab12;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab4;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Program;
import com.jeiglobal.hk.domain.study.RsOmrPrint20RangeAllGet;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Schedule;
import com.jeiglobal.hk.repository.IPPRRepository;

@Service
public class IPPRService {

	@Autowired
	private IPPRRepository ipprRepository;

	/***
	 * 회원기초정보
	 * 
	 * @param infoIPPR
	 * @return
	 */
	public RsOmrPrint20Gicho getOmrPrint20Gicho(String jisaCD, String omrDate, String hkey, String kwamok, String omrPath, String langCD) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", jisaCD);
		map.put("omrDate", omrDate);
		map.put("hkey", hkey);
		map.put("kwamok", kwamok);
		map.put("omrPath", omrPath);
		map.put("langCD", langCD);

		return ipprRepository.spOmrPrint20Gicho(map);
	}

	public List<RsOmrPrint20Odab11Left> getOmrPrint20Odab11Left(String jisaCD, String omrDate, String hkey, String kwamok, String omrPath, String langCD) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", jisaCD);
		map.put("omrDate", omrDate);
		map.put("hkey", hkey);
		map.put("kwamok", kwamok);
		map.put("omrPath", omrPath);
		map.put("langCD", langCD);

		return ipprRepository.spOmrPrint20Odab11Left(map);
	}

	public List<RsOmrPrint20Odab11Right> getOmrPrint20Odab11Right(String jisaCD, String omrDate, String hkey, String kwamok, String omrPath, String langCD) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", jisaCD);
		map.put("omrDate", omrDate);
		map.put("hkey", hkey);
		map.put("kwamok", kwamok);
		map.put("omrPath", omrPath);
		map.put("langCD", langCD);

		return ipprRepository.spOmrPrint20Odab11Right(map);
	}

	public RsOmrPrint20Range getOmrPrint20Range(RqIPPR infoIPPR) {
		// TODO Auto-generated method stub
		return ipprRepository.spOmrPrint20Range(infoIPPR);
	}

	public RsOmrPrint20RangeAllGet getOmrPrint20RangeAllGet(RqIPPR infoIPPR) {
		// TODO Auto-generated method stub
		return ipprRepository.spOmrPrint20RangeAllGet(infoIPPR);
	}

	public List<RsOmrPrint20Odab12> getOmrPrint20Odab12(String jisaCD, String omrDate, String hkey, String kwamok, String omrGrd, String omrKind, String omrPath, String langCD) {
		// TODO Auto-generated method stub
		Map<String, Object> map = new HashMap<>();
		map.put("jisaCD", jisaCD);
		map.put("omrDate", omrDate);
		map.put("hkey", hkey);
		map.put("kwamok", kwamok);
		map.put("omrGrd", omrGrd);
		map.put("omrKind", omrKind);
		map.put("omrPath", omrPath);
		map.put("langCD", langCD);

		return ipprRepository.spOmrPrint20Odab12(map);
	}

	public RsOmrPrint20Odab4 getOmrPrint20Odab4(RqIPPR infoIPPR) {
		// TODO Auto-generated method stub
		return ipprRepository.spOmrPrint20Odab4(infoIPPR);
	}

	public RsOmrPrint20Program getOmrPrint20OdabProgram(RqIPPR infoIPPR) {
		// TODO Auto-generated method stub
		return ipprRepository.spOmrPrint20Program(infoIPPR);
	}

	public RsOmrPrint20Schedule getOmrPrint20Schedule(RqIPPR infoIPPR) {
		// TODO Auto-generated method stub
		return ipprRepository.spOmrPrint20Schedule(infoIPPR);
	}

	/*
	 * public MemberDetailInfo getMemberDetailInfo(MemberDetailInfo
	 * memberDetailInfo, HttpServletRequest request) { // TODO Auto-generated
	 * method stub HashMap<String, Object> map = new HashMap<>(); map.put("mdi",
	 * memberDetailInfo); map.put("lang", getCookieValue(request, "LoginLang"));
	 * MemberDetailInfo mdi = memberInfoRepository.selectMemberDetailInfo(map);
	 * return mdi; }
	 */
}
