## LeetCode Note

#### 一、回溯（Backtracking）

#### 17. Letter Combinations of a Phone Number

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



#### 22. Generate Parentheses

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



#### 39. Combination Sum

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

#### 40. Combination Sum II

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



#### 216. Combination Sum III

Find all possible combinations of **k** numbers that add up to a number **n,** given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

Note:

* All numbers will be positive integers.
* The solution set must not contain duplicate combinations.

Example 1:

```
Input: k = 3, n = 7
Output: [[1,2,4]]
```

Example 2:

```
Input: k = 3, n = 9
Output: [[1,2,6], [1,3,5], [2,3,4]]
```

思路：

题解：

```java
class Solution {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(k,n,1,path,result);
        return result;
    }
    private void dfs(int k, int target, int start, 
                     List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            if (k == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = start; i <= 9 && target - i >= 0; i++) {
            path.add(i);
            dfs(k - 1, target - i, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}
```



#### 46. Permutations

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

code flow like this:

path []

i=0: used[0]==false -> path=[1],used[0]=true

​	i=0: used[0]==true -> continue

​	i=1: used[1]==false -> path=[1,2],used[1]=true

​		i=0: used[0]==true -> continue

​		i=1: used[1]==true -> continue

​		i=2: used[2]==false -> path=[1,2,3],used[2]=true  ==> collect [1,2,3]

​		(紧接着发生两次回溯)

​	i=2: used[2]==false -> path=[1,3],used[2]=true

​		i=0: used[0]==true -> continue

​		i=1: used[1]==false -> path=[1,3,2],used[1]=true ==> collect [1,3,2]

​		(紧接着发生一次回溯)

​		i=2: used[2]==true -> continue

​		(紧接着发生两次回溯)

i=1: 	used[1]==false -> path=[2],used[1]=true

​	...

题解：

```java
// 解法1（推荐，具有通用性）
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(int[] nums, boolean[] used, 
                           List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                backtrack(nums, used, path, result);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
// 解法2
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private void helper(int start, int[] nums, List<List<Integer>> result) {
        if (start == nums.length) {
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

#### 47. Permutations II

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

思路：有待进一步理解

题解：

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, 
                           List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                    continue;
                path.add(nums[i]);
                used[i] = true;
                backtrack(nums, used, path, result);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }
}
```



#### 60. Permutation Sequence

The set [1,2,3,...,n] contains a total of n! unique permutations.

By listing and labeling all of the permutations in order, we get the following sequence for n = 3:

```
1. "123"
2. "132"
3. "213"
4. "231"
5. "312"
6. "321"
```

Given n and k, return the kth permutation sequence.

Note:

* Given n will be between 1 and 9 inclusive.
* Given k will be between 1 and n! inclusive.

Example 1:

```
Input: n = 3, k = 3
Output: "213"
```

Example 2:

```
Input: n = 4, k = 9
Output: "2314"
```

思路：（推荐指数5颗星！）

题解1：

```java
// 采用和46题相同的解法，但是会超时！所以这一题和第46题的区别到底在哪里呢？
class Solution {
    public String getPermutation(int n, int k) {
        boolean[] used = new boolean[n+1];
        List<String> result = new ArrayList<>();
        backtrack(n,used,"",result);
        return result.get(k-1);
    }

    private void backtrack(int n, boolean[] used, String path, List<String> result){
        if(path.length() == n){
            result.add(path);
            return;
        }
        for(int i=1;i<=n;i++){
            if(!used[i]){
                path = path + i; //ok
                used[i] = true;
                backtrack(n,used,path,result);
                used[i] = false;
                path = path.substring(0,path.length()-1);
            }
        }
    }
}
```

题解2：根据本题的特点进行剪枝！非常精妙！

```java
class Solution {
    private int[] factorial = new int[10];

    public String getPermutation(int n, int k) {
        // 初始化阶乘表
        factorial[0] = 1;
        for (int i = 1; i < 10; i++)
            factorial[i] = i * factorial[i - 1];

        StringBuilder sb = new StringBuilder();
        dfs(n, n, k, sb);
        return sb.toString();
    }

    private void dfs(int n, int height, int k, StringBuilder path) {
        if (path.length() == n) {
            return;
        }
        int count = factorial[height - 1];
        for (int i = 1; i <= n; i++) {
            String num = Integer.toString(i);
            if (!path.toString().contains(num)) {
                // 剪枝（本题最关键之处！）
                if (k > count) {
                    k -= count;
                    continue;
                }
                path.append(num);
                dfs(n, height - 1, k, path);
            }
        }
    }
}
```





