package utils

import (
	//"container/heap"
	"fmt"
)

type PriorityQueue []*ListNode

// 返回队列的长度
func (p *PriorityQueue) Len() int{
	return len(*p)
}

// 比较大小（这里实现最小堆）
func (p *PriorityQueue) Less(i, j int) bool {
	return (*p)[i].Val < (*p)[j].Val
}

//
func (p *PriorityQueue) Swap(i, j int) {
	(*p)[i], (*p)[j] = (*p)[j], (*p)[i]
}

//
func (p *PriorityQueue) Push(x interface{}) {
	node := x.(*ListNode) // ?
	*p = append(*p, node)
}

//
func (p *PriorityQueue) Pop() interface{} {
	old := *p
	n := len(old)
	item := old[n-1]
	*p = old[0:n-1]
	return item
}

func main() {
	queue := make(PriorityQueue, 0)
	head := MakeList([]int{3,2,5,1,4})
	for head != nil {
		queue.Push(head)
		head = head.Next
	}

	for queue.Len() > 0 {
		fmt.Println(queue.Pop())
	}
}
