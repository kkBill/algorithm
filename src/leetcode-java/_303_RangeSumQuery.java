package LeetCode;

public class _303_RangeSumQuery {

    private int[] nums;
    public _303_RangeSumQuery(int[] nums) {
        this.nums = nums;
        for(int i=1;i<nums.length;i++){
            this.nums[i] += this.nums[i-1];
        }
    }

    public int sumRange(int i, int j) {
        if(i==0) return this.nums[j];
        return this.nums[j] - this.nums[i-1];
    }

    public static void main(String[] args) {
        int[] nums = {-2, 0, 3, -5, 2, -1};
        _303_RangeSumQuery obj = new _303_RangeSumQuery(nums);
        System.out.println(obj.sumRange(0,2));
        System.out.println(obj.sumRange(2,5));
        System.out.println(obj.sumRange(0,5));
    }
}
