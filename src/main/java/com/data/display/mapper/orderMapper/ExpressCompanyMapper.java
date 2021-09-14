package com.data.display.mapper.orderMapper;

import java.util.List;

import com.data.display.model.order.ExpressCompany;

public interface ExpressCompanyMapper {

	List<ExpressCompany> getData(ExpressCompany expressCompany);

	List<ExpressCompany> selectByOthers(ExpressCompany expressCompany);

    int addExpressCompany(ExpressCompany expressCompany);
}
