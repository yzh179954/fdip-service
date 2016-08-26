/**
 * 
 */
package com.trusdom.fdip.vo;

import java.math.BigDecimal;

import com.trusdom.fdip.common.BaseCase;
import com.trusdom.fdip.common.CodeBuilder;

/**
 * @author  zhihuayang E-mail:425273175@qq.com 
 * @date 创建时间：2016年6月23日 下午2:23:59
 * @version 1.0 
 * @parameter  
 * @return  
*/
/**
 * @author lenovo
 *
 */
public class CbipReFundRequestVo extends BaseCase {

	private String channel;

	private BigDecimal amount;
	
	private String orderNo=CodeBuilder.generatorTradeNo(null);

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	
	
}
