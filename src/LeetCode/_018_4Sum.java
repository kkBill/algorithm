package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _018_4Sum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums.length < 4) return result;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 3; i++) {
            // 去重2
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 剪枝1：计算当前的最小值，如果目标值 < 最小值，说明接下来必然不存在解
            if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
                break;
            // 剪枝2：计算当前的最大值，如果目标值 > 最大值，说明接下来以当前这个i遍历必然不存在解
            // 与前一个剪枝不同的是，随着i的后移，该最大值会增大，仍然存在解，因此这里用的是continue而不是break
            // 只是跳过当前这个i罢了。需要模拟理解~
            if(nums[i] + nums[nums.length-1] + nums[nums.length-2] + nums[nums.length-3] < target)
                continue;

            for (int j = i + 1; j < nums.length - 2; j++) {
                // 去重1
                if (j > i + 1 && nums[j] == nums[j - 1]) continue;

                // 剪枝3: 计算当前的最小值，如果目标值 < 最小值，说明接下来必然不存在解
                if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target)
                    break;
                // 剪枝4：
                if (nums[i] + nums[j] + nums[nums.length-1] + nums[nums.length-2] < target)
                    continue;

                int left = j + 1, right = nums.length - 1;
                //System.out.println("[" + i + "," + j + "," + left + "," + right + "]");

                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum < target) left++;
                    else if (sum > target) right--;
                    else {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        while (left < right && nums[left] == nums[left + 1]) left++; // 去重
                        while (left < right && nums[right] == nums[right - 1]) right--; // 去重
                        left++;
                        right--;
                    }
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _018_4Sum obj = new _018_4Sum();
//        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        int target = 0;
//        System.out.println(obj.fourSum(nums, target));
//
//        nums = new int[]{-2, -2, -2, 0, 0, 0, 2, 2, 2};
//        System.out.println(obj.fourSum(nums, target));
//
//        nums = new int[]{0, 0, 0, 0};
//        System.out.println(obj.fourSum(nums, target));

        int[] nums = new int[]{1, 0, -1, 0, -2, 2};
        System.out.println(obj.fourSum(nums, target));
    }
}
