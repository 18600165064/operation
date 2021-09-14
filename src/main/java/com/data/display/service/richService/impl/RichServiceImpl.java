package com.data.display.service.richService.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.data.display.util.PhotoHandleUtil;
import com.data.display.util.StringUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.data.display.mapper.commodityMapper.SpuCommodityMapper;
import com.data.display.mapper.richMapper.RichMapper;
import com.data.display.model.commodity.SpuCommodity;
import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.rich.Rich;
import com.data.display.service.richService.RichService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

@Service
public class RichServiceImpl implements RichService{

	private static final Logger logger = LoggerFactory.getLogger(RichServiceImpl.class);
	
	@Resource
	private RichMapper richMapper;
	
	@Override
	public Page<Rich> getRichData(DataTableDTO dataTableDTO, Rich rich) {
		PageHelper.startPage(dataTableDTO.getPageNum(), dataTableDTO.getLength());
		return richMapper.getRichData(rich);
	}

	@Override
	public DataTableResult addRichData(Rich rich) {
		DataTableResult result = new DataTableResult();
		try {
				result.setError("保存成功");
				rich.setCreate_time(new Date());
				rich.setStatus(0);
				result.setDraw(richMapper.addRichData(rich));

				if (StringUtil.isNotBlank(rich.getContent())){
					Thread thread = new Thread(new PhotoHandleUtil(null,null,2,rich,richMapper,null,null));
					thread.start();
				}

		} catch (Exception e) {
			result.setError("保存失败");
			logger.error("消息保存失败",e);
		}
		return result;
	}

	@Override
	public Rich selectById(String id) {
		return richMapper.selectById(id);
	}

	@Override
	public DataTableResult editRichData(Rich rich) {
		DataTableResult result = new DataTableResult();
		try {
				result.setError("修改成功");
				rich.setUpdate_time(new Date());
				result.setDraw(richMapper.editRichData(rich));

				if (StringUtil.isNotBlank(rich.getContent())){
					Thread thread = new Thread(new PhotoHandleUtil(null,null,2,rich,richMapper,null,null));
					thread.start();
				}


		} catch (Exception e) {
			result.setError("修改失败");
			logger.error("消息修改失败",e);
		}
		return result;
	}

	@Override
	public List<Rich> selectRichByOthers(Rich rich) {
		return richMapper.selectRichByOthers(rich);
	}
	
}
