### 数组问题汇总

数组类问题一般以一维或二维数组的形式作为载体，总的来说不涉及太难的算法，但是也比较考察编码能力。对于一维数组的类型，可能会结合贪心、动态规划的算法思想；而对于二维矩阵的题，可能涉及BFS或DFS的搜索思想，也可能是纯粹的场景模拟题，不多练习很难一遍bug free写出来。

题目列表

1. [6. Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/) [四星]
2. [14. 最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/) [三星]
3. [31. 下一个排列](https://leetcode-cn.com/problems/next-permutation/) [五星++]
4. [36. 有效的数独](https://leetcode-cn.com/problems/valid-sudoku/) [五星]
5. [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) [五星++]
6. [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/) [五星++]
7. [48. 旋转图像](https://leetcode-cn.com/problems/rotate-image/)
8. [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) [五星，多种方法]
9. [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/)
10. [59. 螺旋矩阵 II](https://leetcode-cn.com/problems/spiral-matrix-ii/)
11. [66. 加一](https://leetcode-cn.com/problems/plus-one/)
12. [73. 矩阵置零](https://leetcode-cn.com/problems/set-matrix-zeroes/)
13. [118. 杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/) 
14. [119. 杨辉三角 II](https://leetcode-cn.com/problems/pascals-triangle-ii/) 
15. [134. 加油站](https://leetcode-cn.com/problems/gas-station/) [五星]
16. [189. 旋转数组](https://leetcode-cn.com/problems/rotate-array/)
17. [217. 存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/)
18. [220. 存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) [五星+++]
19. [228. 汇总区间](https://leetcode-cn.com/problems/summary-ranges/) [三星]
20. [205. 同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/) [二星]
21. [290. 单词规律](https://leetcode-cn.com/problems/word-pattern/) [二星]
22. [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) [一星]
23. [58. 最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/) [一星，字符串的简单处理]




##### [6. Z 字形变换](https://leetcode-cn.com/problems/zigzag-conversion/) [四星]

将一个给定字符串根据给定的行数，以从上往下、从左到右进行 Z 字形排列。

```
比如输入字符串为 "LEETCODEISHIRING" 行数为 3 时，排列如下：
L   C   I   R
E T O E S I I G
E   D   H   N
之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："LCIRETOESIIGEDHN"。

同样的输入，当行数变为4时，排列如下：
L     D     R
E   O E   I I
E C   I H   N
T     S     G
```

分析：虽然题目要求最后的输出是一个String字符串，而不是一个二维矩阵，但是第一个想法是先根据“从上往下、从左到右进行 Z 字形排列”模拟出正确的排列效果，再逐行读取非空字符拼接出最终结果。

方法一：如下图所示，以绿色部分作为一个单元来考虑，剩余的蓝色部分（如果有的话）单独考虑。这种做法的时间复杂度是$O(mn)$，空间复杂度是$O(mn)$。 

![leetcode6](D:\workspace\algorithm\img\leetcode6.png)

```java
class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1 || numRows == s.length()) return s;
        // 确定输出矩阵的列数numCols
        int n = s.length();
        int unit = numRows + numRows - 2; // 一个单元含有unit个字符
        int unitCount = n / unit;         // 可划分出来的单元个数
        int remainder = n % unit;
        int numCols = unitCount * (numRows - 1); // 每个单元占(numRows-1)列
        if(remainder != 0)
            numCols += remainder <= numRows ? 1 : remainder - numRows + 1;
        char[][] matrix = new char[numRows][numCols];
		
      	// 核心：模拟排列
        char[] chars = s.toCharArray();
        int i = 0, row = 0, col = 0;
        while (i < n) {
            // 从上至下
            while (i < n && row < numRows) {
                matrix[row][col] = chars[i];
                row++;
                i++;
            }
            row -= 2;
            col++;
            // 从左下角至右上角
            while (i < n && row > 0) {
                matrix[row][col] = chars[i];
                row--;
                col++;
                i++;
            }
        }
	   // 将二维矩阵的排列转化成字符串
        StringBuilder res = new StringBuilder();
        for (char[] charArray : matrix) {
            for (char c : charArray) {
                if(c != 0){ // 不要写成 c != '' 或 c != ' '
                    res.append(c);
                }
            }
        }
        return res.toString();
    }
}
```

方法二：参考[这里](https://leetcode-cn.com/problems/zigzag-conversion/solution/zzi-xing-bian-huan-by-jyd/) 。

注意如下这样的case，可以直接返回。

```
s = "A", numRows = 2
s = "ABCD", numRows = 6
```

这个方法最精彩的地方在于利用flag来更新行索引，每到折返处令`flag = -flag;` 。这种方法的时间复杂度为O(n)，只需要一趟遍历字符串即可；空间复杂度是O(n)，即存放字符串所需要的空间。

```java
class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1 || numRows >= s.length()) return s;
        List<StringBuilder> matrix = new ArrayList<>();
        for(int i = 0; i < numRows; i++){
            matrix.add(new StringBuilder());
        }
        int flag = -1, r = 0;
        for (char c : s.toCharArray()) {
            matrix.get(r).append(c);
            if(r == 0 || r == numRows-1) flag = -flag; // 折返
            r += flag;
        }
        StringBuilder res = new StringBuilder();
        for(StringBuilder row : matrix){
            res.append(row);
        }
        return res.toString();
    }
}
```



##### [14. 最长公共前缀](https://leetcode-cn.com/problems/longest-common-prefix/) [三星]

编写一个函数来查找字符串数组中的最长公共前缀。如果不存在公共前缀，返回空字符串 `""`。比如：

```
输入: ["flower","flow","flight"]
输出: "fl"

输入: ["dog","racecar","car"]
输出: ""
解释: 输入不存在公共前缀。
```

已知所有输入只包含小写字母 a-z 。

分析：简单题

```java
class Solution {
    // 时间复杂度为O(n)，注意这里的n是字符串数组所有字符的长度之和
    public String longestCommonPrefix(String[] strs) {
        if(strs == null || strs.length == 0) return "";
        String base = strs[0];
        // 比较所有单词的第i个字符是否相等
        // 这里需要注意的是，可能有些单词的长度小于base的长度，
        // 因此在加一个判断条件"i == word.length()"，不然会导致 word.charAt(i) 越界
        for(int i = 0; i < base.length(); i++) {
            char c = base.charAt(i);
            for(String word : strs) { // O(n)
                if(i == word.length() || word.charAt(i) != c){
                    return base.substring(0,i);
                }
            }
        }
        return base;
    }
}
```



##### [31. 下一个排列](https://leetcode-cn.com/problems/next-permutation/) [五星]

将给定数字序列重新排列成**字典序中下一个更大的排列**。如果不存在下一个更大的排列，则将数字重新排列成最小的排列（即升序排列）。必须原地修改，只允许使用额外常数空间。

首先要对“下一个排列”的定义搞清楚，假设原序列是123，那么它的下一个排列是这样变化的：

```
123 -> 132 -> 213 -> 231 -> 312 -> 321 -> 123 -> ...
```

分析：我也不知道为什么。。。这是维基百科给出的求next permutation的算法说明。

![1556953035922.png](https://pic.leetcode-cn.com/4169e8e0c8b4d71d4d32b4f50b09a57c0ea951cb4bdbd16a785d5847959e261f-1556953035922.png)

代码如下：

```java
// 时间复杂度：O(n)
class Solution {
    public void nextPermutation(int[] nums) {
        // find largest k that nums[k] < nums[k+1]
        int k = -1;
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] < nums[i + 1]) k = i;
        }
        // if no such a k exist, then reverse the whole array
        if (k == -1) {
            reverse(nums, 0, nums.length - 1);
            return;
        }

        // find largest l that nums[l] > nums[k]
        int l = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > nums[k]) l = i;
        }
        // swap nums[k] and nums[l]
        swap(nums, k, l);

        // reverse nums[k+1:]
        if (k + 1 < nums.length - 1) {
            reverse(nums, k + 1, nums.length - 1);
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int left, int right) {
        while (left < right) {
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
```



##### [36. 有效的数独](https://leetcode-cn.com/problems/valid-sudoku/) [五星]

判断一个 9x9 的数独是否有效。只需要根据以下规则，验证已经填入的数字是否有效即可。

* 数字 1-9 在每一行只能出现一次。
* 数字 1-9 在每一列只能出现一次。
* 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。

![img](https://upload.wikimedia.org/wikipedia/commons/thumb/f/ff/Sudoku-by-L2G-20050714.svg/250px-Sudoku-by-L2G-20050714.svg.png)

这里保证输入的矩阵一定是9×9的，且仅包含数字和`.` ，其中`.` 表示空白。

分析：本题只是一个medium题，其实非常简单，但是第一遍做的时候对二维数组的运用容易犯迷糊。

这里使用3个额外的二维数组来表示某个数digit是否在某一行/列/块内出现过。比如，令`int[][] rows = new int[9][10];` ，当`rows[0][9]==1`表示数字9已经在第0行出现过了，若`rows[0][9]==1`则表示尚未出现过。因此，在遍历数据矩阵的时候，分别判断行/列/块即可。稍微有点难的是如何把`(i,j)`坐标转换成块的下标，这个也没什么好说的，需要在草稿纸上模拟和尝试。

由于这里的大小总是固定的（为常数个），因此空间复杂度可以认为是O(1)，时间复杂度也可以认为是O(1)，因为只有81次遍历。

```java
class Solution {
    public boolean isValidSudoku(char[][] board) {
        int[][] rows = new int[9][10];
        int[][] cols = new int[9][10];
        int[][] bolcks = new int[9][10];
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++) {
                if(board[i][j] == '.') continue;
			   // 坐标转换
                int blockIndex = i/3*3 + j/3; // 这个转换需要在草稿纸上比划比划
                int digit = board[i][j]-'0';

                if(rows[i][digit] == 1) return false;
                else rows[i][digit] = 1;

                if(cols[j][digit] == 1) return false;
                else cols[j][digit] = 1;

                if(bolcks[blockIndex][digit] == 1) return false;
                else bolcks[blockIndex][digit] = 1;
            }
        }
        return true;
    }
}
```



##### [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/) [五星++]

给定一个非负整数数组，最初位于数组的第一个位置。数组中的每个元素代表你在该位置可以跳跃的最大长度。你的目标是使用**最少**的跳跃次数到达数组的最后一个位置。假设你总是可以到达数组的最后一个位置。

```
输入: [2,3,1,1,4]
输出: 2
解释: 跳到最后一个位置的最小跳跃数是 2。
     从下标为 0 跳到下标为 1 的位置，跳 1 步，然后跳 3 步到达数组的最后一个位置。
```

分析：参考[这里](https://www.cnblogs.com/grandyang/p/4373533.html)

解法1：

```java
class Solution {
    public int jump(int[] nums) {
        int step = 0, i = 0, curMaxPosition = 0;
        while (curMaxPosition < nums.length-1) {
            int preMaxPosition = curMaxPosition;
            while(i <= preMaxPosition) {
                curMaxPosition = Math.max(curMaxPosition, i + nums[i]);
                i++;
            }
            step++;
        }
        return step;
    }
}
```



解法2：

```java
// 时间复杂度：O(1)
class Solution {
    public int jump(int[] nums) {
        int step = 0, cur = 0, last = 0;
        for(int i = 0; i < nums.length - 1; i++) {
        	  cur = Math.max(cur, i + nums[i]);
        	  if(i == last) {
                   last = cur;
                   step++;
                   if(cur >= nums.length - 1) break;
        	  }
        }
        return step;
    }
}
```



##### [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/) [五星++]

问题同上，本题是判断是否能够到达最后一个位置。

分析：

解法1：

```java
class Solution {
    public boolean canJump(int[] nums) {
        int n = nums.length, i = 0, cur = 0;
        while (cur < n-1) {
            int pre = cur;
            while(i <= pre) {
                cur = Math.max(cur, i + nums[i]);
                i++;
            }
            if(pre == cur) return false;
        }
        return true;
    }
}
```

解法2：

```java
class Solution {
    public boolean canJump(int[] nums) {
        int last = 0;
        for(int i = 0; i < nums.length; i++) {
          	  if(i > last) return false;
        	  last = Math.max(last, i + nums[i]);
        }
        return true;
    }
}
```





##### [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/) [五星，多种方法]

给定一个字符串数组，将字母异位词组合在一起。**字母异位词**指字母相同，但排列不同的字符串。假定所有输入均为小写字母，不考虑答案输出的顺序。

```
输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
输出:
[
  ["ate","eat","tea"],
  ["nat","tan"],
  ["bat"]
]
```

分析：本题有多种解法，非常精彩。参考[这里](https://leetcode-cn.com/problems/group-anagrams/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--16/)

**方法1**：每一个字符对应一个质数，由于题目规定了只含有小写字母，因此只需要26个质数。**将一个单词转化成其所有字符对应的质数之积，这个积一定能唯一的表示字母异位词**。这里涉及的数学原理是：

> 算术基本定理，又称为正整数的唯一分解定理，即：每个大于1的自然数，要么本身就是质数，要么可以写为2个以上的质数的积，而且这些质因子按大小排列之后，写法仅有一种方式。

这种做法效率较高，在LeetCode提交可以通过，但是存在溢出的风险，如果出现一个很长很长的单词，那么对应的质数之积很可能就溢出了。这种思想值得借鉴。

```java
/** n是数组的长度，k是最长单词的长度
 * 时间复杂度：O(n*k) 
 * 空间复杂度：O(n*k) 哈希表存储所有单词
 */
public List<List<String>> groupAnagrams(String[] strs) {
    // 每个字符对应一个质数，比如map[0] = 2,代表字符a对应质数2
    int[] map = new int[]{2,3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97,101};

    List<List<String>> res = new ArrayList<>();
    Map<Long, List<String>> dict = new HashMap<>();
    for(String word : strs) {
        long key = 1;
        for(char c : word.toCharArray()) {
            key *= map[c-'a'];
        }
        if(!dict.containsKey(key)){
            List<String> s = new ArrayList<>();
            s.add(word);
            dict.put(key,s);
        }else {
            dict.get(key).add(word);
        }
    }
	// 手写，后来看了题解发现可以直接构建
    for(Map.Entry<Long, List<String>> entry : dict.entrySet()) {
        res.add(entry.getValue());
    }
    return res;
}
```

**方法2**：方法1设法通过计算数值来作为一个key，但是存在溢出的风险，其实我们通过字符串的形式来表示一个key，思路还是和方法1类似，只不过这里用`1#1#1#...`的形式来记录某个单词，解释如下：

`key = i#j#k#...(共26个)`，其中`i,j,k...`分别表示字符`a,b,c...`出现的**次数** 。显然，对于字母异位词，它们的key总是一样的。

```java
// O(n*k)
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();
    for(String word : strs) { //O(n)
        char[] charArray = word.toCharArray();
        // 统计单词 word 中各个字符出现的个数
        int[] count = new int[26];
        for(char c : charArray) { //O(k)
            count[c-'a']++;
        }
        // 将单词转化成 1#1#...的形式
        StringBuilder key = new StringBuilder();
        for(int i = 0; i < 26; i++) { 
            key.append(count[i]).append('#');
        }

        if(map.containsKey(key.toString())) {
            map.get(key.toString()).add(word);
        }else {
            List<String> s = new ArrayList<>();
            s.add(word);
            map.put(key.toString(),s);
        }
    }

    return new ArrayList<>(map.values());
}
```

**方法3：**排序法。不管单词的字符顺序怎么变，我先把它排个序（假设单词长度为k，那么这里需要的时间为O(k*log(k))，这样，对于字母异位词，它们就变成一样的了，因此可以把排序后的单词作为对应的所有字母异位词的key。如下图所示：

![image.png](https://pic.leetcode-cn.com/3bb3a05fd55233986f514509a6d707441d90fbc76a0c15b9ec0aba97aa656518-image.png)

```java
/**
 * 方法3：排序法
 * 时间复杂度：O(n* k*log(k)) k 是最长单词的长度
 * 空间复杂度：O(n*k)
 */
public List<List<String>> groupAnagrams(String[] strs) {
    Map<String, List<String>> map = new HashMap<>();

    for(String word : strs) { //O(n)
        char[] charArray = word.toCharArray();
        Arrays.sort(charArray); //O(k*log(k))
        String key = String.valueOf(charArray);
        if(map.containsKey(key)) {
            map.get(key).add(word);
        }else {
            List<String> s = new ArrayList<>();
            s.add(word);
            map.put(key,s);
        }
    }
	// 更快速的写法，不用自己遍历map构建res
    return new ArrayList<>(map.values()); 
}
```



##### [56. 合并区间](https://leetcode-cn.com/problems/merge-intervals/) [五星,细节&自定义排序]

给出一个区间的集合，请合并所有重叠的区间。比如

```
输入: [[2,3],[1,5]]
输出: [[1,5]]
解释: 区间 [2,3] 和 [1,5] 可被视为重叠区间。

输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```

分析：通过case发现，给出的区间不一定是有序的，因此，首先要做的就是把各个区间根据**左区间值**进行排序。这里就涉及到如何进行自定义排序比较器(comparator)。自定义排序是经常用到的，有必要记住。

如果想要对一个二维矩阵进行排序，排序规则是先比较每一行的第0个值，按升序排列；如果第0个值相等，再比较第1个值，那么可以写成如下：

```java
Arrays.sort(matrix, new Comparator<int[]>(){
    @Override
    public int compare(int[] x, int[] y) {
        if(x[0] != y[0]){
            return x[0] - y[0]; // 升序排列
        }else {
            return x[1] - y[1];
        }
    }
});
```

实现上述功能也可以使用 lambda 表达式，这种方式更简洁。

```java
Arrays.sort(matrix, (x, y) -> {
    if(x[0] != y[0]){
        return x[0] - y[0];
    }else {
        return x[1] - y[1];
    }
});
```

当然，本题中只需要根据第0个值比较即可。



```java
class Solution {
    public int[][] merge(int[][] intervals) {
        int rows = intervals.length;
        if(rows == 0) return new int[0][];

        // lambda 表达式的写法
        Arrays.sort(intervals, (x, y) -> (x[0] - y[0]));

        List<int[]> res = new ArrayList<>(); // int[] 是一个对象，所以可以存在在容器中
        // List<int> res = new ArrayList<>(); // 而 int 是基本数据类型，所以必须用 Integer

        int i = 0;
        while (i < rows) {
            int left = intervals[i][0], right = intervals[i][1];
            while (i + 1 < rows && right >= intervals[i+1][0]){
                right = Math.max(right, intervals[i+1][1]); //关键，多想几个不同的测试用例即可
                i++;
            }
            res.add(new int[]{left, right});
            i++;
        }
      //new int[0][]表示不指定行，行自动填充，如果为new int[4][], 
      //即使结果为[[1,6],[8,10],[15,18]]，也会强制输出[[1,6],[8,10],[15,18],null]，即不足行null补充
      //这种写法相比于先把数据存到List<List<Integer>> matrix，再转化成 int[][] 方便多了
        return res.toArray(new int[0][]);
    }
}
```



##### [48. 旋转图像](https://leetcode-cn.com/problems/rotate-image/) [五星]

给定一个 n × n 的二维矩阵表示一个图像。将图像顺时针旋转 90 度。

说明：必须在**原地(in-place)**旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要使用另一个矩阵来旋转图像。

```
给定 matrix =
[
  [ 5, 1, 9,11],
  [ 2, 4, 8,10],
  [13, 3, 6, 7],
  [15,14,12,16]
], 

原地旋转输入矩阵，使其变为:
[
  [15,13, 2, 5],
  [14, 3, 4, 1],
  [12, 6, 8, 9],
  [16, 7,10,11]
]
```

分析：这一题第一次见还是不知所措的，看了题解才发现有非常简单的做法，故记录于此。最优时间复杂度是$O(n^2)$ 。思路就是**先对矩阵转置，再逐行反转**，即可达到题目要求的旋转90°的效果。真是奇妙~

```java
// 时间复杂度：O(n^2)
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 矩阵转置
        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        // 逐行反转
        for(int i = 0; i < n; i++) {
            for(int left = 0, right = n-1; left < right; left++, right--){
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;
            }
        }
    }
}
```





##### [54. 螺旋矩阵](https://leetcode-cn.com/problems/spiral-matrix/) [三星]

给定一个包含 *m* x *n* 个元素的矩阵（*m* 行, *n* 列），请按照顺时针螺旋顺序，返回矩阵中的所有元素。

分析：这种类型的题目就是纯粹的考察代码能力，不涉及算法，但是要细心。关键思路是设立`up,down,left,right` 四个变量来控制边界，每执行一趟之后观察边界是否越界，即比较`up和down`，`left和right` ，如果发生越界即可退出循环，程序结束。

```java
// 时间复杂度：O(m*n) (因为遍历矩阵的每个元素)
// 空间复杂度：O(m*n) 
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int rows = matrix.length;
        if (rows == 0) return res;
        int cols = matrix[0].length;
        int up = 0, down = rows-1, left = 0, right = cols-1; // 边界
        
        while (true) {
            // up: left -> right
            for(int i=left; i<=right;i++) {
                res.add(matrix[up][i]);
            }
            up++;
            if(up > down) break;

            // right: up -> down
            for(int i=up; i<=down;i++) {
                res.add(matrix[i][right]);
            }
            right--;
            if(right < left) break;

            // down: right -> left
            for(int i=right; i>=left; i--){
                res.add(matrix[down][i]);
            }
            down--;
            if(up > down) break;

            // left: down -> up
            for(int i=down; i>=up;i--){
                res.add(matrix[i][left]);
            }
            left++;
            if(right < left) break;
        }
        return res;
    }
}
```



##### [59. 螺旋矩阵 II](https://leetcode-cn.com/problems/spiral-matrix-ii/) [三星]

给定一个正整数 *n*，生成一个包含 1 到 $n^2$ 所有元素，且元素按顺时针顺序螺旋排列的**正方形**矩阵。

分析：本题就是上一题的逆过程。核心思路是相同的。

```java
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int count = 1, up = 0, down = n - 1, left = 0, right = n - 1;
        n = n * n;
        while (count <= n) {
            // up: left -> right
            for (int i = left; i <= right; i++)
                matrix[up][i] = count++;
            up++;

            // right: up -> down
            for (int i = up; i <= down; i++)
                matrix[i][right] = count++;
            right--;

            // down: right -> left
            for (int i = right; i >= left; i--)
                matrix[down][i] = count++;
            down--;

            // left: down -> up
            for (int i = down; i >= up; i--)
                matrix[i][left] = count++;
            left++;
        }
        return matrix;
    }
}
```



##### [66. 加一](https://leetcode-cn.com/problems/plus-one/) [四星]

给定一个由整数组成的**非空数组**所表示的**非负整数**，在该数的基础上加一。最高位数字存放在数组的首位， 数组中每个元素只存储单个数字(0~9)。假设除了整数 0 之外，其他数字不存在前导0。

```
输入: [1,2,3]
输出: [1,2,4]
解释: 输入数组表示数字 123。
输入: [9,9]
输出: [1,0,0]
解释: 输入数组表示数字 99。
```

本题难是不难，但是自己的写法并不够优雅，主要原因在于并没有深入挖掘本题的特性，还是以“两树相加”的思维来做题了。而本题虽然也属于两个数相加的范畴，但更特殊的在于它**只加一**，因此代码可以写的非常简洁。

```java
// 自己写的版本
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        // 没有产生进位
        if(digits[n-1] + 1 < 10) {
            digits[n-1]++;
            return digits;
        }

        // 产生进位
        int carry = 1;
        digits[n-1] = 0;
        for(int i = n - 2; i >= 0; i--) {
            int d = digits[i] + carry;
            if(d < 10){
                digits[i] = d;
                carry = 0;
                break;
            }else {
                digits[i] = 0;
                carry = 1;
            }
        }
        if(carry == 1){
            int[] res = new int[n+1];
            res[0] = 1;
            return res;
        }else{
            return digits;
        }
    }
}

// 参考版本，优雅了不止一倍~
/*
根据本题的情况，如果某一位上加1不出现进位，就可以结束程序了
判断是否出现进位不需要额外用carry表示，只需要判断加1后这一位上是否变成了0
如果第i位经过加1后变成了0，说明产生了进位，继而向第i-1位加1，直到不再产生进位
如果是[9,9,9] + 1 这种情况，则特殊处理即可
*/
class Solution {
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        for(int i = n - 1; i >= 0; i--){
            digits[i]++;
            digits[i] %= 10;
            if(digits[i] != 0) return digits; //
        }
        int[] res = new int[n+1];
        res[0] = 1;
        return res;
    }
}
```



##### [73. 矩阵置零](https://leetcode-cn.com/problems/set-matrix-zeroes/) [五星]

给定一个 *m* x *n* 的矩阵，如果一个元素为 0，则将其所在行和列的所有元素都设为 0。请使用**原地**算法。并要求只能使用**常数**空间。

```
输入: 
[
  [0,1,2,0],
  [3,4,5,2],
  [1,3,1,5]
]
输出: 
[
  [0,0,0,0],
  [0,4,5,0],
  [0,3,1,0]
]
```

分析：如果`matrix[i][j]`为0，那么第`i`行和第`j`列都要变成0，于是可以在遍历矩阵的过程中把第0行和第0列作为标记，即遇到`matrix[i][j]`为0，就把第`i`行和第`j`列的**第0个元素**都标记成0，用于之后的处理。由于第0行和第0列拿来作为标记了，对第0行和第0列本身是否要变为0则单独处理。

```java
class Solution {
    // 时间复杂度：O(m*n)
    // 空间复杂度：O(1) (只用到了2个布尔型变量)
    public void setZeroes(int[][] matrix) {
        int m = matrix.length;
        if(m == 0) return;
        int n = matrix[0].length;
        
        // step 1：检查第一行和第一列中是否存在0
        boolean existZeroAtFirstRow = false, existZeroAtFirstCol = false;
        for(int col = 0; col < n; col++){ //O(n)
            if(matrix[0][col] == 0){
                existZeroAtFirstRow = true;
                break;
            }
        }
        for(int row = 0; row < m; row++){ //O(m)
            if(matrix[row][0] == 0){
                existZeroAtFirstCol = true;
                break;
            }
        }
        
        // step 2：标记除第一行和第一列之外的其他数据是否存在0
        for(int row = 1; row < m; row++){ //O(m*n)
            for(int col = 1; col < n; col++){
                if(matrix[row][col] == 0){
                    matrix[0][col] = 0;
                    matrix[row][0] = 0;
                }
            }
        }
        
        // step 3：处理
        for(int row = 1; row < m; row++){// O(m*n)
            for(int col = 1; col < n; col++){
                if(matrix[0][col] == 0 || matrix[row][0] == 0){
                    matrix[row][col] = 0;
                }
            }
        }

        // step 4：处理第一行和第一列
        if(existZeroAtFirstRow){
            for(int col = 0; col < n; col++){
                matrix[0][col] = 0;
            }
        }
        if(existZeroAtFirstCol){
            for(int row = 0; row < m; row++){
                matrix[row][0] = 0;
            }
        }
    }
}
```



##### [118. 杨辉三角](https://leetcode-cn.com/problems/pascals-triangle/) [一星]

学会了`Arrays.asList(1,2,3)` 的用法。

```java
class Solution {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<>();
        if (numRows < 1) return triangle;
       
        // 初始化
        triangle.add(Arrays.asList(1));
        for (int r = 1; r < numRows; r++) {
            List<Integer> row = new ArrayList<>();
            row.add(1);
            for (int i = 0; i < triangle.get(r - 1).size() - 1; i++) {
                row.add(triangle.get(r - 1).get(i) + triangle.get(r - 1).get(i + 1));
            }
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }
}
```



##### [119. 杨辉三角 II](https://leetcode-cn.com/problems/pascals-triangle-ii/) [一星]

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        List<Integer> preRow = new ArrayList<>();
        if(rowIndex < 0) return preRow;

        preRow.add(1);
        for(int r = 1; r <= rowIndex; r++){
            List<Integer> currRow = new ArrayList<>();
            currRow.add(1);
            for(int i = 0; i < preRow.size() - 1; i++){
                currRow.add(preRow.get(i) + preRow.get(i+1));
            }
            currRow.add(1);
            preRow = currRow;
        }
        return preRow;
    }
}
```



##### [134. 加油站](https://leetcode-cn.com/problems/gas-station/) [五星]



自己的解法

```java
class Solution {    
    /**
     * 时间复杂度：O(n^2)
     * 这种方法比较暴力，就是先找到一个合法的站点作为起始站，然后开始模拟
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        for (int i = 0; i < n; i++) {
            if (gas[i] < cost[i]) continue;
            int next = i;
            int currGas = gas[i], currCost = cost[i];
            // 模拟
            while (currGas >= currCost) {
                next = (next + 1) % n;
                currGas = currGas - currCost + gas[next];
                currCost = cost[next];
                if (next == i) break;
            }
            if (next == i) return i;
        }
        return -1;
    }
}    
```



参考的解法

```java
// O(n) 
class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int start = 0, currGas = 0, totalGas = 0;
        for(int i = 0; i < n; i++) {
            totalGas += gas[i]-cost[i];
            currGas += gas[i]-cost[i];
            if(currGas < 0) {
                start = i + 1;
                currGas = 0;
            }
        }
        return totalGas < 0 ? -1 : start;
    }
}
```





##### [189. 旋转数组](https://leetcode-cn.com/problems/rotate-array/) [五星，多种解法]

给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。

```
输入: [1,2,3,4,5,6,7] 和 k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右旋转 1 步: [7,1,2,3,4,5,6]
向右旋转 2 步: [6,7,1,2,3,4,5]
向右旋转 3 步: [5,6,7,1,2,3,4]
```

分析：本题要求至少采用3种方法完成，并尽量只是用O(1)的空间。

```java
// 方法1: 每次移动一个，总共移动k次
// 时间复杂度O(k*n)， 空间复杂度O(1)
// 可优化的点是选择移动个数较少的方向移动，不一定非得从前向后移动
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k %= n; // 加上这一行代码之后可以勉强通过测试，耗时约400ms
    for(int step = 0; step < k; step++){
        int tail = nums[n-1];
        for(int i = n-1; i > 0; i--)
            nums[i] = nums[i-1];
        nums[0] = tail;
    }
}

// 优化版本， 时间可以降低到180ms左右
public void rotate2(int[] nums, int k) {
    int n = nums.length;
    k %= n;
    if(k <= n - k){
        // 从左向右移动
        for(int step = 0; step < k; step++){
            int tail = nums[n-1];
            for(int i = n-1; i > 0; i--)
                nums[i] = nums[i-1];
            nums[0] = tail;
        }
    }else{
        // 从右向左移动
        for(int step = 0; step < n-k; step++){
            int head = nums[0];
            for(int i = 0; i < n-1; i++)
                nums[i] = nums[i+1];
            nums[n-1] = head;
        }
    }
}

// 方法2：多次翻转（推荐，实现简单~）
// 时间复杂度：O(n), 空间复杂度：O(1)
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k %= n;
    reverse(nums,0,n-k-1);
    reverse(nums,n-k,n-1);
    reverse(nums,0,n-1);
}
private void reverse(int[] nums, int left, int right) {
    while (left < right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
        left++;
        right--;
    }
}

// 方法3：一次性移动k个，不建议，因为要额外空间
// 时间复杂度O(n)，空间复杂度O(k%n)，耗时约0ms
public void rotate(int[] nums, int k) {
    int n = nums.length;
    k %= n;
    if (k == 0) return;
    int[] tail = new int[k];
    for (int i = n - k; i < n; i++) { // O(k)
        tail[i - n + k] = nums[i];
    }
    for (int i = n - k - 1; i >= 0; i--) { // O(n-k)
        nums[i + k] = nums[i];
    }
    for (int i = 0; i < k; i++) { // O(k)
        nums[i] = tail[i];
    }
}
```



##### [217. 存在重复元素](https://leetcode-cn.com/problems/contains-duplicate/) [一星]

给定一个整数数组，判断是否存在重复元素。如果任何值在数组中出现至少两次，函数返回 true。如果数组中每个元素都不相同，则返回 false。

分析：利用哈希。

```java
// 时间复杂度：O(n)
// 空间复杂度：O(n)
class Solution {
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int val : nums){
            if(set.contains(val)) return true;
            else set.add(val);
        }
        return false;
    }
}
```



##### [219. 存在重复元素 II](https://leetcode-cn.com/problems/contains-duplicate-ii/) [四星]

Given an array of integers and an integer k, find out whether there are two **distinct** indices i and j in the array such that **nums[i] = nums[j]** and the **absolute** difference between i and j is **at most** k.

分析：本题需要注意一下题意。中文版的题干翻译的有点问题。要求判断是否存在两个数，使得这两个数相等，要求下标不同且下标之差的绝对值**小于等于**k。

自己的写法比较直观，用一个HashMap存放值和对应的索引，在遍历的过程中，如果遇到重复元素，则检查这两个元素之间的距离是否小于等于k。如果满足，则返回true程序结束；如果不满足，则把当前元素插入map中。注意，即使遇到重复元素但不满足要求的，也把当前元素插入map中，也就是替换掉之前出现的那个值。为什么可以这样处理？因为符合要求的重复元素必须在间隔k之内，因此可以用更近的这个替换较远的那个（因为较远的那个值一定不符合）

参考的版本就是利用了这一性质，保证了哈希表里仅存放位置为`i-3,i-2,i-1`的这3个元素（假设当前位置为`i`，k=3），因为当前元素只有与这3个位置的元素有重复的，才是满足题目要求的。再往前的位置就已经超出范围了，没有意义。这个思想有点像利用**堆结构**来处理流数据的第k大元素那一题。

```java
class Solution {
    /*
    // 自己写的方法
    // 时间复杂度：O(n) (一趟遍历)
    // 空间复杂度：O(n) 
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        // <value, index>
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            if(map.containsKey(nums[i])){
                if(i - map.get(nums[i]) <= k) return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }
    */
    
    // 参考解法 
    // 时间复杂度：O(n)
    // 空间复杂度：O(k)  (哈希表中只存放k个元素)
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        Set<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            if(set.size() > k){
                set.remove(nums[i-k]);
            }
        }
        return false;
    }
}
```



##### [220. 存在重复元素 III](https://leetcode-cn.com/problems/contains-duplicate-iii/) [五星+++]

Given an array of integers, find out whether there are two distinct indices i and j in the array such that the absolute difference between nums[i] and nums[j] is at most t and the absolute difference between i and j is at most k.

分析：

```java
// 暴力法，超时
// 时间复杂度：O(n*min(n,k))
// 空间复杂度：O(1)
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        for(int i = 0; i < nums.length; i++){
            for(int j = i + 1; j < nums.length && j <= i + k; j++){
                if(Math.abs((long)nums[i] - (long)nums[j]) <= t) return true;
            }
        }
        return false;
    }
}


// 时间复杂度：O(n*log(min(n,k)))
// 空间复杂度：O(min(n,k))
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++){ // O(n)
            // 找到大于等于nums[i]的最小值
            Integer successor = set.ceiling(nums[i]); // O(log(k))
            if(successor != null && (long)successor - (long)nums[i] <= t) return true;
            // 找到小于等于nums[i]的最大值
            Integer predecessor = set.floor(nums[i]); // O(log(k))
            if(predecessor != null && (long)nums[i] - (long)predecessor <= t) return true;

            set.add(nums[i]); // O(log(k))
            if(set.size() > k){
                set.remove(nums[i-k]); // O(log(k))
            }
        }
        return false;
    }
}
```

关于 Java 的 TreeSet 的理解不深，貌似是第一次使用。



最优解，结合“**桶排序**”+“滑动窗口”的思想。参考[这里。](https://leetcode-cn.com/problems/contains-duplicate-iii/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-46/)

```java
// 时间复杂度：O(n)
// 空间复杂度：O(min(n,k))
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        if(t < 0) return false;
        // <key: index of bucket that contains num, value: num>
        // 保证map中只存放k个元素，模拟滑动窗口
        Map<Long, Long> map = new HashMap<>();
        long width = t + 1;

        for(int i = 0; i < nums.length; i++){
            long index = getID(nums[i], width);
            if(map.containsKey(index)) 
              	return true;
            if(map.containsKey(index - 1) && (long)nums[i] - map.get(index - 1) <= t) 
            	return true;
            if(map.containsKey(index + 1) && map.get(index + 1) - (long)nums[i] <= t) 
              	return true;

            map.put(getID(nums[i], width), (long)nums[i]);
            // 保证map中只存放k个元素，否则就删除窗口最左边的那个数
            if(map.size() > k){
                // 注意这里不要写错了，搞清楚map中的key/value分别对应的是什么
                map.remove(getID(nums[i-k], width)); 
            }
        }
        return false;
    }

    // 获取数值num应该存放的桶对应的桶编号
    private long getID(long num, long width) {
        return num >= 0 ? num / width : (num + 1) / width - 1;
    }
}
```



##### [228. 汇总区间](https://leetcode-cn.com/problems/summary-ranges/) [三星]

给定一个无重复元素的有序整数数组，返回数组区间范围的汇总。比如：

```
输入: [0,1,2,4,5,7]
输出: ["0->2","4->5","7"]
解释: 0,1,2 可组成一个连续的区间; 4,5 可组成一个连续的区间。
```

分析：比较常规。

```java
class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> res = new ArrayList<>();
        int n = nums.length;
        if(n == 0) return res;

        int left = 0, right = 0;
        while (right < n) {
            while (right < n - 1 && nums[right] + 1 == nums[right+1]) {
                right++;
            }
            if(left == right){
                res.add(String.valueOf(nums[left]));
            }else {
                res.add(nums[left] + "->" + nums[right]);
            }
            right++;
            left = right;
        }
        return res;
    }
}
```



##### [238. 除自身以外数组的乘积](https://leetcode-cn.com/problems/product-of-array-except-self/) [四星]

给定长度为 n 的整数数组 nums，其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。

```
输入: [1,2,3,4]
输出: [24,12,8,6]
说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
```

进阶：
你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）

分析：本题用到的思想还是经常使用的。

```java
class Solution {
    /*
    // 方法1：
    // 时间复杂度：O(n)  3次遍历
    // 空间复杂度：O(n)  额外申请了 left[] 和 right[]数组
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] left = new int[n];
        left[0] = 1;
        int[] right = new int[n];
        right[n-1] = 1;
        for(int i = 1; i < n; i++) {
            left[i] = left[i-1] * nums[i-1];
        }
        for(int i = n - 2; i >= 0; i--) {
            right[i] = right[i+1] * nums[i+1];
        }
        int[] output = new int[n];
        for(int i = 0; i < n; i++) {
            output[i] = left[i] * right[i];
        }
        return output;
    }
    */

    // 方法2：
    // 时间复杂度：O(n) 2次遍历
    // 空间复杂度：O(1)
    public int[] productExceptSelf(int[] nums) {
        // 1,2,6,24
        int k = 1;
        int[] res = new int[nums.length];
        for(int i = 0; i < nums.length; i++) {
            res[i] = k;
            k *= nums[i];
        }
        k = 1;
        for(int i = nums.length-1; i >= 0; i--) {
            res[i] *= k;
            k *= nums[i];
        }
        return res;
    }
}
```





##### [205. 同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/) [二星]

给定两个字符串 **s **和 **t**，判断它们是否是同构的。假设 **s **和 **t **具有相同的长度。

所谓“同构”，其实就是双向映射，即我对应你，你对应我，彼此唯一。比如：

```
输入: s = "paper", t = "title"
输出: true  --> 因为 p <--> t，a <--> i 等彼此构成唯一的映射

输入: s = "foo", t = "bar"
输出: false 
f 与 b 双向映射没问题，但是 o 可以映射为 a 或 r，这就不符合要求了
```

根据题意，想到通过两个map来存放双向映射。

顺序遍历字符串 s 和 t ，如果 s[i] 和 t[i] 首次出现，那么在两个哈希表中分别存放彼此的映射关系；除此之外的情况，当且仅当smap中存在key为s[i]而value为t[i]，且tmap中存在key为t[i]而value为s[i]时，才表示彼此是唯一的双向映射的，其他情况均不满足关系。

```java
class Solution {
    // 题目已经保证s和t的长度总是一样的
    public boolean isIsomorphic(String s, String t) {
        Map<Character,Character> smap = new HashMap<>(); // s->t的映射
        Map<Character,Character> tmap = new HashMap<>(); // t->s的映射
        int n = s.length();
        for(int i = 0; i < n; i++) {
            char cs = s.charAt(i);
            char ct = t.charAt(i);
            if(!smap.containsKey(cs) && !tmap.containsKey(ct)){
                smap.put(cs, ct);
                tmap.put(ct, cs);
            }else if(smap.containsKey(cs) && tmap.containsKey(ct)){
                if(!smap.get(cs).equals(ct) || !tmap.get(ct).equals(cs)) return false;
            }else {
                return false;
            }
        }
        return true;
    }
}
```



##### [290. 单词规律](https://leetcode-cn.com/problems/word-pattern/) [二星]

给定一种规律 `pattern` 和一个字符串 `str` ，判断 `str` 是否遵循相同的规律。

这一题和第[205. 同构字符串](https://leetcode-cn.com/problems/isomorphic-strings/)是一模一样的。

```java
class Solution {
    public boolean wordPattern(String pattern, String str) {
        String[] words = str.split(" ");
        if(pattern.length() != words.length) return false; //如果长度不相等，就肯定满足映射关系
        int n = pattern.length();

        Map<Character, String> pmap = new HashMap<>(); // pattern -> str 的映射
        Map<String, Character> smap = new HashMap<>(); // str -> pattern 的映射

        for(int i = 0; i < n; i++) {
            char c = pattern.charAt(i);
            String w = words[i];
            if(!pmap.containsKey(c) && !smap.containsKey(w)){
                pmap.put(c, w);
                smap.put(w, c);
            }else if(pmap.containsKey(c) && smap.containsKey(w)){
                if(!pmap.get(c).equals(w) || !smap.get(w).equals(c))
                    return false;
            }else {
                return false;
            }
        }
        return true;
    }
}
```



##### [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/) [一星]

给定两个字符串 *s* 和 *t* ，编写一个函数来判断 *t* 是否是 *s* 的**字母异位词**。假设只包含小写字母。

```java
// 时间复杂度：O(n)
class Solution {
    public boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;
        
        int[] map = new int[26];
        for (char c : s.toCharArray()) {
            map[c - 'a']++;
        }
        for (char c : t.toCharArray()) {
            map[c - 'a']--;
            if (map[c - 'a'] < 0) return false;
        }
        return true;
    }
}
```

**进阶:**
如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？

答：这种情况就要用HashMap来做，而不是使用固定长度的数组。



##### [58. 最后一个单词的长度](https://leetcode-cn.com/problems/length-of-last-word/) [一星，字符串的简单处理]

给定一个仅包含**大小写字母**和**空格 ' '** 的字符串 s，返回其最后一个单词的长度。如果不存在最后一个单词，请返回 0 。一个单词是指仅由字母组成、不包含任何空格的最大子字符串。

```java
class Solution {
    public int lengthOfLastWord(String s) {
        char[] str = s.toCharArray();
        int i = s.length() - 1;
        while (i >= 0 && str[i] == ' '){
            i--;
        }
        if(i == -1) return 0;
        int len = 0;
        while (i >= 0 && str[i] != ' '){
            i--;
            len++;
        }
        return len;
    }
  
  	// 熟悉一下字符串处理的api
    public int lengthOfLastWord(String s) {
        //This method may be used to trim whitespace from the beginning and end of a string.  
        s = s.trim(); 
        String[] words = s.split(" ");
        int n = words.length;
        if(n == 0) return 0;
        return words[n-1].length();
    }
}
```



##### [151. 翻转字符串里的单词](https://leetcode-cn.com/problems/reverse-words-in-a-string/) [二星，字符串的简单处理]

给定一个字符串，逐个翻转字符串中的每个单词。

注意：

- 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
- 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。

比如：

```
输入: "  hello   world!  "
输出: "world! hello"
```

分析：

```java
// 调用api
class Solution {
    public String reverseWords(String s) {
        s = s.trim();
        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();
        for(int i = words.length - 1; i >= 0; i--) {
            if(!"".equals(words[i])){
                sb.append(words[i]).append(" ");
            }
        }
        if(sb.length() == 0) return "";
        return sb.toString().substring(0,sb.length()-1);
    }
}

//手动处理

```





##### [38. 外观数列](https://leetcode-cn.com/problems/count-and-say/) [三星]

这是一道找规律题，本质上不难，就是第一次理解起来比较别扭。但是这个题目此前在PAT中做过一遍。

看了一些题解，感觉自己的代码是最简洁的。

题意理解如下：

```
1     （读作：1个1，  -> 11就是下一行）
11    （读作：2个1，  -> 21就是下一行）
21    （读作：1个2，1个1，-> 1211就是下一行）
1211  （读作：1个1，1个2，2个1，-> 111221就是下一行）
111221（读作：3个1，2个2，1个1，-> 312211就是下一行）
312211（...）
```

通过上面的模拟过程，应该可以很好地理解本题的题意了，代码就是对这个过程的翻译，如下：

```java
class Solution {
    public String countAndSay(int n) {
        String prevLine = "1";
        int k = 1;
        while (k < n){
            // 根据前一行推出当前这一行 
            StringBuilder currLine = new StringBuilder();
            char[] s = prevLine.toCharArray();
            int i = 0, len = s.length;
            while(i < len) {
                char c = s[i];
                int count = 0;
                // c 为当前的数字，count 统计该数字“不间断”的出现了几次
                while (i < len && c == s[i]) {
                    i++;
                    count++;
                }
                // 将统计结果记录下来，即有 count 个 c。完全就是对之前理解过程的翻译
                currLine.append(count).append(c);
            }
            // 将当前行赋值给 preLine，进入下一次迭代
            prevLine = currLine.toString();
            k++;
        }
        return prevLine;
    }
}
```



##### [292. Nim 游戏](https://leetcode-cn.com/problems/nim-game/) [五星]

这也是一个找规律题，或者说是智力题，反正不是编程题。

你和你的朋友，两个人一起玩 Nim 游戏：桌子上有一堆石头，每次你们轮流拿掉 1 - 3 块石头。 拿掉最后一块石头的人就是获胜者。**假定每次都是你第一个拿**。编写一个函数，来判断你是否可以在给定石头数量的情况下赢得游戏。

```
输入: 4
输出: false 
解释: 如果堆中有 4 块石头，那么你永远不会赢得比赛；
     因为无论你拿走 1 块、2 块 还是 3 块石头，最后一块石头总是会被你的朋友拿走。
```

分析：

```java
class Solution {
    public boolean canWinNim(int n) {
        return n % 4 != 0;
    }
}
```



##### [179. 最大数](https://leetcode-cn.com/problems/largest-number/) [四星，排序题]

给定一组非负整数，重新排列它们的顺序使之组成一个最大的整数。

```
示例 1:
输入: [10,2]
输出: 210

示例 2:
输入: [3,30,34,5,9]
输出: 9534330
```

分析：

```java
// 解法1
class Solution {
    public String largestNumber(int[] nums) {
        int n = nums.length;
        if (n == 0) return "";

        String[] s = new String[n];
        for (int i = 0; i < n; i++) {
            s[i] = String.valueOf(nums[i]);
        }
      	// 关键！
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
}

// 解法2
class Solution {
    public String largestNumber(int[] nums) {
    String result = IntStream.of(nums).mapToObj(String::valueOf).sorted(((o1, o2) -> (o2 + o1).compareTo(o1 + o2))).collect(Collectors.joining());
        return result.startsWith("0") ? "0" : result;
    }
}
```



##### [289. 生命游戏](https://leetcode-cn.com/problems/game-of-life/)

给定一个包含 m × n 个格子的面板，每一个格子都可以看成是一个细胞。每个细胞都具有一个初始状态：1 即为活细胞（live），或 0 即为死细胞（dead）。每个细胞与其八个相邻位置（水平，垂直，对角线）的细胞都遵循以下四条生存定律：

* 如果活细胞周围八个位置的活细胞数少于两个，则该位置活细胞死亡；
* 如果活细胞周围八个位置有两个或三个活细胞，则该位置活细胞仍然存活；
* 如果活细胞周围八个位置有超过三个活细胞，则该位置活细胞死亡；
* 如果死细胞周围正好有三个活细胞，则该位置死细胞复活；

根据当前状态，写一个函数来计算面板上所有细胞的下一个（一次更新后的）状态。下一个状态是通过将上述规则同时应用于当前状态下的每个细胞所形成的，其中细胞的出生和死亡是同时发生的。

```
输入： 
[
  [0,1,0],
  [0,0,1],
  [1,1,1],
  [0,0,0]
]
输出：
[
  [0,0,0],
  [1,0,1],
  [0,1,1],
  [0,1,0]
]
```

进阶：

* 你可以使用原地算法解决本题吗？请注意，面板上所有格子需要同时被更新：你不能先更新某些格子，然后使用它们的更新后的值再更新其他格子。
* 本题中，我们使用二维数组来表示面板。原则上，面板是无限的，但当活细胞侵占了面板边界时会造成问题。你将如何解决这些问题？

方法1：借助辅助数组

```java
class Solution {
    public void gameOfLife(int[][] board) {
        int m = board.length;
        if(m == 0) return;
        int n = board[0].length;
        
        // 辅助数组，统计每个点周围8个位置中活细胞的个数
        int[][] count = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int liveCells = getLiveCell(board, i, j);
                count[i][j] = liveCells;
            }
        }
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(board[i][j] == 1){
                    if(count[i][j] < 2 || count[i][j] > 3) board[i][j] = 0;
                }else {
                    if(count[i][j] == 3) board[i][j] = 1;
                }
            }
        }
    }
    
    private int getLiveCell(int[][] board, int i, int j) {
        int count = 0;
        if(isValid(board, i-1, j-1)) count++;
        if(isValid(board, i-1, j)) count++;
        if(isValid(board, i-1, j+1)) count++;
        if(isValid(board, i, j-1)) count++;
        if(isValid(board, i, j+1)) count++;
        if(isValid(board, i+1, j-1)) count++;
        if(isValid(board, i+1, j)) count++;
        if(isValid(board, i+1, j+1)) count++;
        return count;
    }
    
    private boolean isValid(int[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length && board[i][j] == 1;
    }
}
```



方法2：原地处理，不需要额外空间。

这种方法还是有技巧性的。由于数组只有0和1两种状态，并且用int类型表示，也就是说我们可以充分利用int的倒数第2个比特位，用来标记我们是否需要对原数组的值进行改变。

首先，判断某个位置的值是否为1不再使用`board[i][j] == 1`，而是判断`(board[i][j] & 1) == 1` 。比如，数组中某个位置的值a=1(01)，通过统计需要将其变为0(00)。则如下进行操作：

* 令a = 3(11)，最后一个比特位为1，没有改变，不影响其他位置的判断；而倒数第二个比特位为1，表示这个值需要改变；
* 最后遍历数组的时候，发现某个值的倒数第二个比特位为1，即判断`(a&2) == 1`，则改变其末位比特位的值为相反数，即`(~a) & 3` 。

```java
class Solution {
    private static int[] iaxis = new int[]{-1, -1, -1, 0, 0, 1, 1, 1};
    private static int[] jaxis = new int[]{-1, 0, 1, -1, 1, -1, 0, 1};

    public void gameOfLife(int[][] board) {
        int m = board.length;
        if (m == 0) return;
        int n = board[0].length;

        // mark when condition meet
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int liveCells = getLiveCell(board, i, j);
                if ((board[i][j] & 1) == 1) { // board[i][j] == 1
                    if (liveCells < 2 || liveCells > 3) board[i][j] = 3; // 0011
                } else { // board[i][j] == 0
                    if (liveCells == 3) board[i][j] = 2; // 0010
                }
            }
        }
        // convert if needed
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 0010 说明需要改变状态
                if ((board[i][j] & 2) == 2) {
                    board[i][j] = (~board[i][j]) & 3;
                }
            }
        }
    }

    private int getLiveCell(int[][] board, int i, int j) {
        int count = 0;
        for(int k = 0; k < 8; k++) {
            if (isValid(board, i + iaxis[k], j + jaxis[k])) count++;
        }
        return count;
    }

    private boolean isValid(int[][] board, int i, int j) {
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length 
                      && (board[i][j] & 1) == 1;
    }
}
```





