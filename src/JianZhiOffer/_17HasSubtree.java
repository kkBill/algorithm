package JianZhiOffer;

public class _17HasSubtree {
    public boolean doesTree1HasTree2(TreeNode root1, TreeNode root2){
        if(root2 == null) return true;
        if(root1 == null) return false;

        if(root1.val != root2.val){
            return false;
        }else{
            return doesTree1HasTree2(root1.left, root2.left) &&
                    doesTree1HasTree2(root1.right, root2.right);
        }
    }

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        boolean result = false;
        if(root1 != null && root2 != null){
            result = doesTree1HasTree2(root1, root2);
            if(!result)
                result = HasSubtree(root1.left, root2);
            if(!result)
                result = HasSubtree(root1.right, root2);
        }
        return result;
    }
}
