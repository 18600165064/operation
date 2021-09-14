package com.data.display.service.commodityService.impl;

import com.data.display.mapper.commodityMapper.ProServiceBillMapper;
import com.data.display.model.commodity.ProServiceBill;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.service.commodityService.ProServiceBillService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class ProServiceBillServiceImpl implements ProServiceBillService {

    @Resource
    private ProServiceBillMapper proServiceBillMapper;

    @Override
    public Page<ProServiceBill> getProServiceBillData(DataTableDTO dataTableDTO, ProServiceBill proServiceBill) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        return proServiceBillMapper.getProServiceBillData(proServiceBill);
    }
}
