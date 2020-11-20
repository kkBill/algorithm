# 滑动窗口

[toc]

总结：滑动窗口的问题都比较难，第一次接触的话很难完全自己想到。解法技巧性较强，非常巧妙，让人大呼过瘾

---

## [239. 滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/)（单调队列经典应用）

给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。返回滑动窗口中的最大值。可以假设 k 总是有效的，在输入数组不为空的情况下，1 ≤ k ≤ 输入数组的大小。

进阶：你能在线性时间复杂度内解决此题吗？

```
示例:
输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
输出: [3,3,5,5,6,7] 
解释: 
滑动窗口的位置                最大值
---
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7
```

分析：本题在[Heap专题](./Heap.md)中也有提到（借助优先队列实现的一个O(nlogk)的解法），但最优解是使用滑动窗口的思想来做，可达到线性时间复杂度。

在这里，最关键的点在于**使用双端队列来维护一个单调队列**，即保证队列中的元素总是满足：`q[0]>q[1]>q[2]...` ，为什么要这样处理？我们稍后再说。

假设当前位置为 i ，那么此时窗口的范围在 [i-k+1, i]，我们的目标是找到每一个窗口中的最大值，并记录在结果集中。我们借助双端队列来存储窗口范围内的**索引**，存放索引而不存放元素值，主要是为了方便管理窗口的范围。（如果是存放元素值，我们就无法控制窗口的大小了）

1. 如果队列中的索引值小于 i-k+1，也就是说该元素不在窗口范围内了，则丢弃之。
2. 而把当前索引加入队列之前，要先对队列进行处理，以**保证队列中存放的索引值满足`nums[q[0]] > nums[q[1]] > nums[q[2]]...`** 。假设现在队列中已存入了索引 0，接着要存入索引 1，我们发现，nums[1]的值大于nums[0]，那么可以确定，**nums[0]始终不可能成为该窗口内的最大值**，于是我们把它抛弃。同样的，当nums[1]等于nums[0]时也把nums[0]抛弃。也就是说，**我们要维护一个单调递减队列**，理解这一点是完成本题的关键。
3. 通过step 2的处理，队列满足单调递减的性质，因此队首索引对应的值就是当前窗口范围内的最大值。

```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if(n == 0 || k == 0) return new int[0];
        
        int[] result = new int[n-k+1];
        Deque<Integer> deque = new ArrayDeque<>();
        for(int right = 0; right < n; right++) {
            // right-k+1 是当前窗口的左边界，right 是右边界
            int left = right - k + 1;
            
            // 移除窗口范围外的元素
            if(!deque.isEmpty() && deque.peekFirst() < left) {
                deque.pollFirst();
            }
            
            // 把当前索引加入进队列前，比较当前元素值与队尾元素值的大小
            // 即维护队列为单调递减队列
            while(!deque.isEmpty() && nums[right] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.addLast(right);
            
            if(left >= 0) {
                // 由于队列的单调性，队首索引值对应的值就是该窗口范围内的最大值
                result[left] = nums[deque.peekFirst()];  
            }
        }       
        return result;
    }
}
```



## [209. 长度最小的子数组](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)

给定一个含有 **n **个**正整数**的数组和一个正整数 **s ，**找出该数组中满足其和≥ s **的长度最小的连续子数组**。如果不存在符合条件的连续子数组，返回 0。示例如下：

```
输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
```

分析：我们采用滑动窗口的思想来做，即用left和right两个指针，圈定子数组范围为[left, right]。

1. 如果子数组之和sum < 目标值s，则右移right，扩大子数组范围。由于限定数组的元素值是正整数，因此子数组的元素之和sum一定是递增的。
2. 如果子数组之和sum >= 目标值s，则右移left，缩小子数组范围，直到“子数组之和sum >= 目标值s”这一条件不再满足。当条件不再满足时，回到第1步。

```java
class Solution {
    public int minSubArrayLen(int s, int[] nums) {
        int n = nums.length;
        if(n == 0 || s <= 0) return 0;
        
        int minLen = n+1;
        int sum = 0;
        for(int left = 0, right = 0; right < n; right++) {
            sum += nums[right];
            while(sum >= s) {
                minLen = Math.min(minLen, right-left+1);
                sum -= nums[left];
                left++;
            }
        }
        return (minLen == n+1) ? 0 : minLen;
    }
}
```

