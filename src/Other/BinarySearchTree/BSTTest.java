package Other.BinarySearchTree;

public class BSTTest {
    public static void main(String[] args) {
        BST<Integer, String> bst = new BST<>();
        bst.put(10, "aaa");
        bst.put(6, "bbb");
        bst.put(17, "ccc");
        bst.put(3, "ddd");
        bst.put(5, "eee");
        bst.put(12, "fff");
        bst.put(19, "ggg");

        System.out.println("in order traversal：");
        bst.printBSTByInOrder();
        System.out.println("level order traversal：");
        bst.printBSTByLevelOrder();


        System.out.println("bst.size = " + bst.size());
        System.out.println("min node's key of bst: " + bst.min());
        System.out.println("max node's key of bst: " + bst.max());
//        bst.deleteMin();
//        System.out.println("min node's key of bst: " + bst.min());
//        bst.deleteMax();
//        System.out.println("max node's key of bst: " + bst.max());

        System.out.println("the value of 3: " + bst.get(3));
        System.out.println("the key of rank 4: " + bst.select(4));
        System.out.println("the rank of key 12: " + bst.rank(12));

        System.out.println("delete node which key is 10: ");
        bst.delete(10);
        System.out.println("add a new node(13,\"abc\")：");
        bst.put(13,"abc");
        System.out.println("in order traversal：");
        bst.printBSTByInOrder();
        System.out.println("level order traversal：");
        bst.printBSTByLevelOrder();
    }
}
