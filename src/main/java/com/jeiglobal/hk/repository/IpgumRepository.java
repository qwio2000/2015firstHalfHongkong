package com.jeiglobal.hk.repository;

import java.util.*;

import com.jeiglobal.hk.domain.ipgum.*;

@PrimaryRepositoryAnnoInterface
public interface IpgumRepository {

	public TotalHeibi selectTotHeibi(Map<String, Object> map);

	public List<IpgumInfo> selectIpgumInfoList(Map<String, Object> map);

}
