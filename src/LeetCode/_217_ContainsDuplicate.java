package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class _217_ContainsDuplicate {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int val : nums){
            if(set.contains(val)) return true;
            else set.add(val);
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
