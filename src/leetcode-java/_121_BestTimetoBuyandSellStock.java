package LeetCode;

public class _121_BestTimetoBuyandSellStock {
    /*
    // 暴力法
    // 时间复杂度：O(n^2) 空间复杂度：O(1)
    public int maxProfit(int[] prices) {
        int result = 0;
        for (int i = 0; i < prices.length - 1; i++) {
            for (int j = i + 1; j < prices.length; j++) {
                if (prices[j] - prices[i] > result)
                    result = prices[j] - prices[i];
            }
        }
        return result;
    }
     */

    /**
     * 参考版本，一次遍历即可。
     * 虽然一开始不觉得这是动态规划问题，但看了别人的分析，发现其本质上还是DP的思想
     * 时间复杂度：O(n) 空间复杂度：O(1)
     * 分析：
     * 假设当前在第 i 天，令 minPrice 表示前 i-1 天的最低价格；令 maxProfit 表示前 i-1 天的最大收益
     * 那么第 i 天的最大收益则是 max(maxProfit, prices[i] - minPrice)
     *
     */
    /*
    public int maxProfit(int[] prices) {
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            minPrice = Math.min(minPrice, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - minPrice);
        }
        return maxProfit;
    }
    */

    /**
     * 动态规划（通过框架演变而来...）
     *
     */
    /*
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;

        int[][] dp = new int[prices.length][2];
        for(int i=0;i<prices.length;i++){
            // 处理边界情况
            if(i == 0){
                //dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                //dp[i][0] = Math.max(dp[-1][0], dp[-1][1] + prices[i]) = max(0, -INF + prices[i]) = 0
                dp[i][0] = 0;
                //dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
                //dp[i][1] = Math.max(dp[-1][1], -prices[i]) = max(-INF, -prices[0]) = -prices[0]
                dp[i][1] = -prices[0];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
        }
        return dp[prices.length-1][0];
    }

     */

    public int maxProfit(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for(int price : prices){
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, -price);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        _121_BestTimetoBuyandSellStock obj = new _121_BestTimetoBuyandSellStock();
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};
        System.out.println(obj.maxProfit(prices));
        System.out.println(obj.maxProfit(prices2));
    }
}
