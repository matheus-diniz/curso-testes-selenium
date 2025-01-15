package br.mg.matheusdiniz.tests;

import br.mg.matheusdiniz.core.BaseTest;
import br.mg.matheusdiniz.pages.ContaPage;
import com.github.javafaker.Faker;
import org.junit.Test;


public class ContaTests extends BaseTest {
    Faker faker = new Faker();

    @Test
    public void inserirConta() {
        ContaPage contaPage = new ContaPage();
        contaPage.adicionarConta(faker.numerify("######-#"));
    }

    @Test
    public void removerConta() {
        ContaPage contaPage = new ContaPage();
        contaPage.removerConta(faker.numerify("######-#"));
    }

    @Test
    public void inserirContaExistente(){
        ContaPage contaPage = new ContaPage();
        contaPage.inserirContaRepetida();

    }
}
