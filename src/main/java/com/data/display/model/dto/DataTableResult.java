package com.data.display.model.dto;

/**
 * @Author: CYN
 * @Date: 2019/1/23 11:32
 * @Description: dataTable 返回结果封装
 */
public class DataTableResult {

    private Integer draw;
    private Long recordsTotal;
    private Long recordsFiltered;
    private Object data;
    private Object tableData;
    private String error;

    public DataTableResult() {
        super();
    }

    public DataTableResult(Integer draw, Long recordsTotal, Long recordsFiltered, Object data) {
        this.draw = draw;
        this.recordsTotal = recordsTotal;
        this.recordsFiltered = recordsFiltered;
        this.data = data;
    }

    public Object getTableData() {
        return tableData;
    }

    public void setTableData(Object tableData) {
        this.tableData = tableData;
    }

    public Integer getDraw() {
        return draw;
    }

    public void setDraw(Integer draw) {
        this.draw = draw;
    }

    public Long getRecordsTotal() {
        return recordsTotal;
    }

    public void setRecordsTotal(Long recordsTotal) {
        this.recordsTotal = recordsTotal;
    }

    public Long getRecordsFiltered() {
        return recordsFiltered;
    }

    public void setRecordsFiltered(Long recordsFiltered) {
        this.recordsFiltered = recordsFiltered;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
