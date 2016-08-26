package com.trusdom.fdip.controller;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.trusdom.fdip.services.CommonService;
import com.trusdom.fdip.services.VerifyService;
import com.trusdom.fdip.services.cbip.AccountTransferService;
import com.trusdom.fdip.services.mcip.McipThsFundService;
import com.trusdom.fdip.vo.BaseVo;

@Controller
@RequestMapping("/i")
public class CommonController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CommonController.class);
	
	@Autowired
	CommonService commonService;
	
	@Autowired
	VerifyService verifyService;
	
	@Autowired
	AccountTransferService accountTransferService;
	
	@Autowired
	McipThsFundService mcipThsFundService;
	
	static int staticint = 0;
	int index = 0;
	@RequestMapping("/ths/query/{tradeNo}/transfer")
	public @ResponseBody BaseVo capitalTransferQuery(@PathVariable String tradeNo){
		LOGGER.info("资金划拨查询; tradeNo: [{}]", tradeNo);
		BaseVo result = commonService.capitalTransferQuery(tradeNo);
		LOGGER.info("资金划拨查询, 返回结果: [{}]", result);
		return result;
	}
	
	
	@RequestMapping("/ths/capital/verify")
	public @ResponseBody BaseVo capitalVerify() throws IOException{
		LOGGER.info("同花顺资金对账....");
		BaseVo result = verifyService.capitalVerify();
		LOGGER.info("同花顺资金对账, 返回结果: [{}]", result);
		return result;
	}
	@RequestMapping("/verify/download")
	public @ResponseBody String download(@RequestParam("date")String date){
		verifyService.verify(date);
		return "success";
	}
	
	@RequestMapping("/test")
	public @ResponseBody String test(){
		return "";
	}
}
