package LeetCode;

public class _130_SurroundedRegions {
    private char[][] board;
    private int rows;
    private int cols;

    public void solve(char[][] board) {
        rows = board.length;
        if (rows == 0) return;
        cols = board[0].length;
        this.board = board;
        // 检查board的四个边界，如有发现'O'，把与其连通的'O'均替换成'#'
        for (int c = 0; c < cols; c++) {
            if (board[0][c] == 'O') dfs(0, c, '#');
            if (board[rows - 1][c] == 'O') dfs(rows - 1, c, '#');
        }
        for (int r = 0; r < rows; r++) {
            if (board[r][0] == 'O') dfs(r, 0, '#');
            if (board[r][cols - 1] == 'O') dfs(r, cols - 1, '#');
        }

        // 检查board的内部，如遇到'O'，将其替换成'X'；如遇到'#'，则将其还原为'O'
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (board[r][c] == 'O') board[r][c] = 'X';
                if (board[r][c] == '#') board[r][c] = 'O';
            }
        }
    }

    private void dfs(int row, int col, char c) {
        // 判断是否越界
        if (row < 0 || row >= rows || col < 0 || col >= cols
                || board[row][col] == c || board[row][col] == 'X') return;
        board[row][col] = c;
        dfs(row + 1, col, c);
        dfs(row - 1, col, c);
        dfs(row, col + 1, c);
        dfs(row, col - 1, c);
    }

    public static void main(String[] args) {
        _130_SurroundedRegions obj = new _130_SurroundedRegions();
        char[][] board = new char[][]{
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'}};
        obj.solve(board);

        for (char[] chars : board) {
            for (char c : chars) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }
}
