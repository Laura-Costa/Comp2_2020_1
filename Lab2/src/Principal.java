public class Principal {

    public static void main(String[] args) {

        Agencia agencia = new Agencia();
        Pessoa maria = new Pessoa("maria", 568923);
        maria.setEndereco("Rua R, número 50");
        System.out.println(maria.getEndereco());
        ContaCorrente minhaConta = new ContaCorrente(568923, maria, agencia);

        System.out.println(minhaConta.getSaldoEmReais());

        minhaConta.depositar(10000);
        System.out.println(minhaConta.getSaldoEmReais());
        System.out.println(minhaConta.getHistoricoDeOperacao());

        minhaConta.depositar(1000);
        System.out.println(minhaConta.getSaldoEmReais());
        System.out.println(minhaConta.getHistoricoDeOperacao());

        minhaConta.sacar(11000);
        System.out.println(minhaConta.getSaldoEmReais());
        System.out.println(minhaConta.getHistoricoDeOperacao());

        System.out.println(maria);
    }
}
