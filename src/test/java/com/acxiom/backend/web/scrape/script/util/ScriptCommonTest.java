package com.acxiom.backend.web.scrape.script.util;

import com.acxiom.backend.web.scrape.entity.WebScrapeState;
import com.acxiom.backend.web.scrape.framework.CommonWebScrape;
import org.junit.After;
import org.junit.Before;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 12:21 PM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class ScriptCommonTest {

    private ExecutorService executorService;

    @Before
    public void before() {
        executorService = Executors.newFixedThreadPool(1);
    }

    @After
    public void after() {
        executorService.shutdown();
    }

    public void startScript(CommonWebScrape scrapeScript) throws InterruptedException {
        List<Callable<WebScrapeState>> tasks = new ArrayList<Callable<WebScrapeState>>();
        tasks.add(scrapeScript);
        executorService.invokeAll(tasks);
    }
}
