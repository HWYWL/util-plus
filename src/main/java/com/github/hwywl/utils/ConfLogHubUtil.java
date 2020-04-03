package com.github.hwywl.utils;

import com.aliyun.openservices.log.Client;
import com.github.hwywl.config.Config;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地缓存
 *
 * @author huangwenyi
 * @date 2020-4-2 18:04:43
 */
public class ConfLogHubUtil {
    private static final ConcurrentHashMap<String, String> CACHE = new ConcurrentHashMap<>(4);
    private static final ConcurrentHashMap<String, Client> CACHE_CLIENT = new ConcurrentHashMap<>(4);

    /**
     * 赋值
     * @param key
     * @param value
     * @return
     */
    public static String put(String key, String value) {
        return CACHE.put(key, value);
    }

    /**
     * 赋值
     * @param client
     * @return
     */
    public static Client putClient(Client client) {
        return CACHE_CLIENT.put(Config.CLIENT, client);
    }

    /**
     * 获取 Client
     * @return
     */
    public static Client getClient() {
        return CACHE_CLIENT.get(Config.CLIENT);
    }

    /**
     * 获取project值
     * @return project
     */
    public static String getProject() {
        return CACHE.get(Config.PROJECT);
    }

    /**
     * 获取logStore值
     * @return logStore
     */
    public static String getLogStore() {
        return CACHE.get(Config.LOG_STORE);
    }

    /**
     * 获取topic值
     * @return topic
     */
    public static String getTopic() {
        return CACHE.get(Config.TOPIC);
    }

    /**
     * 获取source值
     * @return source
     */
    public static String getSource() {
        return CACHE.get(Config.SOURCE);
    }
}
