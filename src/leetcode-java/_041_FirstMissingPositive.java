package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class _041_FirstMissingPositive {
    public int firstMissingPositive(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int num : nums) {
            if(num <= 0) continue; // 因为我们只关心正整数
            set.add(num);
        }
        int val = 1;
        while (set.contains(val)) {
            val++;
        }
        return val;
    }

    public static void main(String[] args) {
        _041_FirstMissingPositive obj = new _041_FirstMissingPositive();
        System.out.println(obj.firstMissingPositive(new int[]{1,2,0}));
        System.out.println(obj.firstMissingPositive(new int[]{3,4,-1,1}));
        System.out.println(obj.firstMissingPositive(new int[]{7,8,9,12}));
    }
}
