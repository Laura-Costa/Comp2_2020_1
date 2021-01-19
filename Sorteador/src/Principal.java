import java.util.Random;
public class Principal {

    public static void main(String[] args) {

        DadosDeGamao dado1 = new DadosDeGamao();
        DadosTriplos dado2 = new DadosTriplos();

        JogoMalucoComSorteadores jogo = new JogoMalucoComSorteadores(dado1, dado2, "Jogo", "Maria", "João", 0);

        for(int i = 1; i <= 100; i ++){
            jogo.setNumeroDeRodadas(i);
            System.out.println(jogo.jogar());
        }
        System.out.println(jogo.getHistoricoResultados());
    }
}
