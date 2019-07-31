package JianZhiOffer;

public class _7Fibonacci {
    // f(n) = 0                n = 0
    // f(n) = 1                n = 1
    // f(n) = f(n-1) + f(n-2)  n > 1
    public int Fibonacci(int n) {
        int[] res = new int[]{0,1};
        if(n<2) return res[n];

        int pre_i_1 = 1, pre_i_2 = 0;
        int ans = 0;
        for(int i=2;i<=n;i++){
            ans = pre_i_1 + pre_i_2;
            pre_i_2 = pre_i_1;
            pre_i_1 = ans;
        }
        return ans;
    }
}