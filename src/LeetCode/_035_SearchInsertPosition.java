package LeetCode;

public class _035_SearchInsertPosition {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length;
        while (left < right){
            int mid = (right - left)/2 + left;
            if(target > nums[mid]) left = mid + 1;
            else if(target < nums[mid]) right = mid;
            else return mid;
        }
        return left;
    }
    public static void main(String[] args) {
        _035_SearchInsertPosition obj = new _035_SearchInsertPosition();
        int[] nums = new int[]{1,3,5,6};
        System.out.println(obj.searchInsert(nums,5)); // 2
        System.out.println(obj.searchInsert(nums,2)); // 1
        System.out.println(obj.searchInsert(nums,7)); // 4
        System.out.println(obj.searchInsert(nums,0)); // 0
    }
}
