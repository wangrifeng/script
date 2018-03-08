package com.acxiom.backend.web.scrape.framework;

import com.acxiom.backend.web.scrape.entity.Job;
import com.acxiom.backend.web.scrape.framework.exception.GsfAuditException;
import com.acxiom.backend.web.scrape.framework.exception.GsfScrapingException;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 10:20 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public interface WebScrape {

    void scrape() throws GsfScrapingException;
    void audit() throws GsfAuditException;
    void setJob(Job job);
}
