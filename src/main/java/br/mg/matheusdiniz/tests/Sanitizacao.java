package br.mg.matheusdiniz.tests;

import br.mg.matheusdiniz.core.BaseTest;
import br.mg.matheusdiniz.pages.ContaPage;
import br.mg.matheusdiniz.pages.MovimentacaoPage;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.util.List;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)

public class Sanitizacao extends BaseTest {

    @Test
    public void test01limparTodasMovimentacoes() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.limparTodasMovimentacoes();
    }

    @Test
    public void test02limparTodasContas() {
        ContaPage contaPage = new ContaPage();
        List<String> contas = contaPage.listAllAccountCreated();

        for (String conta : contas){
            contaPage.removerConta(conta);
        }

    }
}
