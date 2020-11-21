package LeetCode;

public class BSTNode {
    int val;
    BSTNode left;
    BSTNode right;

    public BSTNode(int x){
        val = x;
        left = null;
        right = null;
    }

    public static BSTNode makeBST(int[] vals) {
        BSTNode root = null;
        for(int val : vals){
            //root = insertRecursive(root,val);
            root = insertNonRecursive(root,val);
        }
        return root;
    }

    // 插入
    // 1. 递归版本
    public static BSTNode insertRecursive(BSTNode root, int x){
        if(root == null)
            return new BSTNode(x);
        else if(x < root.val)
            root.left = insertRecursive(root.left, x);
        else
            root.right = insertRecursive(root.right, x);
        return root;
    }

    // 2. 迭代版本（参考《算法导论》p166）
    public static BSTNode insertNonRecursive(BSTNode root, int x){
        BSTNode parent = null;
        BSTNode curr = root;
        while(curr != null) {
            parent = curr;
            if(x < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        if(parent == null) root = new BSTNode(x);
        else if(x <= parent.val) parent.left = new BSTNode(x);
        else parent.right = new BSTNode(x);
        return root;
    }

    // 遍历
    // 中序遍历，按序输出
    public static void inorderTraversal(BSTNode root) {
        if(root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }

    // 查找
    // 查找关键字为x的节点，如果存在，返回对应的节点；否则返回null
    public static BSTNode find(BSTNode root, int x) {
        BSTNode curr = root;
        while(curr != null && curr.val != x) {
            if(x < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        return curr;
    }

    // 查找最大值
    public static BSTNode findMax(BSTNode root) {
        BSTNode curr = root;
        while(curr.right != null)
            curr = curr.right;
        return curr;
    }

    // 查找最小值
    public static BSTNode findMin(BSTNode root) {
        BSTNode curr = root;
        while(curr.left != null)
            curr = curr.left;
        return curr;
    }

    // 删除（写法1：参考《算法笔记》）
    // 删除值为 x 的节点，并返回二叉搜索树的根
    public static BSTNode delete(BSTNode root, int x) {
        if(root == null) return null;
        if(x == root.val) {
            if(root.left == null && root.right == null){
                root = null;
            }else if(root.left != null) {
                BSTNode preNode = findMax(root.left);
                root.val = preNode.val;
                root.left = delete(root.left,preNode.val);
            }else {
                BSTNode nextNode = findMin(root.right);
                root.val = nextNode.val;
                root.right = delete(root.right,nextNode.val);
            }
        }else if(x < root.val) {
            root.left = delete(root.left, x);
        }else {
            root.right = delete(root.right, x);
        }
        return root;
    }

    // 删除（写法2：参考《算法》第4版）
    public static BSTNode delete2(BSTNode root, int x) {
        if(root == null) return null;

        if(x < root.val) root.left = delete2(root.left,x);
        else if(x > root.val) root.right = delete2(root.right,x);
        else { // 当前root节点为待删除的节点
            //如果当前节点只有一个孩子节点，则直接将其孩子节点提升为新的根节点
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            //如果当前节点有两个孩子节点
            BSTNode successor = findMin(root.right);
            successor.right = deleteMin(root.right);
            successor.left = root.left;
            return successor;
        }
        return root;
    }

    private static BSTNode deleteMin(BSTNode root) {
        if(root.left == null)
            return root.right;
        root.left = deleteMin(root.left);
        return root;
    }

    public static void main(String[] args) {
        int[] vals = {5, 3, 8, 1, 6, 9, 7};
        BSTNode root = makeBST(vals);
        inorderTraversal(root);
        System.out.println();
        System.out.println(findMax(root).val);
        System.out.println(findMin(root).val);
        if(find(root,6) != null) {
            System.out.println("find node 6");
        }else {
            System.out.println("can't find node 6");
        }

        root = delete2(root,6);
        inorderTraversal(root);
    }
}
