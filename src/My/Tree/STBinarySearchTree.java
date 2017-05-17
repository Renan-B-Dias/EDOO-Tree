package My.Tree;

import My.SymbolTable.SymbolItem;

import java.util.Iterator;
import java.util.Stack;

/**
 * Created by yellow-umbrella on 09/05/17.
 */
public class STBinarySearchTree<K extends Comparable<K>, V> implements BinaryTree<K, V> {

    protected TreeNode<SymbolItem<K, V>> root;
    protected int size;

    public STBinarySearchTree() {
        root = null;
        size = 0;
    }

    @Override
    public void put(K key, V value) {
        root = put(root, key, value);
    }

    // Recursive method
    private TreeNode<SymbolItem<K, V>> put(TreeNode<SymbolItem<K, V>> n, K key, V value) {
        if (n == null) {
            size++;
            return new TreeNode<>(new SymbolItem<>(key, value));
        }

        int comp = key.compareTo(n.info.getKey());

        if (comp == 0)
            n.info.setValue(value);
        else if (comp < 0)
            n.left = put(n.left, key, value);
        else
            n.right = put(n.right, key, value);

        return n;
    }

    @Override
    public V get(K key) {
        return get(root, key);
    }

    // Recursive method
    private V get(TreeNode<SymbolItem<K, V>> node, K key) {
        if (node == null)    // Current root is null
            return null;

        int comp = key.compareTo(node.info.getKey());

        if (comp == 0)
            return node.info.getValue();
        if (comp < 0)
            return get(node.left, key);
        return get(node.right, key);
    }

    @Override
    public void remove(K key) {
        if(!isEmpty()) {
            int comp = key.compareTo(root.info.getKey());
            if(comp == 0) {
                root = removeRoot(root);
                return;
            }

            TreeNode<SymbolItem<K, V>> current;
            TreeNode<SymbolItem<K, V>> node = root;

            if(comp < 0)
                current = root.left;
            else
                current = root.right;

            while(current != null) {
                comp = key.compareTo(current.info.getKey());
                if(comp == 0) {
                    if (current == node.left)
                        node.left = removeRoot(current);
                    else
                        node.right = removeRoot(current);
                    size--;
                    return;
                }

                node = current;
                if(comp < 0)
                    current = current.left;
                else
                    current = current.right;
            }
        }
    }

    // Ignore all other args after [0]...
    public K min(boolean... iteractive) {
        if(!isEmpty()) {
            if(iteractive.length > 0 && iteractive[0] == true)
                return recursiveMin(this.root);
            return iteractiveMin(this.root);
        }
        return null;
    }

    private K recursiveMin(TreeNode<SymbolItem<K, V>> node) {
        if (node.left != null)
            return recursiveMin(node.left);
        return node.info.getKey();
    }

    private K iteractiveMin(TreeNode<SymbolItem<K, V>> node) {
        while(node.left != null)
            node = node.left;
        return node.info.getKey();
    }

    // Ignore all other args after [0]...
    public K max(boolean... iteractive) {
        if(!isEmpty()) {
            if(iteractive.length > 0 && iteractive[0] == true)
                return iteractiveMax(this.root);
            return recursiveMax(this.root);
        }
        return null;
    }

    private K recursiveMax(TreeNode<SymbolItem<K, V>> node) {
        if(node.right != null)
            return recursiveMax(node.right);
        return node.info.getKey();
    }

    private K iteractiveMax(TreeNode<SymbolItem<K, V>> node) {
        while(node.right != null)
            node = node.right;
        return node.info.getKey();
    }

    public Integer sumAllKeys() {
        if(!isEmpty() && summable(root.info.getKey()))
            return sumAll(this.root);
        return 0; // Throw new RuntimeException "K" is NOT a number
    }

    private Integer sumAll(TreeNode<SymbolItem<K, V>> node) {
        if(node == null)
            return 0;
        return sumAll(node.left) + sumAll(node.right) + (((Number)node.info.getKey()).intValue());
    }

    private boolean summable(K clss) {
        return clss instanceof Number;
    }

    public void removeMax() {
        remove(max());
    }

    private TreeNode<SymbolItem<K, V>> removeRoot(TreeNode<SymbolItem<K, V>> node) {
        if(node.left == null)
            return node.right;
        if(node.right == null)
            return node.left;

        TreeNode<SymbolItem<K, V>> p = null;
        TreeNode<SymbolItem<K, V>> q = node.right;

        while(q.left != null) {
            p = q;
            q = q.left;
        }

        if(p != null) {
            p.left = q.right;
            q.right = node.right;
        }
        q.left = node.left;

        return q;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public boolean contains(K key) {
        return contains(root, key);
    }

    // Recursive method
    private boolean contains(TreeNode<SymbolItem<K, V>> node, K key) {
        if(node == null)
            return false;

        int comp = key.compareTo(node.info.getKey());

        if(comp == 0)
            return true;
        if(comp < 0)
            return contains(node.left, key);

        return contains(node.right, key);
    }

    @Override
    public int size() {
        return size;
    }

    protected final class TreeNode<T> {
        T info;
        TreeNode<T> left;
        TreeNode<T> right;

        TreeNode(T info) {
            this.info = info;
        }

        TreeNode(T info, TreeNode left, TreeNode right) {
            this.info = info;
            this.left = left;
            this.right = right;
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new ElementIterator(root);
    }

    protected class ElementIterator implements Iterator<V> {
        private Stack<TreeNode<SymbolItem<K, V>>> stack;

        public ElementIterator(TreeNode<SymbolItem<K, V>> node) {
            stack = new Stack<>();
            if(node != null)
                stack.push(node);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public V next() {
            TreeNode<SymbolItem<K, V>> top = stack.pop();
            if (top.left != null)
                stack.push(top.left);
            if (top.right != null)
                stack.push(top.right);
            return top.info.getValue();
        }
    }
}
