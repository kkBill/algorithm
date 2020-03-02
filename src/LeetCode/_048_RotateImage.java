package LeetCode;

public class _048_RotateImage {
    /**
     * 时间复杂度：O(n^2)，这已经是最优解
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 矩阵转置
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 逐行反转
        for(int i = 0; i < n; i++) {
            for(int left = 0, right = n-1; left < right; left++, right--){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
            }
        }
    }

    public static void main(String[] args) {
        _048_RotateImage obj = new _048_RotateImage();

    }
}
