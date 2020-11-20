#### [509. Fibonacci Number](https://leetcode-cn.com/problems/fibonacci-number/)

斐波那契数列问题。

方法1：直白的递归写法

```java
class Solution {
    public int fib(int N) {
        if(N == 0) return 0;
        if(N == 1) return 1;
        return fib(N-1) + fib(N-2);
    }
}
```



方法2：递归写法+备忘录

```java
class Solution {
    private int[] memo = new int[31]; // 题目规定 0 ≤ N ≤ 30
    public int fib(int N) {
        if(N == 0) return 0;
        if(N == 1) return 1;
        if(memo[N] != 0) return memo[N];
        memo[N] = fib(N-1) + fib(N-2); // 把此次计算结果先保存在备忘录中
        return memo[N];
    }
}
```



方法3：动态规划

```java
class Solution {
    public int fib(int N) {
        if(N == 0) return 0;
        if(N == 1) return 1;
        int[] dp = new int[N+1];
        // 初始化
        dp[0] = 0;
        dp[1] = 1;
        // 状态转移
        for(int i = 2; i <= N; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[N];
    }
}
```

