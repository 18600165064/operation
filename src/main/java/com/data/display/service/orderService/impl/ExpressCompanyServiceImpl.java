package com.data.display.service.orderService.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.display.mapper.orderMapper.ExpressCompanyMapper;
import com.data.display.model.order.ExpressCompany;
import com.data.display.service.orderService.ExpressCompanyService;

@Service("expressCompany")
public class ExpressCompanyServiceImpl implements ExpressCompanyService{

	@Resource
    private ExpressCompanyMapper expressCompanyMapper;
	
	@Override
	public List<ExpressCompany> getData(ExpressCompany expressCompany) {
		return expressCompanyMapper.getData(expressCompany);
	}


	@Override
	public int addExpressCompany(ExpressCompany expressCompany){
		return expressCompanyMapper.addExpressCompany(expressCompany);
	}

}
