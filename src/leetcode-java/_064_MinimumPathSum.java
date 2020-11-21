package LeetCode;

public class _064_MinimumPathSum {
    /**
     * 动态规划
     * 令dp[i][j]表示从(0,0)到(i,j)处的最小路径和
     * 初始化dp[0][i] = grid[0][0] + grid[0][1] +...+ grid[0][i]
     * dp[i][0] = grid[0][0] + grid[1][0] +...+ grid[i][0]
     * 对于任意一点(i,j)
     * dp[i][j] = grid[i][j] + min(dp[i][j-1], dp[i-1][j])
     *
     * 这里可以直接在grid[][]网格上进行更新
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        if (m == 0) return 0;
        int n = grid[0].length;

        // 初始化
        for (int i = 1; i < n; i++) grid[0][i] += grid[0][i - 1];
        for (int i = 1; i < m; i++) grid[i][0] += grid[i - 1][0];

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i][j] + Math.min(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[m - 1][n - 1];
    }

    public static void main(String[] args) {
        _064_MinimumPathSum obj = new _064_MinimumPathSum();
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}};
        System.out.println(obj.minPathSum(grid));

        int[][] grid2 = {
                {1, 2, 5},
                {3, 2, 1}};
        System.out.println(obj.minPathSum(grid2));
    }
}
