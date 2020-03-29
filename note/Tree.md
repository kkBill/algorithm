### 树问题

题目列表

|      | 题号                                       | 评注                                       |
| ---- | ---------------------------------------- | ---------------------------------------- |
| 1    | [94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) | 树的遍历，要熟练掌握迭代写法                           |
| 2    | [144. Binary Tree Preorder Traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/) |                                          |
| 3    | [145. Binary Tree Postorder Traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/) |                                          |
|      | [102. Binary Tree Level Order Traversal](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) | 层序遍历，借助队列实现                              |
|      | [103. Binary Tree Zigzag Level Order Traversal](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/) | 巧用链表的头插法实现逆序                             |
|      | [107. Binary Tree Level Order Traversal II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/) | 同103题                                    |
|      |                                          | 卡特兰数♥♥                                   |
|      |                                          | ♥♥♥                                      |
|      |                                          |                                          |
|      |                                          |                                          |
|      |                                          |                                          |
|      | [100. Same Tree](https://leetcode-cn.com/problems/same-tree/) | ♥                                        |
|      | [101. Symmetric Tree](https://leetcode-cn.com/problems/symmetric-tree/) | 本题是第100题的变形♥♥                            |
|      | [112. Path Sum](https://leetcode-cn.com/problems/path-sum/) | ♥♥♥                                      |
|      | [113. Path Sum II](https://leetcode-cn.com/problems/path-sum-ii/) | ♥♥♥                                      |
|      | [437. Path Sum III](https://leetcode-cn.com/problems/path-sum-iii/) | **[前缀和]( <https://oi-wiki.org/basic/prefix-sum/)**的应用♥♥♥♥♥(第一次见) |
|      | [114. Flatten Binary Tree to Linked List](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/) | ⭐⭐⭐⭐⭐                                    |
|      | [116. Populating Next Right Pointers in Each Node](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/) | ⭐⭐⭐⭐⭐                                    |
|      | [117. Populating Next Right Pointers in Each Node II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/) | ⭐⭐⭐⭐⭐极致巧用dummy                           |
|      | [199. Binary Tree Right Side View](https://leetcode-cn.com/problems/binary-tree-right-side-view/) | 求叉树的右侧节点, 递归法不容易                         |
|      | [226. Invert Binary Tree](https://leetcode-cn.com/problems/invert-binary-tree/) |                                          |
|      |                                          |                                          |
|      | [124. Binary Tree Maximum Path Sum](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/) | 任意节点组成的最大点权和♥♥♥♥♥                        |
|      | [257. Binary Tree Paths](https://leetcode-cn.com/problems/binary-tree-paths/) | 树的所有路径♥♥♥                                |
|      | [129. Sum Root to Leaf Numbers](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/) | 和257题其实是一回事                              |
|      | [104. Maximum Depth of Binary Tree](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) | 树的最大高度♥                                  |
|      | [111. Minimum Depth of Binary Tree](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/) | 树的最小高度, 多种方法                             |
|      | [543. Diameter of Binary Tree](https://leetcode-cn.com/problems/diameter-of-binary-tree/) | 五星, 树的最大直径♥♥♥                            |
|      | [110. Balanced Binary Tree](https://leetcode-cn.com/problems/balanced-binary-tree/) | 和543题类似，自底向上法♥♥♥                         |
|      | [662. Maximum Width of Binary Tree](https://leetcode-cn.com/problems/maximum-width-of-binary-tree/) | 五星, 树的最大宽度♥♥                             |
|      |                                          |                                          |
|      | [617. Merge Two Binary Trees](https://leetcode-cn.com/problems/merge-two-binary-trees/) | 合并两个二叉树, 前序遍历的应用                         |
|      | 二叉搜索树相关：                                 |                                          |
|      |                                          |                                          |
|      |                                          |                                          |

普通二叉树：

1. ​



二叉搜索树：

1. [95. Unique Binary Search Trees II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/)
2. [96. Unique Binary Search Trees](https://leetcode-cn.com/problems/unique-binary-search-trees/)
3. [98. Validate Binary Search Tree](https://leetcode-cn.com/problems/validate-binary-search-tree/)
4. [99. Recover Binary Search Tree](https://leetcode-cn.com/problems/recover-binary-search-tree/)
5. [108. Convert Sorted Array to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)
6. [173. Binary Search Tree Iterator](https://leetcode-cn.com/problems/binary-search-tree-iterator/) 
7. [230. Kth Smallest Element in a BST](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)
8. [450. Delete Node in a BST](https://leetcode-cn.com/problems/delete-node-in-a-bst/)

平衡二叉树：

1. [110. Balanced Binary Tree](https://leetcode-cn.com/problems/balanced-binary-tree/) [五星]

完全二叉树：

1. [222. Count Complete Tree Nodes](https://leetcode-cn.com/problems/count-complete-tree-nodes/)
2. [958. Check Completeness of a Binary Tree](https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/)

---

#### 普通二叉树

二叉树的前序遍历、中序遍历、后序遍历的递归和迭代写法，以及层次遍历。

二叉树的很多问题均有四种遍历扩展而来，所以要非常熟悉，其中迭代写法由于自己平时写的不多，要尤为注意。一些问题用递归不太直观，用迭代遍历则很好理解。

除了二叉树的遍历，还有通过“前序序列+中序序列”、“后序序列+中序序列”或“层序序列+中序序列”构建二叉树也是必须要掌握的。

树节点的定义

```java
 public class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode(int x) { val = x; }
 }
```

（1）前序遍历

递归写法

```java
public List<Integer> preorderTraversal(TreeNode root) {
	List<Integer> result = new ArrayList<>();
  	helper(root, result);
  	return result;
}
public void helper(TreeNode root, List<Integer> result) {
  	if(root == null) return;
  	result.add(root.val);
	helper(root.left, result);
  	helper(root.right, result);
}
```

非递归写法

```java
public List<Integer> preorderTraversal(TreeNode root) {
	List<Integer> result = new ArrayList<>();
  	if(root == null) return result;	
  
  	Stack<TreeNode> s = new Stack<>();
  	TreeNode p = root;
  	while(p != null || !s.empty()) {
      	if(p != null) {
        	result.add(p.val);
          	s.push(p);
          	p = p.left;
        }else {
          	p = s.pop();
          	p = p.right;
        }
  	}
  	return result;
}
```



（2）中序遍历

递归写法

```java
public List<Integer> inorderTraversal(TreeNode root) {
	List<Integer> result = new ArrayList<>();
  	helper(root, result);
  	return result;
}
public void helper(TreeNode root, List<Integer> result) {
  	if(root == null) return;
	helper(root.left, result);
  	result.add(root.val);
  	helper(root.right, result);
}
```

非递归写法

```java
public List<Integer> inorderTraversal(TreeNode root) {
	List<Integer> result = new ArrayList<>();
  	Stack<TreeNode> s = new Stack<>();
  	TreeNode p = root;
  	while(p != null || !s.empty()){
    	if(p != null){
      		s.push(p);
      		p = p.left;
    	}else{
      		p = s.pop();
      		result.add(p.val);
      		p = p.right;
    	}
  	}
  	return result;
}
```



（3）后序遍历

递归写法

```java
public List<Integer> postorderTraversal(TreeNode root) {
	List<Integer> result = new ArrayList<>();
  	helper(root, result);
  	return result;
}
public void helper(TreeNode root, List<Integer> result) {
  	if(root == null) return;
	helper(root.left, result);
  	helper(root.right, result);
  	result.add(root.val);
}
```

**非递归写法（重点）**

```java
public List<Integer> postorderTraversal(TreeNode root) {
	List<Integer> result = new ArrayList<>();
 	if(root == null) return result;
  	
  	TreeNode p = root;
  	TreeNode lastVisitNode = null;//标记最后一次被访问的节点
  	Stack<TreeNode> s = new Stack<>();
  	while(p != null || !s.empty()) {
      	if(p != null) {
        	s.push(p);
          	p = p.left;
        }else {
            TreeNode temp = s.peek();
          	if(temp.right != null && temp.right != lastVisitNode) {
            	p = temp.right;
            }else {
              	result.add(temp.val);
              	s.pop();
              	lastVisitNode = temp;
            	p = null;
            }
        }
  	}// while
  	return result;
}
```

相关题目：

[94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)

[144. Binary Tree Preorder Traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

[145. Binary Tree Postorder Traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)



（4）层次遍历

层次遍历二叉树需要借助队列

```java
/**
 * 层序遍历二叉树
 * 关键在于如何确定每一层对应有哪些节点，这里以每一行作为一个遍历单元，
 * 核心代码在 for{} 处，当前队列中存放的节点就是上一层遍历时存进去的节点
 */
public List<List<Integer>> levelOrder(TreeNode root) {
	List<List<Integer>> result = new ArrayList<>();
  	if (root == null) return result;

  	List<TreeNode> queue = new ArrayList<>();
  	queue.add(root);
  	while (!queue.isEmpty()) {
    	int size = queue.size();//当前这一层的节点数量
    	List<Integer> levelNode = new ArrayList<>();
    	for (int i = 0; i < size; i++) {
      		TreeNode node = queue.remove(0);
      		levelNode.add(node.val);
      		if (node.left != null) {
        		queue.add(node.left);
      		}
      		if (node.right != null) {
       			queue.add(node.right);
      		}
    	}
   		result.add(levelNode);
  	}
  	return result;
}
```

相关题目：

[102. Binary Tree Level Order Traversal](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)



（5）通过前序序列+中序序列构建二叉树（模板题）

前序序列的首个元素是根节点，在中序序列中找到根节点，划分出左右子区间，然后递归进行处理。

```java
public TreeNode buildTree(int[] preorder, int[] inorder) {
	return build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
}
private TreeNode build(int[] preorder, int pl, int pr, int[] inorder, int il, int ir) {
    // 边界条件
    if (pl > pr || il > ir) return null;
    // 确定root节点
    int rootVal = preorder[pl];
    TreeNode root = new TreeNode(rootVal);
    int pos = il;
    while (inorder[pos] != rootVal) pos++; // pos 最终停留在中序序列root节点的位置

    int leftNum = pos - il; // 左子树的节点个数
    root.left = build(preorder, pl + 1, pl + leftNum, inorder, il, pos - 1);
    root.right = build(preorder, pl + leftNum + 1, pr, inorder, pos + 1, ir);
    return root;
}
```

相关题目：

[105. Construct Binary Tree from Preorder and Inorder Traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

（6）通过中序序列+后序序列构建二叉树

原理同上

```java
public TreeNode buildTree(int[] inorder, int[] postorder) {
    return build(inorder,0,inorder.length-1,postorder,0,postorder.length-1);
}
private TreeNode build(int[] inorder, int il, int ir, int[] postorder, int pl, int pr) {
	if(il > ir || pl > pr) return null;
	int rootVal = postorder[pr];
  	TreeNode root = new TreeNode(rootVal);
    int pos = il;
    while (inorder[pos] != rootVal) pos++;
    
  	int leftNum = pos - il;
	root.left = build(inorder,il,pos-1,postorder,pl,pl+leftNum-1);
  	root.right = build(inorder,pos+1,ir,postorder,pl+leftNum,pr-1);
	return root;
}
```

相关题目：

[106. Construct Binary Tree from Inorder and Postorder Traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/)

（7）通过层序序列+中序序列构建二叉树



（8）通过前序序列+后序序列构建二叉树

仅根据前序序列和后序序列并**不能确定唯一的二叉树**，只需要构建一个符合要求的即可。乍一看觉得没有思路，但思考的切入点还是之前几题一样的。

分析：

```java
public TreeNode constructFromPrePost(int[] pre, int[] post) {
    return build(pre, 0, pre.length - 1, post, 0, post.length - 1);
}

private TreeNode build(int[] pre, int preL, int preR, int[] post, int postL, int postR) {
    if (preL > preR) return null;
    if (preL == preR) return new TreeNode(pre[preL]);

    TreeNode root = new TreeNode(pre[preL]);
    int childIndex = preL + 1;
    int childVal = pre[childIndex];
    int pos = postL;
    while (post[pos] != childVal) pos++;

  	// 以root为根节点的树只含有一个孩子节点（根据题意，我们可以任意指定为左孩子（或右孩子））
  	if (preR - childIndex == pos - postL) {
    	root.left = null;
    	root.right = build(pre, childIndex, preR, post, postL, pos);
  	} else {
       	int leftNum = pos - postL + 1;// 以root为根节点的左子树的节点个数
    	root.left = build(pre, childIndex, childIndex + leftNum - 1, post, postL, pos);
    	root.right = build(pre, childIndex + leftNum, preR, post, pos + 1, postR - 1);
  	}
  	return root;
}
```

相关题目：

[889. Construct Binary Tree from Preorder and Postorder Traversal](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-postorder-traversal/)





##### [94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) 

二叉树的中序遍历。

递归版本

```
略
```

非递归版本

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            }else {
                p = stack.pop();
                result.add(p.val);
                p = p.right;
            }
        }
        return result;
    }
}
```



##### [144. Binary Tree Preorder Traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)

二叉树的前序遍历。

递归版本

```
略
```

非递归版本

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root;
        while (p != null || !stack.isEmpty()) {
            if(p != null) {
                result.add(p.val);
                stack.push(p);
                p = p.left;
            }else {
                p = stack.pop();
                p = p.right;
            }
        }
        return result;
    }
}
```



##### [145. Binary Tree Postorder Traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)

二叉树的后序遍历。

递归版本

```
略
```

非递归版本

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode p = root, lastVisitNode = null;
        while (p != null || !stack.isEmpty()) {
            if(p != null) {
                stack.push(p);
                p = p.left;
            }else {
                p = stack.peek();
                if(p.right != null && p.right != lastVisitNode) {
                    p = p.right;
                    continue;
                }
                p = stack.pop();
                result.add(p.val);
                lastVisitNode = p;
                p = null;
            }
        }
        return result;
    }
}
```



##### [102. Binary Tree Level Order Traversal](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/) [一星]

二叉树的层次遍历

```java
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();
        
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> line = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                line.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            result.add(line);
        }
        return result;
    }
}
```



##### [103. Binary Tree Zigzag Level Order Traversal](https://leetcode-cn.com/problems/binary-tree-zigzag-level-order-traversal/) [二星]

之字形层次遍历。

分析：巧用链表。还是正常的进行层序遍历，只不过对于奇数行，则采用尾插法，即顺序存储；对于偶数行，则采用头插法，即逆序存储。从而巧妙地实现了之字形遍历。

```java
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if(root == null) return new LinkedList<>();
        
        List<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            // 必须要LinkedList类型
            // 写成 List<Integer> line = new LinkedList<>(); 就不能调用addFirst()这类api
            LinkedList<Integer> line = new LinkedList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                if(level % 2 == 1){
                    line.addLast(node.val); // 尾插法
                }else {
                    line.addFirst(node.val); // 头插法
                }
                
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            result.add(line);
        }
        return result;        
    }
}
```



##### [107. Binary Tree Level Order Traversal II](https://leetcode-cn.com/problems/binary-tree-level-order-traversal-ii/) [一星]

层次遍历，要求自底向上逐行输出。

```java
class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if(root == null) return new LinkedList<>();
        
        LinkedList<List<Integer>> result = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            level++;
            List<Integer> line = new ArrayList<>();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.remove();
                line.add(node.val);
                if(node.left != null) queue.add(node.left);
                if(node.right != null) queue.add(node.right);
            }
            result.addFirst(line); // 逆序存储
        }
        return result;        
    }
}
```





##### [100. Same Tree](https://leetcode-cn.com/problems/same-tree/) [一星]

给定两棵树，判断它们是否相同。两棵树相同要求两个树在结构上相同，并且节点具有相同的值。

分析：其实就是考察二叉树的遍历，只不过这里同时对两个树进行遍历。判断两个树是否相等可以分成以下3类：

* 如果 p 为空，且 q 也为空，则返回true，这是边界情况，应该没什么好解释的
* 如果 p 非空，且 q 也非空，那么就要判断这两节点的值是否相等
  * 如果不相等，直接返回false；
  * 如果相等，则继而递归的判断 p, q 的左子树和 p, q 的右子树；
* 最后一种情况就是p, q一个为空，一个不为空，这种情况直接返回false。

```java
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }else if(p != null && q != null) {
            if(p.val != q.val) return false;
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        }else {
            return false;
        }
    }
}
```



##### [101. Symmetric Tree](https://leetcode-cn.com/problems/symmetric-tree/) [四星]

这是第100题的变形，思路是一样的。

```
class Solution {
    public boolean isSymmetric(TreeNode root) {
        if(root == null) return true;
        return helper(root.left, root.right);
    }
    
    private boolean helper(TreeNode p, TreeNode q) {
        if(p == null && q == null){
            return true;
        }else if(p != null && q != null) {
            if(p.val != q.val) return false;
            return helper(p.left, q.right) && helper(p.right, q.left);
        }else {
            return false;
        }
    }
}
```



##### [112. Path Sum](https://leetcode-cn.com/problems/path-sum/) [五星]

给定一个二叉树和一个目标和，判断该树中是否存在根节点到叶子节点的路径，这条路径上所有节点值相加等于目标和。

```java
class Solution {
       // 第一遍写怎么写成这个鸟样子~ 也太丑了~
//     public boolean hasPathSum(TreeNode root, int sum) {
//         if(root == null) return false;
//         return dfs(root, sum-root.val);
//     }

//     private boolean dfs(TreeNode root, int curSum) {
//         if (root.left == null && root.right == null) {
//             return curSum == 0;
//         }
//         if (root.left != null && root.right == null) {
//             return dfs(root.left, curSum - root.left.val);
//         } else if (root.left == null && root.right != null) {
//             return dfs(root.right, curSum - root.right.val);
//         } else {
//             return dfs(root.left, curSum - root.left.val) 
//                       || dfs(root.right, curSum - root.right.val);
//         }
//     }
    
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null) return sum == root.val;
        return hasPathSum(root.left, sum-root.val)
                || hasPathSum(root.right, sum-root.val);
    }
}
```



##### [113. Path Sum II](https://leetcode-cn.com/problems/path-sum-ii/) [五星]

给定一个二叉树和一个目标和，找到所有从根节点到叶子节点路径总和等于给定目标和的**路径**。

方法1：DFS递归实现。

```java
// 写法1
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return result;
        List<Integer> path = new ArrayList<>();
        path.add(root.val);
        dfs(root, sum-root.val, path);
        return result;
    }
    
    private void dfs(TreeNode root, int curSum, List<Integer> path) {
        if(root.left == null && root.right == null) {
            if(curSum == 0) {
                result.add(new ArrayList<>(path));
                return;
            }
        }
        if(root.left != null) {
            path.add(root.left.val);
            dfs(root.left, curSum-root.left.val, path);
            path.remove(path.size()-1);
        }
        if(root.right != null) {
            path.add(root.right.val);
            dfs(root.right, curSum-root.right.val, path);
            path.remove(path.size()-1);
        }
    }
}

