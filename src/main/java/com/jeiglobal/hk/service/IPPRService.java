package com.jeiglobal.hk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jeiglobal.hk.domain.studyProgress.IPPRInfo;
import com.jeiglobal.hk.domain.studyProgress.OmrPrint20Gicho;
import com.jeiglobal.hk.repository.IPPRRepository;

@Service
public class IPPRService {
	
	@Autowired
	private IPPRRepository ipprRepository;

	public OmrPrint20Gicho getOmrPrint20Gicho(IPPRInfo ipprInfo) {
		// TODO Auto-generated method stub
		return ipprRepository.spOmrPrint20Gicho(ipprInfo);
	}

/*	public MemberDetailInfo getMemberDetailInfo(MemberDetailInfo memberDetailInfo, HttpServletRequest request) {
		// TODO Auto-generated method stub
		HashMap<String, Object> map = new HashMap<>();
		map.put("mdi", memberDetailInfo);
		map.put("lang", getCookieValue(request, "LoginLang"));
		MemberDetailInfo mdi = memberInfoRepository.selectMemberDetailInfo(map);
		return mdi;
	}*/
}
