//给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。 
//
// 有效的 IP 地址 正好由四个整数（每个整数位于 0 到 255 之间组成，且不能含有前导 0），整数之间用 '.' 分隔。 
//
// 例如："0.1.2.201" 和 "192.168.1.1" 是 有效的 IP 地址，但是 "0.011.255.245"、"192.168.1.312"
// 和 "192.168@1.1" 是 无效的 IP 地址。 
//
// 
//
// 示例 1： 
//
// 输入：s = "25525511135"
//输出：["255.255.11.135","255.255.111.35"]
// 
//
// 示例 2： 
//
// 输入：s = "0000"
//输出：["0.0.0.0"]
// 
//
// 示例 3： 
//
// 输入：s = "1111"
//输出：["1.1.1.1"]
// 
//
// 示例 4： 
//
// 输入：s = "010010"
//输出：["0.10.0.10","0.100.1.0"]
// 
//
// 示例 5： 
//
// 输入：s = "101023"
//输出：["1.0.10.23","1.0.102.3","10.1.0.23","10.10.2.3","101.0.2.3"]
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3000 
// s 仅由数字组成 
// 
// Related Topics 字符串 回溯算法 
// 👍 463 👎 0

package main

import (
	"fmt"
	"strconv"
	"strings"
)

//leetcode submit region begin(Prohibit modification and deletion)
func restoreIpAddresses(s string) []string {
	var res []string
	var dfs func(begin int, path []string)
	dfs = func(begin int, path []string) {
		// 边界条件
		if len(path) > 4 {
			return
		}
		if begin == len(s) && len(path) == 4 {
			res = append(res, strings.Join(path, "."))
			return
		}
		//
		for i := begin; i < len(s) && i < begin+3; i++ {
			sub := s[begin : i+1]
			// 判断这个分段是否合理
			// case 1: 当首字母为0时，当且仅当"0"是合法的，而"023"这样存在前导0的，则是不合法的
			if sub[0] == '0' && len(sub) > 1 {
				continue
			}
			// case 2: 分段的大小在0~255之间
			val, err := strconv.Atoi(sub)
			if err != nil {
				panic(err)
			}
			if val < 0 || val > 255 {
				continue
			}
			//
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
	fmt.Printf("%v\n", restoreIpAddresses("0000"))
	fmt.Printf("%v\n", restoreIpAddresses("1111"))
	fmt.Printf("%v\n", restoreIpAddresses("010010"))
	fmt.Printf("%v\n", restoreIpAddresses("101023"))
	fmt.Printf("%v\n", restoreIpAddresses("111111111111111111111"))
}
