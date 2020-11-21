package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _437_PathSum3 {
    public int pathSum(TreeNode root, int sum) {
        // key: prefix sum till current node
        // value: the numbers of path which path sum equals key
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化<0,1> 是为了保证根节点至叶节点之和恰等于sum的情况
        map.put(0, 1);
        return dfs(root, 0, sum, map);
    }

    private int dfs(TreeNode root, int curSum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;
        curSum += root.val;

        // 以本节点为终点，存在路径和等于target的路径条数
        int count = map.getOrDefault(curSum - target, 0);

        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        // 以本节点为起点，存在路径和等于target的路径条数
        // 递归计算其左右子树
        int lCount = dfs(root.left, curSum, target, map);
        int rCount = dfs(root.right, curSum, target, map);

        // 回溯
        map.put(curSum, map.getOrDefault(curSum, 0) - 1);

        return count + lCount + rCount;
    }

    public static void main(String[] args) {
        _437_PathSum3 obj = new _437_PathSum3();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.pathSum(root, 8));
    }
}
