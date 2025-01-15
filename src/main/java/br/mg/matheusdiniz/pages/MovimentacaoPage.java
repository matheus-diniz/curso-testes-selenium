package br.mg.matheusdiniz.pages;

import br.mg.matheusdiniz.core.BasePage;
import br.mg.matheusdiniz.core.DriverFactory;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class MovimentacaoPage extends BasePage {

    public MovimentacaoPage() {
//        this.driver = DriverFactory.getDriver();
        PageFactory.initElements(DriverFactory.getDriver(), this);
    }

    @FindBy(how = How.XPATH, using = "//button[@type='submit']")
    protected WebElement btnSalvarMovimentacao;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-danger']")
    protected WebElement alertDanger;

    @FindBy(how = How.XPATH, using = "//a[@href='/movimentacao']")
    protected WebElement menuMovimentacao;

    @FindBy(how = How.ID, using = "data_transacao")
    protected WebElement iptDataTransacao;

    @FindBy(how = How.ID, using = "data_pagamento")
    protected WebElement iptDataPagamento;

    @FindBy(how = How.ID, using = "descricao")
    protected WebElement iptDescricao;

    @FindBy(how = How.ID, using = "interessado")
    protected WebElement iptInteressado;

    @FindBy(how = How.ID, using = "valor")
    protected WebElement iptValor;

    @FindBy(how = How.XPATH, using = "//input[@id='status_pendente']")
    protected WebElement rdSituacaoPendente;

    @FindBy(how = How.XPATH, using = "//div[@class='alert alert-success']")
    protected WebElement alertSucess;

    @FindBy(how = How.XPATH, using = "//a[@href='/extrato']")
    protected WebElement menuExtrato;

    @FindBy(how = How.XPATH, using = "//tr[last()]//td/a")
    protected WebElement actionRemoverUltimaTransacao;

    public void goToMovimentacaoPage() {
        clickClickableElement(menuMovimentacao);
    }

    public void goToExtratoPage(){
        clickClickableElement(menuExtrato);
    }

    public void validaCamposObrigatorios() {
        clickClickableElement(btnSalvarMovimentacao);

        if (wait.until(ExpectedConditions.visibilityOf(alertDanger)).isDisplayed()) {
            Assert.assertTrue(true);
            List<WebElement> listItens = DriverFactory.getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//ul/li"));
            for (WebElement item : listItens) {
                System.out.println(item.getText());
            }
        }
    }

    public void validarMovimentacaoFutura(){
        Faker faker = new Faker();
        Date future = faker.date().future(10, TimeUnit.DAYS);
        Date payment = faker.date().past(3, TimeUnit.DAYS);

        LocalDate today = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        sendKeys(iptDataTransacao,formatter.format(future));
        sendKeys(iptDataPagamento,formatter.format(payment));
        sendKeys(iptDescricao, "Pagamento "+today.format(monthFormatter)+" "+faker.name().firstName());
        sendKeys(iptInteressado, faker.name().firstName());
        sendKeys(iptValor, faker.numerify("####.##"));
//        clicarRadio(By.xpath("//input[@id='status_pendente']"));

        clickClickableElement(btnSalvarMovimentacao);

        if (wait.until(ExpectedConditions.visibilityOf(alertDanger)).isDisplayed()) {
            List<WebElement> listItens = DriverFactory.getDriver().findElements(By.xpath("//div[@class='alert alert-danger']//ul/li"));
            for (WebElement item : listItens) {
                System.out.println(item.getText());
               Assert.assertEquals("Data da Movimentação deve ser menor ou igual à data atual", item.getText());
            }
        }
    }

    public void cadastrarMovimentacao(){
        Faker faker = new Faker();
        Date future = faker.date().future(5, TimeUnit.DAYS);
        Date payment = faker.date().past(5, TimeUnit.DAYS);

        LocalDate today = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        sendKeys(iptDataTransacao,formatter.format(payment));
        sendKeys(iptDataPagamento,formatter.format(future));
        sendKeys(iptDescricao, "Pagamento "+today.format(monthFormatter)+" "+faker.name().firstName());
        sendKeys(iptInteressado, faker.name().firstName());
        sendKeys(iptValor, faker.numerify("####.##"));
//        clicarRadio(By.xpath("//input[@id='status_pendente']"));

        clickClickableElement(btnSalvarMovimentacao);

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(alertSucess)).isDisplayed());
        Assert.assertEquals("Movimentação adicionada com sucesso!",wait.until(ExpectedConditions.visibilityOf(alertSucess)).getText());
    }

    public void removerMovimentacao(){
        List<String> dadosUltimaMovimentacao = new ArrayList<>();

        for (int i=1;i<6;i++){
            dadosUltimaMovimentacao.add(wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[last()]//td["+i+"]"))).getText());
        }

        System.out.println("Dados da última movimentação da lista...");
        for (String data : dadosUltimaMovimentacao){
            System.out.println(data);
        }

        System.out.println("Removendo movimentação...");
        clickClickableElement(actionRemoverUltimaTransacao);

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(alertSucess)).isDisplayed());
        Assert.assertEquals("Movimentação removida com sucesso!",wait.until(ExpectedConditions.visibilityOf(alertSucess)).getText());
    }

    public String getLastAccountNumber(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[last()]//td[3]"))).getText();
    }

    public String getAllAccountNumber(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[last()]//td[3]"))).getText();
    }



    public void cadastrarMovimentacao(String tipoMovimentacao, String conta, String situacao){
//        System.out.println("Parametros recebidos\nTipo Movimentacao: "+tipoMovimentacao+" Conta: "+conta+" Situacao: "+situacao);

        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToMovimentacaoPage();

        Faker faker = new Faker();
        Date future = faker.date().future(7, TimeUnit.DAYS);
        Date payment = faker.date().past(7, TimeUnit.DAYS);

        LocalDate today = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        selecionarCombo("tipo", tipoMovimentacao);
        sendKeys(iptDataTransacao,formatter.format(payment));
        sendKeys(iptDataPagamento,formatter.format(future));
        sendKeys(iptDescricao, "Pagamento "+today.format(monthFormatter)+" "+faker.name().firstName());
        sendKeys(iptInteressado, faker.name().firstName());
        sendKeys(iptValor, faker.numerify("####.##"));
        selecionarCombo("conta", conta);
        clicarRadio(By.xpath("//input[contains(@id, '"+situacao.toLowerCase()+"')]"));

        clickClickableElement(btnSalvarMovimentacao);

        Assert.assertTrue(wait.until(ExpectedConditions.visibilityOf(alertSucess)).isDisplayed());
        Assert.assertEquals("Movimentação adicionada com sucesso!",wait.until(ExpectedConditions.visibilityOf(alertSucess)).getText());
    }
}
