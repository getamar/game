package com.uk.utils;

import cucumber.api.Scenario;
import cucumber.api.java.Before;
import cucumber.api.java.After;
import org.apache.commons.io.FileUtils;


import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.TimeUnit;

import static com.uk.utils.PageObject.waitForPageToBeLoaded;


public class SharedDriver {

    public static WebDriver driver;
    PageObject pageObject = new PageObject(driver);

    @Before("@UI")
    public void getBrowser() throws IOException {
        String desiredBrowser = pageObject.readDataFromProperties("Browser");
        if (desiredBrowser.equalsIgnoreCase("chrome")) {
            setChromeDriver();
        }
    }

    @After("@UI")
    public void tearDown(Scenario scenario) throws IOException
    {
        SimpleDateFormat dateTimeInGMT = new SimpleDateFormat("yyyy-MMM-dd hh:mm:ss aa");
        dateTimeInGMT.setTimeZone(TimeZone.getTimeZone("GMT"));
        if(scenario.isFailed())
        {
            File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(System.getProperty("user.dir")+
                    "/src/test/java/com/uk/screenshots/"+scenario.getId()+dateTimeInGMT.format(new Date())+".png"));

            scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
        }
    }

    @After("@UI")
    public static void closeDriver(){
        driver.quit();
    }

    public void openURL() throws IOException {
        driver.get(pageObject.readDataFromProperties("BASE_URL"));
        waitForPageToBeLoaded(driver);
    }


    private static void setChromeDriver() {
        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"/src/test/java/com/uk/utils/drivers/chromedriver");
        driver = new ChromeDriver();
        if (driver == null) {
            throw new IllegalStateException("Selenium client is not initialised.");
        }
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().fullscreen();
    }

}
