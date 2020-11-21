package LeetCode;

public class _226_InvertBinaryTree {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        // invert opration
        root.left = right;
        root.right = left;
        return root;
    }

    public static void main(String[] args) {

    }
}
