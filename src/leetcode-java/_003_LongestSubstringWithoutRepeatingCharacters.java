package LeetCode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class _003_LongestSubstringWithoutRepeatingCharacters {
    /**
     * 方法1：滑动窗口
     */
    /*
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int longestLen = 0, L = 0, R = 0;
        while (L < s.length()) {
            if (R < s.length() && !set.contains(s.charAt(R))) {
                set.add(s.charAt(R));
                R++;
            } else {
                longestLen = Math.max(longestLen, R - L);
                set.remove(s.charAt(L));
                L++;
            }
        }
        return longestLen;
    }
    */
    /*
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int longestLen = 0, L = 0, R = 0;
        while (L < s.length() && R < s.length()) {
            if (!map.containsKey(s.charAt(R))) {
                map.put(s.charAt(R), R);
                R++;
                longestLen = Math.max(longestLen, R - L);
            } else {
                int end = map.get(s.charAt(R));
                while (L <= end) {
                    map.remove(s.charAt(L++));
                }
            }
        }
        return longestLen;
    }
    */

    // 事实上并不需要remove操作，因为对map插入相同key的元素时会覆盖之前的值，
    // 也就相当于删除了之前的<key,value>
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int longestLen = 0;
        for (int left = 0, right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                left = Math.max(map.get(s.charAt(right)) + 1, left); //这里的max比较必须注意
            }
            longestLen = Math.max(longestLen, right - left + 1);
            map.put(s.charAt(right), right);
        }
        return longestLen;
    }

    public static void main(String[] args) {
        _003_LongestSubstringWithoutRepeatingCharacters
                obj = new _003_LongestSubstringWithoutRepeatingCharacters();
        System.out.println(obj.lengthOfLongestSubstring("abcbb"));
        System.out.println(obj.lengthOfLongestSubstring("bbbbb"));
        System.out.println(obj.lengthOfLongestSubstring("pwwkew"));
    }
}
