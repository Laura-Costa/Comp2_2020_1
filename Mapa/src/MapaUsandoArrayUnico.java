import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

public class MapaUsandoArrayUnico<C, V> implements Map<C, V> {

    private ArrayList<ParChaveValor<C, V>> minhaListaDePares;

    public MapaUsandoArrayUnico() {
        this.minhaListaDePares = new ArrayList<>();
    }

    @Override
    public int size() {

        return this.minhaListaDePares.size();  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public boolean isEmpty() {

        return this.size() == 0;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public boolean containsKey(Object key) {

        return this.get(key) != null;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public boolean containsValue(Object value) {

        for(ParChaveValor<C, V> par : this.minhaListaDePares){
            if(par.getValor().equals(value)){
                return true;
            }
        }
        return false;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public V put(C chave, V valor) {
        ParChaveValor<C, V> parPreExistente = obterParChaveValor(chave);

        if (parPreExistente == null) {  // chave inédita!!
            this.minhaListaDePares.add(new ParChaveValor<>(chave, valor));
            return null;

        } else {  // chave pré-existente
            V valorAntigo = parPreExistente.getValor();
            parPreExistente.setValor(valor);
            return valorAntigo; // ToDo IMPLEMENT ME!!! (precisa retornar o valor pré-existente!!)
        }

    }

    @Override
    public V remove(Object key) {
        for(int i = 0; i < this.size(); i++){
            ParChaveValor<C, V> par = this.minhaListaDePares.get(i);
            if(par.getChave().equals(key)){
                this.minhaListaDePares.remove(par);
                return par.getValor();
            }
        }
        return null;  // ToDo IMPLEMENT ME!!!
    }

    @Override
    public void putAll(Map<? extends C, ? extends V> m) {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public void clear() {
        this.minhaListaDePares.clear(); // ToDo IMPLEMENT ME!!!
    }

    @Override
    public Set<C> keySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Collection<V> values() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public Set<Entry<C, V>> entrySet() {
        throw new RuntimeException("Operação não suportada!");
    }

    @Override
    public V get(Object chaveDeBusca) {
        ParChaveValor<C, V> par = obterParChaveValor(chaveDeBusca);
        return par == null
                ? null
                : par.getValor();
    }

    private ParChaveValor<C, V> obterParChaveValor(Object chave) {
        for (ParChaveValor<C, V> par : this.minhaListaDePares) {
            if (par.getChave().equals(chave)) {
                return par;
            }
        }
        return null;
    }
}
