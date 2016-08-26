package com.trusdom.fdip.spring.configs;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.logging.stdout.StdOutImpl;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.transaction.SpringManagedTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import com.trusdom.fdip.mappers.Account3RDMapper;
import com.trusdom.fdip.mappers.AccountFundAmountMapper;
import com.trusdom.fdip.mappers.AccountMapper;
import com.trusdom.fdip.mappers.CapitalAllocationMapper;
import com.trusdom.fdip.mappers.ChannelMapper;
import com.trusdom.fdip.mappers.FundMapper;
import com.trusdom.fdip.mappers.HolidayMapper;
import com.trusdom.fdip.mappers.IncomeMapper;
import com.trusdom.fdip.mappers.IncomeRateMapper;
import com.trusdom.fdip.mappers.SgeditMapper;
import com.trusdom.fdip.mappers.TradeMapper;

/**
 * 
* @ClassName: DataConfig 
* @Description: TODO(***)
* @author zjb 
* @date May 4, 2016 6:01:08 PM
*
 */
@org.springframework.context.annotation.Configuration
@ImportResource("classpath:quartz-config.xml")
@MapperScan("com.trusdom.fdip.mappers")
public class DataConfig {
	private static final Logger logger = LoggerFactory.getLogger(DataConfig.class);
	static Properties propts = new Properties();
	static {
		try {
			propts.load(DataConfig.class.getResourceAsStream("/jdbc/jdbc.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			logger.error("jdbc.properties load failed!! system exit!!");
			System.exit(0);
		}
	}
	@Bean(name="dataSource")
	public DataSource dataSource() throws PropertyVetoException{
		ComboPooledDataSource dataSource = new ComboPooledDataSource();
		dataSource.setDriverClass(propts.getProperty("jdbc.driverClassName", ""));
		dataSource.setJdbcUrl(propts.getProperty("jdbc.url", ""));
        dataSource.setUser(propts.getProperty("jdbc.username", ""));
        dataSource.setPassword(propts.getProperty("jdbc.password", ""));
        dataSource.setMinPoolSize(Integer.valueOf(propts.getProperty("jdbc.minPoolSize", "3")));
        dataSource.setMaxPoolSize(Integer.valueOf(propts.getProperty("jdbc.maxPoolSize", "10")));
        dataSource.setInitialPoolSize(Integer.valueOf(propts.getProperty("jdbc.initialPoolSize", "3")));
        dataSource.setIdleConnectionTestPeriod(18000);
        dataSource.setTestConnectionOnCheckin(true);
        dataSource.setTestConnectionOnCheckout(true);
        dataSource.setAutomaticTestTable("test");
        return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws PropertyVetoException{
		TransactionFactory transactionFactory = new SpringManagedTransactionFactory();
		Environment environment =new Environment("development", transactionFactory, dataSource());
		Configuration configuration = new Configuration(environment);
		configuration.setCacheEnabled(false);
		configuration.setLazyLoadingEnabled(true);
		configuration.setAggressiveLazyLoading(true);
		configuration.setLogImpl(StdOutImpl.class);
		configuration.addMappers("com.trusdom.fdip.mappers");
		SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
		return sqlSessionFactory;
	}
	
	@Bean(name="sqlSession")
	public SqlSessionTemplate sqlSessionTemplate() throws PropertyVetoException{
		SqlSessionTemplate sqlSessionTemplate = new SqlSessionTemplate(sqlSessionFactory());
		return sqlSessionTemplate;
	}
	
	@Bean(name="transactionManager")
	public PlatformTransactionManager transactionManager() throws PropertyVetoException {
		DataSourceTransactionManager dsTransactionManager = new DataSourceTransactionManager(dataSource());
		return dsTransactionManager;
	}
	
	/*@Bean
	public AccountMapper getAccountMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(AccountMapper.class);
	}
	
	@Bean(name={"account3RDMapper"})
	public Account3RDMapper getAccoun3RDtMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(Account3RDMapper.class);
	}
	
	@Bean(name={"channelMapper"})
	public ChannelMapper getChannelMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(ChannelMapper.class);
	}
	
	@Bean(name={"fundMapper"})
	public FundMapper getFundMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(FundMapper.class);
	}
	
	@Bean(name={"holidayMapper"})
	public HolidayMapper getHolidayMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(HolidayMapper.class);
	}
	
	@Bean(name="tradeMapper")
	public TradeMapper getTradeMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(TradeMapper.class);
	}
	
	@Bean(name={"accountFundAmountMapper"})
	public AccountFundAmountMapper getAccountFundAmountMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(AccountFundAmountMapper.class);
	}
	
	@Bean
	public SgeditMapper getSgeditMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(SgeditMapper.class);
	}
	
	@Bean
	public IncomeMapper getIncomeMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(IncomeMapper.class);
	}
	
	@Bean
	public IncomeRateMapper getIncomeRateMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(IncomeRateMapper.class);
	}
	
	@Bean
	public CapitalAllocationMapper getCapitalAllocationMapper() throws PropertyVetoException{
		return sqlSessionTemplate().getMapper(CapitalAllocationMapper.class);
	}*/
}
