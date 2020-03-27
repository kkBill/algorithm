package LeetCode;

public class _860_LemonadeChange {
    public boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                ten++;
                five--; // 找零
                if (five < 0) return false;
            } else {
                if (ten > 0) {
                    ten--;
                    five--;
                    if (five < 0) return false;
                } else {
                    five -= 3;
                    if (five < 0) return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        _860_LemonadeChange obj = new _860_LemonadeChange();
        int[] bills = new int[]{5,5,10,10,5,20,5,5,5,20,20};
        System.out.println(obj.lemonadeChange(bills));

        bills = new int[]{5,5,5,10,20};
        System.out.println(obj.lemonadeChange(bills));
    }
}
