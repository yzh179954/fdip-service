package com.trusdom.fdip.model.account3RD;

/**
 * 同花顺账户详情
 * @author octopus
 *
 */
public class ThsAccount  {
	
	private String capitalMethod; //支付渠道
	
	private String certificateNo; //身份证号
	
	private String transActionAccountId; //交易帐号
	
	private String bankAccountName; //账户名
	
	private String custId; //客户号
	
	private String bankName; //银行名字
	
	private String certificateType = "0"; //证件类型; 定值0表示身份证
	
	private String bankAccount; // 银行卡号
	
	private String txtraceNo; //请求流水
	
	private String appsheetSerialNo ; //申请单号

	public String getCapitalMethod() {
		return capitalMethod;
	}

	public void setCapitalMethod(String capitalMethod) {
		this.capitalMethod = capitalMethod;
	}

	public String getCertificateNo() {
		return certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getTransActionAccountId() {
		return transActionAccountId;
	}

	public void setTransActionAccountId(String transActionAccountId) {
		this.transActionAccountId = transActionAccountId;
	}

	public String getBankAccountName() {
		return bankAccountName;
	}

	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(String certificateType) {
		this.certificateType = certificateType;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getTxtraceNo() {
		return txtraceNo;
	}

	public void setTxtraceNo(String txtraceNo) {
		this.txtraceNo = txtraceNo;
	}

	public String getAppsheetSerialNo() {
		return appsheetSerialNo;
	}

	public void setAppsheetSerialNo(String appsheetSerialNo) {
		this.appsheetSerialNo = appsheetSerialNo;
	}

}
