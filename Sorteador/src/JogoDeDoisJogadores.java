import java.util.ArrayList;

public abstract class JogoDeDoisJogadores {

    String nomeDoJogo;
    String nomeJogador1;
    String nomeJogador2;
    int numeroDeRodadas;
    ArrayList<String> historicoDeResultados;
    int resultado = 0;
    int rodadasVencidasPeloJogador1 = 0;
    int rodadasVencidasPeloJogador2 = 0;

    protected abstract int executarRodadaDoJogo();

    public JogoDeDoisJogadores(String nomeDoJogo, String nomeJogador1, String nomeJogador2, int numeroDeRodadas){
        historicoDeResultados = new ArrayList<String>();
        this.nomeDoJogo = nomeDoJogo;
        this.nomeJogador1 = nomeJogador1;
        this.nomeJogador2 = nomeJogador2;
        this.numeroDeRodadas = numeroDeRodadas;
    }

    public String getNomeJogo(){
        return this.nomeDoJogo;
    }

    public String getNomeJogador1(){
        return this.nomeJogador1;
    }

    public String getNomeJogador2(){
        return this.nomeJogador2;
    }

    public int getNumeroDeRodadas(){
        return this.numeroDeRodadas;
    }

    public String jogar(){
        this.rodadasVencidasPeloJogador1 = 0;
        this.rodadasVencidasPeloJogador2 = 0;
        int i = 0;
        while(i < this.numeroDeRodadas){
            i++;
            resultado = executarRodadaDoJogo();
            if(resultado == 1){
                rodadasVencidasPeloJogador1++;
            }
            if(resultado == 2){
                rodadasVencidasPeloJogador2++;
            }
        }
        this.historicoDeResultados.add(obterString());
        return obterString();
    }

    public String obterString() {
        if(this.rodadasVencidasPeloJogador1 > this.rodadasVencidasPeloJogador2){
            return String.format("O jogador %s venceu o jogo por %d a %d.", this.nomeJogador1, this.rodadasVencidasPeloJogador1, this.rodadasVencidasPeloJogador2);
        }
        if(this.rodadasVencidasPeloJogador2 > this.rodadasVencidasPeloJogador1){
            return String.format("O jogador %s venceu o jogo por %d a %d.", this.nomeJogador2, this.rodadasVencidasPeloJogador2, this.rodadasVencidasPeloJogador1);
        }
        return String.format("O jogo terminou em empate após %d rodadas.", this.numeroDeRodadas);
    }

    public void setNumeroDeRodadas(int numeroDeRodadas) {
        this.numeroDeRodadas = numeroDeRodadas;
    }

    public ArrayList<String> getHistoricoResultados() {
        return this.historicoDeResultados;
    }
}
