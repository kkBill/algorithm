package LeetCode;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class _179_LargestNumber {

    // 写法1
    public String largestNumber(int[] nums) {
        int n = nums.length;
        if (n == 0) return "";

        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = String.valueOf(nums[i]);
        }
        Arrays.sort(s, (x, y) -> (y + x).compareTo(x + y));
        StringBuilder res = new StringBuilder();
        for (String item : s) {
            res.append(item);
        }

        //if (res.charAt(0) == '0') return "0"; // 对应res="000"全是0的情况
        //return res.toString();
        // 充分利用api，写出简洁的代码
        return res.toString().startsWith("0") ? "0" : res.toString();
    }


    /*
    // 写法2, 充分利用lambda表达式
    // 参考来的，感觉很骚，我都不认识这是Java代码了
    public String largestNumber(int[] nums) {
        String result = IntStream.of(nums).mapToObj(String::valueOf).sorted(((o1, o2) -> (o2 + o1).compareTo(o1 + o2))).collect(Collectors.joining());
        return result.startsWith("0") ? "0" : result;
    }
    */
    public static void main(String[] args) {
        _179_LargestNumber obj = new _179_LargestNumber();
        int[] nums = new int[]{0, 0, 0, 1};
        System.out.println(obj.largestNumber(nums));

        nums = new int[]{0, 0, 1, 23, 10};
        System.out.println(obj.largestNumber(nums));

        nums = new int[]{0, 0, 0};
        System.out.println(obj.largestNumber(nums));
    }
}
