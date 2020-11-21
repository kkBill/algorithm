package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class _107_BinaryTreeLevelOrderTraversal2 {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        LinkedList<List<Integer>> result = new LinkedList<>();
        if(root == null) return result;

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
            result.addFirst(levelNode);
        }
        return result;
    }

    public static void main(String[] args) {
        _107_BinaryTreeLevelOrderTraversal2 obj = new _107_BinaryTreeLevelOrderTraversal2();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.levelOrderBottom(root));
    }
}
