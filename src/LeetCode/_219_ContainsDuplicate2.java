package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class _219_ContainsDuplicate2 {
    /*
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // <value, index>
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
    */

    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }

    public static void main(String[] args) {

    }
}
