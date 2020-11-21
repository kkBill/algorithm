package LeetCode;

public class _338_CountingBits {
    /**
     * 本题可在"191.number of 1 bits"的基础上思考
     * 从191题中我们已经知道，n&(n-1) 可以移除数字 n 对应的二进制序列的最右边的'1'
     * 换句话说，n 对应的二进制序列中'1'的个数总是比 n&(n-1) 对应的二进制序列中'1'的个数多一个！
     * 且 n&(n-1) 总是满足 n&(n-1) ≤ n-1
     * 我们可以利用这个规律来构造状态转移方程
     * <p>
     * 令dp[n] 表示数字 n 对应的二进制序列中含有的'1'的个数，边界 dp[0] = 0
     * 可以得到状态转移方程为：
     * dp[n] = dp[n&(n-1)] + 1
     */

    public int[] countBits(int num) {
        int[] dp = new int[num + 1];
        for (int i = 1; i <= num; i++) {
            dp[i] = dp[i&(i-1)] + 1;
        }
        return dp;
    }

    public static void main(String[] args) {
        _338_CountingBits obj = new _338_CountingBits();
        int[] res = obj.countBits(2);
        for (int cnt : res) {
            System.out.print(cnt + " ");
        }
    }
}
