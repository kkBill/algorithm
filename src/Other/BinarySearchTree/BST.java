package Other.BinarySearchTree;

import java.util.ArrayDeque;
import java.util.Queue;

// 由于BST中的节点是按序存放的，故Key要继承自Compare
public class BST<Key extends Comparable<Key>, Value> {

    // 每个Node对象都是一棵含有N个节点的子树的根节点
    private Node root;

    // 内部节点：定义树节点
    private class Node {
        private Key key;
        private Value value;
        private Node left, right;
        private int N; // 以该节点为根的子树中的节点总数

        Node(Key key, Value value, int N) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.N = N;
        }
    }

    public void printRoot(){
        System.out.println("root: "+ "["+root.key+", "+root.value+"]");
        if(root.left != null) System.out.println("root's left child: " + "["+root.left.key+", "+root.left.value+"]");
        if(root.right != null) System.out.println("root's right child: " + "["+root.right.key+", "+root.right.value+"]");
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) return 0;
        else return x.N;
    }

    // 查询（递归实现）
    public Value get(Key key) {
        return get(root, key);
    }

    // 在以x为根节点的子树中查找key，并返回对应的value
    // 如果没有找到，则返回null
    private Value get(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key); // if key < x.key, cmp < 0
        if (cmp == 0) return x.value;
        else if (cmp < 0) return get(x.left, key);
        else return get(x.right, key);
    }

    // 插入（递归实现）
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    // 如果key已经存在于以x为根节点的子树中，则更新它对应的value
    // 否则以key和value为键值对的新节点插入相应的位置
    private Node put(Node x, Key key, Value value) {
        if (x == null) return new Node(key, value, 1);

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, value); // 如果待插入的 key<根节点.key，则插入根节点的左子树中
        else if (cmp > 0) x.right = put(x.right, key, value);
        else x.value = value; // 该节点原本就存在key，现在更新它对应的value

        // 在插入节点后，注意更新以x为根节点的子树的节点总数
        // 注意，如果只是更新了原key对应的value值，没插入新的节点，这么计算也是不影响的
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    //
    public Key min() {
        return min(root).key;
    }

    // 获取以x为根节点的子树的最小节点（递归实现）
    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    //
    public Key max() {
        return max(root).key;
    }

    // 获取以x为根节点的子树的最大节点（递归实现）
    private Node max(Node x) {
        if (x.right == null) return x;
        return max(x.right);
    }

    //
    public Key select(int k) {
        Node res = select(root, k);
        if (res == null) return null;
        else return res.key;
    }

    // 查询以x为根节点的子树中排名第k个节点
    private Node select(Node x, int k) {
        if (x == null) return null;
        int t = size(x.left);
        if (t >= k) return select(x.left, k);
        else if (t == k - 1) return x;
        else return select(x.right, k - t - 1);
    }

    public int rank(Key key) {
        return rank(root, key);
    }

    // rank()是select()的逆方法，查询key在以x为根节点的子树中的排名
    // 如果给定的键和根节点的键相等，则返回左子树中的节点总数+1
    private int rank(Node x, Key key) {
        if (x == null) return 0;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) return rank(x.left, key);
        else if (cmp > 0) return size(x.left) + 1 + rank(x.right, key);
        else return size(x.left) + 1;
    }

    public void deleteMin() {
        root = deleteMin(root);
    }

    // 删除以x为根节点的子树的最小节点，返回删除后该子树的根节点
    // 如果x的左子树不为空，则递归删除其左子树的最小值
    private Node deleteMin(Node x) {
        if (x.left == null) return x.right; // 如果左子树为空，则把当前的根节点x删掉，即直接返回x.right作为新的根节点
        x.left = deleteMin(x.left); // 左子树非空，则在左子树中删除最小节点，并调整N
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) return x.left;
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    // 删除key节点，并返回删除节点后子树的根节点
    private Node delete(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // 如果待删除的节点只有一个孩子，则直接把孩子提升上来即可
            if (x.left == null) return x.right;
            if (x.right == null) return x.left;

            Node t = x; // t 是待删除的节点
            x = min(t.right); // 把待删除节点 t 的右子树的最小节点提升为x，然后再删除 t 的右子树中的最小节点
            // 下面两句的顺序不能调换
            x.right = deleteMin(t.right);
            x.left = t.left;
        }

        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 中序遍历打印树节点（for debug）
    public void printBSTByInOrder() {
        printBSTByInOrder(root);
    }

    private void printBSTByInOrder(Node x) {
        if (x != null) {
            printBSTByInOrder(x.left);
            System.out.println("[" + x.key + ", " + x.value + "]");
            printBSTByInOrder(x.right);
        }
    }

    // 层序遍历打印树节点（for debug）
    public void printBSTByLevelOrder() {
        Queue<Node> q = new ArrayDeque<>();
        q.add(root);
        while (!q.isEmpty()) {
            Node t = q.remove();
            System.out.println("[" + t.key + ", " + t.value + "]");
            if(t.left != null) q.add(t.left);
            if(t.right != null) q.add(t.right);
        }
    }
}
