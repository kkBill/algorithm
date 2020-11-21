package LeetCode;

public class _073_SetMatrixZeroes {
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(1) (只用到了2个布尔型变量)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;

        // step 1：检查第一行和第一列中是否存在0
        boolean existZeroAtFirstRow = false, existZeroAtFirstCol = false;
        for(int col = 0; col < n; col++){ //O(n)
            if(matrix[0][col] == 0){
                existZeroAtFirstRow = true;
                break;
            }
        }
        for(int row = 0; row < m; row++){ //O(m)
            if(matrix[row][0] == 0){
                existZeroAtFirstCol = true;
                break;
            }
        }

        // step 2：标记除第一行和第一列之外的其他数据是否存在0
        for(int row = 1; row < m; row++){ //O(m*n)
            for(int col = 1; col < n; col++){
                if(matrix[row][col] == 0){
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }

        // step 3：处理
        for(int row = 1; row < m; row++){// O(m*n)
            for(int col = 1; col < n; col++){
                if(matrix[0][col] == 0 || matrix[row][0] == 0){
                    matrix[row][col] = 0;
                }
            }
        }

        // step 4：处理第一行和第一列
        if(existZeroAtFirstRow){
            for(int col = 0; col < n; col++){
                matrix[0][col] = 0;
            }
        }
        if(existZeroAtFirstCol){
            for(int row = 0; row < m; row++){
                matrix[row][0] = 0;
            }
        }
    }

    public static void main(String[] args) {

    }
}
