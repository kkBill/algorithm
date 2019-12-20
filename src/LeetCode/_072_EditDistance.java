package LeetCode;

public class _072_EditDistance {
    /**
     * 动态规划
     * 时间复杂度: O(m*n); 空间复杂度: O(m*n)
     *
     *
     */
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        if (m == 0) return n;
        if (n == 0) return m;

        int[][] dp = new int[m + 1][n + 1];
        // 初始化边界
        for (int i = 0; i <= n; i++) dp[0][i] = i;
        for (int i = 0; i <= m; i++) dp[i][0] = i;

        // 状态转移
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i][j - 1], dp[i - 1][j])) + 1;
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        _072_EditDistance obj = new _072_EditDistance();
        System.out.println(obj.minDistance("horse", "ros"));
        System.out.println(obj.minDistance("intention", "execution"));
        System.out.println(obj.minDistance("", "hello"));
    }
}
