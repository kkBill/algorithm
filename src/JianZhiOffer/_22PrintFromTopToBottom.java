package JianZhiOffer;

import java.util.ArrayList;
/**
 public class TreeNode {
     int val = 0;
     TreeNode left = null;
     TreeNode right = null;

     public TreeNode(int val) {
     this.val = val;

     }
 }
 */

public class _22PrintFromTopToBottom {
    public static void main(String[] args) {

    }

    public static ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        if(root == null) return res; // 考虑特殊情况

        ArrayList<TreeNode> q = new ArrayList<>(); //用数组模拟队列
        q.add(root);
        while(!q.isEmpty()){
            TreeNode node = q.remove(0);
            res.add(node.val);
            if(node.left != null) q.add(node.left);
            if(node.right != null) q.add(node.right);
        }
        return res;
    }
}
