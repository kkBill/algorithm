package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _51_N_Queen {
    private static int N;
    private List<List<String>> result = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {
        N = n;
        int[] col = new int[N];
        process(0, col);
        return result;
    }

    private void process(int row, int[] col) {
        if (row == N) {
            List<String> solution = new ArrayList<>();
            for (int r = 0; r < N; r++) {
                StringBuilder sb = new StringBuilder(N);
                for (int c = 0; c < N; c++) {
                    if (col[r] == c) sb.append('Q');
                    else sb.append('.');
                }
                solution.add(sb.toString());
            }
            result.add(solution);
        }
        else {
            for (int c = 0; c < N; c++) {
                // 假设把Queen放在(row, c)的位置，并检查该位置的合法性
                col[row] = c;
                boolean ok = true;
                for (int r = 0; r < row; r++) {
                    if (col[r] == col[row]
                            || Math.abs(col[row] - col[r]) == row - r) {
                        ok = false;
                        break;
                    }
                }

                if (ok) {
                    process(row + 1, col);
                }
            }
        }
    }

    public static void main(String[] args) {
        _51_N_Queen a = new _51_N_Queen();
        System.out.println(a.solveNQueens(4));
    }
}
