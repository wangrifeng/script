package com.acxiom.backend.web.scrape.script.license;

import com.acxiom.backend.web.scrape.entity.Job;
import com.acxiom.backend.web.scrape.script.util.ScriptCommonTest;
import org.junit.Test;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 12:11 PM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class ScrapeDataFromProgramTest extends ScriptCommonTest {

    @Test
    public void testScript() throws InterruptedException {
        ScrapeDataFromProgram scrapeDataFromProgram = new ScrapeDataFromProgram();
        Job job = new Job();
        job.setOutputPath("d:\\temp\\AL");
        scrapeDataFromProgram.setJob(job);
        startScript(scrapeDataFromProgram);
    }
}
