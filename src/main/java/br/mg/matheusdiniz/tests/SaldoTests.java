package br.mg.matheusdiniz.tests;

import br.mg.matheusdiniz.core.BaseTest;
import br.mg.matheusdiniz.pages.ContaPage;
import br.mg.matheusdiniz.pages.HomePage;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class SaldoTests extends BaseTest {

    @Test
    public void consultaSaldoContas() {
        HomePage homePage = new HomePage();
        homePage.goToHomePage();

        homePage.validaSaldoContas();
    }
}
