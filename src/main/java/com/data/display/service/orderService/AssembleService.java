package com.data.display.service.orderService;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.order.Assemble;
import com.github.pagehelper.Page;

public interface AssembleService {

    Page<Assemble> getAssembleData(DataTableDTO dataTableDTO, Assemble assemble);
}
