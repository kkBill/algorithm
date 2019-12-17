package Other.dp;

import java.util.Arrays;

public class DigitTower {
    //private static int[][] tower = {{9}, {12, 15}, {10, 6, 8}, {2, 18, 9, 5}, {19, 7, 10, 4, 16}};
    private static int[][] tower = {{5}, {8, 3}, {12, 7, 16}, {4, 10, 11, 6}, {9, 5, 3, 9, 4}};
    static int n = tower.length;
    static int[][] dp = new int[n][n];


    private static int process(int i, int j) {
        if (i == n - 1) return tower[i][j];
        if (dp[i][j] != -1) return dp[i][j];
        else {
            dp[i][j] = Math.max(process(i + 1,j), process(i + 1,j + 1) )+ tower[i][j];
            return dp[i][j];
        }
    }

    public static void main(String[] args) {
        // 初始化
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }
        System.out.println("answer: " + process(0,0));


//        int[][] dp = new int[n][n];
//
//        // 初始化边界
//        for (int j = 0; j < n; j++) {
//            dp[n - 1][j] = tower[n - 1][j];
//        }
//
//        // 从倒数第2层开始自底向上，递推求解
//        for (int i = n - 2; i >= 0; i--) {
//            for (int j = 0; j < i + 1; j++) {
//                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + tower[i][j];
//            }
//        }
//
//        System.out.println("answer: " + dp[0][0]);
    }
}


