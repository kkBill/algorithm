//编写一个程序，通过填充空格来解决数独问题。 
//
// 一个数独的解法需遵循如下规则： 
//
// 
// 数字 1-9 在每一行只能出现一次。 
// 数字 1-9 在每一列只能出现一次。 
// 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。 
// 
//
// 空白格用 '.' 表示。 
//
// 
//
// 一个数独。 
//
// 
//
// 答案被标成红色。 
//
// 提示： 
//
// 
// 给定的数独序列只包含数字 1-9 和字符 '.' 。 
// 你可以假设给定的数独只有唯一解。 
// 给定数独永远是 9x9 形式的。 
// 
// Related Topics 哈希表 回溯算法 
// 👍 709 👎 0


package main

import "fmt"

//leetcode submit region begin(Prohibit modification and deletion)

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
		if !rowUsed[r][i] && !colUsed[c][i] && !blockUsed[block][i] {
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
			board[r][c] = '.'
			rowUsed[r][i] = false
			colUsed[c][i] = false
			blockUsed[block][i] = false
		}
	}
	board[r][c] = '.'
	return false
}

//leetcode submit region end(Prohibit modification and deletion)


func main() {
	board := [][]byte{
		{'.','.','5','7','4','8','.','.','.'},
		{'7','.','.','.','.','.','.','.','.'},
		{'.','2','.','1','.','9','.','.','.'},
		{'.','.','7','.','.','.','2','4','.'},
		{'.','6','4','.','1','.','5','9','.'},
		{'.','9','8','.','.','.','3','.','.'},
		{'.','.','.','8','.','3','.','2','.'},
		{'.','.','.','.','.','.','.','.','6'},
		{'.','.','.','2','7','5','9','.','.'}}

	solveSudoku(board)

	for r := 0; r < 9; r++ {
		for c := 0; c < 9; c++ {
			fmt.Printf("%d ", board[r][c]-'0')
		}
		fmt.Println()
	}
}