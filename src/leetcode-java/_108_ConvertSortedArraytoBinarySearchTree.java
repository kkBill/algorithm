package LeetCode;

public class _108_ConvertSortedArraytoBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return build(nums, 0, nums.length - 1);
    }

    private TreeNode build(int[] nums, int low, int high) {
        if (low > high) return null;
        int mid = (high - low) / 2 + low;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = build(nums, low, mid - 1);
        root.right = build(nums, mid + 1, high);
        return root;
    }

    public static void main(String[] args) {
        _108_ConvertSortedArraytoBinarySearchTree obj = new _108_ConvertSortedArraytoBinarySearchTree();
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        TreeNode root = obj.sortedArrayToBST(nums);
    }
}
