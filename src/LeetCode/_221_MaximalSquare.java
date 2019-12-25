package LeetCode;

public class _221_MaximalSquare {
    /**
     * 暴力遍历
     * 时间复杂度: O((mn)^2) 当整个矩阵都是1的时候 （其实不知道怎么推算出来的这个复杂度）
     * 空间复杂度: O(1) 没有用过额外的辅助空间
     */
    /*
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;

        int maxLen = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                // 如果当前位置是'1'，就以此为起点向右、向下扩散
                if (matrix[i][j] == '1') {
                    int len = 1;
                    boolean flag = true;
                    while (i + len < rows && j + len < cols && flag) {
                        // 向右扩散
                        for(int k = j; k <= j + len; k++){
                            if(matrix[i+len][k] == '0'){
                                flag = false;
                                break;
                            }
                        }
                        // 向下扩散
                        for(int k = i; k <= i + len; k++){
                            if(matrix[k][j+len] == '0'){
                                flag = false;
                                break;
                            }
                        }
                        if(flag) len++;
                    } // end of while

                    maxLen = Math.max(maxLen, len);
                }
            }
        }
        return maxLen*maxLen;
    }
    */

    /**
     * 动态规划，精妙！
     * 时间复杂度：O(mn)
     * 空间复杂度：O(mn)
     */
    /*
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        int cols = rows > 0 ? matrix[0].length : 0;

        int[][] dp = new int[rows+1][cols+1];
        int maxLen = 0;
        // 填充 dp[][] 数组
        for(int i=1; i<=rows;i++){
            for(int j=1;j<=cols;j++){
                if(matrix[i-1][j-1] == '1'){
                    dp[i][j] = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j], dp[i][j-1])) + 1;
                    maxLen = Math.max(maxLen, dp[i][j]);
                }
            }
        }
        return maxLen*maxLen;
    }

    */

    /**
     * 动态规划 + 压缩空间 ==> 这个场景的空间压缩还是第一次遇见！细节问题！
     * 时间复杂度：O(mn)
     * 空间复杂度：O(n)
     */
    public int maximalSquare(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;

        int maxLen = 0;
        int[] dp = new int[cols + 1];
        for (int i = 1; i <= rows; i++) {
            int left = 0;
            for (int j = 1; j <= cols; j++) {
                int tmp = dp[j]; // 这里要注意，因为dp[j]在这一轮的更新中会被覆盖，所以要先取出来
                if (matrix[i - 1][j - 1] == '1') {
                    //System.out.printf("left = %d, dp[%d] = %d, dp[%d] = %d\n", left, j, dp[j], j - 1, dp[j - 1]);
                    dp[j] = Math.min(left, Math.min(dp[j], dp[j - 1])) + 1;
                    maxLen = Math.max(maxLen, dp[j]);
                } else {
                    dp[j] = 0;
                }
                left = tmp;
            }
        }
        return maxLen * maxLen;
    }

    public static void main(String[] args) {
        _221_MaximalSquare obj = new _221_MaximalSquare();

        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        char[][] matrix2 = {
                {'1', '0', '1', '0'},
                {'1', '0', '1', '1'},
                {'1', '0', '1', '1'},
                {'1', '1', '1', '1'}};

        System.out.println(obj.maximalSquare(matrix2));
    }
}
