package LeetCode;


import java.util.HashMap;
import java.util.Map;

public class _337_HouseRobber3 {
    /*
    // 暴力递归
    public int rob(TreeNode root) {
        if(root == null) return 0;
        int sum1 = root.val;
        if(root.left != null){
            sum1 += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null) {
            sum1 += rob(root.right.left) + rob(root.right.right);
        }

        int sum2 = rob(root.left) + rob(root.right);
        return Math.max(sum1, sum2);
    }
     */

    /*
    // 记忆化搜索
    private Map<TreeNode, Integer> map = new HashMap<>();

    public int rob(TreeNode root) {
        if(root == null) return 0;
        if(map.containsKey(root)) return map.get(root); // 直接获取计算结果

        int sum1 = root.val;
        if(root.left != null){
            sum1 += rob(root.left.left) + rob(root.left.right);
        }
        if(root.right != null) {
            sum1 += rob(root.right.left) + rob(root.right.right);
        }

        int sum2 = rob(root.left) + rob(root.right);
        int result = Math.max(sum1, sum2);
        map.put(root, result); // 缓存中间计算结果
        return result;
    }
    */

    // 树状DP
    public int rob(TreeNode root) {
        int[] result = myRob(root);
        return Math.max(result[0], result[1]);
    }

    private int[] myRob(TreeNode root) {
        int[] result = new int[2];
        if(root == null) return result;

        int[] left = myRob(root.left);
        int[] right = myRob(root.right);

        // 选择当前节点
        result[1] = root.val + left[0] + right[0];
        // 不选择当前节点
        result[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);

        return result;
    }

    public static void main(String[] args) {
        _337_HouseRobber3 obj = new _337_HouseRobber3();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.rob(root));
    }
}
