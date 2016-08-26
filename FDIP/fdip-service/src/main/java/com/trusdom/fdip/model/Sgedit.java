/**
 * 
 */
package com.trusdom.fdip.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.trusdom.fdip.common.BaseCase;

/**
 * @author zhihuayang E-mail:425273175@qq.com
 * @date 创建时间：2016年5月20日 上午9:03:57
 * @version 1.0
 * @parameter
 * @return
 */

public class Sgedit extends BaseCase implements Serializable {

	private static final long serialVersionUID = 367103362914051306L;

	private Long id;

	private Long trade;
	
	private String extTradNo;
	
	private BigDecimal amount;

	private Date createTime;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getTrade() {
		return trade;
	}


	public void setTrade(Long trade) {
		this.trade = trade;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getExtTradNo() {
		return extTradNo;
	}

	public void setExtTradNo(String extTradNo) {
		this.extTradNo = extTradNo;
	}

}
