package LeetCode;

public class _242_ValidAnagram {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        _242_ValidAnagram obj = new _242_ValidAnagram();
        System.out.println(obj.isAnagram("abc", "cba"));
        System.out.println(obj.isAnagram("rat", "car"));
    }
}
