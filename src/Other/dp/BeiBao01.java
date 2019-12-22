package Other.dp;

public class BeiBao01 {
    /**
     * 动态规划
     * 时间复杂度: O(n*W); 空间复杂度: O(n*W)
     */
    /*
    public int maxValue(int[] weight, int[] value, int W) {
        int n = weight.length;
        if (n == 0) return 0;

        int[][] dp = new int[n + 1][W + 1];
        for (int i = 1; i <= n; i++) {
            for (int k = 1; k <= W; k++) {
                // 存放 i 号物品（前提是放得下这件物品）
                int valueWith_i = (k - weight[i-1] >= 0) ? (value[i-1] + dp[i-1][k-weight[i-1]]) : 0;
                // 不存放 i 号物品
                int valueWithout_i = dp[i - 1][k];
                dp[i][k] = Math.max(valueWith_i, valueWithout_i);
            }
        }

        return dp[n][W];
    }
    */

    // 和上面的版本稍有不同，个人感觉这个写法可读性更好
    public int maxValue(int[] weight, int[] value, int W) {
        int n = weight.length;
        if (n == 0) return 0;

        int[][] dp = new int[n][W + 1];
        // 先初始化第 0 行，也就是尝试把 0 号物品放入容量为 k 的背包中
        for (int k = 1; k <= W; k++) {
            if (k >= weight[0]) dp[0][k] = value[0];
            else dp[0][k] = 0; // 这一步其实没必要写，因为dp[][]数组默认就是0
        }

        for (int i = 1; i < n; i++) {
            for (int k = 1; k <= W; k++) {
                // 存放 i 号物品（前提是放得下这件物品）
                int valueWith_i = (k - weight[i] >= 0) ? (value[i] + dp[i-1][k-weight[i]]) : 0;
                // 不存放 i 号物品
                int valueWithout_i = dp[i-1][k];
                dp[i][k] = Math.max(valueWith_i, valueWithout_i);
            }
        }

        for(int i=0;i<n;i++){
            for(int j=0;j<=W;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[n-1][W];
    }


    /**
     * 动态规划 + 空间压缩
     * 时间复杂度: O(n*W); 空间复杂度: O(W)
     * 从后向前遍历是为了防止被覆盖
     */
    /*
    public int maxValue(int[] weight, int[] value, int W) {
        int n = weight.length;
        if (n == 0) return 0;

        int[] dp = new int[W + 1];
        for (int i = 0; i < n; i++) {
            for (int k = W; k >= 1; k--) {
                int valueWith_i = (k - weight[i] >= 0) ? (dp[k - weight[i]] + value[i]) : 0;
                int valueWithout_i = dp[k];
                dp[k] = Math.max(valueWith_i, valueWithout_i);
            }
        }

        return dp[W];
    }
    */

    /**
     * 更精简版
     */
    /*
    public int maxValue(int[] weight, int[] value, int W) {
        int n = weight.length;
        if (n == 0) return 0;

        int[] dp = new int[W + 1];
        for (int i = 0; i < n; i++) {
            for (int k = W; k >= weight[i]; k--) {
                dp[k] = Math.max(dp[k - weight[i]] + value[i], dp[k]);
            }
        }

        return dp[W];
    }
    */
    public static void main(String[] args) {
        BeiBao01 obj = new BeiBao01();
        int[] w = {1, 3, 1};
        int[] v = {15, 30, 20};
        int W = 4;
        System.out.println(obj.maxValue(w, v, W));
    }
}
