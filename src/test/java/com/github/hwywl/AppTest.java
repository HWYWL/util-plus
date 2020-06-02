package com.github.hwywl;

import com.github.hwywl.download.ResponseWrapper;
import com.github.hwywl.download.Writer;
import com.github.hwywl.exception.WriterException;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() throws WriterException {
        List<String> list = new ArrayList<>();
        list.add("春种一粒粟，秋收万颗子。");
        list.add("四海无闲田，农夫犹饿死。");

        // 将文件写出到浏览器
        Writer.create().withRows(list).to("aaa.txt");
    }

    /**
     * 追加模式
     */
    @Test
    public void fileAppend() throws WriterException {
        List<String> list = new ArrayList<>();
        list.add("锄禾日当午，汗滴禾下土。");
        list.add("谁知盘中餐，粒粒皆辛苦。");

        // 将文件写出到浏览器
        Writer.create().withRows(list).isAppend(true).to("aaa.txt");
    }

    /**
     * 下载追加模式
     */
    @Test
    public void servletAppend(HttpServletResponse servletResponse) throws WriterException {
        List<String> list = new ArrayList<>();
        list.add("锄禾日当午，汗滴禾下土。");
        list.add("谁知盘中餐，粒粒皆辛苦。");

        // 将文件写出到浏览器
        Writer.create().withRows(list).isAppend(true).to(ResponseWrapper.createFile(servletResponse, "aaa.txt"));
    }
}
