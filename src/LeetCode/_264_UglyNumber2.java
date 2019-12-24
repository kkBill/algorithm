package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _264_UglyNumber2 {
    /**
     * 超时
     */
    /*
    private Map<Integer, Boolean> map = new HashMap<>();
    private boolean isUgly(int num) {
        if(num == 1) return true;
        if(map.containsKey(num)) return map.get(num);

        if(num % 2 == 0) return isUgly(num/2);
        else if(num % 3 == 0) return isUgly(num/3);
        else if(num % 5 == 0) return isUgly(num/5);
        else return false;
    }

    public int nthUglyNumber(int n) {
        for(int i=1; ;i++){
            if(isUgly(i)){
                map.put(i,true);
                n--;
                if(n == 0) return i;
            }else{
                map.put(i,false);
            }
        }
    }
    */

    /**
     * 三指针法 （凭我自己是不可能想出来的，技巧性比较强，卒~~~）
     * 时间复杂度: O(n) (一次遍历); 空间复杂度: O(n)
     *
     * 令 dp[n] 表示第 n 个丑数
     */
    public int nthUglyNumber(int n) {
        int[] dp = new int[n+1];
        dp[1] = 1;
        int i2=1, i3=1, i5=1;
        for(int i=2;i<=n;i++){
            int a = dp[i2]*2, b = dp[i3]*3, c = dp[i5]*5;
            int tmp = Math.min(a, Math.min(b, c));
            if(tmp == a) i2++;
            if(tmp == b) i3++;
            if(tmp == c) i5++;
            dp[i] = tmp;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _264_UglyNumber2 obj = new _264_UglyNumber2();
        System.out.println(obj.nthUglyNumber(10));
        System.out.println(obj.nthUglyNumber(11));
        System.out.println(obj.nthUglyNumber(1690));
    }
}
