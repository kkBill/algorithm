//给定一个二叉树，找出其最小深度。 
//
// 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。 
//
// 说明：叶子节点是指没有子节点的节点。 
//
// 
//
// 示例 1： 
//
// 
//输入：root = [3,9,20,null,null,15,7]
//输出：2
// 
//
// 示例 2： 
//
// 
//输入：root = [2,null,3,null,4,null,5,null,6]
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// 树中节点数的范围在 [0, 105] 内 
// -1000 <= Node.val <= 1000 
// 
// Related Topics 树 深度优先搜索 广度优先搜索 
// 👍 408 👎 0


package main

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

// 方法1：暴力递归，超时
//func minDepth(root *TreeNode) int {
//	if root == nil {
//		return 0
//	}
//	left := minDepth(root.Left)
//	right := minDepth(root.Right)
//	if root.Left == nil || root.Right == nil {
//		return left + right + 1
//	}
//	return 1 + min(minDepth(root.Left), minDepth(root.Right))
//}

// 方法2：层次遍历
func minDepth(root *TreeNode) int {
	if root == nil {
		return 0
	}
	queue := []*TreeNode{root} // 初始化队列，并把root节点入队
	depth := 0
	for len(queue) > 0 {
		size := len(queue) // 当前层的节点个数
		depth++
		for i := 0; i < size; i++ {
			node := queue[i]
			// 由于是层次遍历，当第一次遇到叶子节点时，必然是深度最小的，即可直接返回结果
			if node.Left == nil && node.Right == nil {
				return depth
			}
			if node.Left != nil {
				queue = append(queue, node.Left)
			}
			if node.Right != nil {
				queue = append(queue, node.Right)
			}
		}
		queue = queue[size:]
	}
	return depth
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {

}