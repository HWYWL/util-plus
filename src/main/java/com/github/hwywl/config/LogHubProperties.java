package com.github.hwywl.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * 阿里云LogHub配置信息
 *
 * @author YI
 * @date 2020/6/2 11:08
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "ali.log.hub.conf")
public class LogHubProperties {
    /**
     * 阿里云访问密钥 AccessKeyId（必填）
     */
    String accessId;
    /**
     * 阿里云访问密钥AccessKeySecret（必填）
     */
    String accessKey;
    /**
     * 日志库的名称（必填）
     */
    String logStore;
    /**
     * 日志主题（非必填）
     */
    String logTopic;
    /**
     * 日志来源，一般填项目的名称+环境（非必填）
     */
    String logSource;
    /**
     * project 所属区域匹配的Endpoint，例如：cn-shanghai.log.aliyuncs.com（必填）
     */
    String endpoint;
    /**
     * Project列表中的名称（必填）
     */
    String project;

    /**
     * 是否启用日志上报功能，默认为true（非必填）
     */
    private boolean enabled = true;
}