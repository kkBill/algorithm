package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _216_CombinationSum3 {
    public List<List<Integer>> combinationSum3(int k, int n) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> result = new ArrayList<>();
        dfs(k,n,1,path,result);
        return result;
    }

    private void dfs(int k, int target, int start, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            if (k == 0) {
                result.add(new ArrayList<>(path));
            }
            return;
        }
        for (int i = start; i <= 9 && target - i >= 0; i++) {
            path.add(i);
            dfs(k - 1, target - i, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        _216_CombinationSum3 obj = new _216_CombinationSum3();
        System.out.println(obj.combinationSum3(3,9));
    }
}
