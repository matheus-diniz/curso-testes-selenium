package br.mg.matheusdiniz.tests;

import br.mg.matheusdiniz.core.BaseTest;
import br.mg.matheusdiniz.pages.ContaPage;
import br.mg.matheusdiniz.pages.MovimentacaoPage;
import com.github.javafaker.Faker;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class MovimentacaoTests extends BaseTest {

    @Test
    public void test01CamposObrigatorios() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToMovimentacaoPage();
        movimentacaoPage.validaCamposObrigatorios();
    }

    @Test
    public void test02ValidarMovimentacaoFutura() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToMovimentacaoPage();
        movimentacaoPage.validarMovimentacaoFutura();
    }

    //        @Test
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


    @Test
    public void test04RemoverMovimentacao() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToExtratoPage();
        movimentacaoPage.removerMovimentacao();
    }

    @Test
    public void test05RemoverContaComMovimentacao() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        movimentacaoPage.goToExtratoPage();

        String account = movimentacaoPage.getLastAccountNumber();

        ContaPage contaPage = new ContaPage();
        contaPage.validarRemocaoDeContaComMovimentacao(account);
    }

    @Test
    public void test06_validaFiltrosExtrato() {
        MovimentacaoPage movimentacaoPage = new MovimentacaoPage();
        Faker faker = new Faker();

        LocalDate today = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MMMM", new Locale("pt", "BR"));

        String monthsAdd = today.plusMonths(Long.parseLong(faker.numerify("##"))).format(monthFormatter);

        movimentacaoPage.validaFiltrosExtrato(monthsAdd, String.valueOf(today.getYear()));
    }

    @Test
    public void test03_cadastrarMovimentacoes() {
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

        for (int i = 0; i < 5; i++) {
            int randomContas = random.nextInt(contas.size());
            int randomMov = random.nextInt(tipoMovimentacao.size());
            int randomSituacao = random.nextInt(tipoSituacao.size());

            movimentacaoPage.cadastrarMovimentacao(tipoMovimentacao.get(randomMov), contas.get(randomContas), tipoSituacao.get(randomSituacao));
        }
    }
}






