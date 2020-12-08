import java.util.Scanner;
import java.util.Locale;

public class Principal {

    public static void main(String[] args){

        Scanner scanner = new Scanner(System.in);
        long dreComMaiorMedia = 0;
        float somaDasMedias = 0;
        float maiorMedia = 0;
        int quantidadeDeAlunos = 0;

        System.out.println("Digite o DRE e a média do aluno");
        long dre = scanner.nextLong();
        float media = scanner.useLocale(Locale.ENGLISH).nextFloat();

        while(media > 0){

            quantidadeDeAlunos += 1;
            somaDasMedias += media;

            if(media > maiorMedia){
                maiorMedia = media;
                dreComMaiorMedia = dre;
            }

            System.out.println("Digite o DRE e a média do aluno:");
            dre = scanner.nextLong();
            media = scanner.useLocale(Locale.ENGLISH).nextFloat();

        }

        if (quantidadeDeAlunos != 0){
            System.out.printf("%d notas digitadas\n", quantidadeDeAlunos);
            System.out.printf("Média da turma: %.1f\n", calcularMediaTurma(quantidadeDeAlunos, somaDasMedias));
            System.out.printf("DRE com maior média: %d\n", dreComMaiorMedia);
        }

    }

    /***
     * retorna a média da turma
     * @param quantidadeDeAlunos a quantidade de alunos
     * @param somaDasMedias a soma das médias dos alunos
     * @return
     */
    public static float calcularMediaTurma(int quantidadeDeAlunos, float somaDasMedias){
        return somaDasMedias/quantidadeDeAlunos;
    }

}