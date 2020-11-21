package LeetCode;

public class _091_DecodeWays {
    /*
    // 递归
    // 会超时！
    public int numDecodings(String s) {
        return helper(s, 0);
    }

    private int helper(String s, int start) {
        if (s.length() == start) return 1;
        if (s.charAt(start) == '0') return 0;

        int count1 = helper(s, start + 1);
        int count2 = 0;
        if (start < s.length() - 1) {
            String prestr = s.substring(start, start + 2);
            if (Integer.parseInt(prestr) <= 26) count2 = helper(s, start + 2);
        }
        return count1 + count2;
    }
     */

    /*
    // 递归 + 备忘录
    private int[] dp;

    public int numDecodings(String s) {
        dp = new int[s.length()];
        return helper(s, 0);
    }

    private int helper(String s, int start) {
        if (s.length() == start) return 1;
        if (s.charAt(start) == '0') return 0;
        if (dp[start] != 0) return dp[start];

        int count1 = helper(s, start + 1);
        int count2 = 0;
        if (start < s.length() - 1) {
            String prestr = s.substring(start, start + 2);
            if (Integer.parseInt(prestr) <= 26) count2 = helper(s, start + 2);
        }
        dp[start] = count1 + count2;
        return dp[start];
    }
     */


    /**
     * 动态规划
     * 时间复杂度: O(n); 空间复杂度: O(n)
     */
    /*
    public int numDecodings(String s) {
        int[] dp = new int[s.length() + 1];
        // 初始化边界条件
        dp[s.length()] = 1;
        if (s.charAt(s.length() - 1) != '0')
            dp[s.length() - 1] = 1;
        // 更新状态
        for (int i = s.length() - 2; i >= 0; i--) {
            // 如果当前起始位置的数字是'0'，则dp[i]=0，直接进入下一次循环
            if (s.charAt(i) == '0') {
                dp[i] = 0; // 不写也没关系，因为初始化dp[]数组时默认为0，这么写是为了方便理解
                continue;
            }
            dp[i] = dp[i + 1];
            String substr = s.substring(i, i + 2);
            if (Integer.parseInt(substr) <= 26)
                dp[i] += dp[i + 2];
        }
        return dp[0];
    }
    */

    /**
     * 动态规划 + 压缩空间
     * 时间复杂度: O(n); 空间复杂度: O(1)
     */
    public int numDecodings(String s) {
        // 初始化边界条件
        int post = 1, curr = 0;
        if(s.charAt(s.length() - 1) != '0')
            curr = 1;

        // 更新状态
        for (int i = s.length() - 2; i >= 0; i--) {
            if (s.charAt(i) == '0') {
                post = curr;
                curr = 0;
                continue;
            }

            int cnt1 = curr, cnt2 = 0;
            String substr = s.substring(i, i + 2);
            if (Integer.parseInt(substr) <= 26) {
                cnt2 = post;
            }
            post = curr;
            curr = cnt1 + cnt2;
        }
        return curr;
    }

    public static void main(String[] args) {
        _091_DecodeWays obj = new _091_DecodeWays();
        System.out.println(obj.numDecodings("12"));
        System.out.println(obj.numDecodings("226"));
        System.out.println(obj.numDecodings("0226"));
        System.out.println(obj.numDecodings("0"));
        System.out.println(obj.numDecodings("1"));
        System.out.println(obj.numDecodings("100"));
    }
}
