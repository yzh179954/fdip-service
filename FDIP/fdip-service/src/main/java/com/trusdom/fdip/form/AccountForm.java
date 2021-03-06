package com.trusdom.fdip.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.trusdom.fdip.common.Json;

/**
 * 开户
 * @author octopus
 *
 */
public class AccountForm {
	@NotEmpty(message="渠道编号不能为空")
	private String channel;								//渠道编号
	@NotEmpty(message="摊位号不能为空")
	private String accountNo;								//摊位号
	@NotEmpty(message="银行子账不能为空")
	private String bankAccountNo;							//银行子账号
	@NotEmpty(message="银行账户名不能为空")
	private String bankAccountName;							//银行账户名
	@NotEmpty(message="银行卡号不能为空")
	private String bankAccount;								//银行卡号
	@NotEmpty(message="银行代码不能为空")
	private String bankCode;								//银行代码（同花顺提供）
	@NotEmpty(message="银行名字不能为空")
	private String bankName;								//银行名字（同花顺提供）
	@NotEmpty(message="证件类型不能为空")
	@Pattern(regexp="0",message="证件类型不正确")
	private String identityType;							//证件类型
	@NotEmpty(message="证件号码不能为空")
	private String identityNo;								//证件号码
	@NotEmpty(message="邮件地址不能为空")
	@Email(message="邮件地址格式不正确")
	private String email;									//邮件地址
	@NotEmpty(message="省不能为空")
	private String province;								//省（同花顺提供）
	@NotEmpty(message="市不能为空")
	private String city;									//市（同花顺提供）
	@NotEmpty(message="联行号不能为空")
	private String thsBranchCode;							//同花顺联行号标志（同花顺提供）
	@NotEmpty(message="手机号不能为空")
	@Length(min=11,max=11,message="非法的手机号码")
	private String phone;									//手机号
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getBankAccountNo() {
		return bankAccountNo;
	}
	public void setBankAccountNo(String bankAccountNo) {
		this.bankAccountNo = bankAccountNo;
	}
	public String getBankAccountName() {
		return bankAccountName;
	}
	public void setBankAccountName(String bankAccountName) {
		this.bankAccountName = bankAccountName;
	}
	public String getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getIdentityType() {
		return identityType;
	}
	public void setIdentityType(String identityType) {
		this.identityType = identityType;
	}
	public String getIdentityNo() {
		return identityNo;
	}
	public void setIdentityNo(String identityNo) {
		this.identityNo = identityNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getThsBranchCode() {
		return thsBranchCode;
	}
	public void setThsBranchCode(String thsBranchCode) {
		this.thsBranchCode = thsBranchCode;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	@Override
	public String toString() {
		return Json.toJson(this).toString();
	}
	
}
