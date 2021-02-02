import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    private Pessoa maria;
    private Pessoa joao;
    private Agencia minhaAgencia;
    private ContaCorrente contaDaMaria;
    private ContaCorrente contaDoJoao;
    private static final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;

    private Map<Long, Pessoa> mapaDeCorrentistas;
    private ArrayList<Pessoa> arrayListDeCorrentistas;


    @Before
    public void setUp(){

        mapaDeCorrentistas = new HashMap<>();
        arrayListDeCorrentistas = new ArrayList<>();

        maria = new Pessoa("Maria", 12345678);
        joao = new Pessoa("Joao", 23222);

        minhaAgencia = new Agencia();

        contaDaMaria = new ContaCorrente(maria, minhaAgencia);
        contaDoJoao = new ContaCorrente(joao, minhaAgencia);

        assertFloatsEquals(ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS, contaDaMaria.getSaldoEmReais());
        assertFloatsEquals(ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS, contaDoJoao.getSaldoEmReais());


    }

    @Test
    public void testarNumerosAutomaticosDeConta(){

        ContaCorrente novaConta = new ContaCorrente(maria, minhaAgencia);

        long numeroNovaConta = novaConta.getNumeroDaConta();

        assertEquals(numeroNovaConta + 1, (new ContaCorrente(maria, minhaAgencia)).getNumeroDaConta());
        assertEquals(numeroNovaConta + 2, (new ContaCorrente(joao, minhaAgencia)).getNumeroDaConta());

    }

    @Test
    public void testarDeposito(){
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.depositar(1000);
        assertFloatsEquals(1010f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(500);
        assertFloatsEquals(1510f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(-1);
        assertFloatsEquals(1510f, contaDaMaria.getSaldoEmReais());


    }

    @Test
    public void testarSaque() {

        // sanity check
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.sacar(7);
        assertFloatsEquals(3f, contaDaMaria.getSaldoEmReais());

    }

    @Test
    public void testarSaqueSemFundos() {
        checarSaldoInicial(contaDaMaria);
        contaDaMaria.sacar(17);
        assertFloatsEquals(10f, contaDaMaria.getSaldoEmReais());

    }

    @Test
    public void testarTranferencia() {
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        contaDaMaria.transferir(7, contaDoJoao);

        assertFloatsEquals(3f, contaDaMaria.getSaldoEmReais());
        assertFloatsEquals(17f, contaDoJoao.getSaldoEmReais());

    }

    @Test
    public void testarTranferenciaSemFundosNaContaDeOrigem() {
        // sanity check
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        contaDaMaria.transferir(200f, contaDoJoao);

        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

    }

    private void checarSaldoInicial(ContaCorrente conta){
        assertEquals(ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    public static void assertFloatsEquals(float expected, float actual){
        assertEquals(expected, actual, ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarPerformance(){
        rodarTesteDePerformanceParaMapa(mapaDeCorrentistas);
        rodarTesteDePerformanceParaArrayList(arrayListDeCorrentistas);
    }

    private void rodarTesteDePerformanceParaMapa(Map<Long, Pessoa> mapa){

        System.out.println("Teste de performance para HashMap de correntistas");
        long inicio = System.currentTimeMillis();
        for(long i = 0; i < 100_000; i++){
            mapa.put(i, new Pessoa("nome", i));
        }

        for(int i = 0; i < 100_000; i++){
            mapa.get(i);
        }
        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Duração %.3f segundos\n", duracao/1000f);
    }

    private void rodarTesteDePerformanceParaArrayList(ArrayList<Pessoa> arrayList){

        System.out.println("Teste de performance para ArrayList de correntistas");
        long inicio = System.currentTimeMillis();
        for(long i = 0; i < 100_000; i++){
            arrayListDeCorrentistas.add(new Pessoa("nome", i));
        }

        for(int i = 0; i < 100_000; i++){
            arrayList.indexOf(arrayList.get(i));
        }

        long duracao = System.currentTimeMillis() - inicio;
        System.out.printf("Duração %.3f segundos\n", duracao/1000f);
    }

}