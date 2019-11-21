### LeetCode Note

#### 1. 回溯（Backtracking）



##### 17 Letter Combinations of a Phone Number

Given a string containing digits from 2-9 inclusive, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below. Note that 1 does not map to any letters.

![img](http://upload.wikimedia.org/wikipedia/commons/thumb/7/73/Telephone-keypad2.svg/200px-Telephone-keypad2.svg.png)

**Example:**

```
Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

Note:

Although the above answer is in lexicographical order, your answer could be in any order you want.

思路：参考[官方题解](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode/)

![leetcode17](D:\workspace\algorithm\img\leetcode17.png)

题解：

```java
class Solution {
    Map<String, String> phone = new HashMap<String, String>() {{
        put("2", "abc");
        put("3", "def");
        put("4", "ghi");
        put("5", "jkl");
        put("6", "mno");
        put("7", "pqrs");
        put("8", "tuv");
        put("9", "wxyz");
    }};
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits.length() == 0) return result;
        backtrack("", 0, digits, result);
        return result;
    }
    private void backtrack(String combination, int index, String digits, List<String> result) {
        if (index == digits.length()) {
            result.add(combination);
            return;
        }
        String digit = digits.substring(index, index + 1);
        String charSet = phone.get(digit);
        for (int i = 0; i < charSet.length(); i++) {
            backtrack(combination + charSet.substring(i, i + 1), index + 1, digits, result);
        }
    }
}
```





##### 22 Generate Parentheses

Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

For example, given n = 3, a solution set is:

```
[
  "((()))",
  "(()())",
  "(())()",
  "()(())",
  "()()()"
]
```

思路：以下思路摘自[这里](https://leetcode.com/problems/generate-parentheses/discuss/10100/Easy-to-understand-Java-backtracking-solution)

The goal is to print a string of “(“ ,”)” in certain order. The length of string is 2n. The constraints are that “(“s need to match “)”s.
Without constraints, we just simply print out “(“ or “)” until length hits n. So the base case will be length ==2 n, recursive case is print out “(“ and “)”. The code will look like

//base case
if(string length == 2*n) {
add(string);
return;
}
//recursive case
add a “(“
add a “)"

Let’s add in constraints now. We need to interpret the meanings of constraints. First, the first character should be “(“. Second, at each step, you can either print “(“ or “)”, but print “)” only when there are more “(“s than “)”s. Stop printing out “(“ when the number of “(“ s hit n. The first actually merges into the second condition.
The code will be:

//base case
if(string length == 2*n) {
add(string);
return;
}
//recursive case
if(number of “(“s < n){
add a “(“
}
if(number of “(“s > number of “)”s){
add a “)"
}

题解：

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack("",0,0,n,result);
        return result;
    }

    // openCnt 是当前组合中"("的个数
    // closeCnt 是当前组合中")"的个数
    private void backtrack(String combination, int openCnt, 
                           int closeCnt, int n, List<String> result){
        if(combination.length() == 2*n){
            result.add(combination);
            return;
        }
        if(closeCnt < openCnt) // 理解这一条件是本题重点
            backtrack(combination+")",openCnt,closeCnt+1,n,result);
        if(openCnt < n)
            backtrack(combination+"(",openCnt+1,closeCnt,n,result);
    }
}
```



##### 39 Combination Sum

Given a set of candidate numbers (candidates) (without duplicates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

The **same** repeated number may be chosen from candidates unlimited number of times.

Note:

* All numbers (including target) will be positive integers.
* The solution set must not contain duplicate combinations.

Example 1:

```
Input: candidates = [2,3,6,7], target = 7,
A solution set is:
[
  [7],
  [2,2,3]
]
```

Example 2:

```
Input: candidates = [2,3,5], target = 8,
A solution set is:
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

思路：回溯+剪枝

题解：

```java
class Solution {    
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) return result;
        Arrays.sort(candidates);
        helper(0, candidates, target, new ArrayList<>());
        return result;
    }
    private void helper(int start, int[] candidates, int target, List<Integer> path) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
		// target - candidates[i] >= 0 剪枝
        for (int i = start; i < candidates.length && target - candidates[i] >= 0; i++) {
            path.add(candidates[i]);
            helper(i, candidates, target - candidates[i], path);
            path.remove(path.size() - 1);// backtrack
        }
    }
}
```

##### 40 Combination Sum II

Given a collection of candidate numbers (candidates) and a target number (target), find all unique combinations in candidates where the candidate numbers sums to target.

Each number in candidates may only be used **once** in the combination.

Note:

* All numbers (including target) will be positive integers.
* The solution set must not contain duplicate combinations.

Example 1:

```
Input: candidates = [10,1,2,7,6,1,5], target = 8,
A solution set is:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]
```

Example 2:

```
Input: candidates = [2,5,2,1,2], target = 5,
A solution set is:
[
  [1,2,2],
  [5]
]
```

思路：

题解：

```java
class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        if (candidates.length == 0) return result;
        Arrays.sort(candidates);
        helper(0, candidates, target, new ArrayList<>());
        return result;
    }
    private void helper(int start, int[] candidates, int target, List<Integer> path) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        // 剪枝1：target - candidates[i] >= 0
        for (int i = start; i < candidates.length && target - candidates[i] >= 0; i++) {
            // 剪枝2：
            if (i > start && candidates[i] == candidates[i - 1])
                continue;
            path.add(candidates[i]);
            // i+1，表示数组中的数字只用一次
            helper(i + 1, candidates, target - candidates[i], path);
            path.remove(path.size() - 1);
        }
    }
}
```

##### 46 Permutations

Given a collection of **distinct** integers, return all possible permutations.

**Example:**

```
Input: [1,2,3]
Output:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

