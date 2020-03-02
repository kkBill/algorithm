package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _199_BinaryTreeRightSideView {
    /**
     * 保存二叉树的右视图
     * 本题是层序遍历的一个简单变形，只需保存每一层最右边的节点即可
     * 时间复杂度: O(n) （因为总共要遍历n个节点
     * 空间复杂度: O(n) （借助队列~
     *
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        TreeNode node;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.remove(0);
                if (i == size - 1) result.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _199_BinaryTreeRightSideView obj = new _199_BinaryTreeRightSideView();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.rightSideView(root));
    }
}
