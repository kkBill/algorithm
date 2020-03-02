package LeetCode;

public class _075_SortColors {

    public void sortColors(int[] nums) {
        int[] count = new int[3];//count[i]表示数组中元素i的个数
        for (int num : nums)
            count[num]++;
        int len = 0;
        for (int i = 0; i < 3; i++) {
            while (count[i] > 0) {
                nums[len++] = i;
                count[i]--;
            }
        }
    }

    /*
    public void sortColors(int[] nums) {
        if(nums.length == 1) return;
        int left = 0, right = nums.length;
        int i = 0;
        while (i < right) {
            if (nums[i] == 0) {
                swap(nums, left, i);
                left++;
                i++;
            } else if (nums[i] == 1) {
                i++;
            } else {
                right--;
                swap(nums, right, i);
            }
        }
    }
    */

    private void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }

    private static void printArray(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        _075_SortColors obj = new _075_SortColors();
        int[] nums = new int[]{2, 0, 2, 1, 1, 0};
        obj.sortColors(nums);
        printArray(nums);

        nums = new int[]{1, 2, 0};
        obj.sortColors(nums);
        printArray(nums);
    }
}
