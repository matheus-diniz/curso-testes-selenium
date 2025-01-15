package br.mg.matheusdiniz.core;

import br.mg.matheusdiniz.pages.LoginPage;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.mg.matheusdiniz.core.DriverFactory.getDriver;
import static br.mg.matheusdiniz.core.DriverFactory.killDriver;

public class BaseTest {


    @Rule
    public TestName testName = new TestName();

    @Before
    public void inicializa(){
        DriverFactory.getDriver();
    }

    @BeforeClass
    public static void login(){
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }

    @After
    public void finaliza() throws IOException {
        TakesScreenshot ss = (TakesScreenshot) getDriver();
        File arquivo = ss.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(arquivo, new File("target" + File.separator + "screenshot" +
                File.separator + testName.getMethodName() + ".jpg"));

        if(Propriedades.FECHAR_BROWSER) {
            killDriver();
        }
    }

    @AfterClass
    public static void finalizaTestes(){
        killDriver();
    }
}
