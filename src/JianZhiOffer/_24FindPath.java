package JianZhiOffer;

import java.util.ArrayList;

public class _24FindPath {
    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(6);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(1);
        root.left.left.right = new TreeNode(7);
        root.right.right = new TreeNode(5);

        System.out.println(FindPath(root,11)); // 为什么这里输出 res 就为空了？
    }

    public static ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if(root == null) return res;

        ArrayList<Integer> path = new ArrayList<>();
        DFS(root, res, path, 0, target);

        return res;
    }

    public static void DFS(TreeNode root, ArrayList<ArrayList<Integer>> res, ArrayList<Integer> path, int current, int target) {
        path.add(root.val);
        current += root.val;

        if (root.left == null && root.right == null && current == target) {
            ArrayList<Integer> tmpPath = new ArrayList<>(path);
            res.add(tmpPath); // 我就是想把符合条件的路径存到 res 中。但是到外面怎么就为空了？
        }
        if(root.left != null)
            DFS(root.left, res, path, current, target);
        if(root.right != null)
            DFS(root.right, res, path, current, target);

        current -= root.val;
        path.remove(path.size() - 1);
    }
}