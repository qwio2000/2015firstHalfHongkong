package com.jeiglobal.hk.service;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jeiglobal.hk.domain.common.GlobalMenu;
import com.jeiglobal.hk.repository.MenuRepository;


@Service
public class MenuService {
	
	
	
	@Autowired
	private MenuRepository menuRepository;
	

	
	public List<GlobalMenu> menuList(long mIdx,String mJisaCD,String mEmpKeyLvCD,String mDepMngCD,String mUseState){
		List<GlobalMenu> gmList = new ArrayList<GlobalMenu>();
		gmList.addAll(searchCnt(mIdx,mJisaCD,mEmpKeyLvCD,mDepMngCD,mUseState));
		return gmList;
	}
	
	/**
	 * 메뉴카테고리 등록 눌렀을때
	 */
	@Transactional(value="primaryTransactionManager")
	public String create(GlobalMenu gmMenu) {
		String msg = "";
		boolean sflag = false; // 성공,실패 확인위해
		GlobalMenu jei = new GlobalMenu();

		Date date = new Date();
		Timestamp nowDate = new Timestamp(date.getTime());
		gmMenu.setmRegDate(nowDate); // 현재 날짜
		gmMenu.setmUpdate(nowDate);
		
		jei = findByMIdx(gmMenu.getmParentIdx());
		
		if(jei != null){
			if(jei.getmDepth() + 1 <= 9){ // m9까지 있으므로 10은 될수 없음
				updateMHasChildrenByMidx(jei.getmIdx(),"1");
				gmMenu.setmDepth(jei.getmDepth()+1);
				
				GlobalMenu jj = findOneByMParentOrderByMOrderDESC(gmMenu.getmParentIdx());
				
				if(jj != null){
					gmMenu.setmSort(jj.getmSort()+1);
				}else{
					gmMenu.setmSort(1);
				}
				
				gmMenu.setM1(jei.getM1());
				gmMenu.setM2(jei.getM2());
				gmMenu.setM3(jei.getM3());
				gmMenu.setM4(jei.getM4());
				gmMenu.setM5(jei.getM5());
				gmMenu.setM6(jei.getM6());
				gmMenu.setM7(jei.getM7());
				gmMenu.setM8(jei.getM8());
				gmMenu.setM9(jei.getM9());
				
				insertGlobalMenu(gmMenu);
				
				sflag = true;
			}else{
				sflag = false;
			}
			
		} else{
			
			if(gmMenu.getmParentIdx() == 0){
				GlobalMenu jj = findOneByMParentOrderByMOrderDESC(gmMenu.getmParentIdx());
				
				if(jj != null){
					gmMenu.setmSort(jj.getmSort()+1);
				}else{
					gmMenu.setmSort(1);
				}
			} else{
				gmMenu.setmSort(1);
			}
			
			gmMenu.setmDepth(1);
			gmMenu.setM1((long)0);
			gmMenu.setM2((long)0);
			gmMenu.setM3((long)0);
			gmMenu.setM4((long)0);
			gmMenu.setM5((long)0);
			gmMenu.setM6((long)0);
			gmMenu.setM7((long)0);
			gmMenu.setM8((long)0);
			gmMenu.setM9((long)0);
			
			insertGlobalMenu(gmMenu);
			sflag = true;
		}
		
		if (sflag) {
			long lastmid = selectLastId();

			int mDepth = gmMenu.getmDepth();

			updateMDepthByMIdx(lastmid, mDepth);

			msg = "생성되었습니다.";

		} else {
			msg = "더이상 하위 폴더를 만들 수 없습니다.";
		}
		return msg;
	}
	
	@Transactional(value="primaryTransactionManager")
	public String delete(long mIdx) {
		String msg = "삭제 성공";

		long prs = menuRepository.countMIdxByMParentIdx(mIdx);
		
		if(prs > 0){
			msg = "하위카테고리가 있어서 삭제불가능";
			
		}else{
			
			long rst = menuRepository.selectMParentIdxByMIdx(mIdx);
			long cnt = menuRepository.countMIdxByMParentIdx(rst);
			if(cnt == 1){
				updateMHasChildrenByMidx(rst,"0");
			}
			
			menuRepository.deleteGlobalMenuByMidx(mIdx);
		}
		
		return msg;
	}
	
	public GlobalMenu readOne(long mIdx){
		return findByMIdx(mIdx);
	}
	
