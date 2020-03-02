package LeetCode;

public class _295_FollowUp {
    /**
     * case 1
     */
    private int[] count = new int[101];
    private int n = 0;

    // 插入操作O(1)
    public void addNum(int num) {
        n++;
        count[num]++;
    }

    public double findMedian() {
       return 0;
    }

    public static void main(String[] args) {
        _295_FollowUp obj = new _295_FollowUp();
        obj.addNum(1);
        obj.addNum(2);
        System.out.println(obj.findMedian());
        obj.addNum(3);
        System.out.println(obj.findMedian());
    }
}
