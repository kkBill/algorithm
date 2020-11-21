package LeetCode;

import java.util.*;

public class _113_PathSum2 {

      // 递归
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        process(root, sum, path, result);
        return result;
    }

    private void process(TreeNode node, int sum, List<Integer> path, List<List<Integer>> result) {
        path.add(node.val);

        if (node.left == null && node.right == null && sum == node.val) {
            result.add(new ArrayList<>(path)); // 注意，不能写成 result.add(path); java 引用问题
            return;
        }

        if (node.left != null) {
            process(node.left, sum - node.val, path, result);
            path.remove(path.size() - 1); // 关键！
        }
        if (node.right != null) {
            process(node.right, sum - node.val, path, result);
            path.remove(path.size() - 1);
        }
    }


    // 迭代
    /*
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return new ArrayList<>();

        Stack<TreeNode> s = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        TreeNode p = root;
        TreeNode lastVisitNode = null;
        while(p != null || !s.empty()){
            if(p != null){
                s.push(p);
                path.add(p.val);
                sum -= p.val;

                p = p.left;
            }
            else{
                TreeNode tmp = s.peek();
                if(tmp.right != null && tmp.right != lastVisitNode){
                    p = tmp.right;
                }else{
                    // 检查当前节点是否符合条件
                    if(tmp.left == null && tmp.right == null && sum == 0){
                        //System.out.println(path);
                        result.add(new ArrayList<>(path));
                    }
                    s.pop();
                    path.remove(path.size()-1); // 关键
                    sum += tmp.val; // 关键

                    lastVisitNode = tmp;
                    p = null; // 关键
                }
            }
        }
        return result;
    }
    */

    public static void main(String[] args) {
        TreeNode root = TreeNode.makeTree();
        _113_PathSum2 obj = new _113_PathSum2();
        System.out.println(obj.pathSum(root, 11));
    }
}
