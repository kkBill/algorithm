package main

import (
	"container/heap"
	"fmt"
	"utils"
)

type ListNode = utils.ListNode
type PriorityQueue = utils.PriorityQueue

func mergeKLists(lists []*ListNode) *ListNode {
	if len(lists) == 0 {
		return nil
	}
	priorityQueue := make(PriorityQueue, 0)
	for _, node := range lists {
		if node != nil {
			priorityQueue = append(priorityQueue, node)
		}
	}
	heap.Init(&priorityQueue)


	return nil
}

func main() {
	queue := make(utils.PriorityQueue, 0)
	head := utils.MakeList([]int{3,2,5,1,4})
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
