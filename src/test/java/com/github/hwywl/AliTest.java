package com.github.hwywl;

import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.log.response.PutLogsResponse;
import com.github.hwywl.config.Loglevel;
import com.github.hwywl.service.LogHubService;
import com.github.hwywl.utils.LogHubUtil;
import org.junit.Test;

import java.util.HashMap;
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
        map.put("send_role_level", "1");
        map.put("receive_role_level", "1");
        map.put("send_vip_level", "1");
        map.put("receive_vip_level", "1");
        map.put("send_role_id", "role666");
        map.put("send_role_name", "测试");
        map.put("receive_role_id", "role777");
        map.put("receive_role_name", "被测试");
        map.put("event_time", (System.currentTimeMillis() / 1000) + "");
        map.put("event_date", "2020-04-03 14:52:52");
        map.put("chat_content", "测试数据");
        map.put("app_id", "11");
        map.put("platform_id", "11");

        PutLogsResponse putLogsResponse = LogHubService.create().pushLogHub(map);
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
        String text = "{\"account_type\":5,\"device_app_register_time\":1585822911,\"open_id\":\"4zbBUpcZGf0SRa011wJr10030JjVIjU\",\"role_level\":300,\"currency_balance_info\":\"[\\n  {\\n    \\\"id\\\" : 1,\\n    \\\"num\\\" : 7854862771\\n  },\\n  {\\n    \\\"id\\\" : 6,\\n    \\\"num\\\" : 41840\\n  }\\n]\",\"language\":\"zh-Hans-CN\",\"ua\":\"\",\"screen_height\":\"736\",\"role_id\":\"1000040000000039\",\"project_id\":\"26\",\"model\":\"iPhone10,2\",\"app_id\":113,\"brand\":\"Apple\",\"user_ip\":\"171.82.174.73\",\"screen_width\":\"414\",\"ios_app_version\":\"1.0\",\"server_id\":4,\"role_name\":\"萌萌\",\"distributor_id\":1,\"ios_app_build\":\"1.0\",\"role_create_time\":1585822989,\"vip_level\":11,\"channel_id\":1,\"register_device_app_register_time\":1585822911,\"server_name\":\"\",\"cp_open_id\":\"000O4zbBUpcZGf0SRa011wJr10030JjVIjU\",\"ios_idfv\":\"73AC265C-3248-4907-8986-6AA687C97651\",\"bssid\":\"\",\"event_time_server\":1585900065,\"register_channel_id\":1,\"ssid\":\"\",\"mac\":\"02:00:00:00:00:00\",\"network_info\":\"4G\",\"register_time\":1585822937,\"ios_idfa\":\"7EC652C3-95DC-4DC1-A5EF-5CE069E0720F\",\"register_device_id\":\"7EC652C3-95DC-4DC1-A5EF-5CE069E0720F\",\"device_name\":\"iPhone\",\"vip_type\":-1813697253438076400,\"account_name\":\"\",\"sdk_version\":\"1.0.5\",\"history_recharge\":128800,\"orientation\":0,\"vip_remain\":0,\"os\":2,\"account_register_app_id\":113,\"os_version\":\"12.1\",\"svip_remain\":0,\"app_register_time\":1585822937,\"app_name\":\"狂斩之刃\",\"pkg_name\":\"com.jinke.kzzr.aiqu\",\"boot_time\":\"1585310080\",\"app_key\":\"03c5517fcfc26872ac0fd22f038e371b\",\"event_id\":113,\"party_id\":0,\"platform_id\":213,\"event_time_client\":1585900065}";


        Map<String, String> map = new HashMap<>();
        map.put("open_id", "rfikaseoioire98weio3966666");
        map.put("app_id", "11");
        map.put("platform_id", "11");

        JSONObject jsonObject = JSONObject.parseObject(text);
        try {
            Integer openId = (Integer) jsonObject.get("open_id");
        } catch (Exception e) {
            LogHubService.create().pushLogHub(map, Loglevel.ERROR, text, e);
            e.printStackTrace();
        }
    }
}