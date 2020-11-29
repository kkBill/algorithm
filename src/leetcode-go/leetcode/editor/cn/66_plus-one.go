//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [0]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
// Related Topics 数组 
// 👍 581 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
// 主要关注边界条件，比如 99 + 1 = 100 这种会产生进位的情况
func plusOne(digits []int) []int {
	carry := 0 // 进位
	sum := 0
	for i := len(digits) - 1; i >= 0; i-- {
		if i == len(digits) - 1 {
			sum = carry + digits[i] + 1
		}else {
			sum = carry + digits[i]
		}
		if sum < 10 {
			digits[i]++
			return digits
		}else {
			digits[i] = sum % 10
			carry = sum / 10
		}
	}
	if carry > 0 {
		// https://stackoverflow.com/questions/46128016/insert-a-value-in-a-slice-at-a-given-index
		digits = append(digits[:1], digits...)
		digits[0] = carry
	}
	return digits
}
//leetcode submit region end(Prohibit modification and deletion)

func main() {
	res := plusOne([]int{9})
	fmt.Println(res)
	res = plusOne([]int{8,9})
	fmt.Println(res)
	res = plusOne([]int{9,9,9})
	fmt.Println(res)
	res = plusOne([]int{0})
	fmt.Println(res)
}