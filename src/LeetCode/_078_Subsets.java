package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _078_Subsets {
    public List<List<Integer>> subsets(int[] nums) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        backtrack(0, nums, path, result);
        return result;
    }

    private void backtrack(int start, int[] nums, List<Integer> path, List<List<Integer>> result) {
        // 注意这里的边界条件，这里不能有return;
        // 事实上这个判断条件根本不需要。因为无论如何这个if总是成立的！
        // if (path.size() <= nums.length)
        result.add(new ArrayList<>(path));
        for (int i = start; i < nums.length; i++) {
            path.add(nums[i]);
            backtrack(i + 1, nums, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        _078_Subsets obj = new _078_Subsets();
        System.out.println(obj.subsets(new int[]{1, 2, 3}));
    }
}
