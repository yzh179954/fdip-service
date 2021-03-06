package com.trusdom.fdip.vo;

import java.io.Serializable;

import org.apache.commons.lang3.StringUtils;


/**
 * s
* @ClassName: BaseVo 
* @Description: TODO(***)
* @author zjb 
* @date May 16, 2016 5:52:16 PM
* 
* @param <T>
 */
public class BaseVo extends AbstractVo<BaseVo>{
	
	public static BaseVo error(String msg){
		return error(msg, "");
	}
	
	public static BaseVo error(String msg, String code){
		BaseVo result = new BaseVo();
		result.setSuccess(false);
		result.setErrMsg(msg);
		result.setErrCode(code);
		return result;
	}
	@Override
	public String toString() {
		return toJson().toString();
	}
	
	
}
