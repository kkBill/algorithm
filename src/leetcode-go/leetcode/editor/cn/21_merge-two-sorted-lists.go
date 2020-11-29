//将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
//
//
//
// 示例：
//
// 输入：1->2->4, 1->3->4
//输出：1->1->2->3->4->4
//
// Related Topics 链表
// 👍 1387 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
//Definition for singly-linked list.
type ListNode struct {
	Val int
	Next *ListNode
}

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	dummy := &ListNode{}
	prev := dummy
	for l1 != nil && l2 != nil {
		if l1.Val <= l2.Val {
			prev.Next = l1
			l1 = l1.Next
		}else {
			prev.Next = l2
			l2 = l2.Next
		}
		prev = prev.Next
	}
	for l1 != nil {
		prev.Next = l1
		l1 = l1.Next
		prev = prev.Next
	}
	for l2 != nil {
		prev.Next = l2
		l2 = l2.Next
		prev = prev.Next
	}
	return dummy.Next
}
//leetcode submit region end(Prohibit modification and deletion)

func makeLinkedList(nums []int ) *ListNode {
	dummy := &ListNode{}
	prev := dummy
	for _, v := range nums {
		prev.Next = &ListNode{
			Val:  v,
			Next: nil,
		}
		prev = prev.Next
	}
	return dummy.Next
}

func printList(l *ListNode) {
	for l != nil {
		fmt.Printf("%d ", l.Val)
		l = l.Next
	}
}

func main() {
	l1 := makeLinkedList([]int{1,3,5})
	l2 := makeLinkedList([]int{2,4,6})
	l3 := mergeTwoLists(l1, l2)
	printList(l3)
}