	@Transactional(value="primaryTransactionManager")
	public String update(GlobalMenu gmMenu) {
		boolean rflag = false;
		String msg = "";
		int listCnt = 0;

		List<GlobalMenu> childList = new ArrayList<GlobalMenu>();
		GlobalMenu childjm = new GlobalMenu();
		GlobalMenu jParent = new GlobalMenu();
		Date date = new Date();
		gmMenu.setmUpdate(new Timestamp(date.getTime())); // 업데이트 현재 날짜
		
		long parentMIdx = menuRepository.selectMParentIdxByMIdx(gmMenu.getmIdx());
		
		jParent = findByMIdx(gmMenu.getmParentIdx());

		if (parentMIdx != gmMenu.getmParentIdx()) {

			if ("1".equals(gmMenu.getmHasChildren())) {
				// 검사 자기 자식으로 붙일려고하는지
				if (jParent.getM1() == gmMenu.getmIdx() || jParent.getM2() == gmMenu.getmIdx()
						|| jParent.getM3() == gmMenu.getmIdx()
						|| jParent.getM4() == gmMenu.getmIdx()
						|| jParent.getM5() == gmMenu.getmIdx()
						|| jParent.getM6() == gmMenu.getmIdx()
						|| jParent.getM7() == gmMenu.getmIdx()
						|| jParent.getM8() == gmMenu.getmIdx()
						|| jParent.getM9() == gmMenu.getmIdx()) {

					msg = "자기 하위의 폴더로는 옮길수 없습니다.";
					return msg;
				}
				//
				rflag = true;

				childList = howManyChildren(gmMenu.getmIdx());
				listCnt = childList.size();
				
				int cntcnt = jParent.getmDepth() + ( childList.get(listCnt - 1).getmDepth())- gmMenu.getmDepth() ;
				
				if (cntcnt >= 9) {

					msg = "9계층까지 제한 됨 더이상 하위 폴더를 생성할 수 없습니다.";
					return msg;
				}
			}
		}

		if (jParent != null) {
			
			int changeSort = 1;

			if (jParent.getmDepth() + 1 <= 9) {// m9까지 있으므로 10은 될수가없다
				if (!"1".equals(jParent.getmHasChildren())) {
					updateMHasChildrenByMidx(jParent.getmIdx(),"1");
				}
				
				gmMenu.setmDepth(jParent.getmDepth()+1);
				
				GlobalMenu jj = findOneByMParentOrderByMOrderDESC(gmMenu.getmParentIdx());

				if (jj != null) {
					if (jj.getmParentIdx() != parentMIdx) {
						gmMenu.setmSort(jj.getmSort() + 1);
						changeSort = jj.getmSort() + 1;
					}
				} else {
					gmMenu.setmSort(1);
					changeSort = 1;
				}

				gmMenu.setM1(jParent.getM1());
				gmMenu.setM2(jParent.getM2());
				gmMenu.setM3(jParent.getM3());
				gmMenu.setM4(jParent.getM4());
				gmMenu.setM5(jParent.getM5());
				gmMenu.setM6(jParent.getM6());
				gmMenu.setM7(jParent.getM7());
				gmMenu.setM8(jParent.getM8());
				gmMenu.setM9(jParent.getM9());

				switch (gmMenu.getmDepth()) {
				case 2:
					gmMenu.setM2(gmMenu.getmIdx());
					break;
				case 3:
					gmMenu.setM3(gmMenu.getmIdx());
					break;
				case 4:
					gmMenu.setM4(gmMenu.getmIdx());
					break;
				case 5:
					gmMenu.setM5(gmMenu.getmIdx());
					break;
				case 6:
					gmMenu.setM6(gmMenu.getmIdx());
					break;
				case 7:
					gmMenu.setM7(gmMenu.getmIdx());
					break;
				case 8:
					gmMenu.setM8(gmMenu.getmIdx());
					break;
				case 9:
					gmMenu.setM9(gmMenu.getmIdx());
					break;
				}
				
				//자식 저장
				updateGlobalMenuByMIdx(gmMenu);

				if (rflag) {
					for (int i = 0; i < listCnt; i++) {
						childjm = childList.get(i);
						if(i == 0){
							childjm.setmParentIdx(gmMenu.getmParentIdx());
							childjm.setmSort(changeSort);
						}

						jParent = findByMIdx(childjm.getmParentIdx());

						childjm.setmDepth((jParent.getmDepth() + 1));

						childjm.setM1(jParent.getM1());
						childjm.setM2(jParent.getM2());
						childjm.setM3(jParent.getM3());
						childjm.setM4(jParent.getM4());
						childjm.setM5(jParent.getM5());
						childjm.setM6(jParent.getM6());
						childjm.setM7(jParent.getM7());
						childjm.setM8(jParent.getM8());
						childjm.setM9(jParent.getM9());

						switch (childjm.getmDepth()) {
						case 2:
							childjm.setM2(childjm.getmIdx());
							break;
						case 3:
							childjm.setM3(childjm.getmIdx());
							break;
						case 4:
							childjm.setM4(childjm.getmIdx());
							break;
						case 5:
							childjm.setM5(childjm.getmIdx());
							break;
						case 6:
							childjm.setM6(childjm.getmIdx());
							break;
						case 7:
							childjm.setM7(childjm.getmIdx());
							break;
						case 8:
							childjm.setM8(childjm.getmIdx());
							break;
						case 9:
							childjm.setM9(childjm.getmIdx());
							break;
						}
							
						updateGlobalMenuByMIdx(childjm);

					}

				}

				msg = "수정 성공하였습니다.";

				long parentCnt = menuRepository.countMIdxByMParentIdx(parentMIdx);
				
				if (parentCnt < 1) {
					updateMHasChildrenByMidx(parentMIdx,"0");
				}

			} else {
				msg = "더이상 하위 폴더를 생성할 수 없습니다.";

				return msg;
			}

		}

		return msg;
	}
	
