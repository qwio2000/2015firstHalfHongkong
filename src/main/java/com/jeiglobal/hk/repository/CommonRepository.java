package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;

import com.jeiglobal.hk.domain.common.Comcode;
import com.jeiglobal.hk.domain.common.MemIpgumMst;
import com.jeiglobal.hk.domain.common.MemIpheiAgree;
import com.jeiglobal.hk.domain.common.MemIpheiMst;
import com.jeiglobal.hk.domain.common.MemMstHis;
import com.jeiglobal.hk.domain.common.MemSubjMst;
import com.jeiglobal.hk.domain.common.MemMst;
import com.jeiglobal.hk.domain.common.MemSubInfo;
import com.jeiglobal.hk.domain.common.MemSubjMstHis;
import com.jeiglobal.hk.domain.common.OmrGichoMujin;


@PrimaryRepositoryAnnoInterface
public interface CommonRepository {
	public List<String> findDateSetting(Map<String,Object> map);
	
	public List<Map<String,Object>> findDepart(Map<String,Object> map);
	
	public List<String> selectKwamokList(Map<String, Object> map);

	public List<Map<String, Object>> selectClassList(Map<String, Object> map);
	
	public List<Map<String, Object>> findExistsMember(Map<String, Object> map);
	
	public List<Map<String, Object>> findMujinMember(Map<String, Object> map);
	
	public List<Comcode> selectCodeDtl(Map<String, Object> map);

	public List<String> selectMJgrade(Map<String,Object> map);
	
	public MemSubjMst findMemSubjMst(Map<String,Object> map);
	
	public String selectHuheiYMW(Map<String,Object> map);
	
	public long countJSET(Map<String,Object> map);
	
	public List<String> selectFstDay(Map<String,Object> map);
	
	public boolean isExistsMemSubjMst(Map<String,Object> map);
	
	public String selectStateCDMemSubjMst(Map<String,Object> map);
	
	public boolean isExistsMemMst(Map<String,Object> map);
	
	public MemSubInfo findSubjInfo(Map<String,Object> map);
	
	public boolean isMemIpheiAgree(Map<String,Object> map);
	
	public long findMemIpheiAgree(Map<String,Object> map);
	
	public long countJndate(Map<String,Object> map);
	
	public List<Map<String, Object>> isDepositSet(Map<String,Object> map);
	
	public List<Map<String, Object>> selectDepositSet(Map<String,Object> map);
	
	public MemMst findMemMstById(Map<String,Object> map);
	
	public OmrGichoMujin findOmrGichoMujin(Map<String,Object> map);
	
	public String selectNewKey(Map<String,Object> map);
	
	public long selectMemIpheiAgreeAgnum();
	
	public void insertMemIpheiAgree(MemIpheiAgree memIpheiAgree);
	
	public Map<String,Object> selectComEmpMst(String empKey);
	
	public void insertMemSubjMst(MemSubjMst memSubjMst);
	
	public void updateMemSubjMst(MemSubjMst memSubjMst);
	
	public void insertMemSubjMstHis(MemSubjMstHis memSubjMstHis);
	
	public void insertMemMst(MemMst memMst);
	
	public void updateMemMst(MemMst memMst);

	public void insertMemMstHis(MemMstHis memMstHis);
	
	public void insertMemIpheiMst(MemIpheiMst memIpheiMst);
	
	public void insertMemIpgumMst(MemIpgumMst memIpgumMst);
}
