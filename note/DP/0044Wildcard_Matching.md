#### [44. Wildcard Matching](https://leetcode-cn.com/problems/wildcard-matching/)

给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。

* '?' 可以匹配任何单个字符。
* '*' 可以匹配任意字符串（包括空字符串）。

两个字符串完全匹配才算匹配成功。

说明:

* s 可能为空，且只包含从 a-z 的小写字母。
* p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。

```
示例 1:
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:
输入:
s = "aa"
p = "*"
输出: true
解释: '*' 可以匹配任意字符串。

示例 3:
输入:
s = "cb"
p = "?a"
输出: false
解释: '?' 可以匹配 'c', 但第二个 'a' 无法匹配 'b'。

示例 4:
输入:
s = "adceb"
p = "*a*b"
输出: true
解释: 第一个 '*' 可以匹配空字符串, 第二个 '*' 可以匹配字符串 "dce".

示例 5:
输入:
s = "acdcb"
p = "a*c?b"
输入: false
```

分析：这一题和第10题（正则表达式的匹配）很像，主要的难点还是在于`'*'`的处理。

方法1：暴力递归（会超时）

```java
class Solution {
    public boolean isMatch(String s, String p) {
        if(p.length() == 0) return s.length() == 0;
        if(p.charAt(0) == '*') {
            return isMatch(s, p.substring(1)) || (s.length() > 0 && isMatch(s.substring(1), p));
        }
        if(s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '?')) {
            return isMatch(s.substring(1), p.substring(1));
        }
        return false;
    }
}
```



方法1：递归 + 备忘录

```java
class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length()+1][p.length()+1];
        return helper(s, 0, p, 0, memo);
    }
    
    private boolean helper(String s, int si, String p, int pi, Boolean[][] memo) {
        if(pi == p.length()) return si == s.length();
        
        if(memo[si][pi] != null) return memo[si][pi];
        
        if(p.charAt(pi) == '*') {
            memo[si][pi] = helper(s, si, p, pi+1, memo) // '*'代表空字符串
                || (si < s.length() && helper(s, si+1, p, pi, memo)); // '*'代表一个字符
            return memo[si][pi];
        }
        
        if(si < s.length() && (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '?')) {
            memo[si][pi] = helper(s, si+1, p, pi+1, memo);
            return memo[si][pi];
        }
        
        memo[si][pi] = false;
        return memo[si][pi];
    }
}
```



方法2：动态规划（从左至右）

##### 状态

`dp[i][j]`表示字符串 s 前 i 个字符与模式串 p 前 j 个字符是否匹配，因此最终的结果就是`dp[m][n]`，其中 m 是字符串 s 的长度，n 是模式串 p 的长度。

##### 初始化

* `dp[0][0] = true `， 即空字符串 s 和空字符串 p 显然是匹配的；
* `dp[1...m][0] = false`， 即字符串 s 非空，而模式串 p 为空，显然这种情况均为不匹配；
* `dp[0][1...n] = 待定`， 即字符串 s 为空，而模式串 p 非空。想要`dp[0][j]`匹配，则必须满足模式串 p 的前 j 个字符均为 `*`，因为`*`可以匹配空字符串；而一旦某个位置`p[k]`出现非`*`字符，那么`dp[0][k..n]=false`。

##### 状态转移

对于`dp[i][j]`的更新，和使用递归方法类似，分为两种情况。

* 如果`s[i] == p[j]` 或者 `p[j] == '?'`， 则`dp[i][j] =dp[i-1][j-1] `
* 如果`p[j] == '*'`， 则需要考虑两种情况
  * case 1：假定`*`匹配空字符串，则`dp[i][j] = dp[i][j-1]`。换句话说，s 的前 i 个字符与 p 的前 (j-1) 个字符匹配了，那么，就等价于 s 的前 i 个字符与 p 的前 j 个字符匹配了，因为此时`p[j]`位置上的`*`匹配空字符串，相当于它不存在，直接忽略。
  * case 2：假定`*`匹配 s 中的第 i 个字符，则`dp[i][j] = dp[i-1][j]`。也就是说，s 的前 (i-1) 个字符与 p 的前 j 个字符已经匹配了，现在由于`p[j]`位置为`*`，它可以继续匹配 s 的第 i 个字符。

##### 代码

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        // 初始化
        dp[0][0] = true;
        for(int j = 1; j <= n; j++) {
            dp[0][j] = dp[0][j-1] && p.charAt(j-1) == '*';
        }
        
        // 状态转移
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '?') {
                    dp[i][j] = dp[i-1][j-1];
                }else if(p.charAt(j-1) == '*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                }else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[m][n];
    }
}
```



方法3：动态规划（从右至左）

##### 状态

令`dp[i][j]`表示字符串s[i...]与模式串p[j...]是否匹配，因此最终的结果保存在`dp[0][0]`中。

##### 初始化

令 m 是字符串 s 的长度，n 是模式串 p 的长度。

* `dp[m][n] = true` 显然就表示两个空字符串匹配。
* `dp[0...m-1][n] = false`， 即字符串 s 非空，而模式串 p 为空，显然这种情况均为不匹配；
* `dp[m][0...n-1] = 待定`， 即字符串 s 为空，而模式串 p 非空。

##### 状态转移

* 如果`s[i] == p[j]` 或者 `p[j] == '?'`， 则`dp[i][j] =dp[i+1][j+1] `
* 如果`p[j] == '*'`， 则需要考虑两种情况
  - case 1：假定`*`匹配空字符串，则`dp[i][j] = dp[i][j+1]`。换句话说，s[i...]与 p[j+1...]匹配了，那么，就等价于 s[i...] 与 p[j...] 匹配了，因为此时`p[j]`位置上的`*`匹配空字符串，相当于它不存在，直接忽略。
  - case 2：假定`*`匹配 s 中的第 i 个字符，则`dp[i][j] = dp[i+1][j]`。也就是说，s[i+1...]与 p[j...] 已经匹配了，现在由于`p[j]`位置为`*`，它可以继续匹配 s 的第 i 个字符。

##### 代码

```java
class Solution {
    public boolean isMatch(String s, String p) {
        int m = s.length();
        int n = p.length();
        boolean[][] dp = new boolean[m+1][n+1];
        
        // 初始化
        dp[m][n] = true;
        for(int j = n-1; j >= 0; j--) {
        	dp[m][j] = dp[m][j+1] && p.charAt(j) == '*';  
        }
        
        // 状态转移
        for(int i = m-1; i >= 0; i--) {
            for(int j = n-1; j >= 0; j--) {
                if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '?'){
                    dp[i][j] = dp[i+1][j+1];
                }else if(p.charAt(j) == '*') {
                    dp[i][j] = dp[i+1][j] || dp[i][j+1];
                }else {
                    dp[i][j] = false;
                }
            }
        }
        return dp[0][0];
    }
}
```

