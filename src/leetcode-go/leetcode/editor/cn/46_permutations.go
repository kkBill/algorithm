//给定一个 没有重复 数字的序列，返回其所有可能的全排列。 
//
// 示例: 
//
// 输入: [1,2,3]
//输出:
//[
//  [1,2,3],
//  [1,3,2],
//  [2,1,3],
//  [2,3,1],
//  [3,1,2],
//  [3,2,1]
//] 
// Related Topics 回溯算法 
// 👍 1024 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
func permute(nums []int) [][]int {
	var res [][]int
	var dfs func(n int, path []int, visit []bool)
	dfs = func(n int, path []int, visit []bool) {
		if len(path) == n {
			//tmp := make([]int, n)
			//copy(tmp, path)
			//res = append(res, tmp)
			res = append(res, append([]int{}, path...))
			return
		}
		for i := 0; i < n; i++ {
			if visit[i] { // 如果下标为i的元素已经被访问过了
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
	fmt.Printf("%v\n", permute([]int{1,2,3}))
}