// 写法2，更简洁一点
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return result;
        List<Integer> path = new ArrayList<>();
        dfs(root, sum, path);
        return result;
    }
    
    private void dfs(TreeNode root, int curSum, List<Integer> path) {
        path.add(root.val);
        if(root.left == null && root.right == null) {
            if(curSum == root.val) {
                result.add(new ArrayList<>(path));
                return;
            }
        }
        if(root.left != null) {
            dfs(root.left, curSum-root.val, path);
            path.remove(path.size()-1);
        }
        if(root.right != null) {
            dfs(root.right, curSum-root.val, path);
            path.remove(path.size()-1);
        }
    }
}
```

方法2：后续遍历的非递归实现。在后序遍历的非递归实现中，当访问到叶节点时，栈中存放的恰好是根节点至叶节点的路径，这是最重要的性质。

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;
        
        List<Integer> path = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode lastVisit = null;
        TreeNode p = root;
        while(p != null || !stack.isEmpty()) {
            if(p != null) {
                stack.push(p);
                path.add(p.val);
                sum -= p.val;
                p = p.left;
            }else {
                p = stack.peek();
                if(p.right != null && p.right != lastVisit) {
                    p = p.right;
                    continue;
                }
                p = stack.pop();
                // 如果访问到叶节点，进行判断
                if(p.left == null && p.right == null && sum == 0) {
                    result.add(new ArrayList<>(path));
                }
                path.remove(path.size()-1);
                sum += p.val;
                
                lastVisit = p;
                p = null;
            }
        }
        return result;
    }
}
```



