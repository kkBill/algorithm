package LeetCode;

public class _080_RemoveDuplicatesfromSortedArray2 {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) return nums.length;
        int left = 2;
        for (int right = 2; right < nums.length; right++) {
            if(nums[right] != nums[left-2])
                nums[left++] = nums[right];
        }
        return left;
    }

    public static void main(String[] args) {
        _080_RemoveDuplicatesfromSortedArray2
                obj = new _080_RemoveDuplicatesfromSortedArray2();
        System.out.println(obj.removeDuplicates(new int[]{1, 1, 1, 2, 2, 3}));
        System.out.println(obj.removeDuplicates(new int[]{0, 0, 1, 1, 1, 1, 2, 3, 3}));
        System.out.println(obj.removeDuplicates(new int[]{0}));
        System.out.println(obj.removeDuplicates(new int[]{0, 0}));
        System.out.println(obj.removeDuplicates(new int[]{0, 0, 0, 0, 0, 0, 0}));
    }
}
