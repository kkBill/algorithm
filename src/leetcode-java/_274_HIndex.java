package LeetCode;

public class _274_HIndex {
    public int hIndex(int[] citations) {
        // 修正数据
        int n = citations.length;
        for (int i = 0; i < n; i++) {
            if (citations[i] > n) citations[i] = n;
        }
        // 计数排序
        int[] bucket = new int[n + 1];
        for (int c : citations) {
            bucket[c]++;
        }
        // sk 表示数组元素大于等于k 的个数
        int sk = 0, k;
        for (k = n; k > 0; k--) {
            sk += bucket[k]; // bucket[k] 保存了数组中值为k的个数
            if (sk >= k) break;
        }
        return k;
    }

    public static void main(String[] args) {
        _274_HIndex obj = new _274_HIndex();
        int[] nums = new int[]{5, 0, 1, 3, 6};
        System.out.println(obj.hIndex(nums));

        nums = new int[]{0, 0, 0};
        System.out.println(obj.hIndex(nums));

        nums = new int[]{1, 1, 1};
        System.out.println(obj.hIndex(nums));

        nums = new int[]{3, 3};
        System.out.println(obj.hIndex(nums));
    }
}
