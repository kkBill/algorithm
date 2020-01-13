package LeetCode;

public class _236_LowestCommonAncestorofaBinaryTree {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if(left != null && right != null) return root;
        else return left == null ? right : left;
    }

    public static void main(String[] args) {
        _236_LowestCommonAncestorofaBinaryTree obj = new _236_LowestCommonAncestorofaBinaryTree();
        TreeNode root = TreeNode.makeTree();
        TreeNode p = null, q = null;
        TreeNode lca = obj.lowestCommonAncestor(root, p, q);
    }
}
