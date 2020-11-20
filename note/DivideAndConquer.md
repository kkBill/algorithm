### 分治法问题

##### [95. 不同的二叉搜索树 II](https://leetcode-cn.com/problems/unique-binary-search-trees-ii/) [五星++]

给定一个整数 *n*，生成所有由 1 ... *n* 为节点所组成的**二叉搜索树**。

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

分析：

```java
class Solution {
    public List<TreeNode> generateTrees(int n) {
        if (n < 1) return new ArrayList<>();
        return helper(1, n);
    }

    private List<TreeNode> helper(int left, int right) {
        if (left > right) {
            List<TreeNode> t = new ArrayList<>();
            t.add(null);
            return t;
        }

        List<TreeNode> result = new ArrayList<>();
        // 以 i 作为根节点建立 BST
        // 递归处理左右子树
        for (int i = left; i <= right; i++) {
            List<TreeNode> leftList = helper(left, i - 1);
            List<TreeNode> rightList = helper(i + 1, right);
            for (TreeNode lNode : leftList) {
                for (TreeNode rNode : rightList) {
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



##### [241. 为运算表达式设计优先级](https://leetcode-cn.com/problems/different-ways-to-add-parentheses/) [五星++]

给定一个含有数字和运算符的字符串，为表达式添加括号，改变其运算优先级以求出不同的结果。你需要给出所有可能的组合的结果。有效的运算符号包含 +, - 以及 * 。

```
输入: "2*3-4*5"
输出: [-34, -14, -10, -10, 10]
解释: 
(2*(3-(4*5))) = -34 
((2*3)-(4*5)) = -14 
((2*(3-4))*5) = -10 
(2*((3-4)*5)) = -10 
(((2*3)-4)*5) = 10
```

分析：

```java
// 初始版，会出现重复计算。但是也能AC
class Solution {
    public List<Integer> diffWaysToCompute(String input) {
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (Integer lvalue : left) {
                    for (Integer rvalue : right) {
                        if (c == '+') res.add(lvalue + rvalue);
                        if (c == '-') res.add(lvalue - rvalue);
                        if (c == '*') res.add(lvalue * rvalue);
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        return res;
    }
}


// 添加备忘录，减少重复计算
class Solution {
    Map<String, List<Integer>> memo = new HashMap<>();
    public List<Integer> diffWaysToCompute(String input) {
        if(memo.containsKey(input)) return memo.get(input);
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                List<Integer> left = diffWaysToCompute(input.substring(0, i));
                List<Integer> right = diffWaysToCompute(input.substring(i + 1));
                for (Integer lvalue : left) {
                    for (Integer rvalue : right) {
                        if (c == '+') res.add(lvalue + rvalue);
                        if (c == '-') res.add(lvalue - rvalue);
                        if (c == '*') res.add(lvalue * rvalue);
                    }
                }
            }
        }
        if (res.isEmpty()) {
            res.add(Integer.parseInt(input));
        }
        memo.put(input, res);
        return res;
    }
}
```



#### 