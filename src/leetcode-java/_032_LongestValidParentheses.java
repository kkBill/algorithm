package LeetCode;

public class _032_LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int maxCount = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')') {
                // 处理"...()"这样的情况
                if (s.charAt(i - 1) == '(') {
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                // 处理"...))"这样的情况
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = (i - dp[i - 1] - 2 >= 0 ? dp[i - dp[i - 1] - 2] : 0) + dp[i - 1] + 2;
                }
                maxCount = Math.max(maxCount, dp[i]);
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        _032_LongestValidParentheses obj = new _032_LongestValidParentheses();
        System.out.println(obj.longestValidParentheses("(()"));
        System.out.println(obj.longestValidParentheses(")()())"));
        System.out.println(obj.longestValidParentheses("())((())"));
        System.out.println(obj.longestValidParentheses("())())"));
    }
}
