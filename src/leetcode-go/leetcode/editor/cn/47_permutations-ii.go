//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
// Related Topics 回溯算法 
// 👍 534 👎 0


package main

import (
	"fmt"
	"sort"
)

//leetcode submit region begin(Prohibit modification and deletion)
func permuteUnique(nums []int) [][]int {
	var res [][]int
	var dfs func(n int, path []int, visit []bool)
	sort.Slice(nums, func(i, j int) bool {return nums[i] < nums[j]})

	dfs = func(n int, path []int, visit []bool) {
		if len(path) == n {
			res = append(res, append([]int{}, path...))
			return
		}
		for i := 0; i < n; i++ {
			if visit[i] { // 如果下标为i对应的元素已经被访问过了
				continue
			}
			if i > 0 && nums[i] == nums[i-1] && !visit[i-1] { // 去重
				continue
			}
			// 处理当前层逻辑
			visit[i] = true
			path = append(path, nums[i])
			// 进入下一层（注意，这里的"层数"指的是path内包含的元素个数）
			dfs(n, path, visit)
			// 回溯，重置状态
			path = path[:len(path)-1]
			visit[i] = false
		}
	}
	dfs(len(nums), []int{}, make([]bool, len(nums)))
	return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	fmt.Printf("%v\n", permuteUnique([]int{1,1,3}))
}