//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针 
// 👍 863 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
func moveZeroes(nums []int)  {
	j := 0 // 指向第一个0元素
	for i, _ := range nums {
		if nums[i] != 0 { // 如果当前元素不为0，则与第一个0元素交换，同时更新第一个0元素的位置
			nums[j], nums[i] = nums[i], nums[j]
			j++
		}
	}
}
//leetcode submit region end(Prohibit modification and deletion)

func main() {
	nums := []int{0,1,0,3,12}
	moveZeroes(nums)
	fmt.Println(nums)
	nums = []int{0,0,0}
	moveZeroes(nums)
	fmt.Println(nums)
	nums = []int{1,2,3,4}
	moveZeroes(nums)
	fmt.Println(nums)
}
