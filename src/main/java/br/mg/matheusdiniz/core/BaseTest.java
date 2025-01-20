package br.mg.matheusdiniz.core;

import br.mg.matheusdiniz.pages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.mg.matheusdiniz.core.DriverFactory.getDriver;
import static br.mg.matheusdiniz.core.DriverFactory.killDriver;

import org.junit.Before;
import org.junit.After;
import org.junit.BeforeClass;
import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.Test;

public class BaseTest {

    @Rule
    public TestName testName = new TestName();

    @BeforeClass
    public static void setUp() {
        DriverFactory.getInstance();
    }

    @Before
    public void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();

    }

    @After
    public void logoffAndScreenShot() throws IOException {
        takeScreenShot();
        logout();
//        if (Propriedades.FECHAR_BROWSER) {
//            DriverFactory.getDriver().quit();
//        }
    }

    public void takeScreenShot() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" +
                File.separator + testName.getMethodName() + ".jpg"));
    }

    @AfterClass
    public static void finalizaTestes() {
        killDriver();
    }

    public void logout(){
        DriverFactory.getDriver().findElement(By.xpath("//a[contains(text(), 'Sair')]")).click();
    }


}
