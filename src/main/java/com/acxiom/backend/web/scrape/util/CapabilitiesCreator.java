package com.acxiom.backend.web.scrape.util;

import com.acxiom.backend.web.scrape.entity.Job;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 10:56 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class CapabilitiesCreator {

    public static DesiredCapabilities createFirefoxCapabilities(Job job){
        // set proxy
        String proxyString = job.getProxy();
        Proxy firefoxProxy = new Proxy();
        firefoxProxy.setHttpProxy(proxyString)
                .setFtpProxy(proxyString)
                .setSslProxy(proxyString);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(CapabilityType.PROXY, firefoxProxy);

        //set default download output path
        FirefoxProfile profile = new FirefoxProfile();
        String path = job.getOutputPath();
        profile.setPreference("browser.download.folderList", 2);
        profile.setPreference("browser.download.dir", path);
        profile.setPreference("browser.helperApps.neverAsk.saveToDisk", "application/msword, application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet, application/csv, application/ris, text/csv, image/png, application/pdf, text/html, text/plain, application/zip, application/x-zip, application/x-zip-compressed, application/download, application/octet-stream");
        profile.setPreference("pdfjs.disabled", true);
        profile.setPreference("permissions.default.image", 2);
        capabilities.setCapability(FirefoxDriver.PROFILE, profile);

        return capabilities;
    }
}
