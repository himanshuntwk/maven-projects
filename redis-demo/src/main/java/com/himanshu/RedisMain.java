package com.himanshu;

import redis.clients.jedis.JedisPooled;

import java.util.HashMap;
import java.util.Map;

public class RedisMain {

    private String host;
    private int port;

    public RedisMain(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String redisDemo() {
        JedisPooled jedisPooled = new JedisPooled(host, port);

        Map<String, String> dataHash = new HashMap<>();
        dataHash.put("firstname", "Himanshu");
        dataHash.put("lastname", "Sharma");
        dataHash.put("age", "very old");
        jedisPooled.hset("user_123", dataHash);
        Map<String, String> fromRedisMap = jedisPooled.hgetAll("user_123");
        System.out.println(fromRedisMap);
        return fromRedisMap.get("firstname");
    }

    public static void main(String[] args) {
        RedisMain redisMain = new RedisMain("localhost", 6379);
        System.out.println(redisMain.redisDemo());
    }
}