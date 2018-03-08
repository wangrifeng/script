package com.acxiom.backend.web.scrape.script.license;

import com.acxiom.backend.web.scrape.framework.CommonWebScrape;
import com.acxiom.backend.web.scrape.framework.exception.GsfAuditException;
import com.acxiom.backend.web.scrape.framework.exception.GsfScrapingException;
import com.acxiom.backend.web.scrape.util.*;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;


/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 10:16 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class ScrapeDataFromProgram extends CommonWebScrape {

    private static final Logger LOGGER = LogManager.getLogger(ScrapeDataFromProgram.class);
    private static final String REQUEST_URL = "https://book.douban.com/tag/%E7%BC%96%E7%A8%8B?type=S";
    private static final String FILE_NAME = "program1.txt";
    private static final String SPLIT_CHAR = "|";
    private static final String SPILT_LINE_CHAR = "\r\n";

    private DesiredCapabilities cap = null;
    private GsfFileWriter fileWriter = null;
    private String filePath = null;

    private static final String HEADER = "书名|作者_出版社_年份_售价|星级|评价人数|描述"+SPILT_LINE_CHAR;

    @Override
    public void scrape() throws GsfScrapingException {
        filePath = this.job.getOutputPath() + File.separator + FILE_NAME;

        try {
            cap = CapabilitiesCreator.createFirefoxCapabilities(this.job);
            fileWriter = new GsfFileWriter(filePath, false);
            fileWriter.write(HEADER);
            readURL();
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            throw new GsfScrapingException(e.getMessage());
        } finally {
            try {
                if (fileWriter != null) {
                    fileWriter.close();
                }
            } catch (IOException e) {
                LOGGER.error("close file writer failed..."+e.getMessage());
            }
        }
    }

    private void readURL() {
        GsfWebDriver webDriver = null;
        int pageNumber = 1;
        try {
            webDriver = new GsfWebDriver(new FirefoxDriver(cap));
            webDriver.get(REQUEST_URL);
            SleepUtil.shortSleep();

            String pageSource = webDriver.getPageSource();
            Document doc = Jsoup.parse(pageSource);
            while (true) {
                LOGGER.info("currentPage number is "+pageNumber);
                Elements elements = doc.select("#subject_list > ul > li");
                handleSearchResult(elements);
                LOGGER.info("Scrape data from "+pageNumber+" page end...");
                boolean nextPage = webDriver.isElementExist(By.linkText("后页>"));
                if (nextPage) {
                    webDriver.findElement(By.linkText("后页>")).click();
                    pageNumber++;
                    SleepUtil.sleep(1);
                } else {
                    LOGGER.info("no next page...");
                    break;
                }
                pageSource = webDriver.getPageSource();
                doc = Jsoup.parse(pageSource);
            }
        } catch (Exception e) {
            LOGGER.error("read url failed..."+e.getMessage());
        } finally {
            if (webDriver != null) {
                webDriver.quit();
            }
        }
    }

    private void handleSearchResult(Elements elements) {
        String name = "", info = "", rating = "", num = "", description = "";
        for (Element element : elements) {
            name = element.select("div.info > h2").first().text();
            info = element.select("div.info > div.pub").first().text();
            rating = element.select("div.info > div.star.clearfix > span.rating_nums").first().text();
            num = element.select("div.info > div.star.clearfix > span.pl").first().text();
            description = element.select("div.info > p").first().text();

            StringBuilder sb = new StringBuilder();
            sb.append(name).append(SPLIT_CHAR)
                    .append(info).append(SPLIT_CHAR)
                    .append(rating).append(SPLIT_CHAR)
                    .append(num).append(SPLIT_CHAR)
                    .append(description).append(SPILT_LINE_CHAR);

            try {
                fileWriter.write(sb.toString());
                LOGGER.info(sb.toString());
            } catch (Exception e) {
                // do nothing
            }
        }
    }

    @Override
    public void audit() throws GsfAuditException {
        try {
            FileUtils.checkFile(filePath);
        } catch (Exception e) {
            LOGGER.error("can't find file err: "+e.getMessage());
            throw new GsfAuditException(e.getMessage());
        }
    }
}
