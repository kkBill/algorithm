package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _040_CombinationSum2 {
    private List<List<Integer>> result = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
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

        // 剪枝1：target - candidates[i] >= 0
        for (int i = start; i < candidates.length && target - candidates[i] >= 0; i++) {
            // 剪枝2：
            if (i > start && candidates[i] == candidates[i - 1])
                continue;
            path.add(candidates[i]);
            helper(i + 1, candidates, target - candidates[i], path);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        _040_CombinationSum2 obj = new _040_CombinationSum2();
        int[] candidates = {10, 1, 2, 7, 6, 1, 5};
        int target = 8;
        System.out.println(obj.combinationSum2(candidates, target));
    }
}