package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _054_SpiralMatrix {
    /*
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length;
        if(rows == 0) return res;
        int cols = matrix[0].length;
        int n = rows * cols;

        int count = 0; // 统计已经访问的元素个数
        int up = 0, down = rows-1, left = 0, right = cols-1; // 边界

        while (count < n){
            // up: left -> right
            for(int i=left; i<=right;i++) {
                res.add(matrix[up][i]);
                count++;
            }
            if(count == n) break;
            up++;

            // right: up -> down
            for(int i=up; i<=down;i++) {
                res.add(matrix[i][right]);
                count++;
            }
            if(count == n) break;
            right--;

            // down: right -> left
            for(int i=right; i>=left; i--){
                res.add(matrix[down][i]);
                count++;
            }
            if(count == n) break;
            down--;

            // left: down -> up
            for(int i=down; i>=up;i--){
                res.add(matrix[i][left]);
                count++;
            }
            left++;
        }
        return res;
    }
    */

    // 写法2：更简洁
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length;
        if (rows == 0) return res;
        int cols = matrix[0].length;
        int up = 0, down = rows-1, left = 0, right = cols-1; // 边界

        while (true) {
            // up: left -> right
            for(int i=left; i<=right;i++) {
                res.add(matrix[up][i]);
            }
            up++;
            if(up > down) break;

            // right: up -> down
            for(int i=up; i<=down;i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if(right < left) break;

            // down: right -> left
            for(int i=right; i>=left; i--){
                res.add(matrix[down][i]);
            }
            down--;
            if(up > down) break;

            // left: down -> up
            for(int i=down; i>=up;i--){
                res.add(matrix[i][left]);
            }
            left++;
            if(right < left) break;
        }
        return res;
    }


    public static void main(String[] args) {
        _054_SpiralMatrix obj = new _054_SpiralMatrix();
        int[][] matrix = new int[][]{{1, 2, 3, 4},
                                     {5, 6, 7, 8},
                                     {9, 10, 11, 12}};
//        int[][] matrix = new int[][]{{1,2}};
        System.out.println(obj.spiralOrder(matrix));
    }
}
