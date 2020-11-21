package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _131_PalindromePartitioning {

    public List<List<String>> partition(String s) {
        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        helper(0, s, path, result);
        return result;
    }

    private void helper(int start, String s,
                        List<String> path, List<List<String>> result) {
        if (start == s.length()) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                path.add(s.substring(start, i + 1));
                helper(i + 1, s, path, result);
                path.remove(path.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String s, int start, int end) {
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        _131_PalindromePartitioning obj = new _131_PalindromePartitioning();
        System.out.println(obj.partition("aaaaa"));
    }
}
