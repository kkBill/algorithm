//给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。 
//
// 示例: 
//
// 输入: n = 4, k = 2
//输出:
//[
//  [2,4],
//  [3,4],
//  [2,3],
//  [1,2],
//  [1,3],
//  [1,4],
//] 
// Related Topics 回溯算法 
// 👍 447 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
//func combine(n int, k int) [][]int {
//	var res [][]int
//	var dfs func(i int, path []int)
//	dfs = func(i int, path []int) {
//		// 边界条件（达到了k个数）
//		if len(path) == k {
//			tmp := make([]int, k)
//			copy(tmp, path)
//			res = append(res, tmp) // 注意不能直接res = append(res, path)，底层是同一个引用
//			return
//		}
//		for j := i; j <= n; j++ {
//			// 处理当前层
//			path = append(path, j)
//			// 进入下一层
//			dfs(j+1, path)
//			// 状态恢复，回溯
//			path = path[:len(path)-1]
//		}
//	}
//
//	var path []int
//	dfs(1, path)
//	return res
//}

// 剪枝优化
func combine(n int, k int) [][]int {
	var res [][]int
	var dfs func(begin int, path []int)
	dfs = func(begin int, path []int) {
		// 边界条件（达到了k个数）
		if len(path) == k {
			// 注意不能直接res = append(res, path)，底层是同一个引用
			res = append(res, append([]int{}, path...))
			return
		}

		// 剪枝
		// n-begin+1 表示从当前位置到最后n的个数，如果len(path)+n-begin+1 都不可能大于等于 k 了
		// 说明从位置 begin 开始就没必要继续递归下去了
		if len(path) + n - begin + 1 < k {
			return
		}

		for i := begin; i <= n; i++ {
			// 处理当前层
			path = append(path, i)
			// 进入下一层
			dfs(i+1, path)
			// 状态恢复，回溯
			path = path[:len(path)-1]
		}
	}

	dfs(1, []int{})
	return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	res := combine(7, 4)
	fmt.Printf("%v\n", res)
}