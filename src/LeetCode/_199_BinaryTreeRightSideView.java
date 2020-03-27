package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _199_BinaryTreeRightSideView {
    /**
     * 保存二叉树的右视图
     * 本题是层序遍历的一个简单变形，只需保存每一层最右边的节点即可
     * 时间复杂度: O(n) （因为总共要遍历n个节点
     * 空间复杂度: O(n) （借助队列~
     */
    /*
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove(0);
                if (i == size - 1) result.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }
    */

    // 递归法（第一次见）
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }

    private void dfs(TreeNode root, int depth, List<Integer> res) {
        if(root == null) return;
        if(depth == res.size()) { // 这个边界条件是关键
            res.add(root.val);
        }
        dfs(root.right, depth+1, res);
        dfs(root.left, depth+1, res);
    }

    public static void main(String[] args) {
        _199_BinaryTreeRightSideView obj = new _199_BinaryTreeRightSideView();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.rightSideView(root));
    }
}
