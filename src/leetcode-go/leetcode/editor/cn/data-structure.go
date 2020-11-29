package main

// n 叉树节点
type Node struct {
	Val      int
	Children []*Node
}

// 2 叉树节点
type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}
