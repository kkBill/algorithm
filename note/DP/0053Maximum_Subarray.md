#### [53. Maximum Subarray](https://leetcode-cn.com/problems/maximum-subarray/)

给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。

```
输入: [-2,1,-3,4,-1,2,1,-5,4],
输出: 6
解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
```

分析：

```java
// 时间复杂度：O(n)
// 空间复杂度：O(n)
class Solution {
    public int maxSubArray(int[] nums) {
        // dp[i]表示以nums[i]为结尾的子数组的最大和
        // 注意，是必须以nums[i]为结尾
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for(int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
            res = Math.max(res, dp[i]);
        }
        return res;
    }
}
```





