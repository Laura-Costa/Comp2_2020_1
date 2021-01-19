public class DadosDeGamao implements Sorteador {

    Dado dado = new Dado();

    public int sortear(){
        int dado1 = dado.sortear();
        int dado2 = dado.sortear();
        if(dado1 == dado2){
            return 2*(dado1 + dado2);
        }
        return dado1 + dado2;
    }

}
