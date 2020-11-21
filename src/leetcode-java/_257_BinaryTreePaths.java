package LeetCode;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

public class _257_BinaryTreePaths {
    /**
     * 深度优先遍历
     */
//    public List<String> binaryTreePaths(TreeNode root) {
//        List<String> result = new ArrayList<>();
//        if(root == null) return result;
//
//        dfs(root, new ArrayList<>(), result);
//        return result;
//    }
//
//    private void dfs(TreeNode root, List<Integer> path, List<String> result) {
//        path.add(root.val);
//
//        if (root.left == null && root.right == null) {
//            StringBuilder sb = new StringBuilder();
//            for (int i = 0; i < path.size(); i++) {
//                sb.append(path.get(i));
//                if (i != path.size() - 1) sb.append("->");
//            }
//            if (!result.contains(sb.toString()))
//                result.add(sb.toString());
//        }
//
//        if(root.left != null)
//            dfs(root.left, path, result);
//        if(root.right != null)
//           dfs(root.right, path, result);
//        path.remove(path.size()-1);
//    }

    private List<String> res = new ArrayList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        getPaths(root, new StringBuilder());
        return res;
    }

    private void getPaths(TreeNode node, StringBuilder path){
        if(node!=null){
            path.append(node.val);
            if(node.left==null && node.right==null) {
                res.add(path.toString());
            }else {
                path.append("->");
                getPaths(node.left, path);
                getPaths(node.right, path);
            }
        }
    }

    public static void main(String[] args) {
        _257_BinaryTreePaths obj = new _257_BinaryTreePaths();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.binaryTreePaths(root));
    }
}
