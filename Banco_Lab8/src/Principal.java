import excecoes.*;

import java.util.Scanner;

public class Principal {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Banco meuBanco = new Banco();

        boolean terminar = false;

        while (!terminar) {

            apresentarMenu();
            String opcao = lerOpcao();

            switch (opcao.toUpperCase()) {
                case "D":
                    long numeroDaConta;
                    float valor;
                    ContaCorrente contaCorrente;

                    try{
                        System.out.print("Número da conta: ");
                        numeroDaConta = Long.parseLong(scanner.nextLine());
                        System.out.print("Valor desejado: ");
                        valor = Float.parseFloat(scanner.nextLine());

                        contaCorrente = meuBanco.localizarConta(numeroDaConta);

                        contaCorrente.depositar(valor);

                        System.out.println(contaCorrente.getUltimoItemHistorico());

                    }catch(ContaCorrenteInformadaInexistenteException e){
                        System.out.println("O número informado não corresponde a uma conta. Tente novamente.");
                    }catch(ValorDeDepositoInvalidoException e){
                        System.out.println("O valor de depósito informado é inválido.");
                    }
                    break;

                case "S":

                    try{
                        System.out.print("Número da conta: ");
                        numeroDaConta = Long.parseLong(scanner.nextLine());

                        System.out.print("Valor desejado: ");
                        valor = Float.parseFloat(scanner.nextLine());

                        contaCorrente = meuBanco.localizarConta(numeroDaConta);
                        contaCorrente.sacar(valor);

                        System.out.println(contaCorrente.getUltimoItemHistorico());

                    }catch(ContaCorrenteInformadaInexistenteException e){
                        System.out.println("O número informado não corresponde a uma conta. Tente novamente.");
                    }catch(ValorDeSaqueInvalidoException e){
                        System.out.println("Valor de saque informado é inválido.");
                    }catch(SaldoInsuficienteParaSaqueException e){
                        System.out.println("Saldo da conta é  insuficiente para saque.");
                    }
                    break;

                case "T":
                    try{
                        ContaCorrente contaCorrenteDestino;

                        System.out.print("Número da contade origem: ");
                        long numeroDaContaDeOrigem = Long.parseLong(scanner.nextLine());
                        contaCorrente = meuBanco.localizarConta(numeroDaContaDeOrigem);

                        System.out.print("Número da contade destino: ");
                        long numeroDaContaDeDestino = Long.parseLong(scanner.nextLine());
                        contaCorrenteDestino = meuBanco.localizarConta(numeroDaContaDeDestino);

                        System.out.print("Valor desejado: ");
                        valor = Float.parseFloat(scanner.nextLine());

                        contaCorrente.transferir(valor, contaCorrenteDestino);

                    }catch(ContaCorrenteInformadaInexistenteException e){
                        System.out.println("A conta de origem não existe. Tente novamente.");
                    }catch(ValorDeTransferenciaInvalidoException e){
                        System.out.println("O valor de transferência informado é inválido. Tente novamente.");
                    }catch(SaldoInsuficienteParaTransferenciaException e){
                        System.out.println("O saldo da conta de destino é insuficiente para tranferência. Tente novamente.");
                    }

                    break;  // switch

                case "C":
                    try{
                        System.out.print("Número da conta: ");
                        long numero = Long.parseLong(scanner.nextLine());

                        ContaCorrente conta = meuBanco.localizarConta(numero);

                        System.out.println(String.format("Saldo = %.2f", conta.getSaldoEmReais()));

                    }catch(ContaCorrenteInformadaInexistenteException e){
                        System.out.println("O número informado não corresponde a uma conta. Tente novamente.");
                    }
                    break;

                case "P":
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("CPF: ");
                    long cpf = Long.parseLong(scanner.nextLine());

                    meuBanco.cadastrarCorrentista(nome, cpf);
                    System.out.println("Cadastro realizado!");

                    break;  // switch

                case "N":
                    System.out.print("CPF do correntista: ");
                    cpf = Long.parseLong(scanner.nextLine());

                    Pessoa correntista = null;
                    try{
                        correntista = meuBanco.localizarCorrentista(cpf);
                        ContaCorrente novaConta = meuBanco.cadastrarConta(correntista);
                        System.out.println("Conta criada com o número " + novaConta.getNumeroDaConta());
                    }catch(PessoaInformadaNaoCadastradaComoCorrentistaException e){
                        System.out.println("Correntista não existe!");
                    }
                    break;  // switch

                case "X":
                    terminar = true;
                    break;

                default:
                    System.out.println("Opção inválida");
            }
        }

        System.out.println("Tchau!");
    }

    private static void apresentarMenu() {
        System.out.println(
                "\n------\n" +
                "(D)epositar\n" +
                "(S)acar\n" +
                "(T)ransferir\n" +
                "(C)onsultar saldo\n" +
                "Cadastrar (P)essoa como correntista\n" +
                "Criar (N)ova conta\n" +
                "(X) para sair\n");
    }

    private static String lerOpcao() {
        System.out.print("\nAção desejada: ");
        return scanner.nextLine();
    }

}
