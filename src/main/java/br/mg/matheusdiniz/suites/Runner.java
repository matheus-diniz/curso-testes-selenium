package br.mg.matheusdiniz.suites;


import br.mg.matheusdiniz.pages.LoginPage;
import br.mg.matheusdiniz.tests.ContaTests;
import br.mg.matheusdiniz.tests.MovimentacaoTests;
import br.mg.matheusdiniz.tests.SaldoTests;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static br.mg.matheusdiniz.core.DriverFactory.killDriver;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContaTests.class,
        MovimentacaoTests.class,
        SaldoTests.class
})
public class Runner {

    @BeforeClass
    public static void login() {
        LoginPage loginPage = new LoginPage();
        loginPage.login();
    }

    @AfterClass
    public static void finalizaTestes() {
        killDriver();
    }


}
