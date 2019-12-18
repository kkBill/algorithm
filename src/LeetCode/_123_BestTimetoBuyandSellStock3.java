package LeetCode;

import java.util.Arrays;

public class _123_BestTimetoBuyandSellStock3 {

    public int maxProfit(int[] prices) {
        if (prices.length == 0) return 0;

        int i = 0, k = 0;
        int[] result = new int[prices.length];
        int valley = prices[0], peek = prices[0];
        while (i < prices.length - 1) {
            while (i < prices.length - 1 && prices[i + 1] <= prices[i])
                i++;
            valley = prices[i];
            while (i < prices.length - 1 && prices[i + 1] >= prices[i])
                i++;
            peek = prices[i];
            result[k++] = peek - valley;
        }

        Arrays.sort(result);
        int maxProfit = 0, cnt = 1;
        for (int j = result.length - 1; j >= 0 && cnt <= 2; j--) {
            maxProfit += result[j];
            cnt++;
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        _123_BestTimetoBuyandSellStock3 obj = new _123_BestTimetoBuyandSellStock3();
        int[] prices = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {1, 2, 3, 4, 5};
        int[] prices3 = {1, 2, 4, 2, 5, 7, 2, 4, 9, 0};
        System.out.println(obj.maxProfit(prices));
        System.out.println(obj.maxProfit(prices2));
        System.out.println(obj.maxProfit(prices3));
    }
}