##### [437. Path Sum III](https://leetcode-cn.com/problems/path-sum-iii/) [五星]

给定一个二叉树，它的每个结点都存放着一个整数值。找出路径和等于给定数值的路径总数。**路径不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的**（**只能从父节点到子节点**）。

二叉树不超过1000个节点，且节点数值范围是 [-1000000,1000000] 的整数。示例：

```
root = [10,5,-3,3,2,null,11,3,-2,null,1], sum = 8

      10
     /  \
    5   -3
   / \    \
  3   2   11
 / \   \
3  -2   1

返回 3。和等于 8 的路径有:
1.  5 -> 3
2.  5 -> 2 -> 1
3.  -3 -> 11
```

分析：参考[这里](https://leetcode.com/problems/path-sum-iii/discuss/91878/17-ms-O(n)-java-Prefix-sum-method)。

```java
class Solution {
    public int pathSum(TreeNode root, int sum) {
        // key: prefix sum till current node
        // value: the numbers of path which path sum equals key
        Map<Integer, Integer> map = new HashMap<>();
        // 初始化<0,1> 是为了保证根节点至叶节点之和恰等于sum的情况
        map.put(0, 1);
        return dfs(root, 0, sum, map);
    }

    private int dfs(TreeNode root, int curSum, int target, Map<Integer, Integer> map) {
        if (root == null) return 0;
        curSum += root.val;

        // 以本节点为终点，存在路径和等于target的路径条数
        int count = map.getOrDefault(curSum - target, 0);

        map.put(curSum, map.getOrDefault(curSum, 0) + 1);

        // 以本节点为起点，存在路径和等于target的路径条数
        // 递归计算其左右子树
        int lCount = dfs(root.left, curSum, target, map);
        int rCount = dfs(root.right, curSum, target, map);

        // 回溯
        map.put(curSum, map.getOrDefault(curSum, 0) - 1);

        return count + lCount + rCount;
    }
}
```



##### [114. Flatten Binary Tree to Linked List](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/) [五星++]

题目：将二叉树展开为一个链表，如下所示：

```
    1
   / \
  2   5
 / \   \
3   4   6
   ||
   \/	
1
 \
  2
   \
    3
     \
      4
       \
        5
         \
          6   

```

**方法1：迭代法**

展开后的链表就是原二叉树前序遍历的顺序，所以我们可以顺着前序遍历的思路开始思考。根据题意，如果左子树非空，就将左子树置为根节点的右子树，而原来的右子树则跟在左子树前序序列的最后一个节点。其原理示意图如下：

![image.png](https://pic.leetcode-cn.com/a32c919ea2e3a3211696d1f1ea81ad7ed9dc1829d984c412fb523fae84174d3a-image.png)


我们举一个具体的例子，看看大概的执行过程：（ps：图画得我好累~）

step 1:

![image.png](https://pic.leetcode-cn.com/69d0c484e893e5c56a0f37eab8fedb15d7d3276046399509b473c11480d3c379-image.png)

step 2:

![image.png](https://pic.leetcode-cn.com/03fc792903ce989659961603ee48f0094cc6f6f91f7e11555c9816d75f0a4853-image.png)

step 3:

![image.png](https://pic.leetcode-cn.com/00751b7ce9d086fc2f6c8b6e8934efccd908a1d6ae3505dbe6de02376e978f38-image.png)


step 4: 图略，同step 3一样，只是更新了curr

step 5:

![image.png](https://pic.leetcode-cn.com/c8da81b9401fe14779096df4462a5bff569020ef040803939bf81035a8f3b641-image.png)


至此，别的我就不多说了，结合代码好好体会，并且自己也模拟一遍。

代码：

```java
class Solution {
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while(curr != null) {
            if(curr.left != null) {
                // find rightest node of left subtree
                TreeNode last = curr.left;
                while(last.right != null) {
                    last = last.right;
                }
                // reconnect
                last.right = curr.right;
                curr.right = curr.left;
                curr.left = null; // don't forget
            }
            // update curr
            curr = curr.right;
        }
    }
}
```

**方法2：递归法**
要说迭代法自己还能想想，递归法我是完全想不出来，并且在第二次写的时候仍然没有想到这个方法。答案来自leetcode原站点高票回答。

这种方法的关键思想在于通过递归**自底向上**实现，本质上和后序遍历没有区别，只不过这里稍稍做了一些变形，遍历的顺序为：右子树 -> 左子树 -> 根。

代码：

```java
class Solution {    
    TreeNode prev = null;
    public void flatten(TreeNode root) {
        if(root == null) return;
        flatten(root.right);
        flatten(root.left);
        root.right = prev;
        root.left = null;
        prev = root;
    }
}
```
过程示意图：(示意图也是直接搬运的...LOL)

        1
       / \
      2   5
     / \   \
    3   4   6
    -----------        
    pre = 5
    cur = 4
    
        1
       / 
      2   
     / \   
    3   4
         \
          5
           \
            6
    -----------        
    pre = 4
    cur = 3
    
        1
       / 
      2   
     /   
    3 
     \
      4
       \
        5
         \
          6
    -----------        
    cur = 2
    pre = 3
    
        1
       / 
      2   
       \
        3 
         \
          4
           \
            5
             \
              6
    -----------        
    cur = 1
    pre = 2
    
    1
     \
      2
       \
        3
         \
          4
           \
            5
             \
              6    
参考：

1. [https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/37010/Share-my-simple-NON-recursive-solution-O(1)-space-complexity!](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/37010/Share-my-simple-NON-recursive-solution-O(1)-space-complexity!)
2. [https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36977/My-short-post-order-traversal-Java-solution-for-share](https://leetcode.com/problems/flatten-binary-tree-to-linked-list/discuss/36977/My-short-post-order-traversal-Java-solution-for-share)

自己在时隔2个月后重写这个题，意识里知道这个题可以用递归来做，但是又想不出上面说的那个精妙的递归解法。根据自己的思路，写出了如下这个杂交版的答案。

```java
class Solution {
    public void flatten(TreeNode root) {
        dfs(root);
    }
    
    private TreeNode dfs(TreeNode root) {
        if(root == null) return null;
        TreeNode left = dfs(root.left);
        TreeNode right = dfs(root.right);
        if(left != null) {
            TreeNode curr = left;
            while(curr.right != null) {
                curr = curr.right;
            }
            curr.right = right;
            root.right = left;
            root.left = null;
        }
        return root;
    }
}
```





##### [116. Populating Next Right Pointers in Each Node](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/)

##### [117. Populating Next Right Pointers in Each Node II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/) [五星++]

这两题要求将二叉树的**每一层**的节点自左向右连接起来，如下图所示（其中116题是完全二叉树，117题是普通的二叉树），并且要求**只使用O(1)的空间复杂度**。![img](https://assets.leetcode.com/uploads/2019/02/15/117_sample.png)

在本题中，节点的定义为：

```C++
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

**方法1**：如果不考虑空间复杂度的要求，那么借助队列，通过层序遍历，可以比较直观的写出代码。如下所示，这段代码适用于116, 117两题。

```java
public Node connect(Node root) {
    if (root == null) return null;

    List<Node> queue = new ArrayList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
        int size = queue.size();
        Node pre = null;
        for(int i=0; i<size; i++){
            Node curr = queue.remove(0);
            if(i > 0) pre.next = curr;
            pre = curr;
            if(curr.left != null) queue.add(curr.left);
            if(curr.right != null) queue.add(curr.right);
        }
    }
    return root;
}
```

**方法2**：空间复杂度为O(1)的做法，参考[这里](https://www.cnblogs.com/grandyang/p/4290148.html) 。这种做法实属精妙~



```java
class Solution {
    public Node connect(Node root) {
        Node dummy = new Node(0, null, null, null);
        Node origin = root, curr = dummy;
        while(root != null) {
            if(root.left != null) {
                curr.next = root.left;
                curr = curr.next;
            }
            if(root.right != null) {
                curr.next = root.right;
                curr = curr.next;
            }
            root = root.next;
            if(root == null) { // 说明当前层遍历完毕，进入下一层
                root = dummy.next;
                dummy.next = null;
                curr = dummy;
            }
        }
        return origin;
    }
}
```

下面是仅适用于116题（也就是满二叉树）的解法。

```java
class Solution {
    public Node connect(Node root) {
        if(root == null) return null;
        // 固定first为每一层的第一个节点，curr用于遍历节点
        Node first = root, curr;
        while(first.left != null) {
            curr = first;
            while(curr != null) {
                curr.left.next = curr.right; // 连接以curr为根节点的左右孩子节点
                if(curr.next != null) { // 连接跨父节点的孩子节点
                    curr.right.next = curr.next.left;
                }
                curr = curr.next;
            }
            first = first.left; // 进入下一层
        }
        return root;
    }
}
```



##### [199. Binary Tree Right Side View](https://leetcode-cn.com/problems/binary-tree-right-side-view/) [递归实现, 五星++]

给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。

```
输入: [1,2,3,null,5,null,4]
输出: [1, 3, 4]
解释:

   1            <---
 /   \
2     3         <---
 \     \
  5     4       <---
```

**方法1**：采用层序遍历，每一层取最右边的那个值即可。

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;
        List<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.remove(0);
                if (i == size - 1) result.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
        }
        return result;
    }    
}
```

**方法2**：采用深度遍历，递归实现。这种方法不容易想到，参考评论区的，学到了。

```java
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, 0, res);
        return res;
    }
    private void dfs(TreeNode root, int depth, List<Integer> res) {
        if(root == null) return;
        if(depth == res.size()) { // 这个边界条件是关键
            res.add(root.val);
        }
        dfs(root.right, depth+1, res); // 先遍历右侧
        dfs(root.left, depth+1, res);
    }
}
```



##### [226. Invert Binary Tree](https://leetcode-cn.com/problems/invert-binary-tree/)

翻转二叉树

方法1：后序遍历递归实现（最常用）

```java
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        // invert opration
        root.left = right;
        root.right = left;
        return root;
    }
}
```

方法2：前序遍历递归实现

方法3：中序遍历递归实现

方法4：层序遍历实现





##### [124. Binary Tree Maximum Path Sum](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/) [五星]

计算二叉树任意节点之间的路径之和最大值。本题属于二叉树类问题中比较难的一题，难点在于这里的**路径被定义为一条从树中任意节点出发，达到任意节点的序列**；而不是根节点到某一叶子节点。

分析：题解参考[这里](https://leetcode.com/problems/binary-tree-maximum-path-sum/discuss/39775/Accepted-short-solution-in-Java) 。

```java
class Solution {
    private int maxSum = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if(root == null) return 0;
        dfs(root);
        return maxSum;
    }
    
    private int dfs(TreeNode root) {
        if(root == null) return 0;
        int left = Math.max(dfs(root.left), 0);
        int right = Math.max(dfs(root.right), 0);
        maxSum = Math.max(maxSum, root.val + left + right);
        return Math.max(left, right) + root.val;
    }
}
```

解释：

```java
maxSum = Math.max(maxSum, root.val+left+right);
return Math.max(left, right) + root.val;
```

maxSum is the value which recording whether this current root is the final root, so we use `left + right + node.val`. But to the upper layer(after return statement), we can only choose one brunches, we cannot choose both left and right brunches, so we need to select the larger one, so we use `max(left, right) + node.val` to prune the lower brunch.





##### [257. Binary Tree Paths](https://leetcode-cn.com/problems/binary-tree-paths/) 

给定一个二叉树，返回所有从根节点到叶子节点的路径。

```
输入:

   1
 /   \
2     3
 \
  5

输出: ["1->2->5", "1->3"]
解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3
```

**写法1**：

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        if(root == null) return res;
        
        dfs(root, new ArrayList<>(), res);
        return res;
    }
    
    private void dfs(TreeNode root, List<Integer> path, List<String> res) {
        path.add(root.val);
        if(root.left == null && root.right == null) {
            StringBuilder sb = new StringBuilder();
            int size = path.size();
            for(int i = 0; i < size; i++) {
                sb.append(path.get(i));
                if(i < size-1 ) sb.append("->");
            }
            res.add(sb.toString());
            return;
        }
        if(root.left != null) {
            dfs(root.left, path, res);
            path.remove(path.size()-1);
        }
        if(root.right != null) {
            dfs(root.right, path, res);
            path.remove(path.size()-1);
        }
    }
}
```

**写法2**：不建议这么做，因为直接对String类型进行''+''操作，会产生大量临时的String对象。

```java
class Solution {
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> res = new ArrayList<>();
        dfs(root, "", res);
        return res;
    }
    
    private void dfs(TreeNode root, String path, List<String> res) {
        if(root == null) return;
        path += root.val;
        if(root.left == null && root.right == null) {
            res.add(path);
        }else {
            path += "->";
            dfs(root.left, path, res);
            dfs(root.right, path, res);
        }   
    }
}
```



##### [129. Sum Root to Leaf Numbers](https://leetcode-cn.com/problems/sum-root-to-leaf-numbers/)

在第257题中，要求求出所有从根节点到叶节点的路径。而本题中，把从根节点到叶节点组成的路径形成一个数字，然后计算得出所有路径对应的数字之和。比如：

```
输入: [4,9,0,5,1]
    4
   / \
  9   0
 / \
5   1
输出: 1026
解释:
从根到叶子节点路径 4->9->5 代表数字 495.
从根到叶子节点路径 4->9->1 代表数字 491.
从根到叶子节点路径 4->0 代表数字 40.
因此，数字总和 = 495 + 491 + 40 = 1026.
```

分析：

**写法1**：用了一个全局变量，不够优雅。

```java
class Solution {
    private int res = 0;
    public int sumNumbers(TreeNode root) {
        dfs(root, 0);
        return res;
    }
    
    private void dfs(TreeNode root, int sum) {
        if(root == null) return;
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null)  {
            res += sum;
            return;
        }
        dfs(root.left, sum);
        dfs(root.right, sum);
    }
}
```

**写法2**：避免使用全局变量，完全由递归解决。

```java
class Solution {
    public int sumNumbers(TreeNode root) {
        return dfs(root, 0);
    }
    private int dfs(TreeNode root, int sum) {
        if(root == null) return 0;
        sum = sum * 10 + root.val;
        if(root.left == null && root.right == null) return sum;
        return dfs(root.left, sum) + dfs(root.right, sum);
    }
}
```



##### [104. Maximum Depth of Binary Tree](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/) [一星]

给定一个二叉树，计算最大深度。深度的定义是根节点到叶子节点的节点个数。

分析：求树的深度/高度，可以使用dfs或bfs。

DFS版

```java
// 自己的解法
class Solution {
    private int maxDepth = 0;
    public int maxDepth(TreeNode root) {
        dfs(root, 1);
        return maxDepth;
    }
    
    private void dfs(TreeNode root, int depth) {
        if(root == null) return;
        maxDepth = Math.max(maxDepth, depth);
        dfs(root.left, depth+1);
        dfs(root.right, depth+1);
    }
}

// 参考的解法（简洁）
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }
}
```

BFS版：层序遍历

```java
class Solution {
    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int maxDepth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            maxDepth++;
            for(int i = 0; i < size; i++) {
                TreeNode front = queue.remove();
                if(front.left != null) queue.add(front.left);
                if(front.right != null) queue.add(front.right);
            }
        }
        return maxDepth;
    }
}
```



##### [111. Minimum Depth of Binary Tree](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)

给定一个二叉树，找出其最小深度。最小深度是从根节点到最近叶子节点的最短路径上的节点数量。

DFS版：这种做法需要遍历树的所有节点，因此复杂度为O(n)

```java
class Solution {
    private int minDepth = Integer.MAX_VALUE;
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        dfs(root, 1);
        return minDepth;
    }
    
    private void dfs(TreeNode root, int depth) {
        if(root == null) return;
        if(root.left == null && root.right == null && depth < minDepth) {
            minDepth = depth;
        }
        dfs(root.left, depth + 1);
        dfs(root.right, depth + 1);
    }
}
```

BFS版：由于采用层序遍历，逐层检查，第一个遇到的叶节点就是离根节点最近的，一般情况下不需要遍历所有的节点即可返回结果。当给定的树为满二叉树时，对于BFS解法来说就是”最差“的一种情况，因为要遍历到最后一层才能返回，不过这种情况下依然比DFS要好，因为程序在访问到最后一层最左边的那个叶节点之后就立马返回了，依然不需要访问所有节点。根据满二叉树的性质，假设总的节点个数为n，层数为h，则前h-1层的节点个数为n/2，因此时间复杂度还是O(n)级别的，但是一定要分析出和DFS的区别。

```java
class Solution {
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int minDepth = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            minDepth++;
            for(int i = 0; i < size; i++) {
                TreeNode front = queue.remove();
                // 如果节点是叶子节点，则直接返回结果
                if(front.left == null && front.right == null) return minDepth;
                if(front.left != null) queue.add(front.left);
                if(front.right != null) queue.add(front.right);
            }
        }
        return minDepth;
    }
}
```



##### **[543. Diameter of Binary Tree](https://leetcode-cn.com/problems/diameter-of-binary-tree/)** [三星]

求树的最大直径，可不经过根节点。

方法1：

```java
class Solution {
    private int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        preOrderTraversal(root);
        return maxDiameter;
    }

    private void preOrderTraversal(TreeNode root) {
        if(root != null) {
            int diameter = depthOfTree(root.left) + depthOfTree(root.right);
            if(diameter > maxDiameter) {
                maxDiameter = diameter;
            }
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    private int depthOfTree(TreeNode root) {
        if(root == null) return 0;
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        return Math.max(left, right) + 1;
    }
}
```

方法2：

```java
class Solution {
    private int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depthOfTree(root);
        return maxDiameter;
    }

    private int depthOfTree(TreeNode root) {
        if(root == null) return 0;
        int left = depthOfTree(root.left);
        int right = depthOfTree(root.right);
        maxDiameter = Math.max(maxDiameter, left + right); // update
        return Math.max(left, right) + 1;
    }
}
```



##### [110. Balanced Binary Tree](https://leetcode-cn.com/problems/balanced-binary-tree/) [五星]

给定一个二叉树，判断其是否为平衡二叉树。

分析：这一题和第543题很像，这两题我都只能想出暴力法。两个题目一定要一起思考。

**方法1：暴力法**。判断一个二叉树是否为平衡二叉树，需要递归的判断其左右子树是否为平衡二叉树，此外，还要判断以当前节点为根节点的左右子树高度之差是否小于1。

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return isBalanced(root.left) 
            && isBalanced(root.right)
            && Math.abs(height(root.left) - height(root.right)) < 2;
    }
    
    private int height(TreeNode root) {
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
```

