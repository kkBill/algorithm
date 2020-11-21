package LeetCode;

public class _268_MissingNumber {
    // 方法1：数学法
    /*
    public int missingNumber(int[] nums) {
        int n = nums.length;
        long sum = (1+n) * n / 2;
        for(int num : nums){
            sum -= num;
        }
        return (int)sum;
    }

     */

    // 下面是另一种可以完全放置溢出的写法
    /*
public int missingNumber(int[] nums) {
    int sum = 0;
    for(int i = 0; i < nums.length;i++){
        sum += (i+1);
        sum -= nums[i];
    }
    return sum;
}
    */

    // 位运算法
    public int missingNumber(int[] nums) {
        int missNumber = nums.length;
        for(int i=0;i<nums.length;i++){
            missNumber ^= i ^ nums[i];
        }
        return missNumber;
    }

    public static void main(String[] args) {
        _268_MissingNumber obj = new _268_MissingNumber();
        System.out.println(obj.missingNumber(new int[]{3,0,1}));
        System.out.println(obj.missingNumber(new int[]{9,6,4,2,3,5,7,0,1}));
    }
}
