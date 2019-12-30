package LeetCode;

import java.util.Arrays;

public class _132_PalindromePartitioning2 {
    /**
     * 暴力递归
     */
    /*
    private int minCut = Integer.MAX_VALUE;

    public int minCut(String s) {
        // 先用二维数组dp[i][j]确定s[i...j]是否为回文子串
        // 时间复杂度：O(n^2)
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int len = 1; len <= s.length(); len++) {
            for (int begin = 0; begin <= s.length() - len; begin++) {
                int end = begin + len - 1;
                if (len == 1) dp[begin][end] = true;
                else if (len == 2) dp[begin][end] = s.charAt(begin) == s.charAt(end);
                else  dp[begin][end] = (s.charAt(begin) == s.charAt(end)) && dp[begin + 1][end - 1];
            }
        }

        return helper(s, 0, dp);
    }

    private int helper(String s, int start, boolean[][] dp) {
        if (dp[start][s.length() - 1])
            return 0;
        for (int i = start; i < s.length(); i++) {
            if (dp[start][i]) {
                minCut = Math.min(minCut, 1 + helper(s, i + 1, dp));
            }
        }
        return minCut;
    }
    */

    /**
     * 递归+备忘录
     */
    /*
    private int minCut = Integer.MAX_VALUE;
    private int[] memo;
    public int minCut(String s) {
        // 先用二维数组dp[i][j]确定s[i...j]是否为回文子串
        // 时间复杂度：O(n^2)
        boolean[][] dp = new boolean[s.length()][s.length()];
        memo = new int[s.length()];
        Arrays.fill(memo, -1);

        for (int len = 1; len <= s.length(); len++) {
            for (int begin = 0; begin <= s.length() - len; begin++) {
                int end = begin + len - 1;
                if (len == 1) dp[begin][end] = true;
                else if (len == 2) dp[begin][end] = s.charAt(begin) == s.charAt(end);
                else  dp[begin][end] = (s.charAt(begin) == s.charAt(end)) && dp[begin + 1][end - 1];
            }
        }

        return helper(s, 0, dp);
    }

    private int helper(String s, int start, boolean[][] dp) {
        if (dp[start][s.length() - 1])
            return 0;

        if(memo[start] != -1)
            return memo[start];

        for (int i = start; i < s.length(); i++) {
            if (dp[start][i]) {
                minCut = Math.min(minCut, 1 + helper(s, i + 1, dp));
            }
        }

        memo[start] = minCut; // 记录中间结果
        return minCut;
    }
    */

    /**
     * 动态规划
     * 把“递归+备忘录”法改造成动态规划
     *
     * 令 isPalindrome[i][j] 表示 s[i..j]是否为回文子串
     * 令 dp[i] 表示切割 s[0..i] 为回文子串的最小次数~
     *
     * 时间复杂度：O(n^2)
     * 空间复杂度：O(n^2)
     */
    public int minCut(String s) {
        boolean[][] isPalindrome = new boolean[s.length()][s.length()];
        int[] dp = new int[s.length()];
        // 初始化dp[]数组
        for (int i = 0; i < s.length(); i++) {
            dp[i] = i;
            isPalindrome[i][i] = true; //注意此处的初始化，否则下面在判断 isPalindrome[begin + 1][end - 1] 时会出错
        }

        for (int end = 0; end < s.length(); end++) {
            for (int begin = 0; begin <= end; begin++) {
                // 首先判断 s[begin...end]是否是回文子串
                if (end - begin < 2) { // 长度小于3的子串
                    isPalindrome[begin][end] = s.charAt(begin) == s.charAt(end);
                } else {
                    isPalindrome[begin][end] = (s.charAt(begin) == s.charAt(end)) && isPalindrome[begin + 1][end - 1];
                }

                if (isPalindrome[begin][end]) {
                    // 如果s[0...end]本身就是回文子串，说明不需要切割，直接置dp[end]为0
                    if (begin == 0) {
                        dp[end] = 0;
                        break;
                        // 如果s[begin...end]是回文子串（但begin ≠ 0），那么相当于在 s[begin-1]和s[begin]之间要切一刀
                        // 即 dp[end] = 1 + dp[begin-1]
                    } else {
                        dp[end] = Math.min(dp[end], 1 + dp[begin - 1]);
                    }
                }
            }
        }
        return dp[s.length() - 1];
    }

    /**
     * 动态规划 v2
     * 时间复杂度：O()
     * 空间复杂度：O(n) !!
     */

    public static void main(String[] args) {
        _132_PalindromePartitioning2 obj = new _132_PalindromePartitioning2();
        //System.out.println(obj.minCut("aab"));
        System.out.println(obj.minCut("aaa"));
        //System.out.println(obj.minCut("abc"));
    }
}
