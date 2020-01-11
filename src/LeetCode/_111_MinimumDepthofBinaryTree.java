package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _111_MinimumDepthofBinaryTree {
    /**
     * 递归实现，需要遍历左右的叶节点，速度不够快
     */
    /*
    private int minDepth;
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        minDepth = Integer.MAX_VALUE;
        dfs(root,1);
        return minDepth;
    }
    private void dfs(TreeNode root, int curDepth) {
        if(root.left == null && root.right == null){
            if(curDepth < minDepth) minDepth = curDepth;
            return;
        }
        if(root.left != null) dfs(root.left,curDepth+1);
        if(root.right != null) dfs(root.right,curDepth+1);
    }

    */

    /**
     * 层次遍历，从上至下，首次遇到的叶节点就是对应着树的最低深度
     */
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++) {
                TreeNode node = queue.remove(0);
                if(node.left == null && node.right == null) return level;
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            level++;
        }
        return level;
    }

    public static void main(String[] args) {
        _111_MinimumDepthofBinaryTree obj = new _111_MinimumDepthofBinaryTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.minDepth(root));
    }
}
