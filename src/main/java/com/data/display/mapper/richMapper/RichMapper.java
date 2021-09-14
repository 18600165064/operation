package com.data.display.mapper.richMapper;

import java.util.List;

import com.data.display.model.rich.Rich;
import com.github.pagehelper.Page;

public interface RichMapper {

	Page<Rich> getRichData(Rich rich);

	Integer addRichData(Rich rich);

	Rich selectById(String id);

	Integer editRichData(Rich rich);

	List<Rich> selectRichByOthers(Rich rich);

}
