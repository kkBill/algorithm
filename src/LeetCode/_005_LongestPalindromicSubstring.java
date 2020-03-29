package LeetCode;

public class _005_LongestPalindromicSubstring {

    /**
     * 动态规划
     * 时间复杂度: O(n^2); 空间复杂度: O(n^2)
     */
    public String longestPalindrome(String s) {
        int n = s.length();
        if(n == 0) return "";

        char[] chars = s.toCharArray();
        boolean[][] dp = new boolean[n][n];
        int begin = 0, end = 0;

        // 初始化
        for(int i = 0; i < n; i++) {
            dp[i][i] = true;
            if(i < n-1) {
                dp[i][i+1] = chars[i] == chars[i+1];
                if(dp[i][i+1] && end - begin < 1) {
                    begin = i;
                    end = i+1;
                }
            }
        }
        // 状态转移
        for(int len = 2; len < n; len++) {
            for(int i = 0; i + len < n; i++) {
                int j = i + len;
                dp[i][j] = dp[i+1][j-1] && chars[i] == chars[j];
                if(dp[i][j] && j - i > end - begin) {
                    begin = i;
                    end = j;
                }
            }
        }

        return s.substring(begin, end+1);
    }

    public static void main(String[] args) {
        _005_LongestPalindromicSubstring obj = new _005_LongestPalindromicSubstring();
        System.out.println(obj.longestPalindrome("babad"));
        System.out.println(obj.longestPalindrome("cbbd"));
        System.out.println(obj.longestPalindrome(""));
    }
}