这种方法是自顶向下考虑的，比如针对下面这样一棵树，在判断根节点4是否满足时，会计算其左右子树的高度。而在递归的判断其左右子树是否平衡时，又会重复的计算子树高度，从而造成大量不必要的重复计算。

![img](https://pic.leetcode-cn.com/Figures/110/110-unbalanced-wheight-highlighted.png)

复杂度分析：由于需要遍历完所有节点O(n)，并且每遍历一个节点就要计算其左右子树的高度O(log n)，因此时间复杂度是O(nlogn)。空间复杂度即为O(h)，h为树的高度。

**方法2：最优解**。这种方法的核心思想是自底向上，也就是先判断左右子树是否平衡（如果有其一不满足就可以直接返回了），然后再比较左右子树的高度来判断父节点是否平衡。这种方法使得每个节点的高度仅计算一次，在实现的过程中，我们把”计算树的高度“和”判断是否平衡“两个功能点放在同一个方法中实现。在递归求树高的方法`height(TreeNode root)`中，正常情况下返回的值是一个非负整数，因此我们可以令其返回一个负数，以表示以`root`为根节点的子树不满足平衡要求。（务必好好体会这种方法）

```java
// 时间复杂度：O(n)
// 空间复杂度：O(h) h为树高
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    private int height(TreeNode root) {
        if(root == null) return 0;
        int left = height(root.left);
        if(left == -1) return -1;
        int right = height(root.right);
        if(right == -1) return -1;
        if(Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
```



##### [662. Maximum Width of Binary Tree](https://leetcode-cn.com/problems/maximum-width-of-binary-tree/) [五星]

给定一个二叉树，编写一个函数来获取这个树的最大宽度。**树的宽度是所有层中的最大宽度**。这个二叉树与满二叉树（full binary tree）结构相同，但一些节点为空。

每一层的宽度被定义为两个端点（该层最左和最右的非空节点，两端点间的null节点也计入长度）之间的长度。比如：

```
输入: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

输出: 4
解释: 最大值出现在树的第 3 层，宽度为 4 (5,3,null,9)。

输入: 

          1
         / \
        3   2 
       /        
      5      

输出: 2
解释: 最大值出现在树的第 2 层，宽度为 2 (3,2)。

输入: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
输出: 8
解释: 最大值出现在树的第 4 层，宽度为 8 (6,null,null,null,null,null,null,7)。
```

分析：借助队列对二叉树进行层次遍历，这个问题中的**主要想法是给每个节点一个 `position` 值，如果我们走向左子树，那么 `position -> position×2`，如果我们走向右子树，那么 `position -> positon×2+1`**（这是满二叉树/完全二叉树的性质，别忘了）。当我们在看同一层深度的位置值 L 和 R 的时候，宽度就是 R - L + 1。

```java
class Solution {
    public int widthOfBinaryTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        Map<TreeNode, Integer> map = new HashMap<>();
        queue.add(root);
        map.put(root, 1);
        int maxWidth = 0;
        while (!queue.isEmpty()) {
            maxWidth = Math.max(maxWidth, 
                       map.get(queue.getLast()) - map.get(queue.getFirst()) + 1);
            int size = queue.size();
            for(int i = 0;i < size; i++) {
                TreeNode front = queue.pollFirst();
                int pos = map.get(front);
                if(front.left != null) {
                    queue.add(front.left);
                    map.put(front.left, 2*pos);
                }
                if(front.right != null) {
                    queue.add(front.right);
                    map.put(front.right,2*pos+1);
                }
            }
        }
        return maxWidth;
    }
}
```



##### [617. Merge Two Binary Trees](https://leetcode-cn.com/problems/merge-two-binary-trees/)

合并两个二叉树。

分析：本题要求从根节点开始，合并两个二叉树。因此必须自顶向下进行处理。一开始以为是用层序遍历，但层序遍历不能确定每一层的结构（即无法得知该节点是父节点的左孩子还是有孩子）。而用前序遍历则刚好可以处理。如果当前两个树的根节点均为非空，则合并（把结果累加在root1上）；然后递归的合并左右子树即可。

```java
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if(t1 == null) return t2;
        if(t2 == null) return t1;

        t1.val += t2.val;
        TreeNode leftSub = mergeTrees(t1.left, t2.left);
        TreeNode rightSub = mergeTrees(t1.right, t2.right);
        t1.left = leftSub;
        t1.right = rightSub;
        return t1;
    }
}
```



----

#### 二叉搜索树（Binary Search Tree）

二叉搜索树的性质：对于任何节点v，其左子树节点的值均＜v的值；其右子树节点的值均＞v的值（根据题意决定是'＜'还是'≤'）。

遍历：根据二叉搜索树的性质，**中序遍历可得到递增序列**。这是BST最重要的性质，几乎涉及BST的题目都是根据这一性质来求解。

查找：查找BST的最大值、最小值，以及第k个小的值等

插入：插入一个新的值，要求插入后维持二叉搜索树的性质

删除：删除指定节点，要求删除后维持二叉搜索树的性质

二叉搜索树相关的题目，主要有两类。一类是根据二叉搜索树的性质来求解的，另一类则纯粹是考察对这一数据结构是否熟悉，比如手撕BST，实现插入/删除/查询等操作。

下面是一个简单的二叉搜索树实现，参考《算法》第4版。主要实现以下几个public接口：

* public void insert(int key)：向BST中插入一个值
* public TreeNode search(int key)：查询是否存在值为key的节点，若存在返回对应节点；若不存在返回null
* public void deleteNode(int key)：删除值为key的节点（简单起见，保证key总是有效的，即第450题）
* public int select(int k)：返回BST中第k小的元素（简单期间，保证k总是有效的，即第230题）
* public int size()：返回BST的节点个数

由于在每个节点中维护一个`size`域，表示以该节点为根节点的子树的节点个数。因此，在插入和删除操作时要及时更新这一值。另外，比较容易出错的是删除操作。

```java
public class BST {
    // 节点定义
    class TreeNode {
        int val;
        int size; // 以该节点为根节点的子树的节点总数
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
            size = 1;
        }
    }

    private TreeNode root;

    // 查询BST中是否存在值为key的节点，若存在，返回对应节点；反之，返回null
    public TreeNode search(int key) {
        return search(root, key);
    }

    private TreeNode search(TreeNode root, int key) {
        if (root == null) return null;
        if (key == root.val) return root;
        else if (key < root.val) return search(root.left, key);
        else return search(root.right, key);
    }

    // 向BST中插入一个值为key的节点
    public void insert(int key) {
        root = insert(root, key);
    }

    private TreeNode insert(TreeNode root, int key) {
        if (root == null) return new TreeNode(key);

        if (key < root.val) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }
        //
        root.size = size(root.left) + size(root.right) + 1;

        return root;
    }

    // 删除给定key值的节点，返回树的根节点（根节点可能发生调整或删除）
    // 假定key总是BST中的值
    public void deleteNode(int key) {
        root = deleteNode(root, key);
    }

    private TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;

        if (key < root.val) {
            root.left = deleteNode(root.left, key);
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
        } else {
            // 待删除节点只有一个孩子节点时，将其孩子节点提升为父节点即可
            if (root.left == null) {
                root = root.right;
            } else if (root.right == null) {
                root = root.left;
            } else {
                // 关键
                TreeNode rightMin = findMin(root.right);
                rightMin.right = deleteMin(root.right);
                rightMin.left = root.left;
                root = rightMin;
            }
        }

        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    // 寻找以root为根节点的最小值
    private TreeNode findMin(TreeNode root) {
        if(root.left == null) return root; 
        return findMin(root.left);
    }

    // 删除以root为根节点的最小值节点
    private TreeNode deleteMin(TreeNode root) {
        if (root.left == null) return root.right;
        root.left = deleteMin(root.left);
        root.size = size(root.left) + size(root.right) + 1;
        return root;
    }

    // 查询BST中第k小的数（保证 1 ≤ k ≤ BST's total elements.）
    public int select(int k) {
        return select(root, k);
    }

    private int select(TreeNode root, int k) {
        int leftSize = size(root.left);
        if(leftSize == k - 1) return root.val;
        else if(leftSize > k - 1) return select(root.left, k);
        else return select(root.right, k-leftSize-1);
    }

    public int size() {
        return size(root);
    }

    private int size(TreeNode root) {
        if(root == null) return 0;
        return root.size;
    }

    /**
     * 用于测试
     * @return 中序序列
     */
    public List<Integer> inOrder() {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        inOrder(root, res);
        return res;
    }

    private void inOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        inOrder(root.left, res);
        res.add(root.val);
        inOrder(root.right, res);
    }

    public static void main(String[] args) {
        BST bst = new BST();
        bst.insert(6);
        bst.insert(3);
        bst.insert(9);
        bst.insert(1);
        bst.insert(5);
        bst.insert(11);

        System.out.println("size:" + bst.size() +", " +bst.inOrder());

        System.out.println(bst.search(9) != null); // true
        System.out.println(bst.search(10) != null); // false

        bst.insert(10);
        System.out.println(bst.search(10) != null); // true
        System.out.println("size:" + bst.size() +", " +bst.inOrder());

        bst.deleteNode(11); // 删除叶节点
        System.out.println("size:" + bst.size() +", " +bst.inOrder());

        bst.deleteNode(6); // 删除非叶节点
        System.out.println("size:" + bst.size() +", " +bst.inOrder());

        System.out.println(bst.select(3));
        //System.out.println(bst.kthSmallest(10)); // 无效
    }
}
```



##### [95. Unique Binary Search Trees II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/) [五星++]

给定一个整数 n，生成所有由 1 ... n 为节点所组成的**二叉搜索树**。

```
输入: 3
输出:
[
  [1,null,3,2],
  [3,2,null,1],
  [3,1,null,null,2],
  [2,1,3],
  [1,null,2,null,3]
]
解释:
以上的输出对应以下 5 种不同结构的二叉搜索树：

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

分析：本题在[96. Unique Binary Search Trees](https://leetcode-cn.com/problems/unique-binary-search-trees/) 的基础上，要求求出每个二叉搜索树的结构，最终返回每一种树形结构的根节点。

我们从序列 `1..n` 中取出数字 `i`，作为当前树的树根。于是，剩余 `i - 1` 个元素可用于左子树，`n - i` 个元素用于右子树。和上一题分析的一样，这样会产生`F(i-1)`种左子树 和 `F(n-i)` 种右子树，其中 F 是卡特兰数。

![image.png](https://pic.leetcode-cn.com/f709dff506c20ac970d4cd7ace0436aafca7828c67b510cdbaaa60d54f5479b3-image.png)

现在，我们对序列 `1 ... i-1` 重复上述过程，以构建所有的左子树；然后对 `i+1 ... n`重复，以构建所有的右子树。这样，我们就有了树根 i 和可能的左子树、右子树的列表。最后一步，对两个列表循环，将左子树和右子树连接在根上。

```java
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if(n <= 0) return new ArrayList<>(); // corner case
        return helper(1, n);
    }
    
    private List<TreeNode> helper(int left, int right) {
        List<TreeNode> result = new ArrayList<>();
        if(left > right) {
            result.add(null);
            return result;
        }
        
        for(int i = left; i <= right; i++) {
            List<TreeNode> lList = helper(left, i-1); // 左子树列表
            List<TreeNode> rList = helper(i+1, right);// 右子树列表 
            for(TreeNode lNode : lList) {
                for(TreeNode rNode : rList) {
                    TreeNode root = new TreeNode(i);
                    root.left = lNode;
                    root.right = rNode;
                    result.add(root);
                }
            }
        }
        return result;
    }
}
```

（ps：这一题第一遍做是完全不会的，第二遍做还是懵懂，显然自己还不是真正的掌握，属于“背题”而已，暂时记录于此，待3刷4刷给彻底整明白...）

##### [96. Unique Binary Search Trees](https://leetcode-cn.com/problems/unique-binary-search-trees/) [五星]

给定一个整数 *n*，求以 1 ... *n* 为节点组成的二叉搜索树有多少种？

```
输入: 3
输出: 5
解释:
给定 n = 3, 一共有 5 种不同结构的二叉搜索树:

   1         3     3      2      1
    \       /     /      / \      \
     3     2     1      1   3      2
    /     /       \                 \
   2     1         2                 3
