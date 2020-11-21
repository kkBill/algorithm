package LeetCode;

public class _416_PartitionEqualSubsetSum {
    /**
     * 分析：
     * 分割数组为两个子数组，使得子数组的元素之和相等。等价于，子数组的元素之和等于原数组所有元素之和的一半
     * 因此，如果原数组的元素之和为奇数，则一定不存在解。
     * <p>
     * 动态规划
     * 状态：
     * 令dp[i][v] 表示数组nums[0,..,i]是否存在子数组元素之和为 v 的。如果存在则为true, 否则为false
     * <p>
     * 状态转移方程：
     * 对于nums[i]，如果选择它， 即 dp[i][v] = dp[i-1][v-nums[i]]
     * 如果不选择它，即 dp[i][v] = dp[i-1][v]
     * dp[i][v] = dp[i-1][v-nums[i]] || dp[i-1][v]
     */
    /*
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        // 如果数组之和为奇数，则不存在解
        if (sum % 2 == 1) return false;

        int target = sum / 2;

        // 接下来就是“01背包”问题的模板题解
        boolean[][] dp = new boolean[nums.length][target + 1];
        // 初始化第一行
        for (int v = 1; v <= target; v++) {
            if (nums[0] == v) {
                dp[0][v] = true;
                break;
            }
        }
        //
        for (int i = 1; i < nums.length; i++) {
            for (int v = 1; v <= target; v++) {
                boolean ans1 = false;
                if(v-nums[i] >= 0) ans1 = dp[i-1][v-nums[i]];
                dp[i][v] = ans1 || dp[i-1][v];
            }
        }

        return dp[nums.length-1][target];
    }
    */

    /**
     * 动态规划 + 压缩空间
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums)
            sum += num;
        // 如果数组之和为奇数，则不存在解
        if (sum % 2 == 1) return false;

        int target = sum / 2;
        boolean[] dp = new boolean[target + 1];
        // 初始化边界
        dp[0] = true; // 关键
        // 状态转移
        for (int i = 0; i < nums.length; i++) {
            for (int v = target; v >= nums[i]; v--) {
                dp[v] = dp[v-nums[i]] || dp[v];
            }
        }
        return dp[target];
    }

    public static void main(String[] args) {
        _416_PartitionEqualSubsetSum obj = new _416_PartitionEqualSubsetSum();
        int[] nums = {1, 5, 11, 5};
        System.out.println(obj.canPartition(nums));

        int[] nums2 = {1, 2, 3, 5};
        System.out.println(obj.canPartition(nums2));
    }
}
