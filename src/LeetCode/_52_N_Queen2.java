package LeetCode;

public class _52_N_Queen2 {
    private static int N;
    private int total = 0;

    public int totalNQueens(int n) {
        N = n;
        int[] col = new int[N]; // col[row] 表示把Queen放在(row, col[row])处
        process(0, col);
        return total;
    }

    private void process(int row, int[] col) {
        if (row == N) {
            total++;
            for(int i=0;i<N;i++){
                System.out.print(col[i] + " ");
            }
            System.out.println();
        } else {
            for (int c = 0; c < N; c++) {
                // 假设把Queen放在(row, c)的位置，并检查该位置的合法性
                col[row] = c;
                boolean ok = true;
                for (int r = 0; r < row; r++) {
                    if (col[r] == col[row] || Math.abs(col[row] - col[r]) == row - r) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    //col[row] = c;
                    process(row + 1, col);
                }
            }
        }
    }

    public static void main(String[] args) {
        _52_N_Queen2 a = new _52_N_Queen2();
        System.out.println(a.totalNQueens(4));
    }
}
