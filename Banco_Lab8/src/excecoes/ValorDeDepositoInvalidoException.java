package excecoes;

public class ValorDeDepositoInvalidoException extends Exception{

    private float valorInvalido;

    public ValorDeDepositoInvalidoException(float valor){
        this.valorInvalido = valor;
    }

    public float getValorInvalido() {
        return valorInvalido;
    }

}
