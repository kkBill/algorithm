package LeetCode;

public class _169_MajorityElement {
    public int majorityElement(int[] nums) {
        int candidate = 0, count = 0;
        for(int num : nums) {
            if(count == 0) {
                candidate = num;
            }
            if(candidate == num) count++;
            else count--;
        }

        count = 0;
        for(int num : nums) {
            if(num == candidate) count++;
        }
        if(count > nums.length/2) return candidate;
        return -1;
    }

    public static void main(String[] args) {
        _169_MajorityElement obj = new _169_MajorityElement();
        System.out.println(obj.majorityElement(new int[]{1,1,1,2,1,2,2}));
        System.out.println(obj.majorityElement(new int[]{1,3,3,2,3,1,2}));
    }
}
