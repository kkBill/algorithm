package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _076_MinimumWindowSubstring {

    /*

    public String minWindow(String s, String t) {

      if (s.length() == 0 || t.length() == 0) {
          return "";
      }

      // Dictionary which keeps a count of all the unique characters in t.
      Map<Character, Integer> dictT = new HashMap<Character, Integer>();
      for (int i = 0; i < t.length(); i++) {
          int count = dictT.getOrDefault(t.charAt(i), 0);
          dictT.put(t.charAt(i), count + 1);
      }

      // Number of unique characters in t, which need to be present in the desired window.
      int required = dictT.size();

      // Left and Right pointer
      int l = 0, r = 0;

      // formed is used to keep track of how many unique characters in t
      // are present in the current window in its desired frequency.
      // e.g. if t is "AABC" then the window must have two A's, one B and one C.
      // Thus formed would be = 3 when all these conditions are met.
      int formed = 0;

      // Dictionary which keeps a count of all the unique characters in the current window.
      Map<Character, Integer> windowCounts = new HashMap<Character, Integer>();

      // ans list of the form (window length, left, right)
      int[] ans = {-1, 0, 0};

      while (r < s.length()) {
          // Add one character from the right to the window
          char c = s.charAt(r);
          int count = windowCounts.getOrDefault(c, 0);
          windowCounts.put(c, count + 1);

          // If the frequency of the current character added equals to the
          // desired count in t then increment the formed count by 1.
          if (dictT.containsKey(c) && windowCounts.get(c).intValue() == dictT.get(c).intValue()) {
              formed++;
          }

          // Try and co***act the window till the point where it ceases to be 'desirable'.
          while (l <= r && formed == required) {
              c = s.charAt(l);
              // Save the smallest window until now.
              if (ans[0] == -1 || r - l + 1 < ans[0]) {
                  ans[0] = r - l + 1;
                  ans[1] = l;
                  ans[2] = r;
              }

              // The character at the position pointed by the
              // `Left` pointer is no longer a part of the window.
              windowCounts.put(c, windowCounts.get(c) - 1);
              if (dictT.containsKey(c) && windowCounts.get(c).intValue() < dictT.get(c).intValue()) {
                  formed--;
              }

              // Move the left pointer ahead, this would help to look for a new window.
              l++;
          }

          // Keep expanding the window once we are done co***acting.
          r++;
      }

      return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
  }
  */


    public String minWindow(String s, String t) {
        Map<Character, Integer> dict = new HashMap<>();
        for (char c : t.toCharArray()) {
            int count = dict.getOrDefault(c, 0);
            dict.put(c, count + 1);
        }
        int requiredCount = dict.size();

        // 记录滑动窗口内部的数据
        Map<Character, Integer> window = new HashMap<>();
        int desiredCount = 0;

        // 存放最终结果(window.length, left, right)
        int[] ans = new int[]{-1, 0, 0};
        int left = 0, right = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            int count = window.getOrDefault(c, 0);
            window.put(c, count + 1);

            // 如果当前遍历的字符在子串中出现，并且窗口内该字符的个数与子串中该字符对应的个数一致
            if (dict.containsKey(c) && window.get(c).intValue() == dict.get(c).intValue()) {
                desiredCount++;
            }

            // 找到可以覆盖子串的可行窗口，更新结果
            while (left <= right && desiredCount == requiredCount) {
                c = s.charAt(left);
                if (ans[0] == -1 || right - left + 1 < ans[0]) {
                    ans[0] = right - left + 1;
                    ans[1] = left;
                    ans[2] = right;
                }

                // 更新left，缩小窗口大小，判断是否依旧满足要求
                window.put(c, window.get(c) - 1);
                if (dict.containsKey(c) && window.get(c).intValue() < dict.get(c).intValue()) {
                    desiredCount--;
                }
                left++;
            }
            right++;
        }

        return ans[0] == -1 ? "" : s.substring(ans[1], ans[2] + 1);
    }

    public static void main(String[] args) {
        _076_MinimumWindowSubstring obj = new _076_MinimumWindowSubstring();
        System.out.println(obj.minWindow("ADOBECODEBANC", "ABC"));
        System.out.println(obj.minWindow("A", "A"));
        System.out.println(obj.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
    }
}