#### 51. N-Queens



#### 52. N-Queens II    



#### 78. Subsets

Given a set of distinct integers, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

```
Input: nums = [1,2,3]
Output:
[
  [3],
  [1],
  [2],
  [1,2,3],
  [1,3],
  [2,3],
  [1,2],
  []
]
```

思路：

```
start=0: path=[]
i=0: add(1),path=[1]
	start=1: path=[1]
	i=1: add(2),path=[1,2]
		start=2: path=[1,2]
		i=2: add(3),path=[1,2,3]
	i=2: add(3),path=[1,3]
i=1: add(2),path=[2]
	start=2: path=[2]
	i=2: add(3),path=[2,3]
i=2: add(3),path=[3]
```

题解：

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, path, result);
        return result;
    }
    private void backtrack(int start, int[] nums, 
                           List<Integer> path, List<List<Integer>> result) {
        // 注意这里的边界条件，这里不能有return; 事实上这个判断条件根本不需要
        // if (path.size() <= nums.length) 
        result.add(new ArrayList<>(path)); 
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums, path, result);
            path.remove(path.size() - 1);
        }
    }
}
```

#### 90. Subsets II

Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).

Note: The solution set must not contain duplicate subsets.

Example:

```
Input: [1,2,2]
Output:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

思路：

```
start=0: in this level the initial path is []
i=0, add(1) -> path=[1]
    start=1: in this level the initial path is [1]
    i=1, add(2) -> path=[1,2]
    	start=2: in this level the initial path is [1,2]
    	//when i==start, always pick it，which means pick the first one of repeated sequence
    	i=2, add(2) -> path=[1,2,2] 
    i=2 // don't pick it,cause nums[i] == nums[i-1]
i=1, add(2) -> path=[2]
	start=2: in this level the initial path is [2]
	i=2, add(2) -> path=[2,2]
i=2 // don't pick it,cause nums[i] == nums[i-1]
```

题解：

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, nums, path, result);
        return result;
    }
    private void backtrack(int start, int[] nums,
                           List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i == start || nums[i] != nums[i - 1]) {
                path.add(nums[i]);
                backtrack(i + 1, nums, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}
```



#### 93. Restore IP Addresses

Given a string containing only digits, restore it by returning all possible valid IP address combinations.

**Example:**

```
Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
```

思路：



题解1：暴力法（本题直接暴力也很快）

```java
class Solution {
	public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        int len = s.length();
        for (int i = 1; i <= 3; i++) {
            for (int j = i + 1; j <= i + 4; j++) {
                for (int k = j + 1; k <= j + 4; k++) {
                    if (k < len) {
                        String tmp1 = s.substring(0, i);
                        String tmp2 = s.substring(i, j);
                        String tmp3 = s.substring(j, k);
                        String tmp4 = s.substring(k);
                        if (check(tmp1) && check(tmp2) && check(tmp3) && check(tmp4)) {
                            result.add(tmp1 + "." + tmp2 + "." + tmp3 + "." + tmp4);
                        }
                    }
                }
            }
        }
        return result;
    }
    private boolean check(String s) {
        if (s.length() > 3 || (s.length() != 1 && s.charAt(0) == '0') 
            || Integer.parseInt(s) > 255)
            return false;
        return true;
    }
}
```

题解2：回溯

```java
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> result = new ArrayList<>();
        if (s.length() > 12) return result;
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }
    private void backtrack(String s, int curPos, List<String> segs, List<String> result) {
        if (segs.size() == 4) {
            if(curPos == s.length())
                result.add(String.join(".", segs));
            return;
        }

        for (int k = 1; k <= 3; k++) {
            if (curPos + k > s.length()) break;
            String seg = s.substring(curPos, curPos + k);
            //System.out.println(seg);
            if (check(seg)) {
                segs.add(seg);
                backtrack(s, curPos + k, segs, result);
                segs.remove(segs.size() - 1);
            }
        }
    }
    private boolean check(String s) {
        if (s.length() > 3 || (s.length() != 1 && s.charAt(0) == '0') 
            || Integer.parseInt(s) > 255)
            return false;
        return true;
    }
}
```





#### 79. Word Search

Given a 2D board and a word, find if the word exists in the grid.

The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

Example:

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
Given word = "ABCCED", return true.
Given word = "SEE", return true.
Given word = "ABCB", return false.
```

思路：本题是典型的回溯case 1的情况。

题解：

