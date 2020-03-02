package LeetCode;

import java.util.Arrays;

public class _016_3SumClosest {
    public int threeSumClosest(int[] nums, int target) {
        if (nums.length < 3) return 0;
        int closestTarget = 0, maxDiff = Integer.MAX_VALUE;
        Arrays.sort(nums);// 预处理：排序
        // 固定位置i，双指针滑动left和right
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = target - sum;
                if (Math.abs(diff) < maxDiff) {
                    maxDiff = Math.abs(diff);
                    closestTarget = sum;
                }
                if (sum < target) left++;
                else if (sum > target) right--;
                else return sum;
            }
        }
        return closestTarget;
    }

    public static void main(String[] args) {
        _016_3SumClosest obj = new _016_3SumClosest();
        int[] nums = new int[]{-1, 2, 1, -4};
        System.out.println(obj.threeSumClosest(nums, 1));
    }
}
