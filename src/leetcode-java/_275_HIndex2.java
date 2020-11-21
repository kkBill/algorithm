package LeetCode;

public class _275_HIndex2 {
    /*
    // 方法1：线性遍历，时间复杂度为O(n)
    public int hIndex(int[] citations) {
        for (int i = 0; i < citations.length; i++) {
            if (citations[i] >= citations.length - i) return citations.length - i;
        }
        return 0;
    }
    */

    // 方法2：时间复杂度：O(log n)
    public int hIndex(int[] citations) {
        int left = 0, right = citations.length;
        while (left < right) {
            int mid = (right - left) / 2 + left;
            if (citations[mid] == citations.length - mid) {
                return citations.length - mid;
            } else if (citations[mid] < citations.length - mid) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return citations.length - right;
    }

    public static void main(String[] args) {
        _275_HIndex2 obj = new _275_HIndex2();
        int[] nums = new int[]{0, 1, 3, 5, 6};
        System.out.println(obj.hIndex(nums));

        nums = new int[]{0, 0, 0};
        System.out.println(obj.hIndex(nums));

        nums = new int[]{1, 1, 1};
        System.out.println(obj.hIndex(nums));

        nums = new int[]{3, 3};
        System.out.println(obj.hIndex(nums));
    }
}
