package com.project.utils;

import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class WebDriverUtil {
    public static ChromeDriver getWebDriver(String path, String webDriverLib, String driverEx) {
        System.setProperty(webDriverLib,  System.getProperty("user.dir") + "\\webdrivers\\"+  driverEx);
        ChromeDriver driver = new ChromeDriver();
        driver.get(path);

        return driver;
    }
    public static void setTimeOutSec(ChromeDriver driver, long sec){
        driver.manage().timeouts().pageLoadTimeout(sec, TimeUnit.SECONDS);
    }
}
