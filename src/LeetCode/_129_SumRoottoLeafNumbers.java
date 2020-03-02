package LeetCode;

public class _129_SumRoottoLeafNumbers {
    /**
     * 深度优先遍历、回溯法
     */
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        StringBuilder path = new StringBuilder();
        dfs(root, path);
        return sum;
    }

    private void dfs(TreeNode root, StringBuilder path) {
        if (root.left == null && root.right == null) {
            path.append(root.val);
            sum += Integer.parseInt(path.toString());
            path.deleteCharAt(path.length() - 1);
            return;
        }
        path.append(root.val);
        if (root.left != null) dfs(root.left, path);
        if (root.right != null) dfs(root.right, path);
        path.deleteCharAt(path.length() - 1);
    }

    public static void main(String[] args) {
        _129_SumRoottoLeafNumbers obj = new _129_SumRoottoLeafNumbers();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.sumNumbers(root));
    }
}
