package LeetCode;

public class _190_ReverseBits {
    // you need treat n as an unsigned value
    // 方法1
    public int reverseBits(int n) {
        int res = 0, bitCount = 0;
        while (bitCount < 32) {
            res <<= 1;
            res |= (n & 1);
            n >>>= 1; // >>> 为无符号右移
            bitCount++;
        }
        return res;
    }
    public static void main(String[] args) {
        _190_ReverseBits obj = new _190_ReverseBits();
        System.out.println(obj.reverseBits(43261596));
        System.out.println(obj.reverseBits(-3));

    }
}
