package LeetCode;

public class _063_UniquePaths2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;
        int n = obstacleGrid[0].length;
        int[][] dp = new int[m][n];

        // 初始化
        int k = 0;
        while (k < n && obstacleGrid[0][k] != 1) dp[0][k++] = 1;
        k = 0;
        while (k < m && obstacleGrid[k][0] != 1) dp[k++][0] = 1;

        // 状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if(obstacleGrid[i][j - 1] != 1) dp[i][j] += dp[i][j - 1];
                if(obstacleGrid[i - 1][j] != 1) dp[i][j] += dp[i - 1][j];
                if(obstacleGrid[i][j] == 1) dp[i][j] = 0;
            }
        }
        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        _063_UniquePaths2 obj = new _063_UniquePaths2();
        int[][] grid = {
                {0, 0, 0},
                {0, 1, 0},
                {0, 0, 0}};
        System.out.println(obj.uniquePathsWithObstacles(grid));

        int[][] grid2 = {
                {0, 1, 0},
                {0, 0, 0},
                {0, 0, 0}};
        System.out.println(obj.uniquePathsWithObstacles(grid2));

        int[][] grid3 = {
                {0, 0, 0},
                {0, 0, 0},
                {1, 0, 0}};
        System.out.println(obj.uniquePathsWithObstacles(grid3));

        int[][] grid4 = {
                {0, 0},
                {0, 1}};
        System.out.println(obj.uniquePathsWithObstacles(grid4));
    }
}
