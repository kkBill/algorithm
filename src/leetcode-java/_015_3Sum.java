package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _015_3Sum {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 3) return result;
        // 预处理：排序
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 2; i++) {
            // 优化：若nums[i]>0，那么在i之后的数字必定大于0，故不可能存在3数之和等于0的情况~
            if (nums[i] > 0) break;
            // 去重
            if (i > 0 && nums[i - 1] == nums[i]) continue;

            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum < 0) left++;
                else if (sum > 0) right--;
                else {
                    //if(result.contains(...)) // 也可以在这里使用去重~
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++; // 去重
                    while (left < right && nums[right] == nums[right - 1]) right--; // 去重
                    left++;
                    right--;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _015_3Sum obj = new _015_3Sum();
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(obj.threeSum(nums));

        nums = new int[]{-2, 0, 0, 2, 2};
        System.out.println(obj.threeSum(nums));

        nums = new int[]{0, 0, 0, 0};
        System.out.println(obj.threeSum(nums));
    }
}
