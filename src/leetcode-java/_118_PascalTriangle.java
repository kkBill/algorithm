package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class _118_PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows < 1) return triangle;

        triangle.add(Arrays.asList(1));
        for (int r = 1; r < numRows; r++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 0; i < triangle.get(r - 1).size() - 1; i++) {
                row.add(triangle.get(r - 1).get(i) + triangle.get(r - 1).get(i + 1));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }

    public static void main(String[] args) {
        _118_PascalTriangle obj = new _118_PascalTriangle();
        System.out.println(obj.generate(1));
    }
}
