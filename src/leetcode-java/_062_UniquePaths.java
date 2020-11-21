package LeetCode;

public class _062_UniquePaths {
    /**
     * 动态规划v1
     * 时间复杂度: O(m*n + m + n); 空间复杂度: O(m*n)
     *
     * 令 dp[i][j] 表示从起点(0,0)到(i,j)处共有几条不同的路径
     * 初始化dp[0][0...n-1] 和 dp[0...m-1][0]为1
     * 对于任意一点(i,j), 只可能从(i,j-1)向右到达(i,j); 或从(i-1,j)向下到达(i,j)
     * 因此可以推导出状态转移方程为：
     * dp[i][j] = dp[i][j - 1] + dp[i - 1][j]
     */
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];
        // 初始化
        for (int i = 0; i < n; i++) dp[0][i] = 1;
        for (int i = 0; i < m; i++) dp[i][0] = 1;

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        _062_UniquePaths obj = new _062_UniquePaths();
        System.out.println(obj.uniquePaths(3, 2));
        System.out.println(obj.uniquePaths(7, 3));
    }
}
