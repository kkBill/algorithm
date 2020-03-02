package LeetCode;

public class _137_SingleNumber2 {
    /**
     * 方法一：
     * 时间复杂度：O(32*n) = O(n)
     */
    public int singleNumber(int[] nums) {
        int bitmask = 1, singleNumber = 0;
        for(int i=1; i<= 32; i++){
            // 统计所有元素第 i 位上 ‘1’ 的个数
            int countOf1 = 0;
            for(int num : nums){
                if((num & bitmask) == bitmask) countOf1++;
            }
            if(countOf1 % 3 != 0) {
                singleNumber += bitmask;
            }
            bitmask = bitmask << 1;
        }
        return singleNumber;
    }

    public static void main(String[] args) {
        _137_SingleNumber2 obj = new _137_SingleNumber2();
        System.out.println(obj.singleNumber(new int[]{1, 2, 6, 1, 1, 2, 2, 3, 3, 3}));
    }
}
