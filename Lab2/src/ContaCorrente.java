import java.util.ArrayList;
import java.util.Date;

public class ContaCorrente {

    private final long numeroDaConta;
    private final Agencia agencia;
    private float saldoEmReais;
    private Date dataDeCriacao;
    private Pessoa correntista;
    private Pessoa gerenteDaConta;
    private ArrayList<String> historicoDeOperacao;

    /**
     * método construtor da classe ContaCorrente
     * @param numeroDaConta o número identificador da conta corrente
     * @param correntista é um objeto da classe Pessoa
     * @param agencia é um objeto da classe Agencia
     */
    public ContaCorrente(long numeroDaConta, Pessoa correntista, Agencia agencia){
        this.numeroDaConta = numeroDaConta;
        this.correntista = correntista;
        this.agencia = agencia;
        this.historicoDeOperacao = new ArrayList<>();
        this.dataDeCriacao = new Date();
        this.saldoEmReais = 10;

    }

    /**
     * Método getter
     * @return o saldo da conta corrente
     */
    public float getSaldoEmReais(){
        return saldoEmReais;
    }

    /**
     * Método getter
     * @return o histórico de operações da conta corrente
     */
    public ArrayList<String> getHistoricoDeOperacao(){
        return historicoDeOperacao;
    }

    /**
     * Método que dado um valor float de depósito, verifica se o valor é positivo.
     * Se o valor for positivo, ele é somado ao atributo saldoEmReais.
     * @param valor o valor a ser depositado.
     */
    public void depositar(float valor){
        if(valor <= 0){
            return;
        }
        this.saldoEmReais += valor;
        historicoDeOperacao.add("Deposito em dinheiro: " + valor + " na data " + new Date());
    }

    /**
     * Dado um valor de saque, verifica se há fundo suficiente na conta para efetuar o saque.
     * Se houver, o valor do saque é subtraído do atributo saldoEmReais da conta corrente.
     * @param saque o valor a ser sacado.
     */
    public void sacar(float saque){
        if(saque <= 0){
            return;
        }
        if(saque <= saldoEmReais){
            saldoEmReais -= saque;
            historicoDeOperacao.add("Saque em dinheiro: " + saque + " na data " + new Date());
        }
    }

    /**
     * Dado um valor de transferência e uma conta destino, o método verifica se o valor
     * da transferência é maior do que o fundo da conta de origem ou se é menor ou igual a zero;
     * nesses casos o método retorna sem efetuar a transferencia.
     * Caso contrário, o método transfere o valor para a conta de destino.
     * O valor de transferência é subtraído do atributo saldoEmReais da conta de origem.
     * @param transferencia o valor a ser transferido.
     * @param conta a conta destino, aquela que receberá o valor da transferência.
     */
    public void transferir(float transferencia, ContaCorrente conta){
        if(transferencia > this.getSaldoEmReais() || transferencia <= 0){
            return;
        }
        this.saldoEmReais -= transferencia;
        conta.saldoEmReais += transferencia;
    }

}
