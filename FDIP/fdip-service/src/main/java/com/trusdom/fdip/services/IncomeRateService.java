/**
 * 
 */
package com.trusdom.fdip.services;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.trusdom.fdip.common.Config;
import com.trusdom.fdip.common.HttpUtil;
import com.trusdom.fdip.common.Json;
import com.trusdom.fdip.form.IncomeRateForm;
import com.trusdom.fdip.mappers.IncomeRateMapper;
import com.trusdom.fdip.model.Fund;
import com.trusdom.fdip.model.IncomeRate;
import com.trusdom.fdip.vo.IncomeRateVo;
import com.trusdom.fdip.vo.ResultVo;
import com.trusdom.fdip.vo.ThsMcipIncomeRateResponseVo;

/**
 * @author  zhihuayang E-mail:425273175@qq.com 
 * @date 创建时间：2016年6月16日 下午4:48:24
 * @version 1.0 
 * @parameter  
 * @return  
*/
/**
 * @author lenovo
 *
 */

@Service
public class IncomeRateService {

	private static Logger log = LoggerFactory.getLogger(BuyFundService.class);

	private static final String MCIPURL = Config.getConfigByKey("mcipBaseUrl", "");

	private static SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");

	@Autowired
	IncomeRateMapper incomeRateMapper;

	@Autowired
	FundService fundService;

	@Autowired
	ChannelService channelService;

	private String getDay() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		return df.format(cal.getTime());
	}

	/**
	 * 七日年化万份收益同步
	 */
	@Transactional
	public void addIncomeRate(String fundCode, String queryDate) {
		Fund fund = fundService.queryByCode(fundCode);
		if(incomeRateMapper.findByDay(fund.getChannel().getId(), fund.getId(), queryDate)==null){
			try {
				IncomeRate incomeRate = new IncomeRate();
				incomeRate.setCreateTime(new Date());
				ThsMcipIncomeRateResponseVo thsMcipIncomeRateResponseVo = HttpUtil.mcipIncomeRate(fundCode, queryDate);
				incomeRate.setAnnualIncome7d(new BigDecimal(thsMcipIncomeRateResponseVo.getData().getYield()));
				incomeRate.setMillionIncomeRate(new BigDecimal(thsMcipIncomeRateResponseVo.getData().getFundIncome()));
				incomeRate.setChannel(fund.getChannel().getId());
				incomeRate.setFund(fund.getId());
				incomeRate.setDay(queryDate);
				incomeRateMapper.save(incomeRate);
			} catch (Exception e) {
				// TODO Auto-generated catch block
			}
		}
	}

	/**
	 * 获取七日年化收益列表
	 * 
	 * @param incomeRateForm
	 * @return
	 */
	public ResultVo<IncomeRateVo> find(IncomeRateForm incomeRateForm) {
		ResultVo<IncomeRateVo> result = new ResultVo<IncomeRateVo>();
		List<IncomeRateVo> results = new ArrayList<IncomeRateVo>();
		Fund fund = fundService.queryByCode(incomeRateForm.getFundCode());
		if (fund == null) {
			return result.createError("fund.notfund");
		}
		List<IncomeRate> list = incomeRateMapper.findByDate(fund.getChannel().getId(), fund.getId(),
				incomeRateForm.getStartTime(), incomeRateForm.getEndTime());
		for (IncomeRate incomeRate : list) {
			IncomeRateVo incomeRateVo = new IncomeRateVo();
			incomeRateVo.setDay(incomeRate.getDay());
			incomeRateVo.setAnnualIncome7d(incomeRate.getAnnualIncome7d());
			incomeRateVo.setMillionIncomeRate(incomeRate.getMillionIncomeRate());
			results.add(incomeRateVo);
			incomeRate = null;
		}
		result.setResults(results);
		return result;
	}

}