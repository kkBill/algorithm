package LeetCode;

public class _287_FindtheDuplicateNumber {
    public int findDuplicate(int[] nums) {
        int n = nums.length - 1; // 数组中的元素在区间[1,n]内
        int left = 1, right = n; // 左右边界
        while (left < right) {
            int mid = (right - left) / 2 + left; // 切记不要写成(left+right)/2
            // 遍历数组
            int count = 0; // 记录数组中 nums[i] ≤ mid 的个数
            for (int i = 0; i < nums.length; i++) {
                if (nums[i] <= mid) count++;
            }
            if (count > mid) right = mid;
            else left = mid+1;
        }
        return left;
    }

    public static void main(String[] args) {
        _287_FindtheDuplicateNumber obj = new _287_FindtheDuplicateNumber();
        int[] nums = new int[]{1, 1};
        System.out.println(obj.findDuplicate(nums));
    }
}
