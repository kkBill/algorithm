//给定一个二维网格和一个单词，找出该单词是否存在于网格中。 
//
// 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。 
//
// 
//
// 示例: 
//
// board =
//[
//  ['A','B','C','E'],
//  ['S','F','C','S'],
//  ['A','D','E','E']
//]
//
//给定 word = "ABCCED", 返回 true
//给定 word = "SEE", 返回 true
//给定 word = "ABCB", 返回 false 
//
// 
//
// 提示： 
//
// 
// board 和 word 中只包含大写和小写英文字母。 
// 1 <= board.length <= 200 
// 1 <= board[i].length <= 200 
// 1 <= word.length <= 10^3 
// 
// Related Topics 数组 回溯算法 
// 👍 702 👎 0

package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)
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

//leetcode submit region end(Prohibit modification and deletion)

func main() {
	board := [][]byte{
		{'A', 'B', 'C', 'E'},
		{'S', 'F', 'C', 'S'},
		{'A', 'D', 'E', 'E'},
	}
	fmt.Printf("%v\n", exist(board, "SEE")) // true
	fmt.Printf("%v\n", exist(board, "ABCB")) // false
	fmt.Printf("%v\n", exist(board, "ABFDEE")) // true
	fmt.Printf("%v\n", exist(board, "ABFA")) // false
	board = [][]byte{
		{'A', 'B', 'C', 'E'},
	}
	fmt.Printf("%v\n", exist(board, "ABCE")) // true
	board = [][]byte{
		{'A', 'A'},
	}
	fmt.Printf("%v\n", exist(board, "AAA")) // false
}
