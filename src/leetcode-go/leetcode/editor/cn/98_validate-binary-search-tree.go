//给定一个二叉树，判断其是否是一个有效的二叉搜索树。 
//
// 假设一个二叉搜索树具有如下特征： 
//
// 
// 节点的左子树只包含小于当前节点的数。 
// 节点的右子树只包含大于当前节点的数。 
// 所有左子树和右子树自身必须也是二叉搜索树。 
// 
//
// 示例 1: 
//
// 输入:
//    2
//   / \
//  1   3
//输出: true
// 
//
// 示例 2: 
//
// 输入:
//    5
//   / \
//  1   4
//     / \
//    3   6
//输出: false
//解释: 输入为: [5,1,4,null,null,3,6]。
//     根节点的值为 5 ，但是其右子节点值为 4 。
// 
// Related Topics 树 深度优先搜索 
// 👍 844 👎 0


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

// 关键性质：二叉搜索树的中序遍历是递增的
func isValidBST(root *TreeNode) bool {
	var prevNode *TreeNode
	var inorder func(node *TreeNode) bool
	inorder = func(node *TreeNode) bool {
		if node == nil {
			return true
		}
		// 根据中序遍历，先遍历左子树
		if !inorder(node.Left) {
			return false
		}
		// 判断当前节点与前一个节点的大小关系
		if prevNode != nil && node.Val <= prevNode.Val {
			return false
		}
		prevNode = node
		return inorder(node.Right)
	}
	return inorder(root)
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {

}