package com.github.hwywl.config;

import com.aliyun.openservices.log.Client;
import com.github.hwywl.utils.ConfLogHubUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author YI
 * @date 2020/4/2 17:54
 */
@Configuration
public class Config {
    @Autowired
    LogHubProperties logHubProperties;

    /**
     * Project列表中的名称
     */
    public static String PROJECT = "project";

    /**
     * 日志库的名称
     */
    public static String LOG_STORE = "logStore";

    /**
     * 日志主题
     */
    public static String TOPIC = "topic";

    /**
     * 日志来源，一般填项目的名称+环境，例如：consumer-online-prod
     */
    public static String SOURCE = "source";

    /**
     * loghub客户端
     */
    public static String CLIENT = "client";

    /**
     * 用spring boot初始化阿里云LogHub配置信息
     * @return
     */
    @Bean
    public Client init() {

        ConfLogHubUtil.put(Config.PROJECT, logHubProperties.project);
        ConfLogHubUtil.put(Config.LOG_STORE, logHubProperties.logStore);
        ConfLogHubUtil.put(Config.TOPIC, logHubProperties.logTopic);
        ConfLogHubUtil.put(Config.SOURCE, logHubProperties.logSource);
        ConfLogHubUtil.isLogEnabled = logHubProperties.isEnabled();

        Client client = new Client(logHubProperties.endpoint, logHubProperties.accessId, logHubProperties.accessKey);
        ConfLogHubUtil.putClient(client);

        return client;
    }
}