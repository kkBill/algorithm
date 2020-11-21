package LeetCode;

import java.util.Arrays;


class Solution {
    /*
    定义状态：
        dp[i][j] 表示coins[0...i]中能够凑成金额为 j 所需的最少硬币数
    初始化：
        dp[i][0] = 0, 对于任意 i 都成立，即表示凑成金额为 0 所需的硬币数为 0
        dp[0][j] 分为两种情况，
            (1) 当 j % coins[0] == 0，则 j 可由 j/coins[0] 个面值为coins[0]为的硬币构成，故 dp[0][j] = j/coins[0]
            (2) 当 j % coins[0] != 0，表示不整除，则 j 无法由面值为 coins[0] 的硬币构成，故 dp[0][j] = -1
    状态转移：
        dp[i][j] = min{dp[i-1][j - k×coins[i]] + k} (其中满足，0 ≤ j - k×coins[i] ≤ amount)
        或者
        dp[i][j] = min{dp[i-1][j], dp[i][j-coins[i]] + 1} (满足 0 ≤ j-coins[i] ≤ amount)
                      不选coins[i],           选coins[i]
    */
    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        if(n == 0) return -1;

        int[][] dp = new int[n][amount+1];
        // 初始化
        // dp[i][0] = 0
        for(int j = 1; j <= amount; j++) {
            if(j % coins[0] == 0) dp[0][j] = j / coins[0];
            else dp[0][j] = amount + 1;
        }
        // 状态转移
        for(int i = 1; i < n; i++) {
            for(int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i-1][j]; // 不选
                if(j - coins[i] >= 0) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j-coins[i]] + 1); // 选
                }
            }
        }
        return dp[n-1][amount] == amount+1 ? -1 : dp[n-1][amount];
    }
}

public class _322_CoinChange {

    /**
     * 暴力递归
     */
    /*
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        // 初始化最少次数为amount+1
        int minCnt = amount+1;

        for(int coin : coins) {
            // 如果当前总额已经小于硬币的值了，说明不能基于当前这个硬币继续分解了，因此直接跳过
            if(amount - coin < 0) continue;
            // 计算基于当前这个硬币分解所需要的最少硬币数目
            int subCnt = coinChange(coins, amount-coin);
            if(subCnt == -1) continue;

            minCnt = Math.min(minCnt, subCnt+1);
        }
        return (minCnt == amount+1) ? -1 : minCnt;
    }
    */

    /**
     * 递归 + 备忘录
     */
    /*
    private int[] memo;
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount+1];
        Arrays.fill(memo, Integer.MAX_VALUE);

        return helper(coins, amount);
    }

    private int helper(int[] coins, int amount){
        if(amount == 0) return 0;
        if(memo[amount] != Integer.MAX_VALUE) return memo[amount];

        // 初始化最少次数为amount+1
        int minCnt = amount+1;

        for(int coin : coins) {
            // 如果当前总额已经小于硬币的值了，说明不能基于当前这个硬币继续分解了，因此直接跳过
            if(amount - coin < 0) continue;
            // 计算基于当前这个硬币分解所需要的最少硬币数目
            int subCnt = helper(coins, amount-coin);
            if(subCnt == -1) continue;

            minCnt = Math.min(minCnt, subCnt+1);
        }
        memo[amount] = (minCnt == amount+1) ? -1 : minCnt;
        return memo[amount];
    }
     */

    /**
     * 动态规划
     *
     */
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount+1];
        Arrays.fill(dp, amount+1);
        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0)
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
            }
        }
        return (dp[amount] == amount+1) ? -1 : dp[amount];
    }

    public static void main(String[] args) {
//        _322_CoinChange obj = new _322_CoinChange();
//        int[] coins = {1, 2, 5};
//        int amount = 11;
//        System.out.println(obj.coinChange(coins, amount));
//
//        int[] coins2 = {2};
//        int amount2 = 3;
//        System.out.println(obj.coinChange(coins2, amount2));

        int[] coins = {1, 2, 5};
        int amount = 11;
        Solution solution = new Solution();
        System.out.println(solution.coinChange(coins, amount));
    }
}
