package LeetCode;

public class _100_SameTree {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null)
            return true;
        else if(p == null || q == null)
            return false;
        else
            return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


    public static void main(String[] args) {
        _100_SameTree obj = new _100_SameTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.isSameTree(root, root));
    }
}
