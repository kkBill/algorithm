package LeetCode;

public class _110_BalancedBinaryTree {
    /**
     * 时间复杂度：O(nlgn)
     */
    /*
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if(Math.abs(leftHeight-rightHeight) > 1) return false;
        return isBalanced(root.left) && isBalanced(root.right);
    }
    private int height(TreeNode root) {
        if(root == null) return 0;
        return 1 + Math.max(height(root.left), height(root.right));
    }

    */

    /**
     * 时间复杂度：O(N)
     * 空间复杂度：O(H) （H 为树的高度）
     */
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1; //height(root) == -1 ? false : true;
    }
    private int height(TreeNode root) {
        if(root == null) return 0;
        int left = height(root.left);
        if(left == -1) return -1;
        int right = height(root.right);
        if(right == -1) return -1;
        if(Math.abs(left - right) > 1) return -1;
        return 1 + Math.max(left, right);
    }

    public static void main(String[] args) {
        _110_BalancedBinaryTree obj = new _110_BalancedBinaryTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.isBalanced(root));
    }
}
