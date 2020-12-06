//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例： 
//
// 输入：n = 3
//输出：[
//       "((()))",
//       "(()())",
//       "(())()",
//       "()(())",
//       "()()()"
//     ]
// 
// Related Topics 字符串 回溯算法 
// 👍 1446 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
//func generateParenthesis(n int) []string {
//	var res []string
//	var dfs func(int, string)
//	dfs = func(i int, s string) {
//		// 边界条件
//		if i == 2*n {
//			res = append(res, s)
//			return
//		}
//		// 处理当前层
//		s1 := s + "("
//		s2 := s + ")"
//		// 进入下一层
//		dfs(i+1, s1)
//		dfs(i+1, s2)
//	}
//	dfs(0, "")
//	return res
//}

func generateParenthesis(n int) []string {
	var res []string
	var dfs func(left, right int, s string)
	// left,right分别表示左括号和右括号的个数
	dfs = func(left, right int, s string) {
		// 边界条件
		if left + right == 2 * n {
			res = append(res, s)
			return
		}
		// 处理当前层，并进入下一层
		if left < n {
			dfs(left+1, right, s + "(")
		}
		if left > right {
			dfs(left, right+1, s + ")")
		}
	}
	dfs(0, 0, "")
	return res
}

//leetcode submit region end(Prohibit modification and deletion)


func main() {
	res := generateParenthesis(3)
	fmt.Printf("%v\n", res)
	res = generateParenthesis(1)
	fmt.Printf("%v\n", res)
}