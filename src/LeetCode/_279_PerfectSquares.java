package LeetCode;

public class _279_PerfectSquares {
    public int numSquares(int n) {
        int[] dp = new int[n+1];

        for(int i=0;i<=n;i++){
            dp[i] = i; // 初始化
            for(int j=1;i-j*j>=0;j++){
                dp[i] = Math.min(dp[i], 1+dp[i-j*j]);
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _279_PerfectSquares obj = new _279_PerfectSquares();
        System.out.println(obj.numSquares(12));
        System.out.println(obj.numSquares(13));
        System.out.println(obj.numSquares(7));
        System.out.println(obj.numSquares(0));
    }
}
