package com.jeiglobal.hk.repository;

import com.jeiglobal.hk.domain.studyProgress.IPPRInfo;
import com.jeiglobal.hk.domain.studyProgress.OmrPrint20Gicho;
@PrimaryRepositoryAnnoInterface
public interface IPPRRepository {

	public OmrPrint20Gicho spOmrPrint20Gicho(IPPRInfo ipprInfo);
	
//	public OmrPrint20Gicho spOmrPrint20Odab11(IPPRInfo ipprInfo);
}
