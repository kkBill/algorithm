package LeetCode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class _662_MaximumWidthofBinaryTree {
    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        queue.add(root);
        map.put(root, 1);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            maxWidth = Math.max(maxWidth, map.get(queue.getLast()) - map.get(queue.getFirst()) + 1);
            int size = queue.size();
            for(int i = 0;i < size; i++) {
                TreeNode front = queue.pollFirst();
                int pos = map.get(front);
                if(front.left != null) {
                    queue.add(front.left);
                    map.put(front.left, 2*pos);
                }
                if(front.right != null) {
                    queue.add(front.right);
                    map.put(front.right,2*pos+1);
                }
            }
        }
        return maxWidth;
    }

    public static void main(String[] args) {
        _662_MaximumWidthofBinaryTree obj = new _662_MaximumWidthofBinaryTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.widthOfBinaryTree(root));
    }
}