```

分析：本题第一遍做的时候毫无思路，看过题解才发现其本质上考察的是**卡特兰数**，如果有了这个前置知识，再结合二叉搜索树本身的性质，就不是很难了。

**什么是卡特兰数**(catalan)?

```
F(n) = F(0)×F(n-1) + F(1)×F(n-2) + ... + F(n-1)×F(0), 其中 F(0)=1, F(1)=1
```

卡特兰数和斐波那契数列一样，都是常见的数学递推式，并且在算法题中经常出现，因此了解一下是必须的。

**假设n个节点可构成不同形状的二叉搜索树个数为F(n)**，那么：

```
F(n) = f(1) + f(2) + ... + f(n), (公式1), 其中 f(i) 表示以 i 为根节点所构成的二叉搜索树的个数，且有
f(i) = F(i-1)×F(n-i)             (公式2)
```

比如以 3 为根节点，那么根据二叉搜索树的特点，

```
左子树有(3-1)个节点 ==> 对应有F(3-1)种子树
右子树有(n-3)个节点 ==> 对应有F(n-3)种子树

结合公式1,2, 即可推出
F(n) = F(0)×F(n-1) + F(1)×F(n-2) + ... + F(n-1)×F(0)
```

也就是卡特兰数递推公式，再转化成代码即可.

复杂度分析：时间复杂度为$\sum_{i= 2}^n i = \frac{(2+n)(n-1)}{2}$ ，也就是$O(n^2)$ ，空间复杂度为$O(n)$ 。 

```java
class Solution {
    // 卡特兰数
    public int numTrees(int n) {
        int[] F = new int[n+1];
        F[0] = 1;
        F[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 0; j < i; j++) {
                F[i] += F[j]*F[i-j-1];
            }
        }
        return F[n];
    }
}
```



##### [98. Validate Binary Search Tree](https://leetcode-cn.com/problems/validate-binary-search-tree/) [五星]

给定一棵二叉树，验证其是否是二叉搜索树。

分析：涉及二叉搜索树的题，八成是通过“**中序序列是递增序列**”这一性质出发的。本题就是这一性质最基本的应用。

方法1：我们可以通过中序遍历得到给定二叉树的中序序列，然后判断该序列是否为递增序列，如果是，则为二叉搜索树；反之则不是。这是最直接的解法。不过需要O(n)的辅助空间，并且需要2次遍历，一次是遍历二叉树的所有节点产生序列，第二次是遍历序列检查递增性是否满足要求。

方法2：在方法1中，我们把遍历二叉树和检查序列的递增性分解成2步了，但事实上这是没必要的，我们可以**在遍历二叉树的同时进行递增性检查**，这样的话就只需要一趟遍历即可。

我们定义一个全局的`inorder`用于存放中序序列，并采用递归法进行遍历，因此代码骨架差不多是这样的：

```java
boolean isValid(TreeNode root) {
  	if(root == null) return true;
  	if(!isValid(root.left)) return false;
    // 针对 root 节点的操作
  	// 如果inorder为空，表示当前访问到的节点是首个节点，于是把它存入inorder内
    // 如果inorder不为空，那么在把当前访问到的节点值存入之前，
    //   先比较一下上一次访问的节点值是否比当前节点值大，如果是的，说明在这里就不满足递增性了，
    //   于是返回false;如果不是，那么继续按正常的步骤进行
  	return isValid(root.right);
}
```

翻译成代码如下：

```java
class Solution {
    List<Integer> inorder = new ArrayList<>();
    public boolean isValidBST(TreeNode root) {
        return isValid(root);
    }
    
