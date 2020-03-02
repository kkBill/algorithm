package LeetCode;

public class _074_Searcha2DMatrix {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        int left = 0, right = m*n;
        while(left < right) {
            int mid = (right - left) / 2 + left;
            int row = mid / n;
            int col = mid % n;
            if(target == matrix[row][col]) return true;
            else if(target > matrix[row][col]) left = mid+1;
            else right = mid;
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
