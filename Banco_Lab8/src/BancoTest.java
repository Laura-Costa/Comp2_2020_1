import excecoes.PessoaInformadaNaoCadastradaComoCorrentistaException;
import excecoes.SaldoInsuficienteParaSaqueException;
import excecoes.ValorDeDepositoInvalidoException;
import excecoes.ValorDeSaqueInvalidoException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BancoTest {

    private Banco banco;
    final int TAMANHO = 20_000;

    @Before
    public void setUp() {
        banco = new Banco();
    }

    @Test
    public void testeParaSituacaoNormalDeCadastroDeUsuarioComSaldoNegativo() throws PessoaInformadaNaoCadastradaComoCorrentistaException, ValorDeDepositoInvalidoException, SaldoInsuficienteParaSaqueException, ValorDeSaqueInvalidoException {

        Pessoa maria = banco.cadastrarCorrentista("Mariah", 123456L);

        ContaCorrente contaDaMaria = banco.cadastrarConta(maria);
        contaDaMaria.depositar(1000);
        contaDaMaria.sacar(1100);

        // no futuro... num outro ponto do código...
        Pessoa outroObjetoRepresentandoAMesmaMaria = new Pessoa("Maria", 123456L);

        assertTrue(banco.getCorrentistasComSaldoNegativo().contains(outroObjetoRepresentandoAMesmaMaria));
    }

    @Test
    public void testeParaSituacaoNormalDePerformance() throws PessoaInformadaNaoCadastradaComoCorrentistaException {

        for (int i = 1; i <= TAMANHO; i++) {
            banco.cadastrarCorrentista("Correntista Numero " + i, i);
        }

        for (int i = 1; i <= TAMANHO; i++) {
            assertNotNull(banco.localizarCorrentista(i));
        }

    }

    @Test
    public void testeParaSituacaoDePessoaNaoCadastradaComoCorrentistaException(){
         try{
             for (int i = TAMANHO + 1; i <= 2 * TAMANHO; i++) {
                 assertNull(banco.localizarCorrentista(i));
             }
             fail();
         }catch(PessoaInformadaNaoCadastradaComoCorrentistaException e){
             //ok
         }
    }
}