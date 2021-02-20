public enum CategoriaDoLivro {

    ENCICLOPEDIA("ENC"),
    DIDATICO("LD"),
    FICCAO("FIC"),
    BIOGRAFIA("BIO"),
    ARTE("AR"),
    DICIONARIO("DIC"),
    NAO_FICCAO("NF");

    private String abreviacao;

    CategoriaDoLivro(String abreviacao){
        this.abreviacao = abreviacao;
    }

    public String getAbreviacao(){
        return this.abreviacao;
    }

}
