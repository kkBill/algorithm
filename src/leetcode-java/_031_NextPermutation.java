package LeetCode;

import java.util.Arrays;

public class _031_NextPermutation {
    public void nextPermutation(int[] nums) {
        // find largest k that nums[k] < nums[k+1]
        int k = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) k = i;
        }
        // if no such a k exist, then reverse the whole array
        if (k == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // find largest l that nums[l] > nums[k]
        int l = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[k]) l = i;
        }
        // swap nums[k] and nums[l]
        swap(nums, k, l);

        // reverse nums[k+1:]
        if (k + 1 < nums.length - 1) {
            reverse(nums, k + 1, nums.length - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }


    public static void main(String[] args) {
        _031_NextPermutation obj = new _031_NextPermutation();
        int[] nums = new int[]{3, 2, 1};
        obj.nextPermutation(nums);
        for(int num : nums) {
            System.out.print(num + " ");
        }
    }
}
