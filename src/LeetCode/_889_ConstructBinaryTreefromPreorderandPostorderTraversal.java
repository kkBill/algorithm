package LeetCode;

public class _889_ConstructBinaryTreefromPreorderandPostorderTraversal {
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return build(pre, 0, pre.length - 1, post, 0, post.length - 1);
    }

    private TreeNode build(int[] pre, int preL, int preR, int[] post, int postL, int postR) {
        if (preL > preR) return null;
        if (preL == preR) return new TreeNode(pre[preL]);

        TreeNode root = new TreeNode(pre[preL]);
        int childIndex = preL + 1;
        int childVal = pre[childIndex];
        int pos = postL;
        while (post[pos] != childVal) pos++;

        // 以root为根节点的树只含有一个孩子节点（根据题意，我们可以任意指定为左孩子（或右孩子））
        if (preR - childIndex == pos - postL) {
            root.left = null;
            root.right = build(pre, childIndex, preR, post, postL, pos);
        } else {
            int leftNum = pos - postL + 1;// 以root为根节点的左子树的节点个数
            root.left = build(pre, childIndex, childIndex + leftNum - 1, post, postL, pos);
            root.right = build(pre, childIndex + leftNum, preR, post, pos + 1, postR - 1);
        }
        return root;
    }

    public static void main(String[] args) {
        _889_ConstructBinaryTreefromPreorderandPostorderTraversal obj =
                new _889_ConstructBinaryTreefromPreorderandPostorderTraversal();

        int[] pre = {1, 2, 3, 4};
        int[] post = {3, 4, 2, 1};
        TreeNode root = obj.constructFromPrePost(pre, post);
        System.out.println(TreeNode.serialize(root));

        int[] pre2 = {1, 2, 3, 4, 5};
        int[] post2 = {2, 4, 5, 3, 1};
        root = obj.constructFromPrePost(pre2, post2);
        System.out.println(TreeNode.serialize(root));
    }
}
