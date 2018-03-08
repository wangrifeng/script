package com.acxiom.backend.web.scrape.framework;

import com.acxiom.backend.web.scrape.entity.Job;
import com.acxiom.backend.web.scrape.entity.WebScrapeState;
import com.acxiom.backend.web.scrape.framework.exception.GsfAuditException;
import com.acxiom.backend.web.scrape.framework.exception.GsfScrapingException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.util.concurrent.Callable;

/**
 * @Author dazhan
 * @CreateDate 03/08/2018 10:19 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public abstract class CommonWebScrape implements WebScrape, Callable<WebScrapeState> {

    private static final Logger LOGGER = LogManager.getLogger(CommonWebScrape.class);

    protected Job job;

    public WebScrapeState call() throws Exception {
        try {
            scrape();
            audit();
        } catch (Exception e) {
            LOGGER.error("Failed to execute web scrape script, ", e);
        }
        return WebScrapeState.AUDIT_SUCCESS;
    }

    public abstract void scrape() throws GsfScrapingException;

    public abstract void audit() throws GsfAuditException;

    public void setJob(Job job) {
        this.job = job;
    }
}
