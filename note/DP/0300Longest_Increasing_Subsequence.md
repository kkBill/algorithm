#### [300. 最长上升子序列](https://leetcode-cn.com/problems/longest-increasing-subsequence/)

本题求最长上升子序列。

```
输入: [10,9,2,5,3,7,101,18]
输出: 4 
解释: 最长的上升子序列是 [2,3,7,101]，它的长度是 4。
```

说明:

* 可能会有多种最长上升子序列的组合，你只需要输出对应的长度即可。
* 算法的时间复杂度应该为 O(n^2) 。
* **进阶: 你能将算法的时间复杂度降低到 O(n log n) 吗**?

**方法1**：**动态规划**，O(n^2)

定义状态：令 dp[i] 表示**以s[i]为结尾**的子串的最长上升子序列的长度。那么有初始化的值 dp[i] = 1 (0 ≤ i＜n)。

状态转移：假定 **0 ≤ j ＜ i**，则有
$$
dp[i] =
\begin{cases}
max\{dp[j]\} + 1& 如果存在 s[j]<s[i]\\ 
1 & 如果不存在 s[j]<s[i]\\
\end{cases}
$$


对于每个dp[i]，都要从[0...i-1] 遍历一遍，因此时间复杂度是O(n^2)

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int LIS = 0;
        // 状态转移
        for(int i = 0; i < nums.length; i++) {
            dp[i] = 1; // 初始化
            // 通过比较[0...i-1]的值来更新dp[i]的值
            for(int j = 0; j < i; j++) {
                if(nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            LIS = Math.max(LIS, dp[i]);
        }
        return LIS;
    }
}
```



**方法2**：**贪心 + 二分查找**，O(n×logn)

考虑这样一个简单的贪心，想要一个上升序列尽量的长，那么在满足递增性的前提下，我们希望每次在上升子序列最后加上的那个数尽可能的小，值越小，我们称越有“潜力”。

基于上面的贪心思路，我们维护一个数组 d[len]，表示长度为 len 的上升子序列的最后一个值。初始化d[1] = s[0].由于是上升子序列，因此 d[i] 显然是关于 i 递增的。最后的结果，就是 len 能取到的最大值。

我们依次遍历s[]的每个值，并更新数组 d[] 和 len。

* 若 s[i] > d[len]，则更新 len = len + 1，同时d[len] = s[i]，即增加了上升子序列的长度。
* 若 s[i] < d[len]，则在d[1...len]中寻找满足条件 d[j-1] < s[i] < d[j] 的下标 j，并替换 d[j] = s[i] 。这种情况并没有增加上升子序列的长度，但是替换掉了原d[1...len]中的某个值，使其更有“潜力”。由于 d[] 是一个递增序列，因此可以利用二分查询，从而把时间复杂度将为O(log n)。
* 若 s[i] == d[len]，由于本题要求的是严格的上升，因此忽略这种情况。

以输入序列 [0, 8, 4, 12, 2]为例：

第一步插入 0，d = [0]；

第二步插入 8，d = [0, 8]；

第三步插入 4，d = [0, 4]；

第四步插入 12，d = [0, 4, 12]；

第五步插入 2，d = [0, 2, 12]。

最终得到最大递增子序列长度为 3。

```java
class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if(n == 0) return 0;
        
        int[] d = new int[n+1];
        int len = 1;
        d[len] = nums[0];
        for(int i = 1; i < n; i++) {
            if(nums[i] > d[len]) {
                len++;
                d[len] = nums[i];
            }else {
                if(len == 1) {
                    d[len] = nums[i];
                    continue;
                }
                int left = 1, right = len;
                while(left < right) {
                    int mid = (right - left) / 2 + left;
                    if(d[mid] < nums[i]) left = mid + 1;
                    else right = mid;
                }
                d[left] = nums[i];
            }
        }
        return len;
    }
}
```

