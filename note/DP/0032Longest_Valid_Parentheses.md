#### [32. Longest Valid Parentheses](https://leetcode-cn.com/problems/longest-valid-parentheses/)

给定一个只包含 `'('` 和 `')'` 的字符串，找出最长的包含有效括号的子串的长度。

```
输入: "(()"
输出: 2
解释: 最长有效括号子串为 "()"

输入: ")()())"
输出: 4
解释: 最长有效括号子串为 "()()"
```

方法1：动态规划

```java
class Solution {
    public int longestValidParentheses(String s) {
        int n = s.length();
        if(n == 0) return 0;
        // dp[i]表示以s[i]为结尾的子串的有效括号的最大长度
        int[] dp = new int[n];
        dp[0] = 0;
        int maxLen = 0;
        for(int i = 1; i < n; i++) {
            // 以'('结尾的肯定是无效的，即dp[i] = 0, 无需更新
            if(s.charAt(i) == '(') continue;
            // 只对以')'结尾的子串进行更新
            if(s.charAt(i-1) == '(') {
                if(i - 2 >= 0) dp[i] = dp[i-2] + 2;
                else dp[i] = 2;
            }else {
                int t = i - dp[i-1] - 1;
                if(t >= 0 && s.charAt(t) == '(') {
                    dp[i] = dp[i-1] + 2 + (t-1 >= 0 ? dp[t-1] : 0);
                }else {
                    dp[i] = 0;
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
```



方法2：栈

```java
class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1); //本题点睛之笔
        int maxLen = 0;
        for(int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if(ch == '(') {
                stack.push(i);
            }else {
                stack.pop();
                // 当栈为空
                if(stack.isEmpty()){
                    stack.push(i);
                }else {
                    maxLen = Math.max(maxLen, i - stack.peek());
                }
            }
        }
        return maxLen;
    }
}
```



