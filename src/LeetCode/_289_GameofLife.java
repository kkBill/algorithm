package LeetCode;

public class _289_GameofLife {
    private static int[] iaxis = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] jaxis = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    public void gameOfLife(int[][] board) {
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveCells = getLiveCell(board, i, j);
                if ((board[i][j] & 1) == 1) { // board[i][j] == 1
                    // convert '1' to '0'
                    if (liveCells < 2 || liveCells > 3) board[i][j] = 3; // 0011
                } else { // board[i][j] == 0
                    // convert '0' to '1'
                    if (liveCells == 3) board[i][j] = 2; // 0010
                }
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 0010 说明需要改变状态
                if ((board[i][j] & 2) == 2) {
                    board[i][j] = (~board[i][j]) & 3;
                }
            }
        }
    }

    private int getLiveCell(int[][] board, int i, int j) {
        int count = 0;
        for(int k = 0; k < 8; k++) {
            if (isValid(board, i + iaxis[k], j + jaxis[k])) count++;
        }
        return count;
    }

    private boolean isValid(int[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length && (board[i][j] & 1) == 1;
    }

    public static void main(String[] args) {
        _289_GameofLife obj = new _289_GameofLife();
        int[][] board = new int[][]{
                {0, 1, 0},
                {0, 0, 1},
                {1, 1, 1},
                {0, 0, 0}};

        obj.gameOfLife(board);

        for (int[] arr : board) {
            for (int val : arr) {
                System.out.print(val + " ");
            }
            System.out.println();
        }
    }
}
