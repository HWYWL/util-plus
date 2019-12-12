# util-plus
一个使用很方便的工具类

[![author](https://img.shields.io/badge/author-HWY-red.svg)](https://github.com/HWYWL)  [![Maven Central](https://img.shields.io/badge/util--plus-1.0.1--RELEASE-ff69b4.svg)](https://search.maven.org/artifact/com.github.hwywl/util-plus/1.0.1-RELEASE/jar)

### 安装
**maven**
```
<dependency>
  <groupId>com.github.hwywl</groupId>
  <artifactId>util-plus</artifactId>
  <version>1.0.1-RELEASE</version>
</dependency>
```

**Gradle**
```
implementation 'com.github.hwywl:util-plus:1.0.1-RELEASE'
```



### 下载
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

