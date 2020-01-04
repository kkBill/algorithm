## Kadane's algorithm

Kadane's algorithm用于处理最大子序列问题（maximum subarray problem）。

最大子序列问题：

> 给定一个整数数组 `nums` ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。比如，nums = [-2,1,-3,4,-1,2,1,-5,4]，其最大连续子数组为 [4,-1,2,1] ，对应的和为 6。

之前我们在解决这个问题的时候，使用了动态规划的思想。在这里，介绍Kadane's algorithm。其算法为：

```
int maxSubarray(int[] nums){
	int maxSoFar = 0, maxCurr = 0;
	for(int num : nums){
     	maxCurr = max(0, maxCurr + num);
     	maxSoFar = max(maxSoFar, maxCurr);
	}
	return maxSoFar;
}
```



参考：<https://en.wikipedia.org/wiki/Maximum_subarray_problem#Kadane's_algorithm>

