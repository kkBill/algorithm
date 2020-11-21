package LeetCode;

import java.util.Arrays;

public class _123_BestTimetoBuyandSellStock3 {

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int K = 2;
        int[][][] dp = new int[prices.length][K + 1][2];
        for (int i = 0; i < prices.length; i++) {
            for (int k = 1; k <= K; k++) {
                if (i == 0) {
                    dp[i][k][0] = 0;
                    dp[i][k][1] = -prices[i];
                    continue;
                }
                dp[i][k][0] = Math.max(dp[i - 1][k][0], dp[i - 1][k][1] + prices[i]);
                dp[i][k][1] = Math.max(dp[i - 1][k][1], dp[i - 1][k - 1][0] - prices[i]);
            }
        }
        return dp[prices.length - 1][K][0];
    }

    public static void main(String[] args) {
        _123_BestTimetoBuyandSellStock3 obj = new _123_BestTimetoBuyandSellStock3();
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        int[] prices2 = {1, 2, 3, 4, 5};
        int[] prices3 = {7, 6, 4, 3, 1};
        System.out.println(obj.maxProfit(prices));
        System.out.println(obj.maxProfit(prices2));
        System.out.println(obj.maxProfit(prices3));
    }
}
