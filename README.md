# util-plus
一个使用很方便的工具类

[![author](https://img.shields.io/badge/author-HWY-red.svg)](https://github.com/HWYWL)  [![Maven Central](https://img.shields.io/badge/util--plus-1.0.5--RELEASE-ff69b4.svg)](https://search.maven.org/artifact/com.github.hwywl/util-plus/1.0.5-RELEASE/jar)

### 安装
**maven**
```
<dependency>
    <groupId>com.github.hwywl</groupId>
    <artifactId>util-plus-spring-boot-starter</artifactId>
    <version>1.0.5-RELEASE</version>
</dependency>
```

**Gradle**
```
implementation 'com.github.hwywl:util-plus-spring-boot-starter:1.0.5-RELEASE'
```

因为加入了spring框架的支持，所以jar包会比较大一点，如果你的项目中没有使用spring框架，可以使用老版本：
```
<dependency>
    <groupId>com.github.hwywl</groupId>
    <artifactId>util-plus</artifactId>
    <version>1.0.4-RELEASE</version>
</dependency>
```

## 下载
以Spring boot构建的项目为例，我们查看如下代码：
```java
@RestController
public class DownController {

    /**
     * 下载
     *
     * @return
     */
    @GetMapping("/downFile")
    public void downFile(HttpServletResponse servletResponse) throws WriterException {
        List<String> list = new ArrayList<>();
        list.add("春种一粒粟，秋收万颗子。");
        list.add("四海无闲田，农夫犹饿死。");
        list.add("锄禾日当午，汗滴禾下土。");
        list.add("谁知盘中餐，粒粒皆辛苦。");

        Writer.create().withRows(list)
                .to(ResponseWrapper.createFile(servletResponse, "悯农.txt"));
    }
}
```

如果是写入本地提供了**isAppend**函数，可以用于覆盖文件或者追加模式
```
Writer.create().withRows(list).isAppend(true).to("aaa.txt");
```
只要浏览器请求 /downFile这个接口就能下载一个名称为**悯农.txt**的文件，让其他繁琐的操作统统交给**util-plus**吧


## 阿里云LogHub日志
### 初始化
在使用之前需要初始化基本的参数，如果你使用Spring Boot，可以直接在配置文件中配置即可
```
ali.log.hub.conf.access-id=*************
ali.log.hub.conf.access-key=************
ali.log.hub.conf.endpoint=cn-shanghai.log.aliyuncs.com
ali.log.hub.conf.project=jk-chat
ali.log.hub.conf.log-store=game-chat-log
ali.log.hub.conf.log-source=test
ali.log.hub.conf.log-topic=test
ali.log.hub.conf.enabled=true
```

配置的含义可以参看下面的信息：
```
accessId：阿里云访问密钥 AccessKeyId（必填）
accessKey：阿里云访问密钥AccessKeySecret（必填）
logStore：日志库的名称（必填）
endpoint：所属区域匹配的Endpoint，例如：cn-shanghai.log.aliyuncs.com（必填）
project：Project列表中的名称（必填）
logTopic：日志主题（非必填）
logSource：日志来源，一般填项目的名称+环境（非必填）
enabled：是否启用日志上报功能，默认为true（非必填）
```

如果你只是一个普通的Java项目，把初始化数据写在程序执行开始的地方即可，可以参考**Test**中的测试案例。
```java
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
```

这就是上面所需要的参数
![](https://i.imgur.com/B48Cw4m.png)

**注意**：初始化方法提供了一个重载方法，可以用于控制是否发送日志到阿里云，请参考**LogHubUtil**类。

### 使用
分为两个重载方法，一个是推送普通数据的，另一个是推送异常日志的。

这样就把数据推送到日志库了：
```java
Map<String, String> map = new HashMap<>();
map.put("send_server_id", "11");
map.put("receive_server_id", "1");
map.put("chat_type", "私聊");
map.put("open_id", "rfikaseoioire98weio3966666");

PutLogsResponse putLogsResponse = LogHubService.create().pushLogHub(map);
```
还**支持**批量推送：
```java
List<Map<String, String>> batch = new ArrayList<>();
Map<String, String> map = new HashMap<>();
map.put("send_server_id", "11");
map.put("receive_server_id", "1");
map.put("chat_type", "私聊");
map.put("open_id", "rfikaseoioire98weio3966666");

batch.add(map);

PutLogsResponse putLogsResponse = LogHubService.create().pushLogHubBatch(batch);
```

异常推送，因为考虑到一个**try catch**为一个异常，所以**不支持**批量推送：
```java
String text = "{\"open_id\":\"rfikaseoioire98weio3966666\",\"platform_id\":11,\"app_id\":11}";

JSONObject jsonObject = JSONObject.parseObject(text);
try {
    Integer openId = (Integer) jsonObject.get("open_id");
} catch (Exception e) {
    LogHubService.create().pushLogHub(null, Loglevel.ERROR, text, e);
    e.printStackTrace();
}
```
上面代码中**pushLogHub**中有一个null的入参，其实那个是自定义参数，可传可不传，方便扩展，如果传只需要定义一个Map传进去即可。

### 扩展
如果**source**和**topic**需要自定义传，可以使用下面这种链式表达式即可，如果不传使用最开始配置中的参数。
```java
LogHubService.create().source("app").topic("android").pushLogHub(map, Loglevel.ERROR, text, e);
```

