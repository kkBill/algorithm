### 字符串处理问题汇总

题目列表：



---



##### [165. 比较版本号](https://leetcode-cn.com/problems/compare-version-numbers/) [二星]

题目给了一大堆的说明，但是好在输入都是比较规范的。已知：

1. 版本字符串由以点 （`.`） 分隔的数字字符串组成。这个数字字符串**可能**有前导零。
2. 版本字符串不以点开始或结束，并且其中不会有两个连续的点。

要我们比较两个版本号的大小。

这题看起来好像很复杂的样子，但是非常简单。首先通过`split()`方法分隔字符串，这里要稍微注意一下写法，即分隔的参数是`\\.`，而不是`.`或`\.`，在脱离了IDE写白板代码的时候要注意。另外一个是，题目中说了，数字可能包含leading zero，但是使用`Integer.parseInt(String s)`方法的时候并不需要担心这个问题。

```java
class Solution {
    public int compareVersion(String version1, String version2) {
        String[] s1 = version1.split("\\.");
        String[] s2 = version2.split("\\.");
        for(int i = 0; i < s1.length || i < s2.length; i++) {
            int a1 = i < s1.length ? Integer.parseInt(s1[i]) : 0;
            int a2 = i < s2.length ? Integer.parseInt(s2[i]) : 0;
            if(a1 != a2) return a1 > a2 ? 1 : -1;
        }
        return 0;
    }
}
```



