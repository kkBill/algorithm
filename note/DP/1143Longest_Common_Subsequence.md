#### [1143. Longest Common Subsequence](https://leetcode-cn.com/problems/longest-common-subsequence/)

求两个字符串的最长公共子序列。

分析：令`dp[i][j]`表示s1[0...i]和s2[0...j]的最长公共子序列的长度。
$$
dp[i][j] = 
\begin {cases}
dp[i-1][j-1] + 1 & s[i] == s[j] \\
max(dp[i-1][j],dp[i][j-1]) & s[i] != s[j]\\
\end {cases}
$$
方法1：动态规划

```java
// 时间复杂度：O(n*m)
// 空间复杂度：O(n*m)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        if(n == 0 || m == 0) return 0;
        
        int[][] dp = new int[n+1][m+1];
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[n][m];
    }
}
```



方法2：利用滚动数组优化辅助空间

![img](https://pic.leetcode-cn.com/df4dec97e0ea8288172a681e6b90f42698115a47aae3a3fb99a5b6129967ce6c-file_1578114778873)

优化后只需要一维DP即可解决问题。一开始直接就写优化的方法比较难，在写出常规的动态规划方法后，再对照着进行优化。

* `topleft` 位于`dp[j]` 的左上角，相当于原`dp[i-1][j-1]`
* `up`位于`dp[j]`的上方，相当于原`dp[i-1][j]`
* `dp[j-1]` 位于`dp[j]`的左侧 ，相当于原`dp[i][j-1]` 

注意：每一行开始的时候把`topleft`初始化为0；并在向右移动的过程中记得更新`topleft`为`up`。 

```java
// 时间复杂度：O(n*m)
// 空间复杂度：O(m)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length(), m = text2.length();
        if(n == 0 || m == 0) return 0;
        
        int[] dp = new int[m+1];
        int topleft = 0, up = 0;
        for(int i = 1; i <= n; i++) {
            topleft = 0;
            for(int j = 1; j <= m; j++) {
                up = dp[j];
                if(text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[j] = topleft + 1;
                }else {
                    dp[j] = Math.max(up, dp[j-1]);
                }
                topleft = up; // 注意更新
            }
        }
        return dp[m];
    }
}
```



