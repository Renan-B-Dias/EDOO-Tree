package My.SymbolTable;

/**
 * Created by yellow-umbrella on 02/05/17.
 */
public final class SymbolItem<K, V> {

    protected K key;
    protected V value;

    public SymbolItem() {}

    public SymbolItem(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

}
