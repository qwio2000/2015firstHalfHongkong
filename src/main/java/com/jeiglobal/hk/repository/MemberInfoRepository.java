package com.jeiglobal.hk.repository;

import java.util.HashMap;
import java.util.List;

import com.jeiglobal.hk.domain.member.DtlCD;
import com.jeiglobal.hk.domain.member.JungDabInfo;
import com.jeiglobal.hk.domain.member.MemberDetailInfo;
import com.jeiglobal.hk.domain.member.MemberHuheiInfo;
import com.jeiglobal.hk.domain.member.MemberIpgumInfo;
import com.jeiglobal.hk.domain.member.MemberIpheiInfo;
import com.jeiglobal.hk.domain.member.MemberJindoInfo;
import com.jeiglobal.hk.domain.member.MemberJindoSearch;
import com.jeiglobal.hk.domain.member.MemberJindoSearchInfo;
import com.jeiglobal.hk.domain.member.MemberKwamokInfo;
import com.jeiglobal.hk.domain.member.OmrGicho;
import com.jeiglobal.hk.domain.member.OmrInfo;
@PrimaryRepositoryAnnoInterface
public interface MemberInfoRepository {

	public MemberDetailInfo selectMemberDetailInfo(HashMap<String, Object> map);

	public List<DtlCD> selectDtlCodeList(String jisaCD);

	public List<MemberKwamokInfo> selectMemberKwamokInfo(HashMap<String, Object> map);

	public int updateMemberDetailInfo(HashMap<String, Object> map);

	public String selectFirstIpheiDate(HashMap<String, Object> map);

	public List<MemberIpheiInfo> selectMemberIpheiInfoList(
			HashMap<String, Object> map);

	public List<MemberHuheiInfo> selectMemberHuheiInfoList(
			HashMap<String, Object> map);

	public List<MemberIpgumInfo> selectMemberIpgumInfoList(
			HashMap<String, Object> map);

	public List<MemberJindoInfo> selectMemberJindoInfoList(
			HashMap<String, Object> map);

	public MemberJindoSearch selectMemberJindoSearch(
			HashMap<String, Object> map);

	public List<MemberJindoSearchInfo> selectMemberJindoSearchInfo(
			HashMap<String, Object> map);

	public List<OmrGicho> selectOmrGichoList(HashMap<String, Object> map);

	public OmrInfo selectMemberJindanCheck(MemberDetailInfo memberDetailInfo);

	public List<JungDabInfo> selectJungDabList(HashMap<String, Object> map);

	public int selectTotMunCount(HashMap<String, Object> map);

	public String selectMemberOmrCheck(MemberDetailInfo memberDetailInfo);

	public void insertOmrGicho(HashMap<String, Object> map);

	public void insertOdabInfo(HashMap<String, Object> map);

	public String omrBan(HashMap<String, Object> map);

	public String selectKwamokName(HashMap<String, Object> map);

	public List<DtlCD> selectHuheiSayuList(String jisa);

	public String selectTodayHuheiCheck(HashMap<String, Object> map);

	public String selectIsHuheiAgreeState(MemberDetailInfo memberDetailInfo);

	public String insertMemberHuheiInfo(HashMap<String, Object> map);

}
