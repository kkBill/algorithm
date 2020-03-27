package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _124_BinaryTreeMaximumPathSum {
    /**
     * 递归
     */
    /*
    int maxSum = Integer.MIN_VALUE;

    public int maxPathSum(TreeNode root) {
        if (root == null) return 0;
        dfs(root);
        return maxSum;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        int leftVal = Math.max(dfs(root.left), 0);
        int rightVal = Math.max(dfs(root.right), 0);
        maxSum = Math.max(maxSum, leftVal + rightVal + root.val);
        return Math.max(leftVal, rightVal) + root.val;
    }
    */

    int maxSum = Integer.MIN_VALUE;
    List<Integer> path = new ArrayList<>();

    public List<Integer> maxPath(TreeNode root) {
        if (root == null) return path;
        dfs(root);
        return path;
    }

    private int dfs(TreeNode root) {
        if (root == null) return 0;
        path.add(root.val);
        int leftVal = Math.max(dfs(root.left), 0);
        int rightVal = Math.max(dfs(root.right), 0);

        maxSum = Math.max(maxSum, leftVal + rightVal + root.val);
        return Math.max(leftVal, rightVal) + root.val;
    }

    public static void main(String[] args) {
        _124_BinaryTreeMaximumPathSum obj = new _124_BinaryTreeMaximumPathSum();
        TreeNode root = TreeNode.makeTree();
        //System.out.println(obj.maxPathSum(root));
        System.out.println(obj.maxPath(root));
    }
}
