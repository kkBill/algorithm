package LeetCode;

public class _343_IntegerBreak {
    /**
     * 暴力递归
     * 分析：
     *
     * 令F(n)表示拆分n可获得的最大乘积，那么就有：
     * F(n) = max{ 1*F(n-1), 2*F(n-2),..., (n-2)*F(2) }
     * 即 F(n) = max{i*F(n-i) | i=1,2,...,n-2}
     * 其中边界为 F(2) = 1
     *
     * 但是按着这样的思路，会发现有问题！
     * F(3) = max{1*F(2)} = 1 （对应3 = 1 + 1 + 1 ）; 而正确的情况应该是 F(3) = 1*2 = 2 （对应3 = 1 + 2）
     * 这到底是为什么呢？
     * 因为 n 可以分解成至少两个数，或者是多个数，而F(n) = max{i*F(n-i)}事实上只考虑了分解成多个数的情况，而没有考虑分解成两个数的情况
     * 因此，正确的递归式应该是：
     * F(n) = max{i*(n-i), i*F(n-i)} (i=1,2,..,n-2)
     */
    /*
    public int integerBreak(int n) {
        if (n == 2) return 1;
        int result = -1;
        for (int i = 1; i <= n - 2; i++) {
            result  = Math.max(result, Math.max(i*(n-i), i*integerBreak(n-i)));
        }
        return result;
    }
    */

    /**
     * 递归 + 备忘录
     * 已经不会超时了~
     */
    /*
    private int[] memo;
    public int integerBreak(int n) {
        memo = new int[n+1];
        return helper(n);
    }

    private int helper(int n){
        if(n == 2) return 1;
        if(memo[n] != 0) return memo[n];

        int result = -1;
        for (int i = 1; i <= n - 2; i++) {
            result  = Math.max(result, Math.max(i*(n-i), i*helper(n-i)));
        }
        memo[n] = result;
        return result;
    }
    */

    /**
     * 动态规划
     * 递归是自顶向下，动态规划则自底向上递推
     *
     * 令dp[n] 表示拆分n可获得的最大乘积，根据上面的分析，有
     * dp[n] = max{i*(n-i), i*dp[n-i]} (i=1,2,..,n-2)
     * 已知边界为 dp[2] = 1，从左向右递推
     *
     */
    public int integerBreak(int n) {
        int[] dp = new int[n+1];
        dp[2] = 1;
        for(int i=3;i<=n;i++){
            for(int j=1;j<=i-2;j++){
                dp[i] = Math.max(dp[i], Math.max(j*(i-j), j*dp[i-j]));
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _343_IntegerBreak obj = new _343_IntegerBreak();
        System.out.println(obj.integerBreak(2));
        System.out.println(obj.integerBreak(3));
        System.out.println(obj.integerBreak(4));
        System.out.println(obj.integerBreak(5));
        System.out.println(obj.integerBreak(10));
    }
}
