package LeetCode;

public class CountSort {
public static void countSort(int[] nums) {
    if (nums.length < 2) return;
    int minVal = nums[0], maxVal = nums[0];
    for (int num : nums) {
        minVal = Math.min(minVal, num);
        maxVal = Math.max(maxVal, num);
    }
    // 优化，如果按照maxVal建立桶太浪费空间了
    // 比如出现[100, 93, 97, 92, 96, 99, 92, 89, 93, 97, 90, 94, 92, 95]这种情况
    int m = maxVal - minVal + 1;
    // 建立 m 个"桶"
    int[] bucket = new int[m]; // bucket[i] 实际上表示值为 i+minVal 的元素个数
    // 扫面一遍数组，把值放入相应的桶中
    for (int num : nums) {
        bucket[num - minVal]++; // 优化处理，减小了桶占用的空间
    }
    // 扫描一遍桶: O(n)
    int n = 0;
    for (int i = 0; i < bucket.length; i++) {
        while (bucket[i] > 0) {
            nums[n++] = i + minVal;
            bucket[i]--;
        }
    }
}

    public static void main(String[] args) {
        int[] nums = new int[]{100, 93, 97, 92, 96, 99, 92, 89, 93, 97, 90, 94, 92, 95};
        countSort(nums);
        for(int num : nums){
            System.out.print(num + " ");
        }
    }
}
