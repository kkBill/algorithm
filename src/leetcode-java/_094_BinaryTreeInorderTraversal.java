package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _094_BinaryTreeInorderTraversal {
    // 迭代写法比较生疏
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            }else {
                p = stack.pop();
                list.add(p.val);
                p = p.right;
            }
        }
        return list;
    }

    public static void main(String[] args) {
        _094_BinaryTreeInorderTraversal obj = new _094_BinaryTreeInorderTraversal();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.inorderTraversal(root));
    }
}
