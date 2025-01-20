package br.mg.matheusdiniz.core;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

import static br.mg.matheusdiniz.core.DriverFactory.*;

public class BasePage {
    private static WebDriver driver;
    public WebDriverWait wait = new WebDriverWait(DriverFactory.getDriver(), 30);

    public BasePage() {
        driver = DriverFactory.getDriver();
    }

    /********* TextField e TextArea ************/

    public void escrever(By by, String texto){
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        getDriver().findElement(by).clear();
        getDriver().findElement(by).sendKeys(texto);
    }

    public void sendKeys(WebElement element, String value){
        wait.until(ExpectedConditions.visibilityOf(element));
        highlight(element);
//        element.click();
        element.sendKeys(value);
    }

    public void escrever(String id_campo, String texto){
        escrever(By.id(id_campo), texto);
    }

    public String obterValorCampo(String id_campo) {
        return getDriver().findElement(By.id(id_campo)).getAttribute("value");
    }

    public Boolean isElementXpathExists(String element){
        boolean isExists = true;

        if (DriverFactory.getDriver().findElements(By.xpath(element)).isEmpty()){
            isExists = false;
        }

        return isExists;
    }

    /********* Radio e Check ************/

    public void clicarRadio(By by) {
        getDriver().findElement(by).click();
    }

    public void clicarRadio(String id) {
        clicarRadio(By.id(id));
    }

    public boolean isRadioMarcado(String id){
        return getDriver().findElement(By.id(id)).isSelected();
    }

    public void clicarCheck(String id) {
        getDriver().findElement(By.id(id)).click();
    }

    public boolean isCheckMarcado(String id){
        return getDriver().findElement(By.id(id)).isSelected();
    }

    /********* Combo ************/

    public void selecionarCombo(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.selectByVisibleText(valor);
    }

    public void deselecionarCombo(String id, String valor) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        combo.deselectByVisibleText(valor);
    }

    public String obterValorCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        return combo.getFirstSelectedOption().getText();
    }

    public List<String> obterValoresCombo(String id) {
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> allSelectedOptions = combo.getAllSelectedOptions();
        List<String> valores = new ArrayList<String>();
        for(WebElement opcao: allSelectedOptions) {
            valores.add(opcao.getText());
        }
        return valores;
    }

    public int obterQuantidadeOpcoesCombo(String id){
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        return options.size();
    }

    public boolean verificarOpcaoCombo(String id, String opcao){
        WebElement element = getDriver().findElement(By.id(id));
        Select combo = new Select(element);
        List<WebElement> options = combo.getOptions();
        for(WebElement option: options) {
            if(option.getText().equals(opcao)){
                return true;
            }
        }
        return false;
    }

    public void selecionarComboPrime(String radical, String valor) {
        clicarRadio(By.xpath("//*[@id='"+radical+"_input']/../..//span"));
        clicarRadio(By.xpath("//*[@id='"+radical+"_items']//li[.='"+valor+"']"));
    }

    /********* Botao ************/

    public void clicarBotao(String id) {
        clicarBotao(By.id(id));
    }

    public void clicarBotao(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        getDriver().findElement(by).click();
    }

    public void clickClickableElement(WebElement element){
        wait.until(ExpectedConditions.visibilityOf(element));
        highlight(element);
        try {
            element.click();
        } catch (Exception e){
            System.out.println("O elemento: "+element+" nao foi encontrado. "+e);
        }

    }

    public String obterValueElemento(String id) {
        return getDriver().findElement(By.id(id)).getAttribute("value");
    }

    /********* Link ************/

    public void clicarLink(String link) {
        getDriver().findElement(By.linkText(link)).click();
    }

    /********* Textos ************/

    public String obterTexto(By by) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        return getDriver().findElement(by).getText();
    }

    public String obterTexto(String id) {
        return obterTexto(By.id(id));
    }

    /********* Alerts ************/

    public String alertaObterTexto(){
        Alert alert = getDriver().switchTo().alert();
        return alert.getText();
    }

    public String alertaObterTextoEAceita(){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.accept();
        return valor;

    }

    public String alertaObterTextoENega(){
        Alert alert = getDriver().switchTo().alert();
        String valor = alert.getText();
        alert.dismiss();
        return valor;

    }

    public void alertaEscrever(String valor) {
        Alert alert = getDriver().switchTo().alert();
        alert.sendKeys(valor);
        alert.accept();
    }

    /********* Frames e Janelas ************/

    public void entrarFrame(String id) {
        getDriver().switchTo().frame(id);
    }

    public void sairFrame(){
        getDriver().switchTo().defaultContent();
    }

    public void trocarJanela(String id) {
        getDriver().switchTo().window(id);
    }

    /************** JS *********************/

    public Object executarJS(String cmd, Object... param) {
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        return js.executeScript(cmd, param);
    }

    /************** Tabela *********************/

    public void clicarBotaoTabela(String colunaBusca, String valor, String colunaBotao, String idTabela){
        //procurar coluna do registro
        WebElement tabela = getDriver().findElement(By.xpath("//*[@id='"+idTabela+"']"));
        int idColuna = obterIndiceColuna(colunaBusca, tabela);

        //encontrar a linha do registro
        int idLinha = obterIndiceLinha(valor, tabela, idColuna);

        //procurar coluna do botao
        int idColunaBotao = obterIndiceColuna(colunaBotao, tabela);

        //clicar no botao da celula encontrada
        WebElement celula = tabela.findElement(By.xpath(".//tr["+idLinha+"]/td["+idColunaBotao+"]"));
        celula.findElement(By.xpath(".//input")).click();

    }

    protected int obterIndiceLinha(String valor, WebElement tabela, int idColuna) {
        List<WebElement> linhas = tabela.findElements(By.xpath("./tbody/tr/td["+idColuna+"]"));
        int idLinha = -1;
        for(int i = 0; i < linhas.size(); i++) {
            if(linhas.get(i).getText().equals(valor)) {
                idLinha = i+1;
                break;
            }
        }
        return idLinha;
    }

    protected int obterIndiceColuna(String coluna, WebElement tabela) {
        List<WebElement> colunas = tabela.findElements(By.xpath(".//th"));
        int idColuna = -1;
        for(int i = 0; i < colunas.size(); i++) {
            if(colunas.get(i).getText().equals(coluna)) {
                idColuna = i+1;
                break;
            }
        }
        return idColuna;
    }

    private void highlight(WebElement element){
        executarJS("arguments[0].style.border = arguments[1]", element, "solid 4px #00ff00");
    }
}
