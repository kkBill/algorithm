### 数学问题和位运算

* 进制转换问题
* 数值对应的二进制序列中‘1’的个数
* 如何求质数（筛法、定义法）
* 如何取一个数各个位上的值
* 位运算




##### [7. 整数反转](https://leetcode-cn.com/problems/reverse-integer/) [四星，有意思]

给出一个 32 位的有符号整数，你需要将这个整数中每位上的数字进行反转。如 123 -> 321；-123 -> -321；120 -> 21；假设我们的环境**只能存储得下 32 位的有符号整数**，则其数值范围为 [−2^31,  2^31 − 1]。请根据这个假设，如果反转后整数溢出那么就返回 0。比如，2147483647 -> 7463847412？(overflow) 

本题关键是处理溢出的情况。怎么判断是否溢出？比如 9 * 1000000000 会溢出，结果是一个非负的错误值：410065408。

从本题中学到两点：

* 如何取出原数字的低位并转化成新数的高位？我最先的想法是借助队列来存放每一位的数字。但是这么做显然就反应了对这一类题目的理解不够，正确的做法如下：

```
// pop operation
pop = x % 10; （注意负数的求余操作也是一样的：-18 % 10 = -8）
x /= 10;
// push operation, rev 初始化为 0
temp = rev * 10 + pop; // overflow may happens here
rev = temp;
```

* 如何判断溢出？
  * 先考虑正数溢出，若发生溢出，则 $$ rev * 10  + pop > 2147483647 $$
    * 若 $$rev > 2147483647 / 10$$，则肯定溢出
    * 若 $$rev == 2147483647 / 10$$ 那就要保证 pop 不能大于 7 
  * 负数溢出，若发生溢出，则 $$ rev * 10  + pop < -2147483648 $$
    * 若 $$rev < -2147483648 / 10$$，则肯定溢出
    * 若 $$rev == -2147483648 / 10$$，那就要保证 pop 不能小于 -8

时间复杂度：$O(\log_{10}(x))$，因为 x 中大约有 $\log_{10}(x)$位数字；空间复杂度：$O(1)$。ps：这代码实在是太精美了！！！

```java
public int reverse(int x) {
    int a = Integer.MAX_VALUE / 10, b = Integer.MIN_VALUE / 10;
    int rev = 0;
    while (x != 0){
        int pop = x % 10;
        x /= 10;
        if(rev > a || (rev == a && pop > 7)) return 0;
        if(rev < b || (rev == b && pop < -8)) return 0;
        rev = rev * 10 + pop;
    }
    return rev;
}
```



##### [9. 回文数](https://leetcode-cn.com/problems/palindrome-number/) [四星，有意思]

判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。比如，121是回文数，但 -121 就不是回文数，从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。（你能不将整数转为字符串来解决这个问题吗？）

分析：采用上面一题“整数反转”的思路来做，如果一个数翻转之后和原来数相等，则表明它是一个回文数，反之则不是。因此，考察的还是整数反转的内容。时间复杂度和上一题一样。

```java
public boolean isPalindrome(int x) {
    if(x < 0) return false;
    int a = Integer.MAX_VALUE / 10;
    int reverseX = 0, originalX = x;
    while(x != 0) {
        int pop = x % 10;
        x /= 10;
        if(reverseX > a || (reverseX == a && pop > 7)) return false; // 溢出
        reverseX = reverseX * 10 + pop;
    }
    //注意不能写成 reverseX == x，因为 x 在之前的处理中已经变成 0 了
    return reverseX == originalX;
}
```

