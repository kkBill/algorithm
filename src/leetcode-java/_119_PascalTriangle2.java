package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _119_PascalTriangle2 {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> preRow = new ArrayList<>();
        if(rowIndex < 0) return preRow;

        preRow.add(1);
        for(int r = 1; r <= rowIndex; r++){
            List<Integer> currRow = new ArrayList<>();
            currRow.add(1);
            for(int i = 0; i < preRow.size() - 1; i++){
                currRow.add(preRow.get(i) + preRow.get(i+1));
            }
            currRow.add(1);
            preRow = currRow;
        }
        return preRow;
    }

    public static void main(String[] args) {
        _119_PascalTriangle2 obj = new _119_PascalTriangle2();
        System.out.println(obj.getRow(0));
        System.out.println(obj.getRow(1));
        System.out.println(obj.getRow(2));
        System.out.println(obj.getRow(3));
    }
}
