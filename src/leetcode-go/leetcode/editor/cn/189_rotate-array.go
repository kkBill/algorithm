//给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。 
//
// 示例 1: 
//
// 输入: [1,2,3,4,5,6,7] 和 k = 3
//输出: [5,6,7,1,2,3,4]
//解释:
//向右旋转 1 步: [7,1,2,3,4,5,6]
//向右旋转 2 步: [6,7,1,2,3,4,5]
//向右旋转 3 步: [5,6,7,1,2,3,4]
// 
//
// 示例 2: 
//
// 输入: [-1,-100,3,99] 和 k = 2
//输出: [3,99,-1,-100]
//解释: 
//向右旋转 1 步: [99,-1,-100,3]
//向右旋转 2 步: [3,99,-1,-100] 
//
// 说明: 
//
// 
// 尽可能想出更多的解决方案，至少有三种不同的方法可以解决这个问题。 
// 要求使用空间复杂度为 O(1) 的 原地 算法。 
// 
// Related Topics 数组 
// 👍 735 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)

// 方法1：暴力法，这种方法会产生新的切片，因此必须返回新的数组，原数组不会改变，不属于原地算法
// 只是为了熟悉go的语法
//func rotate(nums []int, k int) []int {
//	// k may larger than len(nums)
//	k = k % len(nums)
//	nums = append(nums[len(nums)-k:],nums[:len(nums)-k]...)
//	return nums
//}

// 方法2：时间复杂度O(k×n)，空间复杂度O(1)
//func rotate(nums []int, k int) {
//	k = k % len(nums)
//	for k > 0 { // 总共需要移动k轮，每一轮向后移动一个元素
//		last := nums[len(nums)-1]
//		for i := len(nums)-2; i >= 0; i-- {
//			nums[i+1] = nums[i]
//		}
//		nums[0] = last
//		k--
//	}
//}

// 方法3：时间复杂度O(n)，空间复杂度O(1)
// 这种思维方式记住就是了
func rotate(nums []int, k int) {
	k = k % len(nums)
	reverse(nums, 0, len(nums)-k-1)
	reverse(nums, len(nums)-k, len(nums)-1)
	reverse(nums, 0, len(nums)-1)
}

func reverse(nums []int, l, r int) {
	for l < r {
		nums[l], nums[r] = nums[r], nums[l]
		l++
		r--
	}
}

//leetcode submit region end(Prohibit modification and deletion)

func main() {
	nums := []int{1, 2, 3, 4, 5, 6, 7}
	rotate(nums, 3)
	fmt.Println(nums)

	nums = []int{1}
	rotate(nums, 3)
	fmt.Println(nums)
}
