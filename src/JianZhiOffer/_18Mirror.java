package JianZhiOffer;

public class _18Mirror {
    // 树的镜像
    public void Mirror(TreeNode root) {
        if(root == null)
            return;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        Mirror(root.left);
        Mirror(root.right);
    }
}
