package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _095_UniqueBinarySearchTrees2 {
    /**
     * 返回二叉搜索树根结点的列表
     */
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int left, int right) {
        if (left > right) {
            List<TreeNode> t = new ArrayList<>();
            t.add(null);
            return t;
        }

        List<TreeNode> result = new ArrayList<>();
        // 以 i 作为根节点建立 BST
        // 递归处理左右子树
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftList = helper(left, i - 1);
            List<TreeNode> rightList = helper(i + 1, right);
            for (TreeNode lNode : leftList) {
                for (TreeNode rNode : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = lNode;
                    root.right = rNode;
                    result.add(root);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _095_UniqueBinarySearchTrees2 obj = new _095_UniqueBinarySearchTrees2();
        List<TreeNode> res = obj.generateTrees(3);
        for(TreeNode root : res) {
            System.out.println(TreeNode.serialize(root));
        }
    }
}