看了官方题解，原来还有更骚的方法。参考[这里](https://leetcode-cn.com/problems/palindrome-number/solution/hui-wen-shu-by-leetcode/)。

```java
// 翻转“半个”整数，无需考虑溢出的情况  --> 太骚了
public boolean isPalindrome(int x) {
    // 如果是负数，或者 x 的末位为 0，则直接可以判定不是回文数
    if (x < 0 || (x != 0 && x % 10 == 0)) return false;
    int reversedX = 0;
    while (x > reversedX) {
        int pop = x % 10;
        x /= 10;
        reversedX = reversedX * 10 + pop;
    }
    // 比如原x = 1221，则循环结束后，x = 12, reversedX = 12
    // 比如原x = 121，则循环结束后，x = 1, reversedX = 12
    return x == reversedX || x == reversedX/10;
}
```



##### [8. 字符串转换整数 (atoi)](https://leetcode-cn.com/problems/string-to-integer-atoi/)

分析：题目不难，主要有几个case没有考虑到，"   +-43" --> 0 ；"+0 123" --> 0；"0-1" --> 0；" -   123" --> 0；

代码：

```java
public int myAtoi(String str) {
    int result = 0, sign = 1, t = Integer.MAX_VALUE / 10; // t 用于判别是否溢出
    boolean noSignBefore = true, noDigitBefore = true; // 关键
  
    for (char c : str.toCharArray()) {
        if (c == ' ' && noSignBefore && noDigitBefore) {
            continue;
        }
        if (c == '+' && noSignBefore && noDigitBefore) {
            noSignBefore = false;
            continue;
        }
        if (c == '-' && noSignBefore && noDigitBefore) {
            noSignBefore = false;
            sign = -1;
            continue;
        }
        if (Character.isDigit(c)) {
            noDigitBefore = false;
            int currDigit = c - '0';
            // 处理溢出
            if (sign == 1 && (result > t || (result == t && currDigit > 7))) 
            	return Integer.MAX_VALUE;
            if (sign == -1 && (result > t || (result == t && currDigit > 8))) 
            	return Integer.MIN_VALUE;

            result = result * 10 + currDigit;
            continue;
        }
        // 遇到无效字符，直接跳出循环
        break;
    }
    return sign * result;
}
```



##### [12. 整数转罗马数字](https://leetcode-cn.com/problems/integer-to-roman/) [五星]

分析：本题居然可以用贪心的思想来做，然而我连暴力法都写不好！贪心做法参考[这里](https://leetcode-cn.com/problems/integer-to-roman/solution/tan-xin-suan-fa-by-liweiwei1419/) 。简单的说，想要表示一个数，首先从尽量大的值去考虑。

```java
// 暴力法
public String intToRoman(int num) {
    String[][] table = new String[][]{
            {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"}, // 1~9
            {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"}, // 10~90
            {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"}, // 100~900
            {"", "M", "MM", "MMM"} };                                     // 1000~3000

    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (num > 0) {
        sb.insert(0, table[i][num % 10]);
        i++;
        num /= 10;
    }
    return sb.toString();
}
// 贪心法（绝了~）
// 时间复杂度：O(1)， 空间复杂度：O(1)
public String intToRoman(int num) {
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    StringBuilder sb = new StringBuilder();
    int k = 0;
    while (k < values.length) {
        while (num >= values[k]){
            sb.append(roman[k]);
            num -= values[k];
        }
        k++;
    }
    return sb.toString();
}
```



##### [13. 罗马数字转整数](https://leetcode-cn.com/problems/roman-to-integer/) [四星]

思路基本和上一题一样，从左向右遍历字符串`s`，遍历过程中截取哈希表中尽可能长的子串，将对应的值加入到最终的结果中。

```java
public int romanToInt(String s) {
    // 预处理
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    Map<String, Integer> map = new HashMap<>();
    for (int i = 0; i < roman.length; i++) {
        map.put(roman[i], values[i]);
    }
    // 
    int num = 0, begin = 0, end = 1;
    while (begin < s.length()) {
        while (end <= s.length() && map.containsKey(s.substring(begin, end))) end++;
        end--;
        num += map.get(s.substring(begin,end));
        begin = end;
        end++;
    }
    return num;
}
```







##### [67. 二进制求和](https://leetcode-cn.com/problems/add-binary/) [一星]

常规题，从低位开始逐位相加，注意加上进位carry（初始化为0）

```java
/**
 * 时间复杂度：O(max(m,n))
 * 空间复杂度：O(max(m,n))
 */
public String addBinary(String a, String b) {
    StringBuilder sb = new StringBuilder();
    int carry = 0;
    int i = a.length()-1, j = b.length()-1;
    while(i >= 0 || j >= 0){
        int xi = i >= 0 ? a.charAt(i)-'0' : 0;
        int xj = j >= 0 ? b.charAt(j)-'0' : 0;
        int d = carry + xi + xj;
        if(d > 1) {
            carry = 1;
            d = d % 2;
        }else {
            carry = 0;
        }
        sb.append((char)('0' + d));
        i--;
        j--;
    }
    if(carry == 1){
        sb.append('1');
    }
    return sb.reverse().toString();
}
```



##### [415. 字符串相加](https://leetcode-cn.com/problems/add-strings/) [一星]

本题已经保证字符串表示的数是合法的（只包含数字，且不包含前导零），方法和上一题一样。

```java
public String addStrings(String num1, String num2) {
    int carry = 0, i = num1.length()-1, j = num2.length()-1;
    StringBuilder sb = new StringBuilder();
    while (i >= 0 || j >= 0){
        int x1 = i >= 0 ? num1.charAt(i)-'0' : 0;
        int x2 = j >= 0 ? num2.charAt(j)-'0' : 0;
        int d = x1 + x2 + carry;
        if(d >= 10){
            carry = 1;
            d %= 10;
        }else{
            carry = 0;
        }
        sb.append(d);
        i--;
        j--;
    }
    if(carry == 1) sb.append(1);
    return sb.reverse().toString();
}
```



##### [43. 字符串相乘](https://leetcode-cn.com/problems/multiply-strings/) [四星]

本题其实就是前一题“字符串相加”的升级版，老老实实根据乘法的规则写出代码即可。当然还存在很骚的解法，但这不是我能想出来的。

```java
// 时间复杂度：O(m*n)
// 空间复杂度：O(m+n)
public String multiply(String num1, String num2) {
    if (num1.equals("0") || num2.equals("0")) return "0";

    StringBuilder result = new StringBuilder();
    StringBuilder tailingZero = new StringBuilder();

    for (int i = num1.length() - 1; i >= 0; i--) {
        int carry = 0;
        int x1 = num1.charAt(i) - '0';
        StringBuilder product = new StringBuilder();
        product.append(tailingZero);

        for (int j = num2.length() - 1; j >= 0; j--) {
            int x2 = num2.charAt(j) - '0';
            int d = x1 * x2 + carry;
            if (d >= 10) {
                carry = d / 10;
                d = d % 10;
            } else {
                carry = 0;
            }
            product.append(d);
        }
        if(carry > 0){
            product.append(carry);
        }
        result = addStrings(result.toString(), product.reverse().toString());
        tailingZero.append('0');
    }
    return result.toString();
}

public StringBuilder addStrings(String num1, String num2) {
    int carry = 0, i = num1.length() - 1, j = num2.length() - 1;
    StringBuilder sb = new StringBuilder();
    while (i >= 0 || j >= 0) {
        int x1 = i >= 0 ? num1.charAt(i) - '0' : 0;
        int x2 = j >= 0 ? num2.charAt(j) - '0' : 0;
        int d = x1 + x2 + carry;
        if (d >= 10) {
            carry = 1;
            d %= 10;
        } else {
            carry = 0;
        }
        sb.append(d);
        i--;
        j--;
    }
    if (carry == 1) sb.append(1);
    return sb.reverse();
}
```





##### [168. Excel表列名称](https://leetcode-cn.com/problems/excel-sheet-column-title/) [三星]

给定一个正整数，返回它在 Excel 表中相对应的列名称。

```
    1 -> A
    2 -> B
    3 -> C
    ...
    26 -> Z
    27 -> AA
    28 -> AB 
    ...
示例 1:
输入: 1
输出: "A"
示例 2:
输入: 28
输出: "AB"
示例 3:
输入: 701
输出: "ZY"
```

本题属于**进制转换**问题。比如十进制表示一个数，那么就由0~9这10个数字来表示；这里是26进制，按常规应该由0~25这几个数字（或对应的字母）来表示，但是本题的范围1~26，这让我有点转不过弯了。先考虑常规的进制转换问题，假设0~25分别由A~Z表示，那么 5362 应该表示成 HYG，其原理如下：

$$ a = ... x_4×26^3 + x_3×26^2 + x_2×26^1 + x_1×26^0 $$  --> $$ (x_4x_3x_2x_1)_{26} = a_{10}$$

$$ 5362 = 7×26^2 + 24×26^1 + 6×26^0 $$ --> 7->H，24->Y，6->G，因此 5362 -> HYG。  

```java
public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while(n > 0){
        int d = n % 26;
        sb.insert(0,(char)('A'+d));
        n /= 26;
    }
    return sb.toString();
}
```

在上面的代码中，每次取余，相当于求出 $$x_i$$ (i 从小到大) ；而每除一次，就把低位消去。以 5362 为例：

(1) $$ 5362 = 7×26^2 + 24×26^1 + 6×26^0 $$

(2) $$ 5362 / 26 = 7×26^1 + 24×26^0 + 0 $$

(3) $$ (5362 / 26) / 26 = 7×26^0 + 0 + 0 $$

上面是常规的进制转换处理，而本题中的范围变成了1~26，也就是$$x_i$$ 的取值变成了1~26，那么会发生什么不一样呢？以几个例子行说明，表示成上面的原理公式，如下：

$$ 28 = 1×26^1 + 2×26^0 $$  --> AB   (这个例子不够特殊，看不出区别)

$$ 701 = 26×26^1 + 25×26^0 $$ --> ZY  (如果写成 $$ 701 = 1×26^2 + 0×26^1 + 25 × 26^0 $$ 就错了！因为这里限定了$$x_i$$ 的取值是1~26，而不是0~25)

$$ 52 = 1×26^1 + 26×26^0 $$  --> AZ  (同理)

每次取余运算，若$$x_i$$是1~25，都能正确获得对应的值。但是当$$x_i$$为26时，取余结果为0，因此，我们要把这种情况恢复成 26 ；而除法获得结果也和之前不一样，前面说了，常规题目中每除一次会把低位消去，但是这里却不能消去，如下：

$$ 52/26 = 2 = 1×26^0 + 1 $$ 

可以看到，若某位的 $$x_i$$为26时，相除之后在该位上还多出了一个1，并没有消去。为了下一轮计算能得到正确结果，在进行除法运算消除低位前，应该先减去1。

代码如下：

```java
public String convertToTitle(int n) {
    StringBuilder sb = new StringBuilder();
    while(n > 0){
        int d = n % 26;
        if(d == 0){
            d = 26; // 恢复
            n--; // trick here
        }
        sb.insert(0,(char)('A'+d-1)); // 微小的调整
        n /= 26;
    }
    return sb.toString();
}
```



##### [171. Excel表列序号](https://leetcode-cn.com/problems/excel-sheet-column-number/) [一星]

本题是168题的逆过程。很简单就不说了。

```java
public int titleToNumber(String s) {
    int ans = 0, time = 1;
    for(int i=s.length()-1; i>=0;i--){
        ans += (s.charAt(i) - 'A' + 1) * time;
        time *= 26;
    }
    return ans;
}
```



##### [172. 阶乘后的零](https://leetcode-cn.com/problems/factorial-trailing-zeroes/) [五星，有难度]

这题考察什么？——纯数学问题，找规律，归纳总结。

题解参考[这里](https://leetcode-cn.com/problems/factorial-trailing-zeroes/solution/xiang-xi-tong-su-de-si-lu-fen-xi-by-windliang-3/) 。我是想不出来啊。

分析：什么情况下$x_i × x_j × x_k × ...$ 的结果会在末尾出现 0 ？显然，仅在出现 5×2 或以 5, 2 为因子的数相乘时才会出现 0。

```java
public int trailingZeroes(int n) {
    int count = 0;
    while(n > 0){
        count += n/5;
        n /= 5;
    }
    return count;
}
```



##### [202. 快乐数](https://leetcode-cn.com/problems/happy-number/)

编写一个算法来判断一个数是不是“快乐数”。一个“快乐数”定义为：对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和，然后重复这个过程直到这个数变为 1，**也可能是无限循环始终变不到 1**。如果可以变为 1，那么这个数就是快乐数。

```
示例: 
输入: 19
输出: true
解释: 
1^2 + 9^2 = 82
8^2 + 2^2 = 68
6^2 + 8^2 = 100
1^2 + 0^2 + 0^2 = 1
```

由于存在“可能无线循环始终变不到1”的情况，也就是说，判定一个数是快乐数是简单的，一遍遍循环直到变为1就行了；而**关键是要找到return false的条件**。这里看到了题目的标签提到了“哈希表”，由此想到：某个数不是快乐数，在无限循环的过程中意味着会有数重复的出现，于是我们用哈希表标记一下，一旦某个数重复出现了，即可return false。（由于看了标签提示，事实上是作弊了，虽然是easy题，自己不一定想得出来）

```java
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
```



##### [204. 计数质数](https://leetcode-cn.com/problems/count-primes/) [四星，筛法不熟]

计算质数/素数(prime)有两种方法，筛法(最优解)和定义法(最常规)，记住即可。

筛法：具体可见本题的[提示部分](https://leetcode-cn.com/problems/count-primes/)，这里介绍筛法已经很详细了。

```java
// 时间复杂度：O(N * loglogN)
// 空间复杂度：O(N)
public int countPrimes(int n) {
    boolean[] isPrime = new boolean[n];
    for (int i = 2; i < n; i++) {
        isPrime[i] = true;
    }
    // 过滤
    for (int i = 2; i * i < n; i++) {
        if (!isPrime[i]) {
            continue;
        }
        for (int j = i * i; j < n; j += i) {
            isPrime[j] = false;
        }
    }
    int count = 0;
    for (int i = 1; i < n; i++) {
        if (isPrime[i]) count++;
    }
    return count;
}
```

常规方法

```java
// 时间复杂度：O(n^1.5)
// 空间复杂度：O(1)
public int countPrimes(int n) {
    int count = 0;
    for (int i = 1; i < n; i++) {
        if (isPrime(i)) count++;
    }
    return count;
}
private boolean isPrime(int n){
    if(n <= 1) return false;
    for(int i=2; i*i<=n;i++){
        if(n % i == 0) return false;
    }
    return true;
}
```





##### [231. 2的幂](https://leetcode-cn.com/problems/power-of-two/)

给定一个整数，编写一个函数来判断它是否是 2 的幂次方。

分析：如果一个数是2的幂次方，那么它对应的二进制序列中‘1’的个数必定**只含有一个**；而其他数的二进制序列中‘1’的个数肯定不止一个。由此可以通过计算二进制序列中1的个数来判定。这里想到了“海明距离”，“计算1的个数”这两个题目，是一模一样的。

```java
//计算一个十进制数的二进制序列中1的个数
int countOf1(int n){
	int count = 0;
	while(n > 0){
      	count++;
      	n = (n-1) & n;
	}
	return count;
}
// 因此我们可以先计算出给定数值的'1'的个数，继而判断其是否为2的幂
public boolean isPowerOfTwo(int n) {
    int count = 0;
    while(n > 0){
        count++;
        n = (n-1) & n;
    }
    return count == 1 ? true : false;
}
```

不过，想上面这样写还是没有必要，因为已经分析出来了2的幂只有一个'1'，也就是没有必要通过while循环把给定值的'1'的个数都求出来。可以简化成这样：

```java
// 时间复杂度：O(1)
public boolean isPowerOfTwo(int n) {
    if(n <= 0) return false;
    return ((n - 1) & n) == 0;
}
```



##### [326. 3的幂](https://leetcode-cn.com/problems/power-of-three/) [四星]

列了几个3的幂在找对应的二进制序列的规律，结果毫无头绪。看了题解才发现这题考察的压根不是位运算，应该从数学的角度，幂的概念本身着手解题。另外，Int 范围能表示的 3 的幂也就20个，即$3^0, 3^1,3^2,...,3^{19}$ 所以枚举也是个不错的选择啊（狗头~）

事实上还有其他解法，具体可参考[这里](https://leetcode-cn.com/problems/power-of-three/solution/3de-mi-by-leetcode/)，但我觉得价值不大，所以就贴了一个最符合常规思维的。

```java
class Solution {
    public boolean isPowerOfThree(int n) {
        if(n <= 0) return false;
        while (n % 3 == 0){ // 注意不要让0落入这里，否则就死循环了
            n /= 3;
        }
        return n == 1;
    }
}
```





##### [342. 4的幂](https://leetcode-cn.com/problems/power-of-four/)

这一题和判断一个数是否是2的幂那一题很像，因为4的幂一定是2的幂，但反过来就不一定。且二进制序列中有且仅有一个'1'。并且这个'1'只可能出现在偶数位（从低到高的0，2，4...）上。

通过`(num & (num-1)) == 0` 保证有且仅有一个1；通过`(0xaaaaaaaa & num) == 0` 保证1出现在正确的位置上。

```java
class Solution {
    public boolean isPowerOfFour(int num) {
        return num > 0 && (num & (num-1)) == 0 && (0xaaaaaaaa & num) == 0;
    }
}
```





##### [258. 各位相加](https://leetcode-cn.com/problems/add-digits/)

给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。

```
示例:
输入: 38
输出: 2 
解释: 各位相加的过程为：3 + 8 = 11, 1 + 1 = 2。 由于 2 是一位数，所以返回 2。
进阶:
你可以不使用循环或者递归，且在 O(1) 时间复杂度内解决这个问题吗？
```

常规思路不需要多讲，居然还有O(1)的做法，实在是惊叹不已。关于O(1)的做法，我觉得通过找规律比较容易得出。

```
(左边为输入的值，右边为输出的值)
(0,0)
(1,1)  (10,1) (19,1)
(2,2)  (11,2) (20,2)
(3,3)  (12,3)  ...
(4,4)  (13,4)
(5,5)  (14,5)
(6,6)  (15,6)
(7,7)  (16,7)
(8,8)  (17,8)
(9,9)  (18,9)
```

可以看到，最终返回的结果不是任意的，而是周期性的出现1~9（当num=0时除外），由此可以从中找到规律。

```java
// 方法1
public int addDigits(int num) {
    while(num > 9){
        int t = 0;
        while(num > 0){
            t += num % 10;
            num /= 10;
        }
        num = t;
    }
    return num;
}
// 方法2：时间复杂度,O(1).很骚  (参考来的)
public int addDigits(int num) {
    if(num == 0) return 0;
    return num % 9 == 0 ? 9 : num % 9;
    // 或者上面两行代码可以合并成
    // return (num - 1) % 9 + 1;
}

```



##### [223. 矩形面积](https://leetcode-cn.com/problems/rectangle-area/) [五星，看似简单，但是想不出来]

在**二维**平面上计算出两个**由直线构成的**矩形重叠后形成的总面积。每个矩形由其左下顶点和右上顶点坐标表示，如图所示。

![Rectangle Area](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2018/10/22/rectangle_area.png)

```
示例:
输入: -3, 0, 3, 4, 0, -1, 9, 2
输出: 45
说明: 假设矩形面积不会超出 int 的范围。
```

分析：本题看上去毫无思维难度，但是计算起来又好像无从下手，感觉分的类别有很多，导致思绪紊乱。事实上，这里有一个关键的事实，就是在计算交集矩形时：

* 它的上边总是取两个矩形的较低值；
* 它的下边总是取两个矩形的较高值；
* 它的左边总是取两个矩形的较右值；
* 它的右边总是取两个矩形的较左值。

因此，根本无需分类处理，只要考虑不会相交和会相交两种情况即可。此外，为了方便处理，应该固定一个矩形，在这里始终将由(A,B)和(C,D)构成的矩形置为平面的较左侧。

```java
public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    // 确保第一个矩形一定在左侧，这样便于处理
    if (E < A) return computeArea(E, F, G, H, A, B, C, D);
    int totalArea = (C - A) * (D - B) + (G - E) * (H - F);
    // 如果没有交集，直接返回结果。
    if(B>=H || F>=D || E>=C) return totalArea;
    // 有交集，计算覆盖的面积（关键）
    int up = Math.min(D,H);
    int down = Math.max(B,F);
    int left = Math.max(A,E);
    int right = Math.min(C,G);
    int duplicatedArea = (right - left) * (up - down);
    return totalArea - duplicatedArea;
}
```





##### [41. 缺失的第一个正数](https://leetcode-cn.com/problems/first-missing-positive/) [五星]

给定一个未排序的整数数组，找出其中没有出现的最小的正整数。要求算法的时间复杂度应为O(*n*)，并且只能使用**常数级别**的空间。

```
示例 1:
输入: [1,2,0]
输出: 3
示例 2:
输入: [3,4,-1,1]
输出: 2
示例 3:
输入: [7,8,9,11,12]
输出: 1
```

方法1：借助哈希两次遍历，但是这需要O(n)的空间。

```

```



##### [268. 缺失数字](https://leetcode-cn.com/problems/missing-number/) [三星]

Given an array containing n **distinct** numbers taken from 0, 1, 2, ..., n, find the one that is missing from the array. 算法应具有**线性时间复杂度**，且仅使用额外**常数空间**。

```
示例 1:
输入: [3,0,1]
输出: 2

示例 2:
输入: [9,6,4,2,3,5,7,0,1]
输出: 8
```

方法一：数学法

由于已经限定了数组的长度为n，且数组元素为[0,n] 。因此由高斯公式可以计算出这n个数（假设没有缺失的情况下）的和，即：$sum = (1+n)×n / 2$ 。然后再遍历一趟数组，求出真实的和，两者相减即可求出缺失的那个值。

```java
public int missingNumber(int[] nums) {
    int n = nums.length;
    long sum = (1+n) * n / 2;
    for(int num : nums){
        sum -= num;
    }
    return (int)sum;
}
```

但是上面那种方法存在溢出的风险（对于两个 int 型整数相乘要尤为注意溢出风险），看了评论区，可以写成如下这样，一边加一边减，真实绝妙，完美的防止溢出。

```java
public int missingNumber(int[] nums) {
    int sum = 0;
    for(int i = 0; i < nums.length;i++){
        sum += (i+1);
        sum -= nums[i];
    }
    return sum;
}
```

方法二：位运算

熟悉**异或**运算的简单使用。对于任意一个数 a ，`a ^ a = 0, a ^ 0 = a` ，本题使用的就是这两个简单的原理。以示例 nums = [3,0,1] 为例，数组长度为 3 ，也就是说数组的元素只可能是 0,1,2,3 这几个数。

| i       | 0    | 1    | 2    |
| ------- | ---- | ---- | ---- |
| nums[i] | 3    | 0    | 1    |

初始化missNumber = nums.length，也就是3，然后一趟遍历，对于每一个元素，执行 `missNumber ^= i ^ nums[i]` ，也就是：

```
missNumber = 3 ^ (0^3) ^ (1^0) ^ (2^1)   // 由于异或运算具有交换律，因此等价于
           = (3^3) ^ (0^0) ^ (1^1) ^ 2
           = 2
```

代码如下： 

```java
public int missingNumber(int[] nums) {
    int missNumber = nums.length;
    for(int i = 0; i < nums.length; i++){
        missNumber ^= i ^ nums[i];
    }
    return missNumber;
}
```



##### [136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/) [三星]

给定一个**非空**整数数组，**除了某个元素只出现一次以外，其余每个元素均出现两次**。找出那个只出现了一次的元素。要求算法应该具有**线性时间复杂度**，且不使用额外空间来实现。比如，输入: [4,1,2,1,2]，输出: 4。

分析：经过了前面那一题，这一题就显得非常简单了。题目已经说明了”除了某个元素只出现一次以外，其余每个元素均出现两次”，这毫无疑问就是提醒我们用**异或**运算。

```java
public int singleNumber(int[] nums) {
    int ans = nums[0];
    for(int i = 1; i < nums.length; i++){
        ans ^= nums[i];
    }
    return ans;
}
```



##### [137. 只出现一次的数字 II](https://leetcode-cn.com/problems/single-number-ii/) [五星++]

这一题和前一题不同的是，除了某个元素只出现一次以外，其余每个元素均出现了**三次**。

方法一：数学计算法





方法二：位运算

假设给定的数组是：[1 2 6 1 1 2 2 3 3 3]，为了方便说明，将每个元素写成二进制的形式，如下：

```
                      1: 0 0 1
                      2: 0 1 0 
                      6: 1 1 0 
                      1: 0 0 1
                      1: 0 0 1
                      2: 0 1 0
                      2: 0 1 0
                      3: 0 1 1  
                      3: 0 1 1
                      3: 0 1 1
按列统计各个位上1的个数：  1 7 6
根据“规则”改写：          1 1 0 ==> 6 ==> 这就是所求的结果
```

这里的“规则”是：**如果某位上1的个数恰为3的倍数，则将其改写为0；如果不是3的倍数，就改写为1**。从而可以得到数组中只出现一次的那个值。这看起来像是什么骚操作？其实分析起来并不难理解！由于题目已经限定数组中的元素要么出现3次，要么仅出现1次。那么，将所有元素各个位上对应的1加起来，如果其个数恰是3的倍数，说明仅出现1次的那个元素在这一位必定是0；如果不是3的倍数，说明仅出现1次的那个元素在这一位必定是1（正是由于这个1导致了该位1的总个数不被3整除）。根据这样的规则，对统计结果加以改写，即可获得结果。

原理已经明了，问题是——如何编写代码？如何统计各个位上1的个数之和呢？

由于 Java 的 int 型由32位表示，因此第一版代码如下：

```java
// 时间复杂度：O(32*n) = O(n)
// 空间复杂度：O(1)
public int singleNumber(int[] nums) {
    int bitmask = 1, singleNumber = 0;
    for(int i = 1; i <= 32; i++){
        // 统计所有元素第 i 位上 ‘1’ 的个数
        int countOf1 = 0;
        for(int num : nums){
            if((num & bitmask) == bitmask) countOf1++;
        }
        if(countOf1 % 3 != 0) {
            singleNumber += bitmask;
        }
        bitmask = bitmask << 1;
    }
    return singleNumber;
}
```

上面的代码可以通过测试，但是直觉告诉我们，内层的 32 次遍历一定是可以优化的。仔细想想，我们真的有必要统计每一位上 1 的个数吗？不，我们需要知道的不是精确的个数，而是该位上 1 的个数**是否被3整数**！





##### [260. 只出现一次的数字 III](https://leetcode-cn.com/problems/single-number-iii/) [五星]

给定一个整数数组 nums，其中**恰好有两个元素只出现一次，其余所有元素均出现两次**。 找出只出现一次的那两个元素（结果输出的顺序并不重要）。要求算法应该具有**线性时间复杂度**，且使用**常数空间**复杂度来实现。比如，输入: [1,2,1,3,2,5]，输出: [3,5]

分析：根据之前的想法，采取异或运算。由于异或运算能够消除偶数个数的重复元素，因此，在一趟遍历之后，能够保留两个不同元素 x 和 y 的异或结果。

```
int mask = 0;
for(int num : nums){
	mask ^= num;
}
// 此时，mask = x^y;
```

所以，我们要设法从 mask 中分离出 x 和 y。可以直接从 mask 中提取 x 和 y 吗？答案是不能，但是我们可以利用 mask 作为标记从原数组中分离出 x 和 y。在这里，需要知道位运算中的一个应用：令 **diff = mask & (-mask) ，diff 能够保留 mask 中最右边的那个 1**。（当作定理来记住，是位运算中很基础的工具）

```
 mask: 0110
-mask = ~mask + 1 = 1010 (一个数的相反数就是取反加1)
 mask & (-mask) = 0110 & 1010 = 0010（它保留了mask最右边的那个1）
```

而这个 1，不是来自 x，就是来自 y。

![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMubGVldGNvZGUtY24uY29tL0ZpZ3VyZXMvMjYwL2lzb2xhdGUyX25ldy5wbmc?x-oss-process=image/format,png)

代码：

```java
public int[] singleNumber(int[] nums) {
    int mask = 0;
    for(int num : nums) 
      	mask ^= num;
    int diff = mask & (-mask);
    int x = 0;
    for(int num : nums) {
        // 对于 x 和 y，利用 (num & diff) 的结果是否为 0 来加以区分
        // 而对于数组中的其他元素，由于他们都出现两次，因此会消去，不用考虑
        if((num & diff) != 0) x ^= num;
    }
    return new int[]{x, x^mask};
}
```





##### [166. 分数到小数](https://leetcode-cn.com/problems/fraction-to-recurring-decimal/) [四星]

给定两个整数，分别表示分数的分子 numerator 和分母 denominator，以字符串形式返回小数。如果小数部分为循环小数，则将循环的部分括在括号内。比如，numerator = 2, denominator = 3，结果输出`0.(6)` 。

分析：由于本题条件没有限定说只能用 32 位的整数，因此可以先转化为 long ，以防止溢出。比如，`-2147483648 ÷ -1 = 2147483648 ` 这种情况。核心代码的过程就是模拟数学的“长除法”。参考[这里](https://leetcode-cn.com/problems/fraction-to-recurring-decimal/solution/fen-shu-dao-xiao-shu-by-leetcode/)。

```java
public String fractionToDecimal(int numerator, int denominator) {
    StringBuilder result = new StringBuilder();
    // 确定符号
    if ((numerator < 0 && denominator > 0) || (numerator > 0 && denominator < 0)) {
        result.append('-');
    }

    // 边界情况
    if (numerator == 0) return "0";
    if (denominator == 0) return "error";

    long n = Math.abs((long) numerator);
    long d = Math.abs((long) denominator);
    // 整数部分可以直接计算得到
    result.append(n / d);
    long remainder = n % d;// 从余数部分开始处理
    if(remainder == 0) return result.toString(); // 整除

    result.append('.');
    Map<Long, Integer> map = new HashMap<>();
    while (remainder != 0){
        if(map.containsKey(remainder)){
            result.insert(map.get(remainder),"(");
            result.append(')');
            break;
        }
        map.put(remainder,result.length());
        remainder *= 10;
        result.append(remainder / d);
        remainder %= d;
    }
    return result.toString();
}
```



##### [149. 直线上最多的点数](https://leetcode-cn.com/problems/max-points-on-a-line/) [五星]

给定一个二维平面，平面上有 *n* 个点，求最多有多少个点在同一条直线上。比如：

```java
输入: [[1,1],[3,2],[5,3],[4,1],[2,3],[1,4]]
输出: 4
解释:
^
|
|  o
|     o        o
|        o
|  o        o
+------------------->
0  1  2  3  4  5  6
```

分析：首先，在数学上如何表示一条直线，一般有截距式、点斜式。在这里采用点斜式的方法，也就是，由**一点$(x_0,y_0)$**和**斜率 $k$** 来确定一条直线。**所有与点$(x_0,y_0)$ 构成相同斜率的点一定都在同一条直线上**。因此解决本题的大致思路是：先固定一点，作为基准点，再遍历剩余的其他点，分别计算其他点与基准点构成的斜率，并由HashMap来记录斜率与对应点的个数。

关于斜率的处理，这里涉及到一个精度的问题，由于保存为浮点数会有精度损失，因此本题中把斜率保存为分数（化成最简）形式，用`String`类型来保存，为了把分子与分母区分开，可以令`String key = y + "/" + x` 。

此外，由测试用例发现本题还可以包含重叠的点，比如给出的points数组是：`{{0, 0}, {1, 1}, {0, 0}}` 或 `{{1, 1}, {1, 1}, {1, 1}}` 。为了处理这种情况，对于每个固定点`points[i]`，统计与其重合的点的个数。

代码：

```java
// 时间复杂度：O(n^2)
// 空间复杂度：O(n)
public int maxPoints(int[][] points) {
    if (points.length < 3) return points.length;

    int maxPoints = 0;
    // 固定points[i]作为基准点，统计其余点与该点构成的斜率
    for (int i = 0; i < points.length; i++) {
        // <斜率, 该斜率点的个数>
        Map<String, Integer> map = new HashMap<>();
        // 与point[i]重合的点的个数
        int duplicate = 0;
        // 记录所有经过点points[i]的直线中的最大点数
        int max = 0;
        for (int j = i + 1; j < points.length; j++) {
            // 两点的差值，用于计算斜率
            int y = points[j][1] - points[i][1];
            int x = points[j][0] - points[i][0];
            // 节点points[j]与节点points[i]重合
            if (y == 0 && x == 0) {
                duplicate++;
                continue;
            }

            int gcd = gcd(y, x);
            y /= gcd;
            x /= gcd;
            String key = y + "/" + x; // 斜率
            map.put(key, map.getOrDefault(key, 0) + 1);
            max = Math.max(max, map.get(key));
        }
        // 由于duplicate表示与points[i]重合的点
        // 因此，duplicate应该加到以points[i]为基准点的直线上
        maxPoints = Math.max(maxPoints, max + duplicate + 1); // +1 表示的是节点points[i]本身
    }
    return maxPoints;
}

public int gcd(int a, int b) {
    if (b == 0) return a;
    return gcd(b, a % b);
}
```



##### [470. Implement Rand10() Using Rand7()](https://leetcode-cn.com/problems/implement-rand10-using-rand7/) [五星]

已有方法 `rand7` 可生成 1 到 7 范围内的均匀随机整数，试写一个方法 `rand10` 生成 1 到 10 范围内的均匀随机整数。

分析：参见自己写的[题解](https://leetcode-cn.com/problems/implement-rand10-using-rand7/solution/cong-zui-ji-chu-de-jiang-qi-ru-he-zuo-dao-jun-yun-/)。

```java
class Solution extends SolBase {
    // 方法1：
    // public int rand10() {
    //     while(true) {
    //         int num = (rand7() - 1) * 7 + rand7();
    //         if(num <= 40) return num % 10 + 1;
    //     }
    // }
    
    // 优化后的版本：
    public int rand10() {
        while(true) {
            int a = rand7();
            int b = rand7();
            int num = (a-1)*7 + b; // rand 49
            if(num <= 40) return num % 10 + 1; // 拒绝采样
            
            a = num - 40; // rand 9
            b = rand7();
            num = (a-1)*7 + b; // rand 63
            if(num <= 60) return num % 10 + 1;
            
            a = num - 60; // rand 3
            b = rand7();
            num = (a-1)*7 + b; // rand 21
            if(num <= 20) return num % 10 + 1;
        }
    }
}
```







---

### 数组问题

##### [169. 多数元素](https://leetcode-cn.com/problems/majority-element/) [五星，摩尔投票算法]

给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数大于 **⌊ n/2 ⌋** 的元素。假设数组是非空的，并且给定的数组总是存在多数元素。

方法一：排序法。先对数组进行排序，通过下图进行说明。![image.png](https://pic.leetcode-cn.com/a70cb9316157ecd7eeffe7900d3ca83849079824964e8a0aaefbcffd4040f175-image.png)

根据题目给定的限定条件，如果众数元素是较小值，则处于上图的下划线位置；如果众数元素是较大值，则处于上图的上划线位置。不管元素个数是奇数还是偶数，nums[length/2] 都是众数。这种方法的实现非常简单，时间复杂度和空间复杂度取决于排序算法本身，一般来说是O(n*log n) 和 O(1)。

```java
public int majorityElement(int[] nums) {
    Arrays.sort(nums);
    return nums[nums.length/2];
}
```

方法二：借助哈希表

方法三：摩尔投票法（Boyer-Moore 算法）

时间复杂度O(n)，空间复杂度O(1)

```java
public int majorityElement(int[] nums) {
    int candidate = 0, count = 0;
    // pairing phase
    for(int num : nums) {
        if(count == 0) {
            candidate = num;
        }
        if(candidate == num) count++;
        else count--;
    }
    // counting phase
    count = 0;
    for(int num : nums) {
        if(num == candidate) count++;
    }
    if(count > nums.length/2) return candidate;
    return -1;
}
```



##### [229. 求众数 II](https://leetcode-cn.com/problems/majority-element-ii/) [暂时不理解，没做，2020/02/14]

给定一个大小为 n 的数组，找出其中所有出现超过 `⌊ n/3 ⌋` 次的元素。要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。

分析：本题考察摩尔投票算法的变形。

```

```



### 位运算

与(&)、或(|)、异或(^)、取反(~)、左移(<<)、右移(>>)、**无符号右移(>>>)**。具体参考这里<https://www.cnblogs.com/SunArmy/p/9837348.html>

##### [461. 汉明距离](https://leetcode-cn.com/problems/hamming-distance/)

```java
class Solution {
    public int hammingDistance(int x, int y) {
        int n = x ^ y;
        int count = 0;
        while (n > 0){
            count++;
            n = (n-1) & n;
        }
        return count;
    }
}
```



##### [201. 数字范围按位与](https://leetcode-cn.com/problems/bitwise-and-of-numbers-range/) [五星，多种解法，非常精彩]

给定范围 [m, n]，其中 0 <= m <= n <= 2147483647，返回此范围内所有数字的按位与（包含 m, n 两端点）。

分析：本题的题干非常简单，首先根据直觉，写出如下代码。

```java
public int rangeBitwiseAnd(int m, int n) {
        int res = m;
        for (int i = m; i <= n; i++) {
            res &= i;
            if (res == 0) break; 
        }
        return res;
    }
```

我知道这样肯定会超时，但提交后出现一个错误。即当`m=2147483646, n=2147483647` 的情况。事实上，上面的代码并不是安全的，而是会发生溢出。当`i`等于 2147483647 时，会进入循环，执行与操作后`i`继续++，按理说就应该退出了，但是别忘了，由于是`int` 类型数值，2147483647 + 1并不等于 2147483648，而是发生了溢出，变成了负数，因此会继续进入for循环，从而造成异常情况。因此，上面的代码应该修改成如下：

```java
// 方法1
public int rangeBitwiseAnd(int m, int n) {
  	int res = m;
  	for (int i = m; i <= n; i++) {
    	res &= i;
    	if (res == 0 || i == Integer.MAX_VALUE) break;
  	}
  	return res;
}
```

上面的代码至少是正确了，但是，由于`&` 操作具有交换律，即 `a & b` 等价于 `b & a` 。因此，我们也可以从大到小逐个相与，从而避免了++溢出的情况。

```java
// 方法2
public int rangeBitwiseAnd(int m, int n) {
    int res = n;
    for (int i = n; i >= m; i--) {
        res &= i;
        if (res == 0) break;
    }
    return res;
}
```

上面的做法都是超时的，记录一下只是为了提醒自己**注意在循环中++操作可能会发生溢出**的情况。下面才是本题正解。

首先，我们必须明确`&`操作的性质，也就是某一位上但凡出现了一次0，那么与任何其他数相与，这一位上的结果就都是0了。基于这个认识，对于[m,m+1,...,n-1,n]这些数，从二进制序列的角度观察它们，看看是如何变化的？

![leetcode201](D:\workspace\algorithm\img\leetcode201.png)

可以看到，红色表示的高位部分在这一范围内并没有变化，而黑色表示的低位部分则发生了变化。对于任意两个数，它们的二进制序列的最小公共前缀部分是不变的，也就公共的高位部分；而低位则在变化，根据与操作的性质，低位部分变成了0，最终保留最小公共前缀部分。（思考一下，对一个数加1，无非就是把这个数表示的最低位要么从0变成1，要么从1变成了0，并向上进一位。总的来说，就是咱先对低位部分所能表示的都给了，等到低位部分已经没法继续表示那么多数了，再向前进一位。）由此可以写出代码如下：

```java
// 方法3，时间复杂度是 O(m和n的非公共低位部分的位数) <= O(32), 因此是常数时间复杂度。
public int rangeBitwiseAnd(int m, int n) {
    int bitCount = 0; // 记录右移了多少位，也就是上面说的低位部分
    // 每次右移一位，直到两个数相等
    // 从二进制序列的角度来看，就是寻找两者的最小公共前缀
    while (m != n){
        m >>= 1;
        n >>= 1;
        bitCount++;
    }
    return m << bitCount;
}
```

看别人的题解，发现还有另外一种方法。方法3是m和n每次右移一位直到找到两者的最小公共前缀，可是有没有更快的方法？如果你已经做过了“汉明距离”和“1的个数”这两题，应该能有所启发。这里就直接说结论了，即每执行一次 `n & (n-1)` ，就能把`n`对应的二进制序列最右边的1移除。比如：

```
n = 29            --> 11101
n = n&(n-1) = 28  --> 11100
n = n&(n-1) = 24  --> 11000
```

换句话说，为了找到m和n的最小公共前缀（为了方便叙述，这里假定高位的相同部分占x位，低位的不相同部分占y位），没必要通过右移y位来达到这一目的，而是每次移除n最低位的1，当n小于m或者n等于m的时候退出循环，此时对应的n就是两者的最小公共前缀。利用这种方法，**至多**右移y位，而不需要每次都右移y位。

```java
// 方法4：
public int rangeBitwiseAnd(int m, int n) {
    while (n > m){ // 注意跳出循环的条件
        n &= (n-1);
    }
    return n;
}
```



##### [187. 重复的DNA序列](https://leetcode-cn.com/problems/repeated-dna-sequences/) [五星]

所有 DNA 都由一系列缩写为 A，C，G 和 T 的核苷酸组成，例如：“ACGAATTCCG”。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。编写一个函数来查找 DNA 分子中所有出现超过一次的 10 个字母长的序列（子串）。比如：

```
输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
输出：["AAAAACCCCC", "CCCCCAAAAA"]
```

分析：这题的常规做法并不是采用位运算，而是采用哈希。但是能够想到位运算就是锦上添花的加分项。

```java
// 哈希做法，时间复杂度为O(n)
class Solution {
    public List<String> findRepeatedDnaSequences(String s) {
        Set<String> result = new HashSet<>(); //HashSet去重
        Set<String> set = new HashSet<>();

        for (int i = 0; i <= s.length() - 10; i++) {
            String sub = s.substring(i, i + 10);
            if(set.contains(sub)){
                result.add(sub);
            }else {
                set.add(sub);
            }
        }
        return new ArrayList<>(result);
    }
}
```

位运算做法。为什么会想到位运算的做法？（参考[这里]()）

观察常规做法的遍历过程，为了保证子串的长度始终为10，遍历时每次向右移动一个位置，就**增添一个新的字符到原子串的末位，同时丢弃原子串的首个字符，而中间的9个元素根本没变过**。考虑到本题的特殊性（即字符串仅有A/C/G/T这4个字符组成），因此，想到能否把这4个字符转化成二进制序列（只需要2 bits 就可以区分出这4个字符了），从而把长度为10的字符串sub变为20位表示的一个数key。这样转化的好处在于，每次向右移动一个字符时，只需要把key左移2个bit，再加上新的字符代表的二进制序列即可。

```java
class Solution {
    // 采用位运算做法~，时间复杂度也是O(n)
    public List<String> findRepeatedDnaSequences(String s) {
        if(s.length() < 10) return new ArrayList<>();

        Set<String> result = new HashSet<>();
        Set<Integer> set = new HashSet<>();

        char[] chars = s.toCharArray();
        // 首先处理第一个长为10个字符的子串
        int key = 0;
        for(int i = 0; i < 10; i++){
            key <<= 2;
            key |= charToInt(chars[i]); // 加上第i个字符对应的二进制序列
        }
        set.add(key);

        for(int i = 10; i < s.length(); i++){
            // 更新key：即原来的key左移2位，并加上当前这个字符
            key <<= 2;
            key |= charToInt(chars[i]);
            key &= 0xfffff; // 由于Java的int有32位，而我们只需要低20位
            if(set.contains(key)){
                result.add(s.substring(i-9, i+1));
            }else {
                set.add(key);
            }
        }
        return new ArrayList<>(result);
    }

    private int charToInt(char c){
        switch (c){
            case 'A': return 0;
            case 'G': return 1;
            case 'C': return 2;
            case 'T': return 3;
        }
        return -1;  // never happened
    }
}
```



##### [190. 颠倒二进制位](https://leetcode-cn.com/problems/reverse-bits/) [五星，第一次遇见]

颠倒给定的 32 位无符号整数的二进制位。参考[这里](https://leetcode-cn.com/problems/reverse-bits/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-4-9/) 。

总的思路就是取出原数字的最低位，加到新数字的最高位上，这个过程循环32次就好了。取出数字的最低位通过`n&1`来做到，然后右移一位，保证下一次取到的数也是正确的。而把“新数字加到最高位”上，我们通过先把数字a左移一位，然后a |= x，这样就把x的最低位加到a上了，并且该位又是下一次循环的高位。

```java
public class Solution {
    // you need treat n as an unsigned value
    public int reverseBits(int n) {
        int res = 0, bitCount = 0;
        while (bitCount < 32) {
            res <<= 1; // 
            res |= (n & 1); // 去除原来n的最低为，加到res上
            n >>>= 1; // >>> 为无符号右移
            bitCount++;
        }
        return res;
    }
}
```





##### [371. 两整数之和](https://leetcode-cn.com/problems/sum-of-two-integers/) [五星++，没搞懂]

**不使用**运算符 `+` 和 `-` ，计算两整数 `a` 、`b` 之和。

分析：

```java

```



##### [389. 找不同](https://leetcode-cn.com/problems/find-the-difference/)

给定两个字符串 s 和 t，它们只包含小写字母。字符串 **t** 由字符串 **s** 随机重排，然后在随机位置添加**一个**字母。请找出在 t 中被添加的字母。比如，输入：s = "abcd"，t = "abcde"；输出：e。

分析：

把 s 和 t 的字符放在一起看，处理新添加的字符外，其余字符均成对出现，这不就是[136. 只出现一次的数字](https://leetcode-cn.com/problems/single-number/) 题嘛，一摸一样。异或运算找single one

```java
// 方法1：借助哈希，两次遍历
public char findTheDifference(String s, String t) {
    int[] map = new int[26];
    for (char c : s.toCharArray())
        map[c - 'a']++;
    for (char c : t.toCharArray()) {
        map[c - 'a']--;
        if (map[c - 'a'] < 0) return c;
    }
    return 'a';//never happens
}


// 方法2：太秀了（当然存在溢出的风险，但是这个思路值得学习）
public char findTheDifference(String s, String t) {
    int sum = 0;
    for (char c : t.toCharArray())
        sum += c - 'a';
    for (char c : s.toCharArray())
        sum -= c - 'a';
    return (char) (sum + 'a');
}

// 方法3：位操作
public char findTheDifference(String s, String t) {
    int diff = 0;
    for (char c : t.toCharArray())
        diff ^= c - 'a';
    for (char c : s.toCharArray())
        diff ^= c - 'a';
    return (char)(diff + 'a');
}

// 直接用 char 进行位运算也行
public char findTheDifference(String s, String t) {
    char diff = 0;
    for (char c : t.toCharArray())
        diff ^= c;
    for (char c : s.toCharArray())
        diff ^= c;
    return diff;
}
```

