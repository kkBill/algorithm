package JianZhiOffer;

import java.util.ArrayList;

public class _19PrintMatrixInCircle {

    public static void main(String[] args) {
        //int [][] matrix={{1,2,3,4,5},{6,7,8,9,10}};
        int [][] matrix={{1,2,3},{4,5,6},{7,8,9}};
        //int [][] matrix={{1}};
        //int [][] matrix = {{1,2,3,4,5}};
        //int [][] matrix = {{1},{2},{3},{4},{5}};
        for(int i=0;i<matrix.length;i++){
            for(int j=0;j<matrix[i].length;j++){
                System.out.print(matrix[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println(printMatrix(matrix));

    }

    public static ArrayList<Integer> printMatrix(int [][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;
        int start = 0;
        ArrayList<Integer> result=new ArrayList<>();

        while(start*2 < rows && start*2 < cols){
            /***************/
            int endRows = rows-1-start;
            int endCols = cols-1-start;

            // from left to right
            for(int i=start; i<=endCols; i++){
                result.add(matrix[start][i]);
            }

            // from up to down
            for(int i=start+1; i<=endRows; i++){
                result.add(matrix[i][endCols]);
            }

            // from right to left
            if(start < endRows) { // 排除了只有一行的矩阵
                for (int i = endCols - 1; i >= start; i--) {
                    result.add(matrix[endRows][i]);
                }
            }

            // from down to up
            if(start < endCols) { // 排除了只有一列的矩阵
                for (int i = endRows - 1; i > start; i--) {
                    result.add(matrix[i][start]);
                }
            }
            /***************/

            start++;
        }//while

        return result;
    }
}