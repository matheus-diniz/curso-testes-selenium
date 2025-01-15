package br.mg.matheusdiniz.tests;

import br.mg.matheusdiniz.core.BaseTest;
import br.mg.matheusdiniz.pages.ContaPage;
import br.mg.matheusdiniz.pages.MovimentacaoPage;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovimentacaoTests extends BaseTest {

    //    @Test
    public void test01CamposObrigatorios() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToMovimentacaoPage();
        movimentacaoPage.validaCamposObrigatorios();
    }

    //    @Test
    public void test02ValidarMovimentacaoFutura() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToMovimentacaoPage();
        movimentacaoPage.validarMovimentacaoFutura();
    }

    //    @Test
    public void test03CadastrarMovimentacao() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToMovimentacaoPage();
        movimentacaoPage.cadastrarMovimentacao();
    }

//    @Test
//    public void criarMassaDeDados(){
//        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
//        movimentacaoPage.goToMovimentacaoPage();
//        for (int i=0;i<6;i++){
//            movimentacaoPage.cadastrarMovimentacao();
//        }


    //    @Test
    public void test04RemoverMovimentacao() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToExtratoPage();
        movimentacaoPage.removerMovimentacao();
    }

    //    @Test
    public void test05RemoverContaComMovimentacao() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToExtratoPage();

        String account = movimentacaoPage.getLastAccountNumber();

        ContaPage contaPage = new ContaPage();
        contaPage.validarRemocaoDeContaComMovimentacao(account);
    }

    @Test
    public void cadastrarMovimentacoes() {
        ContaPage contaPage = new ContaPage();
        List<String> contas = contaPage.listAllAccountCreated();

        List<String> tipoMovimentacao = new ArrayList<>();
        tipoMovimentacao.add("Receita");
        tipoMovimentacao.add("Despesa");

        List<String> tipoSituacao = new ArrayList<>();
        tipoSituacao.add("Pago");
        tipoSituacao.add("Pendente");

        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int randomContas = random.nextInt(0, contas.size()+1);
            int randomMov = random.nextInt(0, tipoMovimentacao.size()+1);
            int randomSituacao = random.nextInt(0, tipoSituacao.size()+1);

            movimentacaoPage.cadastrarMovimentacao(tipoMovimentacao.get(randomMov), contas.get(randomContas), tipoSituacao.get(randomSituacao));
        }
    }
}



