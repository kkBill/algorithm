package LeetCode;

public class _191_Numberof1Bits {
    /**
     * 位运算的规律题，没什么练习价值，之前在《剑指Offer》中做过这题了
     */
    // you need to treat n as an unsigned value
    public int hammingWeight(int n) {
        int cnt = 0;
        while(n != 0){
            cnt++;
            n = n & (n-1);
        }
        return cnt;
    }

    public static void main(String[] args) {
        _191_Numberof1Bits obj = new _191_Numberof1Bits();
        System.out.println(obj.hammingWeight(15));
    }
}
