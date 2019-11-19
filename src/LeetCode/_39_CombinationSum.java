package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _39_CombinationSum {

    private List<List<Integer>> result = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates.length == 0) return result;
        Arrays.sort(candidates);
        helper(0, candidates, target, new ArrayList<>());
        return result;
    }

    private void helper(int start, int[] candidates, int target, List<Integer> path) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        // target - candidates[i] >= 0 剪枝
        for (int i = start; i < candidates.length && target - candidates[i] >= 0; i++) {
            path.add(candidates[i]);
            helper(i, candidates, target - candidates[i], path);
            path.remove(path.size() - 1); // backtrack
        }
    }

    public static void main(String[] args) {
        _39_CombinationSum obj = new _39_CombinationSum();
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        System.out.println(obj.combinationSum(candidates, target));
    }
}
