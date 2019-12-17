package LeetCode;

/**
 * dp[i] 表示数组 nums[0...i] 范围内在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 *
 */
public class _198_HouseRobber {
    /*
    // 空间复杂度为 O(N) 的做法
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]); // 关键
        //int result = Math.max(dp[0], dp[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
            //result = Math.max(result, dp[i]);
        }
        return dp[nums.length-1];
    }
    */

    // 空间复杂度为 O(1) 的做法
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;

        int pre = 0, cur = nums[0], tmp;
        for(int i=1; i<nums.length;i++){
            tmp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = tmp;
        }
        return cur;
    }

    public static void main(String[] args) {
        _198_HouseRobber obj = new _198_HouseRobber();
        int[] nums = {1, 2, 3, 1};
        int[] nums2 = {2, 7, 9, 3, 1};
        int[] nums3 = {2, 1, 1, 2};
        System.out.println(obj.rob(nums));
        System.out.println(obj.rob(nums2));
        System.out.println(obj.rob(nums3));
    }
}