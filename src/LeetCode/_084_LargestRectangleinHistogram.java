package LeetCode;

import java.util.Stack;

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
     * 耗时 600+ ms，可通过
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
     * 参考：（https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-1-7/）
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     *
     * 耗时约 4 ms，性能提升巨大！
     */
    /*
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
    */

    /**
     * 借助栈的方法~ 也是精妙无比！
     * 算法思想同上一个方法并无本质差别，只不过这里借助了"栈"这个数据结构实现，值得借鉴学习
     * 时间复杂度：O(n) 只遍历一次heights[] 数组，每个元素只入栈/出栈一次
     * 空间复杂度：O(n) 栈空间
     */
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> s = new Stack<>();
        int maxArea = 0;
        int curr = 0;
        while (curr < heights.length){
            // 如果此时栈为空，或者当前 curr 的柱高大于栈顶元素对应的柱高，则入栈
            if(s.isEmpty() || heights[curr] >= heights[s.peek()]){
                s.push(curr);
                curr++;
            }
            // 否则将栈顶元素出栈，并计算以栈顶元素对应的柱体的面积
            else{
                int p = s.pop();
                int left = s.isEmpty() ? -1 : s.peek();
                maxArea = Math.max(maxArea, (curr-left-1)*heights[p]);
            }
        }
        // 最后出现逐层递增(不再下降)的情况
        // 此时 curr 停留在 heights.length
        while (!s.isEmpty()){
            int p = s.pop();
            int left = s.isEmpty() ? -1 : s.peek();
            maxArea = Math.max(maxArea, (curr-left-1)*heights[p]);
        }
        return maxArea;
    }

    public static void main(String[] args) {
        _084_LargestRectangleinHistogram obj = new _084_LargestRectangleinHistogram();
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(obj.largestRectangleArea(heights));
    }
}
