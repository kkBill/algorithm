package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _098_ValidateBinarySearchTree {
    /**
     * 方法1：最直观的方法
     * 中序遍历BST，再判断所得的序列是否为升序序列
     */
    /*
    public boolean isValidBST(BSTNode root) {
        List<Integer> inorder = new ArrayList<>();
        inorderTraversal(root, inorder);
        for(int i=1;i<inorder.size();i++){
            if(inorder.get(i-1) >= inorder.get(i)) return false;
        }
        return true;
    }
    private void inorderTraversal(BSTNode root, List<Integer> inorder) {
        if(root != null) {
            inorderTraversal(root.left, inorder);
            inorder.add(root.val);
            inorderTraversal(root.right,inorder);
        }
    }
    */

    /**
     * 方法2：（递归实现，而不需要保存整个序列的值）
     * 中序遍历，判断当前的值与前一个值的大小
     * 如果当前的值 >= 前一个值，则返回 false
     */
    /*
    // 注意，这种写法是错误的！
    // 当出现下面这种特殊的情况时：
    //     1
    //    /
    //   1
    // 会出现错误的判定~（本应判定为false，但程序会返回true）
    // 原因在于 pre 节点并没有更新
    public boolean isValidBST(BSTNode root) {
        BSTNode pre = null;
        return inorder(root, pre);
    }

    private boolean inorder(BSTNode root, BSTNode pre) {
        if (root == null) return true;
        boolean flag = inorder(root.left, pre);
        if(!flag) return false;
        if(pre != null && pre.val >= root.val) return false;
        pre = root;
        return inorder(root.right, pre);
    }
    */

    // 正确的写法~
    /*
    private BSTNode pre = null;
    public boolean isValidBST(BSTNode root) {
        return inorder(root);
    }

    private boolean inorder(BSTNode root) {
        if (root == null) return true;
        boolean flag = inorder(root.left);
        if(!flag) return false;
        if(pre != null && pre.val >= root.val) return false;
        pre = root;
        return inorder(root.right);
    }

    */


    // 还可以用中序遍历的非递归写法进行判断

    /**
     * 方法3：比较上下界（好方法！）
     * 对于二叉搜索树而言，任意节点必须满足 左子树的值 < root.val < 右子树的值
     * 所以针对每个节点，都进行这一条件的判断
     */
    public boolean isValidBST(BSTNode root) {
        if (root == null) return true;
        return valid(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
    private boolean valid(BSTNode root, long low, long high) {
        if (root == null) return true;
        if (low >= root.val || root.val >= high) return false;
        return valid(root.left, low, root.val) &&
                valid(root.right, root.val, high);
    }

    public static void main(String[] args) {
        _098_ValidateBinarySearchTree obj = new _098_ValidateBinarySearchTree();
        int[] vals = {1, 1};
        BSTNode root = BSTNode.makeBST(vals);
        System.out.println(obj.isValidBST(root));
    }
}
