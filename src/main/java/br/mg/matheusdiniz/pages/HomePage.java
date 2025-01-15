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
        ContaPage contaPage = new ContaPage();
        List<String> contas = contaPage.listAllAccountCreated();

        HomePage homePage = new HomePage();
        homePage.goToHomePage();

//TODO: AJUSTAR MÉTODO PARA NÃO QUEBRAR NOS CASOS EM QUE A CONTA NÃO TEM SALDO POIS NÃO HÁ MOVIMENTAÇÃO
        for (String conta : contas){
            if (wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[td[contains(text(), '"+conta+"')]]"))).isDisplayed()){
                String saldoConta = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[td[contains(text(), '"+conta+"')]]/td[2]"))).getText();
                Assert.assertNotNull(saldoConta);
                System.out.println("Saldo da conta "+conta+": R$"+saldoConta);
            } else System.out.println("Conta não possui saldo!");
        }
    }
}
