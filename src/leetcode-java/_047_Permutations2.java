package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _047_Permutations2 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        Arrays.sort(nums);
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                // 难点在于搞清楚 !used[i-1] 的含义
                if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1])
                    continue;
                path.add(nums[i]);
                used[i] = true;
                backtrack(nums, used, path, result);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        _047_Permutations2 obj = new _047_Permutations2();
        System.out.println(obj.permuteUnique(new int[]{1, 1, 2}));
    }
}
