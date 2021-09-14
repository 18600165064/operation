package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class Product extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private Integer id;  //主键
	private String skuid; //sku编号
	private String sku_name; //sku名称
	private String sku_image; //'sku主图
	private String spuid; //spu编号
	private String spec; //具体规格
	private Integer stock; //库存
	private Integer s_id; //供应商编号
	private BigDecimal supply_price; //供货价
	private Integer on_sale; //0,上架状态；1,下架状态
	private BigDecimal group_price_three; //3人团价格
	private BigDecimal group_price_five; //5人团价格
	private BigDecimal group_price_eight; //8人团
	private BigDecimal market_price; //显示用的市场价
	private BigDecimal price; //单买价格
	/**
	 * 3人团佣金
	 */
	private BigDecimal commission_price_three;
	/**
	 * 5人团佣金
	 */
	private BigDecimal commission_price_five;
	/**
	 * 8人团佣金
	 */
	private BigDecimal commission_price_eight;
	/**
	 * 返现
	 * @return
	 */
	private BigDecimal refund_price;
	/**
	 * 服务费
	 */
	private BigDecimal service_price;
	private BigDecimal commission; //3人团佣金
	private BigDecimal reduce; //减
	private Integer sale_amount; //销量
	private Date create_time; //创建时间
	private Integer status; //状态
	private BigDecimal sub_dis_fee; //结算运费续件金额
	private Integer sub_dis_value; //结算运费续件数
	/**
	 * 结算运费首单金额
	 */
	private BigDecimal default_price;
	/**
	 * 结算运费首单数
	 */
	private Integer default_number;
	/**
	 * 运费模板id
	 */
	private Integer shipping_id;
	
	private Date update_time; //更新时间
	private Integer is_top; //是否置顶 0  否 1 是
	private Integer maintain_status;   //SPU中是否维护 0 不维护  1维护

	/**
	 * 结算模板id
	 * @return
	 */
	private Integer settle_id;


	public Integer getSettle_id() {
		return settle_id;
	}

	public void setSettle_id(Integer settle_id) {
		this.settle_id = settle_id;
	}

	public BigDecimal getCommission_price_three() {
		return commission_price_three;
	}

	public void setCommission_price_three(BigDecimal commission_price_three) {
		this.commission_price_three = commission_price_three;
	}

	public BigDecimal getCommission_price_five() {
		return commission_price_five;
	}

	public void setCommission_price_five(BigDecimal commission_price_five) {
		this.commission_price_five = commission_price_five;
	}

	public BigDecimal getCommission_price_eight() {
		return commission_price_eight;
	}

	public void setCommission_price_eight(BigDecimal commission_price_eight) {
		this.commission_price_eight = commission_price_eight;
	}

	public BigDecimal getRefund_price() {
		return refund_price;
	}

	public void setRefund_price(BigDecimal refund_price) {
		this.refund_price = refund_price;
	}

	public BigDecimal getService_price() {
		return service_price;
	}
	public void setService_price(BigDecimal service_price) {
		this.service_price = service_price;
	}
	public BigDecimal getDefault_price() {
		return default_price;
	}
	public void setDefault_price(BigDecimal default_price) {
		this.default_price = default_price;
	}
	public Integer getDefault_number() {
		return default_number;
	}
	public void setDefault_number(Integer default_number) {
		this.default_number = default_number;
	}
	public BigDecimal getSub_dis_fee() {
		return sub_dis_fee;
	}
	public void setSub_dis_fee(BigDecimal sub_dis_fee) {
		this.sub_dis_fee = sub_dis_fee;
	}
	public Integer getSub_dis_value() {
		return sub_dis_value;
	}
	public void setSub_dis_value(Integer sub_dis_value) {
		this.sub_dis_value = sub_dis_value;
	}
	public Integer getMaintain_status() {
		return maintain_status;
	}
	public void setMaintain_status(Integer maintain_status) {
		this.maintain_status = maintain_status;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public Integer getIs_top() {
		return is_top;
	}
	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}
	public Integer getShipping_id() {
		return shipping_id;
	}
	public void setShipping_id(Integer shipping_id) {
		this.shipping_id = shipping_id;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getSkuid() {
		return skuid;
	}
	public void setSkuid(String skuid) {
		this.skuid = skuid;
	}
	public String getSku_name() {
		return sku_name;
	}
	public void setSku_name(String sku_name) {
		this.sku_name = sku_name;
	}
	public String getSku_image() {
		return sku_image;
	}
	public void setSku_image(String sku_image) {
		this.sku_image = sku_image;
	}
	public String getSpuid() {
		return spuid;
	}
	public void setSpuid(String spuid) {
		this.spuid = spuid;
	}
	public String getSpec() {
		return spec;
	}
	public void setSpec(String spec) {
		this.spec = spec;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public BigDecimal getSupply_price() {
		return supply_price;
	}
	public void setSupply_price(BigDecimal supply_price) {
		this.supply_price = supply_price;
	}
	public Integer getOn_sale() {
		return on_sale;
	}
	public void setOn_sale(Integer on_sale) {
		this.on_sale = on_sale;
	}
	public BigDecimal getGroup_price_three() {
		return group_price_three;
	}
	public void setGroup_price_three(BigDecimal group_price_three) {
		this.group_price_three = group_price_three;
	}
	public BigDecimal getGroup_price_five() {
		return group_price_five;
	}
	public void setGroup_price_five(BigDecimal group_price_five) {
		this.group_price_five = group_price_five;
	}
	public BigDecimal getGroup_price_eight() {
		return group_price_eight;
	}
	public void setGroup_price_eight(BigDecimal group_price_eight) {
		this.group_price_eight = group_price_eight;
	}
	public BigDecimal getMarket_price() {
		return market_price;
	}
	public void setMarket_price(BigDecimal market_price) {
		this.market_price = market_price;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public BigDecimal getCommission() {
		return commission;
	}
	public void setCommission(BigDecimal commission) {
		this.commission = commission;
	}
	public BigDecimal getReduce() {
		return reduce;
	}
	public void setReduce(BigDecimal reduce) {
		this.reduce = reduce;
	}
	public Integer getSale_amount() {
		return sale_amount;
	}
	public void setSale_amount(Integer sale_amount) {
		this.sale_amount = sale_amount;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	
}
