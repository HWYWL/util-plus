package com.github.hwywl.download;

import com.github.hwywl.exception.WriterException;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Collection;

/**
 * 将文件写出到浏览器
 *
 * @author YI
 * @date 2019-11-13
 */
public class WriterWithFile {

    private OutputStream   outputStream;

    public WriterWithFile(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void writeSheet(Writer writer) throws WriterException {
        Collection<?> rows = writer.rows();

        try (OutputStreamWriter osWriter = new OutputStreamWriter(outputStream)) {
            Object[] array = rows.toArray();
            osWriter.write('\ufeff');

            for (Object line : array) {
                osWriter.write((String)line);
                osWriter.write("\n");
            }
        } catch (Exception e) {
            throw new WriterException(e);
        }
    }
}
