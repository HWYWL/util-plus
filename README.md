# util-plus
一个使用很方便的工具类

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
只要浏览器请求 /downFile这个接口就能下载一个名称为**悯农.txt**的文件，让其他繁琐的操作统统交给**util-plus**吧

