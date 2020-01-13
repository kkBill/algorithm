package LeetCode;

public class _230_KthSmallestElementinaBST {
    /**
     * 中序遍历的递归写法
     *
     */



    /**
     * 中序遍历的非递归写法
     *
     */


    /**
     * 分治法，需要计算以当前节点为根节点的树的节点个数（这个是关键）
     *
     */
    public int kthSmallest(BSTNode root, int k) {
        int cnt = count(root.left);
        if(k <= cnt) return kthSmallest(root.left,k);
        else if(k == cnt+1) return root.val;
        else return kthSmallest(root.right,k-cnt-1);
    }

    // 关键，老是忘记
    private int count(BSTNode root) {
        if(root == null) return 0;
        return 1 + count(root.left) + count(root.right);
    }

    public static void main(String[] args) {
        _230_KthSmallestElementinaBST obj = new _230_KthSmallestElementinaBST();
        int[] vals = {5, 3, 8, 1, 6, 9};
        BSTNode root = BSTNode.makeBST(vals);
        System.out.println(obj.kthSmallest(root,1));
        System.out.println(obj.kthSmallest(root,3));
    }
}
