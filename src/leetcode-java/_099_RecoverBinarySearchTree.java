package LeetCode;

public class _099_RecoverBinarySearchTree {
    /**
     * 递归实现，需要借助全局变量
     *
     * 正常的顺序是：1, 2, 3, 4, 5
     *
     * case 1: 相邻的顺序出现错误
     * 1, 3, 2, 4, 5
     *
     * case 2: 不相邻的顺序出现错误
     * 1, 5, 3, 4, 2
     *
     *
     */
    TreeNode first = null;
    TreeNode second = null;
    TreeNode pre = null;
    public void recoverTree(TreeNode root) {
        inorder(root);
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        if(pre != null && pre.val > root.val){
            if(first == null) { // 首次遇到逆序对
                first = pre;
                second = root;
            }else {
                second = root;
            }
        }
        pre = root;
        inorder(root.right);
    }

    public static void main(String[] args) {
        _099_RecoverBinarySearchTree obj = new _099_RecoverBinarySearchTree();

    }
}
