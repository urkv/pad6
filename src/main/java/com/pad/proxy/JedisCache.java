package com.pad.proxy;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Created by LegalSoul on 14.12.2016.
 */
public class JedisCache {

    private static JedisCache myJed;

    private static final String REDIS_HOST = "localhost";
    private static final Integer REDIS_PORT = 6379;
    private static final Integer EXPIRE_TIME = 10;//seconds
    public Jedis jedis;
    public JedisPool theCache;

    private JedisCache() {

        theCache = new JedisPool(REDIS_HOST, REDIS_PORT);
        jedis = theCache.getResource();
    }

    public void jedisAdd(String key, String value){
            jedis.sadd(key, value);
            jedis.expire(key, EXPIRE_TIME);

    }


    public static  JedisCache getInstance(){
        if (myJed==null)
            myJed = new JedisCache();
        return myJed;
    }

}
