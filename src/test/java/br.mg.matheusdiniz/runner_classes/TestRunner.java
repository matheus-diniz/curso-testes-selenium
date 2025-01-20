package br.mg.matheusdiniz.runner_classes;

import br.mg.matheusdiniz.tests.ContaTests;
import br.mg.matheusdiniz.tests.MovimentacaoTests;
import br.mg.matheusdiniz.tests.SaldoTests;
import br.mg.matheusdiniz.tests.Sanitizacao;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ContaTests.class,
        MovimentacaoTests.class,
        SaldoTests.class,
//        Sanitizacao.class
})
public class TestRunner {

}