package com.data.display.service.supplierService.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.display.mapper.supplierMapper.SupplierWithdrawJMapper;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.supplier.SupplierWithdrawJournal;
import com.data.display.service.supplierService.SupplierWithdrawJService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service("supplierWithdrawJService")
public class SupplierWithdrawJServiceImpl implements SupplierWithdrawJService{

	@Resource
    private SupplierWithdrawJMapper supplierWithdrawJMapper;
	
	@Override
	public Page<SupplierWithdrawJournal> getSupplierWithdrawJData(DataTableDTO dataTableDTO,SupplierWithdrawJournal supplierWithdrawJournal) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return supplierWithdrawJMapper.getSupplierWithdrawJData(supplierWithdrawJournal);
	}

}
