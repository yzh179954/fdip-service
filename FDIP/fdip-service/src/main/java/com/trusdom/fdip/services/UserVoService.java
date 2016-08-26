/**
 * 
 */
package com.trusdom.fdip.services;

import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trusdom.fdip.common.Json;
import com.trusdom.fdip.model.Account3RD;
import com.trusdom.fdip.spring.configs.RedisConfig;
import com.trusdom.fdip.vo.ThsAccountVo;
import com.trusdom.fdip.vo.UserVo;

/**
 * @author  zhihuayang E-mail:425273175@qq.com 
 * @date 创建时间：2016年6月1日 上午11:33:54
 * @version 1.0 
 * @parameter  
 * @return  
*/
/**
 * @author lenovo
 *
 */
@Service
public class UserVoService {
	
	private Logger log=Logger.getLogger(UserVoService.class);
	
	@Autowired
	Account3RDService account3RDService;
	
	@Autowired
	AccountService accountService;
	
	/**
	 *  存在账户信息则直接在redis中取值
	 *  不存在则通过基金编码获取渠道id,然后获取第三方账户信息,将第三方信息放在redis当中
	 * @param accountNo
	 * @param fundCode
	 * @return
	 */
	public UserVo finduserVo(String accountNo,Long channelId){
		UserVo userVo=(UserVo) RedisConfig.getObject(accountNo+channelId);
		if(userVo==null){
			//查詢第三方賬戶
			log.info("redis緩存未命中,取mysql");
			Account3RD account3rd=account3RDService.queryByChannlAndAccount(channelId, accountNo);
			ThsAccountVo thsAccountVo=Json.fromJson(Json.parse(account3rd.getAccountInfo()), ThsAccountVo.class);
			userVo=new UserVo();
			userVo.setAccount3RD(account3rd.getId());
			userVo.setTradePassword(accountService.queryAccountByAccountNo(accountNo).getTradePwd());
			userVo.setTransActionAccountId(thsAccountVo.getTransActionAccountId());
			userVo.setUserId(thsAccountVo.getCustId());
			RedisConfig.set(accountNo+channelId, userVo);
		}
		return userVo;
	}

}
