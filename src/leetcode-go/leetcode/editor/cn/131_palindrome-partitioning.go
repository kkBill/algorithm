//给定一个字符串 s，将 s 分割成一些子串，使每个子串都是回文串。 
//
// 返回 s 所有可能的分割方案。 
//
// 示例: 
//
// 输入: "aab"
//输出:
//[
//  ["aa","b"],
//  ["a","a","b"]
//] 
// Related Topics 回溯算法 
// 👍 432 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
func partition(s string) [][]string {
	var res [][]string
	var dfs func(begin int, path []string)
	var isPalindrome func(s string) bool
	isPalindrome = func(s string) bool {
		l, r := 0, len(s)-1
		for l < r {
			if s[l] != s[r] {
				return false
			}
			l++
			r--
		}
		return true
	}
	dfs = func(begin int, path []string) {
		// 边界条件
		if begin == len(s) && len(path) > 0 {
			res = append(res, append([]string{}, path...))
			return
		}
		//
		for i := begin; i < len(s); i++ {
			sub := s[begin:i+1]
			if !isPalindrome(sub) {
				continue
			}
			path = append(path, sub)
			dfs(i+1, path)
			path = path[:len(path)-1]
		}
	}
	dfs(0, []string{})
	return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	fmt.Printf("%v\n", partition("a"))
}