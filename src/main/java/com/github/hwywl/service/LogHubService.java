package com.github.hwywl.service;

import com.alibaba.fastjson.JSON;
import com.aliyun.openservices.log.Client;
import com.aliyun.openservices.log.common.LogItem;
import com.aliyun.openservices.log.exception.LogException;
import com.aliyun.openservices.log.request.PutLogsRequest;
import com.aliyun.openservices.log.response.PutLogsResponse;
import com.github.hwywl.utils.ConfLogHubUtil;
import com.github.hwywl.utils.StringUtil;

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
     */
    public PutLogsResponse pushLogHub(Object data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || null == data) {
            return null;
        }

        String jsonStr = JSON.toJSONString(data);

        return pushLogHub(jsonStr);
    }

    /**
     * 单条日志推送
     *
     * @param data 数据
     * @return
     */
    public PutLogsResponse pushLogHub(String data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || StringUtil.isEmpty(data)) {
            return null;
        }

        List<LogItem> logGroup = new ArrayList<>();
        LogItem logItem = new LogItem();

        logItem.PushBack("log", data);
        logGroup.add(logItem);

        return putLogsRequest(logGroup);
    }

    /**
     * 批量日志推送
     *
     * @param data 数据
     * @return
     */
    public PutLogsResponse pushLogHubListObjectBatch(List<Object> data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || null == data) {
            return null;
        }

        String jsonStr = JSON.toJSONString(data);

        return pushLogHub(jsonStr);
    }

    /**
     * 批量日志推送
     *
     * @param data 数据
     * @return
     */
    public PutLogsResponse pushLogHubListBatch(List<String> data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || null == data || data.size() == 0) {
            return null;
        }

        String jsonStr = JSON.toJSONString(data);

        return pushLogHub(jsonStr);
    }

    /**
     * 批量日志推送
     *
     * @param key  loghub中数据键，类似于map中的key
     * @param data 数据
     * @return
     */
    public PutLogsResponse pushLogHubListObjectBatch(String key, List<Object> data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || StringUtil.isEmpty(key) || null == data) {
            return null;
        }

        String jsonStr = JSON.toJSONString(data);

        return pushLogHub(key, jsonStr);
    }

    /**
     * 批量日志推送
     *
     * @param key  loghub中数据键，类似于map中的key
     * @param data 数据
     * @return
     */
    public PutLogsResponse pushLogHubListBatch(String key, List<String> data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || StringUtil.isEmpty(key) || null == data || data.size() == 0) {
            return null;
        }

        String jsonStr = JSON.toJSONString(data);

        return pushLogHub(key, jsonStr);
    }

    /**
     * 单条日志推送
     *
     * @param key  loghub中数据键，类似于map中的key
     * @param data 数据
     * @return
     */
    public PutLogsResponse pushLogHub(String key, Object data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || null == data || StringUtil.isEmpty(key)) {
            return null;
        }

        String jsonStr = JSON.toJSONString(data);

        return pushLogHub(key, jsonStr);
    }

    /**
     * 单条日志推送
     *
     * @param data 数据
     * @return
     */
    public PutLogsResponse pushLogHub(String key, String data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || StringUtil.isEmpty(data) || StringUtil.isEmpty(key)) {
            return null;
        }

        List<LogItem> logGroup = new ArrayList<>();
        LogItem logItem = new LogItem();

        logItem.PushBack(key, data);
        logGroup.add(logItem);

        return putLogsRequest(logGroup);
    }

    /**
     * 单条日志推送
     *
     * @param data 数据
     * @return
     * @throws LogException
     */
    public PutLogsResponse pushLogHub(Map<String, String> data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || null == data || data.size() == 0) {
            return null;
        }

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
     * 批量日志推送
     *
     * @param data 数据
     * @return
     * @throws LogException
     */
    public PutLogsResponse pushLogHubBatch(List<Map<String, String>> data) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled || null == data || data.size() == 0) {
            return null;
        }

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
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled) {
            return null;
        }

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

    /**
     * 单条异常日志推送
     *
     * @param data      自定义参数
     * @param level     错误类型
     * @param logData   完整数据
     * @param throwable 异常信息
     * @return
     */
    public PutLogsResponse pushLogHub(Map<String, String> data, String level, String logData, Exception throwable) {
        // 判断是否推送日志到LogHub
        if (!ConfLogHubUtil.isLogEnabled) {
            return null;
        }

        List<LogItem> logGroup = new ArrayList<>();
        LogItem logItem = new LogItem();

        // 可以不传
        if (null != data && data.size() > 0) {
            // 构建日志结构
            for (String key : data.keySet()) {
                String value = data.get(key);
                logItem.PushBack(key, value);
            }
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
}