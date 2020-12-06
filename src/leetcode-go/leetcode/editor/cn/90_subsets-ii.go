//给定一个可能包含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: [1,2,2]
//输出:
//[
//  [2],
//  [1],
//  [1,2,2],
//  [2,2],
//  [1,2],
//  []
//] 
// Related Topics 数组 回溯算法 
// 👍 349 👎 0


package main

import (
	"sort"
)

//leetcode submit region begin(Prohibit modification and deletion)
func subsetsWithDup(nums []int) [][]int {
	var res [][]int
	var dfs func(begin int, path []int)
	sort.Slice(nums, func(i, j int) bool {return nums[i] < nums[j]})

	dfs = func(begin int, path []int) {
		if begin > len(nums) {
			return
		}
		res = append(res, append([]int{}, path...))
		//fmt.Printf("%v\n", res)
		for i := begin; i < len(nums); i++ {
			// 去重
			if i > begin && nums[i] == nums[i-1] {
				continue
			}
			path = append(path, nums[i])
			dfs(i+1, path)
			path = path[:len(path)-1]
		}
	}

	dfs(0, []int{})
	return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	subsetsWithDup([]int{2,2})
}