//给定一个 N 叉树，返回其节点值的层序遍历。 (即从左到右，逐层遍历)。 
//
// 例如，给定一个 3叉树 : 
//
// 
//
// 
//
// 
//
// 返回其层序遍历: 
//
// [
//     [1],
//     [3,2,4],
//     [5,6]
//]
// 
//
// 
//
// 说明: 
//
// 
// 树的深度不会超过 1000。 
// 树的节点总数不会超过 5000。 
// Related Topics 树 广度优先搜索 
// 👍 120 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)

func levelOrder(root *Node) [][]int {
	// 特判
	if root == nil {
		return [][]int{}
	}
	var res [][]int
	var q []*Node // 声明一个队列
	q = append(q, root) // 根节点入队
	for len(q) > 0 {
		size := len(q) // 当前层的元素个数
		var currLayer []int // 存放当前层的元素
		for i := 0; i < size; i++ {
			top := q[i] // 取出队首元素
			currLayer = append(currLayer, top.Val)
			for _, child := range top.Children { // 将该节点的孩子节点加入队列中
				if child != nil {
					q = append(q, child)
				}
			}
		}
		res = append(res, currLayer)
		q = q[size:] // 更新队列，相当于把当前层的元素删掉
	}
	return res
}

//leetcode submit region end(Prohibit modification and deletion)

/*
			1
        2       3
      4 5 6    7 8
*/
// debug
func NewTree() *Node {
	root := &Node{
		Val:      1,
		Children: make([]*Node, 0),
	}
	node2 := &Node{
		Val:      2,
		Children: make([]*Node, 0),
	}
	node3 := &Node{
		Val:      3,
		Children: make([]*Node, 0),
	}
	root.Children = append(root.Children, node2, node3)

	node4 := &Node{
		Val:      4,
		Children: make([]*Node, 0),
	}
	node5 := &Node{
		Val:      5,
		Children: make([]*Node, 0),
	}
	node6 := &Node{
		Val:      6,
		Children: make([]*Node, 0),
	}
	node2.Children = append(node2.Children, node4, node5, node6)

	node7 := &Node{
		Val:      7,
		Children: make([]*Node, 0),
	}
	node8 := &Node{
		Val:      8,
		Children: make([]*Node, 0),
	}
	node3.Children = append(node3.Children, node7, node8)
	return root
}

func main() {
	root := NewTree()
	res := levelOrder(root)
	for i := 0; i < len(res); i++ {
		for j := 0; j < len(res[i]); j++ {
			fmt.Printf("%d ", res[i][j])
		}
		fmt.Println()
	}
}
