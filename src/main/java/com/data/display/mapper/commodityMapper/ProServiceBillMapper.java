package com.data.display.mapper.commodityMapper;

import com.data.display.model.commodity.ProServiceBill;
import com.github.pagehelper.Page;

import java.util.List;

public interface ProServiceBillMapper {

    Page<ProServiceBill> getProServiceBillData(ProServiceBill proServiceBill);

    List<ProServiceBill> getProServiceByOrderNo(ProServiceBill proServiceBill);

    Integer insertProServiceBill(ProServiceBill proServiceBill);
}
