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

public class ContaPage extends BasePage {

    private final WebDriver driver;

    public ContaPage() {
        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(how = How.XPATH, using = "//li[@class='dropdown']")
    protected WebElement menuConta;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Adicionar')]")
    protected WebElement opAdicionarConta;

    @FindBy(how = How.XPATH, using = "//a[contains(text(), 'Listar')]")
    protected WebElement opListarContas;

    @FindBy(how = How.XPATH, using = "//input[@id='nome']")
    protected  WebElement iptNomeConta;

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    protected  WebElement btnSalvarConta;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-success']")
    protected WebElement alertSucess;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']")
    protected WebElement alertDanger;

    public void adicionarConta(String conta){
        System.out.println("Criando conta "+conta+"...");
        clickClickableElement(menuConta);
        clickClickableElement(opAdicionarConta);
        sendKeys(iptNomeConta, conta);
        clickClickableElement(btnSalvarConta);

        String textAlert = wait.until(ExpectedConditions.visibilityOf(alertSucess)).getText();
        Assert.assertEquals("Conta adicionada com sucesso!", textAlert);
    }

    public void removerConta(String conta){
        clickClickableElement(menuConta);
        clickClickableElement(opListarContas);

        if (isElementXpathExists("//tr/td[contains(text(), '"+conta+"')]")){
            System.out.println("Conta "+conta+" existe! Removendo...");
            clicarBotao(By.xpath("//tr/td[contains(text(), '"+conta+"')]/..//a[contains(@href, 'remover')]"));
        } else {
            System.out.println("Conta "+conta+" não existe! Removendo a ultima...");
            clicarBotao(By.xpath("//tr[last()]/td[last()]/..//a[contains(@href, 'remover')]"));
        }

        String textAlert = wait.until(ExpectedConditions.visibilityOf(alertSucess)).getText();
        Assert.assertEquals("Conta removida com sucesso!", textAlert);
    }

    public void inserirContaRepetida(){
        clickClickableElement(menuConta);
        clickClickableElement(opListarContas);

        String contaExistente = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[last()]/td[1]"))).getText();

        System.out.println("Ultima conta da lista "+contaExistente+". Inserindo ela novamente...");

        clickClickableElement(menuConta);
        clickClickableElement(opAdicionarConta);

        sendKeys(iptNomeConta, contaExistente);
        clickClickableElement(btnSalvarConta);

        String textAlert = wait.until(ExpectedConditions.visibilityOf(alertDanger)).getText();
        Assert.assertEquals("Já existe uma conta com esse nome!", textAlert);
    }



}
