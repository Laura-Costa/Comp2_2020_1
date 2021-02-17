import excecoes.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContaCorrenteTest {

    private static final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;

    private Pessoa maria;
    private Pessoa joao;

    private Banco banco;

    private Agencia minhaAgencia;

    private ContaCorrente contaDaMaria;
    private ContaCorrente contaDoJoao;

    @Before
    public void setUp() {
        banco = new Banco();

        // cria algumas pessoas
        maria = new Pessoa("Maria", 12345678);
        joao = new Pessoa("Joao", 23222);

        // cria uma agencia
        minhaAgencia = new Agencia(banco, 1, "Agência Principal");

        ContaCorrente.numeroDeContasCriadas = 0;  // reseta o static da classe

        // cria algumas contas
        contaDaMaria = new ContaCorrente(maria, minhaAgencia);
        contaDoJoao = new ContaCorrente(joao, minhaAgencia);
    }

    @Test
    public void testeParaNumerosAutomaticosDeContas() {
        ContaCorrente novaConta = new ContaCorrente(maria, minhaAgencia);
        long numeroDaConta = novaConta.getNumeroDaConta();

        assertEquals(numeroDaConta + 1, (new ContaCorrente(joao, minhaAgencia).getNumeroDaConta()));
        assertEquals(numeroDaConta + 2, (new ContaCorrente(joao, minhaAgencia).getNumeroDaConta()));
    }

    @Test
    public void testeParaSituacaoNormalDeDeposito() throws ValorDeDepositoInvalidoException {
        checarSaldoInicial(contaDaMaria);

        contaDaMaria.depositar(1000);
        assertFloatEquals(1010f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(500);
        assertFloatEquals(1510f, contaDaMaria.getSaldoEmReais());

        contaDaMaria.depositar(100);
        assertFloatEquals(1610f, contaDaMaria.getSaldoEmReais());
   }

    @Test
    public void testeParaSituacaoDeValorInvalidoDeDepositoException(){
        rodarTesteParaSituacaoDeValorInvalidoDeDepositoException(0);
        rodarTesteParaSituacaoDeValorInvalidoDeDepositoException(-1);
        rodarTesteParaSituacaoDeValorInvalidoDeDepositoException(-100);
        rodarTesteParaSituacaoDeValorInvalidoDeDepositoException(-80);
    }

   private void rodarTesteParaSituacaoDeValorInvalidoDeDepositoException(float valorDeDeposito){
        try{
            checarSaldoInicial(contaDaMaria);
            contaDaMaria.depositar(valorDeDeposito);
            fail();
        }catch(ValorDeDepositoInvalidoException e){
            //ok
        }
   }

    @Test
    public void testeParaSituacaoNormalDeSaque() throws SaldoInsuficienteParaSaqueException, ValorDeSaqueInvalidoException {
        checarSaldoInicial(contaDaMaria);
        contaDaMaria.sacar(7);
        assertEquals(3f, contaDaMaria.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testeParaSituacaoDeSaldoInsuficienteParaSaqueException() throws ValorDeSaqueInvalidoException {
        checarSaldoInicial(contaDaMaria);

        try{
            contaDaMaria.sacar(contaDaMaria.getSaldoEmReais() + ContaCorrente.LIMITE_CHEQUE_ESPECIAL + 1);
            assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
            fail("Uma SaldoInsuficienteParaSaqueException deve ser lançada quando " +
                    "o valor de saque exceder saldo da conta + LIMITE_CHEQUE_ESPECIAL");
        }catch(SaldoInsuficienteParaSaqueException e){
            //ok
        }

    }

    @Test
    public void testeParaSituacaoDeValorDeSaqueInvalidoException() throws SaldoInsuficienteParaSaqueException {
        rodarTesteParaSituacaoDeValorDeSaqueInvalidoException(0);
        rodarTesteParaSituacaoDeValorDeSaqueInvalidoException(-10);
    }

    private void rodarTesteParaSituacaoDeValorDeSaqueInvalidoException(float valorDeSaque) throws SaldoInsuficienteParaSaqueException{
        checarSaldoInicial(contaDaMaria);

        try{
            contaDaMaria.sacar(valorDeSaque);
        }catch(ValorDeSaqueInvalidoException e){
            //ok
        }

    }

    @Test
    public void testeParaSituacaoNormalDeTransferencia() throws SaldoInsuficienteParaTransferenciaException, ValorDeTransferenciaInvalidoException {

        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        contaDaMaria.transferir(7, contaDoJoao);

        assertFloatEquals(3f, contaDaMaria.getSaldoEmReais());
        assertFloatEquals(17f, contaDoJoao.getSaldoEmReais());
    }

    @Test
    public void testeParaSituacaoDeSaldoInsuficienteParaTransferenciaException() throws ValorDeTransferenciaInvalidoException{

        try {
            // sanity check: as contas já começam com saldo 10 (regra de negócio)
            assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
            assertFloatEquals(10f, contaDoJoao.getSaldoEmReais());

            // a transferência NÃO DEVE SER REALIZADA, porque não há fundos na conta de origem (Maria).
            contaDaMaria.transferir(contaDaMaria.getSaldoEmReais() + ContaCorrente.LIMITE_CHEQUE_ESPECIAL + 100, contaDoJoao);

            assertFloatEquals(10f, contaDaMaria.getSaldoEmReais());
            assertFloatEquals(10f, contaDoJoao.getSaldoEmReais());
            fail();
        }catch(SaldoInsuficienteParaTransferenciaException e){
            //ok
        }
    }

    @Test
    public void testeParaSituacaoDeValorDeTransferenciaInvalidoException() throws SaldoInsuficienteParaTransferenciaException {
        rodarTesteParaSituacaoDeValorDeTransferenciaInvalidoException(0);
        rodarTesteParaSituacaoDeValorDeTransferenciaInvalidoException(-10);
    }

    private void rodarTesteParaSituacaoDeValorDeTransferenciaInvalidoException(float valorDeTransferencia) throws SaldoInsuficienteParaTransferenciaException{
        checarSaldoInicial(contaDaMaria);
        checarSaldoInicial(contaDoJoao);

        try{
            contaDaMaria.transferir(valorDeTransferencia, contaDoJoao);
        }catch(ValorDeTransferenciaInvalidoException e){
            //ok
        }
    }

    private void checarSaldoInicial(ContaCorrente conta) {
        // sanity check: as contas já começam com saldo 10 (regra de negócio)
        assertFloatEquals(
                ContaCorrente.SALDO_INICIAL_DE_NOVAS_CONTAS,
                conta.getSaldoEmReais());
    }

    private static void assertFloatEquals(float expected, float actual) {
        assertEquals(expected, actual, ERRO_ACEITAVEL_NOS_FLOATS);
    }


}