package My.Tree;

import My.SymbolTable.SymbolItem;

/**
 * Created by yellow-umbrella on 09/05/17.
 */
public class IteractiveTree<K extends Comparable<K>, V> extends STBinarySearchTree<K, V> {

    @Override
    public V get(K key) {
        System.out.println("Iteractive get()");
        TreeNode<SymbolItem<K, V>> node = this.root;

        if(node == null) {return null;}

        do {
            int comp = key.compareTo(node.info.getKey());

            if(comp == 0)
                break;
            else if(comp < 0)
                node = node.left;
            else
                node = node.right;

        }while(node != null);

        if(node != null)
            return node.info.getValue();
        return null;
    }

    @Override
    public void put(K key, V value) {

        if(root == null) {
            SymbolItem<K, V> syIt = new SymbolItem<K, V>(key, value);
            TreeNode<SymbolItem<K, V>> node = new TreeNode(syIt);
            root = node;
            this.size++;
        } else {
            SymbolItem<K, V> syIt = new SymbolItem<K, V>(key, value);
            TreeNode<SymbolItem<K, V>> node = this.root;

            do {

                int comp = key.compareTo(node.info.getKey());

                if(comp == 0) {
                    node.info = new SymbolItem<>(key, value);
                    break;
                }
                else if(comp > 0) {
                    if(node.right == null) {
                        node.right = new TreeNode<SymbolItem<K, V>>(syIt);
                        this.size++;
                        break;
                    } else {
                        node = node.right;
                    }
                } else {
                    if(node.left == null) {
                        node.left = new TreeNode<SymbolItem<K, V>>(syIt);
                        this.size++;
                        break;
                    } else {
                        node = node.left;
                    }
                }
            }while(true);
        }
    }

    @Override
    public int size() {
        return recursiveSize(root);
    }

    private Integer recursiveSize(TreeNode<SymbolItem<K, V>> node) {
        if(node == null) {return 0;}
        return recursiveSize(node.left) + recursiveSize(node.right) + 1;
    }
}
