package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _077_Combinations {
    public List<List<Integer>> combine(int n, int k) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        backtrack(1, n, k, path, result);
        return result;
    }

    private void backtrack(int start, int n, int k,
                           List<Integer> path, List<List<Integer>> result) {
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i <= n; i++) {
            path.add(i);
            backtrack(i + 1, n, k, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        _077_Combinations obj = new _077_Combinations();
        System.out.println(obj.combine(4, 2));
    }
}