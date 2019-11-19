package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _46_Permutations {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        helper(0, nums, result);
        return result;
    }

    private void helper(int start, int[] nums, List<List<Integer>> result) {
        if (start == nums.length - 1) {
            List<Integer> perm = new ArrayList<>();
            for (Integer e : nums) {
                perm.add(e);
            }
            result.add(perm);
            return;
        }
        for (int i = start; i < nums.length; i++) {
            swap(start, i, nums);
            helper(start + 1, nums, result);
            swap(start, i, nums);
        }
    }

    private void swap(int i, int j, int[] nums) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    public static void main(String[] args) {
        _46_Permutations obj = new _46_Permutations();
        int[] nums = {1, 2, 3};
        System.out.println(obj.permute(nums));
    }
}