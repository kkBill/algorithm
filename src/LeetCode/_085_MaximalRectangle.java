package LeetCode;

public class _085_MaximalRectangle {

    /**
     * 方法一：复用#84题的方法
     * 时间复杂度：O(nm)
     * 空间复杂度：O(m)
     */
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0) return 0;
        int cols = matrix[0].length;

        int maxArea = 0;
        int[] heights = new int[cols];
        for (int i = 0; i < rows; i++) {
            // 每遍历一层，更新高度
            for (int j = 0; j < cols; j++) {
                if(matrix[i][j] == '1') {
                    heights[j] += 1;
                }else{
                    heights[j] = 0;
                }
            }
            // 调用 #84题 的方法，计算第[0...i]层中'1'构成的最大面积
            maxArea = Math.max(maxArea, largestRectangleArea(heights));
        }
        return maxArea;
    }

    private int largestRectangleArea(int[] heights) {
        if (heights.length == 0) return 0;

        int[] leftLessMin = new int[heights.length];
        leftLessMin[0] = -1;
        for (int i = 1; i < heights.length; i++) {
            int left = i - 1;
            while (left >= 0 && heights[left] >= heights[i]) {
                //left--;
                left = leftLessMin[left];
            }
            leftLessMin[i] = left;
        }

        int[] rightLessMin = new int[heights.length];
        rightLessMin[heights.length - 1] = heights.length;
        for (int i = heights.length - 2; i >= 0; i--) {
            int right = i + 1;
            while (right < heights.length && heights[right] >= heights[i]) {
                //right++;
                right = rightLessMin[right];
            }
            rightLessMin[i] = right;
        }

        // 计算面积
        int maxArea = 0;
        for (int i = 0; i < heights.length; i++) {
            maxArea = Math.max(maxArea, (rightLessMin[i] - leftLessMin[i] - 1) * heights[i]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        _085_MaximalRectangle obj = new _085_MaximalRectangle();
        char[][] matrix = {
                {'1', '0', '1', '0', '0'},
                {'1', '0', '1', '1', '1'},
                {'1', '1', '1', '1', '1'},
                {'1', '0', '0', '1', '0'}};
        System.out.println(obj.maximalRectangle(matrix));
    }
}
