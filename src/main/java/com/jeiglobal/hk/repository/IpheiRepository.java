package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;

import com.jeiglobal.hk.domain.common.*;


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

	public List<MemberIpheiList> findIpheiList(Map<String, Object> map);
}
