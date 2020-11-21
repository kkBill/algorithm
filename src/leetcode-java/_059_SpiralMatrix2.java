package LeetCode;

public class _059_SpiralMatrix2 {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1, up = 0, down = n - 1, left = 0, right = n - 1;
        n = n * n;
        while (count <= n) {
            // up: left -> right
            for (int i = left; i <= right; i++)
                matrix[up][i] = count++;
            up++;

            // right: up -> down
            for (int i = up; i <= down; i++)
                matrix[i][right] = count++;
            right--;

            // down: right -> left
            for (int i = right; i >= left; i--)
                matrix[down][i] = count++;
            down--;

            // left: down -> up
            for (int i = down; i >= up; i--)
                matrix[i][left] = count++;
            left++;
        }
        return matrix;
    }

    public static void main(String[] args) {
        _059_SpiralMatrix2 obj = new _059_SpiralMatrix2();
        int[][] matrix = obj.generateMatrix(4);
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}
