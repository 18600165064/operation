package com.data.display.service.orderService;

import java.util.List;

import com.data.display.model.order.ExpressCompany;

public interface ExpressCompanyService {

	List<ExpressCompany> getData(ExpressCompany expressCompany);

    int addExpressCompany(ExpressCompany expressCompany);
}
