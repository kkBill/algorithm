package LeetCode;

public class _034_FindFirstandLastPositionofElementinSortedArray {
    public int[] searchRange(int[] nums, int target) {
        // find first position
        int firstPos = findFirst(nums, target);
        if (firstPos == -1) return new int[]{-1, -1};

        // find last position
        int lastPos = findLast(nums, target);
        return new int[]{firstPos, lastPos};
    }

    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) {
                if (mid == left || (mid > left && nums[mid - 1] != target)) return mid;
                else right = mid - 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length-1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                if (mid == right || (mid < right && nums[mid + 1] != target)) return mid;
                else left = mid + 1;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        _034_FindFirstandLastPositionofElementinSortedArray
                obj = new _034_FindFirstandLastPositionofElementinSortedArray();
        int[] nums = new int[]{1};
        int[] ans = obj.searchRange(nums, 1);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