```java
class Solution {
    private int[] X = {0, 0, -1, 1};
    private int[] Y = {1, -1, 0, 0};
    private int ROW_SIZE;
    private int COL_SIZE;
    private boolean[][] visited;

    public boolean exist(char[][] board, String word) {
        ROW_SIZE = board.length;
        if(ROW_SIZE == 0) return false;

        COL_SIZE = board[0].length;
        visited = new boolean[ROW_SIZE][COL_SIZE];

        for (int r = 0; r < ROW_SIZE; r++) {
            for (int c = 0; c < COL_SIZE; c++) {
                if (dfs(board, word, r, c))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, String word, int row, int col) {
        if (word.length() == 1) {
            return word.charAt(0) == board[row][col];
        }

        if (board[row][col] == word.charAt(0)) {
            visited[row][col] = true;

            for (int i = 0; i < 4; i++) {
                int nextRow = row + Y[i];
                int nextCol = col + X[i];
                boolean isValid = (nextRow >= 0 && nextRow < ROW_SIZE) 
                                   && (nextCol >= 0 && nextCol < COL_SIZE);
                if (isValid && !visited[nextRow][nextCol]) {
                    if (dfs(board, word.substring(1), nextRow, nextCol)) {
                        return true;
                    }
                }
            }

            visited[row][col] = false;
        }
        return false;
    }
}
```

#### 212. Word Search II

Given a 2D board and a list of words from the dictionary, find all words in the board.

Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once in a word.

Example:

```
Input: 
board = [
  ['o','a','a','n'],
  ['e','t','a','e'],
  ['i','h','k','r'],
  ['i','f','l','v']
]
words = ["oath","pea","eat","rain"]
Output: ["eat","oath"]
```

最详细的参考：[题解](https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00))

题解：

```java

```



#### 131. Palindrome Partitioning

Given a string s, partition s such that every substring of the partition is a palindrome.

Return all possible palindrome partitioning of s.

Example:

```
Input: "aab"
Output:
[
  ["aa","b"],
  ["a","a","b"]
]
```

思路：

xx

