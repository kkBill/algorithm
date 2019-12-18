package Other.dp;

public class Fibonacci {
    /*
    public static int F(int n) {
        if(n==0 || n==1) return 1;
        int[] dp = new int[n+1];
        dp[0] = dp[1] = 1;
        for(int i=2; i<=n; i++){
            dp[i] = dp[i-2] + dp[i-1];
        }
        return dp[n];
    }
     */
    public static int F(int n) {
        if(n==0 || n==1) return 1;
        int pre1 = 1, pre2 = 1, cur = 0;
        for(int i=2;i<=n;i++){
            cur = pre2 + pre1;
            pre2 = pre1;
            pre1 = cur;
        }
        return cur;
    }


    public static void main(String[] args) {
        System.out.println(F(5));
    }
}
