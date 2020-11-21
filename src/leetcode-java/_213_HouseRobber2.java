package LeetCode;

import java.util.Arrays;

public class _213_HouseRobber2 {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max( myRob(Arrays.copyOfRange(nums,0,nums.length-1)),
                         myRob(Arrays.copyOfRange(nums,1,nums.length)));
    }

    private int myRob(int[] nums) {
        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]); // 关键
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length-1];
    }

    public static void main(String[] args) {
        _213_HouseRobber2 obj = new _213_HouseRobber2();
        int[] nums = {2, 3, 2};
        int[] nums2 = {1, 2, 3, 1};
        int[] nums3 = {1};
        System.out.println(obj.rob(nums));
        System.out.println(obj.rob(nums2));
        System.out.println(obj.rob(nums3));
    }
}
