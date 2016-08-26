package com.trusdom.fdip.services;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.trusdom.fdip.common.Constants;
import com.trusdom.fdip.common.Json;
import com.trusdom.fdip.form.AccountForm;
import com.trusdom.fdip.form.mcip.OpenThsAccountForm;
import com.trusdom.fdip.mappers.Account3RDMapper;
import com.trusdom.fdip.mappers.AccountFundAmountMapper;
import com.trusdom.fdip.mappers.AccountMapper;
import com.trusdom.fdip.mappers.TradeMapper;
import com.trusdom.fdip.model.Account;
import com.trusdom.fdip.model.Account.Status;
import com.trusdom.fdip.model.Account3RD;
import com.trusdom.fdip.model.AccountFundAmount;
import com.trusdom.fdip.model.Channel;
import com.trusdom.fdip.model.Fund;
import com.trusdom.fdip.model.Income;
import com.trusdom.fdip.model.IncomeRate;
import com.trusdom.fdip.model.account3RD.ThsAccount;
import com.trusdom.fdip.services.mcip.McipThsFundService;
import com.trusdom.fdip.spring.configs.RedisConfig;
import com.trusdom.fdip.vo.BalanceVo;
import com.trusdom.fdip.vo.BaseVo;
import com.trusdom.fdip.vo.IncomeVo;
import com.trusdom.fdip.vo.OpenAccountResultVo;
import com.trusdom.fdip.vo.ResultVo;
import com.trusdom.fdip.vo.TradingVo;
import com.trusdom.fdip.vo.UserVo;

@Service
public class AccountService {
	private static final Logger LOGGER = LoggerFactory.getLogger(AccountService.class);
	
	@Autowired
	RestTemplate template;
	
	@Autowired
	AccountMapper accountMapper;
	
	@Autowired
	Account3RDMapper account3RDMapper;
	
	@Autowired
	ChannelService channelService;
	
	@Autowired
	FundService fundService;
	
	@Autowired
	AccountFundAmountMapper accountFundAmountMapper;

	@Autowired
	TradeMapper tradeMapper;
	
	@Autowired
	Account3RDService account3RDService;
	
	@Autowired
	IncomeService incomeService;
	
	@Autowired
	McipThsFundService mcipThsFundService;
	
	/**
	 * 根据accountNo查询账户
	 * @param accountNo
	 * @return
	 */
	public Account queryAccountByAccountNo(String accountNo){
		String key=""+(Constants.REDISBASEKEY+"Account"+accountNo).hashCode();
		Account account=(Account) RedisConfig.getObject(key);
		if(account==null&&(account=accountMapper.findByAccountNo(accountNo))!=null&&account.getStatus().equals(Status.ENABLE)){
			RedisConfig.set(key, account);
		}
		return account;
	}
	
