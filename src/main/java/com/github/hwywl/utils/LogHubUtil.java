package com.github.hwywl.utils;

import com.aliyun.openservices.log.Client;
import com.github.hwywl.config.Config;

/**
 * 用于初始化阿里云loghub
 *
 * @author YI
 * @date 2020/4/2 17:19
 */
public class LogHubUtil {

    /**
     * @param endpoint  project 所属区域匹配的Endpoint，一般为：cn-shanghai.log.aliyuncs.com（必填）
     * @param accessId  阿里云访问密钥 AccessKeyId（必填）
     * @param accessKey 阿里云访问密钥AccessKeySecret（必填）
     * @param project   Project列表中的名称（必填）
     * @param logStore  日志库的名称（必填）
     * @param topic     日志主题（非必填）
     * @param source    日志来源，一般填项目的名称+环境，例如：consumer-online-prod（非必填）
     * @return Client
     */
    public static Client init(String endpoint, String accessId, String accessKey,
                              String project, String logStore, String topic, String source) {

        ConfLogHubUtil.put(Config.PROJECT, project);
        ConfLogHubUtil.put(Config.LOG_STORE, logStore);
        ConfLogHubUtil.put(Config.TOPIC, topic);
        ConfLogHubUtil.put(Config.SOURCE, source);

        Client client = new Client(endpoint, accessId, accessKey);
        ConfLogHubUtil.putClient(client);
        return client;
    }
}