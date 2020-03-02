package LeetCode;

public class _222_CountCompleteTreeNodes {
    // 对于任意二叉树都适用
    // 是一种普适的做法，类似的还有求树的高度
    /*
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
    */

    // 击败100%
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        TreeNode leftNode = root, rightNode = root;
        // 分别记录左子树的最左侧和右子树的最右侧的高度
        // 注意这里不完全等价于树的高度
        int left = 0, right = 0;
        while (leftNode != null){
            left++;
            leftNode = leftNode.left;
        }
        while (rightNode != null){
            right++;
            rightNode = rightNode.right;
        }
        // 判断是否是满二叉树，如果是，则可以直接根据其性质计算出树的高度
        // 假设满二叉树的高度为h，则节点个数n = 2^h - 1
        if(left == right) return (1 << left) - 1;
        return countNodes(root.left) + countNodes(root.right) + 1;
    }

    public static void main(String[] args) {
        _222_CountCompleteTreeNodes obj = new _222_CountCompleteTreeNodes();
        TreeNode root = TreeNode.makeTree();
        System.out.println(obj.countNodes(root));
    }
}
