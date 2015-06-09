package com.jeiglobal.hk.repository;

import java.util.*;

import com.jeiglobal.hk.domain.manageInfo.*;

@PrimaryRepositoryAnnoInterface
public interface ManageInfoRepository {

	public List<MemberSearchInfo> selectMemberSearchInfo(Map<String, Object> map);

	public List<HuheiMemberInfo> selectHuheiMemberList(Map<String, Object> map);

	public List<StudyState> selectStudyStateList(Map<String, Object> map);

	public List<EmptyHakjukInfo> selectEmptyHakjukInfo(Map<String, Object> map);

}
