package com.data.display.model.dto;

/**
 * @Author: CYN
 * @Date: 2019/6/4 12:34
 * @Description:
 */
public class DateDTO {
    private String begin_date;
    private String end_date;

    public DateDTO() {
    }

    public DateDTO(String begin_date, String end_date) {
        this.begin_date = begin_date;
        this.end_date = end_date;
    }

    public String getBegin_date() {
        return begin_date;
    }

    public void setBegin_date(String begin_date) {
        this.begin_date = begin_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }
}


