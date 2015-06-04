package com.jeiglobal.hk.repository;

import java.util.*;

import com.jeiglobal.hk.domain.manageInfo.*;

@PrimaryRepositoryAnnoInterface
public interface ManageInfoRepository {

	List<MemberSearchInfo> selectMemberSearchInfo(Map<String, Object> map);

}
