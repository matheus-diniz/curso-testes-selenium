package br.mg.matheusdiniz.suites;


import br.mg.matheusdiniz.core.DriverFactory;
import br.mg.matheusdiniz.core.Propriedades;
import br.mg.matheusdiniz.pages.LoginPage;
import br.mg.matheusdiniz.tests.ContaTests;
import br.mg.matheusdiniz.tests.MovimentacaoTests;
import br.mg.matheusdiniz.tests.SaldoTests;
import br.mg.matheusdiniz.tests.Sanitizacao;
import org.apache.commons.io.FileUtils;
import org.junit.*;
import org.junit.rules.TestName;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static br.mg.matheusdiniz.core.DriverFactory.getDriver;
import static br.mg.matheusdiniz.core.DriverFactory.killDriver;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContaTests.class,
        MovimentacaoTests.class,
        SaldoTests.class,
//        Sanitizacao.class
})
public class TestRunner {

}