package LeetCode;

public class _543_DiameterofBinaryTree {
    private int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depthOfTree(root);
        return maxDiameter;
    }

    private int depthOfTree(TreeNode root) {
        if(root == null) return 0;
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        maxDiameter = Math.max(maxDiameter, left + right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        _543_DiameterofBinaryTree obj = new _543_DiameterofBinaryTree();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.diameterOfBinaryTree(root));
    }
}
