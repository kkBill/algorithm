#### [746. Min Cost Climbing Stairs](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)

On a staircase, the i-th step has some non-negative cost cost[i] assigned (0 indexed).

Once you pay the cost, you can either climb one or two steps. You need to find minimum cost to reach the top of the floor, and **you can either start from the step with index 0, or the step with index 1**.

```
Input: cost = [10, 15, 20]
Output: 15
Explanation: Cheapest is start on cost[1], pay that cost and go to the top.

Input: cost = [1, 100, 1, 1, 1, 100, 1, 1, 100, 1]
Output: 6
Explanation: Cheapest is start on cost[0], and only step on 1s, skipping cost[3].
```

Note:

1. cost will have a length in the range [2, 1000].
2. Every cost[i] will be an integer in the range [0, 999].

分析：令`dp[i]`表示到达第`i`个台阶需要的最小花费，和第70题相同的是，只可能从第`i-1`个台阶向上跨1步，或从第`i-2`个台阶向上跨2步到达第`i`个台阶。同时，这里还要考虑花费，如果从第`i-1`个台阶向上跨1步，则需要花费`dp[i-1] + cost[i-1]`；如果从第`i-2`个台阶向上跨2步，则需要花费`dp[i-2] + cost[i-2]`。因此，状态转移方程也就是：`dp[i] = min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2])`。

需要注意这里的初始状态，题目规定“**you can either start from the step with index 0, or the step with index 1**”，因此`dp[0] = 0, dp[1] = 0` 。

```java
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        if(n == 0) return 0;        
        //dp[i] 表示到达第 i 个台阶所需要的最小花费
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 0; //容易错写成 dp[1] = cost[0], 边界条件, 注意读题
        for(int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }
        return dp[n];
    }
}
```

