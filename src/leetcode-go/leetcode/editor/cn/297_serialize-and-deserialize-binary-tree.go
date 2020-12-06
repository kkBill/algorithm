//序列化是将一个数据结构或者对象转换为连续的比特位的操作，进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方
//式重构得到原数据。 
//
// 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串
//反序列化为原始的树结构。 
//
// 示例: 
//
// 你可以将以下二叉树：
//
//    1
//   / \
//  2   3
//     / \
//    4   5
//
//序列化为 "[1,2,3,null,null,4,5]" 
//
// 提示: 这与 LeetCode 目前使用的方式一致，详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这
//个问题。 
//
// 说明: 不要使用类的成员 / 全局 / 静态变量来存储状态，你的序列化和反序列化算法应该是无状态的。 
// Related Topics 树 设计 
// 👍 413 👎 0


package main

import (
	"fmt"
	"strconv"
	"strings"
)

//leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * type TreeNode struct {
 *     Val int
 *     Left *TreeNode
 *     Right *TreeNode
 * }
 */

type Codec struct {
	queue []*TreeNode
}

func CodecConstructor() Codec {
	return Codec{}
}

// Serializes a tree to a single string.
// 中序遍历
func (c *Codec) serialize(root *TreeNode) string {
	if root == nil {
		return ""
	}
	/*
	当需要频繁的进行数组的拼接时，直接使用 s += "xxx" 会很慢
	同Java中的StringBuilder一样，在Go中也有strings.Builder{}，建议使用strings.Builder{}
	*/
	sb := strings.Builder{}
	var lastCnt int // 计算最后一层节点个数
	c.queue = append(c.queue, root)
	for len(c.queue) > 0 {
		size := len(c.queue)
		lastCnt = size
		for i := 0; i < size; i++ {
			node := c.queue[i]
			if node != nil {
				sb.WriteString(strconv.Itoa(node.Val) + ",")
				c.queue = append(c.queue, node.Left)
				c.queue = append(c.queue, node.Right)
			}else {
				sb.WriteString("null,")
			}
		}
		c.queue = c.queue[size:]
	}
	s := sb.String()
	s = s[:len(s)-lastCnt*5-1]
	fmt.Println(s)
	return s
}

// Deserializes your encoded data to tree.
func (c *Codec) deserialize(data string) *TreeNode {
	if data == "" {
		return nil
	}
	strs := strings.Split(data, ",")
	i := 0
	val, _ := strconv.Atoi(strs[i])
	i++
	root := &TreeNode{ Val: val}
	c.queue = append(c.queue, root)
	for i < len(strs) {
		node := c.queue[0]
		c.queue = c.queue[1:]

		if strs[i] == "null" {
			node.Left = nil
		}else {
			val, _ = strconv.Atoi(strs[i])
			node.Left = &TreeNode{Val: val}
			c.queue = append(c.queue, node.Left)
		}
		i++

		if strs[i] == "null" {
			node.Right = nil
		}else {
			val, _ = strconv.Atoi(strs[i])
			node.Right = &TreeNode{Val: val}
			c.queue = append(c.queue, node.Right)
		}
		i++
	}
	return root
}


/**
 * Your Codec object will be instantiated and called as such:
 * ser := Constructor();
 * deser := Constructor();
 * data := ser.serialize(root);
 * ans := deser.deserialize(data);
 */
//leetcode submit region end(Prohibit modification and deletion)

/*
			 1
         2      3
      4    5       6
*/
func NewBinaryTree() *TreeNode {
	root := &TreeNode{Val: 1}
	root.Left = &TreeNode{Val: 2}
	root.Right = &TreeNode{Val: 3}
	root.Left.Left = &TreeNode{Val: 4}
	root.Left.Right = &TreeNode{Val: 5}
	root.Right.Right = &TreeNode{Val: 6}
	return root
}

func main() {
	root := NewBinaryTree()
	codec := CodecConstructor()
	decodec := CodecConstructor()
	data := codec.serialize(root)
	fmt.Printf("%s\n", data)
	root = decodec.deserialize(data)
	res := preorderTraversal(root)
	for _, v := range res {
		fmt.Printf("%d ", v)
	}
}