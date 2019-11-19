package LeetCode;

import java.util.Stack;

public class _112_PathSum {

    // 递归
    /*
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum)
            return true;
        return hasPathSum(root.left, sum - root.val) ||
                hasPathSum(root.right, sum - root.val);
    }
    */


    // 迭代
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        Stack<TreeNode> s = new Stack<>();
        TreeNode lastVisitNode = null;
        TreeNode p = root;
        boolean result = false;

        while(p != null || !s.empty()){
            if(p != null){
                s.push(p);
                sum -= p.val;

                p = p.left;
            }else{
                TreeNode tmp = s.peek();
                if(tmp.right != null && lastVisitNode != tmp.right){
                    p = tmp.right;
                }else{
                    if(tmp.left == null && tmp.right == null && sum == 0){
                        result = true;
                        break;
                    }

                    // visit(tmp)
                    s.pop();
                    sum += tmp.val; //关键

                    lastVisitNode = tmp;
                    p = null; //关键
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = TreeNode.makeTree();
        _112_PathSum obj = new _112_PathSum();
        System.out.println(obj.hasPathSum(root, 11));
    }
}