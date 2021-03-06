package com.trusdom.fdip.spring.configs;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 
* @ClassName: RedisConfig 
* @Description: TODO(***)
* @author zjb 
* @date May 4, 2016 6:00:56 PM
*
 */
@Configuration
@PropertySource("classpath:redis/redis.properties")
public class RedisConfig {
	private static final Logger logger = LoggerFactory.getLogger(RedisConfig.class);	
	@Autowired
	Environment env;
	
    private static String HOST;
    private int PORT;
    private String PWD;
    //可用连接实例的最大数目，默认值为8；
    //如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
    private int MAX_ACTIVE;
    //控制一个pool最多有多少个状态为idle(空闲的)的jedis实例，默认值也是8。
    private int MAX_IDLE;
    //等待可用连接的最大时间，单位毫秒，默认值为-1，表示永不超时。如果超过等待时间，则直接抛出JedisConnectionException；
    private int MAX_WAIT;
    private int TIMEOUT;
    //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
    private boolean TEST_ON_BORROW = true;
    private static JedisPool jedisPool = null;
    @Bean
    public JedisPool jedisPool(){
    	HOST = env.getProperty("jedis.host","123.56.92.10");
    	PORT = Integer.valueOf(env.getProperty("jedis.port","6379"));
        PWD = env.getProperty("jedis.pwd","");
        MAX_ACTIVE = Integer.valueOf(env.getProperty("jedis.max_active","1024"));
        MAX_IDLE = Integer.valueOf(env.getProperty("jedis.max_idle","200"));
        MAX_WAIT = Integer.valueOf(env.getProperty("jedis.max_wait","-1"));
        TIMEOUT = Integer.valueOf(env.getProperty("jedis.timeout","10000"));
        try {
            JedisPoolConfig config = new JedisPoolConfig();
            config.setMaxIdle(MAX_IDLE);
            config.setMaxTotal(MAX_ACTIVE);
            config.setMaxWaitMillis(MAX_WAIT);
            config.setTestOnBorrow(TEST_ON_BORROW);
            if (StringUtils.isBlank(PWD)){
            	jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT);
            }else{
            	jedisPool = new JedisPool(config, HOST, PORT, TIMEOUT, PWD);
            }
            return jedisPool;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("redis 连接池配置错误,系统退出...");
            //System.exit(1);
        }
        return null;
    }
    
    public static synchronized Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                logger.debug("getJedis--------------------------------->"+resource.toString());
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("redis 连接配置错误,系统退出...");
            //System.exit(1);
        }
        return null;
    }
    
    public static void returnResource(final Jedis jedis){
    	if(jedis!=null){
    		jedisPool.returnResourceObject(jedis);
    	}
    }
    
	/**
	 * 字符串获取
	 * @param key
	 * @return
	 */
	public static String get(String key) {
		Jedis jedis = getJedis();
		String value = jedis.get(key);
		returnResource(jedis);
		return value;
	}
	
	
	/**
	 * 字符串存储
	 * @param key
	 * @param value
	 */
	public static void set(String key,String value){
		Jedis jedis=getJedis();
		jedis.set(key, value);
		returnResource(jedis);
	}

	/**
	 * 自定义对象存储
	 * @param key
	 * @param value
	 */
	public static void set(String key, Object value) {
		Jedis jedis = getJedis();
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(value);
			byte[] byteArray = bos.toByteArray();
			jedis.set(key.getBytes(), byteArray);
			returnResource(jedis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if(oos!=null){
					oos.close();
				}
				if(bos!=null){
					bos.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	
	/**
	 * 自定义对象获取
	 * @param key
	 * @return 
	 * @return
	 */
	public static Object getObject(String key){
		Object object=null;
		Jedis jedis = getJedis();
		System.out.println(jedis);
		byte[] bs = jedis.get(key.getBytes());
		if(bs!=null){
			ByteArrayInputStream bis = new ByteArrayInputStream(bs);
			ObjectInputStream inputStream = null;
			try {
				inputStream = new ObjectInputStream(bis);
				object = inputStream.readObject();
				return object;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				returnResource(jedis);
				try {
					bis.close();
					inputStream.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}

}
