#### [10. Regular Expression Matching](https://leetcode-cn.com/problems/regular-expression-matching/)

给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。

* **'.' Matches any single character.**
* **'*' Matches zero or more of the preceding element.**

所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。

说明:

* s 可能为空，且只包含从 a-z 的小写字母。
* p 可能为空，且只包含从 a-z 的小写字母，以及字符 . 和 *。

```
示例 1:
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。

示例 2:
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。

示例 3:
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。

示例 4:
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。

示例 5:
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```

方法1：递归法

首先验证两个简单的字符串是否相等，我们会怎么考虑？这太简单了，很快就能写出来。

```java
public boolean isMatch(String s, String p) {
    // 首先检查长度，如果两个字符串长度不等，立刻返回false
    if (s.length() != p.length()) return false;
    // 如果长度相同，再逐个进行比较
    for (int i = 0; i < s.length(); i++) {
        if (s.charAt(i) != p.charAt(i)) return false;
    }
    return true;
}
```

我们尝试把上面的问题改写成递归的形式，该怎么考虑呢？这也不难，首先，当然是考虑当前字符是否相等，如果相等，就可以递归的考虑子问题；否则，直接返回false。如下：

```java
public boolean isMatch(String s, String p) {
    if (s.length() != p.length()) return false;
    if (s.length() == 0) return true; // s 和 p 均为空字符串
    boolean firstMatch = s.charAt(0) == p.charAt(0);
    return firstMatch && isMatch(s.substring(1), p.substring(1));
}
```

现在，我们考虑在模式串p中加入`.` ，由于`.`可以匹配任意单个字符，因此处理起来也很简单。首先，还是判断两者的长度，因为`.`只能对应单个字符，所以两者的长度必须相等；其次，如果p中当前字符是`.`，那么意味着它能和s中的任意字符匹配。稍作改动如下：

```java
public boolean isMatch(String s, String p) {
    if (s.length() != p.length()) return false;
    if (s.length() == 0) return true; // s 和 p 均为空字符串
    boolean firstMatch = (s.charAt(0) == p.charAt(0)) || (p.charAt(0) == '.');
    return firstMatch && isMatch(s.substring(1), p.substring(1));
}
```

最后，考虑在模式串p中加入`*`，由于`*`可以匹配0个或多个字符，也就是说，s和p的长度即使不相等，依旧可能是匹配的，因此我们需要重新考虑两者的长度关系：

* 如果p为空，当且仅当s也为空的时候，两者是匹配的；除此之外，都不匹配。
* 如果p不为空，情况会比较复杂
  * 如果s为空，两者一定是不匹配的吗？——No！比如，`s="", p="a*b*"`，根据题意，两者是匹配的，重复一遍：`*`Matches zero or more of the preceding element. 在这里，令`*`匹配0个前一个元素，即`a*`视为一个整体，相当于空字符串，`b*`也视为一个整体，相当于空字符串，从而p就表示为空字符串，从而s与p匹配。
  * 如果s不为空，这种情况是最普遍的情况，需要我们处理。

总结来说，如果p不为空，则不管s是否为空，都无法通过简单的判断就返回结果，而需要分析处理。这也就是本题的难点所在。

对于模式串p中含有`*`的情况，需要明确的是，它只可能跟在某个正常字符('a'~'z')或`.`的后面，比如"ab**"是不允许的。

如果p的长度大于2，并且第2个元素恰是`*`，而`*`可以表示0个或多个在它之前的那个元素，这该如何考虑呢？递归的精髓在于，只考虑当前应该考虑的情况，至于之后的多种可能，就交给递归完成。在这里，只考虑`*`匹配0或1个前一个元素的情况。

```java
public boolean isMatch(String s, String p) {
    // p 为空，当且仅当s也为空的时候，两者是匹配的；除此之外，都不匹配。
    if(p.length() == 0) return s.length() == 0;
    // p 不为空
    boolean firstMatch = s.length() > 0 && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.');
    if(p.length() >= 2 && p.charAt(1) == '*') {
        // isMatch(s, p.substring(2)) 
        // --> 表示 '*' 匹配0个在其之前的那个元素，比如s="aab"和p="c*a*b"这种情况
        // (firstMatch && isMatch(s.substring(1), p)) 
        // --> 表示 '*' 匹配1个在其之前的那个元素（✨）
        return isMatch(s, p.substring(2)) || (firstMatch && isMatch(s.substring(1), p));
    }else {
        return firstMatch && isMatch(s.substring(1), p.substring(1));
    }
}
```

上面的代码已经能AC了，但是效率较低，我们可以通过添加备忘录来减少不必要的计算。

```java
class Solution {
    public boolean isMatch(String s, String p) {
        Boolean[][] memo = new Boolean[s.length()+1][p.length()+1];
        // memo[si][pi] 表示s[0...si]与p[0...pi]是否匹配
        return helper(s, 0, p, 0, memo);
    }
    public boolean helper(String s, int si, String p, int pi, Boolean[][] memo) {
        // p 为空，当且仅当s也为空的时候，两者是匹配的；除此之外，都不匹配。
        if(p.length() == pi) return s.length() == si;
        
        // 先检查备忘录
        if(memo[si][pi] != null) return memo[si][pi];
        
        // p 不为空
        boolean firstMatch = si < s.length() && 
                             (s.charAt(si) == p.charAt(pi) || p.charAt(pi) == '.');
        if(pi <= p.length()-2 && p.charAt(pi+1) == '*') {
            memo[si][pi] = helper(s, si, p, pi+2, memo) || 
                           (firstMatch && helper(s, si+1, p, pi, memo));
            return memo[si][pi];
        }else {
            memo[si][pi] = firstMatch && helper(s, si+1, p, pi+1, memo);
            return memo[si][pi];
        }
    }
}
```



方法2：动态规划



```

```

