package com.github.hwywl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.response.PutLogsResponse;
import com.github.hwywl.config.Loglevel;
import com.github.hwywl.service.LogHubService;
import com.github.hwywl.utils.LogHubUtil;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

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

        CompletableFuture<PutLogsResponse> future = LogHubService.create().pushLogHub(map);

        // 正式服可以不需要这一段，这里只是为了让主线程不要立刻停止
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        // 执行成功，如果不需要返回值这一段可以不需要
        future.thenAccept((result) -> System.out.println("thenAccept: " + result));
        // 执行异常，如果不需要返回值这一段可以不需要
        future.exceptionally((exec) -> {
            exec.printStackTrace();
            return null;
        });
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

        CompletableFuture<PutLogsResponse> future = LogHubService.create().pushLogHubBatch(batch);

        // 正式服可以不需要这一段，这里只是为了让主线程不要立刻停止
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ie) {
            ie.printStackTrace();
        }

        // 执行成功，如果不需要返回值这一段可以不需要
        future.thenAccept((result) -> System.out.println("thenAccept: " + result));
        // 执行异常，如果不需要返回值这一段可以不需要
        future.exceptionally((exec) -> {
            exec.printStackTrace();
            return null;
        });
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
            CompletableFuture<PutLogsResponse> future = LogHubService.create().source("app").topic("android").pushLogHub(map, Loglevel.ERROR, text, e);

            // 正式服可以不需要这一段，这里只是为了让主线程不要立刻停止
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            // 执行成功，如果不需要返回值这一段可以不需要
            future.thenAccept((result) -> System.out.println("thenAccept: " + result));
            // 执行异常，如果不需要返回值这一段可以不需要
            future.exceptionally((exec) -> {
                exec.printStackTrace();
                return null;
            });

            e.printStackTrace();
        }
    }

    /**
     * Rigorous Test :-)
     */
    @Test
    public void push03() {
        LogHubUtil.init(
                "cn-shanghai.log.aliyuncs.com",
                "****************",
                "****************",
                "ikylin-errorlog",
                "consumer-online",
                "test",
                "test",
                false
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
            CompletableFuture<PutLogsResponse> future = LogHubService.create().source("app").topic("android").pushLogHub(map, Loglevel.ERROR, text, e);

            // 正式服可以不需要这一段，这里只是为了让主线程不要立刻停止
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }

            // 执行成功，如果不需要返回值这一段可以不需要
            future.thenAccept((result) -> System.out.println("thenAccept: " + result));
            // 执行异常，如果不需要返回值这一段可以不需要
            future.exceptionally((exec) -> {
                exec.printStackTrace();
                return null;
            });

            e.printStackTrace();
        }
    }

    @Test
    public void push04() {
        LogHubUtil.init(
                "cn-hongkong.log.aliyuncs.com",
                "****************",
                "****************",
                "ikylin-syslogs",
                "ikylin-consumer",
                "test",
                "test"
        );

        List<String> vals = new ArrayList<>();
        vals.add("锄禾日当午，汗滴禾下土。");
        vals.add("谁知盘中餐，粒粒皆辛苦。");

        CompletableFuture<PutLogsResponse> future = LogHubService.create().source("app").topic("android").pushLogHubListBatch("log", vals);

        // 正式服可以不需要这一段，这里只是为了让主线程不要立刻停止
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 执行成功，如果不需要返回值这一段可以不需要
        future.thenAccept((result) -> System.out.println("thenAccept: " + result));
        // 执行异常，如果不需要返回值这一段可以不需要
        future.exceptionally((e) -> {
            e.printStackTrace();
            return null;
        });
    }
}