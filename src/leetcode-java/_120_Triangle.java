package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _120_Triangle {
    /**
     * 动态规划, 自顶向下
     * 时间复杂度: O(n^2); 空间复杂度: O(n^2) n是triangle底边的长度
     */
    /*
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if (n == 0) return 0;
        if (n == 1) return triangle.get(0).get(0);

        int[][] dp = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[i][j] = triangle.get(i).get(j);
            }
        }

        for (int level = n - 2; level >= 0; level--) {
            for (int j = 0; j <= level; j++) {
                dp[level][j] += Math.min(dp[level + 1][j], dp[level + 1][j + 1]);
            }
        }

        return dp[0][0];
    }
     */

    /**
     * 动态规划, 自底向上
     * 时间复杂度: O(n^2); 空间复杂度: O(n)
     */
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        int[] dp = new int[n];
        // 初始化
        for (int i = 0; i < n; i++)
            dp[i] = triangle.get(n - 1).get(i);
        // 状态转移
        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = triangle.get(i).get(j) + Math.min(dp[j], dp[j + 1]);
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        _120_Triangle obj = new _120_Triangle();
        List<List<Integer>> triangle = new ArrayList<>();
//        int[][] data = {
//                {2},
//                {3, 4},
//                {6, 5, 7},
//                {4, 1, 8, 3}};

//        int[][] data = {{2}};

        int[][] data = {
                {-1},
                {2, 3},
                {1, -1, -3}};

        for (int[] datum : data) {
            ArrayList<Integer> line = new ArrayList<>(datum.length);
            for (int i : datum) {
                line.add(i);
            }
            triangle.add(line);
        }

        System.out.println(obj.minimumTotal(triangle));

    }
}
