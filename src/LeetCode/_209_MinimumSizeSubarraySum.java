package LeetCode;

public class _209_MinimumSizeSubarraySum {
public int minSubArrayLen(int s, int[] nums) {
    int sum = 0, minLen = Integer.MAX_VALUE, left = 0;
    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];
        while (sum >= s) {
            minLen = Math.min(minLen, right - left + 1);
            sum -= nums[left];
            left++;
        }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}

    public static void main(String[] args) {
        _209_MinimumSizeSubarraySum obj = new _209_MinimumSizeSubarraySum();
        int[] nums = new int[]{1, 1, 1};
        System.out.println(obj.minSubArrayLen(2, nums));
    }
}
