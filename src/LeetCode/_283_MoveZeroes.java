package LeetCode;

public class _283_MoveZeroes {
    public void moveZeroes(int[] nums) {
        for (int left = 0, i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 交换
                int t = nums[left];
                nums[left] = nums[i];
                nums[i] = t;
                left++;
            }
        }
    }

    public static void main(String[] args) {

    }
}
