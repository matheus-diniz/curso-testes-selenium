package br.mg.matheusdiniz.core;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class DriverFactory {
    //    static WebDriver driver;
//    static WebDriver driver;
//    private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>() {
//        @Override
//        protected synchronized WebDriver initialValue() {
//            return initDriver();
//        }
//    };
//    private static DriverFactory instance;
    private static ThreadLocal<WebDriver> threadDriver = ThreadLocal.withInitial(() -> initDriver());

    private DriverFactory() {
    }

    public static synchronized ThreadLocal<WebDriver> getInstance(){
        if (threadDriver == null){
            threadDriver = new ThreadLocal<>();
        }
        return threadDriver;
    }

    public static WebDriver getDriver() {
        return threadDriver.get();
    }

    public static WebDriver initDriver() {
        WebDriver driver;
        switch (Propriedades.browser) {
            case FIREFOX:
                driver = startFirefoxDriver();
                break;
            case CHROME:
                driver = startChromeDriver();
                break;
            default:
                throw new IllegalStateException("Navegador não suportado: " + Propriedades.browser);
        }
        driver.manage().window().maximize();
        return driver;
    }

    private static WebDriver startChromeDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/driver/chromedriver.exe");
        return new ChromeDriver();
    }

    private static WebDriver startFirefoxDriver() {
        System.setProperty("webdriver.gecko.driver", "src/main/resources/driver/geckodriver.exe");

        // Configurar as opções do Firefox para iniciar no modo privado
         FirefoxOptions options = new FirefoxOptions();
         options.addArguments("-private");
        return new FirefoxDriver();
    }

    public static void killDriver() {
        WebDriver driver = threadDriver.get();
        if (driver != null){
            driver.quit();
            threadDriver.remove();
        }
    }
}
