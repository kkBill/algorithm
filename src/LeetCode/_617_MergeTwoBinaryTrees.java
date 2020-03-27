package LeetCode;

public class _617_MergeTwoBinaryTrees {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        if(t2 == null) return t1;

        t1.val += t2.val;
        TreeNode leftSub = mergeTrees(t1.left, t2.left);
        TreeNode rightSub = mergeTrees(t1.right, t2.right);
        t1.left = leftSub;
        t1.right = rightSub;
        return t1;
    }

    public static void main(String[] args) {
        _617_MergeTwoBinaryTrees obj = new _617_MergeTwoBinaryTrees();
        TreeNode t1 = TreeNode.makeTree();
        TreeNode t2 = TreeNode.makeTree2();
        TreeNode t3 = obj.mergeTrees(t1,t2);
        System.out.println(TreeNode.serialize(t3));
    }
}
