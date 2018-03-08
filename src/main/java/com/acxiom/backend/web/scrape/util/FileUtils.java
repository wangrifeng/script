package com.acxiom.backend.web.scrape.util;

import com.acxiom.backend.web.scrape.framework.exception.GsfScrapingException;
import org.apache.log4j.Logger;

import java.io.File;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 11:20 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class FileUtils {

    private static final Logger LOG = Logger.getLogger(FileUtils.class);

    public static void checkFile(String filePath) throws GsfScrapingException{
        File file = new File(filePath);
        if(!file.isFile()){
            String errorMessage = "The file "+ filePath + "is not exists.";
            LOG.error(errorMessage);
            throw new GsfScrapingException(errorMessage);
        }
        if(file.length()<=0){
            String errorMessage = "The file "+ filePath + "is invalid.";
            LOG.error(errorMessage);
            throw new GsfScrapingException(errorMessage);
        }
    }
}
