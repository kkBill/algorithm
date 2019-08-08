package Other.RedBlackTree;

public class RBTreeTest {
    public static void main(String[] args) {
        RBTree<String,Integer> rbt = new RBTree<>();
        rbt.put("S", null);
        rbt.put("E", null);
        rbt.put("A", null);
        rbt.put("R", null);
        rbt.put("C", null);
        rbt.put("H", null);
        rbt.put("X", null);
        rbt.put("M", null);
        rbt.put("P", null);
        rbt.put("L", null);

        System.out.println("层次遍历：");
        rbt.printByLevelOrder();
        System.out.println("中序遍历：");
        rbt.printByInOrder();
    }
}
