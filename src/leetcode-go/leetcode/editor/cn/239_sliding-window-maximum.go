//给定一个数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。 
//
//
// 返回滑动窗口中的最大值。 
//
// 
//
// 进阶： 
//
// 你能在线性时间复杂度内解决此题吗？ 
//
// 
//
// 示例: 
//
// 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
//输出: [3,3,5,5,6,7] 
//解释: 
//
//  滑动窗口的位置                最大值
//---------------               -----
//[1  3  -1] -3  5  3  6  7       3
// 1 [3  -1  -3] 5  3  6  7       3
// 1  3 [-1  -3  5] 3  6  7       5
// 1  3  -1 [-3  5  3] 6  7       5
// 1  3  -1  -3 [5  3  6] 7       6
// 1  3  -1  -3  5 [3  6  7]      7 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10^5 
// -10^4 <= nums[i] <= 10^4 
// 1 <= k <= nums.length 
// 
// Related Topics 堆 Sliding Window 
// 👍 637 👎 0


package main

import (
	"container/heap"
	"fmt"
)

//leetcode submit region begin(Prohibit modification and deletion)
// 方法1：暴力法，时间复杂度O(n*k)

// 方法2：最大堆，时间复杂度O(n*n)
// 这个方法超时了，并没有一开始认为的O(n*logk)，那是因为当删除堆中的任意一个元素时，
// 需要消耗O(n)的时间，因此，时间复杂度其实是O(n*n)
type Item struct {
	Value int
	Index int
}
type MaxHeap []*Item

func (h MaxHeap) Len() int {return len(h)}
func (h MaxHeap) Less(i, j int) bool {return h[i].Value > h[j].Value} // 最大堆
func (h MaxHeap) Swap(i, j int) {
	h[i], h[j] = h[j], h[i]
	h[i].Index = i
	h[j].Index = j
}

func (h *MaxHeap) Push(x interface{}) {
	item := x.(*Item)
	item.Index = len(*h)
	*h = append(*h, item)
}

func (h *MaxHeap) Pop() interface{} {
	n := h.Len()
	x := (*h)[n-1]
	*h = (*h)[:n-1]
	return x
}

// O(n)
func (h *MaxHeap) Remove(value int) {
	for _, item := range *h { // O(n)
		if item != nil && item.Value == value {
			heap.Remove(h, item.Index) // O(logn)
			break
		}
	}
}

func maxSlidingWindow(nums []int, k int) []int {
	var res []int
	var h MaxHeap
	heap.Init(&h)
	for i := 0; i < len(nums); i++ { // O(n)
		left := i - k + 1
		if left > 0 {
			h.Remove(nums[left-1]) // O(n)
		}
		heap.Push(&h, &Item{Value: nums[i]})
		if h.Len() == k {
			res = append(res, h[0].Value) // 取出队首元素
		}
	}
	return res
}

// 方法3：单调队列
//func maxSlidingWindow(nums []int, k int) []int {
//
//}
//leetcode submit region end(Prohibit modification and deletion)

func main() {
	nums := []int{1,3,-1,-3,5,3,6,7}
	res := maxSlidingWindow(nums, 3)
	fmt.Printf("%v\n", res)
}