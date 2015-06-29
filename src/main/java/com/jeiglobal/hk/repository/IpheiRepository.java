package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;

import com.jeiglobal.hk.domain.common.MemIpgumMst;
import com.jeiglobal.hk.domain.common.MemIpheiAgree;
import com.jeiglobal.hk.domain.common.MemIpheiMst;
import com.jeiglobal.hk.domain.common.MemJindoCngDtlAfter;
import com.jeiglobal.hk.domain.common.MemJindoCngDtlBefore;
import com.jeiglobal.hk.domain.common.MemJindoCngMst;
import com.jeiglobal.hk.domain.common.MemJindoMst;
import com.jeiglobal.hk.domain.common.MemMst;
import com.jeiglobal.hk.domain.common.MemMstHis;
import com.jeiglobal.hk.domain.common.MemSubInfo;
import com.jeiglobal.hk.domain.common.MemSubjMst;
import com.jeiglobal.hk.domain.common.MemSubjMstHis;
import com.jeiglobal.hk.domain.common.OmrGichoMujin;
import com.jeiglobal.hk.domain.common.OmrJinSet;


@PrimaryRepositoryAnnoInterface
public interface IpheiRepository {
	
	public boolean isMemIpheiAgree(Map<String,Object> map);
	
	public long findMemIpheiAgree(Map<String,Object> map);
	
	public OmrGichoMujin findOmrGichoMujin(Map<String,Object> map);
	
	public List<Map<String, Object>> isDepositSet(Map<String,Object> map);
	
	public List<Map<String, Object>> selectDepositSet(Map<String,Object> map);
	
	public long selectMemIpheiAgreeAgnum();
	
	public void insertMemIpheiAgree(MemIpheiAgree memIpheiAgree);
	
	public List<Map<String, Object>> findExistsMember(Map<String, Object> map);
	
	public List<Map<String, Object>> findMujinMember(Map<String, Object> map);
	
	public MemSubjMst findMemSubjMst(Map<String,Object> map);
	
	public long countJSET(Map<String,Object> map);
	
	public boolean isExistsMemSubjMst(Map<String,Object> map);
	
	public String selectStateCDMemSubjMst(Map<String,Object> map);
	
	public boolean isExistsMemMst(Map<String,Object> map);
	
	public MemSubInfo findSubjInfo(Map<String,Object> map);
	
	public MemMst findMemMstById(Map<String,Object> map);
	
	public Map<String,Object> selectComEmpMst(String empKey);
	
	public void insertMemSubjMst(MemSubjMst memSubjMst);
	
	public void updateMemSubjMst(MemSubjMst memSubjMst);
	
	public void insertMemSubjMstHis(MemSubjMstHis memSubjMstHis);
	
	public void insertMemMst(MemMst memMst);
	
	public void updateMemMst(MemMst memMst);

	public void insertMemMstHis(MemMstHis memMstHis);
	
	public void insertMemIpheiMst(MemIpheiMst memIpheiMst);
	
	public void insertMemIpgumMst(MemIpgumMst memIpgumMst);
	
	public List<String> selectMJgrade(Map<String,Object> map);
	
	public List<String> selectCAS_NSETbyNSys2(Map<String,Object> map);
	
	public void insertMemJindoMst(MemJindoMst memJindoMst);
	
	public MemJindoMst findOneMemJindoMst(Map<String,Object> map);
	
	public void deleteMemJindoCngMst(Map<String,Object> map);
	
	public void deleteMemJindoCngDtlAfter(Map<String,Object> map);
	
	public void deleteMemJindoCngDtlBefore(Map<String,Object> map);
	
	public void insertMemJindoCngMst(MemJindoCngMst memJindoCngMst);
	
	public void insertMemJindoCngDtlAfter(MemJindoCngDtlAfter memJindoCngDtlAfter);
	
	public void insertMemJindoCngDtlBefore(MemJindoCngDtlBefore memJindoCngDtlBefore);
	
	public MemJindoCngMst findMemJindoCngMst(Map<String,Object> map);
	
	public List<MemJindoMst> findMemJindoMstOrderByYyMmWkSort(Map<String,Object> map);
	
	public void deleteMemJindoMst(Map<String,Object> map);
	
	public void deleteMemJindoMstWhereYyMmWeekSort(Map<String,Object> map);
	
	public List<String> selectCAS_NSETbyNSys2UseMujinInsert(Map<String,Object> map);
	
	public List<OmrJinSet> findOmrJinSet(Map<String,Object> map);
	
	public List<MemJindoMst> findMemJindoMst(Map<String,Object> map);
	
	public void deleteMemJindoMstWhereYyMm(Map<String,Object> map);
}
