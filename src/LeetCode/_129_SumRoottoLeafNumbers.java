package LeetCode;

public class _129_SumRoottoLeafNumbers {
    /**
     * 深度优先遍历、回溯法
     */
//    int sum = 0;
//    public int sumNumbers(TreeNode root) {
//        StringBuilder path = new StringBuilder();
//        dfs(root, path);
//        return sum;
//    }
//
//    private void dfs(TreeNode root, StringBuilder path) {
//        if (root.left == null && root.right == null) {
//            path.append(root.val);
//            sum += Integer.parseInt(path.toString());
//            path.deleteCharAt(path.length() - 1);
//            return;
//        }
//        path.append(root.val);
//        if (root.left != null) dfs(root.left, path);
//        if (root.right != null) dfs(root.right, path);
//        path.deleteCharAt(path.length() - 1);
//    }

    /*
    int res = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    private void dfs(TreeNode root, int sum) {
        if(root == null) return;
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null)  {
            res += sum;
            return;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
    }
    */

    // 最优雅的写法
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode root, int sum) {
        if(root == null) return 0;
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null) return sum;
        return dfs(root.left, sum) + dfs(root.right, sum);
    }

    public static void main(String[] args) {
        _129_SumRoottoLeafNumbers obj = new _129_SumRoottoLeafNumbers();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.sumNumbers(root));
    }
}
