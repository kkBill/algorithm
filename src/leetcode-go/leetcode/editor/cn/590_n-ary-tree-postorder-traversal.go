//给定一个 N 叉树，返回其节点值的后序遍历。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其后序遍历: [5,6,3,2,4,1]. 
//
// 
//
// 说明: 递归法很简单，你可以使用迭代法完成此题吗? Related Topics 树 
// 👍 112 👎 0


package main

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a Node.
 * type Node struct {
 *     Val int
 *     Children []*Node
 * }
 */

func postorder(root *Node) []int {
    var res []int
    if root == nil {
    	return res
	}

	var helper func(node *Node)
    helper = func(node *Node) {
    	if node != nil {
			for _, child := range node.Children {
				helper(child)
			}
			res = append(res, node.Val)
		}
	}
	helper(root)
	return res
}
//leetcode submit region end(Prohibit modification and deletion)


func main() {

}