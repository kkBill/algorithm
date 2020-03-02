package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class _202_HappyNumber {
public boolean isHappy(int n) {
    Set<Integer> set = new HashSet<>();
    while (true) {
        if (set.contains(n)) return false;
        else set.add(n);
        int t = 0;
        while (n > 0) {
            int d = n % 10;
            t += d * d;
            n /= 10;
        }
        if (t == 1) return true;
        n = t;
    }
}

    public static void main(String[] args) {
        _202_HappyNumber obj = new _202_HappyNumber();
        System.out.println(obj.isHappy(19));
        System.out.println(obj.isHappy(22));
    }
}
