/**
 * 
 */
package com.trusdom.fdip.vo;

import com.trusdom.fdip.common.BaseCase;

/**
 * @author  zhihuayang E-mail:425273175@qq.com 
 * @date 创建时间：2016年6月1日 上午9:25:56
 * @version 1.0 
 * @parameter  
 * @return  
*/
/**
 * @author lenovo
 *
 */
public class ThsMcipFastRedeRequestVo extends BaseCase{
	
	private String transActionAccountId;

	private String shareType = "0";

	private String fundCode;

	private String tradePassword;

	private String money;

	private String largeRedemptionSelect = "1";

	private String mobileTelNo;

	private String operator="365";
	
	private String userid;
	
	private String txTraceNo;

	public String getTransActionAccountId() {
		return transActionAccountId;
	}

	public void setTransActionAccountId(String transActionAccountId) {
		this.transActionAccountId = transActionAccountId;
	}

	public String getShareType() {
		return shareType;
	}

	public void setShareType(String shareType) {
		this.shareType = shareType;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		this.money = money;
	}

	public String getLargeRedemptionSelect() {
		return largeRedemptionSelect;
	}

	public void setLargeRedemptionSelect(String largeRedemptionSelect) {
		this.largeRedemptionSelect = largeRedemptionSelect;
	}

	public String getMobileTelNo() {
		return mobileTelNo;
	}

	public void setMobileTelNo(String mobileTelNo) {
		this.mobileTelNo = mobileTelNo;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTxTraceNo() {
		return txTraceNo;
	}

	public void setTxTraceNo(String txTraceNo) {
		this.txTraceNo = txTraceNo;
	}

}
