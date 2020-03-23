package main

import (
	"fmt"
)

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func MakeTree() *TreeNode {
	root := &TreeNode{Val: 7}
	root.Left = &TreeNode{Val: 3}
	root.Right = &TreeNode{Val: 5}
	return root
}

/*************************************/

type Entry struct {
	node *TreeNode
	pos  int
}

func widthOfBinaryTree(root *TreeNode) int {
	queue := make([]*Entry, 0);
	queue = append(queue, &Entry{root, 1})
	front := 0 // 队首指针
	rear := 0  // 队尾指针
	maxWidth := 0
	for rear >= front {
		width := queue[rear].pos - queue[front].pos + 1
		if width > maxWidth {
			maxWidth = width
		}
		size := rear - front + 1
		for i := 0; i < size; i++ {
			node := queue[front].node
			pos := queue[front].pos
			front++
			if node.Left != nil {
				queue = append(queue, &Entry{node.Left, 2*pos})
				rear++
			}
			if node.Right != nil {
				queue = append(queue, &Entry{node.Right, 2*pos + 1})
				rear++
			}
		}
	}
	return maxWidth
}

func main() {
	root := MakeTree()
	result := widthOfBinaryTree(root)
	fmt.Println(result)
}
