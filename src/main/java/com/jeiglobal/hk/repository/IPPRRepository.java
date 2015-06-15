package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;

import com.jeiglobal.hk.domain.study.RqIPPR;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Gicho;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Range;
import com.jeiglobal.hk.domain.study.RsOmrPrint20RangeAllGet;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab11Left;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab11Right;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab12;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Odab4;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Program;
import com.jeiglobal.hk.domain.study.RsOmrPrint20Schedule;

@PrimaryRepositoryAnnoInterface
public interface IPPRRepository {
	public RsOmrPrint20Gicho spOmrPrint20Gicho(Map<String,Object> map);

	public List<RsOmrPrint20Odab11Left> spOmrPrint20Odab11Left(Map<String,Object> map);
	
	public List<RsOmrPrint20Odab11Right> spOmrPrint20Odab11Right(Map<String,Object> map);	
	
	public RsOmrPrint20Range spOmrPrint20Range(RqIPPR infoIPPR);
	
	public RsOmrPrint20RangeAllGet spOmrPrint20RangeAllGet(RqIPPR infoIPPR);	

	public List<RsOmrPrint20Odab12> spOmrPrint20Odab12(Map<String,Object> map);

	public RsOmrPrint20Odab4 spOmrPrint20Odab4(RqIPPR infoIPPR);

	public RsOmrPrint20Program spOmrPrint20Program(RqIPPR infoIPPR);

	public RsOmrPrint20Schedule spOmrPrint20Schedule(RqIPPR infoIPPR);
}
