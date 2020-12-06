//给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用一次。 
//
// 说明： 
//
// 
// 所有数字（包括目标数）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1: 
//
// 输入: candidates = [10,1,2,7,6,1,5], target = 8,
//所求解集为:
//[
//  [1, 7],
//  [1, 2, 5],
//  [2, 6],
//  [1, 1, 6]
//]
// 
//
// 示例 2: 
//
// 输入: candidates = [2,5,2,1,2], target = 5,
//所求解集为:
//[
//  [1,2,2],
//  [5]
//] 
// Related Topics 数组 回溯算法 
// 👍 451 👎 0

package main

import (
	"fmt"
	"sort"
)

//leetcode submit region begin(Prohibit modification and deletion)
func combinationSum2(candidates []int, target int) [][]int {
	sort.Slice(candidates, func(i, j int) bool {
		return candidates[i] < candidates[j]
	})
	var res [][]int
	var dfs func(target, begin int, path []int)
	dfs = func(target, begin int, path []int) {
		// 边界条件
		if target == 0 {
			res = append(res, append([]int{}, path...))
			return
		}
		// 处理当前层的逻辑
		for i := begin; i < len(candidates); i++ {
			// 剪枝
			if target - candidates[i] < 0 {
				return
			}
			// 去重
			if i > begin && candidates[i] == candidates[i-1] {
				continue
			}
			// 处理当前层
			path = append(path, candidates[i])
			target -= candidates[i]
			// 进入下一层
			dfs(target, i+1, path)
			// 回溯
			target += candidates[i]
			path = path[:len(path)-1]
		}
	}

	dfs(target, 0, []int{})
	return res
}

//leetcode submit region end(Prohibit modification and deletion)

func main() {
	var candidates []int
	var target int
	candidates = []int{1, 1, 2, 5, 6, 7, 10}
	target = 8
	res := combinationSum2(candidates, target)
	fmt.Printf("%v\n", res)

	candidates = []int{1, 1, 1, 1, 1}
	target = 4
	res = combinationSum2(candidates, target)
	fmt.Printf("%v\n", res)
}
