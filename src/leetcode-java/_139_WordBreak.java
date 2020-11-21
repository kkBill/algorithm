package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class _139_WordBreak {
    /**
     * 暴力递归
     *
     */
    /*
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        return helper(s, wordSet, 0);
    }

    private boolean helper(String s, Set<String> set, int start) {
        if (start == s.length()) {
            return true;
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (set.contains(s.substring(start, end)) && helper(s, set, end)) {
                return true;
            }
        }
        return false;
    }
    */

    /**
     * 递归 + 备忘录 （or 记忆化搜索）
     *
     */
    /*
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        Boolean[] memo = new Boolean[s.length()];
        return helper(s, wordSet, memo, 0);
    }

    private boolean helper(String s, Set<String> set, Boolean[] memo, int start) {
        if (start == s.length()) {
            return true;
        }
        if(memo[start] != null) {
            return memo[start];
        }
        for (int end = start + 1; end <= s.length(); end++) {
            if (set.contains(s.substring(start, end)) && helper(s, set, memo, end)) {
                memo[start] = true;
                return true;
            }
        }
        memo[start] = false;
        return false;
    }
    */

    /**
     * 动态规划（有人提到这一题是完全背包问题？我还不理解~~）
     *
     * 暂时不会~ 周末先把文档写好吧~
     *
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return true;
    }

    public static void main(String[] args) {
        _139_WordBreak obj = new _139_WordBreak();
        String s = "leetcode";
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");

        System.out.println(obj.wordBreak(s, wordDict));
    }
}
