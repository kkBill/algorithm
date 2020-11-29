//给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。 
//
// 示例 1: 
//
// 输入: s = "anagram", t = "nagaram"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s = "rat", t = "car"
//输出: false 
//
// 说明: 
//你可以假设字符串只包含小写字母。 
//
// 进阶: 
//如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？ 
// Related Topics 排序 哈希表 
// 👍 308 👎 0


package main

//leetcode submit region begin(Prohibit modification and deletion)
// 方法1：哈希法，借助map[]这个数据结构来实现
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

// 方法2：暴力法，字符串排序
//func isAnagram(s string, t string) bool {
//	s1, s2 := []byte(s), []byte(t)
//	sort.Slice(s1, func(i, j int) bool {return s1[i] < s1[j]})
//	sort.Slice(s2, func(i, j int) bool {return s2[i] < s2[j]})
//	return string(s1) == string(s2)
//}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	//fmt.Printf("%v\n", isAnagram("abc","cba"))
	//fmt.Printf("%v\n", isAnagram("car","tar"))
}