	private Account openLocalAccount(AccountForm form){
		Account account = queryAccountByAccountNo(form.getAccountNo());
		if (null != account){
			LOGGER.info("摊位号为[{}]的用户本地基金账户已开通无需再次开通! 对应帐号信息为:[{}]", form.getAccountNo(), account);
			
		}else{
			account = new Account();
			account.setAccountNo(form.getAccountNo());
			account.setBankAccountNo(form.getBankAccountNo());
			account.setTradePwd(DigestUtils.md5Hex(form.getAccountNo()));
			account.setBankAccount(form.getBankAccount());
			account.setName(form.getBankAccountName());
			account.setCredential(form.getIdentityNo());
			account.setCreateTime(new Date());
			account.setUpdateTime(new Date());
			accountMapper.insert(account);
		}
		return account;
	}
	/**
	 * 开户
	 * @param form
	 * @throws Exception 
	 */
	@Transactional(isolation=Isolation.SERIALIZABLE)
	public BaseVo openAccount(AccountForm form) throws Exception{
		Account account = openLocalAccount(form);
		//FIXME:调用MCIP进行同花顺账户开户; 后续需要根据渠道不同开通不同基金公司的账户
		BaseVo baseVo = mcipThsFundService.openAccount(OpenThsAccountForm.from(form));
		if (!baseVo.isSuccess()){
			return baseVo;
		}
		LOGGER.info("同花顺开户结果: {}",baseVo);
		OpenAccountResultVo result = (OpenAccountResultVo) baseVo;
		ThsAccount thsAccount = Json.fromJson(Json.parse(result.getAccountInfo()), ThsAccount.class);
		Channel channel = channelService.QueryByCode(form.getChannel());
		
		Account3RD account3RD = new Account3RD();
		account3RD.setAccount(account);
		account3RD.setChannel(channel);
		account3RD.setAccountInfo(Json.toJson(thsAccount).toString());
		account3RD.setUserId(thsAccount.getTransActionAccountId());
		account3RDMapper.insert(account3RD);
		return baseVo;
	}
	
	
	@SuppressWarnings("unused")
	private BigDecimal amountOfModify(ThsAccount account){
		BigDecimal amount = new BigDecimal(0);
		List<TradingVo> processings = mcipThsFundService.processing(account.getTransActionAccountId(), account.getCustId());
		for (TradingVo tradingVo : processings) {
			amount = amount.add(new BigDecimal(tradingVo.getApplicationAmount()));
		}
		return amount;
	}
	/**
	 * 获取账户余额,对开薪工资来说即有息余额
	 * 同步返回总有息余额 和 当前可赎回份额
	 * 有息余额>=可赎回份额
	 * @param accountNo
	 * @param channelCode
	 * @param fundCode
	 * @return
	 */
	public BaseVo balance(String accountNo, String channelCode, String fundCode){
		BalanceVo result = new BalanceVo();
		Account account = queryAccountByAccountNo(accountNo);
		if (null == account){
			return result.createError("account.notOpenAccount");
		}
		Channel channel = channelService.QueryByCode(channelCode);
		if (null == channel){
			return result.createError("channel.illegal");
		}
		Fund fund = fundService.queryByCode(fundCode);
		if (null == fund){
			return result.createError("fund.illegal");
		}
		fund=mcipThsFundService.fundStatus(fund);
		AccountFundAmount accountFundAmount = accountFundAmountMapper.findByAccountAndChannelAndFund(account.getId(), channel.getId(), fund.getId());
		if (null != accountFundAmount){
			result.setBalance(accountFundAmount.getAmount().add(accountFundAmount.getInterestAmount()));
//			Account3RD account3rd = account3RDService.queryByChannlAndAccount(channel, account);
//			BigDecimal amountOfModifiable = tradeMapper.getTotalAmountModifiable(account3rd.getId(), fund.getId(), new Date());
//			ThsAccount thsAccount = Json.fromJson(Json.parse(account3rd.getAccountInfo()), ThsAccount.class);
//			BigDecimal amountOfModifiable = amountOfModify(thsAccount);
//			Income income = mcipThsFundService.income(fundCode, thsAccount, Constants.SDF_DATE.format(new Date()));
//			if (null != income){
//				result.setRedeemable(amountOfModifiable.compareTo(income.getInterestAmount())>=0?amountOfModifiable:income.getInterestAmount());
//				result.setMaxRedeemable(amountOfModifiable.compareTo(income.getInterestAmount())>=0?amountOfModifiable.subtract(fund.getFstMinPurchaseAmount()):
//					income.getInterestAmount().subtract(fund.getMinRedemption()));
//			}else{
//				result.setRedeemable(amountOfModifiable);
//				result.setMaxRedeemable(amountOfModifiable.subtract(fund.getFstMinPurchaseAmount()));
//			}
//			if(null!=income&&null!=income.getInterestAmount()){
//				result.setRedeemable(income.getInterestAmount());
//				result.setMaxRedeemable(income.getInterestAmount().compareTo(fund.getMinRedemption())>=0?income.getInterestAmount().subtract(fund.getMinRedemption()):new BigDecimal("0.00"));
//			}
			result.setRedeemable(accountFundAmount.getInterestAmount());
			result.setMaxRedeemable(accountFundAmount.getInterestAmount().compareTo(fund.getMinRedemption())>=0?accountFundAmount.getInterestAmount().subtract(fund.getMinRedemption()):new BigDecimal("0.00"));
		}
		return result;
	}
	
