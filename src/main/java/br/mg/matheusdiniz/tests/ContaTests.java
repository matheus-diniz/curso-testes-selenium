package br.mg.matheusdiniz.tests;

import br.mg.matheusdiniz.core.BaseTest;
import br.mg.matheusdiniz.pages.ContaPage;
import com.github.javafaker.Faker;
import org.junit.*;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ContaTests extends BaseTest {
    Faker faker = new Faker();

    @Test
    public void test01inserirConta() {
        ContaPage contaPage = new ContaPage();
        contaPage.adicionarConta(faker.numerify("######-#"));
    }

    @Test
    public void test02inserirXContas() {
        ContaPage contaPage = new ContaPage();
        for (int i = 0; i<5; i++){
            contaPage.adicionarConta(faker.numerify("######-#"));
        }
    }

    @Test
    public void test02removerConta() {
        ContaPage contaPage = new ContaPage();
        contaPage.removerConta(faker.numerify("######-#"));
    }

    @Test
    public void test03inserirContaExistente(){
        ContaPage contaPage = new ContaPage();
        contaPage.inserirContaRepetida();
    }
}
