package LeetCode;

public class _122_BestTimetoBuyandSellStock2 {
    /**
     * 解法 1
     *
     */
    /*
    public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;

        int[][] dp = new int[prices.length][2];
        for(int i=0; i<prices.length; i++){
            if(i == 0){
                dp[i][0] = 0;
                dp[i][1] = -prices[i];
                continue;
            }
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i-1][1], dp[i-1][0] - prices[i]);
        }

        return dp[prices.length-1][0];
    }

    */

    /**
     * 解法 2
     */
    public int maxProfit(int[] prices) {
        int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
        for (int price : prices) {
            dp_i_0 = Math.max(dp_i_0, dp_i_1 + price);
            dp_i_1 = Math.max(dp_i_1, dp_i_0 - price);
        }
        return dp_i_0;
    }

    public static void main(String[] args) {
        _122_BestTimetoBuyandSellStock2 obj = new _122_BestTimetoBuyandSellStock2();
        int[] nums = {7, 1, 5, 3, 6, 4};
        System.out.println(obj.maxProfit(nums));
        int[] nums2 = {1, 2, 3, 4, 5};
        System.out.println(obj.maxProfit(nums2));
        int[] nums3 = {7, 6, 4, 3, 1};
        System.out.println(obj.maxProfit(nums3));

    }
}
