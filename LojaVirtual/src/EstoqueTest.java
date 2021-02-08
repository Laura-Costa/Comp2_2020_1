import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EstoqueTest {

    Loja loja;
    Usuario maria;
    Transportadora transportadora;
    Impressora impressora;

    Livro livro1;
    Livro livro2;

    CD cd1;
    CD cd2;

    Bicicleta bicicleta1;
    Bicicleta bicicleta2;

    @Before
    public void setUp(){

        transportadora = new Transportadora();
        impressora = new ImpressoraMatricial();
        loja = new Loja(transportadora, impressora);

        maria = new Usuario(1234, "maria");
        maria.setEndereco("Rua A, numero 5.");

        livro1 = new Livro(1, "titulo", "autor", "editora", 1);
        livro2 = new Livro(1, "titulo2", "autor", "editora", 1);

        cd1 = new CD(2, "nome", "artista", 1);
        cd2 = new CD(2, "nome2", "artista", 1);

        bicicleta1 = new Bicicleta(3, 700, "marca");
        bicicleta2 = new Bicicleta(3, 700, "marca2");
    }

    @Test
    public void testarIncluirLivroNoEstoque(){

        // sanity check
        assertNull(loja.receberPedido(livro1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(livro1));


        loja.incluirItemNoCatalogo(livro1);
        loja.incluirItemNoEstoque(livro1, 3);

        assertNotNull(loja.receberPedido(livro1, 1, maria));
        assertEquals((Integer)2, loja.quantidadeEmEstoqueDoVendavel(livro1));
    }

    @Test
    public void testarIncluirLivroNoEstoqueComTituloDiferente(){

        // sanity check
        assertNull(loja.receberPedido(livro1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(livro1));


        loja.incluirItemNoCatalogo(livro1);
        loja.incluirItemNoEstoque(livro1, 1);

        assertNotNull(loja.receberPedido(livro1, 1, maria));
        assertEquals((Integer)0, loja.quantidadeEmEstoqueDoVendavel(livro1));

        loja.incluirItemNoCatalogo(livro2);
        loja.incluirItemNoEstoque(livro2, 3);

        assertNotNull(loja.receberPedido(livro1, 2, maria));
        assertEquals((Integer)1, loja.quantidadeEmEstoqueDoVendavel(livro1));

    }

    @Test
    public void testarVendaDeLivro(){

        // sanity check
        assertNull(loja.receberPedido(livro1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(livro1));

        loja.incluirItemNoCatalogo(livro1);
        loja.incluirItemNoEstoque(livro1, 3);

        assertNotNull(loja.receberPedido(livro1, 1, maria));

    }

    @Test
    public void testarIncluirCdNoEstoque(){

        // sanity check
        assertNull(loja.receberPedido(cd1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(cd1));


        loja.incluirItemNoCatalogo(cd1);
        loja.incluirItemNoEstoque(cd1, 3);

        assertNotNull(loja.receberPedido(cd1, 1, maria));
        assertEquals((Integer)2, loja.quantidadeEmEstoqueDoVendavel(cd1));
    }

    @Test
    public void testarIncluirCdNoEstoqueComTituloDiferente(){

        // sanity check
        assertNull(loja.receberPedido(cd1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(cd1));


        loja.incluirItemNoCatalogo(cd1);
        loja.incluirItemNoEstoque(cd1, 1);

        assertNotNull(loja.receberPedido(cd1, 1, maria));
        assertEquals((Integer)0, loja.quantidadeEmEstoqueDoVendavel(cd1));

        loja.incluirItemNoCatalogo(cd2);
        loja.incluirItemNoEstoque(cd2, 3);

        assertNotNull(loja.receberPedido(cd1, 2, maria));
        assertEquals((Integer)1, loja.quantidadeEmEstoqueDoVendavel(cd1));

    }


    @Test
    public void testarVendaDeCd(){

        // sanity check
        assertNull(loja.receberPedido(cd1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(cd1));

        loja.incluirItemNoCatalogo(cd1);
        loja.incluirItemNoEstoque(cd1, 3);

        assertNotNull(loja.receberPedido(cd1, 1, maria));

    }


    @Test
    public void testarIncluirBicicletaNoEstoque(){

        // sanity check
        assertNull(loja.receberPedido(bicicleta1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(bicicleta1));


        loja.incluirItemNoCatalogo(bicicleta1);
        loja.incluirItemNoEstoque(bicicleta1, 3);

        assertNotNull(loja.receberPedido(bicicleta1, 1, maria));
        assertEquals((Integer)2, loja.quantidadeEmEstoqueDoVendavel(bicicleta1));
    }


    @Test
    public void testarIncluiBicicletaNoEstoqueComMarcasDiferente(){

        // sanity check
        assertNull(loja.receberPedido(bicicleta1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(bicicleta1));


        loja.incluirItemNoCatalogo(bicicleta1);
        loja.incluirItemNoEstoque(bicicleta1, 1);

        assertNotNull(loja.receberPedido(bicicleta1, 1, maria));
        assertEquals((Integer)0, loja.quantidadeEmEstoqueDoVendavel(bicicleta1));

        loja.incluirItemNoCatalogo(bicicleta2);
        loja.incluirItemNoEstoque(bicicleta2, 3);

        assertNotNull(loja.receberPedido(bicicleta1, 2, maria));
        assertEquals((Integer)1, loja.quantidadeEmEstoqueDoVendavel(bicicleta1));

    }

    @Test
    public void testarVendaDeBicicleta(){

        // sanity check
        assertNull(loja.receberPedido(bicicleta1, 1, maria));
        assertNull(loja.quantidadeEmEstoqueDoVendavel(bicicleta1));

        loja.incluirItemNoCatalogo(bicicleta1);
        loja.incluirItemNoEstoque(bicicleta1, 3);

        assertNotNull(loja.receberPedido(bicicleta1, 1, maria));

    }

}
