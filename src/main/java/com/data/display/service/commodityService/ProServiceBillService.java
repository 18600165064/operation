package com.data.display.service.commodityService;

import com.data.display.model.commodity.ProServiceBill;
import com.data.display.model.dto.DataTableDTO;
import com.github.pagehelper.Page;

public interface ProServiceBillService {

    Page<ProServiceBill> getProServiceBillData(DataTableDTO dataTableDTO, ProServiceBill proServiceBill);

}
