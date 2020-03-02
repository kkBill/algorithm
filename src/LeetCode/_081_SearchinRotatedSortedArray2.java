package LeetCode;

public class _081_SearchinRotatedSortedArray2 {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (target == nums[mid]) return true;

            if (nums[mid] > nums[right]) {
                if (target >= nums[left] && target < nums[mid]) { // 目标值在 [left, mid] 区间内
                    right = mid - 1;
                } else { // 目标值在 [mid+1, right]
                    left = mid + 1;
                }
            } else if(nums[mid] < nums[right]){
                if (target > nums[mid] && target <= nums[right]) { // 目标值在 [mid, right] 内
                    left = mid + 1;
                } else { // 目标值在 [left, mid-1] 内
                    right = mid - 1;
                }
            }else {
                right--;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        _081_SearchinRotatedSortedArray2 obj = new _081_SearchinRotatedSortedArray2();
        int[] nums = new int[]{4,5,6,7,0,0,1,2};
        System.out.println(obj.search(nums,5)); // true
        System.out.println(obj.search(nums,0)); // true
        System.out.println(obj.search(nums,3)); // false

        nums = new int[]{1, 1, 3, 1};
        System.out.println(obj.search(nums, 3)); // true
    }
}
