package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class _228_SummaryRanges {
    /*
    // 写法1：能通过，但是不好
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        if(nums.length == 0) return res;

        int start = nums[0], end = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if((long)nums[i] - (long)nums[i-1] > 1){ // 2147483647 - (-2147483647) 发生了溢出
                end = nums[i-1];
                if(start == end) res.add(String.valueOf(start));
                else res.add(start + "->" + end);
                start = nums[i];
                end = nums[i];
            }else {
                end = nums[i];
            }
        }
        if(start == end) res.add(String.valueOf(start));
        else res.add(start + "->" + end);
        return res;
    }
    */

    // 写法2
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if(n == 0) return res;

        int left = 0, right = 0;
        while (right < n) {
            while (right < n - 1 && nums[right] + 1 == nums[right+1]) {
                right++;
            }
            if(left == right){
                res.add(String.valueOf(nums[left]));
            }else {
                res.add(nums[left] + "->" + nums[right]);
            }
            right++;
            left = right;
        }
        return res;
    }

    public static void main(String[] args) {
        _228_SummaryRanges obj = new _228_SummaryRanges();
        // 特殊的case
        int[] nums = new int[]{-2147483648,-2147483647,2147483647};
        System.out.println(obj.summaryRanges(nums));
    }
}
