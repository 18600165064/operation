package com.data.display.service.richService;

import java.util.List;

import com.data.display.model.dto.DataTableDTO;
import com.data.display.model.dto.DataTableResult;
import com.data.display.model.rich.Rich;
import com.github.pagehelper.Page;

public interface RichService {

	Page<Rich> getRichData(DataTableDTO dataTableDTO, Rich rich);

	DataTableResult addRichData(Rich rich);

	Rich selectById(String id);

	DataTableResult editRichData(Rich rich);

	List<Rich> selectRichByOthers(Rich rich);

}
