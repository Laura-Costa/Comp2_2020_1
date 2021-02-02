import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Principal {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);

        ArrayList<ContaCorrente> vetorDeContas = new ArrayList<>();
        ArrayList<Pessoa> vetorDeCorrentistas = new ArrayList<>();

        Agencia agencia = new Agencia();
        int numeroDaConta;
        int numeroDaContaDeDestino;
        ContaCorrente conta;
        ContaCorrente contaDeDestino;
        long cpf;
        Pessoa correntista;
        String nome;

        //ContaCorrente[] vetorDeContas = new ContaCorrente[1];

        //Pessoa[] vetorDeCorrentistas = new Pessoa[5];
        //int cont = 0;

        while(true){
            System.out.println("");
            System.out.println("--------------------------------");
            System.out.println("[1] Depositar");
            System.out.println("[2] Sacar");
            System.out.println("[3] Transferir");
            System.out.println("[4] Consultar saldo");
            System.out.println("[5] Cadastrar pessoa como correntista");
            System.out.println("[6] Criar nova conta");
            System.out.println("[0] Sair");
            System.out.println("--------------------------------");
            System.out.print("\nPor favor, escolha uma das opções acima: ");

            // String enter = scanner.nextLine();
            String entradaPeloConsole = scanner.nextLine();

            if(!soNumero(entradaPeloConsole)){
                System.out.println("Entrada invalida.");
                continue;
            }
            int opcao = Integer.parseInt(entradaPeloConsole);

            switch (opcao){
                case 1:
                    System.out.print("Digite o número da conta: ");
                    numeroDaConta = scanner.nextInt();
                    scanner.nextLine();
                    if(numeroDaConta > vetorDeContas.size() || vetorDeContas.size() == 0){
                        System.out.println("Número de conta inválido. Por favor, tente novamente.");
                        continue;
                    }

                    System.out.print("Digite o valor a ser depositado: R$ ");
                    float valor = scanner.useLocale(Locale.ENGLISH).nextFloat();
                    scanner.nextLine();

                    conta = vetorDeContas.get(numeroDaConta-1);
                    conta.depositar(valor);
                    ArrayList<String> array = conta.getHistoricoDeOperacoes();
                    System.out.println(array.get(array.size() - 1));
                    break;
                case 2:
                    System.out.print("Digite o número da conta: ");
                    numeroDaConta = scanner.nextInt();
                    scanner.nextLine();
                    if(numeroDaConta > vetorDeContas.size() || vetorDeContas.size() == 0){
                        System.out.println("Número de conta inválido. Por favor, tente novamente.");
                        continue;
                    }

                    System.out.print("Digite o valor que deseja sacar: R$ ");
                    float valorDeSaque = scanner.useLocale(Locale.ENGLISH).nextFloat();
                    scanner.nextLine();

                    conta = vetorDeContas.get(numeroDaConta-1);

                    if(valorDeSaque <= conta.getSaldoEmReais() && valorDeSaque > 0){
                        conta.sacar(valorDeSaque);
                        ArrayList<String> arraySaque = conta.getHistoricoDeOperacoes();
                        System.out.println(arraySaque.get(arraySaque.size() - 1));
                        continue;
                    }
                    System.out.println("O saque não foi efetuado (valor de saque maior do que os fundos da conta).");
                    System.out.println("Para consultar seu saldo pressione 4.");
                    break;
                case 3:
                    System.out.print("Digite o número da conta de origem: ");
                    numeroDaConta = scanner.nextInt();
                    if(numeroDaConta > vetorDeContas.size() || vetorDeContas.size() == 0){
                        System.out.println("Número de conta inválido. Por favor, tente novamente.");
                        continue;
                    }
                    System.out.print("Digite o número da conta de destino: ");

                    numeroDaContaDeDestino = scanner.nextInt();
                    if(numeroDaContaDeDestino > vetorDeContas.size() || vetorDeContas.size() == 0){
                        System.out.println("Número de conta inválido. Por favor, tente novamente.");
                        continue;
                    }

                    System.out.print("Digite o valor que você quer transferir: R$ ");
                    float valorDeTransferencia = scanner.useLocale(Locale.ENGLISH).nextFloat();
                    scanner.nextLine();

                    conta = vetorDeContas.get(numeroDaConta - 1);
                    contaDeDestino = vetorDeContas.get(numeroDaContaDeDestino - 1);


                    if((valorDeTransferencia <= conta.getSaldoEmReais()) & ( valorDeTransferencia > 0)){
                        conta.transferir(valorDeTransferencia, contaDeDestino);
                        ArrayList<String> arrayTransferencia = conta.getHistoricoDeOperacoes();
                        System.out.println(arrayTransferencia.get(arrayTransferencia.size() - 1));
                        break;
                    }

                    System.out.println("A transferência não foi efetuada (valor de transferência maior do que os fundos na conta de origem).");
                    System.out.println("Por favor tente novamente.");
                    break;
                case 4:
                    System.out.print("Digite o número da conta: ");
                    numeroDaConta = scanner.nextInt();
                    scanner.nextLine();
                    if(numeroDaConta > vetorDeContas.size() || vetorDeContas.size() == 0){
                        System.out.println("Número de conta inválido. Por favor, tente novamente.");
                        continue;
                    }
                    conta = vetorDeContas.get(numeroDaConta - 1);
                    System.out.printf("%s, seu saldo é R$%.2f.\n", conta.getCorrentista().getNome(),conta.getSaldoEmReais());
                    break;
                case 5:
                    System.out.print("Digite o nome do correntista: ");
                    nome = scanner.nextLine();

                    System.out.print("Digite o CPF do correntista: ");
                    entradaPeloConsole = scanner.nextLine();
                    while(!soNumero(entradaPeloConsole) || entradaPeloConsole.length() != 11){
                        System.out.println("CPF inválido. Por favor, digite uma sequência de 11 dígitos.");
                        entradaPeloConsole = scanner.nextLine();
                    }

                    cpf = Long.parseLong(entradaPeloConsole);

                    correntista = new Pessoa(nome, cpf);

                    vetorDeCorrentistas.add(correntista);
                    System.out.printf("Correntista %s (CPF %d) cadastrado(a) com sucesso.\n", nome, cpf);
                    break;
                case 6:
                    System.out.print("Digite o CPF do correntista: ");
                    entradaPeloConsole = scanner.nextLine();
                    while(!soNumero(entradaPeloConsole) || entradaPeloConsole.length() != 11){
                        System.out.println("CPF inválido. Por favor, digite uma sequência de 11 dígitos.");
                        entradaPeloConsole = scanner.nextLine();
                    }

                    cpf = Long.parseLong(entradaPeloConsole);

                    boolean encontrado = false;

                    for(int i = 0; i < vetorDeCorrentistas.size(); i++){
                        if(vetorDeCorrentistas.get(i).getCpf() == cpf){
                            nome =  vetorDeCorrentistas.get(i).getNome();
                            cpf = vetorDeCorrentistas.get(i).getCpf();
                            correntista = new Pessoa(nome, cpf);
                            vetorDeCorrentistas.add(correntista);
                            conta = new ContaCorrente(correntista, agencia);
                            vetorDeContas.add(conta);
                            System.out.printf("Conta de %s criada com sucesso. Número da conta: %d.\n",conta.getCorrentista().getNome(), conta.getNumeroDaConta());
                            encontrado = true;
                            break;
                        }
                    }
                    if(!encontrado){
                        System.out.println("O cpf informado não corresponde a correntista cadastrado no sistema.");
                        System.out.println("Para cadastrar uma pessoa como correntista, pressione 5.");
                        continue;
                    }
                    break;
                case 0:
                    System.out.println("Encerrando o programa...");
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }

        }

    }

    public static boolean soNumero(String entradaPeloConsole) {
        char[] chars = entradaPeloConsole.toCharArray();
        if(entradaPeloConsole.trim().equals("")){
            return false;
        }
        for (char c : chars) {
            if(!Character.isDigit(c)) {
                return false;
            }
        }
        return true;
    }
}