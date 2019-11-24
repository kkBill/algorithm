package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _89_GrayCode {
    public List<Integer> grayCode(int n) {
        List<Integer> result = new ArrayList<>();
        if (n < 0) return result;
        result.add(0);
        for (int i = 0; i < n; i++) {
            int add = 1 << i;
            for (int j = result.size() - 1; j >= 0; j--) {
                result.add(result.get(j) + add);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        _89_GrayCode obj = new _89_GrayCode();
        System.out.println(obj.grayCode(3));
    }
}
