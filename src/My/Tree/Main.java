package My.Tree;

/**
 * Created by yellow-umbrella on 16/05/17.
 */
public class Main {
    public static void main(String args[]) {
        STBinarySearchTree<Integer, String> tree = new STBinarySearchTree<>();

        tree.put(9, "Num9");
        tree.put(3, "Num3");
        tree.put(11, "Num11");
        tree.put(1, "Num1");
        tree.put(2, "Num2");
        tree.put(7, "Num7");
        tree.put(5, "Num5");
        tree.put(8, "Num8");
        tree.put(6, "Num6");
        tree.put(10, "Num10");
        tree.put(13, "Num13");

        System.out.println(tree.min());
        System.out.println(tree.min(true));
        System.out.println(tree.max());
        System.out.println(tree.max(true));
        System.out.println(tree.sumAllKeys());
        tree.removeMax();
        System.out.println(tree.max());

        for(String x: tree)
            System.out.println(x);

    }
}
