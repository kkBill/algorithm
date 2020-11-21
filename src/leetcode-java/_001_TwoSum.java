package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _001_TwoSum {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (map.containsKey(temp)) {
                ans[0] = map.get(temp);
                ans[1] = i;
                return ans;
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("no valid answer.");
    }

    public static void main(String[] args) {
        _001_TwoSum obj = new _001_TwoSum();
        int[] nums = new int[]{2, 7, 2, 15};
        int[] ans = obj.twoSum(nums, 4);
        System.out.println(ans[0] + " "+ ans[1]);


//        Map<Integer, Integer> map = new HashMap<>();
//        map.put(3,1);
//        map.put(3,2); // 覆盖前一个<key,value>
//        System.out.println(map.get(3));
    }
}
