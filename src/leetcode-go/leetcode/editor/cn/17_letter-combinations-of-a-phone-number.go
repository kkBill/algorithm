//给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。 
//
// 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。 
//
// 
//
// 示例: 
//
// 输入："23"
//输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// 
//
// 说明: 
//尽管上面的答案是按字典序排列的，但是你可以任意选择答案输出的顺序。 
// Related Topics 深度优先搜索 递归 字符串 回溯算法 
// 👍 1022 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
func letterCombinations(digits string) []string {
	phone := map[string]string {
		"2":"abc",
		"3":"def",
		"4":"ghi",
		"5":"jkl",
		"6":"mno",
		"7":"pqrs",
		"8":"tuv",
		"9":"wxyz",
	}

	var res []string
	var dfs func(index, n int, path string)
	dfs = func(index, n int, path string) {
		if index == n {
			res = append(res, path)
			return
		}

		digit := string(digits[index])
		chars, _ := phone[digit]
		for _, ch := range chars {
			//path += string(ch)
			dfs(index+1,n,path+string(ch))
			//path = path[:len(path)-1]
		}
	}

	dfs(0, len(digits), "")
	return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	fmt.Printf("%s\n", letterCombinations("23"))
}