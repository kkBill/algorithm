//给定一个 N 叉树，返回其节点值的前序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其前序遍历: [1,3,5,6,2,4]。 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 115 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func preorder(root *Node) []int {
    var res []int
	if root == nil {
		return res
	}
    var helper func(node *Node)
    helper = func(node *Node) {
    	if node != nil {
			res = append(res, node.Val)
			for _, child := range node.Children {
				helper(child)
			}
		}
	}
	helper(root)
    return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {
	root := NewTree()
	res := preorder(root)
	for _, v := range res {
		fmt.Printf("%d ", v)
	}
}