    private boolean isValid(TreeNode root) {
        if(root == null) return true;
        if(!isValid(root.left)) return false;
        // 在这里处理存储中序序列和比较递增性问题
        if(inorder.isEmpty()) {
            inorder.add(root.val);
        }else {
            if(inorder.get(inorder.size()-1) >= root.val) return false;
            inorder.add(root.val);
        }
        return isValid(root.right);
    }
}
```

方法2改进：在上一个版本中，我们仍然使用了O(n)的辅助空间，但事实上**在比较递增性的时候，我们仅与“上一次访问的节点”进行比较**，也就是说，我们只需要O(1)辅助空间就够了。原理还是一样，我们将中序遍历的上一次访问到的那个节点记为 prev，对其初始化为 null，代码如下：

```java
// 时间复杂度：O(n)
// 空间复杂度：O(1)
class Solution {
    private TreeNode prev = null;
    public boolean isValidBST(TreeNode root) {
        if(root == null) return true;
        if(!isValidBST(root.left)) return false;
        if(prev == null) {
            prev = root;
        }else {
            if(prev.val >= root.val) return false;
            else prev = root;
        }
        return isValidBST(root.right);
    }
}
```



##### [99. Recover Binary Search Tree](https://leetcode-cn.com/problems/recover-binary-search-tree/) [五星]

已知二叉搜索树中的**两个**节点被错误地交换。在**不改变其结构**的情况下，恢复这棵树。比如：

```
输入: [3,1,4,null,null,2]

  3
 / \
