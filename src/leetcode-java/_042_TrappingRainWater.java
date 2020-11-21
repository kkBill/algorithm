package LeetCode;

public class _042_TrappingRainWater {
    /**
     * 按列计算：
     * 外层循环每次指定中间一列k，内层循环分别找出k左右两侧的最大高度，然后判断能否蓄水并计算相应的面积
     * 时间复杂度：O(n^2) （能AC，约150ms）
     */
    /*
    public int trap(int[] height) {
        int total = 0;
        for (int k = 1; k < height.length - 1; k++) {
            int leftMax = 0, rightMax = 0;
            for (int i = 0; i < k; i++)
                leftMax = Math.max(leftMax, height[i]);
            for (int i = k + 1; i < height.length; i++)
                rightMax = Math.max(rightMax, height[i]);
            if(leftMax > height[k] && rightMax > height[k])
                total += (Math.min(leftMax, rightMax) - height[k]);
        }
        return total;
    }
     */
    public int trap(int[] height) {
        // 令leftMax[i]表示i左侧的最大高度，rightMax[i]表示i右侧的最大高度
        int[] leftMax = new int[height.length];
        int[] rightMax = new int[height.length];
        for (int i = 1; i < height.length; i++)
            leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
        for (int i = height.length - 2; i >= 0; i--)
            rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
        int total = 0;
        for (int i = 1; i < height.length - 1; i++) {
            if (leftMax[i] > height[i] && rightMax[i] > height[i]) {
                total += Math.min(leftMax[i], rightMax[i]) - height[i];
            }
        }
        return total;
    }

    public static void main(String[] args) {
        _042_TrappingRainWater obj = new _042_TrappingRainWater();
        int[] height = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(obj.trap(height));
    }
}
