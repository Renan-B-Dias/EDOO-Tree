package My.Tree;

/**
 * Created by yellow-umbrella on 09/05/17.
 */
public interface BinaryTree<K, V> extends Iterable<V> {
    void put(K key, V value);
    V get(K key);
    void remove(K key);
    boolean isEmpty();
    boolean contains(K key);
    int size();
}
