package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _958_CheckCompletenessofaBinaryTree {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;

        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.remove(0);
            if(node == null) {
                while(!queue.isEmpty()) {
                    node = queue.remove(0);
                    if(node != null) return false;
                }
            }
            if(node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _958_CheckCompletenessofaBinaryTree obj = new _958_CheckCompletenessofaBinaryTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.isCompleteTree(root));
    }
}
