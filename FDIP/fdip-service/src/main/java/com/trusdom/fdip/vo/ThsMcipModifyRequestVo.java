/**
 * 
 */
package com.trusdom.fdip.vo;

import com.trusdom.fdip.common.BaseCase;

/**
 * @author  zhihuayang E-mail:425273175@qq.com 
 * @date 创建时间：2016年6月1日 下午2:45:06
 * @version 1.0 
 * @parameter  
 * @return  
*/
/**
 * @author lenovo
 *
 */
public class ThsMcipModifyRequestVo extends BaseCase{

	private String userid;

	private String transActionAccountId;

	private String tradePassword;

	private String appsheetSerialNo;

	private String newApplicationAmount;
	
	private String txTraceNo;

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getTransActionAccountId() {
		return transActionAccountId;
	}

	public void setTransActionAccountId(String transActionAccountId) {
		this.transActionAccountId = transActionAccountId;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
	}

	public String getAppsheetSerialNo() {
		return appsheetSerialNo;
	}

	public void setAppsheetSerialNo(String appsheetSerialNo) {
		this.appsheetSerialNo = appsheetSerialNo;
	}

	public String getNewApplicationAmount() {
		return newApplicationAmount;
	}

	public void setNewApplicationAmount(String newApplicationAmount) {
		this.newApplicationAmount = newApplicationAmount;
	}

	public String getTxTraceNo() {
		return txTraceNo;
	}

	public void setTxTraceNo(String txTraceNo) {
		this.txTraceNo = txTraceNo;
	}

}
