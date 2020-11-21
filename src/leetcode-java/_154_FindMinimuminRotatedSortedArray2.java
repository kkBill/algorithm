package LeetCode;

public class _154_FindMinimuminRotatedSortedArray2 {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length-1;
        while (left < right){
            int mid = (right - left) / 2 + left;
            // [left, mid] 是前半个有序区间，最小值位于[mid+1, right]内
            if(nums[mid] > nums[right]) left = mid + 1;
                // [mid, right] 是后半个有序区间，最小值位于[left, mid]内
            else if(nums[mid] < nums[right])
                right = mid;
            else
                right--;
        }
        return nums[right];
    }

    public static void main(String[] args) {
        _154_FindMinimuminRotatedSortedArray2
                obj = new _154_FindMinimuminRotatedSortedArray2();
        int[] nums = new int[]{2,0,1}; //
        System.out.println(obj.findMin(nums));

        nums = new int[]{1,2,0}; //
        System.out.println(obj.findMin(nums));

        nums = new int[]{0,1,0}; //
        System.out.println(obj.findMin(nums));

        nums = new int[]{1,1,1}; //
        System.out.println(obj.findMin(nums));
    }
}
