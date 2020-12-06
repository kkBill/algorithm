# 回溯问题
问题列表
  - [写在前面](#写在前面)
  - [17. 电话号码的字母组合](#17-电话号码的字母组合)
  - [22. 括号生成](#22-括号生成)
  - [37. 解数独](#37-解数独)
  - [39. 组合总和](#39-组合总和)
  - [40. 组合总和II](#40-组合总和ii)
  - [46. 全排列](#46-全排列)
  - [47. 全排列II](#47-全排列ii)
  - [77. 组合](#77-组合)
  - [78. 子集](#78-子集)
  - [90. 子集II](#90-子集ii)
  - [79. 单词搜索](#79-单词搜索)
  - [212. 单词搜索II](#212-单词搜索ii未完成)
  - [93. 复原IP地址](#93-复原ip地址)
  - [131. 分割回文串](#131-分割回文串)
  - [679. 24 点游戏](#679-24-点游戏)


---

## 写在前面

关于回溯算法的基本思想，可以看[回溯思想](./backtrack-understanding.md)这篇文章先有个整体的了解。

回溯算法的基本代码模板：

```java
public void recursion(int level, int param) {
    // 1. 递归边界
    if(level > MAX_LEVEL) {
        // 处理结果
        return
    }

    // 2. 处理当前层的逻辑
    process(level, param)

    // 3. 进入到下一层
    recursion(level+1, newParam)

    // 4. 如果有必要，在这里进行状态重置或者资源清理(也就是回溯，回到前一个状态)
    ...
}
```

简单的说，回溯可以理解为递归的一种形式。只要是递归，最重要的一点，就是要设置合理的递归出口，否则递归会一直进行下去。在对回溯(递归)类问题进行抽象建模的时候，我认为，最重要的就是如何理解递归的“层次关系”，也就是模板代码中的`level`所代表的的含义。一旦确定了这个“层次关系”，就可以明确递归出口是什么了，然后，我们只要处理当前递归层的逻辑即可。

---

## [17. 电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)

给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。

给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

<img src="https://assets.leetcode-cn.com/aliyun-lc-upload/original_images/17_telephone_keypad.png" alt="img" style="zoom:50%;" />

```
示例:
输入："23"
输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
```

分析：这一题属于求“所有解”的问题。

Java版

```java
class Solution {
    Map<String, String> map = new HashMap<String, String>() {{
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
        List<String> res = new ArrayList<>();
        if(digits.length() == 0) return res;
        dfs(digits, 0, "", res);
        return res;
    }

    private void dfs(String digits, int index, String path, List<String> res) {
        if(index >= digits.length()) {
            res.add(path);
            return;
        }

        String digit = digits.substring(index, index+1);
        String letters = map.get(digit);
        for(int i = 0; i < letters.length(); i++) {
            String ch = letters.substring(i, i+1);
            dfs(digits, index+1, path+ch, res);
        }
    }
}
```

Go版

```go
func letterCombinations(digits string) []string {
    if digits == "" {
        return []string{}
    }
	phone := map[string]string {
		"2":"abc",
		"3":"def",
		"4":"ghi",
		"5":"jkl",
		"6":"mno",
		"7":"pqrs",
		"8":"tuv",
		"9":"wxyz",
	}

	var res []string
	var dfs func(index, n int, path string)
	dfs = func(index, n int, path string) {
    // 边界条件
		if index == n {
			res = append(res, path)
			return
		}
        
		digit := string(digits[index])
		chars, _ := phone[digit]
		for _, ch := range chars {
      // 处理当前层逻辑
			path += string(ch)
      // 进入下一层
			dfs(index+1,n,path)
			// 回溯
      path = path[:len(path)-1]
		}
	}

	dfs(0, len(digits), "")
	return res
}
```



## [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/)

数字 *n* 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 **有效的** 括号组合。

```
输入：n = 3
输出：[
       "((()))",
       "(()())",
       "(())()",
       "()(())",
       "()()()"
     ]
```

分析：摘自[这里](https://leetcode.com/problems/generate-parentheses/discuss/10100/Easy-to-understand-Java-backtracking-solution)
```
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
```

Java版

```java
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        if(n <= 0) return res;
        dfs(n, 0, 0, "", res);
        return res;
    }

    private void dfs(int n, int leftCnt, int rightCnt, String path, List<String> res) {
        if(leftCnt + rightCnt > 2 * n) return;
        if(leftCnt + rightCnt == 2 * n) {
            res.add(path);
            return;
        }
        // 事实上，可以把条件1简化为 if(leftCnt < n){...}
        if(leftCnt == rightCnt || (leftCnt > rightCnt && leftCnt < n)) { // 条件1
            dfs(n, leftCnt+1, rightCnt, path+"(", res); 
        }
        if(leftCnt > rightCnt) { // 条件2
            dfs(n, leftCnt, rightCnt+1, path+")", res);
        }
    }
}
```

Go版

```go
func generateParenthesis(n int) []string {
	var res []string
	var dfs func(left, right int, s string)
	// left,right分别表示左括号和右括号的个数
	dfs = func(left, right int, s string) {
		// 边界条件
		if left + right == 2 * n {
			res = append(res, s)
			return
		}
		// 处理当前层，并进入下一层
		if left < n {
			dfs(left+1, right, s + "(")
		}
		if left > right {
			dfs(left, right+1, s + ")")
		}
	}
	dfs(0, 0, "")
	return res
}
```



## [37. 解数独](https://leetcode-cn.com/problems/sudoku-solver/)

编写一个程序，通过已填充的空格来解决数独问题。

一个数独的解法需遵循如下规则：

* 数字 1-9 在每一行只能出现一次。
* 数字 1-9 在每一列只能出现一次。
* 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。
* 空白格用 '.' 表示。

分析：

3*3分块的索引计算如下：假设当前位置是(row, col)，那么**块索引 = (row / 3) * 3 + col / 3**，示意图如下：

<img src="https://pic.leetcode-cn.com/5a7856c3c2a2185600b7cb5cd3fd50101281af7391a70a63293d82d62873aadd-36_boxes_2.png" alt="img" style="zoom:30%;" />

基本思路是逐行填写，填写的时候逐一检查1~9是否符合约束条件，如果当前数字d符合条件，就填入当前位置，然后递归填写下一个位置；

这里的**约束条件**指的是：如果数字d放置在(row, col)，那么对于整个矩阵来说，第row行、第col列、以及第 i 块都不能再放置这个数字了（这里的 `i = (row / 3) * 3 + col / 3`）。

我们定义三个布尔数组，分别标记数字 d 在行、列、块是否已经使用过

* `boolean[9][10] rowUsed`。如果`rowUsed[0][9]==true`，表示在第0行，数字9已经使用过了，那么在第0行不能再填入数字9了。
* `boolean[9][10] colUsed`。解释同上。
* `boolean[9][10] blockUsed`。如果`blockUsed[0][9]==true`，表示在第0块，数字9已经使用过了，那么在第0块不能再填入数字9了。


思路：

本题完全符合“回溯思想”摘录中case 1（判断一个问题有没有解）的情况。


Java版

```java
class Solution {
    boolean[][] rowUsed = new boolean[9][10];
    boolean[][] colUsed = new boolean[9][10];
    boolean[][] blockUsed = new boolean[9][10];

    public void solveSudoku(char[][] board) {
        // 初始化
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] != '.') {
                    int val = board[i][j] - '0';
                    rowUsed[i][val] = true;
                    colUsed[j][val] = true;
                    blockUsed[(i/3)*3+j/3][val] = true;
                }
            }
        }
        dfs(board, 0, 0);
    }

    private boolean dfs(char[][] board, int row, int col) {
        // 边界条件：至关重要
        // 二维边界条件
        if(col == 9) {
            col = 0;
            row++;
            if(row == 9) return true;
        }
        if(board[row][col] != '.') return dfs(board, row, col+1);
        for(int v = 1; v <= 9; v++) {
            if(check(v, row, col)) {
                board[row][col] = (char)(v+'0');
                rowUsed[row][v] = true;
                colUsed[col][v] = true;
                blockUsed[(row/3)*3 + col/3][v] = true;
                
                if(dfs(board, row, col+1)) return true;

                // 回溯
                board[row][col] = '.';
                rowUsed[row][v] = false;
                colUsed[col][v] = false;
                blockUsed[(row/3)*3 + col/3][v] = false;
            }
        }
        return false;
    }

    private boolean check(int v, int row, int col) {
        if(rowUsed[row][v] || colUsed[col][v] || blockUsed[(row/3)*3 + col/3][v]) return false;
        return true;
    }
}
```

Go版（为啥提交有错？）

```go
var (
	colUsed [9][10]bool
	rowUsed [9][10]bool
	blockUsed [9][10]bool
)
func solveSudoku(board [][]byte)  {
	// 初始化
	for r := 0; r < 9; r++ {
		for c := 0; c < 9; c++ {
			if board[r][c] != '.' {
				num := int(board[r][c]-'0')
				rowUsed[r][num] = true
				colUsed[c][num] = true
				blockUsed[(r / 3) * 3 + c / 3][num] = true
			}
		}
	}
	dfs(board, 0, 0, 9, 9)
}

func dfs(board [][]byte, r, c, rows, cols int) bool {
	// 边界条件
	if c == cols {
		c = 0
		r++
		if r == rows {
			return true
		}
	}
	if board[r][c] != '.' {
		return dfs(board, r, c+1, rows, cols)
	}
	// 处理当前层逻辑
	block := (r / 3) * 3 + c / 3 // 位置(r，c)所在的区块
	for i := 1; i <= 9; i++ {
		if !rowUsed[r][i] && !colUsed[c][i]&& !blockUsed[block][i] {
			// 处理当前层
			board[r][c] = byte(i+'0')
			rowUsed[r][i] = true
			colUsed[c][i] = true
			blockUsed[block][i] = true
			// 进入下一层
			if dfs(board, r, c+1, rows, cols) {
				return true
			}
			// 回溯
			board[r][c] = byte('.')
			rowUsed[r][i] = false
			colUsed[c][i] = false
			blockUsed[block][i] = false
		}
	}
	return false
}
```

Go版（这样提交就不会有错~）

```go
func solveSudoku(board [][]byte)  {
    var (
        colUsed [9][10]bool
        rowUsed [9][10]bool
        blockUsed [9][10]bool
    )
    // 初始化
    for r := 0; r < 9; r++ {
      for c := 0; c < 9; c++ {
        if board[r][c] != '.' {
          num := int(board[r][c]-'0')
          rowUsed[r][num] = true
          colUsed[c][num] = true
          blockUsed[(r / 3) * 3 + c / 3][num] = true
        }
      }
    }

    var dfs func(board [][]byte, r, c, rows, cols int) bool

    dfs = func (board [][]byte, r, c, rows, cols int) bool {
        // 边界条件
        if c == cols {
            c = 0
            r++
            if r == rows {
                return true
            }
        }
        if board[r][c] != '.' {
            return dfs(board, r, c+1, rows, cols)
        }
        // 处理当前层逻辑
        block := (r / 3) * 3 + c / 3 // 位置(r，c)所在的区块
        for i := 1; i <= 9; i++ {
            if !rowUsed[r][i] && !colUsed[c][i]&& !blockUsed[block][i] {
                // 处理当前层
                board[r][c] = byte(i+'0')
                rowUsed[r][i] = true
                colUsed[c][i] = true
                blockUsed[block][i] = true
                // 进入下一层
                if dfs(board, r, c+1, rows, cols) {
                    return true
                }
                // 回溯
                board[r][c] = byte('.')
                rowUsed[r][i] = false
                colUsed[c][i] = false
                blockUsed[block][i] = false
            }
        }
        return false
    }

	dfs(board, 0, 0, 9, 9)
}
```



## [39. 组合总和](https://leetcode-cn.com/problems/combination-sum/)

给定一个**无重复元素**的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的数字可以**无限制重复被选取**。

说明：

* 所有数字（包括 target）都是正整数
* 解集不能包含重复的组合。 

```
示例 1：
输入：candidates = [2,3,6,7], target = 7,
所求解集为：
[
  [7],
  [2,2,3]
]

示例 2：
输入：candidates = [2,3,5], target = 8,
所求解集为：
[
  [2,2,2,2],
  [2,3,3],
  [3,5]
]
```

分析：

Java版

```java
class Solution {
    public List<List<Integer>> combinationSum(int[] nums, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, target, new ArrayList<>(), res);
        return res;
    }

    public void dfs(int[] nums, int start, int target, 
                    List<Integer> path, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = start; i < nums.length; i++) {
            if(target - nums[i] < 0) continue;
            target -= nums[i];
            path.add(nums[i]);
            dfs(nums, i, target, path, res);
            // 回溯
            path.remove(path.size()-1);
            target += nums[i];
        }
    }
}
```

Go版

```go
func combinationSum(candidates []int, target int) [][]int {
	var res [][]int
	var dfs func(target, begin int, path []int)
	dfs = func(target, begin int, path []int) {
		// 边界条件
		if target == 0 {
			res = append(res, append([]int{}, path...))
			return
		}
		for i := begin; i < len(candidates); i++ {
			if target - candidates[i] < 0 {
				continue
			}
			// 处理当前层逻辑
			path = append(path, candidates[i])
			target -= candidates[i]
			// 进入下一层
			dfs(target, i, path)
			// 回溯
			target += candidates[i]
			path = path[:len(path)-1]
		}
	}

	dfs(target,0,[]int{})
	return res
}
```



## [40. 组合总和II](https://leetcode-cn.com/problems/combination-sum-ii/)

给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。

candidates 中的每个数字在**每个组合中只能使用一次**。

说明：

* 所有数字（包括目标数）都是正整数。
* 解集不能包含重复的组合。 

```
示例 1:
输入: candidates = [10,1,2,7,6,1,5], target = 8,
所求解集为:
[
  [1, 7],
  [1, 2, 5],
  [2, 6],
  [1, 1, 6]
]

示例 2:
输入: candidates = [2,5,2,1,2], target = 5,
所求解集为:
[
  [1,2,2],
  [5]
]
```

分析：注意，本题与39题的区别在于，**数组中的元素可能是重复的，并且每个元素只能使用一次**。

Java版

```java
class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        if(candidates.length == 0) return res;
        Arrays.sort(candidates); 
        dfs(candidates, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] candidates, int index, int target, 
                     List<Integer> path, List<List<Integer>> res) {
        if(target == 0) {
            res.add(new ArrayList<>(path));
            return;
        }

        for(int i = index; i < candidates.length && target - candidates[i] >= 0; i++) {
            // 去重
            if(i > index && candidates[i] == candidates[i-1]) {
                continue;
            }
            path.add(candidates[i]);
            dfs(candidates, i+1, target-candidates[i], path, res);
            path.remove(path.size()-1);
        }
    }
}
```

Go版（在这个版本实现中，我使用了used []bool来标记一个元素是否被访问过，但这其实没有必要的。在一段时间后重新做这个题目，我把这一题与第47题搞混了）

```go
func combinationSum2(candidates []int, target int) [][]int {
	sort.Slice(candidates, func(i, j int) bool {
		return candidates[i] < candidates[j]
	})
	var res [][]int
	var dfs func(target, begin int, path []int, used []bool)
	dfs = func(target, begin int, path []int, used []bool) {
		// 边界条件
		if target == 0 {
			res = append(res, append([]int{}, path...))
			return
		}
		// 处理当前层的逻辑
		for i := begin; i < len(candidates); i++ {
			// 剪枝
			if target - candidates[i] < 0 {
				return
			}
			// 去重
			if i > begin && candidates[i] == candidates[i-1] && !used[i-1] {
				continue
			}

			path = append(path, candidates[i])
			target -= candidates[i]
			used[i] = true
			// 进入下一层
			dfs(target, i+1, path, used)
			// 回溯
			target += candidates[i]
			path = path[:len(path)-1]
			used[i] = false
		}
	}

	dfs(target, 0, []int{}, make([]bool, len(candidates)))
	return res
}
```

Go版（正确版）

```go
func combinationSum2(candidates []int, target int) [][]int {
	sort.Slice(candidates, func(i, j int) bool {
		return candidates[i] < candidates[j]
	})
	var res [][]int
	var dfs func(target, begin int, path []int)
	dfs = func(target, begin int, path []int) {
		// 边界条件
		if target == 0 {
			res = append(res, append([]int{}, path...))
			return
		}
		// 处理当前层的逻辑
		for i := begin; i < len(candidates); i++ {
			// 剪枝
			if target - candidates[i] < 0 {
				return
			}
			// 去重
			if i > begin && candidates[i] == candidates[i-1] {
				continue
			}
			// 处理当前层
			path = append(path, candidates[i])
			target -= candidates[i]
			// 进入下一层
			dfs(target, i+1, path)
			// 回溯
			target += candidates[i]
			path = path[:len(path)-1]
		}
	}

	dfs(target, 0, []int{})
	return res
}
```





## [46. 全排列](https://leetcode-cn.com/problems/permutations/)

给定一个 **没有重复** 数字的序列，返回其所有可能的全排列。

```
输入: [1,2,3]
输出:
[
  [1,2,3],
  [1,3,2],
  [2,1,3],
  [2,3,1],
  [3,1,2],
  [3,2,1]
]
```

**方法1**：通过visit数组标记已经被访问过的元素，个人认为这是比较通用的解法，**每次遍历都从下标0开始**。

代码流如下：
```
path []
i=0: used[0]==false -> path=[1],used[0]=true
	i=0: used[0]==true -> continue
	i=1: used[1]==false -> path=[1,2],used[1]=true
		i=0: used[0]==true -> continue
		i=1: used[1]==true -> continue
		i=2: used[2]==false -> path=[1,2,3],used[2]=true  ==> collect [1,2,3]
		(紧接着发生两次回溯)
	i=2: used[2]==false -> path=[1,3],used[2]=true
		i=0: used[0]==true -> continue
		i=1: used[1]==false -> path=[1,3,2],used[1]=true ==> collect [1,3,2]
		(紧接着发生一次回溯)
		i=2: used[2]==true -> continue
		(紧接着发生两次回溯)
i=1: 	used[1]==false -> path=[2],used[1]=true
	...
```
Java版
```java
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        //Arrays.sort(nums); //对于没有重复数字的序列，并不需要排序
        dfs(nums, visited, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, 
                     List<Integer> path, List<List<Integer>> res) {
        if(path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
        
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            path.add(nums[i]);
            dfs(nums, visited, path, res);
            path.remove(path.size()-1);
            visited[i] = false;
        }
    }
}
```

Go版

```go
func permute(nums []int) [][]int {
	var res [][]int
	var dfs func(n int, path []int, visit []bool)
	dfs = func(n int, path []int, visit []bool) {
		if len(path) == n {
			//tmp := make([]int, n)
			//copy(tmp, path)
			//res = append(res, tmp)
			res = append(res, append([]int{}, path...))
			return
		}
		for i := 0; i < n; i++ {
			if visit[i] { // 如果下标为i的元素已经被访问过了
				continue
			}
			// 处理当前层逻辑
			visit[i] = true
			path = append(path, nums[i])
			// 进入下一层（注意，这里的"层数"指的是path内包含的元素个数）
			dfs(n, path, visit)
			// 回溯，重置状态
			path = path[:len(path)-1]
			visit[i] = false
		}
	}
	dfs(len(nums), []int{}, make([]bool, len(nums)))
	return res
}
```



**方法2**：通过交换

```java
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



## [47. 全排列II](https://leetcode-cn.com/problems/permutations-ii/)

给定一个可**包含重复数字**的序列，返回所有不重复的全排列。

```
输入: [1,1,2]
输出:
[
  [1,1,2],
  [1,2,1],
  [2,1,1]
]
```

分析：

Java版

```java
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums); // 使重复元素相邻，方便去重
        dfs(nums, visited, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, boolean[] visited, 
                     List<Integer> path, List<List<Integer>> res) {
        if(path.size() == nums.length) {
            res.add(new ArrayList<>(path));
            return;
        }
    
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue; 
            if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue; // 去重
            visited[i] = true;
            path.add(nums[i]);
            dfs(nums, visited, path, res);
            path.remove(path.size()-1);
            visited[i] = false;
        }
    }
}
```

Go版

```go
func permuteUnique(nums []int) [][]int {
	var res [][]int
	var dfs func(n int, path []int, visit []bool)
	sort.Slice(nums, func(i, j int) bool {return nums[i] < nums[j]})

	dfs = func(n int, path []int, visit []bool) {
		if len(path) == n {
			res = append(res, append([]int{}, path...))
			return
		}
		for i := 0; i < n; i++ {
			if visit[i] { // 如果下标为i对应的元素已经被访问过了
				continue
			}
			if i > 0 && nums[i] == nums[i-1] && !visit[i-1] { // 去重
				continue
			}
			// 处理当前层逻辑
			visit[i] = true
			path = append(path, nums[i])
			// 进入下一层（注意，这里的"层数"指的是path内包含的元素个数）
			dfs(n, path, visit)
			// 回溯，重置状态
			path = path[:len(path)-1]
			visit[i] = false
		}
	}
	dfs(len(nums), []int{}, make([]bool, len(nums)))
	return res
}
```



## [77. 组合](https://leetcode-cn.com/problems/combinations/)

给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
```
示例:
输入: n = 4, k = 2
输出:
[
  [2,4],
  [3,4],
  [2,3],
  [1,2],
  [1,3],
  [1,4],
]
```
分析：

Java版

```

```

Go版

```go
func combine(n int, k int) [][]int {
	var res [][]int
	var dfs func(begin int, path []int)
	dfs = func(begin int, path []int) {
		// 边界条件（达到了k个数）
		if len(path) == k {
			// 注意不能直接res = append(res, path)，底层是同一个引用
			res = append(res, append([]int{}, path...))
			return
		}

		// 剪枝
		// n-begin+1 表示从当前位置到最后n的个数，如果len(path)+n-begin+1 都不可能大于等于 k 了
		// 说明从位置 begin 开始就没必要继续递归下去了
		if len(path) + n - begin + 1 < k {
			return
		}

		for i := begin; i <= n; i++ {
			// 处理当前层
			path = append(path, i)
			// 进入下一层
			dfs(i+1, path)
			// 状态恢复，回溯
			path = path[:len(path)-1]
		}
	}

	dfs(1, []int{})
	return res
}
```



## [78. 子集](https://leetcode-cn.com/problems/subsets/)

给定一组**不含重复元素**的整数数组 *nums*，返回该数组所有可能的子集。

**说明**：解集不能包含重复的子集。

```
输入: nums = [1,2,3]
输出:
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

分析：

**方法1**：以子集的长度为基准构建递归树

Java版

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        for(int subLen = 0; subLen <= nums.length; subLen++) {
            dfs(nums, 0, subLen, new ArrayList<>(), res);
        }
        return res;
    }

    private void dfs(int[] nums, int start, int subLen, 
                     List<Integer> path, List<List<Integer>> res) {
        if(path.size() == subLen) {
            //System.out.println(path.toString());
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i+1, subLen, path, res);//注意这里是i+1
            path.remove(path.size()-1);
        }
    }
}
```

Go版

```go
func subsets(nums []int) [][]int {
	var res [][]int
	var dfs func(size int, begin int, path []int)

	dfs = func(size int, begin int, path []int) {
		// 边界
		if size == len(path) {
			res = append(res, append([]int{}, path...))
			//fmt.Printf("%v\n", res)
			return
		}
		for i := begin; i < len(nums); i++ {
			// 处理当前层逻辑
			path = append(path, nums[i])
			// 进入下一层
			dfs(size, i+1, path)
			// 回溯
			path = path[:len(path)-1]
		}
	}

	for size := 0; size <= len(nums); size++ {
		dfs(size, 0, []int{})
	}
	return res
}
```

该方法生成子集的顺序为：

```
[] 
[1] 
[2] 
[3] 
[1, 2] 
[1, 3] 
[2, 3] 
[1, 2, 3]
```

**方法2**：深度优先递归 

Java版

```java
class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int start, 
                     List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for(int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            dfs(nums, i+1, path, res);
            path.remove(path.size()-1);
        }
    }
}
```

Go版

```go
func subsets(nums []int) [][]int {
	var res [][]int
	var dfs func(begin int, path []int)
	dfs = func(begin int, path []int) {
    // 边界条件(其实，这个函数递归出口不写也可以，因为对于范围外的begin，下面的for loop是进不去的)
    // 但思考的过程是不能省略的！
		if begin > len(nums) {
			return
		}
		// 处理当前层的逻辑
		res = append(res, append([]int{}, path...))
		for i := begin; i < len(nums); i++ {
			path = append(path, nums[i])
			// 进入下一层
			dfs(i+1, path)
			// 回溯
			path = path[:len(path)-1]
		}
	}

	dfs(0, []int{})
	return res
}
```

该方法生成子集的顺序为：

```
start=0: path=[]             ==> res=[[]]
i=0: add(1),path=[1]         ==> res=[[],[1]]
	start=1: path=[1]
	i=1: add(2),path=[1,2]     ==> res=[[],[1],[1,2]]
		start=2: path=[1,2]
		i=2: add(3),path=[1,2,3] ==> res=[[],[1],[1,2],[1,2,3]]
	i=2: add(3),path=[1,3]     ==> res=[[],[1],[1,2],[1,2,3],[1,3]]
i=1: add(2),path=[2]         ==> res=[[],[1],[1,2],[1,2,3],[1,3],[2]]
	start=2: path=[2]
	i=2: add(3),path=[2,3]     ==> res=[[],[1],[1,2],[1,2,3],[1,3],[2],[2,3]]
i=2: add(3),path=[3]         ==> res=[[],[1],[1,2],[1,2,3],[1,3],[2],[2,3],[3]]
```



## [90. 子集II](https://leetcode-cn.com/problems/subsets-ii/)

给定一个**可能包含重复元素**的整数数组 ***nums***，返回该数组所有可能的子集。

**说明**：解集不能包含重复的子集。

```
输入: [1,2,2]
输出:
[
  [2],
  [1],
  [1,2,2],
  [2,2],
  [1,2],
  []
]
```

**方法1**：根据子集长度构建递归树

Java版（Go版略）

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for(int subLen = 0; subLen <= nums.length; subLen++) {
            dfs(nums, 0, subLen, new ArrayList<>(), res);
        }
        return res;
    }

    private void dfs(int[] nums, int start, int subLen, 
                     List<Integer> path, List<List<Integer>> res) {
        if(path.size() == subLen) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            path.add(nums[i]);
            dfs(nums, i+1, subLen, path, res);
            path.remove(path.size()-1);
        }
    }
}
```

**方法2**：深度优先递归

Java版

```java
class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        dfs(nums, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] nums, int start, 
                     List<Integer> path, List<List<Integer>> res) {
        res.add(new ArrayList<>(path));
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i-1]) continue;
            path.add(nums[i]);
            dfs(nums, i+1, path, res);
            path.remove(path.size()-1);
        }
    }
}
```

Go版

```go
func subsetsWithDup(nums []int) [][]int {
	var res [][]int
	var dfs func(begin int, path []int)
	sort.Slice(nums, func(i, j int) bool {return nums[i] < nums[j]})

	dfs = func(begin int, path []int) {
		//if begin > len(nums) {
		//	return
		//}
		res = append(res, append([]int{}, path...))
		//fmt.Printf("%v\n", res)
		for i := begin; i < len(nums); i++ {
			// 去重
			if i > begin && nums[i] == nums[i-1] {
				continue
			}
			path = append(path, nums[i])
			dfs(i+1, path)
			path = path[:len(path)-1]
		}
	}

	dfs(0, []int{})
	return res
}
```



## [79. 单词搜索](https://leetcode-cn.com/problems/word-search/)

给定一个二维网格和一个单词，找出该单词是否存在于网格中。

单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。

```
board =
[
  ['A','B','C','E'],
  ['S','F','C','S'],
  ['A','D','E','E']
]
给定 word = "ABCCED", 返回 true
给定 word = "SEE", 返回 true
给定 word = "ABCB", 返回 false
```

分析：对于上下左右四个方向，我们只要走通一个方向，即可确定最终解。这种模型就是说，只要有**至少一个**局部解存在，全局解就存在。

Java版

```java
class Solution {
    private boolean[][] visited;
    private int[] x_axis = new int[]{-1, 1, 0, 0};
    private int[] y_axis = new int[]{0, 0, -1, 1};

    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for(int x = 0; x < board.length; x++) {
            for(int y = 0; y < board[0].length; y++) {
                if(dfs(board, x, y, word, 0))  {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, int x, int y, String word, int start) {
        if(board[x][y] != word.charAt(start)) return false;
        if(start == word.length()-1) return board[x][y] == word.charAt(start);

        visited[x][y] = true;
        for(int i = 0; i < 4; i++) {
            int nextX = x + x_axis[i];
            int nextY = y + y_axis[i];
            boolean outOfBoundary = nextX < 0 || nextX >= board.length || nextY < 0 || nextY >= board[0].length;
            if(!outOfBoundary && !visited[nextX][nextY]) {
                if(dfs(board, nextX, nextY, word, start+1)) {
                    return true; // 只要其中一个子问题有解，全局就存在解，理解这一步是核心！
                }
            }
        }
        visited[x][y] = false;// 回溯
        return false; // 如果之前的4个方向的子问题均不存在解，那么到了这里一定是返回false
    }
}
```

Go版：这一版本写的边界条件不够简洁，还是Java版实现的更好。但还是贴出来，以示自己的思维过程。

```go
func exist(board [][]byte, word string) bool {
	// 初始化
	Iaxis := []int{-1, 1, 0, 0}
	Jaxis := []int{0, 0, -1, 1}
	ROWS, COLS := len(board), len(board[0])
	visited := make([][]bool, ROWS)
	for i := range visited {
		visited[i] = make([]bool, COLS)
	}

	// 定义函数
	var dfs func(index, i, j int, visited [][]bool) bool
	dfs = func(index, i, j int, visited [][]bool) bool {
		// 边界条件
		if index == len(word) {
			return true
		}
		// 处理当前层逻辑
		if word[index] != board[i][j] {
			return false
		}else if index == len(word)-1 {
			return word[index] == board[i][j]
		}
		visited[i][j] = true
		for k := 0; k < 4; k++ {
			nextI := i + Iaxis[k]
			nextJ := j + Jaxis[k]
			// 判断是否越界
			if nextI < 0 || nextI >= ROWS || nextJ < 0 || nextJ >= COLS {
				continue
			}
			// 判断是否已经被访问过
			if visited[nextI][nextJ] {
				continue
			}

			// 进入下一层
			if dfs(index + 1, nextI, nextJ, visited) {
				return true
			}
		}
		visited[i][j] = false
		return false
	}

	// 执行
	for i := 0; i < ROWS; i++ {
		for j := 0; j < COLS; j++ {
			if dfs(0, i, j, visited) {
				return true
			}
		}
	}
	return false
}
```



## [212. 单词搜索II](https://leetcode-cn.com/problems/word-search-ii/)[未完成]

最详细的参考：[题解](https://leetcode.com/problems/word-search-ii/discuss/59780/Java-15ms-Easiest-Solution-(100.00))



## [93. 复原IP地址](https://leetcode-cn.com/problems/restore-ip-addresses/)

给定一个只包含数字的字符串，复原它并返回所有可能的 IP 地址格式。有效的 IP 地址正好由四个整数（每个整数位于 0 到 255 之间组成），整数之间用 '.' 分隔。

```
输入: "25525511135"
输出: ["255.255.11.135", "255.255.111.35"]

// 下面这两个属于边界case，需要特别注意，即存在前导0的情况
输入: "010010"
输出: ["0.10.0.10","0.100.1.0"]

输入: "0000"
输出: ["0.0.0.0"]
```

分析：

Java版

```java
class Solution {
    public List<String> restoreIpAddresses(String s) {
        List<String> res = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(String s, int i, List<String> path, List<String> res) {
        if(path.size() > 4) return;

        if(i >= s.length() && path.size() == 4) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < path.size(); j++) {
                sb.append(path.get(j));
                if(j < path.size() - 1) sb.append(".");
            }
            res.add(sb.toString());
            return;
        }

        for(int k = 1; k <= 3; k++) {
            if(i + k <= s.length()) {
                String sub = s.substring(i, i+k);
                if(isValid(sub)) {
                    path.add(sub);
                    dfs(s, i+k, path, res);
                    path.remove(path.size()-1);//回溯
                }
            }
        }
    }

    private boolean isValid(String s) {
        int val = Integer.parseInt(s);
        if(val < 0 || val > 255 || (s.length() > 1 && s.startsWith("0"))) return false;
        else return true;
    }
}
```

Go版

```go
func restoreIpAddresses(s string) []string {
	var res []string
	var dfs func(begin int, path []string)
	dfs = func(begin int, path []string) {
		// 边界条件
    if len(path) > 4 { // 注意这个条件
			return
		}
		if begin == len(s) && len(path) == 4 {
			res = append(res, strings.Join(path, "."))
			return
		}
		for i := begin; i < len(s) && i < begin+3; i++ {
			sub := s[begin : i+1]
			// 判断这个分段是否合理
			// case 1: 当首字母为0时，当且仅当"0"是合法的，而"023"这样的是不合法的
			if sub[0] == '0' && len(sub) > 1 {
				continue
			}
			// case 2: 分段的大小在0~255之间
			val, err := strconv.Atoi(sub)
			if err != nil {
				panic(err)
			}
			if val < 0 || val > 255 {
				continue
			}
			//
			path = append(path, sub)
      // 进入下一层
			dfs(i+1, path)
      // 回溯
			path = path[:len(path)-1]
		}
	}
	dfs(0, []string{})
	return res
}
```



## [131. 分割回文串](https://leetcode-cn.com/problems/palindrome-partitioning/)

给定一个字符串 *s*，将 *s* 分割成一些子串，使每个子串都是回文串。返回 *s* 所有可能的分割方案。

```
输入: "aab"
输出:
[
  ["aa","b"],
  ["a","a","b"]
]
```

分析：

Java版

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(String s, int start, List<String> path, List<List<String>> res) {
        if(start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = start; i < s.length(); i++) {
            String sub = s.substring(start, i+1);
            if(isPalindrome(sub)) {
                path.add(sub);
                dfs(s, i+1, path, res);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s) {
        char[] ss = s.toCharArray();
        int l = 0, r = ss.length-1;
        while(l < r) {
            if(ss[l] != ss[r]) return false;
            l++;
            r--;
        }
        return true;
    }
}
```

下面这种实现更快，因为在判断子串是否为回文串时，没有使用substring生成新的子串，减少了开销。

```java
class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> res = new ArrayList<>();
        dfs(s, 0, new ArrayList<>(), res);
        return res;
    }

    private void dfs(String s, int start, List<String> path, List<List<String>> res) {
        if(start == s.length()) {
            res.add(new ArrayList<>(path));
            return;
        }
        for(int i = start; i < s.length(); i++) {
            if(isPalindrome(s, start, i)) {
                path.add(s.substring(start, i+1));
                dfs(s, i+1, path, res);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s, int l, int r) {
        while(l < r) {
            if(s.charAt(l) != s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}
```

Go版

```go
func partition(s string) [][]string {
	var res [][]string
	var dfs func(begin int, path []string)
	var isPalindrome func(s string) bool
	isPalindrome = func(s string) bool {
		l, r := 0, len(s)-1
		for l < r {
			if s[l] != s[r] {
				return false
			}
			l++
			r--
		}
		return true
	}
	dfs = func(begin int, path []string) {
		// 边界条件
		if begin == len(s) && len(path) > 0 {
			res = append(res, append([]string{}, path...))
			return
		}
		//
		for i := begin; i < len(s); i++ {
			sub := s[begin:i+1]
			if !isPalindrome(sub) {
				continue
			}
			path = append(path, sub)
			dfs(i+1, path)
			path = path[:len(path)-1]
		}
	}
	dfs(0, []string{})
	return res
}
```



## [679. 24 点游戏](https://leetcode-cn.com/problems/24-game/)

你有 4 张写有 1 到 9 数字的牌。你需要判断是否能通过 `*`，`/`，`+`，`-`，`(`，`)` 的运算得到 24。

```
示例 1:
输入: [4, 1, 8, 7]
输出: True
解释: (8-4) * (7-1) = 24

示例 2:
输入: [1, 2, 1, 2]
输出: False
```

分析：
一共有 4 个数和 3 个运算操作，因此可能性非常有限。一共有多少种可能性呢？

首先从 4 个数字中有序地选出 2 个数字，共有4×3=12 种选法，并选择加、减、乘、除 4 种运算操作之一，用得到的结果取代选出的 2个数字，剩下 3 个数字。

然后在剩下的3个数字中有序地选出 2个数字，共有3×2=6 种选法，并选择4 种运算操作之一，用得到的结果取代选出的2个数字，剩下2个数字。

最后剩下 2个数字，有 2种不同的顺序，并选择 4种运算操作之一。

因此，一共有12×4×6×4×2×4=9216 种不同的可能性。

**可以通过回溯的方法遍历所有不同的可能性。具体做法是，使用一个列表存储目前的全部数字，每次从列表中选出 2个数字，再选择一种运算操作，用计算得到的结果取代选出的 2个数字，这样列表中的数字就减少了 1个。重复上述步骤，直到列表中只剩下 1个数字，这个数字就是一种可能性的结果，如果结果等于24，则说明可以通过运算得到 24。如果所有的可能性的结果都不等于 24，则说明无法通过运算得到 24。**

实现时，有一些细节需要注意。

* 除法运算为实数除法，因此结果为浮点数，列表中存储的数字也都是浮点数。在判断结果是否等于 24 时应考虑精度误差，这道题中，误差小于 1e-6可以认为是相等。

* 进行除法运算时，除数不能为0，如果遇到除数为0的情况，则这种可能性可以直接排除。由于列表中存储的数字是浮点数，因此判断除数是否为 0时应考虑精度误差，这道题中，当一个数字的绝对值小于1e-6时，可以认为该数字等于0。

还有一个可以优化的点。

* 加法和乘法都满足交换律，因此如果选择的运算操作是加法或乘法，则对于选出的2 个数字不需要考虑不同的顺序，在遇到第二种顺序时可以不进行运算，直接跳过。

实现1：

```java
class Solution {
    private static int TARGET = 24;
    private static double eps = 1e-6; // 精度
    private static int ADD = 0, MUL = 1, SUB = 2, DIV = 3;

    public boolean judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        for(int num : nums) {
            list.add((double)num);
        }
        return dfs(list);
    }

    public boolean dfs(List<Double> nums) {
        if(nums.size() == 1) {
            return Math.abs(nums.get(0) - TARGET) <= eps;
        }
        int size = nums.size();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i == j) continue;
                // 初始化辅助数组tmp为非i和非j的两个元素，然后再对nums[i],nums[j]进行考虑
                List<Double> tmp = new ArrayList<>();
                for(int k = 0; k < size; k++) {
                    if(k != i && k != j) tmp.add(nums.get(k));
                }
                double a = nums.get(i), b = nums.get(j);

                // 分别考虑+,*,-,/
                for(int op = 0; op < 4; op++) {
                    // 对于"+"和"*"，避免重复
                    if(i > j && (op == ADD || op == MUL) ) continue;

                    if(op == ADD) {
                        tmp.add(a+b);
                    }else if(op == MUL) {
                        tmp.add(a*b);
                    }else if(op == SUB) {
                        tmp.add(a-b);
                    }else if(op == DIV) {
                        // 当b小于eps时，认为b==0，因此不能作为被除数
                        if(Math.abs(b) <= eps) continue; 
                        tmp.add(a/b);
                    }
                    if(dfs(tmp)) return true;
                    tmp.remove(tmp.size()-1);
                }
            }
        }
        return false;
    }
}
```



实现2：

```java
class Solution {
    private final double eps = 0.0001;
    public boolean judgePoint24(int[] nums) {
        List<Double> arrs = new ArrayList<>();
        for(int num : nums) {
            arrs.add((double)num);
        }
        return dfs(arrs);
    }

    public boolean dfs(List<Double> nums) {
        if(nums.size() == 1) {
            return Math.abs(nums.get(0) - 24) < eps;
        }

        for(int i = 0; i < nums.size(); i++) {
            for(int j = i+1; j < nums.size(); j++) {
                List<Double> copy = new ArrayList<>(nums);
//              注意，这里必须要先remove j，再remove i，因为 j > i
//              假设此时数组的长度为2，i = 0, j = 1,
//              如果先执行copy.remove(i)，那么执行完这一句之后，数组的长度就变成了1，
//              此时如果再执行remove(j)，就会发生越界的情况
                double b = copy.remove(j);
                double a = copy.remove(i);

                boolean isValid = false;
                // "+"
                copy.add(a+b);
                isValid = isValid || dfs(copy);

                // "-"
                copy.set(copy.size()-1, a-b);
                isValid = isValid || dfs(copy);
                copy.set(copy.size()-1, b-a);
                isValid = isValid || dfs(copy);

                // "*"
                copy.set(copy.size()-1, a*b);
                isValid = isValid || dfs(copy);

                // "/"
                if(a > eps) {
                    copy.set(copy.size()-1, b / a);
                    isValid = isValid || dfs(copy);
                }
                if(b > eps) {
                    copy.set(copy.size()-1, a / b);
                    isValid = isValid || dfs(copy);
                }

                // 如果有满足条件的，则返回true
                if(isValid) return true;
            }
        }
        return false;
    }
}
```



进阶：除了要判断能否凑成24点，还要输出所有可能的方案。

```java
public class _697_24Game {

    private static int TARGET = 24;
    private static double eps = 1e-6; // 精度
    private static int ADD = 0, MUL = 1, SUB = 2, DIV = 3;

    public void judgePoint24(int[] nums) {
        List<Double> list = new ArrayList<>();
        List<String> path = new ArrayList<>();
        for(int num : nums) {
            list.add((double)num);
            path.add(Integer.toString(num));
        }
        List<List<String>> res = new ArrayList<>();
        dfs(list, path, res);
        for(List<String> line : res) {
            System.out.println(line.toString());
        }
    }

    public void dfs(List<Double> nums, List<String> path, List<List<String>> res) {
        if(nums.size() == 1) {
            if(Math.abs(nums.get(0) - TARGET) <= eps) {
                res.add(new ArrayList<>(path));
            }
            return;
        }
        int size = nums.size();
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(i == j) continue;
                // 初始化辅助数组tmp为非i和非j的两个元素，然后再对nums[i],nums[j]进行考虑
                List<Double> tmp_nums = new ArrayList<>();
                List<String> tmp_path = new ArrayList<>();
                for(int k = 0; k < size; k++) {
                    if(k != i && k != j) {
                        tmp_nums.add(nums.get(k));
                        tmp_path.add(path.get(k));
                    }
                }
                double a = nums.get(i), b = nums.get(j);
                String as = path.get(i), bs = path.get(j);

                // 分别考虑+,*,-,/
                for(int op = 0; op < 4; op++) {
                    // 对于"+"和"*"，避免重复
                    if(i > j && (op == ADD || op == MUL) ) continue;

                    if(op == ADD) {
                        tmp_nums.add(a+b);
                        tmp_path.add("("+as+"+"+bs+")");
                    }else if(op == MUL) {
                        tmp_nums.add(a*b);
                        tmp_path.add("("+as+"*"+bs+")");
                    }else if(op == SUB) {
                        tmp_nums.add(a-b);
                        tmp_path.add("("+as+"-"+bs+")");
                    }else if(op == DIV) {
                        // 当b小于eps时，认为b==0，因此不能作为被除数
                        if(Math.abs(b) <= eps) continue; 
                        tmp_nums.add(a/b);
                        tmp_path.add("("+as+"/"+bs+")");
                    }
                    dfs(tmp_nums, tmp_path, res);
                    tmp_nums.remove(tmp_nums.size()-1);
                    tmp_path.remove(tmp_path.size()-1);
                }
            }
        }
    }

    public static void main(String[] args) {
        _697_24Game obj = new _697_24Game();
        int[] nums = new int[]{4, 1, 8, 7};
        obj.judgePoint24(nums);
    }
}
```
