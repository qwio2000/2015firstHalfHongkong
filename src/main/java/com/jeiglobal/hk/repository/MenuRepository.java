package com.jeiglobal.hk.repository;

import java.util.List;
import java.util.Map;

import com.jeiglobal.hk.domain.common.GlobalMenu;

@PrimaryRepositoryAnnoInterface
public interface MenuRepository {

	public GlobalMenu findOneByMParentIdx(long mIdx);
	
	public List<GlobalMenu> findByMParentIdxAndJisaCDAndEmpKeyLvCDAndDepMngCD(Map<String, Object> map);
	
	public GlobalMenu findByMIdx(long mIdx);
	
	public void updateMHasChildrenByMidx(Map<String, Object> map);
	
	public GlobalMenu findOneByMParentOrderByMOrderDESC(long mIdx);
	
	public void insertGlobalMenu(GlobalMenu globalMenu);
	
	public long selectLastId();
	
	public void updateMDepthByMIdx(Map<String, Object> map);
	
	public long countMIdxByMParentIdx(long mIdx);
	
	public long selectMParentIdxByMIdx(long mIdx);
	
	public void deleteGlobalMenuByMidx(long mIdx);
	
	public int selectMDepthByMIdx(long mIdx);
	
	public List<GlobalMenu> findByMDepth(Map<String, Object> map);
	
	public void updateGlobalMenuByMIdx(GlobalMenu globalMenu);
	
	public void updateMSortByMIdx(Map<String, Object> map);
	
	public List<GlobalMenu> findByMParentIdx(long mIdx);
}
