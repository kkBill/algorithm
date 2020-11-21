package LeetCode;

public class _312_BurstBalloons {
    /**
     * “区间DP问题”，看题解的，自己想不到~~~（ps: 什么是区间DP问题？）
     * <p>
     * 令dp[i][j]表示捏爆区间nums[i...j]之间所有气球可获得的最大价值，那么最终的结果就是dp[1][n]
     * 为什么不是dp[0][n-1]，因为在nums[]数组的前后分别加了一个值，相当于哨兵
     * 因为在处理两端时，以乘以 1 处理，因此在原数组的始/末添加1
     * <p>
     * 对于区间[i,j]，假设k(i≤k≤j)是最后一个被捏爆的，那么有：
     * dp[i][j] = max(dp[i][k-1] + nums[i-1]*nums[k]*nums[j+1] + dp[k+1][j]) (i≤k≤j)
     *
     * 这里特别注意 nums[i-1]*nums[k]*nums[j+1] !!!
     * 这里很容易写成 nums[k-1]*nums[k]*nums[k+1] ,但这样是错的!
     * 为什么呢? 注意这里的定义——dp[i][j]表示捏爆区间nums[i...j]之间“所有气球”可获得的最大价值
     * 当“最后”被捏爆的气球是第k个时，区间[i..j]中的其他气球已经没了，所以此时与第k个气球相邻的是(i-1)和(j+1), 而不是(k-1)和(k+1)
     * <p>
     * 时间复杂度：O()
     * 空间复杂度：O()
     */
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[] nums2 = new int[n + 2];
        nums2[0] = nums2[n + 1] = 1;
        System.arraycopy(nums, 0, nums2, 1, n);

        int[][] dp = new int[n + 2][n + 2];
        for (int step = 1; step <= n; step++) {
            for (int begin = 1; begin <= n - step + 1; begin++) {
                int end = begin + step - 1;
                for (int k = begin; k <= end; k++) {
                    dp[begin][end] = Math.max(dp[begin][end], dp[begin][k - 1] + dp[k + 1][end] + nums2[begin - 1] * nums2[k] * nums2[end + 1]);
                }
            }
        }
        return dp[1][n];
    }

    public static void main(String[] args) {
        _312_BurstBalloons obj = new _312_BurstBalloons();
        int[] nums = {3, 1, 5, 8};
        System.out.println(obj.maxCoins(nums));
    }
}