思路：

```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private void helper(int start, int[] nums, List<List<Integer>> result) {
        if (start == nums.length - 1) {
            List<Integer> perm = new ArrayList<>();
            for (Integer e : nums) {
                perm.add(e);
            }
            result.add(perm);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(start, i, nums);
            helper(start + 1, nums, result);
            swap(start, i, nums); //backtrack
        }
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}
```

##### 47 Permutations II

Given a collection of numbers that might contain duplicates, return all possible unique permutations.

Example:

```
Input: [1,1,2]
Output:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

思路：

```

```






#### 2. 树（Tree）

##### 前置知识

二叉树、二叉搜索树、平衡二叉树

二叉树的遍历（前序/中序/后序/层次遍历，递归版本和迭代版本）

树节点的定义如下：

```java
 public class TreeNode {
   int val;
   TreeNode left;
   TreeNode right;
   TreeNode(int x) { val = x; }
 }
```



前序遍历（递归）

```java
public void preOrderTraversalWithRecursion(TreeNode root){
  if(root != null){
    System.out.print(root.val + " ");
    preOrderTraversalWithRecursion(root.left);
    preOrderTraversalWithRecursion(root.right);
  }
}
```

前序遍历（迭代）

```java
public void preOrderTraversalWithIteration(TreeNode root) {
  TreeNode p = root;
  Stack<TreeNode> s = new Stack<>();
  while (p != null || !s.empty()) {
    if (p != null) {
      System.out.print(p.val + " ");
      s.push(p);
      p = p.left;
    } else {
      p = s.pop();
      p = p.right;
    }
  }
}
```



中序遍历（递归）

```java
public void inOrderTraversalWithRecursion(TreeNode root){
  if(root!=null){
    inOrderTraversalWithRecursion(root.left);
    System.out.print(root.val + " ");
    inOrderTraversalWithRecursion(root.right);
  }
}
```

中序遍历（迭代）

```java
public void inOrderTraversalWithIteration(TreeNode root) {
  TreeNode p = root;
  Stack<TreeNode> s = new Stack<>();
  while(p != null || !s.empty()){
    if(p!=null){
      s.push(p);
      p = p.left;
    }else{
      p = s.pop();
      System.out.print(p.val + " ");
      p = p.right;
    }
  }
}
```



后序遍历（递归）

```java
public void postOrderTraversalWithRecursion(TreeNode root) {
  if (root != null) {
    postOrderTraversalWithRecursion(root.left);
    postOrderTraversalWithRecursion(root.right);
    System.out.print(root.val + " ");
  }
}
```

后序遍历（迭代）（重点掌握！）

```java
public void postOrderTraversalWithIteration(TreeNode root) {
  TreeNode p = root;
  TreeNode lastVisitNode = null; // 标记最近一次访问过的节点
  Stack<TreeNode> s = new Stack<>();

  while(p!=null || !s.empty()){
    if(p != null){
      s.push(p);
      p = p.left;
    }
    else{
      TreeNode tmp = s.peek();
      // tmp.right != lastVisitNode 是为了避免重复访问！ 
      if(tmp.right != null && tmp.right != lastVisitNode){
        p = tmp.right;
      }else{
        System.out.print(tmp.val + " ");
        s.pop();
        lastVisitNode = tmp;
        p = null; // 关键!
      }
    }
  }
}
```



----

##### 112 Path Sum

Given a binary tree and a sum, determine if the tree has a root-to-leaf path such that adding up all the values along the path equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \      \
7    2      1
```

return true, as there exist a root-to-leaf path `5->4->11->2` which sum is 22.

