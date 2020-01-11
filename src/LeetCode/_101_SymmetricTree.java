package LeetCode;

public class _101_SymmetricTree {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return isMirror(root.left, root.right);
    }
    private boolean isMirror(TreeNode p, TreeNode q){
        if(p == null && q == null) return true;
        else if(p == null || q == null) return false;
        else return p.val == q.val && isMirror(p.left,q.right) && isMirror(p.right,q.left);
    }
    public static void main(String[] args) {
        _101_SymmetricTree obj = new _101_SymmetricTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.isSymmetric(root));
    }
}
