package LeetCode;

public class _004_MedianofTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        // 技巧
        int k1 = (m+n+1)/2, k2 = (m+n+2)/2;
        double median1 = findKthElementOfTwoSortedArrays(nums1,0,m-1,nums2,0,n-1,k1);
        double median2 = findKthElementOfTwoSortedArrays(nums1,0,m-1,nums2,0,n-1,k2);
        return (median1+median2)/2;
    }
    // 求两个有序数组的第k个值
    private double findKthElementOfTwoSortedArrays(int[] nums1, int l1, int r1, int[] nums2, int l2, int r2, int k){
        // 递归边界：数组nums1已经空了，因此可以直接在数组nums2中返回值
        if(l1 > r1) {
            return nums2[l2+k-1];
        }

        int len1 = r1-l1+1, len2 = r2-l2+1;
        if(len1 > len2){ // 保证数组nums1的长度小于数组nums2的长度，便于后面的处理
            return findKthElementOfTwoSortedArrays(nums2,l2,r2,nums1,l1,r1,k);
        }

        // 递归边界
        if(k == 1) {
            return Math.min(nums1[l1],nums2[l2]);
        }

        // 两个数组中本轮待比较的元素索引
        int k1 = Math.min(len1,k/2) - 1 + l1;
        int k2 = Math.min(len2,k/2) - 1 + l2;

        if(nums1[k1] >= nums2[k2]){ // 排除nums2中前(k/2)个元素
            return findKthElementOfTwoSortedArrays(nums1,l1,r1,nums2,l2+k/2,r2,k-Math.min(len2,k/2));
        }else {
            return findKthElementOfTwoSortedArrays(nums1,l1+k/2,r1,nums2,l2,r2,k-Math.min(len1,k/2));
        }
    }

    public static void main(String[] args) {
        _004_MedianofTwoSortedArrays obj = new _004_MedianofTwoSortedArrays();
        int[] nums1 = new int[]{2};
        int[] nums2 = new int[]{};
        System.out.println(obj.findMedianSortedArrays(nums1, nums2));

        nums1 = new int[]{1};
        nums2 = new int[]{2,3,4,5,6};
        System.out.println(obj.findMedianSortedArrays(nums1, nums2));
    }
}
