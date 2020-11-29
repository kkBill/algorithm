//给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 
//
// 
//
// 示例: 
//
// 给定 nums = [2, 7, 11, 15], target = 9
//
//因为 nums[0] + nums[1] = 2 + 7 = 9
//所以返回 [0, 1]
// 
// Related Topics 数组 哈希表 
// 👍 9664 👎 0
package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
// 方法1：暴力法，时间复杂度O(n^2)
//func twoSum(nums []int, target int) []int {
//	var res []int
//	for i := 0; i < len(nums); i++ {
//		for j := i + 1; j < len(nums); j++ {
//			if nums[i]+nums[j] == target {
//				res = append(res, i, j)
//				return res
//			}
//		}
//	}
//	return res
//}

// 方法2：借助哈希，时间复杂度O(n)
// 当遍历到nums[i]时，在哈希表中检查是否存在target-nums[i]
// 如果存在，则表示nums[i]与之前的某个值之和恰等于target
// 否则，在哈希中记录nums[i]的位置
func twoSum(nums []int, target int) []int {
	var res []int
	table := make(map[int]int)
	for i := 0; i < len(nums); i++ {
		if index, ok := table[target-nums[i]]; ok {
			res = append(res, index, i)
			break // 因为本题假设每种输入只会对应一个答案，因此可以提前跳出
		}else {
			table[nums[i]] = i
		}
	}
	return res
}
//leetcode submit region end(Prohibit modification and deletion)

func main() {
	res := twoSum([]int{2, 7, 11, 15}, 9)
	fmt.Println(res)

	res = twoSum([]int{2, 11, 7, 15}, 17)
	fmt.Println(res)
}
