package com.jeiglobal.hk.service;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.jeiglobal.hk.repository.*;

@Service
public class IpgumService {
	
	@Autowired
	private IpgumRepository ipgumRepository;

}
