//给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。 
//
// 示例: 
//
// 输入: ["eat", "tea", "tan", "ate", "nat", "bat"]
//输出:
//[
//  ["ate","eat","tea"],
//  ["nat","tan"],
//  ["bat"]
//] 
//
// 说明： 
//
// 
// 所有输入均为小写字母。 
// 不考虑答案输出的顺序。 
// 
// Related Topics 哈希表 字符串 
// 👍 526 👎 0


package main

import (
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)
// 方法1：暴力法，时间复杂度O(n^2 × k)，n 是strs数组元素个数，k是每个元素的长度
// 当strs非空时，选择首个元素strs[0]，计算各个字母出现的次数，然后遍历剩下的其他元素
// 把与strs[0]同属于异位词的归属为一组
// 同时用额外的一个哈希表来表示某个字符串是否被使用过
//func groupAnagrams(strs []string) [][]string {
//	var res [][]string
//	computed := make(map[string]bool, len(strs))
//	for i := 0; i < len(strs); i++ {
//		if computed[strs[i]] {
//			continue
//		}
//		base := strs[i]
//		hashMap := make(map[byte]int, len(base))
//		for j, size := 0, len(base); j < size; j++ {
//			hashMap[base[j]]++
//		}
//		baseGroup := []string{base}
//		computed[base] = true
//
//		for j := i + 1; j < len(strs); j++ {
//			if isAnagram(base, strs[j]) {
//				baseGroup = append(baseGroup, strs[j])
//				computed[strs[j]] = true
//			}
//		}
//		res = append(res, baseGroup)
//	}
//	return res
//}
//
//func isAnagram(s string, t string) bool {
//	if len(s) != len(t) {
//		return false
//	}
//	hashMap := make(map[byte]int, len(s))
//	for i, size := 0, len(s); i < size; i++ {
//		hashMap[s[i]]++
//	}
//	for i, size := 0, len(t); i < size; i++ {
//		hashMap[t[i]]--
//		if hashMap[t[i]] < 0 {
//			return false
//		}
//	}
//	return true
//}

// 方法2：时间复杂度O(n × klogk)
//func groupAnagrams(strs []string) [][]string {
//	var res [][]string
//	hashMap := make(map[string][]string)
//	for _, s := range strs { // O(n)
//		ss := []byte(s)
//		// O(k×logk)
//		sort.Slice(ss, func(i, j int) bool {return ss[i] < ss[j]})
//		hashMap[string(ss)] = append(hashMap[string(ss)], s)
//	}
//	for _, value := range hashMap {
//		res = append(res, value)
//	}
//	return res
//}

// 方法3：时间复杂度O(nk)
func groupAnagrams(strs []string) (res [][]string) {
	hashMap := make(map[[26]int][]string) //数组[26]int 作为key, 切片[]string作为值
	for _, s := range strs {
		key := generateKey(s)
		hashMap[key] = append(hashMap[key], s)
	}
	for _,  value := range hashMap {
		res = append(res, value)
	}
	return
}

func generateKey(s string) [26]int {
	key := [26]int{}
	for _, ch := range s {
		key[ch-'a']++
	}
	return key
}

//leetcode submit region end(Prohibit modification and deletion)


func main() {
	strs := []string{"eat", "tea", "tan", "ate", "nat", "bat"}
	res := groupAnagrams(strs)
	fmt.Printf("%v", res)
}