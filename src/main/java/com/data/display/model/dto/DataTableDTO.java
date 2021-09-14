package com.data.display.model.dto;

import java.util.List;
import java.util.Map;

/**
 * @Author: CYN
 * @Date: 2019/1/23 11:16
 * @Description: dataTable 分页封装
 */
public class DataTableDTO {
    /**
     * 排序
     */
    private List<Map<String,String>> order;
    /**
     * 前端传入直接返回
     */
    private Integer draw;
    /**
     *  数量
     */
    private Integer start;
    /**
     * 每页显示数量
     */
    private Integer length;
    /**
     * 页数
     */
    private Integer pageNum;

    public Integer getPageNum() {
        if(start==0){
         return 1;
        }
        return start/length+1;
    }
    public List<Map<String, String>> getOrder() {
        return order;
    }

    public void setOrder(List<Map<String, String>> order) {
        this.order = order;
    }

    public DataTableDTO() {
    }
    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }
}
