
package com.trusdom.fdip.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.trusdom.fdip.common.BaseCase;
import com.trusdom.fdip.common.Json;

/**
 * @author zhihuayang E-mail:425273175@qq.com
 * @date 创建时间：2016年5月17日 下午4:55:48
 * @version 1.0
 * @parameter
 * @return
 */

public class Trade extends BaseCase implements Serializable {

	private static final long serialVersionUID = 8655000504962302999L;

	public enum Status{
		SUCCESS,FAIL, INPROGRESS
	}
	
	public enum TradeType{
		PURCHASE,REDEMPTION
	}
	private Long id;

	private Account account;
	
	private Long thrdAccount;

	private String tradeNo;

	private String extTradeNo;
	
	private String transferNo;
	
	private BigDecimal amount;

	private BigDecimal realAmount;

	private Status status;

	private Date createTime;

	private Date updateTime;

	private String payday;
	
	private TradeType tradeType;
	
	private Date editableDeadline;
	
//	private Date interestTime;
	
	private Channel channel;
	
	private Fund fund;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getThrdAccount() {
		return thrdAccount;
	}

	public void setThrdAccount(Long thrdAccount) {
		this.thrdAccount = thrdAccount;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getExtTradeNo() {
		return extTradeNo;
	}

	public void setExtTradeNo(String extTradeNo) {
		this.extTradeNo = extTradeNo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getRealAmount() {
		return realAmount;
	}

	public void setRealAmount(BigDecimal realAmount) {
		this.realAmount = realAmount;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getPayday() {
		return payday;
	}

	public void setPayday(String payday) {
		this.payday = payday;
	}

	public Date getEditableDeadline() {
		return editableDeadline;
	}

	public void setEditableDeadline(Date editableDeadline) {
		this.editableDeadline = editableDeadline;
	}

//	public Date getInterestTime() {
//		return interestTime;
//	}
//
//	public void setInterestTime(Date interestTime) {
//		this.interestTime = interestTime;
//	}


	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public Fund getFund() {
		return fund;
	}

	public void setFund(Fund fund) {
		this.fund = fund;
	}

	public TradeType getTradeType() {
		return tradeType;
	}

	public void setTradeType(TradeType tradeType) {
		this.tradeType = tradeType;
	}

	public String getTransferNo() {
		return transferNo;
	}

	public void setTransferNo(String transferNo) {
		this.transferNo = transferNo;
	}

	@Override
	public String toString() {
		return Json.toJson(this).toString();
	}
	
}
