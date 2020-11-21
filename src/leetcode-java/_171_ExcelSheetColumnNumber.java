package LeetCode;

import java.util.Arrays;
import java.util.Collections;

public class _171_ExcelSheetColumnNumber {
    public int titleToNumber(String s) {
        int ans = 0, time = 1;
        for(int i=s.length()-1; i>=0;i--){
            ans += (s.charAt(i) - 'A' + 1) * time;
            time *= 26;
        }
        return ans;
    }
    public static void main(String[] args) {
        _171_ExcelSheetColumnNumber obj = new _171_ExcelSheetColumnNumber();
        System.out.println(obj.titleToNumber("A"));
        System.out.println(obj.titleToNumber("Z"));
        System.out.println(obj.titleToNumber("AZ"));
        System.out.println(obj.titleToNumber("AB"));
        System.out.println(obj.titleToNumber("GXF"));
    }
}
