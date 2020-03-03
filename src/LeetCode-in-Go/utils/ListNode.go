package main

import "fmt"

type ListNode struct {
	Val int
	Next *ListNode
}

func MakeList(nums []int) *ListNode {
	if len(nums) == 0 {
		return nil
	}
	dummy := &ListNode{-1, nil}
	curr := dummy
	for _, val := range nums {
		curr.Next = &ListNode{val, nil}
		curr = curr.Next
	}
	return dummy.Next
}

func PrintList(head *ListNode) {
	for head != nil {
		fmt.Printf("%d ", head.Val)
		head = head.Next
	}
}

func main() {
	var nums []int = []int{1,2,3,4,5}
	head := MakeList(nums)
	PrintList(head)
}


