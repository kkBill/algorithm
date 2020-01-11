package LeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class _103_BinaryTreeZigzagLevelOrderTraversal {
    /**
     * 方法1
     */
    /*
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> levelNode = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove(0);
                // 存放这一层的结果
                levelNode.add(node.val);

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            // "作弊"写法
            if(level % 2 == 1)
                Collections.reverse(levelNode);
            result.add(levelNode);
            level++;
        }
        return result;
    }
    */

    /**
     * 巧妙地利用链表来控制每一行的顺序
     * 假设levelNode(LinkedList类型)是用来存放每一行的遍历结果的，
     * 1)当偶数行时，就利用“尾插法”插入levelNode
     * 2)当奇数行时，就利用“头插法”插入levelNode
     * 通过头插法达到逆序的效果！！！
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        int level = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> levelNode = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove(0);
                // 存放这一层的结果
                if(level % 2 == 0)
                    levelNode.add(node.val);
                else
                    levelNode.addFirst(node.val); // 关键

                if (node.left != null)
                    queue.add(node.left);
                if (node.right != null)
                    queue.add(node.right);
            }
            result.add(levelNode);
            level++;
        }
        return result;
    }

    public static void main(String[] args) {
        _103_BinaryTreeZigzagLevelOrderTraversal obj = new _103_BinaryTreeZigzagLevelOrderTraversal();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.zigzagLevelOrder(root));
    }
}
