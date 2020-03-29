#### [5. Longest Palindromic Substring](https://leetcode-cn.com/problems/longest-palindromic-substring/)

给定一个字符串 `s`，找到 `s` 中最长的回文子串。你可以假设 `s` 的最大长度为 1000。

```
示例 1：
输入: "babad"
输出: "bab"
注意: "aba" 也是一个有效答案。

示例 2：
输入: "cbbd"
输出: "bb"
```

**方法1：动态规划**

用二维数组`dp[i][j]` 表示s[i...j]是否为回文子串。因此状态表示为：
$$
dp[i][j] =
\begin{cases}
true & s[i..j]为回文子串\\ 
false & 其他\\
\end{cases}
$$
从而状态转移方程：
$$
dp[i][j] = dp[i+1][j-1] \&\& s[i] == s[j]
$$
也就是说，如果s[i]和s[j]相同，那么`dp[i][j]` 是否是回文子串就取决于`dp[i+1][j-1]` 是否为回文子串；如果s[i]和s[j]不同，那么`dp[i][j]` 显然不可能是回文子串。第一次做的时候想出状态转移方程不是件简单的事情，但大概的思路是，**先分析小问题（也就是边界情况） ，再由小问题推导出大问题**。比如，在本题中，边界情况是长度为1和2的子串，对于任何长度为1的子串，显然均为回文子串，即`dp[i][i](0≤i＜n)` 均为true；此外，如果不对长度为2的子串的状态先进行初始化，那么状态转移方程中`dp[i+1][j-1]` 这部分比较难处理，即`dp[i][i+1] = s[i]==s[i+1]`。

第1版代码

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n == 0) return "";

        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int begin = 0, end = 0;

        // 初始化
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
            if(i < n-1) {
                dp[i][i+1] = chars[i] == chars[i+1];
                if(dp[i][i+1] && end - begin < 1) {
                    begin = i;
                    end = i+1;
                }
            }
        }
        // 状态转移
        for(int len = 2; len < n; len++) {
            for(int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = dp[i+1][j-1] && chars[i] == chars[j];
                if(dp[i][j] && j - i > end - begin) {
                    begin = i;
                    end = j;
                }
            }
        }

        return s.substring(begin, end+1);
    }
}
```

第2版代码

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n == 0) return "";

        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int begin = 0, end = 0;
        // 初始化 + 状态转移
        for(int len = 1; len <= n; len++) {
            for(int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                if(i == j) {
                    dp[i][j] = true;
                    continue;
                }
                
                if(i + 1 == j) {
                    dp[i][j] = chars[i] == chars[j];
                    if(dp[i][j] && j - i > end - begin) {
                        begin = i;
                        end = j;
                    }
                    continue;
                }
                
                dp[i][j] = dp[i+1][j-1] && chars[i] == chars[j];
                if(dp[i][j] && j - i > end - begin) {
                    begin = i;
                    end = j;
                }
            }
        }
        return s.substring(begin, end+1);
    }
}
```

第3版代码

```java
class Solution {
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n == 0) return "";

        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int begin = 0, end = 0;
        // 初始化 + 状态转移
        for(int len = 1; len <= n; len++) {
            for(int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                
                dp[i][j] = (i == j) 
                            || (i + 1 == j && chars[i] == chars[j]) 
                            || (dp[i+1][j-1] && chars[i] == chars[j]);
                
                if(dp[i][j] && j - i > end - begin) {
                    begin = i;
                    end = j;
                }
            }
        }
        return s.substring(begin, end+1);
    }
}
```

复杂度分析

* 时间复杂度：O(n^2)，双层for循环
* 空间复杂度：O(n^2)，dp二维数组





**方法2：中心扩展算法**

该算法可以把空间复杂度优化至O(1)。

