package com.pad.proxy;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

import java.util.Set;

/**
 * Created by LegalSoul on 14.12.2016.
 */
public class JedisCache {

    private static JedisCache myJed;

    private static final String redisHost = "localhost";
    private static final Integer redisPort = 6379;
    public Jedis jedis;
    public JedisPool theCache;

    private JedisCache() {

        theCache = new JedisPool(redisHost, redisPort);
        jedis = theCache.getResource();
    }

    public void jedisAdd(String key, String value){
            jedis.sadd(key, value);
            jedis.expire(key, 20);

    }


    public static  JedisCache getInstance(){
        if (myJed==null)
            myJed = new JedisCache();
        return myJed;
    }

}
