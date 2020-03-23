## 树相关问题汇总

题目列表：

1. [94. Binary Tree Inorder Traversal](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/) [关于树的遍历，要熟练掌握迭代写法]
2. [144. Binary Tree Preorder Traversal](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)
3. [145. Binary Tree Postorder Traversal](https://leetcode-cn.com/problems/binary-tree-postorder-traversal/)
4. [96. Unique Binary Search Trees](https://leetcode-cn.com/problems/unique-binary-search-trees/) [五星]
5. [95. Unique Binary Search Trees II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/) [五星++]
6. [98. Validate Binary Search Tree](https://leetcode-cn.com/problems/validate-binary-search-tree/) [五星, 这题是有关BST一些列题目的模板]
7. [99. Recover Binary Search Tree](https://leetcode-cn.com/problems/recover-binary-search-tree/) [五星, 基于98题的模板]
8. [108. Convert Sorted Array to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/) [二星]
9. ​
10. [543. Diameter of Binary Tree](https://leetcode-cn.com/problems/diameter-of-binary-tree/) [五星, 树的最大直径]
11. [662. Maximum Width of Binary Tree](https://leetcode-cn.com/problems/maximum-width-of-binary-tree/) [五星, 树的最大宽度]




---

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



---

### 普通二叉树

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



除此之外，还有以特殊的二叉树，比如二叉搜索树为背景，给出较少的已知条件，需要利用特殊的性质来构建二叉树。比如第[108. Convert Sorted Array to Binary Search Tree](https://leetcode-cn.com/problems/convert-sorted-array-to-binary-search-tree/)题，根据给定的**递增序列**构建**平衡二叉搜索树**。这一题本质上和5/6/7没有任何区别，由于要保证是平衡二叉树，所以根节点要从中间位置开始，当确定了根节点之后，由于原数组已经是有序的，那么根据二叉搜索树的性质即可划分出左右子树，然后对左右子树递归处理即可。

```java
public TreeNode sortedArrayToBST(int[] nums) {
	return build(nums, 0, nums.length - 1);
}

private TreeNode build(int[] nums, int low, int high) {
     if (low > high) return null;
     int mid = (high - low) / 2 + low;
     TreeNode root = new TreeNode(nums[mid]);
     root.left = build(nums, low, mid - 1);
     root.right = build(nums, mid + 1, high);
     return root;
}
```









### 二叉搜索树（BST，Binary Search Tree）

二叉搜索树的性质：对于任何节点v，其左子树节点的值均小于（或等于）v的值；其右子树节点的值均大于（或等于）v的值。

遍历：根据二叉搜索树的性质，中序遍历可得到递增序列。

查找：查找给定的关键字、最大值、最小值

插入：插入一个新的值，要求插入后维持二叉搜索树的性质

删除：删除指定节点，要求删除后维持二叉搜索树的性质

简单实现如下：（另外还可以参考[这里](https://github.com/kkBill/algorithm/tree/master/src/Other/BinarySearchTree)）

```java
package LeetCode;

public class BSTNode {
    int val;
    BSTNode left;
    BSTNode right;

    public BSTNode(int x){
        val = x;
        left = null;
        right = null;
    }

    public static BSTNode makeBST(int[] vals) {
        BSTNode root = null;
        for(int val : vals){
            //root = insertRecursive(root,val);
            root = insertNonRecursive(root,val);
        }
        return root;
    }

    // 插入
    // 1. 递归版本
    public static BSTNode insertRecursive(BSTNode root, int x){
        if(root == null)
            return new BSTNode(x);
        else if(x < root.val)
            root.left = insertRecursive(root.left, x);
        else
            root.right = insertRecursive(root.right, x);
        return root;
    }

    // 2. 迭代版本（参考《算法导论》p166）
    public static BSTNode insertNonRecursive(BSTNode root, int x){
        BSTNode parent = null;
        BSTNode curr = root;
        while(curr != null) {
            parent = curr;
            if(x < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        if(parent == null) root = new BSTNode(x);
        else if(x <= parent.val) parent.left = new BSTNode(x);
        else parent.right = new BSTNode(x);
        return root;
    }

    // 遍历
    // 中序遍历，按序输出
    public static void inorderTraversal(BSTNode root) {
        if(root != null) {
            inorderTraversal(root.left);
            System.out.print(root.val + " ");
            inorderTraversal(root.right);
        }
    }

    // 查找
    // 查找关键字为x的节点，如果存在，返回对应的节点；否则返回null
    public static BSTNode find(BSTNode root, int x) {
        BSTNode curr = root;
        while(curr != null && curr.val != x) {
            if(x < curr.val) curr = curr.left;
            else curr = curr.right;
        }
        return curr;
    }

    // 查找最大值
    public static BSTNode findMax(BSTNode root) {
        BSTNode curr = root;
        while(curr.right != null)
            curr = curr.right;
        return curr;
    }

    // 查找最小值
    public static BSTNode findMin(BSTNode root) {
        BSTNode curr = root;
        while(curr.left != null)
            curr = curr.left;
        return curr;
    }

    // 删除（写法1：参考《算法笔记》）
    // 删除值为 x 的节点，并返回二叉搜索树的根
    public static BSTNode delete(BSTNode root, int x) {
        if(root == null) return null;
        if(x == root.val) {
            if(root.left == null && root.right == null){
                root = null;
            }else if(root.left != null) {
                BSTNode preNode = findMax(root.left);
                root.val = preNode.val;
                root.left = delete(root.left,preNode.val);
            }else {
                BSTNode nextNode = findMin(root.right);
                root.val = nextNode.val;
                root.right = delete(root.right,nextNode.val);
            }
        }else if(x < root.val) {
            root.left = delete(root.left, x);
        }else {
            root.right = delete(root.right, x);
        }
        return root;
    }

    // 删除（写法2：参考《算法》第4版）
    public static BSTNode delete2(BSTNode root, int x) {
        if(root == null) return null;

        if(x < root.val) root.left = delete2(root.left,x);
        else if(x > root.val) root.right = delete2(root.right,x);
        else { // 当前root节点为待删除的节点
            //如果当前节点只有一个孩子节点，则直接将其孩子节点提升为新的根节点
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            //如果当前节点有两个孩子节点
            BSTNode successor = findMin(root.right);
            successor.right = deleteMin(root.right);
            successor.left = root.left;
            return successor;
        }
        return root;
    }

    private static BSTNode deleteMin(BSTNode root) {
        if(root.left == null)
            return root.right;
        root.left = deleteMin(root.left);
        return root;
    }

    public static void main(String[] args) {
        int[] vals = {5, 3, 8, 1, 6, 9, 7};
        BSTNode root = makeBST(vals);
        inorderTraversal(root);
        System.out.println();
        System.out.println(findMax(root).val);
        System.out.println(findMin(root).val);
        if(find(root,6) != null) {
            System.out.println("find node 6");
        }else {
            System.out.println("can't find node 6");
        }

        root = delete2(root,6);
        inorderTraversal(root);
    }
}
```

二叉搜索树相关：

* 删除二叉搜索树中的一个节点（[450. Delete Node in a BST](https://leetcode-cn.com/problems/delete-node-in-a-bst/) ）

直接套用上面分析的模板即可，不再赘述。

* 设计二叉搜索树的迭代器（[173. Binary Search Tree Iterator](https://leetcode-cn.com/problems/binary-search-tree-iterator/) ）

本题考察的本质是利用BST中序序列是升序的特点，要求以非递归的方式进行中序遍历。









### 平衡二叉树（AVL）

平衡二叉树的性质：一个二叉树每个节点的左右两个子树的高度差的绝对值不超过1。

平衡二叉树的插入、删除操作比较繁琐（PAT考过，LeetCode好像还没刷到过），至少要会判断给定的二叉树是否为平衡二叉树。

（1）判断任意二叉树是否为平衡二叉树

方法1：根据平衡二叉树的定义，对每个节点，分别计算其左右子树的高度，如果左右子树高度之差的绝对值大于1，则判定其为false；当且仅当左右子树**均满足**平衡二叉树的定义时，整棵树才是平衡二叉树。这种方法会导致没必要的重复计算，对于每个节点，计算其左右子树高度需要O(lgN)的时间，总共有N个节点，因此，时间复杂度为O(NlgN)，而空间复杂度为O(H) (H为树的高度)。

```java
public boolean isBalanced(TreeNode root) {
  	// 边界
	if(root == null) return true;
  	int leftHeight = height(root.left);
  	int rightHeight = height(root.right);
  	if(Math.abs(leftHeight-rightHeight) > 1) return false;
  	// 递归判定左右子树
  	return isBalanced(root.left) && isBalanced(root.right);
}
// 计算以root为根节点的树的高度
private int height(TreeNode root) {
  	if(root == null) return 0;
  	return 1 + Math.max(height(root.left), height(root.right));
}
```



方法2：时间复杂度O(N)，空间复杂度O(H) (H为树的高度)

在方法1中，我们对每一个子树都计算其高度，这其实是没必要的。在遍历过程中，只要发现有某一棵子树不满足平衡二叉树，就可以结束程序。在方法2中，如果子树满足平衡二叉树，则返回对应正常的高度，如果不满足，则返回-1。

```java
public boolean isBalanced(TreeNode root) {
	return height(root) != -1; //即 height(root) == -1 ? false : true;
}
private int height(TreeNode root) {
  	if(root == null) return 0;
  	int left = height(root.left);
  	if(left == -1) return -1;
  	int right = height(root.right);
  	if(right == -1) return -1;
  	if(Math.abs(left - right) > 1) return -1;
  	return 1 + Math.max(left, right);
}
```

相关题目：

[110. Balanced Binary Tree](https://leetcode-cn.com/problems/balanced-binary-tree/)



### 完全二叉树

什么是完全二叉树？

对于一颗二叉树，假设其深度为d（d>1）。除了第d层外，其它各层的节点数目均已达最大值，且第d层所有节点**从左向右**连续地紧密排列，这样的二叉树被称为完全二叉树。

如下图，区分满二叉树和完全二叉树。

![img](https://raw.githubusercontent.com/grandyang/leetcode/master/img/222_full_binary_tree.png)

关于完全二叉树的一个常规题目就是，**如何判断给定的树是否为完全二叉树**？

根据上面的定义，可以借助队列进行层次遍历，把节点的孩子节点（包括空节点）都入队，这里的空节点是用来判定完全二叉树的关键要素。**若在空节点之后还存在非空节点，则说明不是完全二叉树**。

```java
public boolean isCompleteTree(TreeNode root) {
	if(root == null) return true;

	List<TreeNode> queue = new ArrayList<>();
	queue.add(root);
	while(!queue.isEmpty()) {
		TreeNode node = queue.remove(0);
		if(node == null) {
			while(!queue.isEmpty()) {
				node = queue.remove(0);
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
```

相关题目：

[958. Check Completeness of a Binary Tree](https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/)

[222. Count Complete Tree Nodes](https://leetcode-cn.com/problems/count-complete-tree-nodes/)



---

上面介绍了有关二叉树的基础知识，下面归纳整理了LeetCode中有关树的习题。

二叉树经常还和链表结合起来一起考察！





翻转二叉树[226. Invert Binary Tree](https://leetcode-cn.com/problems/invert-binary-tree/)

题解：

```java
// 自己的写法
public TreeNode invertTree(TreeNode root) {
	invert(root);
    return root;        
}
private void invert(TreeNode root) {
    if(root == null) return;
    TreeNode t = root.left;
    root.left = root.right;
    root.right = t;
    invert(root.left);
    invert(root.right);
}

// 参考的写法
public TreeNode invertTree(TreeNode root) {
    if (root == null) return null;
 
    TreeNode right = invertTree(root.right);
    TreeNode left = invertTree(root.left);
    root.left = right;
    root.right = left;
    return root;
}
```

时间复杂度：O(n)（因为对每个节点必须要遍历一遍，这是少不了的）

空间复杂度：O(H)（H是树的高度，因为H∈O(n)，所以空间复杂度也是O(n)）



将二叉树展开为链表[114. Flatten Binary Tree to Linked List](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/)

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

分析：题目中提示了，展开后的链表就是原二叉树前序遍历的顺序，所以我们可以顺着前序遍历的思路开始思考。根据题意，如果左子树非空，就将左子树置为根节点的右子树，而原来的右子树则跟在左子树的最后一个节点。具体解析过程参考[这里](https://leetcode-cn.com/problems/flatten-binary-tree-to-linked-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--26/) 。

过程如下：

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
    / \
   3   4
   	    \
   	     5
   	      \
   	       6
   	 ||
   	 \/
   1
    \
     2
    / \
   3   4
   	    \
   	     5
   	      \
   	       6
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

对应的代码：

```java
public void flatten(TreeNode root) {
    TreeNode curr = root;
    while (curr != null) {
        if(curr.left == null){
            curr = curr.right;
        }else {
            TreeNode pre = curr.left;
            // 找到左子树中最右边的节点
            while (pre.right != null) {
                pre = pre.right;
            }
            pre.right = curr.right;
            curr.right = curr.left;
            curr.left = null;
            curr = curr.right;
        }
    }
}
```

递归法：递归法非常的精妙，值得学习借鉴！

我们从前序遍历的角度出发进行思考，但是前序遍历的递归写法会切断右子树，换句话说，自顶向下的思路行不通，于是我们思考如何自底向上，本质上和后序遍历没有区别，只不过这里稍稍做了一些变形，遍历的顺序为：右子树 -> 左子树 -> 根。

代码如下：

```java
TreeNode pre = null;
public void flatten(TreeNode root) {
    if(root == null) return;
    flatten(root.right);
    flatten(root.left);
    root.right = pre;
    root.left = null;
    pre = root;
}
```



填充每个节点的下一个右侧节点指针

[116. Populating Next Right Pointers in Each Node](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/)

[117. Populating Next Right Pointers in Each Node II](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/)

这两题要求将二叉树的**每一层**的节点自左向右连接起来（其中116题是完全二叉树，117题是普通的二叉树），如下图所示，并且要求只使用O(1)的空间复杂度。在本题中，节点的定义为：

```
struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}
```

![img](https://assets.leetcode.com/uploads/2019/02/15/117_sample.png)

如果不考虑空间复杂度的要求，那么借助队列，和层序遍历一样，可以比较直观的写出代码。如下所示，这段代码适用于116、117两题。

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



下面是空间复杂度为O(1)的做法，参考[这里](https://www.cnblogs.com/grandyang/p/4290148.html) 。这种做法实属精妙~

```java
public Node connect(Node root) {
    Node dummy = new Node(0,null,null,null);
    Node curr = dummy, head = root;
    while (root != null) {
        if(root.left != null) {
            curr.next = root.left;
            curr = curr.next;
        }
        if(root.right != null) {
            curr.next = root.right;
            curr = curr.next;
        }
        root = root.next;
        // 切换至下一层
        if(root == null) {
            curr = dummy;
            root = dummy.next;
            dummy.next = null;
        }
    }
    return head;
}
```



下面是仅适用于116题（也就是满二叉树）的解法。

```java
public Node connect(Node root) {
    if(root == null) return null;
    // start 指向每一层的第一个节点，curr 用于遍历一层的节点
    Node start = root, curr = null;
    while (start.left != null) {
        curr = start;
        while (curr != null) {
            curr.left.next = curr.right;
            if(curr.next != null) curr.right.next = curr.next.left;
            curr = curr.next;
        }
        start = start.left; // 下一层
    }
    return root;
}
```



计算二叉树任意节点之间的路径之和最大值[124. Binary Tree Maximum Path Sum](https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/)

本题属于二叉树类问题中比较难的一题，难点在于这里的**路径被定义为一条从树中任意节点出发，达到任意节点的序列**；而不是根节点到某一叶子节点。题解参考[这里](https://www.cnblogs.com/grandyang/p/4280120.html) 。

```java
int maxSum = Integer.MIN_VALUE;
public int maxPathSum(TreeNode root) {
    if (root == null) return 0;
    dfs(root);
    return maxSum;
}
private int dfs(TreeNode root) {
    if (root == null) return 0;
    int leftVal = Math.max(dfs(root.left), 0);
    int rightVal = Math.max(dfs(root.right), 0);
    maxSum = Math.max(maxSum, leftVal + rightVal + root.val);
    return Math.max(leftVal, rightVal) + root.val;
}
```

题解的关键是理解全局变量 `maxSum` 的含义，以及递归函数返回值的含义。

全局变量`maxSum`存放最终的结果，而递归返回值`dfs(root)`表示的是经过当前节点的路径之和，而由于节点的值可能存在负数，因此递归返回的结果值要与0进行比较，如果返回值小于0，则取0，表示不经过这个节点。



本题的一个扩展是不仅需要求出最大路径和，还要确定最大路径本身。代码如下：

```java
// 暂时写不出来~ 2020-01-21

```

