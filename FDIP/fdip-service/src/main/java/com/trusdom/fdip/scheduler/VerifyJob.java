package com.trusdom.fdip.scheduler;

import java.util.Calendar;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Service;

import com.trusdom.fdip.common.Constants;
import com.trusdom.fdip.services.VerifyService;

public class VerifyJob extends QuartzJobBean {
	private static final Logger LOGGER = LoggerFactory.getLogger(VerifyJob.class);
	
	@Autowired
	VerifyService verifyService;
	@Override
	protected void executeInternal(JobExecutionContext ctx)
			throws JobExecutionException {
		String day = Constants.SDF_DATE.format(Calendar.getInstance().getTime());
		LOGGER.info("{}-同花顺基金开始对账处理...", day);
		verifyService.verify("");
		LOGGER.info("{}-同花顺基金对账处理完成!", day);
	}
}
