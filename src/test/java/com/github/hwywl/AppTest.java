package com.github.hwywl;

import com.github.hwywl.download.ResponseWrapper;
import com.github.hwywl.download.Writer;
import com.github.hwywl.exception.WriterException;
import org.junit.Test;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue(HttpServletResponse servletResponse) throws WriterException {
        List<String> list = new ArrayList<>();
        list.add("春种一粒粟，秋收万颗子。");
        list.add("四海无闲田，农夫犹饿死。");
        list.add("锄禾日当午，汗滴禾下土。");
        list.add("谁知盘中餐，粒粒皆辛苦。");

        // 将文件写出到浏览器
        Writer.create().withRows(list).to(ResponseWrapper.createFile(servletResponse,"aaa.txt"));
    }
}
