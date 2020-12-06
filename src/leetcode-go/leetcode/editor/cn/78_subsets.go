//给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。 
//
// 说明：解集不能包含重复的子集。 
//
// 示例: 
//
// 输入: nums = [1,2,3]
//输出:
//[
//  [3],
//  [1],
//  [2],
//  [1,2,3],
//  [1,3],
//  [2,3],
//  [1,2],
//  []
//] 
// Related Topics 位运算 数组 回溯算法 
// 👍 897 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
// 根据子集的元素个数分层
func subsets(nums []int) [][]int {
	var res [][]int
	var dfs func(size int, begin int, path []int)

	dfs = func(size int, begin int, path []int) {
		// 边界
		if size == len(path) {
			res = append(res, append([]int{}, path...))
			fmt.Printf("%v\n", res)
			return
		}
		for i := begin; i < len(nums); i++ {
			// 处理当前层逻辑
			path = append(path, nums[i])
			// 进入下一层
			dfs(size, i+1, path)
			// 回溯
			path = path[:len(path)-1]
		}

	}

	for size := 0; size <= len(nums); size++ {
		dfs(size, 0, []int{})
	}
	return res
}

// 根据深度遍历的顺序加入
//func subsets(nums []int) [][]int {
//	var res [][]int
//	var dfs func(begin int, path []int)
//	dfs = func(begin int, path []int) {
//		// 边界条件
//		//if begin > len(nums) {
//		//	return
//		//}
//		// 处理当前层的逻辑
//		res = append(res, append([]int{}, path...))
//		fmt.Printf("%v\n", res)
//		for i := begin; i < len(nums); i++ {
//			path = append(path, nums[i])
//			// 进入下一层
//			dfs(i+1, path)
//			// 回溯
//			path = path[:len(path)-1]
//		}
//	}
//
//	dfs(0, []int{})
//	return res
//}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	subsets([]int{1,2,3})
	//fmt.Printf("%v\n", subsets([]int{1,2,3}))
}