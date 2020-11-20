### 贪心问题

题目列表

| 标题                                       | 评注   |
| ---------------------------------------- | ---- |
| [860. Lemonade Change](https://leetcode-cn.com/problems/lemonade-change/) |      |
|                                          |      |
|                                          |      |
|                                          |      |



---

##### [860. Lemonade Change](https://leetcode-cn.com/problems/lemonade-change/)

在柠檬水摊上，每一杯柠檬水的售价为 5 美元。顾客排队购买你的产品，（按账单 bills 支付的顺序）一次购买一杯。每位顾客只买一杯柠檬水，然后向你付 5 美元、10 美元或 20 美元。你必须给每个顾客正确找零，也就是说净交易是每位顾客向你支付 5 美元。

注意，一开始你手头没有任何零钱。

如果你能给每位顾客正确找零，返回 true ，否则返回 false 。

```
输入：[5,5,5,10,20]
输出：true
解释：
前 3 位顾客那里，我们按顺序收取 3 张 5 美元的钞票。
第 4 位顾客那里，我们收取一张 10 美元的钞票，并返还 5 美元。
第 5 位顾客那里，我们找还一张 10 美元的钞票和一张 5 美元的钞票。
由于所有客户都得到了正确的找零，所以我们输出 true。

输入：[5,5,10,10,20]
输出：false
解释：
前 2 位顾客那里，我们按顺序收取 2 张 5 美元的钞票。
对于接下来的 2 位顾客，我们收取一张 10 美元的钞票，然后返还 5 美元。
对于最后一位顾客，我们无法退回 15 美元，因为我们现在只有两张 10 美元的钞票。
由于不是每位顾客都得到了正确的找零，所以答案是 false。
```

分析：这题主要是简单的模拟。不知道为什么leetcode把它标记为贪心，可能贪心的思想体现在找零时，优先找回10块的，再找回5块的。

```java
class Solution {
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
}
```

