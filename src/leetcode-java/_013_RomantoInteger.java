package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _013_RomantoInteger {

    public int romanToInt(String s) {
        // 预处理
        int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < roman.length; i++) {
            map.put(roman[i], values[i]);
        }
        //
        int num = 0, begin = 0, end = 1;
        while (begin < s.length()) {
            while (end <= s.length() && map.containsKey(s.substring(begin, end))) end++;
            end--;
            num += map.get(s.substring(begin,end));
            begin = end;
            end++;
        }
        return num;
    }

    public static void main(String[] args) {
        _013_RomantoInteger obj = new _013_RomantoInteger();
        System.out.println(obj.romanToInt("III"));
        System.out.println(obj.romanToInt("IV"));
        System.out.println(obj.romanToInt("IX"));
        System.out.println(obj.romanToInt("LVIII"));
        System.out.println(obj.romanToInt("MCMXCIV"));
    }
}
