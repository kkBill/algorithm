//实现 pow(x, n) ，即计算 x 的 n 次幂函数。 
//
// 示例 1: 
//
// 输入: 2.00000, 10
//输出: 1024.00000
// 
//
// 示例 2: 
//
// 输入: 2.10000, 3
//输出: 9.26100
// 
//
// 示例 3: 
//
// 输入: 2.00000, -2
//输出: 0.25000
//解释: 2-2 = 1/22 = 1/4 = 0.25 
//
// 说明: 
//
// 
// -100.0 < x < 100.0 
// n 是 32 位有符号整数，其数值范围是 [−231, 231 − 1] 。 
// 
// Related Topics 数学 二分查找 
// 👍 551 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
// 时间复杂度：O(logn)
func myPow(x float64, n int) float64 {
	if n == 0 || x == 1.0 {
		return 1.0
	}
	if n < 0 {
		return 1.0 / myPow(x, -n)
	}
	val := myPow(x, n/2)
	if n % 2 == 1 { // 奇数
		return val * val * x
	}else { // 偶数
		return val * val
	}
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	fmt.Printf("%f\n", myPow(2.0, 10))
	fmt.Printf("%f\n", myPow(2.1, 3))
	fmt.Printf("%f\n", myPow(2.0, -2))
}