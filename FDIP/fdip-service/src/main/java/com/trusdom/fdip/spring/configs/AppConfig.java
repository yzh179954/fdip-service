package com.trusdom.fdip.spring.configs;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.lmax.disruptor.IgnoreExceptionHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;
import com.trusdom.fdip.common.Json;
import com.trusdom.fdip.services.disruptor.event.IncomeSyncEvent;
import com.trusdom.fdip.services.disruptor.handler.AmountHandler;
import com.trusdom.fdip.services.disruptor.handler.IncomeHandler;
import com.trusdom.fdip.services.disruptor.handler.SyncHandler;



/**
 * 
* @ClassName: AppConfig 
* @Description: TODO(***)
* @author zjb 
* @date May 4, 2016 6:01:17 PM
*
 */
@Configuration
//@ComponentScan(basePackages ={"com.trusdom.fdip"})
public class AppConfig {
	private static final Logger LOGGER = LoggerFactory.getLogger(AppConfig.class);
	
    private int ringBufferSize = 2048;
    public static Disruptor<IncomeSyncEvent> disruptor;
    public static RingBuffer<IncomeSyncEvent> ringBuffer;
    
	public AppConfig(){
		configJson();
		disruptor = new Disruptor<IncomeSyncEvent>(IncomeSyncEvent.INCOME_SYNC_EVENT_FACTORY, ringBufferSize, DaemonThreadFactory.INSTANCE);
		disruptor.setDefaultExceptionHandler(new IgnoreExceptionHandler());
		IncomeHandler incomeHandler = new IncomeHandler();
		AmountHandler amountHandler = new AmountHandler();
		SyncHandler syncHandler = new SyncHandler();
		disruptor.handleEventsWith(incomeHandler);
		disruptor.start();
		ringBuffer = disruptor.getRingBuffer();
		LOGGER.info("disruptor start... ...");
	}
	
	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	private void configJson() {
		LOGGER.info("Json config!!!");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(SerializationFeature.WRITE_BIGDECIMAL_AS_PLAIN,true); 
        Json.setObjectMapper(objectMapper);
    }
}

