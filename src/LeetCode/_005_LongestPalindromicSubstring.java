package LeetCode;

public class _005_LongestPalindromicSubstring {

    /**
     * 时间复杂度: O(n^2); 空间复杂度: O(n^2)
     *
     */
    public String longestPalindrome(String s) {
        if(s.length() == 0) return "";

        boolean[][] dp = new boolean[s.length()][s.length()];
        int begin = 0, end = 0, maxLen = 0;
        for (int sublen = 1; sublen <= s.length(); sublen++) {
            for (int i = 0; i < s.length(); i++) {
                int j = i + sublen - 1;
                // 超出边界
                if (j >= s.length()) break;

                // 状态转移
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                    continue;
                }
                // 关键
                dp[i][j] = (sublen == 1) || (sublen == 2) || dp[i + 1][j - 1];
                // 更新最大回文子串
                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    begin = i;
                    end = j;
                }
            }
        }
        return s.substring(begin, end + 1);
    }

    public static void main(String[] args) {
        _005_LongestPalindromicSubstring obj = new _005_LongestPalindromicSubstring();
        System.out.println(obj.longestPalindrome("babad"));
        System.out.println(obj.longestPalindrome("cbbd"));
        System.out.println(obj.longestPalindrome(""));
    }
}
