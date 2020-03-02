package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class _189_RotateArray {
    // 方法1: 每次移动一个，总共移动k次
    // 时间复杂度O(k*n)(会超时)， 空间复杂度O(1)
    // 可优化的点是选择移动个数较少的方向移动，不一定非得从前向后移动
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n; // 加上这一行代码之后可以勉强通过测试，耗时约400ms
        for(int step = 0; step < k; step++){
            int tail = nums[n-1];
            for(int i = n-1; i > 0; i--)
                nums[i] = nums[i-1];
            nums[0] = tail;
        }
    }

    // 优化版本， 时间可以降低到180ms左右
    public void rotate2(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if(k <= n - k){
            // 从左向右移动
            for(int step = 0; step < k; step++){
                int tail = nums[n-1];
                for(int i = n-1; i > 0; i--)
                    nums[i] = nums[i-1];
                nums[0] = tail;
            }
        }else{
            // 从右向左移动
            for(int step = 0; step < n-k; step++){
                int head = nums[0];
                for(int i = 0; i < n-1; i++)
                    nums[i] = nums[i+1];
                nums[n-1] = head;
            }
        }
    }

    // 方法3：多次翻转
    // 时间复杂度：O(n), 空间复杂度：O(1)
    /*
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        reverse(nums,0,n-k-1);
        reverse(nums,n-k,n-1);
        reverse(nums,0,n-1);
    }
    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
    */

    // 方法2：一次性移动k个，不建议，因为要额外空间
    // 时间复杂度O(n)，空间复杂度O(k%n)，耗时约0ms
    /*
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k %= n;
        if (k == 0) return;
        int[] tail = new int[k];
        for (int i = n - k; i < n; i++) { // O(k)
            tail[i - n + k] = nums[i];
        }
        for (int i = n - k - 1; i >= 0; i--) { // O(n-k)
            nums[i + k] = nums[i];
        }
        for (int i = 0; i < k; i++) { // O(k)
            nums[i] = tail[i];
        }
    }
    */

    public static void main(String[] args) {
        _189_RotateArray obj = new _189_RotateArray();
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        obj.rotate2(nums, 6);
        for (int val : nums) {
            System.out.print(val + " ");
        }
    }
}
