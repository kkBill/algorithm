package LeetCode;

public class _188_BestTimetoBuyandSellStock4 {

    public int maxProfitWithInfK(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, dp_i_0 - price);
        }
        return dp_i_0;
    }

    public int maxProfit(int K, int[] prices) {
        if (prices.length == 0) return 0;
        if (K > prices.length / 2) return maxProfitWithInfK(prices);


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
        _188_BestTimetoBuyandSellStock4 obj = new _188_BestTimetoBuyandSellStock4();
        int[] prices = {2, 4, 1};
        int K = 2;
        System.out.println(obj.maxProfit(K, prices));
        prices = new int[]{3, 2, 6, 5, 0, 3};
        System.out.println(obj.maxProfit(K, prices));
    }
}
