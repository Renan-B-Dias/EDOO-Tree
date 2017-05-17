package My.SymbolTable;

import java.util.Iterator;

/**
 * Created by yellow-umbrella on 02/05/17.
 */
public class LinkedSymbolTable<K, V> implements SymbolTable<K, V> {

    private Node<SymbolItem<K, V>> head;
    private int size;

    public LinkedSymbolTable() {
        head = null;
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        Node<SymbolItem<K, V>> node = head;

        while(node != null) {
            if (node.element.key.equals(key)) {
                node.element.value = value;
                return;
            }
            node = node.next;
        }

//        while(node != null)
//            if(node.element.key.equals(key)) {
//                node.element.value = value;
//                return;
//            }
//            node = node.next;

        // Does not contains...

        head = new Node<>(new SymbolItem<>(key, value), head);
        size++;
    }

    @Override
    public V get(K key) {
        Node<SymbolItem<K, V>> node = head;

        while(node != null) {
            if (node.element.key.equals(key))
                return node.element.value;
            node = node.next;
        }

        // Did not find...
        return null;
    }

    @Override
    public void remove(K key) {
        // Lazy remove... tsk tsk...
        put(key, null);
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public boolean contains(K key) {
        return get(key) != null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<V> iterator() {
        return new ElementIterator();
    }

    private static final class Node<T> {
        T element;
        Node<T> next;

        Node(T element, Node<T> current) {
            this.element = element;
            this.next = current;
        }
    }

    private final class ElementIterator implements Iterator<V> {
        private Node<SymbolItem<K, V>> node;

        public ElementIterator() {
            node = head;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

        @Override
        public V next() {
            V value = node.element.value;
            node = node.next;
            return value;
        }

    }
//    private final class KeyIterator implements Iterator<K> {
//
//        private Node<SymbolItem<K, V>> node;
//
//        public KeyIterator() {
//            node = head;
//        }
//
//        @Override
//        public boolean hasNext() {
//            return node != null;
//        }
//
//        @Override
//        public K next() {
//            K key = node.element.key;
//            node = node.next;
//            return key;
//        }
//    }

}
