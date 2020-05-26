#### [221. Maximal Square](https://leetcode-cn.com/problems/maximal-square/)

在一个由 0 和 1 组成的二维矩阵内，找到**只包含 1 的最大正方形**，并返回其面积。

```
输入: 
1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

输出: 4
```

分析：第一遍自己做毫无思路，但是看了题解又觉得很简单。

最关键的是理解下面这张图：

![Max Square](https://leetcode.com/media/original_images/221_Maximal_Square.PNG?raw=true)

令`dp[i][j]`表示以位置`(i,j)`为右下角可形成的最大正方形的边长。根据上图，可推导出状态转移方程：

```
dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1;
```

方法1：动态规划

```java
// 时间复杂度：O(m*n)
// 空间复杂度：O(m*n)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) return 0;
        int n = matrix[0].length;
        
        // dp[i][j]表示已(i,j)为右下角可形成的最大正方形的宽度
        int[][] dp = new int[m+1][n+1];
        int maxLen = 0;
        // 状态转移
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(matrix[i-1][j-1] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1]) + 1;
                }else {
                    dp[i][j] = 0;
                }
                maxLen = Math.max(maxLen, dp[i][j]);
            }
        }
        return maxLen * maxLen;
    }
}
```



方法2：滚动数组空间优化

![ Max Square ](https://leetcode.com/media/original_images/221_Maximal_Square1.png?raw=true)

观察到上面的递推方程，更新`dp`数组仅与其前一层的数组有关，因此可以把二维dp降为一维dp。这里特别需要注意的是左上角那个值的变化。

```java
// 时间复杂度：O(m*n)
// 空间复杂度：O(n)
class Solution {
    public int maximalSquare(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) return 0;
        int n = matrix[0].length;
        
        int[] dp = new int[n+1];
        int maxLen = 0;
        // 状态转移
        for(int i = 1; i <= m; i++) {
          	int prev = 0; // 代表原二维dp中的dp[i-1][j-1]
            for(int j = 1; j <= n; j++) {
                int temp = dp[j]; // 关键，由于dp[j]会被覆盖，故先暂存起来
                if(matrix[i-1][j-1] == '1') {
                    dp[j] = Math.min(Math.min(dp[j-1], dp[j]), prev) + 1;
                }else {
                    dp[j] = 0;
                }
                prev = temp;
                maxLen = Math.max(maxLen, dp[j]);
            }
        }
        return maxLen * maxLen;
    }
}
```

