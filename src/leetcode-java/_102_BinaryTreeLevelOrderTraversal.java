package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _102_BinaryTreeLevelOrderTraversal {
    /**
     * 层序遍历二叉树
     * 关键在于如何确定每一层对应的节点有哪些，这里以每一行作为一个遍历单元，
     * 核心代码在 for{} 处，当前队列中存放的节点数目就是上一层遍历时存进去的节点
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelNode = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove(0);
                levelNode.add(node.val);
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            result.add(levelNode);
        }
        return result;
    }

    public static void main(String[] args) {
        _102_BinaryTreeLevelOrderTraversal obj = new _102_BinaryTreeLevelOrderTraversal();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.levelOrder(root));
    }
}