	public BaseVo income(String accountNo, String channelCode, String fundCode){
		IncomeVo result = new IncomeVo();
		Account account = queryAccountByAccountNo(accountNo);
		if (null == account){
			return result.createError("account.notOpenAccount");
		}
		Channel channel = channelService.QueryByCode(channelCode);
		if (null == channel){
			return result.createError("channel.illegal");
		}
		Fund fund = fundService.queryByCode(fundCode);
		if (null == fund){
			return result.createError("fund.illegal");
		}
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String ystday = Constants.SDF_DATE.format(cal.getTime());
		AccountFundAmount accountFundAmount = accountFundAmountMapper.findByAccountAndChannelAndFund(account.getId(), channel.getId(), fund.getId());
		if (null != accountFundAmount){
			result.setIncome(new BigDecimal(accountFundAmount.getIncome().toPlainString()));
			Income ystdayIncome = incomeService.findIncomeByDay(account.getId(), channel.getId(), fund.getId(), ystday);
			if (null != ystdayIncome){
				result.setYstdayIncome(new BigDecimal(ystdayIncome.getIncome().toPlainString()));
			}
		}
		
		//昨日万份收益; TODO: 遇到节假日,将同步不到昨日万份收益, 这里可以返回最后当前最后一日万份收益
		IncomeRate ystdayRate = incomeService.findIncomeRateByDay(channel.getId(), fund.getId(), ystday);
		if (null != ystdayRate){
			result.setMillionIncome(new BigDecimal(ystdayRate.getMillionIncomeRate().toPlainString()));
		}
		return result;
	}
	
	public ResultVo<Income> historyIncome(String accountNo, String channelCode, String fundCode, String startTime, String endTime){
		ResultVo<Income> result = new ResultVo<Income>();
		Account account = queryAccountByAccountNo(accountNo);
		if (null == account){
			return result.createError("account.notOpenAccount");
		}
		Channel channel = channelService.QueryByCode(channelCode);
		if (null == channel){
			return result.createError("channel.illegal");
		}
		Fund fund = fundService.queryByCode(fundCode);
		if (null == fund){
			return result.createError("fund.illegal");
		}
		if (StringUtils.isBlank(startTime)){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -30);
			startTime = Constants.SDF_DATE.format(cal.getTime());
		}
		if (StringUtils.isBlank(endTime)){
			Calendar cal = Calendar.getInstance();
			cal.add(Calendar.DATE, -1);
			endTime = Constants.SDF_DATE.format(cal.getTime());
		}
		List<Income> incomes = incomeService.findIncomeByDates(account.getId(), channel.getId(), fund.getId(), startTime, endTime);
		result.setResults(incomes);
		return result;
	}
	
	public ResultVo<IncomeRate> incomeRate(String channelCode, String fundCode, String startTime, String endTime){
		ResultVo<IncomeRate> result = new ResultVo<IncomeRate>();
		Channel channel = channelService.QueryByCode(channelCode);
		if (null == channel){
			return result.createError("channel.illegal");
		}
		Fund fund = fundService.queryByCode(fundCode);
		if (null == fund){
			return result.createError("fund.illegal");
		}
		List<IncomeRate> rates = incomeService.findIncomeRateByDates(channel.getId(), fund.getId(), startTime, endTime);
		result.setResults(rates);
		return result;
	}
	
	public long findAccountIdBy3RD(String account3RD){
		return account3RDMapper.findAccountByAccount3RD(account3RD);
	}
	
	public Account findAccountBy3RD(String account3RD){
		return accountMapper.findById(findAccountIdBy3RD(account3RD));
	}
	
	public void enableOrDisable(Long id, Status status){
		accountMapper.enableOrDisable(id, status);
	}
}
