package com.data.display.util.config;

import com.data.display.util.RedisUtil;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Author: CYN
 * @Date: 2018/12/19 11:21
 * @Description:redis连接池配置
 */
@Configuration
public class RedisConfig {
    @Value("${spring.redis.hostName}")
    private String hostName;
    @Value("${spring.redis.port}")
    private int port;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.maxIdle}")
    private int maxIdle;
    @Value("${spring.redis.maxTotal}")
    private int maxTotal;
    @Value("${spring.redis.maxWaitMillis}")
    private long maxWaitMillis;
    @Value("${spring.redis.minEvictableIdleTimeMillis}")
    private long minEvictableIdleTimeMillis;
    @Value("${spring.redis.numTestsPerEvictionRun}")
    private int numTestsPerEvictionRun;
    @Value("${spring.redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;
    @Value("${spring.redis.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.redis.testWhileIdle}")
    private boolean testWhileIdle;

    /**
     * 单机版配置
     *
     * @param @param  jedisPoolConfig
     * @param @return
     * @return JedisConnectionFactory
     * @throws
     * @Title: JedisConnectionFactory
     * @autor lpl
     * @date 2018年2月24日
     */
    @Bean
    public JedisClientConfiguration getJedisClientConfiguration() {
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder JedisPoolingClientConfigurationBuilder = (
                JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        GenericObjectPoolConfig GenericObjectPoolConfig = new GenericObjectPoolConfig();
        GenericObjectPoolConfig.setMaxIdle(maxIdle);
        GenericObjectPoolConfig.setMaxTotal(maxTotal);
        GenericObjectPoolConfig.setMaxWaitMillis(maxWaitMillis);
        GenericObjectPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        GenericObjectPoolConfig.setNumTestsPerEvictionRun(numTestsPerEvictionRun);
        GenericObjectPoolConfig.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        GenericObjectPoolConfig.setTestOnBorrow(testOnBorrow);
        GenericObjectPoolConfig.setTestWhileIdle(testWhileIdle);
        GenericObjectPoolConfig.setMinIdle(100);
        return JedisPoolingClientConfigurationBuilder.poolConfig(GenericObjectPoolConfig).build();
    }

    @Bean
    public JedisConnectionFactory getJedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
        redisStandaloneConfiguration.setDatabase(0);
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        return new JedisConnectionFactory(redisStandaloneConfiguration, getJedisClientConfiguration());
    }

    /**
     * 实例化 RedisTemplate 对象
     *
     * @return
     */
    @Bean
    public RedisTemplate<String, Object> functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate
     * @param factory
     */
    private void initDomainRedisTemplate(RedisTemplate<String, Object> redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
       // redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
        //redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // redis 开启事务 简单事物 不能开启
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);
    }

    /**
     * 注入封装RedisTemplate
     *
     * @return RedisUtil
     * @throws
     * @Title: redisUtil
     * @autor lpl
     * @date 2017年12月21日
     */
    @Bean(name = "redisUtil")
    public RedisUtil redisUtil(RedisTemplate<String, Object> redisTemplate) {
        RedisUtil redisUtil = new RedisUtil();
        redisUtil.setRedisTemplate(redisTemplate);
        return redisUtil;
    }
}