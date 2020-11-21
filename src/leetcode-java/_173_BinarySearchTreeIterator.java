package LeetCode;

import java.util.LinkedList;
import java.util.Stack;

class BSTIterator {
    /*
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode p = root;
        while(p != null) {
            stack.push(p);
            p = p.left;
        }
    }

    public int next() {
        TreeNode t = stack.pop();
        TreeNode p = t.right;
        while(p != null) {
            stack.push(p);
            p = p.left;
        }
        return t.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
    */

    LinkedList<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new LinkedList<>();
        TreeNode p = root;
        while(p != null) {
            stack.addLast(p);
            p = p.left;
        }
    }

    public int next() {
        TreeNode t = stack.pollLast();
        TreeNode p = t.right;
        while(p != null) {
            stack.addLast(p);
            p = p.left;
        }
        return t.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
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
