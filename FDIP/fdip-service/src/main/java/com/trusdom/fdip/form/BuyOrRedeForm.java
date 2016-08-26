/**
 * 
 */
package com.trusdom.fdip.form;

import java.math.BigDecimal;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import com.trusdom.fdip.common.Json;

/**
 * @author zhihuayang E-mail:425273175@qq.com
 * @date 创建时间：2016年5月17日 下午1:43:05
 * @version 1.0
 * @parameter
 * @return
 */

public class BuyOrRedeForm {

	@NotBlank(message="渠道不能为空")
	private String channel;
	
	@NotBlank(message = "基金代码不能为空")
	private String fundCode;

	@NotNull(message="份额不能为空")
	@Digits(fraction = 2, integer = 100000)
	private BigDecimal amount;

	@NotBlank(message = "交易卡号不能为空")
	private String accountNo;

	@NotBlank(message = "交易流水号不能为空")
	private String tradeNo;
	
	@NotBlank(message="手机号不能为空")
	@Length(max=11,min=11,message="非法的手机号码")
	private String phone;

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public String getFundCode() {
		return fundCode;
	}

	public void setFundCode(String fundCode) {
		this.fundCode = fundCode;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
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

	public static void main(String[] args) {
		BuyOrRedeForm buyForm=new BuyOrRedeForm();
		buyForm.setAmount(new BigDecimal(1.206).setScale(2,BigDecimal.ROUND_HALF_UP));
		String result=Json.toJson(buyForm).toString();
		buyForm=Json.fromJson(Json.parse(result),BuyOrRedeForm.class);
		System.out.println(buyForm.getAmount());
	}
}
