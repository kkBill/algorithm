package LeetCode;

public class _088_MergeSortedArray {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (n == 0) return;
        if (m == 0) {
            // 把nums2[]中从0开始的n个元素复制到nums1[0...]中
            System.arraycopy(nums2, 0, nums1, 0, n);
            return;
        }

        int i = m - 1, j = n - 1, len = m + n - 1;
        while (i >= 0 && j >= 0) {
            if (nums1[i] >= nums2[j]) nums1[len--] = nums1[i--];
            else nums1[len--] = nums2[j--];
        }
        if (i == -1) {
            System.arraycopy(nums2,0,nums1,0,len+1);
//            while (j >= 0) {
//                nums1[len--] = nums2[j--];
//            }
        }
    }

    public static void main(String[] args) {
        _088_MergeSortedArray obj = new _088_MergeSortedArray();
        int[] nums1 = new int[]{5, 0, 0};
        int[] nums2 = new int[]{3, 4};
        obj.merge(nums1, 1, nums2, 2);
        for (int num : nums1) {
            System.out.print(num + " ");
        }
    }
}
