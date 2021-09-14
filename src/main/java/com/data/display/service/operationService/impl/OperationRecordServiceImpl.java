package com.data.display.service.operationService.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.data.display.mapper.operationMapper.OperationRecordMapper;
import com.data.display.model.operation.OperationRecord;
import com.data.display.service.operationService.OperationRecordService;

@Service("operationRecordService")
public class OperationRecordServiceImpl implements OperationRecordService{

	@Resource
	 private OperationRecordMapper operationRecordMapper;
	
	@Override
	public void addOperationRecord(OperationRecord operationRecord) {
		operationRecordMapper.addOperationRecord(operationRecord);
	}

}
