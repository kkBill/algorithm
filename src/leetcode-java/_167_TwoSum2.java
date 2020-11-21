package LeetCode;

public class _167_TwoSum2 {
    public int[] twoSum(int[] numbers, int target) {
        int[] ans = new int[2];
        int left = 0, right = numbers.length-1;
        while (left < right) {
            if(numbers[left] + numbers[right] < target) left++;
            else if(numbers[left] + numbers[right] > target) right--;
            else {
                ans[0] = left+1;
                ans[1] = right+1;
                return ans;
            }
        }
        return new int[]{-1, -1};
    }
    public static void main(String[] args) {
        _167_TwoSum2 obj = new _167_TwoSum2();
        int[] nums = new int[]{1,2,3,4,5};
        int[] ans = obj.twoSum(nums, 6);
        System.out.println(ans[0] + " " + ans[1]);
    }
}
