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


    public static void main(String[] args) {
        _084_LargestRectangleinHistogram obj = new _084_LargestRectangleinHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(obj.largestRectangleArea(heights));
    }
}
