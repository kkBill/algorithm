package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class _104_MaximumDepthofBinaryTree {
    /* DFS
    // 解法1
    private int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        dfs(root, 1);
        return maxDepth;
    }

    private void dfs(TreeNode root, int depth) {
        if(root == null) return;
        maxDepth = Math.max(maxDepth, depth);
        dfs(root.left, depth+1);
        dfs(root.right, depth+1);
    }
    */

    /* DFS
    // 解法2：优雅
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
    */

    // BFS
    // 解法3：
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxDepth++;
            for(int i = 0; i < size; i++) {
                TreeNode front = queue.remove();
                if(front.left != null) queue.add(front.left);
                if(front.right != null) queue.add(front.right);
            }
        }
        return maxDepth;
    }

    public static void main(String[] args) {
        _104_MaximumDepthofBinaryTree obj = new _104_MaximumDepthofBinaryTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.maxDepth(root));
    }
}
