package LeetCode;

public class _201_BitwiseANDofNumbersRange {
    /*
    // 方法1（暴力，从小到达逐个相与，会超时，并且还要特殊处理溢出）
    public int rangeBitwiseAnd(int m, int n) {
        int res = m;
        for (int i = m; i <= n; i++) {
            res &= i;
            if (res == 0) break;
            //if (res == 0 || i == Integer.MAX_VALUE) break;
        }
        return res;
    }
    */

    /*
    // 方法2（暴力，从大到小逐个相与，会超时，但是避免了溢出）
    // 既然可以从m到n逐个相与，为何不从n到m相与呢？
    // 这样就不用考虑在++的过程中出现溢出了~
    public int rangeBitwiseAnd(int m, int n) {
        int res = n;
        for (int i = n; i >= m; i--) {
            res &= i;
            if (res == 0) break;
        }
        return res;
    }
    */

    /*
    public int rangeBitwiseAnd(int m, int n) {
        int bitCount = 0;
        while (m != n){
            m >>= 1;
            n >>= 1;
            bitCount++;
        }
        return m << bitCount;
    }

    */

    // 这个做法和上一个做法的原理相似
    public int rangeBitwiseAnd(int m, int n) {
        while (n > m){
            n &= (n-1);
        }
        return n;
    }

    public static void main(String[] args) {
        _201_BitwiseANDofNumbersRange obj = new _201_BitwiseANDofNumbersRange();
        System.out.println(obj.rangeBitwiseAnd(5, 7));
        System.out.println(obj.rangeBitwiseAnd(2147483646, 2147483647));

        for (int i = 24; i <= 31; i++) {
            System.out.println(Integer.toBinaryString(i));
        }
    }
}