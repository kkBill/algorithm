package LeetCode;

import java.util.Stack;

class BSTIterator {
    private Stack<TreeNode> stack = new Stack<>();
    private TreeNode curr;

    public BSTIterator(TreeNode root) {
        curr = root;
    }

    /** @return the next smallest number */
    public int next() {
        int ans = -1;
        while(curr != null || !stack.empty()) {
            if(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }else{
                TreeNode node = stack.pop();
                curr = node.right;
                ans = node.val;
                break;
            }
        }
        return ans;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return curr != null || !stack.empty();
    }
}

public class _173_BinarySearchTreeIterator {
    public static void main(String[] args) {
        TreeNode root = TreeNode.makeTree();
        BSTIterator iterator = new BSTIterator(root);
        System.out.println(iterator.next());    // return 3
        System.out.println(iterator.next());    // return 7
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 9
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 15
        System.out.println(iterator.hasNext()); // return true
        System.out.println(iterator.next());    // return 20
        System.out.println(iterator.hasNext()); // return false
    }
}
