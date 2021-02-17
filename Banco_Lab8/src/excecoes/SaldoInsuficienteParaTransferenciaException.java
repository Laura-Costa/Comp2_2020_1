package excecoes;

public class SaldoInsuficienteParaTransferenciaException extends Exception{

    private float valorInvalido;

    public SaldoInsuficienteParaTransferenciaException(float valorInvalido){
        this.valorInvalido = valorInvalido;
    }

    public float getValorInvalido() {
        return valorInvalido;
    }
}
