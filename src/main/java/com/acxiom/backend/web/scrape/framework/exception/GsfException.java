package com.acxiom.backend.web.scrape.framework.exception;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 10:22 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class GsfException extends Exception {

    private String code;
    private String message;

    public GsfException(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

}
