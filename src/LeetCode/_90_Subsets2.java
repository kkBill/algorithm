package LeetCode;

import java.util.*;

public class _90_Subsets2 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(0, nums, path, result);
        return result;
    }
    private void backtrack(int start, int[] nums,
                           List<Integer> path, List<List<Integer>> result) {
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            if (i == start || nums[i] != nums[i - 1]) {
                path.add(nums[i]);
                backtrack(i + 1, nums, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        _90_Subsets2 obj = new _90_Subsets2();
        System.out.println(obj.subsetsWithDup(new int[]{1, 2, 2}));
    }
}
