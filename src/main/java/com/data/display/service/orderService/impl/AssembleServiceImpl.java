package com.data.display.service.orderService.impl;

import com.data.display.mapper.orderMapper.AssembleMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.order.Assemble;
import com.data.display.service.orderService.AssembleService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("assembleService")
public class AssembleServiceImpl implements AssembleService {

    @Resource
    private AssembleMapper assembleMapper;

    @Override
    public Page<Assemble> getAssembleData(DataTableDTO dataTableDTO, Assemble assemble) {
        PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
        return assembleMapper.getAssembleData(assemble);
    }
}
