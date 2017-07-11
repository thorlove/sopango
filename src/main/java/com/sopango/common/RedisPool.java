package com.sopango.common;

import java.util.ResourceBundle;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
    private static JedisPool jedisPool = null;
    static{
        try {
            ResourceBundle bundle = ResourceBundle.getBundle("redis");
            String addr = bundle.getString("host"); 
            int port = Integer.valueOf(bundle.getString("port")).intValue();                  
            String auth = bundle.getString("pass"); 
            JedisPoolConfig config = new JedisPoolConfig();
            config.setBlockWhenExhausted(true);
            config.setEvictionPolicyClassName("org.apache.commons.pool2.impl.DefaultEvictionPolicy"); 
            //是否启用pool的jmx管理功能, 默认true
            config.setJmxEnabled(true);
            //最大空闲连接数, 默认8个
            config.setMaxIdle(8);
            //最大连接数, 默认8个
            config.setMaxTotal(8);
            //获取连接时的最大等待毫秒数(如果设置为阻塞时BlockWhenExhausted),如果超时就抛异常, 小于零:阻塞不确定的时间,  默认-1
            config.setMaxWaitMillis(-1);
            //逐出连接的最小空闲时间 默认1800000毫秒(30分钟)
            config.setMinEvictableIdleTimeMillis(1800000);
            //最小空闲连接数, 默认0
            config.setMinIdle(0);
            //每次逐出检查时 逐出的最大数目 如果为负数就是 : 1/abs(n), 默认3
            config.setNumTestsPerEvictionRun(3);
            //对象空闲多久后逐出, 当空闲时间>该值 且 空闲连接>最大空闲数 时直接逐出,不再根据MinEvictableIdleTimeMillis判断  (默认逐出策略)   
            config.setSoftMinEvictableIdleTimeMillis(1800000);
            //在获取连接的时候检查有效性, 默认false
            config.setTestOnBorrow(false);
            //在空闲时检查有效性, 默认false
            config.setTestWhileIdle(false);
            //逐出扫描的时间间隔(毫秒) 如果为负数,则不运行逐出线程, 默认-1
            config.setTimeBetweenEvictionRunsMillis(-1);
            jedisPool = new JedisPool(config, addr, port, 3000, auth);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized static Jedis getJedis() {
        try {
            if (jedisPool != null) {
                Jedis resource = jedisPool.getResource();
                return resource;
            } else {
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(final Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }

    public static void main(String[] args) {
        Jedis jedis = RedisPool.getJedis();
        System.out.println(jedis.getDB());
        RedisPool.close(jedis);
    }
}