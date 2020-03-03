package main

import (
	"container/heap"
	"fmt"
)


func mergeKLists(lists []*ListNode) *ListNode {

	return nil
}

func main() {
	queue := make(PriorityQueue, 0)
	head := MakeList([]int{3,2,5,1,4})
	for head != nil {
		queue = append(queue, head)
		head = head.Next
	}
	//
	heap.Init(&queue)

	for queue.Len() > 0 {
		node := heap.Pop(&queue).(*ListNode)
		fmt.Println(node.Val)
	}
}
