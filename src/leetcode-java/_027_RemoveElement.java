package LeetCode;

public class _027_RemoveElement {
    public int removeElement(int[] nums, int val) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) nums[len++] = nums[i];
        }
        return len;
    }

    public static void printArray(int[] nums, int n) {
        for (int i = 0; i < n; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        _027_RemoveElement obj = new _027_RemoveElement();
        int[] nums = new int[]{3, 3};
        int len = obj.removeElement(nums, 3);
        printArray(nums, len);

        int[] nums2 = new int[]{2, 3, 3, 2};
        len = obj.removeElement(nums2, 3);
        printArray(nums2, len);

        int[] nums3 = new int[]{3, 3};
        len = obj.removeElement(nums3, 5);
        printArray(nums3, len);

        int[] nums4 = new int[]{4, 5};
        len = obj.removeElement(nums4, 4);
        printArray(nums4, len);

        int[] nums5 = new int[]{4, 5};
        len = obj.removeElement(nums5, 5);
        printArray(nums5, len);
    }
}
