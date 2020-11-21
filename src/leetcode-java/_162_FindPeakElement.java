package LeetCode;

public class _162_FindPeakElement {
    // 返回峰值的索引
    /*
// 写法1
public int findPeakElement(int[] nums) {
    if (nums.length == 0) return -1;
    int left = 0, right = nums.length;
    while (left < right) {
        int mid = (right - left) / 2 + left;
        if (mid + 1 < nums.length && nums[mid] < nums[mid + 1]) left = mid + 1;
        else right = mid;
    }
    return left;
}
*/

// 写法2
public int findPeakElement(int[] nums) {
    if (nums.length == 0) return -1;
    int left = 0, right = nums.length-1;
    while (left < right) {
        int mid = (right - left) / 2 + left;
        if (nums[mid] < nums[mid + 1]) left = mid + 1;
        else right = mid;
    }
    return left;
}

    public static void main(String[] args) {
        _162_FindPeakElement obj = new _162_FindPeakElement();
        int[] nums = new int[]{1, 2, 1, 3, 5, 6, 4};
        System.out.println(obj.findPeakElement(nums)); // 5

        nums = new int[]{2, 1};
        System.out.println(obj.findPeakElement(nums)); // 0

        nums = new int[]{1, 2};
        System.out.println(obj.findPeakElement(nums)); // 1

        // nums[-1] = nums[n] = -∞
        nums = new int[]{2};
        System.out.println(obj.findPeakElement(nums)); // 0
    }
}
