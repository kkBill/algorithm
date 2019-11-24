package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _46_Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // used[i]==true if nums[i] is used
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == nums.length) {
            result.add(new ArrayList<>(path));
            return;
        }

        // enumerate possible number for current position
        for (int i = 0; i < nums.length; i++) {
            // if nums[i] is not used, add nums[i] to path
            if (!used[i]) {
                path.add(nums[i]);
                used[i] = true;
                backtrack(nums, used, path, result);
                used[i] = false;
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        _46_Permutations obj = new _46_Permutations();
        int[] nums = {1, 2, 3};
        System.out.println(obj.permute(nums));
    }
}