	public String change(String lan) {
		String msg = "순서 변경성공";
		String[] mid = lan.split(",");

		for (int i = 0; i < mid.length; i++) {
			updateMSortByMIdx(Integer.parseInt(mid[i]),i+1);
		}
		
		return msg;
	}
	
	public List<GlobalMenu> changeList(long mIdx){
		return findByMParentIdx(mIdx);
	}
	
	private List<GlobalMenu> findByMParentIdx(long mIdx){
		return menuRepository.findByMParentIdx(mIdx);
	}
	
	private void updateMSortByMIdx(long mIdx,int mSort){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mIdx",mIdx);
		map.put("mSort",mSort);
		menuRepository.updateMSortByMIdx(map);
	}
	
	private List<GlobalMenu> searchCnt(long mIdx,String mJisaCD,String mEmpKeyLvCD,String mDepMngCD,String mUseState){
		List<GlobalMenu> gmMenuList = new ArrayList<GlobalMenu>();
		List<GlobalMenu> gmList = new ArrayList<GlobalMenu>();
		
		if (mIdx == 0) {
			GlobalMenu ji = findOneByMParentIdx(mIdx);

			if(ji != null){
				gmList.add(ji);
				gmList.addAll(searchCnt(ji.getmIdx(),mJisaCD,mEmpKeyLvCD,mDepMngCD,mUseState));
			}
		} else {
			gmMenuList = findByMParentIdxAndJisaCDAndEmpKeyLvCDAndDepMngCD(mIdx,mJisaCD,mEmpKeyLvCD,mDepMngCD,mUseState);
			
			if(gmMenuList != null){
				int cnt = gmMenuList.size();
				
				for(int i = 0;i < cnt; i++){
					GlobalMenu gm = gmMenuList.get(i);
					
					if("0".equals(gm.getmHasChildren())){
						gmList.add(gm);
					}else if("1".equals(gm.getmHasChildren())){
						gmList.add(gm);
						gmList.addAll(searchCnt(gm.getmIdx(),mJisaCD, mEmpKeyLvCD, mDepMngCD,mUseState));
					}
				}
			}

		}
		return gmList;
	}
	
	private void updateGlobalMenuByMIdx(GlobalMenu globalmenu){
		menuRepository.updateGlobalMenuByMIdx(globalmenu);
	}
	
	private GlobalMenu findOneByMParentIdx(long mIdx){
		return menuRepository.findOneByMParentIdx(mIdx);
	}
	
	private List<GlobalMenu> findByMParentIdxAndJisaCDAndEmpKeyLvCDAndDepMngCD(long mIdx,String mJisaCD,String mEmpKeyLvCD,String mDepMngCD,String mUseState){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("mIdx",mIdx);
		map.put("mJisaCD",mJisaCD);
		map.put("mEmpKeyLvCD",mEmpKeyLvCD);
		map.put("mDepMngCD",mDepMngCD);
		map.put("mUseState",mUseState);
		
		List<GlobalMenu> gmMenuList = menuRepository.findByMParentIdxAndJisaCDAndEmpKeyLvCDAndDepMngCD(map);
		
		return gmMenuList;
	}	
	
	private GlobalMenu findByMIdx(long mIdx){
		return menuRepository.findByMIdx(mIdx);
	}
	
	private void updateMHasChildrenByMidx(long mIdx,String mHasChildren){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mIdx",mIdx);
		map.put("mHasChildren",mHasChildren);
		
		menuRepository.updateMHasChildrenByMidx(map);
	}
	
	private GlobalMenu findOneByMParentOrderByMOrderDESC(long mIdx){
		return menuRepository.findOneByMParentOrderByMOrderDESC(mIdx);
	}
	
	private void insertGlobalMenu(GlobalMenu globalMenu){
		menuRepository.insertGlobalMenu(globalMenu);
	}
	
	private long selectLastId(){
		return menuRepository.selectLastId();
	}
	
	private void updateMDepthByMIdx(long mIdx,int mDepth){
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mIdx",mIdx);
		map.put("mDepth",mDepth);
		
		menuRepository.updateMDepthByMIdx(map);
	}
	
	private List<GlobalMenu> howManyChildren(long mIdx) {
		Map<String,Object> map = new HashMap<String, Object>();
		
		int mDepth = menuRepository.selectMDepthByMIdx(mIdx);
		
		map.put("mIdx",mIdx);
		map.put("mDepth",mDepth);

		return menuRepository.findByMDepth(map);
	}
	
}
