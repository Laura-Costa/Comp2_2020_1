import java.util.ArrayList;

public class Album {

    private final int tamanhoDoAlbum;

    private final int quantFigurinhasPorPacotinho;

    public static final int PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR = 90;  // 90%

    private int quantPacotinhosComprados;

    private Figurinha[] album;

    private ArrayList<Figurinha> repetidas;

    public Album(int tamanhoDoAlbum, int quantFigurinhasPorPacotinho) {
        this.tamanhoDoAlbum = tamanhoDoAlbum;
        this.quantFigurinhasPorPacotinho = quantFigurinhasPorPacotinho;
        album = new Figurinha[tamanhoDoAlbum + 1];
        repetidas = new ArrayList<>();
    }

    public void receberNovoPacotinho(Pacotinho pacotinho) {
        this.quantPacotinhosComprados++;
        for (Figurinha fig : pacotinho) {
            if (album[fig.getPosicao()] == null){
                album[fig.getPosicao()] = fig;
                continue;
            }
            else{
                this.repetidas.add(fig);
            }
        }
    }

    public void autoCompletar() {
        // verifica se o álbum já está suficientemente cheio
        // o álbum só pode ser auto completado se ele estiver com mais de 90% da sua capacidade de figurinhas coladas
        if(!((this.getQuantFigurinhasColadas()) > (int)(PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR/100.0*this.tamanhoDoAlbum))){
            return;
        }
        int[] arrayDeComPosicoesFaltantes = new int[tamanhoDoAlbum];
        int quantDeFigurasFaltantes = 0;
        for(int posicaoNoAlbum = 1; posicaoNoAlbum <= tamanhoDoAlbum; posicaoNoAlbum++){
            if(this.album[posicaoNoAlbum] == null){
                arrayDeComPosicoesFaltantes[quantDeFigurasFaltantes++] = posicaoNoAlbum;
            }
        }

        if(quantDeFigurasFaltantes == 0){
            return;
        }

        int[] posicoes = new int[quantDeFigurasFaltantes]; // um array com a dimensão igual ao número de figurinhas faltantes
        for(int i = 0; i < posicoes.length; i++){ // posicoes.length == j
            posicoes[i] = arrayDeComPosicoesFaltantes[i];
        }

        int[] auxiliar = new int[this.quantFigurinhasPorPacotinho];

        // chamando o construtor de Pacotinho, percorrendo o array figurinhasFaltantes
        // e transferindo o número das figurinhas para o array auxiliar

        int j = 0;
        while(true){
            for(int i = 0; i < this.quantFigurinhasPorPacotinho; i++){
                auxiliar[i] = posicoes[j++];
                if(j == posicoes.length){
                    break;
                }
            }
            Pacotinho pacotinho = new Pacotinho(this, auxiliar);
            this.receberNovoPacotinho(pacotinho);
            if(j == posicoes.length){
                break;
            }
        }

    }



    public int getQuantFigurinhasColadas() {
        int quantFigurinhasColadas = 0;
        for(int i = 1; i <= tamanhoDoAlbum; i++){
            if(album[i] != null){
                quantFigurinhasColadas++;
            }
        }
        return quantFigurinhasColadas;
    }

    public int getQuantFigurinhasRepetidas() {
        return repetidas.size();
    }

    public boolean possuiFigurinhaColada(int posicao) {
        if(posicao >= album.length || posicao <= 0){
            return false;
        }
        return album[posicao] != null;
    }

    public boolean possuiFigurinhaRepetida(int posicao) {
        for(Figurinha figurinha: repetidas){
            if(figurinha.getPosicao() == posicao){
                return true;
            }
        }
        return false;
    }

    public int getQuantFigurinhasFaltantes() {
        int quantFigurinhasFaltantes = 0;
        for (int i = 1; i <= tamanhoDoAlbum; i++){
            if (album[i] == null) {
                quantFigurinhasFaltantes++;
            }
        }
        return quantFigurinhasFaltantes;
    }

    public int getTamanhoDoAlbum() {
        return tamanhoDoAlbum;
    }

    public int getQuantFigurinhasPorPacotinho() {
        return this.quantFigurinhasPorPacotinho;
    }

    public int getQuantPacotinhosComprados() {
        return quantPacotinhosComprados;
    }
}
