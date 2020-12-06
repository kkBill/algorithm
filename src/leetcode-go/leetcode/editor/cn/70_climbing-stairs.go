//假设你正在爬楼梯。需要 n 阶你才能到达楼顶。 
//
// 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？ 
//
// 注意：给定 n 是一个正整数。 
//
// 示例 1： 
//
// 输入： 2
//输出： 2
//解释： 有两种方法可以爬到楼顶。
//1.  1 阶 + 1 阶
//2.  2 阶 
//
// 示例 2： 
//
// 输入： 3
//输出： 3
//解释： 有三种方法可以爬到楼顶。
//1.  1 阶 + 1 阶 + 1 阶
//2.  1 阶 + 2 阶
//3.  2 阶 + 1 阶
// 
// Related Topics 动态规划 
// 👍 1353 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
// 方法1：暴力递归
//func climbStairs(n int) int {
//	if n == 1 || n == 2 {
//		return n
//	}
//	return climbStairs(n-1) + climbStairs(n-2)
//}

// 方法2：记忆化搜索
//var cache = make(map[int]int)
//func climbStairs(n int) int {
//	// 边界条件
//	if n == 1 || n == 2 {
//		return n
//	}
//	// 先从缓存中查询
//	if _, ok := cache[n]; ok {
//		return cache[n]
//	}
//	// 处理子问题
//	nums1 := climbStairs(n-1)
//	nums2 := climbStairs(n-2)
//	// 缓存计算结果
//	cache[n] = nums1 + nums2
//	return cache[n]
//}

// 方法3：动态规划
// time: O(n)，space: O(n)
func climbStairs(n int) int {
	if n == 1 || n == 2 {
		return n
	}
	dp := make([]int, n+1)
	// 初始化
	dp[1], dp[2] = 1, 2
	for i := 3; i <= n; i++ {
		dp[i] = dp[i-1] + dp[i-2]
 	}
	return dp[n]
}

// 方法4：动态规划，空间压缩
// time: O(n)，space: O(1)

//leetcode submit region end(Prohibit modification and deletion)


func main() {
	fmt.Printf("%d\n", climbStairs(12))
}