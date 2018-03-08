package com.acxiom.backend.web.scrape.util;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

/**
 * @Author wangrifeng
 * @CreateDate 03/08/2018 10:59 AM
 * @Description
 * @UpdateAuthor
 * @UpdateDate
 */
public class GsfWebDriver implements WebDriver {

    private final static Logger LOG = Logger.getLogger(GsfWebDriver.class);
    private final static long DEFAULT_TIME_OUT_IN_SECONDS = 15;
    private WebDriver webDriver = null;

    public GsfWebDriver(WebDriver driver) {
        this.webDriver = driver;
    }

    public WebDriver getWebDriver() {
        return this.webDriver;
    }

    public WebElement findElement(By locator, long timeOutInSeconds) {
        WebElement element = (new WebDriverWait(webDriver, timeOutInSeconds))
                .until(ExpectedConditions.presenceOfElementLocated(locator));
        return element;
    }

    public List<WebElement> findElements(By by, long timeOutInSeconds) {
        List<WebElement> elementList = (new WebDriverWait(webDriver, timeOutInSeconds))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return elementList;
    }

    public boolean isElementExist(By by) {
        try {
            WebElement element = findElement(by);
        } catch (TimeoutException e) {
            LOG.debug(e);
            return false;
        } catch (NoSuchElementException e) {
            LOG.debug(e);
            return false;
        }

        return true;
    }

    public void get(String s) {
        webDriver.get(s);
    }

    public String getCurrentUrl() {
        return webDriver.getCurrentUrl();
    }

    public String getTitle() {
        return webDriver.getTitle();
    }

    public List<WebElement> findElements(By by) {
        List<WebElement> elementList = (new WebDriverWait(webDriver, DEFAULT_TIME_OUT_IN_SECONDS))
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
        return elementList;
    }

    public WebElement findElement(By by) {
        WebElement element = (new WebDriverWait(webDriver, DEFAULT_TIME_OUT_IN_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(by));
        return element;
    }

    public String getPageSource() {
        return webDriver.getPageSource();
    }

    public void close() {
        webDriver.close();
    }

    public void quit() {
        webDriver.quit();
    }

    public Set<String> getWindowHandles() {
        return webDriver.getWindowHandles();
    }

    public String getWindowHandle() {
        return webDriver.getWindowHandle();
    }

    public TargetLocator switchTo() {
        return webDriver.switchTo();
    }

    public Navigation navigate() {
        return webDriver.navigate();
    }

    public Options manage() {
        return webDriver.manage();
    }

    public void setScroll(int height) {
        String scrollScript = "document.documentElement.scrollTop=" + height;

        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript(scrollScript);
    }

    public void executeJavaScript(String script) {
        JavascriptExecutor jse = (JavascriptExecutor) webDriver;
        jse.executeScript(script);
    }
}
