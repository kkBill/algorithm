package LeetCode;

public class _104_MaximumDepthofBinaryTree {
    private int maxDepth;
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;

        maxDepth = 0;
        dfs(root,0);
        return maxDepth;
    }

    private void dfs(TreeNode root, int curDepth) {
        if(root == null){
            if(curDepth > maxDepth) maxDepth = curDepth;
            return;
        }
        dfs(root.left,curDepth+1);
        dfs(root.right,curDepth+1);
    }

    public static void main(String[] args) {
        _104_MaximumDepthofBinaryTree obj = new _104_MaximumDepthofBinaryTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.maxDepth(root));
    }
}
