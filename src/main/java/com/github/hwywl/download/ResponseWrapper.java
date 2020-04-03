package com.github.hwywl.download;

import com.github.hwywl.exception.WriterException;
import com.github.hwywl.utils.StringUtil;
import lombok.experimental.UtilityClass;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/**
 * 包装HttpServletResponse下载文件名
 *
 * @author YI
 * @date 2019-11-13
 */
@UtilityClass
public class ResponseWrapper {

    public static OutputStream createFile(HttpServletResponse servletResponse, String fileName) throws WriterException {
        return create(servletResponse, fileName);
    }

    private OutputStream create(HttpServletResponse servletResponse, String fileName) throws WriterException {
        try {
            if (null == servletResponse) {
                throw new WriterException("response instance not null");
            }
            if (StringUtil.isEmpty(fileName)) {
                throw new WriterException("response file name not empty");
            }
            fileName = new String(fileName.getBytes(StandardCharsets.UTF_8), "ISO8859-1");
            servletResponse.setHeader("Content-Disposition", "attachment; filename=" + fileName);
            return servletResponse.getOutputStream();
        } catch (Exception e) {
            throw new WriterException(e);
        }
    }

}