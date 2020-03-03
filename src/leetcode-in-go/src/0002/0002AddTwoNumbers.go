package main

import "utils"

type ListNode = utils.ListNode

func addTwoNumbers(l1 *ListNode, l2 *ListNode) *ListNode {
	carry := 0
	dummy := &ListNode{-1,nil}
	curr := dummy
	for l1 != nil || l2 != nil {
		sum := carry
		if l1 != nil {
			sum += l1.Val
			l1 = l1.Next
		}
		if l2 != nil {
			sum += l2.Val
			l2 = l2.Next
		}
		carry = sum / 10
		curr.Next = &ListNode{sum % 10, nil}
		curr = curr.Next
	}
	if carry != 0 {
		curr.Next = &ListNode{carry, nil}
	}
	return dummy.Next
}

func main() {
	l1 := utils.MakeList([]int{9,9,9})
	l2 := utils.MakeList([]int{1})
	res := addTwoNumbers(l1, l2)
	utils.PrintList(res)
}
