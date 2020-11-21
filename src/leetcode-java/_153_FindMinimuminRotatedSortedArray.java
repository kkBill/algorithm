package LeetCode;

public class _153_FindMinimuminRotatedSortedArray {
    /**
     * 方法1：中间元素与right节点进行比较
     * 旋转排序数组 nums可以被拆分为 2 个排序数组 nums1, nums2，并且 nums1任一元素 >= nums2任一元素；
     * 因此，考虑二分法寻找此两数组的分界点 nums[i] (即第 2 个数组的首个元素)。
     * 设置 left, right指针在 nums数组两端，mid为每次二分的中点：
     * 当 nums[mid] > nums[right]时，mid一定在第 1 个排序数组中，i一定满足 mid < i <= right，因此执行 left = mid + 1；
     * 当 nums[mid] < nums[right] 时，mid一定在第 2 个排序数组中，i一定满足 left < i <= mid，因此执行 right = mid；
     */
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right){
            int mid = (right - left) / 2 + left;
            // [left, mid] 是前半个有序区间，最小值位于[mid+1, right]内
            if(nums[mid] > nums[right]) left = mid + 1;
            // [mid, right] 是后半个有序区间，最小值位于[left, mid]内
            else right = mid;
        }
        return nums[right];
    }

    public static void main(String[] args) {
        _153_FindMinimuminRotatedSortedArray obj = new _153_FindMinimuminRotatedSortedArray();
        int[] nums = new int[]{2,0,1}; //
        System.out.println(obj.findMin(nums));

        nums = new int[]{1,2,0}; //
        System.out.println(obj.findMin(nums));

        nums = new int[]{1,0}; //
        System.out.println(obj.findMin(nums));

        // 题目说了不存在重复数字
        //nums = new int[]{1,1,1}; //
        //System.out.println(obj.findMin(nums));
    }
}
