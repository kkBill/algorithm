//给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
//输出：6
//解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。 
// 
//
// 示例 2： 
//
// 
//输入：height = [4,2,0,3,2,5]
//输出：9
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 0 <= n <= 3 * 104 
// 0 <= height[i] <= 105 
// 
// Related Topics 栈 数组 双指针 
// 👍 1825 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
// 单调栈问题
func trap(height []int) int {
	var stack []int // 单调栈，存放下标
	total := 0
	for i, _ := range height {
        // 计算面积
		for len(stack) > 0 && height[i] > height[stack[len(stack)-1]] {
			// 栈顶元素出栈
			top := stack[len(stack)-1]
			stack = stack[:len(stack)-1] // pop
			if len(stack) > 0 {          // 栈非空
				fmt.Printf("%d %d %d ", height[stack[len(stack)-1]], height[top], height[i])
				w := i - stack[len(stack)-1] - 1
				h := min(height[i], height[stack[len(stack)-1]]) - height[top]
				fmt.Printf("%d\n", w*h)
				total += w * h
			}
		}
		stack = append(stack, i) // push
	}
	return total
}

func min(i int, j int) int {
	if i < j {
		return i
	} else {
		return j
	}
}

//leetcode submit region end(Prohibit modification and deletion)

func main() {
	height := []int{4, 2, 0, 3, 2, 5}
	fmt.Println(trap(height))

	height = []int{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}
	fmt.Println(trap(height))
}
