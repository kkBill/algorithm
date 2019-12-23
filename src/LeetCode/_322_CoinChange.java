package LeetCode;

import java.util.Arrays;

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
        _322_CoinChange obj = new _322_CoinChange();
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(obj.coinChange(coins, amount));

        int[] coins2 = {2};
        int amount2 = 3;
        System.out.println(obj.coinChange(coins2, amount2));
    }
}
