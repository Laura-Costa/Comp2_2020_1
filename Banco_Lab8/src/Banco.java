import excecoes.ContaCorrenteInformadaInexistenteException;
import excecoes.PessoaInformadaNaoCadastradaComoCorrentistaException;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Banco {

    Map<Long, ContaCorrente> contaPorNumero;
    Map<Long, Pessoa> correntistaPorCpf;

    Set<Pessoa> correntistasComSaldoNegativo;

    private Agencia agenciaMatriz;

    public Banco() {
        contaPorNumero = new HashMap<>();
        correntistaPorCpf = new HashMap<>();
        correntistasComSaldoNegativo = new HashSet<>();
        agenciaMatriz = new Agencia(this, 1, "Agência Um");
    }

    public Pessoa cadastrarCorrentista(String nome, long cpf) {
        Pessoa p = null;
        try{
            p = localizarCorrentista(cpf);
            // Localizei o correntista! Não é preciso recriá-lo! Podemos atualizar o nome, se for o caso.
            p.setNome(nome);
        }catch(PessoaInformadaNaoCadastradaComoCorrentistaException e){
            // Correntista novo
            p = new Pessoa(nome, cpf);
            correntistaPorCpf.put(cpf, p);
        }
        return p;

    }

    public ContaCorrente cadastrarConta(Pessoa correntista) throws PessoaInformadaNaoCadastradaComoCorrentistaException {
        // verifica correntista
        // Lança PessoaNaoCadastradaException quando o cpf informado não
        // corresponde a um Correntista cadastrado.
        localizarCorrentista(correntista.getCpf());

        // aceitamos mais de uma conta para o mesmo correntista

        ContaCorrente novaConta = new ContaCorrente(correntista, this.agenciaMatriz);
        this.contaPorNumero.put(novaConta.getNumeroDaConta(), novaConta);

        return novaConta;
    }

    public Pessoa localizarCorrentista(long cpf) throws PessoaInformadaNaoCadastradaComoCorrentistaException {
        Pessoa correntista = this.correntistaPorCpf.get(cpf);
        if(correntista == null){
            // Lanço exceção quando o correntista não está cadastrado.
            throw new PessoaInformadaNaoCadastradaComoCorrentistaException();
        }
        return correntista;
    }

    public ContaCorrente localizarConta(long numeroDaConta) throws ContaCorrenteInformadaInexistenteException {
        ContaCorrente conta =  this.contaPorNumero.get(numeroDaConta);
        if(conta == null){
            throw new ContaCorrenteInformadaInexistenteException();
        }
        return conta;
    }

    void registrarCorrentistaComSaldoNegativo(Pessoa correntista) {
        this.correntistasComSaldoNegativo.add(correntista);
    }

    Set<Pessoa> getCorrentistasComSaldoNegativo() {
        return this.correntistasComSaldoNegativo;
    }
}
