package LeetCode;

public class _026_RemoveDuplicatesfromSortedArray {
    /*
    // 自己写的版本，虽然复杂度是一样的，但是代码不够优雅。
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) return 0;
        int left = 0, right = 0;
        while (right < nums.length) {
            while (right < nums.length && nums[left] == nums[right])
                right++;
            if (right == nums.length) break;
            nums[++left] = nums[right];
        }
        return left + 1;
    }
    */
    // 时间复杂度：O(n)
    // 空间复杂度：O(1)
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0) return 0;
        int left = 0;
        for(int right = 1; right < nums.length; right++){
            if(nums[left] != nums[right]){
                nums[++left] = nums[right];
            }
        }
        return left+1;
    }


    public static void main(String[] args) {
        _026_RemoveDuplicatesfromSortedArray obj = new _026_RemoveDuplicatesfromSortedArray();
        System.out.println(obj.removeDuplicates(new int[]{1, 2, 3, 4}));
        System.out.println(obj.removeDuplicates(new int[]{1, 2, 2, 3, 3, 4}));
    }
}