本题限定数组的元素值都是正整数，这实际上是降低了此类问题的难度。在第862题中，允许数组的元素值为负数，难度就上升了许多。本题只是滑动窗口思想的小试牛刀，这里用两个指针表示滑动窗口的左边界和右边界，也属于是two pointers的思想。

但是在第862题中，就不能简单的使用双指针来处理了。



## [862. 和至少为 K 的最短子数组](https://leetcode-cn.com/problems/shortest-subarray-with-sum-at-least-k/)

返回 `A` 的最短的非空连续子数组的**长度**，该子数组的和至少为 `K` 。如果没有和至少为 `K` 的非空子数组，返回 `-1` 。

```
输入：A = [1,2], K = 4
输出：-1

输入：A = [3,-2,5], K = 4
输出：1
```

分析：由于本题的数组中允许出现负数，因此不能简单的使用双指针的思想来维护滑动窗口。以`A = [3,-2,5], K = 4`为例，我们不妨用上一题的思路做一下，看看哪里会出错。

1. 初始化left, right均为0，此时sum=3，不满足题意，right右移；
2. left=0，right=1，此时sum=1，仍然不满足，right继续右移；
3. left=0，right=2，此时sum=6 > 4，满足，于是进入while循环中，更新minLen=3。然后开始缩小窗口范围，即 left右移
4. left=1，right=2，此时sum=3 < 4，不满足条件了，于是就退出while循环了
5. 最后程序结束，结果最短子数组的长度是3，但正确的结果应该是1。可以发现，在缩小窗口范围这一步存在问题。

处理子数组问题时，常常用到**前缀和**（**prefix sum**）这一思想来对数据做预处理，本题中就是用到了这一方法。假设已经计算好了给定数组A的前缀和B，如下：

```java
int n = A.length;
int[] B = new int[n+1];
for(int i = 0; i < n; i++) {
	B[i+1] = B[i] + A[i];  
}

//
A: 3, -2, 5
B: 0,  3, 1, 6   
```

那么`B[i] - B[j]` 表示的就是子数组 `A[j],A[j+1]...A[i-1]` 的和。我们的目标，就是寻找**在满足`B[i] - B[j]≥ K` 的基础上，尽量使 `i - j` 最小**。

我们先来观察一下前缀和数组B：`0, 3, 1, 6` 

* case1：B[3] - B[1] = 3，对应子数组的长度为2，即{-2, 5}，子数组之和sum=3
* case2：B[3] - B[2] = 5，对应子数组的长度为1，即{5}，子数组之和sum=5

相比于case1，case2不仅子数组之和更大，而且子数组的长度越小，因此更符合我们的目标。因此，相比较而言，前缀和数组B中的B[1]就没有存在的价值。

这也就是在下面引入双端队列并维护队列**单调递增**的原因所在。

```java
class Solution {
    public int shortestSubarray(int[] A, int K) {
        int n = A.length;
        int[] preSum = new int[n+1];
        // 计算前缀和
        for(int i = 0; i < n; i++) {
            preSum[i+1] = preSum[i] + A[i];
        }
        
        int minSubLen = n + 1;
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i = 0; i < preSum.length; i++) {
            while(!deque.isEmpty() && preSum[i] - preSum[deque.peekFirst()] >= K) {
                minSubLen = Math.min(minSubLen, i - deque.pollFirst());
            }
            // 维护单调递增队列
            while(!deque.isEmpty() && preSum[i] <= preSum[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.add(i);
        }
        return (minSubLen == n+1) ? -1 : minSubLen;
    }
}
```

本题主要参考：

1. <https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/189039/Detailed-intuition-behind-Deque-solution>
2. <https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/143726/C%2B%2BJavaPython-O(N)-Using-Deque>

单调队列思想和问题汇总，参考这里：<https://leetcode.com/problems/shortest-subarray-with-sum-at-least-k/discuss/204290/Monotonic-Queue-Summary>