解法1（递归）

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;
        if(root.left == null && root.right == null && root.val == sum) 
            return true;
        return hasPathSum(root.left, sum - root.val) ||
                hasPathSum(root.right, sum - root.val);
    }
}
```

解法2（迭代）

思路：利用后续遍历的迭代写法。

```java
class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;

        Stack<TreeNode> s = new Stack<>();
        TreeNode lastVisitNode = null;
        TreeNode p = root;
        boolean result = false;
        while(p != null || !s.empty()){
            if(p != null){
                s.push(p);
                sum -= p.val;

                p = p.left;
            }else{
                TreeNode tmp = s.peek();
                if(tmp.right != null && lastVisitNode != tmp.right){
                    p = tmp.right;
                }else{
                    if(tmp.left == null && tmp.right == null && sum == 0){
                        result = true;
                        break;
                    }
                    s.pop();
                    sum += tmp.val;//关键
                    lastVisitNode = tmp;
                    p = null; //关键
                }
            }
        }
        return result;
    }
}
```



##### 113 Path Sum II

Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

Note: A leaf is a node with no children.

Example:

Given the below binary tree and sum = 22,

```
      5
     / \
    4   8
   /   / \
  11  13  4
 /  \    / \
7    2  5   1
```

Return:

```
[
   [5,4,11,2],
   [5,8,4,5]
]
```

解法1（递归）

```java
class Solution {
   public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if (root == null) return new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        process(root, sum, path, result);
        return result;
    }

    private void process(TreeNode node, int sum, 
                         List<Integer> path, List<List<Integer>> result) {
        path.add(node.val);
        if (node.left == null && node.right == null && sum == node.val) {
            result.add(new ArrayList<>(path)); // 注意，不能写成 result.add(path); java引用问题
            return;
        }

        if (node.left != null) {
            process(node.left, sum - node.val, path, result);
            path.remove(path.size() - 1);
        }
        if (node.right != null) {
            process(node.right, sum - node.val, path, result);
            path.remove(path.size() - 1);
        }
    }
}
```

解法2（迭代）

思路：采用此解法的关键是理解二叉树**后序遍历的迭代写法**。后序遍历的迭代写法会在栈中记录当前节点到根节点的路径信息。

```java
class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        if(root == null) return new ArrayList<>();
      
        Stack<TreeNode> s = new Stack<>();
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        TreeNode p = root;
        TreeNode lastVisitNode = null;
        while(p != null || !s.empty()){
            if(p != null){
                s.push(p);
                path.add(p.val);
                sum -= p.val;
              
                p = p.left;
            }
            else{
                TreeNode tmp = s.peek();
                if(tmp.right != null && tmp.right != lastVisitNode){
                    p = tmp.right;
                }else{
                    // 检查当前节点是否符合条件
                    if(tmp.left == null && tmp.right == null && sum == 0){
                        //System.out.println(path);
                        result.add(new ArrayList<>(path));
                    }
                    s.pop();
                    path.remove(path.size()-1); // 关键
                    sum += tmp.val; // 关键

                    lastVisitNode = tmp;
                    p = null; // 关键
                }
            }
        }
        return result;
    }
}
```

##### 94 Binary Tree Inorder Traversal

Given a binary tree, return the *inorder* traversal of its nodes' values.

```java
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
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
}
```

##### 144 Binary Tree Preorder Traversal

Given a binary tree, return the *preorder* traversal of its nodes' values.

```java
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;
        Stack<TreeNode> s = new Stack<>();
        TreeNode p = root;
        while(p != null || !s.empty()){
            if(p != null){
                s.push(p);
                result.add(p.val);
                p = p.left;
            }else{
                p = s.pop();
                p = p.right;
            }
        }
        return result;
    }
}
```

##### 145 Binary Tree Postorder Traversal

Given a binary tree, return the postorder traversal of its nodes' values.

后续遍历的迭代实现，这属于基础题，要熟练到秒过。

```java
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        if(root == null) return new ArrayList<Integer>();
        
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> s = new Stack<>();
        TreeNode lastVisitNode = null;
        TreeNode p = root;
        while(p != null || !s.empty()){
            if(p != null){
                s.push(p);
                p = p.left;
            }else{
                TreeNode tmp = s.peek();
                if(tmp.right != null && tmp.right != lastVisitNode){
                    p = tmp.right;
                }else{
                    result.add(tmp.val);
                    s.pop();
                    lastVisitNode = tmp;
                    p = null;
                }
            }
        } // while
        return result;
    }
}
```





#### 3. 深度优先搜索（Depth First Search）



#### 4. 广度优先搜索（Breadth First Search）



#### 5. 动态规划（Dynamic Programming）



