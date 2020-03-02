package LeetCode;

public class _238_ProductofArrayExceptSelf {
    /*
    // 时间复杂度：O(n)  3次遍历
    // 空间复杂度：O(n)  left[] 和 right[]数组
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = 1;
        int[] right = new int[n];
        right[n-1] = 1;
        for(int i = 1; i < n; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        for(int i = n - 2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }
        int[] output = new int[n];
        for(int i = 0; i < n; i++) {
            output[i] = left[i] * right[i];
        }
        return output;
    }

     */

    // 时间复杂度：O(n) 2次遍历
    // 空间复杂度：O(1)
    public int[] productExceptSelf(int[] nums) {
        // 1,2,6,24
        int k = 1;
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            res[i] = k;
            k *= nums[i];
        }
        k = 1;
        for(int i = nums.length-1; i >= 0; i--) {
            res[i] *= k;
            k *= nums[i];
        }
        return res;
    }

    public static void main(String[] args) {
        _238_ProductofArrayExceptSelf obj = new _238_ProductofArrayExceptSelf();
        int[] nums = new int[]{2,3,4,5};
        int[] res = obj.productExceptSelf(nums);
        for(int val : res) {
            System.out.print(val + " ");
        }
    }
}
