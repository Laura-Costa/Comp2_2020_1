import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContaCorrenteTest {

    private final float ERRO_ACEITAVEL_NOS_FLOATS = 0.000001f;

    @Test
    public void testarDeposito(){

        Pessoa primeiraPessoa = new Pessoa("primeiraPessoa", 1111);
        Agencia primeiraAgencia = new Agencia();
        ContaCorrente conta = new ContaCorrente(1111, primeiraPessoa, primeiraAgencia);

        //sanity check
        assertEquals(10, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.depositar(1000);
        assertEquals(1010f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.depositar(500);
        assertEquals(1510f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.depositar(-100);
        assertEquals(1510f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarSaqueNegativo(){

        Pessoa segundaPessoa = new Pessoa("segundaPessoa", 2222);
        Agencia segundaAgencia = new Agencia();
        ContaCorrente conta = new ContaCorrente(9999, segundaPessoa, segundaAgencia);

        //sanity check
        assertEquals(10f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.sacar(-100);
        assertEquals(10f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.sacar(-5);
        assertEquals(10f, conta.getSaldoEmReais(),ERRO_ACEITAVEL_NOS_FLOATS);

    }

    @Test
    public void testarSaquePositivo(){

        Pessoa terceiraPessoa = new Pessoa("terceiraPessoa", 3333);
        Agencia terceiraAgencia = new Agencia();
        ContaCorrente conta = new ContaCorrente(8888, terceiraPessoa, terceiraAgencia);

        //sanity check
        assertEquals(10f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.sacar(2);
        assertEquals(8f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.sacar(5);
        assertEquals(3f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        conta.sacar(3);
        assertEquals(0f, conta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

    }

    @Test
    public void testarTransferenciaSemFundosNaContaDeOrigem(){

        Pessoa quartaPessoa = new Pessoa("quartaPessoa", 4444);
        Pessoa quintaPessoa = new Pessoa("quintaPessoa", 5555);
        Agencia quartaAgencia = new Agencia();

        ContaCorrente quartaConta = new ContaCorrente(7777, quartaPessoa, quartaAgencia);
        ContaCorrente quintaConta = new ContaCorrente(6666, quintaPessoa, quartaAgencia);

        //sanity check
        assertEquals(10f, quartaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f,quintaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        quartaConta.transferir(22, quintaConta);

        assertEquals(10f, quartaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, quintaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

    }

    @Test
    public void testarTransferenciaComFundosNaContaDeOrigem(){

        Pessoa sextaPessoa = new Pessoa("quartaPessoa", 6666);
        Pessoa setimaPessoa = new Pessoa("quintaPessoa", 7777);
        Agencia quintaAgencia = new Agencia();

        ContaCorrente sextaConta = new ContaCorrente(5555, sextaPessoa, quintaAgencia);
        ContaCorrente setimaConta = new ContaCorrente(4444, setimaPessoa, quintaAgencia);

        //sanity check
        assertEquals(10f, sextaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f,setimaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        sextaConta.transferir(7, setimaConta);

        assertEquals(3f, sextaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(17f, setimaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        sextaConta.transferir(2, setimaConta);

        assertEquals(1f, sextaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(19f, setimaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

    @Test
    public void testarTransferenciaNegativa(){

        Pessoa oitavaPessoa = new Pessoa("oitavaPessoa", 8888);
        Pessoa nonaPessoa = new Pessoa("nonaPessoa", 9999);
        Agencia sextaAgencia = new Agencia();

        ContaCorrente oitavaConta = new ContaCorrente(3333, oitavaPessoa, sextaAgencia);
        ContaCorrente nonaConta = new ContaCorrente(2222, nonaPessoa, sextaAgencia);

        //sanity check
        assertEquals(10f, oitavaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f,nonaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        oitavaConta.transferir(-100, nonaConta);

        assertEquals(10f, oitavaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, nonaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);

        oitavaConta.transferir(-2, oitavaConta);

        assertEquals(10f, oitavaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
        assertEquals(10f, nonaConta.getSaldoEmReais(), ERRO_ACEITAVEL_NOS_FLOATS);
    }

}
