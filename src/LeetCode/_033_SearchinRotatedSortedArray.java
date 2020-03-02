package LeetCode;

public class _033_SearchinRotatedSortedArray {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) return mid;

            // 因为数组不存在重复元素，
            // 所以只存在nums[mid] > nums[nums.length-1]或nums[mid] < nums[nums.length-1] 两种情况
            // mid 位于前半个有序数组
            if (nums[mid] > nums[nums.length - 1]) {
                if (target >= nums[left] && target < nums[mid]) { // 目标值在 [left, mid] 区间内
                    right = mid - 1;
                } else { // 目标值在 [mid+1, right]
                    left = mid + 1;
                }
            }
            // mid 位于后半个有序数组
            else {
                if (target > nums[mid] && target <= nums[right]) { // 目标值在 [mid, right] 内
                    left = mid + 1;
                } else { // 目标值在 [left, mid-1] 内
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        _033_SearchinRotatedSortedArray obj = new _033_SearchinRotatedSortedArray();
        int[] nums = new int[]{2, 0, 1}; //
        System.out.println(obj.search(nums, 1));

        nums = new int[]{1, 2, 0}; //
        System.out.println(obj.search(nums, 2));

        nums = new int[]{1, 2, 0}; //
        System.out.println(obj.search(nums, 3));

        nums = new int[]{1, 0}; //
        System.out.println(obj.search(nums, 0));
    }
}
