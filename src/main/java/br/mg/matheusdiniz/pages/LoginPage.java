package br.mg.matheusdiniz.pages;

import br.mg.matheusdiniz.core.BasePage;
import br.mg.matheusdiniz.core.DriverFactory;
import br.mg.matheusdiniz.core.Propriedades;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Instant;

public class LoginPage extends BasePage {

    //matheusalvesdiniz+2025@gmail.com
    //matheus1234

    private final WebDriver driver;

    public LoginPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.ID, using = "email")
    protected WebElement iptLogin;

    @FindBy(how = How.ID, using = "senha")
    protected WebElement iptSenha;

    @FindBy(how = How.XPATH, using = "//button[.='Entrar']")
    protected WebElement btnEntrar;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-success']")
    protected WebElement alertSucess;

    public void login() {
        DriverFactory.getDriver().get(Propriedades.URL_SEU_BARRIGA_APP);
        sendKeys(iptLogin, Propriedades.LOGIN);
        sendKeys(iptSenha, Propriedades.SENHA);
        clickClickableElement(btnEntrar);
        String textAlert = wait.until(ExpectedConditions.visibilityOf(alertSucess)).getText();
        Assert.assertTrue(textAlert.contains("Bem vindo"));
    }

}
