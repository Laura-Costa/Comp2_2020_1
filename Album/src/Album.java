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
                this.colarFigurinha(fig);
            }
            else{
                this.repetidas.add(fig);
            }
        }
    }

    private void colarFigurinha(Figurinha figurinha){
        album[figurinha.getPosicao()] = figurinha;
    }

    public void autoCompletar() {
        // verifica se o álbum já está suficientemente cheio
        // o álbum só pode ser auto completado se ele estiver com mais de 90% da sua capacidade de figurinhas coladas

        if(!((this.getQuantFigurinhasColadas()) > (int)(PERCENTUAL_MINIMO_PARA_AUTO_COMPLETAR/100.0*this.tamanhoDoAlbum))){
            return;
        }
        for(int posicao = 0; posicao < this.tamanhoDoAlbum; posicao ++){
            if(album[posicao] == null){
                Figurinha figurinha = new Figurinha(posicao);
                colarFigurinha(figurinha);
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
        return this.tamanhoDoAlbum - this.getQuantFigurinhasColadas();
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
