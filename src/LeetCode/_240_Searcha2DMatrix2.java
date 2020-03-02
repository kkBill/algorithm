package LeetCode;

public class _240_Searcha2DMatrix2 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        // 初始化起点为矩阵的左下角
        int row = m - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) row--;
            else col++;
        }
        return false;
    }

    public static void main(String[] args) {
        _240_Searcha2DMatrix2 obj = new _240_Searcha2DMatrix2();
        int[][] matrix = new int[][]{
                {1, 4, 7, 11, 15},
                {2, 5, 8, 12, 19},
                {3, 6, 9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}};
        System.out.println(obj.searchMatrix(matrix,17));
        System.out.println(obj.searchMatrix(matrix,5));
        System.out.println(obj.searchMatrix(matrix,20));
    }
}
