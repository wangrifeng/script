package com.acxiom.backend.web.scrape.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 10:59 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class GsfFileWriter {

    private BufferedWriter bufferedWriter = null;

    public GsfFileWriter(String filePath) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(filePath));
    }

    public GsfFileWriter(String filePath,boolean flag) throws IOException {
        bufferedWriter = new BufferedWriter(new FileWriter(filePath,flag));
    }

    public void write(String str) throws IOException {
        bufferedWriter.write(str);
        bufferedWriter.flush();
    }

    public void close() throws IOException {
        bufferedWriter.close();
    }
}
