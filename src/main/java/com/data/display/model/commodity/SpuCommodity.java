package com.data.display.model.commodity;

import com.data.display.model.dto.ParentComment;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class SpuCommodity extends ParentComment implements Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 主键
	 */
	private Integer id; 
	/**
	 * spu编号
	 */
	private String spuid;
	/**
	 * spu名称
	 */
	private String spu_name;
	/**
	 * 关键字
	 */
	private String spu_keyword;
	/**
	 * spu规格
	 */
	private String spu_spec;
	/**
	 * 分类ID
	 */
	private Integer catagory_id;
	/**
	 * 供应商ID
	 */
	private Integer s_id;
	/**
	 * 品牌名称
	 */
	private String brand_name;
	/**
	 * 一级分类编号
	 */
 	private Integer first_category;
 	/**
	 * 二级分类编号
	 */
	private Integer second_category;
	/**
	 * 三级分类编号
	 */
	private Integer third_category;
	/**
	 * 一级分类
	 */
	private String first_category_name;
	/**
	 * 二级分类
	 */
	private String second_category_name;
	/**
	 * 三级分类名称
	 */
	private String third_category_name;
	/**
	 * 产品主图
	 */
	private String image;
	/**
	 * 轮播图
	 */
	private String images;
	/**
	 * 视频地址
	 */
	private String video_url;
	/**
	 * 视频截图
	 */
	private String video_image;
	/**
	 * 商品标签
	 */
	private String tag;
	/**
	 * 服务保证 正品保证 价格优惠 （无忧退货 快速退款 免费包邮）
	 */
	private String statement;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 维护状态(0,未维护；1,开始维护；2 维护中)
	 */
	private Integer maintain_status;
	/**
	 * 拼团时长（小时单位）
	 */
	private BigDecimal valid_duration;
	
	private String list;
	
	private String topUserId;
	/**
	 * 商品副标题
	 */
	private String spu_subheading;
	/**
	 * 首页banner图片
	 */
	private String banner_image;
	/**
	 * 是否是首页banner 0  否 1 是
	 */
	private Integer is_banner;
	/**
	 * 是否置顶 0  否 1 是
	 */
	private Integer is_top;
	/**
	 * 更新时间
	 */
	private Date update_time;
	/**
	 * 供货价
	 */
	private BigDecimal supply_price;
	/**
	 * 3人团价格
	 */
	private BigDecimal group_price_three;
	/**
	 * 5人团价格
	 */
	private BigDecimal group_price_five;
	/**
	 * 8人团
	 */
	private BigDecimal group_price_eight;
	/**
	 *显示用的市场价 
	 */
	private BigDecimal market_price;
	/**
	 * 单买价格
	 */
	private BigDecimal price;
	/**
	 *佣金 
	 */
	private BigDecimal commission;
	/**
	 * 满减
	 */
	private BigDecimal reduce;
	/**
	 * 销量
	 */
	private Integer sale_amount;
	/**
	 * 0 上架  1下架
	 * @return
	 */
	private Integer on_sale;
	/**
	 * 服务费
	 */
	private BigDecimal service_price;
	/**
	 * 3人团差额
	 */
	private BigDecimal three_difference_price;
	/**
	 * 5人团差额
	 */
	private BigDecimal five_difference_price;
	/**
	 * 8人团差额
	 */
	private BigDecimal eight_difference_price;
	/**
	 * 结算运费续件金额
	 */
	private BigDecimal sub_dis_fee;
	/**
	 * 结算运费续件数
	 */
	private Integer sub_dis_value;
	/**
	 * 结算运费首单金额
	 */
	private BigDecimal default_price;
	/**
	 * 结算运费首单数
	 */
	private Integer default_number;
	
	private SpuDesc spuDesc;
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
	 * 维护开始时间
	 * @return
	 */
	private Date maintain_start_time;
	/**
	 * 维护结束时间
	 * @return
	 */
	private Date maintain_end_time;
	/**
	 * 佣金区间值
	 * @return
	 */
	private String interval_value;
	/**
	 * 支付成功图片
	 * @return
	 */
	private String success_image;
	/**
	 *支付成功动态图
	 * @return
	 */
	private String success_gif_image;


	public String getInterval_value() {
		return interval_value;
	}

	public void setInterval_value(String interval_value) {
		this.interval_value = interval_value;
	}

	public String getSuccess_image() {
		return success_image;
	}

	public void setSuccess_image(String success_image) {
		this.success_image = success_image;
	}

	public String getSuccess_gif_image() {
		return success_gif_image;
	}

	public void setSuccess_gif_image(String success_gif_image) {
		this.success_gif_image = success_gif_image;
	}

	public Date getMaintain_start_time() {
		return maintain_start_time;
	}

	public void setMaintain_start_time(Date maintain_start_time) {
		this.maintain_start_time = maintain_start_time;
	}

	public Date getMaintain_end_time() {
		return maintain_end_time;
	}

	public void setMaintain_end_time(Date maintain_end_time) {
		this.maintain_end_time = maintain_end_time;
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

	public String getSpu_keyword() {
		return spu_keyword;
	}

	public void setSpu_keyword(String spu_keyword) {
		this.spu_keyword = spu_keyword;
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
	public BigDecimal getThree_difference_price() {
		return three_difference_price;
	}
	public void setThree_difference_price(BigDecimal three_difference_price) {
		this.three_difference_price = three_difference_price;
	}
	public BigDecimal getFive_difference_price() {
		return five_difference_price;
	}
	public void setFive_difference_price(BigDecimal five_difference_price) {
		this.five_difference_price = five_difference_price;
	}
	public BigDecimal getEight_difference_price() {
		return eight_difference_price;
	}
	public void setEight_difference_price(BigDecimal eight_difference_price) {
		this.eight_difference_price = eight_difference_price;
	}
	public BigDecimal getService_price() {
		return service_price;
	}
	public void setService_price(BigDecimal service_price) {
		this.service_price = service_price;
	}
	public SpuDesc getSpuDesc() {
		return spuDesc;
	}
	public void setSpuDesc(SpuDesc spuDesc) {
		this.spuDesc = spuDesc;
	}
	public Integer getOn_sale() {
		return on_sale;
	}
	public void setOn_sale(Integer on_sale) {
		this.on_sale = on_sale;
	}
	public String getSpu_subheading() {
		return spu_subheading;
	}
	public void setSpu_subheading(String spu_subheading) {
		this.spu_subheading = spu_subheading;
	}
	public String getBanner_image() {
		return banner_image;
	}
	public void setBanner_image(String banner_image) {
		this.banner_image = banner_image;
	}
	public Integer getIs_banner() {
		return is_banner;
	}
	public void setIs_banner(Integer is_banner) {
		this.is_banner = is_banner;
	}
	public Integer getIs_top() {
		return is_top;
	}
	public void setIs_top(Integer is_top) {
		this.is_top = is_top;
	}
	public Date getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(Date update_time) {
		this.update_time = update_time;
	}
	public BigDecimal getSupply_price() {
		return supply_price;
	}
	public void setSupply_price(BigDecimal supply_price) {
		this.supply_price = supply_price;
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
	public BigDecimal getValid_duration() {
		return valid_duration;
	}
	public void setValid_duration(BigDecimal valid_duration) {
		this.valid_duration = valid_duration;
	}
	public Integer getMaintain_status() {
		return maintain_status;
	}
	public void setMaintain_status(Integer maintain_status) {
		this.maintain_status = maintain_status;
	}
	public String getTopUserId() {
		return topUserId;
	}
	public void setTopUserId(String topUserId) {
		this.topUserId = topUserId;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
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
	public String getSpu_name() {
		return spu_name;
	}
	public void setSpu_name(String spu_name) {
		this.spu_name = spu_name;
	}
	public String getSpu_spec() {
		return spu_spec;
	}
	public void setSpu_spec(String spu_spec) {
		this.spu_spec = spu_spec;
	}
	public Integer getCatagory_id() {
		return catagory_id;
	}
	public void setCatagory_id(Integer catagory_id) {
		this.catagory_id = catagory_id;
	}
	public Integer getS_id() {
		return s_id;
	}
	public void setS_id(Integer s_id) {
		this.s_id = s_id;
	}
	public String getBrand_name() {
		return brand_name;
	}
	public void setBrand_name(String brand_name) {
		this.brand_name = brand_name;
	}
	public Integer getFirst_category() {
		return first_category;
	}
	public void setFirst_category(Integer first_category) {
		this.first_category = first_category;
	}
	public Integer getSecond_category() {
		return second_category;
	}
	public void setSecond_category(Integer second_category) {
		this.second_category = second_category;
	}
	public Integer getThird_category() {
		return third_category;
	}
	public void setThird_category(Integer third_category) {
		this.third_category = third_category;
	}
	public String getFirst_category_name() {
		return first_category_name;
	}
	public void setFirst_category_name(String first_category_name) {
		this.first_category_name = first_category_name;
	}
	public String getSecond_category_name() {
		return second_category_name;
	}
	public void setSecond_category_name(String second_category_name) {
		this.second_category_name = second_category_name;
	}
	public String getThird_category_name() {
		return third_category_name;
	}
	public void setThird_category_name(String third_category_name) {
		this.third_category_name = third_category_name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getImages() {
		return images;
	}
	public void setImages(String images) {
		this.images = images;
	}
	public String getVideo_url() {
		return video_url;
	}
	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	public String getVideo_image() {
		return video_image;
	}
	public void setVideo_image(String video_image) {
		this.video_image = video_image;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getStatement() {
		return statement;
	}
	public void setStatement(String statement) {
		this.statement = statement;
	}
	public Date getCreate_time() {
		return create_time;
	}
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	
}
