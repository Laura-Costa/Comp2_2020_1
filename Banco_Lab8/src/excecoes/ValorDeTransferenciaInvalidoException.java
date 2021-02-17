package excecoes;

public class ValorDeTransferenciaInvalidoException extends Exception{

    private float valorInvalido;

    public ValorDeTransferenciaInvalidoException(float valorInvalido){
        this.valorInvalido = valorInvalido;
    }

    public float getValorInvalido() {
        return valorInvalido;
    }
}
