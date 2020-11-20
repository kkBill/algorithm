#### [516. 最长回文子序列](https://leetcode-cn.com/problems/longest-palindromic-subsequence/)

本题求最长回文子序列，注意**子序列**和**子串**的不同。**子串要求连续，而子序列不要求连续**。因此，两者的思考方式会有不同。本题只需要返回最长回文子序列的长度，而不需要返回具体的子序列是什么。

**定义`dp[i][j]`表示s[i...j]中的回文子序列的长度**，比如下图中，`dp[i+1][j-1]=3`，因为在这区间范围内，“bab”可以构成回文子序列。

<img src="https://pic.leetcode-cn.com/2ff90cfc4c93a2c51030ce621bf6871977f930851496921b8b8ce54be021042b-file_1578114396873" alt="img" style="zoom:40%;" />

那么如何定义状态转移方程呢？这样来进行考虑：

* 如果s[i] == s[j]，那么 `dp[i][j] = dp[i+1][j-1] + 2`，如下图所示。`dp[i][j] = 5`，因为“cbabc”是这一范围内的回文子序列。

<img src="https://pic.leetcode-cn.com/38ae4454d8972b16301c30d942582c661837668225b280a1f79862d6f86bc756-file_1578114396868" alt="img2" style="zoom:40%;" />

* 如果s[i] != s[j]，那么s[i...j]范围内的最长回文子序列和s[i+1...j-1]一样，即`dp[i][j] = dp[i+1][j-1]`，但是s[i...j-1]和s[i+1...j]范围内的最长回文子序列说不定会发生变化。因此，这一情况下的状态转移方程应该为： `dp[i][j] = max(dp[i][j-1],dp[i+1][j])` 。 如下图所示：

<img src="https://pic.leetcode-cn.com/d4139f3ab5d52728f7d839e563d1f6586ca183de4622e6a6ec168d1503355195-file_1578114396872" alt="img3" style="zoom:40%;" />

综上分析：
$$
dp[i][j] = 
\begin {cases}
dp[i+1][j-1] + 2 & s[i] == s[j] \\
max(dp[i+1][j],dp[i][j-1]) & s[i] != s[j]\\
\end {cases}
$$
推导出了状态转移方程，接下来就是怎么实现的问题了。首先，需要明确，由于`d[i][j]` 表示的是s[i...j]之间的回文子序列的长度，因此满足` i ≤ j`。另外，观察递推方程可知，想要求出`dp[i][j]`，需要知道`dp[i+1][j-1], dp[i+1][j], dp[i][j-1] `，即需要知道它的左侧，下侧和左下角，如下图所示：

<img src="https://pic.leetcode-cn.com/3ffa562fae83741ca7b2fed5a9528c28efb849232095251a3b829d5ea276edcc-file_1578114396879" alt="img4" style="zoom:40%;" />

对此，有两种遍历方式：

<img src="https://pic.leetcode-cn.com/3a0a09ded5c0027d9645282b48e1dec2c915efec605ed46e9c311f9b68e06672-file_1578114396880" alt="img5" style="zoom:40%;" />

方式1：

```java
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];
        char[] ss = s.toCharArray();
        // 初始化
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 状态变化
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(ss[i] == ss[j]) {
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
                }
            }
        }
        return dp[0][n-1];
    }
}
```



方式2：即 i 从大到小(对应红线)，j 从小到大(对应蓝线)进行遍历。

```java
// 时间复杂度：O(n^2)
// 空间复杂度：O(n^2)
class Solution {
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        if(n == 0) return 0;
        
        int[][] dp = new int[n][n];
        // 初始化, base cases
        for(int i = 0; i < n; i++) {
            dp[i][i] = 1;
        }
        // 状态转移
        char[] chars = s.toCharArray();
        for(int i = n-1; i >= 0; i--) {
            for(int j = i+1; j < n; j++) {
                if(chars[i] == chars[j]) { // 比直接用s.charAt(i)更快啊
                    dp[i][j] = dp[i+1][j-1] + 2;
                }else {
                    dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                }
            }
        }
        return dp[0][n-1];
    }
}
```

注：图片来自[这里](https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/zi-xu-lie-wen-ti-tong-yong-si-lu-zui-chang-hui-wen/)。



