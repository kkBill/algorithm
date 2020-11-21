package LeetCode;

public class _304_RangeSumQuery2D {
    /**
     * 预计算
     * 主要是编码技巧，一些细节的处理
     * 时间复杂度:O(1)； 空间复杂度: O(mn)
     */

    private int[][] dp;
    public _304_RangeSumQuery2D(int[][] matrix) {
        int rows = matrix.length;
        if(rows == 0) return;
        int cols = matrix[0].length;

        dp = new int[rows+1][cols+1];
        // 预处理 dp[][]
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                dp[i+1][j+1] = matrix[i][j] + dp[i][j+1] + dp[i+1][j] - dp[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        row1++; col1++; row2++; col2++; // 这里先 ++ 处理是为了下面语句更符合人的第一印象
        return dp[row2][col2] - dp[row1-1][col2] - dp[row2][col1-1] + dp[row1-1][col1-1];
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {3, 0, 1, 4, 2},
                {5, 6, 3, 2, 1},
                {1, 2, 0, 1, 5},
                {4, 1, 0, 1, 7},
                {1, 0, 3, 0, 5}};

        _304_RangeSumQuery2D obj = new _304_RangeSumQuery2D(matrix);
        System.out.println(obj.sumRegion(2, 1, 4, 3));
        System.out.println(obj.sumRegion(1, 1, 2, 2));
        System.out.println(obj.sumRegion(1, 2, 2, 4));
    }
}
