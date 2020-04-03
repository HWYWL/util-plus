package com.github.hwywl.service;

import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.request.PutLogsRequest;
import com.aliyun.openservices.log.response.PutLogsResponse;
import com.github.hwywl.utils.ConfLogHubUtil;
import com.github.hwywl.utils.StringUtil;
import lombok.extern.slf4j.Slf4j;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 阿里云日志操作
 *
 * @author YI
 * @date 2020/4/3 10:20
 */
@Slf4j
public class LogHubService {

    public static LogHubService create() {
        return new LogHubService();
    }

    /**
     * 日志主题
     */
    private String topic = "";

    /**
     * 日志来源，一般填项目的名称+环境，例如：consumer-online-prod
     */
    private String source = "";

    /**
     * 接收数据
     *
     * @param topic 来源
     * @return Writer
     */
    public LogHubService topic(String topic) {
        this.topic = topic;
        return this;
    }

    /**
     * 接收数据
     *
     * @param source 主题
     * @return Writer
     */
    public LogHubService source(String source) {
        this.source = source;
        return this;
    }

    /**
     * 单条日志推送
     *
     * @param data 数据
     * @return
     * @throws LogException
     */
    public PutLogsResponse pushLogHub(Map<String, String> data){
        List<LogItem> logGroup = new ArrayList<>();
        LogItem logItem = new LogItem();

        // 构建日志结构
        for (String key : data.keySet()) {
            String value = data.get(key);
            logItem.PushBack(key, value);
        }
        logGroup.add(logItem);

        return putLogsRequest(logGroup);
    }

    /**
     * 单条异常日志推送
     *
     * @param data 数据
     * @return
     * @throws LogException
     */
    public PutLogsResponse pushLogHub(Map<String, String> data, String level, String logData, Exception throwable) {
        List<LogItem> logGroup = new ArrayList<>();
        LogItem logItem = new LogItem();

        // 构建日志结构
        for (String key : data.keySet()) {
            String value = data.get(key);
            logItem.PushBack(key, value);
        }

        logItem.PushBack("level", level);
        logItem.PushBack("log", logData);

        // 错误日志
        if (null != throwable) {
            StringWriter stringWriter = new StringWriter();
            throwable.printStackTrace(new PrintWriter(stringWriter));

            logItem.PushBack("message", throwable.getMessage());
            logItem.PushBack("throwable", stringWriter.toString());
        }

        logGroup.add(logItem);

        return putLogsRequest(logGroup);
    }

    /**
     * 批量日志推送
     *
     * @param data 数据
     * @return
     * @throws LogException
     */
    public PutLogsResponse pushLogHubBatch(List<Map<String, String>> data) {

        List<LogItem> logGroup = new ArrayList<>();

        // 构建批量日志结构
        for (Map<String, String> datum : data) {
            LogItem logItem = new LogItem();
            for (String key : datum.keySet()) {
                String value = datum.get(key);
                logItem.PushBack(key, value);
            }
            logGroup.add(logItem);
        }

        return putLogsRequest(logGroup);
    }

    /**
     * 推送数据到阿里云日志库
     *
     * @param logGroup
     * @return
     * @throws LogException
     */
    private PutLogsResponse putLogsRequest(List<LogItem> logGroup) {
        Client client = ConfLogHubUtil.getClient();

        // 构建推送请求结构
        PutLogsRequest req = new PutLogsRequest(
                ConfLogHubUtil.getProject(),
                ConfLogHubUtil.getLogStore(),
                StringUtil.isEmpty(topic) ? ConfLogHubUtil.getTopic() : topic,
                StringUtil.isEmpty(source) ? ConfLogHubUtil.getSource() : source,
                logGroup
        );

        // 推送到阿里云日志仓库
        PutLogsResponse putLogsResponse = null;
        try {
            putLogsResponse = client.PutLogs(req);
        } catch (LogException e) {
            e.printStackTrace();
        }
        return putLogsResponse;
    }
}