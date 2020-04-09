#### [63. Unique Paths II](https://leetcode-cn.com/problems/unique-paths-ii/)

在第62题基础上，矩阵中加入了障碍物，但是思考模式还是一样的。

```
输入:
[
  [0,0,0],
  [0,1,0],
  [0,0,0]
]
输出: 2
解释:
3x3 网格的正中间有一个障碍物。
从左上角到右下角一共有 2 条不同的路径：
1. 向右 -> 向右 -> 向下 -> 向下
2. 向下 -> 向下 -> 向右 -> 向右
```



第1版

```java
class Solution {
    public int uniquePathsWithObstacles(int[][] grid) {
        int m = grid.length;
        if(m == 0) return 0;
        int n = grid[0].length;
        
        int[][] dp = new int[m][n];
        // 初始化
        for(int j = 0; j < n; j++) {
            if(grid[0][j] == 1) break;
            dp[0][j] = 1;
        }
        for(int i = 0; i < m; i++) {
            if(grid[i][0] == 1) break;
            dp[i][0] = 1;
        }
        // 状态转移
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(grid[i][j] == 1) {
                    dp[i][j] = 0;
                    continue;
                }
                dp[i][j] = dp[i-1][j] + dp[i][j-1];
            }
        }
        return dp[m-1][n-1];        
    }
}
```

