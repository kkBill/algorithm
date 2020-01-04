package LeetCode;

public class _010_RegularExpressionMatching {

    // 1. 简单的匹配
    /*
    public boolean isMatch(String s, String p) {
        if (s.length() != p.length()) return false;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != p.charAt(i)) return false;
        }
        return true;
    }
    */

    // 2. 简单的匹配，递归实现
    /*
    public boolean isMatch(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && s.charAt(0) == p.charAt(0);
        return firstMatch && isMatch(s.substring(1), p.substring(1));
    }

     */

    // 3. 匹配"."
//    public boolean isMatch(String s, String p) {
//        if(p.isEmpty())
//            return s.isEmpty();
//        boolean firstMatch = !s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
//        return firstMatch && isMatch(s.substring(1), p.substring(1));
//    }

    // 4. 匹配"."和"*"
    // 时间复杂度和空间复杂度 分析起来比较复杂
    /*
    public boolean isMatch(String s, String p) {
        if (p.isEmpty())
            return s.isEmpty();
        boolean firstMatch = !s.isEmpty() && (p.charAt(0) == s.charAt(0) || p.charAt(0) == '.');
        if (p.length() >= 2 && p.charAt(1) == '*') {
            // isMatch(s, p.substring(2)) --> 表示 '*' 匹配0个在其之前的那个元素，比如s="aab"和p="c*a*b"这种情况
            // (firstMatch && isMatch(s.substring(1), p)) --> 表示 '*' 匹配1个在其之前的那个元素
            return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
        } else {
            return firstMatch && isMatch(s.substring(1), p.substring(1));
        }
    }
    */

    // 解法4 是一种暴力递归的解法
    // 这里加上"备忘录"以减少重复计算
    /*
    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length() + 1][p.length() + 1];
        return helper(s, 0, p, 0, memo);
    }

    private boolean helper(String s, int si, String p, int pi, Boolean[][] memo) {
        if (pi == p.length())
            return si == s.length();

        if (memo[si][pi] != null) return memo[si][pi];

        boolean firstMatch = si < s.length() && (p.charAt(pi) == s.charAt(si) || p.charAt(pi) == '.');

        if (pi <= p.length() - 2 && p.charAt(pi + 1) == '*') {
            memo[si][pi] = helper(s, si, p, pi + 2, memo) || (firstMatch && helper(s, si + 1, p, pi, memo));
            return memo[si][pi];
        } else {
            memo[si][pi] = firstMatch && helper(s, si + 1, p, pi + 1, memo);
            return memo[si][pi];
        }
    }

    */

    /**
     * 动态规划实现
     *
     */
    public boolean isMatch(String s, String p) {
        return false;
    }

    public static void main(String[] args) {
        _010_RegularExpressionMatching obj = new _010_RegularExpressionMatching();
        System.out.println(obj.isMatch("ab", ".*"));
        System.out.println(obj.isMatch("aab", "c*a*b"));
        System.out.println(obj.isMatch("aa", "a*"));
        System.out.println(obj.isMatch("aa", "aa"));
        System.out.println(obj.isMatch("aa", "a."));
        System.out.println(obj.isMatch("mississippi", "mis*is*p*."));
    }
}
