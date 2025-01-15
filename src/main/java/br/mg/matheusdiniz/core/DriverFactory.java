package br.mg.matheusdiniz.core;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DriverFactory {
    static WebDriver driver;

    private DriverFactory() {}

    public static WebDriver getDriver(){
        if(driver == null) {
            switch (Propriedades.browser) {
                case FIREFOX: startFirefoxDriver(); break;
                case CHROME: startChromeDriver(); break;
            }
        }
        return driver;
    }

    private static void startChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/geckodriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    private static void startFirefoxDriver(){
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
    }

    public static void killDriver(){
        if(driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
