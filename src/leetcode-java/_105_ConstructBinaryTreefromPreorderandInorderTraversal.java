package LeetCode;

public class _105_ConstructBinaryTreefromPreorderandInorderTraversal {
    /**
     * 通过 前序序列 + 中序序列 构建二叉树
     * key：
     * 1) 前序序列的首个元素是二叉树的root，找到root在中序序列中的位置，即可分割左右子树
     * 2) 递归处理左右子树
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode build(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
        // 边界条件
        if (pl > pr || il > ir) return null;
        // 确定root节点
        int rootVal = preorder[pl];
        TreeNode root = new TreeNode(rootVal);

        int pos = il;
        while (inorder[pos] != rootVal) pos++; // pos 最终停留在中序序列root节点的位置

        int leftNum = pos - il; // 左子树的节点个数
        root.left = build(preorder, pl + 1, pl + leftNum, inorder, il, pos - 1);
        root.right = build(preorder, pl + leftNum + 1, pr, inorder, pos + 1, ir);
        return root;
    }

    public static void main(String[] args) {
        _105_ConstructBinaryTreefromPreorderandInorderTraversal obj =
                new _105_ConstructBinaryTreefromPreorderandInorderTraversal();
        int[] preorder = {1,2,3};
        int[] inorder = {3,2,1};
        TreeNode root = obj.buildTree(preorder, inorder);
        TreeNode.preOrderTraversalWithRecursion(root);
    }
}
