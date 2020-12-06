//给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的数字可以无限制重复被选取。 
//
// 说明： 
//
// 
// 所有数字（包括 target）都是正整数。 
// 解集不能包含重复的组合。 
// 
//
// 示例 1： 
//
// 输入：candidates = [2,3,6,7], target = 7,
//所求解集为：
//[
//  [7],
//  [2,2,3]
//]
// 
//
// 示例 2： 
//
// 输入：candidates = [2,3,5], target = 8,
//所求解集为：
//[
//  [2,2,2,2],
//  [2,3,3],
//  [3,5]
//] 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 1 <= candidates[i] <= 200 
// candidate 中的每个元素都是独一无二的。 
// 1 <= target <= 500 
// 
// Related Topics 数组 回溯算法 
// 👍 1081 👎 0


package main

import (
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)
func combinationSum(candidates []int, target int) [][]int {
	var res [][]int
	var dfs func(target, begin int, path []int)
	dfs = func(target, begin int, path []int) {
		// 边界条件
		if target == 0 {
			res = append(res, append([]int{},path...))
			return
		}
		for i := begin; i < len(candidates); i++ {
			if target - candidates[i] < 0 {
				continue
			}
			// 处理当前层逻辑
			path = append(path, candidates[i])
			target -= candidates[i]
			// 进入下一层
			dfs(target, i, path)
			// 回溯
			target += candidates[i]
			path = path[:len(path)-1]
		}
	}

	dfs(target,0,[]int{})
	return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	var candidates []int
	var target int
	candidates = []int{2,3,6,7}
	target = 7
	res := combinationSum(candidates, target)
	fmt.Printf("%v\n", res)

	candidates = []int{2,3,5}
	target = 8
	res = combinationSum(candidates, target)
	fmt.Printf("%v\n", res)
}