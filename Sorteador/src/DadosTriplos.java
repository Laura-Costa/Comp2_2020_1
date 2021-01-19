public class DadosTriplos implements Sorteador{

    Dado dado = new Dado();

    public int sortear(){

        int soma = 0;
        int i = 0;
        while(i < 3){
            soma += dado.sortear();
            i++;
        }
        return soma;
    }
}
