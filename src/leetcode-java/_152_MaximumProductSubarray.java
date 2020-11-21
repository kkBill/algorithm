package LeetCode;

public class _152_MaximumProductSubarray {
    /**
     * 令 imax 表示以 nums[i] 为结尾的连续子序列的最大乘积
     * 令 imin 表示以 nums[i] 为结尾的连续子序列的最小乘积
     * 初始化 imax 和 imin 为 1
     * 当 nums[i+1] 为负数时，想要获得最大乘积，就应该与 imin 相乘(考虑到这一点是本题关键，这也是为什么要维护 imin 的原因)
     * 反之，如果 nums[i+1] 为整数时，就应该与 imax 相乘；
     * 因此，在更新 imax 和 imin 之前，需要判断一下当前值的正负情况
     *
     * 时间复杂度: O(n) 一次遍历
     * 空间复杂度: O(1)
     */
    public int maxProduct(int[] nums) {
        int result = Integer.MIN_VALUE;
        int imax = 1, imin = 1, tmp;
        for (int i = 0; i < nums.length; i++) {
            // 如果当前值为负数，则交换 imax 和 imin
            // 这是为了后续方便计算
            if (nums[i] < 0) {
                tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);

            result = Math.max(result, imax);
        }
        return result;
    }

    public static void main(String[] args) {
        _152_MaximumProductSubarray obj = new _152_MaximumProductSubarray();
        int[] nums = {2, 3, -2, 4};
        System.out.println(obj.maxProduct(nums));

        int[] nums2 = {-2, 0, -1};
        System.out.println(obj.maxProduct(nums2));

        int[] nums3 = {-2, 3, -4};
        System.out.println(obj.maxProduct(nums3));
    }
}
