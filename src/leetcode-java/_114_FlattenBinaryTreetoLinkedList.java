package LeetCode;

public class _114_FlattenBinaryTreetoLinkedList {
    /**
     * 方法1：迭代法
     */
    /*
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null) {
            if (curr.left != null) {
                TreeNode last = curr.left;
                while (last.right != null) {
                    last = last.right;
                }
                last.right = curr.right;
                curr.right = curr.left;
                curr.left = null;
            }
            curr = curr.right;
        }
    }
    */

    /**
     * 方法2：递归法, 太精妙了~~~
     */
    TreeNode pre = null;
    public void flatten(TreeNode root) {
        if (root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = pre;
        root.left = null;
        pre = root;
    }

    public static void main(String[] args) {
        _114_FlattenBinaryTreetoLinkedList obj = new _114_FlattenBinaryTreetoLinkedList();
        TreeNode root = TreeNode.makeTree();
        obj.flatten(root);
        while (root != null) {
            System.out.print(root.val + " ");
            root = root.right;
        }
    }
}
