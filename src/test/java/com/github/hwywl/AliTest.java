package com.github.hwywl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.response.PutLogsResponse;
import com.github.hwywl.config.Loglevel;
import com.github.hwywl.service.LogHubService;
import com.github.hwywl.utils.LogHubUtil;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author YI
 * @date 2020/4/3 14:43
 */
public class AliTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void push() {
        LogHubUtil.init(
                "cn-shanghai.log.aliyuncs.com",
                "****************",
                "******************",
                "jk-chat",
                "game-chat-log",
                "test",
                "test"
        );
        Map<String, String> map = new HashMap<>();
        map.put("send_server_id", "11");
        map.put("receive_server_id", "1");
        map.put("chat_type", "私聊");
        map.put("open_id", "rfikaseoioire98weio3966666");

        PutLogsResponse putLogsResponse = LogHubService.create().pushLogHub(map);
    }

    @Test
    public void pushBatch() {
        LogHubUtil.init(
                "cn-shanghai.log.aliyuncs.com",
                "****************",
                "******************",
                "jk-chat",
                "game-chat-log",
                "test",
                "test"
        );
        List<Map<String, String>> batch = new ArrayList<>();
        Map<String, String> map = new HashMap<>();
        map.put("send_server_id", "11");
        map.put("receive_server_id", "1");
        map.put("chat_type", "私聊");
        map.put("open_id", "rfikaseoioire98weio3966666");

        batch.add(map);

        PutLogsResponse putLogsResponse = LogHubService.create().pushLogHubBatch(batch);
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void push02() {
        LogHubUtil.init(
                "cn-shanghai.log.aliyuncs.com",
                "****************",
                "****************",
                "ikylin-errorlog",
                "consumer-online",
                "test",
                "test"
        );
        String text = "{\"open_id\":\"rfikaseoioire98weio3966666\",\"platform_id\":11,\"app_id\":11}";


        Map<String, String> map = new HashMap<>();
        map.put("open_id", "rfikaseoioire98weio3966666");
        map.put("app_id", "11");
        map.put("platform_id", "11");

        JSONObject jsonObject = JSONObject.parseObject(text);
        try {
            Integer openId = (Integer) jsonObject.get("open_id");
        } catch (Exception e) {
            LogHubService.create().source("app").topic("android").pushLogHub(map, Loglevel.ERROR, text, e);
            e.printStackTrace();
        }
    }
}