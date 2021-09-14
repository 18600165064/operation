package com.data.display.mapper.orderMapper;

import com.data.display.model.order.Assemble;
import com.github.pagehelper.Page;

public interface AssembleMapper {

    Page<Assemble> getAssembleData(Assemble assemble);
}
