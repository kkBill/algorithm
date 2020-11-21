package LeetCode;

import java.util.Arrays;

public class _260_SingleNumber3 {
    public int[] singleNumber(int[] nums) {
        int mask = 0;
        for(int num : nums) mask ^= num;
        int diff = mask & (-mask);
        int x = 0;
        for(int num : nums) {
            if((num & diff) != 0) x ^= num;
        }
        return new int[]{x, x^mask};
    }
    public static void main(String[] args) {
        _260_SingleNumber3 obj = new _260_SingleNumber3();
        System.out.println(Arrays.toString(obj.singleNumber(new int[]{1, 2, 1, 3, 2, 5})));
    }
}
