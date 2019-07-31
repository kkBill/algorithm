package JianZhiOffer;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode(int x) {
        val = x;
    }
}

public class _4reConstructBinaryTree {

    public TreeNode buildBTree(int[] pre, int preLeft, int preRight, int[] in, int inLeft, int inRight) {
        if (preLeft > preRight || inLeft > inRight) return null;
        int rootVal = pre[preLeft]; // 前序遍历的第一个节点是根节点
        TreeNode root = new TreeNode(rootVal);
        int p = inLeft; // 根节点在中序遍历中的位置
        while (in[p] != rootVal) p++;
        int leftCnt = p - inLeft; // 左子树的节点个数
        root.left = buildBTree(pre, preLeft + 1, preLeft + leftCnt, in, inLeft, p - 1);
        root.right = buildBTree(pre, preLeft + leftCnt + 1, preRight, in, p + 1, inRight);
        return root;
    }

    public TreeNode reConstructBinaryTree(int[] pre, int[] in) {
        return buildBTree(pre, 0, pre.length - 1, in, 0, in.length - 1);
    }
}