1   4
   /
  2

输出: [2,1,4,null,null,3]

  2
 / \
1   4
   /
  3
```

**进阶:**

- 使用 O(*n*) 空间复杂度的解法很容易实现。
- 你能想出一个只使用常数空间的解决方案吗？

分析：如果深刻理解了[98. Validate Binary Search Tree](https://leetcode-cn.com/problems/validate-binary-search-tree/) 题，那么这道题其实就是换了个马甲罢了。第98题要我们验证，这一题要我们找出2个乱序的节点，本质都是从“二叉搜索树的中序序列是递增序列”这一性质出发的，完全就是一回事儿。而且在上一题中，空间复杂度已经从O(n)优化到了O(1)，因此，这里就直接采用O(1)的解法了。

对于有两个乱序节点的"二叉搜索树"来说，观察其中序序列，可以发现如下特点。注，题目条件给的例子可能不容易发现这样的特点，需要自己多举几个测试用例加以验证。比如：

```
    4
   / \
  2   3
 /\   /\
1  6 5  7
观察可以很容易的知道节点6和节点3的位置错了，对应的中序序列为
inorder: 1,2,[6],4,5,[3],7

```

当**第一次**出现逆序时，前一个节点就是第1个位置错误的节点；

当**最后一次**出现逆序时，后一个节点就是第2个位置错误的节点；

再比如下面这个例子，

```
  3
 / \
1   4
   /
  2
inorder: 1,[3],[2],4
```

一定注意我这里的描述！我们记录好这两个位置错误的节点，最后把它俩的值交换一下即可。

代码如下，可以看到其整体骨架和前一题毫无区别，就是在前一题的模板上再根据本题的特点进行简单扩充。

```java
class Solution {
    private TreeNode first = null;
    private TreeNode second = null;
    private TreeNode prev = null;
    
    public void recoverTree(TreeNode root) {
        inOrder(root);
        int t = first.val;
        first.val = second.val;
        second.val = t;
    }
    
