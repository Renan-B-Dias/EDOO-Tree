package My.SymbolTable;

/**
 * Created by yellow-umbrella on 02/05/17.
 */
public interface SymbolTable<K, V> extends Iterable<V> {

    void put(K key, V value);
    V get(K key);
    void remove(K key);
    boolean isEmpty();
    boolean contains(K key);
    int size();

}
