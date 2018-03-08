package com.acxiom.backend.web.scrape.framework.exception;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 10:28 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class GsfScrapingException extends GsfException {

    public GsfScrapingException(String message) {
        super("", message);
    }

    public GsfScrapingException(String code, String message) {
        super(code, message);
    }

}
