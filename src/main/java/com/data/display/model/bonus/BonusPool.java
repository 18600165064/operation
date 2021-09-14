package com.data.display.model.bonus;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 分红池
 */
public class BonusPool extends ParentComment implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String spuid;
    private Integer pool_type;
    private BigDecimal amount;
    private BigDecimal input_ratio;
    private Date create_time;

    private String time;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSpuid() {
        return spuid;
    }

    public void setSpuid(String spuid) {
        this.spuid = spuid;
    }

    public Integer getPool_type() {
        return pool_type;
    }

    public void setPool_type(Integer pool_type) {
        this.pool_type = pool_type;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getInput_ratio() {
        return input_ratio;
    }

    public void setInput_ratio(BigDecimal input_ratio) {
        this.input_ratio = input_ratio;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }
}
