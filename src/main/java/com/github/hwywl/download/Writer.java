package com.github.hwywl.download;

import com.github.hwywl.exception.WriterException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Collection;

/**
 * 文件写出
 *
 * @author YI
 * @date 2019-11-13
 */
public class Writer {

    public static Writer create() {
        return new Writer();
    }

    /**
     * 数据信息
     */
    private Collection<?> rows;

    /**
     * 接收数据
     *
     * @param rows row data
     * @return Writer
     */
    public Writer withRows(Collection<?> rows) {
        this.rows = rows;
        return this;
    }

    /**
     * 将数据写入文件
     *
     * @param fileName 文件名称
     * @throws WriterException
     */
    public void to(String fileName) throws WriterException {
        File file = new File(fileName);
        try {
            this.to(new FileOutputStream(file));
        } catch (FileNotFoundException e) {
            throw new WriterException(e);
        }
    }

    /**
     * 将文件写入输出流
     *
     * @param outputStream outputStream
     * @throws WriterException
     */
    public void to(OutputStream outputStream) throws WriterException {
        if (null == rows || rows.isEmpty()) {
            throw new WriterException("写出数据不能为空!!!");
        }
        new WriterWithFile(outputStream).writeSheet(this);
    }

    Collection<?> rows() {
        return rows;
    }
}
