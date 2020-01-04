package LeetCode;

public class _044_WildcardMatching {
    /**
     * 动态规划，从后向前
     * <p>
     * 令 dp[i][j] 表示s[i...] 与 p[j...]是否匹配，因此最终的答案就是 dp[0][0]
     * <p>
     * 令 slen=s.length(); plen=s.length()
     * 那么显然有 dp[slen][plen] = true
     * <p>
     * 令i为s的下标，j为p的下标
     * case 1: i = slen, j < plen 且 p[j...]非空，此时仅当p[j...]全是‘*’时才匹配
     * dp[slen][j] = p[j] == '*' && dp[slen][j+1]
     * case 2: i < slen, s[i]不为空且 j = plen时，此时都不匹配
     * dp[i][plen] = false
     * case 3: 当 i < slen 且 j < plen时，
     * 当 s[i] == p[j] || p[j] == ‘?’ 时，dp[i][j] = dp[i+1][j+1]
     * 当 p[j] == ‘*’ 时，
     * 如果p[j]匹配s中的0个字符，这就相当于p[j]这个位置不存在，匹配结果由p[j+1]决定，即 dp[i][j] = dp[i][j+1]
     * 如果p[j]匹配s中的1个字符，即p[j]匹配s[i]，那么匹配结果等价于p[j]匹配s[i+1], 即 dp[i][j] = dp[i+1][j]
     */

    public boolean isMatch(String s, String p) {
        int slen = s.length(), plen = p.length();
        boolean[][] dp = new boolean[slen + 1][plen + 1];
        // 初始化
        dp[slen][plen] = true;
        for (int j = plen - 1; j >= 0; j--) {
            dp[slen][j] = (p.charAt(j) == '*' && dp[slen][j + 1]);
        }
        for (int i = 0; i < slen; i++) {
            dp[i][plen] = false;
        }

        // 状态转移
        for (int i = slen - 1; i >= 0; i--) {
            for (int j = plen - 1; j >= 0; j--) {
                if (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?') {
                    dp[i][j] = dp[i + 1][j + 1];
                }
                if (p.charAt(j) == '*') {
                    dp[i][j] = dp[i][j+1] || dp[i+1][j];
                }
            }
        }

        // debug
        for(int i=0;i<=slen;i++){
            for(int j=0;j<=plen;j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }

        return dp[0][0];
    }

    public static void main(String[] args) {
        _044_WildcardMatching obj = new _044_WildcardMatching();
//        System.out.println(obj.isMatch("aa", "a")); // false
//        System.out.println(obj.isMatch("aa", "*")); // true
//        System.out.println(obj.isMatch("cb", "?a")); // false
        System.out.println(obj.isMatch("adceb", "*a*b")); // true
//        System.out.println(obj.isMatch("acdcb", "a*c?b")); // false
    }
}
