package com.project.service;

import com.project.utils.WebDriverUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

public class PromLoginService {
    public static void createLogin(String name, String email, String password) {
        String webDriverName = "webdriver.chrome.driver";
        String driverEx = "chromedriver.exe";
        ChromeDriver driver = WebDriverUtil.getWebDriver("https://prom.ua/join-customer?source_id=txt.register.customer" , webDriverName, driverEx);
        WebDriverUtil.setTimeOutSec(driver,  10);
        setLoginFields(driver, name, email, password);
        WebDriverUtil.setTimeOutSec(driver, 50);
        driver.quit();


    }

    private static void setLoginFields(ChromeDriver driver, String name, String email, String pass) {
        driver.findElement(By.name("vertical-name")).sendKeys(name);
        driver.findElementByName("vertical-email").sendKeys(email);
        driver.findElementByName("vertical-password").sendKeys(pass);
        driver.findElementByClassName("lp-button__text").click();
    }
}
