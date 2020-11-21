package LeetCode;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class _220_ContainsDuplicate3 {
    /*
    // 暴力法，时间复杂度O(n*min(n,k))
    // 超时
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length && j <= i + k; j++){
                // 注意溢出的情况
                if(Math.abs((long)nums[i] - (long)nums[j]) <= t) return true;
            }
        }
        return false;
    }
    */

    /*
    //
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++){ // O(n)
            // 找到大于等于nums[i]的最小值
            Integer successor = set.ceiling(nums[i]); // O(log(k))
            if(successor != null && (long)successor - (long)nums[i] <= t) return true;
            // 找到小于等于nums[i]的最大值
            Integer predecessor = set.floor(nums[i]); // O(log(k))
            if(predecessor != null && (long)nums[i] - (long)predecessor <= t) return true;

            set.add(nums[i]); // O(log(k))
            if(set.size() > k){
                set.remove(nums[i-k]); // O(log(k))
            }
        }
        return false;
    }
    */

    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t < 0) return false;
        // <index of bucket that contains num, num>
        // 保证map中只存放k个元素，模拟滑动窗口
        Map<Long, Long> map = new HashMap<>();
        long width = t + 1;

        for(int i = 0; i < nums.length; i++){
            long index = getID(nums[i], width);
            if(map.containsKey(index)) return true;
            if(map.containsKey(index - 1) && (long)nums[i] - map.get(index - 1) <= t) return true;
            if(map.containsKey(index + 1) && map.get(index + 1) - (long)nums[i] <= t) return true;

            map.put(index, (long)nums[i]);
            if(map.size() > k){
                map.remove(getID(nums[i-k], width)); // 注意这里不要写错了
            }
        }
        return false;
    }

    // 获取数值num应该存放的桶对应的桶编号
    private long getID(long num, long width) {
        return num >= 0 ? num / width : (num+1)/width - 1;
    }

    public static void main(String[] args) {
        _220_ContainsDuplicate3 obj = new _220_ContainsDuplicate3();
        int[] nums = new int[]{2,0,-2,2};
        int k = 2, t = 1;
        System.out.println(obj.containsNearbyAlmostDuplicate(nums,k,t));

        nums = new int[]{-1,2147483647};
        System.out.println(obj.containsNearbyAlmostDuplicate(nums,1,2147483647));

    }
}
