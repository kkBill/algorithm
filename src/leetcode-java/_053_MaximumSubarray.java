package LeetCode;

public class _053_MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        int result = nums[0];
        // 边界
        dp[0] = nums[0];
        // 递推
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1] + nums[i], nums[i]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    public static void main(String[] args) {
        _053_MaximumSubarray obj = new _053_MaximumSubarray();
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        int[] nums2 = {-1, -1, -1, -1};
        System.out.println(obj.maxSubArray(nums));
        System.out.println(obj.maxSubArray(nums2));
    }
}
