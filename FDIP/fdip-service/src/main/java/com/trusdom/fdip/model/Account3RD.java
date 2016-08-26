package com.trusdom.fdip.model;

import java.io.Serializable;

import com.trusdom.fdip.common.Json;

/**
 * 第三方平台账号
 * @author octopus
 *
 */
public class Account3RD implements Serializable {
	private Long id;						
	private Channel channel;				//渠道
	private Account account;				//基础账户
	private String accountInfo;				//平台账户详情（JSON）
	private String userId;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Channel getChannel() {
		return channel;
	}
	public void setChannel(Channel channel) {
		this.channel = channel;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public String getAccountInfo() {
		return accountInfo;
	}
	public void setAccountInfo(String accountInfo) {
		this.accountInfo = accountInfo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	@Override
	public String toString() {
		return Json.toJson(this).toString();
	}
	
	
}
