package LeetCode;

public class _079_WordSearch {
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
                boolean isValid = (nextRow >= 0 && nextRow < ROW_SIZE) && (nextCol >= 0 && nextCol < COL_SIZE);
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

    public static void main(String[] args) {
        _079_WordSearch obj = new _079_WordSearch();

        char[][] board = {{'C', 'A', 'A'},
                {'A', 'A', 'A'},
                {'B', 'C', 'D'}};
        String word1 = "AAB";
        String word2 = "SEE";
        String word3 = "A";
        //System.out.println(word3.substring(1).length() == 0);

        System.out.println(obj.exist(board, word1));
        //System.out.println(obj.exist(board, word2));
        //System.out.println(obj.exist(board, word3));
    }
}