题解：

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        helper(0, s, path, result);
        return result;
    }

    private void helper(int start, String s, List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                path.add(s.substring(start, i + 1));
                helper(i + 1, s, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}
```

##### 

#### 37. Sudoku Solver

Write a program to solve a Sudoku puzzle by filling the empty cells.

A sudoku solution must satisfy all of the following rules:

1. Each of the digits 1-9 must occur exactly once in each row.
2. Each of the digits 1-9 must occur exactly once in each column.
3. Each of the the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.

Empty cells are indicated by the character '.'.

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

A sudoku puzzle...

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/3/31/Sudoku-by-L2G-20050714_solution.svg/250px-Sudoku-by-L2G-20050714_solution.svg.png)

...and its solution numbers marked in red.

Note:

* The given board contain only digits 1-9 and the character '.'.
* You may assume that the given Sudoku puzzle will have a single unique solution.
* The given board size is always 9x9.

思路：

3*3分块的索引计算如下：假设当前位置是(row, col)，那么**块索引 = (row / 3) * 3 + col / 3**，示意图如下：

![36_boxes_2.png](https://pic.leetcode-cn.com/5a7856c3c2a2185600b7cb5cd3fd50101281af7391a70a63293d82d62873aadd-36_boxes_2.png)

基本思路是逐行填写，填写的时候逐一检查1~9是否符合约束条件，如果当前数字d符合条件，就填入当前位置，然后递归填写下一个位置；

这里的**约束条件**指的是：如果数字d放置在(row, col)，那么对于整个矩阵来说，第row行、第col列、以及第 i 块都不能再放置这个数字了（这里的 i = (row / 3) * 3 + col / 3）。

我们定义三个布尔数组，分别标记数字 d 在行、列、块是否已经使用过

* boolean\[9][10] rowUsed。如果rowUsed\[0][9]==true，表示在第0行，数字9已经使用过了，那么在第0行不能再填入数字9了。
* boolean\[9][10] colUsed。解释同上。
* boolean\[9][10] blockUsed。如果blockUsed\[0][9]==true，表示在第0块，数字9已经使用过了，那么在第0块不能再填入数字9了。


思路：

本题的题解完全符合“回溯思想”摘录中case 1（判断一个问题有没有解）的情况。自己做的时候没有严格遵循模板所以在细节处出错了。


题解：

```java
// 基础版本（还有更优化的版本）
class Solution {
    private boolean[][] rowUsed = new boolean[9][10];
    private boolean[][] colUsed = new boolean[9][10];
    private boolean[][] blockUsed = new boolean[9][10];
    private int ROW_SIZE;
    private int COL_SIZE;

    public void solveSudoku(char[][] board) {
        ROW_SIZE = board.length;
        COL_SIZE = board[0].length;

        // 初始化棋盘
        for (int i = 0; i < ROW_SIZE; i++) {
            for (int j = 0; j < COL_SIZE; j++) {
                if (board[i][j] != '.') {
                    int d = Character.getNumericValue(board[i][j]);
                    int blockIdx = (i / 3) * 3 + j / 3;
                    rowUsed[i][d] = true;
                    colUsed[j][d] = true;
                    blockUsed[blockIdx][d] = true;
                }
            }
        }
        solve(board, 0, 0);
    }

    private boolean solve(char[][] board, int row, int col) {
        if (col == COL_SIZE) {
            col = 0;
            row++;
            if (row == ROW_SIZE) {
                return true;
            }
        }

        if (board[row][col] == '.') {
            for (int d = 1; d <= 9; d++) {
                if (check(d, row, col)) {
                    int blockIdx = (row / 3) * 3 + col / 3;
                    rowUsed[row][d] = true;
                    colUsed[col][d] = true;
                    blockUsed[blockIdx][d] = true;
                    board[row][col] = (char) (d + '0');

                    if(solve(board, row, col + 1)){
                        return true;
                    }

                    board[row][col] = '.';
                    rowUsed[row][d] = false;
                    colUsed[col][d] = false;
                    blockUsed[blockIdx][d] = false;
                }
            }
        } else {
            return solve(board, row, col + 1);
        }
        return false;
    }

    // 如果数字 d 允许放置在(row, col)，返回true
    private boolean check(int d, int row, int col) {
        int blockIdx = (row / 3) * 3 + col / 3;
        return (!rowUsed[row][d]) && (!colUsed[col][d]) && (!blockUsed[blockIdx][d]);
    }
}
```





#### 89. Gray Code

思路：本题应该不是回溯类别的，找规律背题目，不具有适用性。（参考[这里](https://leetcode-cn.com/problems/gray-code/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--12/)）

题解：

```java
class Solution {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        if (n < 0) return result;
        result.add(0);
        for (int i = 0; i < n; i++) {
            int add = 1 << i;
            for (int j = result.size() - 1; j >= 0; j--) {
                result.add(result.get(j) + add);
            }
        }
        return result;
    }
}
```




#### 二、树（Tree）

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
            result.add(new ArrayList<>(path)); 
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





#### 三、深度优先搜索（Depth First Search）



#### 四、广度优先搜索（Breadth First Search）



#### 五、动态规划（Dynamic Programming）

#### [198. House Robber](https://leetcode-cn.com/problems/house-robber/)

分析：

dp[i] 表示数组 nums[0...i] 范围内在不触动警报装置的情况下，能够偷窃到的最高金额。最终结果保留在dp[nums.length-1] 中。

题解1：

```java
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        if(nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]);
        for(int i=2; i<nums.length;i++){
            dp[i] = Math.max(dp[i-2] + nums[i], dp[i-1]);
        }
        return dp[nums.length-1];
    }
}

//时间复杂度：O(N)（即遍历一遍数组）
//空间复杂度：O(N)（开辟dp[]数组），空间复杂度可以简化为O(1)，我写的版本还不够精简。参考版本如下：
```

题解2：

```java
class Solution {
    public int rob(int[] nums) {
        if(nums.length == 0) return 0;
        int pre = 0, cur = nums[0], tmp;
        for(int i=1; i<nums.length;i++){
            tmp = cur;
            cur = Math.max(pre + nums[i], cur);
            pre = tmp;
        }
        return cur;
    }
}

//时间复杂度：O(N)
//空间复杂度：O(1)
```



#### [213. House Robber II](https://leetcode-cn.com/problems/house-robber-ii/)

分析：



题解：

```java
class Solution {
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.max(nums[0], nums[1]);
        return Math.max( myRob(Arrays.copyOfRange(nums,0,nums.length-1)),
                         myRob(Arrays.copyOfRange(nums,1,nums.length)));
    }

    private int myRob(int[] nums) {
        int[] dp = new int[nums.length];
        // 初始化
        dp[0] = nums[0];
        dp[1] = Math.max(nums[1], dp[0]); // 关键
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
        }
        return dp[nums.length-1];
    }
}
```

自己写的不够优雅，导致需要在rob()方法中预处理数组长度为0，1，2的情况，这是因为我的myRob()方法写的还不够具有通用性。



#### [337. House Robber III](https://leetcode-cn.com/problems/house-robber-iii/)

分析：

题解：

```java


```
