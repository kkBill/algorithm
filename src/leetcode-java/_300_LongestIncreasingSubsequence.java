package LeetCode;

public class _300_LongestIncreasingSubsequence {
    /**
     * 动态规划
     * 两层循环，时间复杂度: O(n^2); 空间复杂度: O(n)
     * 分析：
     * 令 dp[i] 表示以 nums[i] 结尾的最长上升子序列的长度
     * 初始化 dp[i] = 1 (i=0,1,...,n-1), 表示每个数字单独称为一个字串
     * 对于任意位置 i,
     * 1) 如果 nums[i] 比所有 nums[j] (j=0,1,...,i-1) 都小，那么它只能单独成为一个子串了
     * 2) 如果存在 nums[i] > nums[j] (j=0,1,...,i-1) 且 dp[j] + 1 > dp[i]，那么把 nums[i] 跟在 nums[j] 之后
     *    对应的最长上升子序列的长度加1，即 dp[i] = dp[j] + 1
     */
    /*
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int result = 0;
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
            result = Math.max(result, dp[i]);
        }
        return result;
    }
    */

    /**
     * 动态规划+二分查找
     * 时间复杂度: O(nlogn); 空间复杂度: O(n)
     */
    public int lengthOfLIS(int[] nums) {
        int[] tails = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = 0, j = len;
            while (i < j) {
                int m = (i + j) / 2;
                if (tails[m] < num) i = m + 1;
                else j = m;
            }
            tails[i] = num;
            if (len == j) len++;
        }

        for(int num : tails){
            System.out.print(num + " ");
        }
        System.out.println();

        return len;
    }

    public static void main(String[] args) {
        _300_LongestIncreasingSubsequence obj = new _300_LongestIncreasingSubsequence();
        int[] nums = {10, 9, 2, 5, 3, 7, 21, 4};
        int[] nums2 = {10, 9, 2, 5, 3, 7, 101, 1};
        System.out.println(obj.lengthOfLIS(nums));
        System.out.println(obj.lengthOfLIS(nums2));
    }
}
