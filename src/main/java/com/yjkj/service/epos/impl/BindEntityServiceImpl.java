package com.yjkj.service.epos.impl;

import com.yjkj.entity.BindEntity;
import com.yjkj.mapper.BindEntityMapper;
import com.yjkj.service.BaseServiceImpl;
import com.yjkj.service.epos.BindEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BindEntityServiceImpl extends BaseServiceImpl<BindEntity> implements BindEntityService {
	
	@Autowired
	private BindEntityMapper bindEntityMapper;


}
