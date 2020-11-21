package LeetCode;

public class _037_SudokuSolver {
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

    public static void main(String[] args) {
        _037_SudokuSolver obj = new _037_SudokuSolver();
        char[][] board = {
                {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
                {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
                {'.', '9', '8', '.', '.', '.', '.', '6', '.'},

                {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
                {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
                {'7', '.', '.', '.', '2', '.', '.', '.', '6'},

                {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
                {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
                {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };

        obj.solveSudoku(board);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}