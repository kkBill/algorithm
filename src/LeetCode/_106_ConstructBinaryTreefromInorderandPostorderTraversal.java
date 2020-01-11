package LeetCode;

public class _106_ConstructBinaryTreefromInorderandPostorderTraversal {
    /**
     * 通过 中序序列 + 后序序列 构建二叉树
     * key:
     * 1) 后序序列的最后一个元素是root
     * 2) 在中序序列中找到root的位置，然后分割出左右子树，在递归进行处理
     */
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
    }

    private TreeNode build(int[] inorder, int il, int ir, int[] postorder, int pl, int pr) {
        if (il > ir || pl > pr) return null;
        int rootVal = postorder[pr];
        TreeNode root = new TreeNode(rootVal);
        int pos = il;
        while (inorder[pos] != rootVal) pos++;
        int leftNum = pos - il;
        root.left = build(inorder, il, pos - 1, postorder, pl, pl + leftNum - 1);
        root.right = build(inorder, pos + 1, ir, postorder, pl + leftNum, pr - 1);
        return root;
    }

    public static void main(String[] args) {
        _106_ConstructBinaryTreefromInorderandPostorderTraversal obj =
                new _106_ConstructBinaryTreefromInorderandPostorderTraversal();
//        int[] inorder = {9,3,15,20,7};
//        int[] postorder = {9,15,7,20,3};
        int[] inorder = {3, 2, 1};
        int[] postorder = {3, 2, 1};
        TreeNode root = obj.buildTree(inorder, postorder);
        TreeNode.preOrderTraversalWithRecursion(root);
    }
}
