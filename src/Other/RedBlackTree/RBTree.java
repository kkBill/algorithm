package Other.RedBlackTree;

import java.util.ArrayDeque;
import java.util.Queue;

public class RBTree<Key extends Comparable<Key>, Value> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int N; //该子树的节点总数
        boolean color; //标识该节点的颜色（由其父节点指向它的连接的颜色）

        Node(Key key, Value value, int n, boolean color) {
            this.key = key;
            this.value = value;
            this.N = n;
            this.color = color;
        }
    }

    private boolean isEmpty() {
        return root == null;
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    private int size(Node x) {
        if (x == null) return 0;
        return x.N;
    }

    // 查询。如果存在key节点，则返回对应的value；否则返回null
    // LLRB tree的查询操作与普通BST的查询操作一样
    public Value get(Key key){
        Node x = root;
        // 非递归写法
        while(x!=null){
            int cmp = key.compareTo(x.key);
            if(cmp < 0) x = x.left;
            else if(cmp > 0) x = x.right;
            else return x.value;
        }
        return null;
    }

    /*
        min() max()方法也和BST一样

     */


    /*
       插入操作
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    // 分裂一个4-节点时，需要转换3个节点的颜色
    private void colorFlip(Node h) {
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
    }

    // 插入
    public void put(Key key, Value value) {
        // 查找key，找到则更新它的值，否则为它新建一个节点
        root = put(root, key, value);
        root.color = BLACK;
    }

    // 向以 h 为根节点的树中插入 key
    // 首先，查找key，找到则更新它的值，否则为它新建一个节点（即到 h == null 时）
    // 插入节点后再进行自下而上的旋转调整
    private Node put(Node h, Key key, Value value) {
        // insert a new node at the bottom
        if (h == null)
            return new Node(key, value, 1, RED);

        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, value);
        else if (cmp > 0) h.right = put(h.right, key, value);
        else h.value = value;

        // 旋转，调整
        // rotate-left right-leaning reds to enforce left-leaning condition
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        // rotate-right red-red pairs to balance a 4-node
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        // split a 4-node
        if (isRed(h.left) && isRed(h.right))
            colorFlip(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


    /**
     *  删除树的最大节点
     */
    public void deleteMax(){
        root = deleteMax(root);
        if(!isEmpty())
            root.color = BLACK;
    }

    // 删除以 h 为根节点的子树的最大节点，并返回删除后该子树的根节点
    private Node deleteMax(Node h){
        if(isRed(h.left))
            h = rotateRight(h);

        // 执行删除，把节点 h 给删除了
        if(h.right == null)
            return null;

        if(!isRed(h.right) && !isRed(h.right.left))
            h = moveRedRight(h);

        h.right = deleteMax(h.right);

        return fixUp(h);
    }


    // fix right-leaning reds and eliminate 4-nodes on the way up
    private Node fixUp(Node h){
        // rotate-left right-leaning reds to enforce left-leaning condition
        if (isRed(h.right) && !isRed(h.left))
            h = rotateLeft(h);
        // rotate-right red-red pairs to balance a 4-node
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        // split a 4-node
        if (isRed(h.left) && isRed(h.right))
            colorFlip(h);

        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }

    private Node moveRedRight(Node h){
        colorFlip(h);
        if(isRed(h.left.left)){
            h = rotateRight(h);
            colorFlip(h);
        }
        return h;
    }

    /**
     *  删除树的最小节点
     */
    public void deleteMin(){
        root = deleteMin(root);
        if(!isEmpty()) root.color = BLACK;
    }

    private Node deleteMin(Node h){
        if(h.left == null) return null;
        if(!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return fixUp(h);
    }

    private Node moveRedLeft(Node h) {
        colorFlip(h);
        if(isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            colorFlip(h);
        }
        return h;
    }

    /**
     * for debug
     */
    // 中序遍历打印树节点
    public void printByInOrder() {
        printByInOrder(root);
    }

    private void printByInOrder(Node x) {
        if (x != null) {
            printByInOrder(x.left);
            System.out.println("[" + x.key + ", " + x.value + ", " + (x.color ? "RED" : "BLACK") + "]");
            printByInOrder(x.right);
        }
    }

    // 层序遍历打印树节点
    public void printByLevelOrder() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node t = q.remove();
            System.out.println("[" + t.key + ", " + t.value + ", " + (t.color ? "RED" : "BLACK") + "]");
            if(t.left != null) q.add(t.left);
            if(t.right != null) q.add(t.right);
        }
    }

}
