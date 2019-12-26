package LeetCode;

public class _084_LargestRectangleinHistogram {
    /**
     * 暴力法
     * 分析：
     * 我们需要O(n^2)的时间来枚举所有可能的组合
     * 对于每个可能的组合[begin, end]，我们需要O(n)时间来遍历来判断最值
     *
     * 时间复杂度：O(n^3) （会超时！）
     * 空间复杂度：O(1)
     *
     */
    /*
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int begin = 0; begin < heights.length; begin++) {
            for (int end = begin; end < heights.length; end++) {

                int minHeight = heights[begin];
                for (int i = begin; i <= end; i++) {
                    minHeight = Math.min(minHeight, heights[i]);
                    maxArea = Math.max(maxArea, minHeight * (i - begin + 1));
                }
            }
        }
        return maxArea;
    }
    */

    /**
     * 优化的暴力法
     *
     * 这个暴力才是正常的思路啊~
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(1)
     *
     * 耗时约 681 ms，可通过
     */
    /*
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        for (int begin = 0; begin < heights.length; begin++) {
            int minHeight = heights[begin];
            for (int end = begin; end < heights.length; end++) {
                minHeight = Math.min(minHeight, heights[end]);
                maxArea = Math.max(maxArea, minHeight*(end-begin+1));
            }
        }
        return maxArea;
    }
    */

    /**
     * 精妙无比！
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 耗时约 4 ms，性能提升巨大！
     */
    public int largestRectangleArea(int[] heights) {
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
        _084_LargestRectangleinHistogram obj = new _084_LargestRectangleinHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(obj.largestRectangleArea(heights));
    }
}
