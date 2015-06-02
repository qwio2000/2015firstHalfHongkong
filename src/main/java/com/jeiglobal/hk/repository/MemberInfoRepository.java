package com.jeiglobal.hk.repository;

import java.util.*;

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

	public MemberDetailInfo selectMemberDetailInfo(Map<String, Object> map);

	public List<DtlCD> selectDtlCodeList(String jisaCD);

	public List<MemberKwamokInfo> selectMemberKwamokInfo(Map<String, Object> map);

	public int updateMemberDetailInfo(Map<String, Object> map);

	public List<MemberIpheiInfo> selectMemberIpheiInfoList(
			Map<String, Object> map);

	public List<MemberHuheiInfo> selectMemberHuheiInfoList(
			Map<String, Object> map);

	public List<MemberIpgumInfo> selectMemberIpgumInfoList(
			Map<String, Object> map);

	public List<MemberJindoInfo> selectMemberJindoInfoList(
			Map<String, Object> map);

	public MemberJindoSearch selectMemberJindoSearch(
			Map<String, Object> map);

	public List<MemberJindoSearchInfo> selectMemberJindoSearchInfo(
			Map<String, Object> map);

	public List<OmrGicho> selectOmrGichoList(Map<String, Object> map);

	public OmrInfo selectMemberJindanCheck(MemberDetailInfo memberDetailInfo);

	public List<JungDabInfo> selectJungDabList(Map<String, Object> map);

	public int selectTotMunCount(Map<String, Object> map);

	public String selectMemberOmrCheck(MemberDetailInfo memberDetailInfo);

	public void insertOmrGicho(Map<String, Object> map);

	public void insertOdabInfo(Map<String, Object> map);

	public String omrBan(Map<String, Object> map);

	public String selectKwamokName(Map<String, Object> map);

	public List<DtlCD> selectHuheiSayuList(String jisa);

	public String selectTodayHuheiCheck(Map<String, Object> map);

	public String selectIsHuheiAgreeState(MemberDetailInfo memberDetailInfo);

	public String insertMemberHuheiInfo(Map<String, Object> map);

}
