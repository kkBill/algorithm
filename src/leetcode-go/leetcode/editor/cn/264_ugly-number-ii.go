//编写一个程序，找出第 n 个丑数。 
//
// 丑数就是质因数只包含 2, 3, 5 的正整数。 
//
// 示例: 
//
// 输入: n = 10
//输出: 12
//解释: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 是前 10 个丑数。 
//
// 说明: 
//
// 
// 1 是丑数。 
// n 不超过1690。 
// 
// Related Topics 堆 数学 动态规划 
// 👍 430 👎 0


package main

import (
	"container/heap"
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)

// 方法1：堆
// 利用最小堆（优先队列）的排序功能，来存储丑数，
// 假设当前丑数是uglyNum，然后将uglNum分别乘以2，3，5，存入最小堆中，取到的第n个不重复的堆顶元素就是所求结果
type MinHeap []int

func (h MinHeap) Len() int {return len(h)}
func (h MinHeap) Less(i, j int) bool {return h[i] < h[j]}
func (h MinHeap) Swap(i, j int) {h[i], h[j] = h[j], h[i]}
func (h *MinHeap) Pop() interface{} {
	n := len(*h)
	x := (*h)[n-1]
	*h = (*h)[:n-1]
	return x
}
func (h *MinHeap) Push(x interface{}) {
	*h = append(*h, x.(int))
}

func nthUglyNumber(n int) int {
	uglyNum := -1
	var h MinHeap
	heap.Init(&h)
	heap.Push(&h, 1)
	for n > 0 {
		// 去重
		for h[0] == uglyNum {
			heap.Pop(&h)
		}
		uglyNum = heap.Pop(&h).(int)
		heap.Push(&h, uglyNum*2)
		heap.Push(&h, uglyNum*3)
		heap.Push(&h, uglyNum*5)
		n--
	}
	return uglyNum
}

// 方法2：动态规划
//func nthUglyNumber(n int) int {
//
//}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	fmt.Printf("第 %d 个丑数是：%d\n", 1, nthUglyNumber(1)) // 1
	fmt.Printf("第 %d 个丑数是：%d\n", 2, nthUglyNumber(2)) // 2
	fmt.Printf("第 %d 个丑数是：%d\n", 10, nthUglyNumber(10)) // 12
}