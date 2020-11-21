package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _006_ZigZagConversion {
    /*
    // 方法1：模拟
    public String convert(String s, int numRows) {
        if(numRows == 1 || numRows == s.length()) return s;

        int n = s.length();
        int unit = numRows + numRows - 2;
        int unitCount = n / unit;
        int remainder = n % unit;
        int numCols = unitCount * (numRows - 1);
        if(remainder != 0)
            numCols += remainder <= numRows ? 1 : remainder - numRows + 1;
        char[][] matrix = new char[numRows][numCols];

        char[] chars = s.toCharArray();
        int i = 0, row = 0, col = 0;
        while (i < n) {
            // 从上至下
            while (i < n && row < numRows) {
                matrix[row][col] = chars[i];
                row++;
                i++;
            }
            row -= 2;
            col++;
            // 从左下至右上
            while (i < n && row > 0) {
                matrix[row][col] = chars[i];
                row--;
                col++;
                i++;
            }
        }

        StringBuilder res = new StringBuilder();
        for (char[] charArray : matrix) {
            for (char c : charArray) {
                if(c != 0){
                    res.append(c);
                }
            }
        }

        return res.toString();
    }
    */

    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows == s.length()) return s;
        List<StringBuilder> matrix = new ArrayList<>();
        for(int i = 0; i < Math.min(numRows, s.length()); i++){
            matrix.add(new StringBuilder());
        }
        int flag = -1, r = 0;
        for (char c : s.toCharArray()) {
            matrix.get(r).append(c);
            if(r == 0 || r == numRows-1) flag = -flag; // 折返
            r += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : matrix){
            res.append(row);
        }
        return res.toString();
    }


    public static void main(String[] args) {
        _006_ZigZagConversion obj = new _006_ZigZagConversion();
        //char[][] matrix = obj.convert("LEETCODEISHIRING", 5);
        System.out.println(obj.convert("A", 4));

    }
}
