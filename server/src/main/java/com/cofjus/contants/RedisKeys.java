package com.cofjus.contants;

public class RedisKeys {

    /**
     * frequency control key
     * key: frequency_control:{userId}
     * value: 1
     * expire: 3s
     */
    public final static String FREQUENCY_CONTROL = "frequency_control:%s";
}
