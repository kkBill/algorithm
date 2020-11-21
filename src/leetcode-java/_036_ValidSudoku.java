package LeetCode;

public class _036_ValidSudoku {
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][10];
        int[][] cols = new int[9][10];
        int[][] bolcks = new int[9][10];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') continue;

                int blockIndex = i/3*3 + j/3;
                int digit = board[i][j]-'0';

                if(rows[i][digit] == 1) return false;
                else rows[i][digit] = 1;

                if(cols[j][digit] == 1) return false;
                else cols[j][digit] = 1;

                if(bolcks[blockIndex][digit] == 1) return false;
                else bolcks[blockIndex][digit] = 1;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _036_ValidSudoku obj = new _036_ValidSudoku();
        char[][] board = new char[][]{};

    }
}
