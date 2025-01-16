package br.mg.matheusdiniz.pages;

import br.mg.matheusdiniz.core.BasePage;
import br.mg.matheusdiniz.core.DriverFactory;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HomePage extends BasePage {

    public HomePage() {
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(how = How.XPATH, using = "//a[@href='/']")
    protected WebElement menuHome;

    public void goToHomePage(){
        clickClickableElement(menuHome);
    }

    public void validaSaldoContas(){
//        ContaPage contaPage = new ContaPage();
//        List<String> contas = contaPage.listAllAccountCreated();

        HomePage homePage = new HomePage();
        homePage.goToHomePage();

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr")));

        List<WebElement> listElements = DriverFactory.getDriver().findElements(By.xpath("//tbody//tr"));

        Assert.assertFalse(listElements.isEmpty());

        for (int i=0;i<listElements.size();i++){
            System.out.println("Conta: "+wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr["+(i+1)+"]//td[1]"))).getText());
            System.out.println("Saldo: "+wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tbody//tr["+(i+1)+"]//td[2]"))).getText());
        }
    }
}
