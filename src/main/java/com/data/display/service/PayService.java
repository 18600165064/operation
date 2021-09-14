package com.data.display.service;

import com.data.display.model.dto.DataTableResult;
import com.data.display.model.user.WidthBean;
import org.nutz.lang.util.NutMap;

public interface PayService {

	/**
	 *
	 */
	DataTableResult getAccessToken();

	/**
	 * 发红包
	 * @param bean
	 * @return
	 * @throws Exception
	 */
	String sendHongbao(WidthBean bean) throws Exception;

	/**
	 * 余额提现
	 * @param openid
	 * @param amount
	 * @param payOrderNo
	 * @return
	 */
	DataTableResult balanceWithdraw(int userId,String openid, String amount, String payOrderNo,String remark);
	
	/**
	 * 查询订单
	 * @param payOrderNo
	 * @return
	 */
	DataTableResult balanceQuery(String payOrderNo);
	
	/**
	 * 申请退款
	 * @param totalAmount 订单总金额 单位：分
	 * @param refundAmount 退款金额 单位：分
	 * @param payOrderNo 商户订单号
	 * @param refundNo 退款单号
	 * @return
	 */
	DataTableResult refund(Integer totalAmount, Integer refundAmount, String payOrderNo, String refundNo);

	/**
	 * 公众号申请退款
	 * @param totalAmount 订单总金额 单位：分
	 * @param refundAmount 退款金额 单位：分
	 * @param payOrderNo 商户订单号
	 * @param refundNo 退款单号
	 * @return
	 */
	DataTableResult refundWx(Integer totalAmount, Integer refundAmount, String payOrderNo, String refundNo);

	/**
	 * 退款查询
	 * @param refundNo 退款单号
	 * @return
	 */
	NutMap refundQuery(String refundNo);

	/**
	 * 微信订单状态查询
	 * @param order_no
	 * @return
	 */
	public boolean checkOrderState(String order_no);

	/**
	 * 微信公众平台 订单状态查询
	 * @param order_no
	 * @return
	 */
	public boolean checkWxOrderState(String order_no);


	/**
	 * 订单是否 已支付
	 * @param order_no
	 * @return
	 */
	public boolean ifOrderPayed(String order_no);
}