    private void inOrder(TreeNode root) {
        if(root == null) return;
        inOrder(root.left);
        if(prev == null) {
            prev = root;
        }else {
            if(first == null && prev.val > root.val) {
                first = prev;
            }
            if(prev.val > root.val) {
                second = root;
            }
            // update prev
            prev = root;
        }
        inOrder(root.right);
    }
}
```



##### [108. Convert Sorted Array to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/) [二星]

将一个按照升序排列的有序数组，转换为一棵**高度平衡二叉搜索树**。

```java
// 时间复杂度：O(n) 每个节点需要访问一次
// 空间复杂度：O(log(n)) 递归栈
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length-1);
    }
    
    private TreeNode helper(int[] nums, int left, int right) {
        if(left > right) return null;
        int mid = (right - left) / 2 + left;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid-1);
        root.right = helper(nums, mid+1, right);
        return root;
    }
}
```



##### 



##### [173. Binary Search Tree Iterator](https://leetcode-cn.com/problems/binary-search-tree-iterator/) 

本题考察的本质是利用BST中序序列是升序的特点，要求以非递归的方式进行中序遍历。

```java
class BSTIterator {
    Stack<TreeNode> stack;
    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode p = root;
        while(p != null) {
            stack.push(p);
            p = p.left;
        }
    }
    
    public int next() {
        TreeNode t = stack.pop();
        TreeNode p = t.right;
        while(p != null) {
            stack.push(p);
            p = p.left;
        }
        return t.val;
    }
    
    public boolean hasNext() {
        return !stack.isEmpty();
    }    
}
```



##### [230. Kth Smallest Element in a BST](https://leetcode-cn.com/problems/kth-smallest-element-in-a-bst/)

返回二叉搜索树中第k小的元素。

```java
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        if(root == null) return -1; // never happens
        int leftSize = size(root.left);
        if(leftSize == k - 1) return root.val;
        else if(leftSize > k - 1) return kthSmallest(root.left, k);
        else return kthSmallest(root.right, k-leftSize-1);
    }
    
    // 返回以 root 为根节点的树的节点个数
    private int size(TreeNode root) {
        if(root == null) return 0;
        return size(root.left) + size(root.right) + 1;
    }
}
```

**follow-up** ：
如果二叉搜索树经常被修改（插入/删除操作）并且你需要频繁地查找第 k 小的值，你将如何优化 `kthSmallest` 函数？

答：在leetcode提交的解法中，每调用一次 `kthSmallest` 函数，内部都会调用一个`size()`函数，计算节点个数，由于这个`size()`是递归实现的，因此如果频繁的调用会很耗时。优化的做法就是在每个节点中新增一个`size`域，表示以该节点为根节点的子树的节点个数。

##### [450. Delete Node in a BST](https://leetcode-cn.com/problems/delete-node-in-a-bst/) 

删除二叉搜索树中的一个节点。

```java
class Solution {
    public TreeNode deleteNode(TreeNode root, int key) {
        if(root == null) return null;
        if(key < root.val) {
            root.left = deleteNode(root.left, key);
        }else if(key > root.val) {
            root.right = deleteNode(root.right, key);
        }else {
            // 待删除节点仅有一个孩子节点(或没有孩子节点)
            if(root.left == null) {
                root = root.right;
            }else if(root.right == null) {
                root = root.left;
            }else {
            // 待删除节点含有两个孩子节点
                TreeNode rightMin = findMin(root.right);
                rightMin.right = deleteMin(root.right);
                rightMin.left = root.left;
                root = rightMin;
            }
        }
        return root;
    }
    
    // 返回以root为根节点的子树的最小节点
    private TreeNode findMin(TreeNode root) {
        if(root.left == null) return root; 
        return findMin(root.left);
    }
    
    // 删除以root为根节点的子树的最小节点，并返回子树的根节点
    private TreeNode deleteMin(TreeNode root) {
        if(root.left == null) return root.right;
        root.left = deleteMin(root.left);
        return root;
    }
}
```



---

#### 平衡二叉树

平衡二叉树的性质：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。

平衡二叉树的插入、删除操作比较繁琐（PAT考过，LeetCode好像还没刷到过），至少要会判断给定的二叉树是否为平衡二叉树。

##### [110. Balanced Binary Tree](https://leetcode-cn.com/problems/balanced-binary-tree/) [五星]

给定一个二叉树，判断其是否为平衡二叉树。

分析：这一题和第543题很像，这两题我都只能想出暴力法。两个题目一定要一起思考。

**方法1：暴力法**。判断一个二叉树是否为平衡二叉树，需要递归的判断其左右子树是否为平衡二叉树，此外，还要判断以当前节点为根节点的左右子树高度之差是否小于1。

```java
class Solution {
    public boolean isBalanced(TreeNode root) {
        if(root == null) return true;
        return isBalanced(root.left) 
            && isBalanced(root.right)
            && Math.abs(height(root.left) - height(root.right)) < 2;
    }
    
    private int height(TreeNode root) {
        if(root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }
}
```

这种方法是自顶向下考虑的，比如针对下面这样一棵树，在判断根节点4是否满足时，会计算其左右子树的高度。而在递归的判断其左右子树是否平衡时，又会重复的计算子树高度，从而造成大量不必要的重复计算。

![img](https://pic.leetcode-cn.com/Figures/110/110-unbalanced-wheight-highlighted.png)

复杂度分析：由于需要遍历完所有节点O(n)，并且每遍历一个节点就要计算其左右子树的高度O(log n)，因此时间复杂度是O(nlogn)。空间复杂度即为O(h)，h为树的高度。

**方法2：最优解**。这种方法的核心思想是自底向上，也就是先判断左右子树是否平衡（如果有其一不满足就可以直接返回了），然后再比较左右子树的高度来判断父节点是否平衡。这种方法使得每个节点的高度仅计算一次，在实现的过程中，我们把”计算树的高度“和”判断是否平衡“两个功能点放在同一个方法中实现。在递归求树高的方法`height(TreeNode root)`中，正常情况下返回的值是一个非负整数，因此我们可以令其返回一个负数，以表示以`root`为根节点的子树不满足平衡要求。（务必好好体会这种方法）

```java
// 时间复杂度：O(n)
// 空间复杂度：O(h) h为树高
class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) != -1;
    }
    private int height(TreeNode root) {
        if(root == null) return 0;
        int left = height(root.left);
        if(left == -1) return -1;
        int right = height(root.right);
        if(right == -1) return -1;
        if(Math.abs(left - right) > 1) return -1;
        return Math.max(left, right) + 1;
    }
}
```



---

#### 完全二叉树

什么是完全二叉树？定义如下：

>  在完全二叉树中，除了最底层节点可能没填满外，其余每层节点数都达到最大值，并且最下面一层的节点都集中在该层最左边的若干位置。若最底层为第 h 层，则该层包含 1~ 2^h 个节点。

##### [222. Count Complete Tree Nodes](https://leetcode-cn.com/problems/count-complete-tree-nodes/)

计算完全二叉树的节点个数。

**方法1**：计算二叉树节点个数的通用方法。时间复杂度是O(n)，即要遍历所有节点。

```java
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        return 1 + countNodes(root.left) + countNodes(root.right);
    }
}
```

**方法2**：利用完全二叉树特有的性质。参考[这里](https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/chang-gui-jie-fa-he-ji-bai-100de-javajie-fa-by-xia/) 。

首先回顾一下**满二叉树**的节点个数怎么计算，如果满二叉树的层数为h，则总节点数为：`2^h - 1`。
那么我们来对root节点的左右子树进行高度统计，分别记为left和right，有以下两种结果：

* left == right：这说明，左子树一定是满二叉树，因为节点已经填充到右子树了，左子树必定已经填满了。所以左子树的节点总数我们可以直接得到，是`2^left - 1`，加上当前这个root节点，则正好是`2^left`。再对右子树进行递归统计。存在以下两种情况：

![cbt1](../img/cbt1.png)

* left != right：说明此时最后一层不满，但倒数第二层已经满了，可以直接得到右子树的节点个数。同理，右子树节点个数加上root节点，总数为`2^right`。再对左子树进行递归查找。存在以下两种情况：

![cbt1](../img/cbt2.png)

```java
class Solution {
    public int countNodes(TreeNode root) {
        if(root == null) return 0;
        int left = height(root.left);
        int right = height(root.right);
        if(left == right) return (1<<left) + countNodes(root.right);
        else return (1<<right) + countNodes(root.left);
    }
    
    private int height(TreeNode root) {
        if(root == null) return 0;
        TreeNode curr = root;
        int h = 1;
        while(curr.left != null) {
            h++;
            curr = curr.left;
        }
        return h;
    }
}
```

复杂度分析：这种做法的时间复杂度是O((log n)^2)，因为计算高度需要O(log n)，而自顶向下的过程中，每一次选择一个分支(相当于二分搜索的思想)，也是O(log n)；空间复杂度需要O(log n)，递归栈。

方法2还有另外一种[写法](https://leetcode-cn.com/problems/count-complete-tree-nodes/solution/wan-quan-er-cha-shu-de-jie-dian-ge-shu-by-leetcode/)，是更直观的二分查找的思想。



##### [958. Check Completeness of a Binary Tree](https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/)

判断给定的树是否为完全二叉树。

分析：根据定义，可以借助队列进行层次遍历，把节点的孩子节点（包括空节点）都入队，这里的空节点是用来判定完全二叉树的关键要素。**若在空节点之后还存在非空节点，则说明不是完全二叉树**。

```java
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        if(root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node == null) {
                while(!queue.isEmpty()) {
                    node = queue.poll();
                    if(node != null) return false;
                }
            }
            if(node != null) {
                queue.add(node.left);
                queue.add(node.right);
            }
        }
        return true;
    }
}
```
