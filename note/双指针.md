### 哈希相关问题汇总

1. 两数之和（[1. Two Sum](https://leetcode-cn.com/problems/two-sum/)）**（有点意思~）**

思路：本题的思路非常精妙，当出现nums = [2, 3, 2, 5], target = 4的情况时，map中存放的\<nums[i], i>理论上虽然会被覆盖，但由于代码的巧妙设置，仍然可以正确返回结果。这也是本题精彩的地方！

```java
// 时间复杂度：O(n) 一趟遍历
// 空间复杂度：O(n) 使用map
public int[] twoSum(int[] nums, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    int[] ans = new int[2];
    for (int i = 0; i < nums.length; i++) {
        int temp = target - nums[i];
        if (map.containsKey(temp)) {
            ans[0] = map.get(temp);
            ans[1] = i;
            return ans;
        }
        map.put(nums[i], i);
    }
    throw new IllegalArgumentException("no valid answer.");
}
```







### 双指针法

1. 两数之和Ⅱ(输入有序数组)（[167. Two Sum II - Input array is sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)）

常规题，双指针法

```java
public int[] twoSum(int[] numbers, int target) {
    int left = 0, right = numbers.length-1;
    while (left < right) {
        if(numbers[left] + numbers[right] < target) left++;
        else if(numbers[left] + numbers[right] > target) right--;
        else return new int[]{left+1, right+1};
    }
    return new int[]{-1, -1};
}
```



2. 三数之和（[15. 3Sum](https://leetcode-cn.com/problems/3sum/)）

思路：

```java
// 时间复杂度：O(n^2)
public List<List<Integer>> threeSum(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums.length < 3) return result;
    // 预处理：排序
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 2; i++) {
        // 优化：若nums[i]>0，那么在i之后的数字必定大于0，故不可能存在3数之和等于0的情况~
        if (nums[i] > 0) break;
        // 去重
        if (i > 0 && nums[i - 1] == nums[i]) continue;

        int left = i + 1, right = nums.length - 1;
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            if (sum < 0) left++;
            else if (sum > 0) right--;
            else {
              	//也可以使用result.contains(...)去重，但效率会比较低
                result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                while (left < right && nums[left] == nums[left + 1]) left++; //去重
                while (left < right && nums[right] == nums[right - 1]) right--; //去重
                left++;
                right--;
            }
        }
    }
    return result;
}
```



3. 最接近的3数之和（[16. 3Sum Closest](https://leetcode-cn.com/problems/3sum-closest/)）

思路：

```java
public int threeSumClosest(int[] nums, int target) {
    if (nums.length < 3) return 0;
    int closestTarget = 0, maxDiff = Integer.MAX_VALUE;
    Arrays.sort(nums);// 预处理：排序
    // 固定位置i，双指针滑动left和right
    for (int i = 0; i < nums.length - 2; i++) {
        int left = i + 1, right = nums.length - 1;
        while (left < right) {
            int sum = nums[i] + nums[left] + nums[right];
            int diff = target - sum;
            if (Math.abs(diff) < maxDiff) {
                maxDiff = Math.abs(diff);
                closestTarget = sum;
            }
            if (sum < target) left++;
            else if (sum > target) right--;
            else return sum;
        }
    }
    return closestTarget;
}
```



4. 四数之和（[18. 4Sum](https://leetcode-cn.com/problems/4sum/)）

思路：

```java
public List<List<Integer>> fourSum(int[] nums, int target) {
    List<List<Integer>> result = new ArrayList<>();
    if (nums.length < 4) return result;
    Arrays.sort(nums);

    for (int i = 0; i < nums.length - 3; i++) {
        // 去重2
        if (i > 0 && nums[i] == nums[i - 1]) continue;

        // 剪枝1：计算当前的最小值，如果目标值 < 最小值，说明接下来必然不存在解
        if (nums[i] + nums[i+1] + nums[i+2] + nums[i+3] > target)
            break;
        // 剪枝2：计算当前的最大值，如果目标值 > 最大值，说明接下来以当前这个i遍历必然不存在解
        // 与前一个剪枝不同的是，随着i的后移，该最大值会增大，仍然存在解，
        // 因此这里用的是continue而不是break，只是跳过当前这个i罢了。需要模拟理解~
        if(nums[i] + nums[nums.length-1] + nums[nums.length-2] + nums[nums.length-3] < target)
            continue;

        for (int j = i + 1; j < nums.length - 2; j++) {
            // 去重1
            if (j > i + 1 && nums[j] == nums[j - 1]) continue;

            // 剪枝3: 同剪枝1
            if (nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) 
                break;
            // 剪枝4：同剪枝2
            if (nums[i] + nums[j] + nums[nums.length-1] + nums[nums.length-2] < target) 
                continue;
            
            int left = j + 1, right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[j] + nums[left] + nums[right];
                if (sum < target) left++;
                else if (sum > target) right--;
                else {
                    result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                    while (left < right && nums[left] == nums[left + 1]) left++; // 去重
                    while (left < right && nums[right] == nums[right - 1]) right--; // 去重
                    left++;
                    right--;
                }
            }
        }
    }
    return result;
}
```

不用剪枝耗时33ms，加了剪枝耗时4ms，这里的剪枝思想还是非常值得借鉴的~





双指针法中有一种比较难的应用，就是**滑动窗口(Sliding Window)**类型的题目，现总结如下：



5. 无重复字符的最长子串（[3. Longest Substring Without Repeating Characters](https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/)）

在刷题时经常会遇到**子串（substring）**和**子序列（subsequence）**这两种说法，在这里做一个说明：

假设有一个字符串为"pwwkew"，那么 "wke"属于substring，而"pwke"属于subsequence。也就是说，子串必须是连续的，而子序列不一定要连续。

思路：本题要求找出给定字符串最长的无重复字符子串。

（原来自己在无意之中已经使用了滑动窗口的思想~）

* 时间复杂度：O(2n) = O(n)。在最糟糕的情况下，每个字符将被 i 和 j 访问两次。
* 空间复杂度：O(min(m, n))。滑动窗口法需要 O(k) 的空间，其中 k 表示 Set 的大小。而 Set 的大小取决于字符串 n 的大小以及字符集字母数 m 的大小。

```java
// 自己写的版本，时间复杂度不够好
public int lengthOfLongestSubstring(String s) {
    Set<Character> set = new HashSet<>();
    int longestLen = 0, L = 0, R = 0;
    while (L < s.length()) {
        if (R < s.length() && !set.contains(s.charAt(R))) {
            set.add(s.charAt(R));
            R++;
        } else {
            longestLen = Math.max(longestLen, R - L);
            set.remove(s.charAt(L));
            L++;
        }
    }
    return longestLen;
}
```



参考版本

```java
public int lengthOfLongestSubstring(String s) {
    Map<Character, Integer> map = new HashMap<>();
    int longestLen = 0;
    for (int left = 0, right = 0; right < s.length(); right++) {
        if (map.containsKey(s.charAt(right))) {
            left = Math.max(map.get(s.charAt(right)) + 1, left); //这里的max比较必须注意
        }
        longestLen = Math.max(longestLen, right - left + 1);
        map.put(s.charAt(right), right);
    }
    return longestLen;
}
```



**最小覆盖子串**（[76. Minimum Window Substring](https://leetcode-cn.com/problems/minimum-window-substring/)）

给你一个字符串 S、一个字符串 T，请在字符串 S 里面找出：包含 T 所有字母的最小子串。

```
示例：
输入: S = "ADOBECODEBANC", T = "ABC"
输出: "BANC"
说明：
如果 S 中不存这样的子串，则返回空字符串 ""。
如果 S 中存在这样的子串，我们保证它是唯一的答案。
```





**删除有序数组中的重复项**（[26. Remove Duplicates from Sorted Array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/)）

给定一个排序数组，在**原地(in-place)**删除重复出现的元素，使得每个元素**只出现一次**，返回移除后数组的新长度。不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。比如给定nums = [0,0,1,1,1,2,2,3,3,4]，函数应该返回新的长度 5, 并且原数组 nums 的**前五个元素**被修改为 0, 1, 2, 3, 4。

思路：本题不涉及什么算法，只要有双指针的意识，根据直觉去写就能写出来。

数组完成排序后，我们可以放置两个指针 left 和 right，其中 left  是慢指针，而 right 是快指针。只要 nums[left] = nums[right]，我们就增加 right 以跳过重复项。当我们遇到 nums[left] != nums[right] 时，跳过重复项的运行已经结束，因此我们必须把它（nums[right] ）的值复制到 nums[left]。然后递增 left，接着我们将再次重复相同的过程，直到 right 到达数组的末尾为止。

代码：

```java
// 时间复杂度：O(n)
// 空间复杂度：O(1)
public int removeDuplicates(int[] nums) {
    if(nums.length == 0) return 0;
    int left = 0;
    for(int right = 1; right < nums.length; right++){
        if(nums[left] != nums[right]){
            nums[++left] = nums[right];
        }
    }
    return left+1;
}
```



**删除有序数组中的重复项2**（[80. Remove Duplicates from Sorted Array II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/)）（有点意思~）

基本要求同上，只是要求保留两个重复元素。比如：

```
给定 nums = [0,0,1,1,1,1,2,3,3],
函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
```

这一题想想好像并不难，但实现起来却比较费劲，最后还是参考了评论区的[题解](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii/solution/zhi-jie-bian-li-yi-ci-ji-ke-by-luo-ben-zhu-xiao-ma/) ，这个题解可以说是相当优雅了，如果不加解释，根本看不懂。对于**数组原地删除**类型的题目，肯定是双指针，**一个指向可以写入的位置，一个指向遍历的元素**，前者小于等于后者，关键在于题目条件的转化，如何实现限制最多两次的重复出现。

思路：由于本题允许重复的元素最多出现两次，那么，**数组的前两个元素无论如何是要保留的**。对于任意两个值，只有相等或不相等两种可能，这都符合题意。令left指向待插入的位置，right指向当前遍历的元素，初始化 left=2, right=2。现在考虑是否需要把nums[right]覆盖到nums[left]：

* 如果位置 left 的前两个值相等（即nums[left-1] == nums[left-2]）
  * 如果当前遍历的值nums[right]与待插入位置left之前的值相等（也就是nums[right] == nums[left-2] 或者 nums[right] == nums[left-1]），那么不能再把nums[right]覆盖到nums[left]，因为这样重复元素就会达到3个，不符合要求；
  * 如果当前遍历的值nums[right]与待插入位置left之前的值不相等，那么就要执行覆盖（也就是nums[left] = nums[right]）
* 如果位置 left 的前两个值不相等（即nums[left-1] != nums[left-2]），这种情况下，无论nums[right]是什么，都要将其移动至left处，因为此时nums[left-2, left-1, left]三个元素一定不会出现重复3次的情况。

代码：

```java
public int removeDuplicates(int[] nums) {
    if (nums.length <= 2) return nums.length;
    int left = 2;
    for (int right = 2; right < nums.length; right++) {
        if(nums[right] != nums[left-2])
            nums[left++] = nums[right];
    }
    return left;
}
```



**移除元素**（[27. Remove Element](https://leetcode-cn.com/problems/remove-element/)）**（有点意思~）**

思路：仍然采用双指针法，一个指针（len）表示待覆盖的节点，另一个指针（i）用于遍历数组，当nums[i] 等于val 时，跳过即可（因为这个位置会被覆盖的）；当nums[i] 不等于 val 时，就把nums[i] 复制到nums[len] 处，并增加len。

```java
// 时间复杂度：O(n)
// 空间复杂度：O(1)
public int removeElement(int[] nums, int val) {
    int len = 0;
    for (int i = 0; i < nums.length; i++) {
        if (nums[i] != val) nums[len++] = nums[i];
    }
    return len;
}
```

这只是一道easy题，看了自己的写法和参考写法，真是受到了打击。自己花了这么久也没有AC的主要原因是陷入了思维的误区（一直在想怎么用双指针进行交换，而没有考虑过覆盖的思路），完全没有想到参考解法的思路上去，非常值得学习！其实回头看，这一题和之前的[26. Remove Duplicates from Sorted Array](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array/) 是很像的，说明对这一类题型没有很好的掌握，尚没有形成第一思维。



**移动零**（[283. Move Zeroes](https://leetcode-cn.com/problems/move-zeroes/)）（有点意思~）

思路：本题虽然是easy题，但仍然有值得探讨的空间~在面试中遇到这题，如果能有解法2的思考，便是极好的！

代码

```java
// 解法1：覆盖法
public void moveZeroes(int[] nums) {
    int len = 0;
    for(int i=0; i<nums.length; i++){
        if(nums[i] != 0) nums[len++] = nums[i];
    }
    for(int i=len; i<nums.length; i++){
        nums[i] = 0;
    }
}

// 解法2：交换法
public void moveZeroes(int[] nums) {
    for (int left = 0, i = 0; i < nums.length; i++) {
        if (nums[i] != 0) {
            // 交换
            int t = nums[left];
            nums[left] = nums[i];
            nums[i] = t;
            left++;
        }
    }
}
```



**寻找重复元素**（[287. Find the Duplicate Number](https://leetcode-cn.com/problems/find-the-duplicate-number/)）**（非常好的思路~）**

给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n）。假设有且仅有一个重复的整数，找出这个重复的数。

```
示例 1:
输入: [1,3,4,2,2]
输出: 2

示例 2:
输入: [3,1,3,4,2]
输出: 3

说明：
1) 不能更改原数组（假设数组是只读的）。
2) 只能使用额外的 O(1) 的空间。
3) 时间复杂度小于 O(n^2) 。
4) 数组中只有一个重复的数字，但它可能不止重复出现一次。
```

思路：本题采用**二分法**，实在是太妙了，第一次看到这样的思路~，参考[这里](https://leetcode-cn.com/problems/find-the-duplicate-number/solution/er-fen-fa-si-lu-ji-dai-ma-python-by-liweiwei1419/)。

寻找数组中的重复项，首先想到的是利用**哈希**记录每个值出现的次数，但是这违反了空间复杂度只能为O(1)的限制；其次想到对原数组**先进行排序，让重复元素相邻**，但是这违反了”不能更改原数组“的限制。

这里先了解一个”抽屉原理“：即如果有十个苹果放到九个抽屉里，则至少有一个抽屉里有两个苹果。

怎么利用这个原理来理解这道题呢？对于一个长度为(n+1)的数组，数组内的元素值被限制在区间[1,n]内，这是题目给定的条件，也是本题的切入点。以数组nums = [1,3,4,2,2] 为例，元素值在区间[1,4]之间，那么区间[1,4]的中位数mid = (1+4)/2 = 2，遍历数组并统计**小于等于mid**的元素个数（记为count），如果count大于mid，说明重复元素一定在区间[1,mid]内（为什么呢？这里就是”抽屉原理“的理解运用，因为如果没有重复数字，那么**小于等于mid**的元素个数**至多**为mid个）；反之，说明重复元素在区间[mid+1, n]内。

代码：

```java
// 时间复杂度：O(n*log n) 二分法O(log n)，每次遍历数组O(n)
// 空间复杂度：O(1)
public int findDuplicate(int[] nums) {
    int n = nums.length - 1; // 数组中的元素在区间[1,n]内
    int left = 1, right = n; // 左右边界
    while (left < right) {
        int mid = (right - left) / 2 + left; // 切记不要写成(left+right)/2
        // 遍历数组
        int count = 0; // 记录数组中 nums[i] ≤ mid 的个数
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= mid) count++;
        }
        if (count > mid) right = mid;
        else left = mid+1;
    }
    return left;
}
```

还有一种时间复杂度为O(n)的方法，把数组看成是**静态形式**的链表的，然后利用快慢指针寻找链表环入口的方式来做（和[142. Linked List Cycle II](https://leetcode-cn.com/problems/linked-list-cycle-ii/) 一摸一样），这种方法真是耳目一新，具体参考[这里](https://leetcode-cn.com/problems/find-the-duplicate-number/solution/kuai-man-zhi-zhen-de-jie-shi-cong-damien_undoxie-d/) 。





**合并有序数组**（[88. Merge Sorted Array](https://leetcode-cn.com/problems/merge-sorted-array/)）

双指针常规题，从后向前遍历

```java
// 时间复杂度：O(n+m)
// 空间复杂度：O(1)
public void merge(int[] nums1, int m, int[] nums2, int n) {
    if (n == 0) return;
    if (m == 0) {
        // 把nums2[]中从0开始的n个元素复制到nums1[0...]中
        System.arraycopy(nums2, 0, nums1, 0, n);
        return;
    }
    int i = m - 1, j = n - 1, len = m + n - 1;
    while (i >= 0 && j >= 0) {
        if (nums1[i] >= nums2[j]) nums1[len--] = nums1[i--];
        else nums1[len--] = nums2[j--];
    }
    if (i == -1) {
        System.arraycopy(nums2,0,nums1,0,len+1);
//            while (j >= 0) {
//                nums1[len--] = nums2[j--];
//            }
    }
}
```





**接水最多的容器**（[11. Container With Most Water](https://leetcode-cn.com/problems/container-with-most-water/)）

思路：常规题。本题采用双指针法，根据常规思路即可接触

代码

```java
public int maxArea(int[] height) {
    int maxArea = 0;
    int left = 0, right = height.length-1;
    while(left < right) {
        int area = (right - left) * Math.min(height[left], height[right]);
        maxArea = Math.max(maxArea, area);
        if(height[left] <= height[right]) left++;
        else right--;
    }
    return maxArea;
}
```



**接雨水**（[42. Trapping Rain Water](https://leetcode-cn.com/problems/trapping-rain-water/)）（有点意思，多种方法~）

思路：

```java
// 解法1(约150ms)
// 时间复杂度：O(n^2)，空间复杂度：O(1)
public int trap(int[] height) {
    int total = 0;
    for (int k = 1; k < height.length - 1; k++) {
        int leftMax = 0, rightMax = 0;
        for (int i = 0; i < k; i++)
            leftMax = Math.max(leftMax, height[i]);
        for (int i = k + 1; i < height.length; i++)
            rightMax = Math.max(rightMax, height[i]);
        if(leftMax > height[k] && rightMax > height[k])
            total += Math.min(leftMax, rightMax) - height[k];
    }
    return total;
}

//解法2(约1ms)
// 时间复杂度：O(n)，空间复杂度：O(n)
public int trap(int[] height) {
    // 令leftMax[i]表示i左侧的最大高度，rightMax[i]表示i右侧的最大高度
    int[] leftMax = new int[height.length];
    int[] rightMax = new int[height.length];
    for (int i = 1; i < height.length; i++)
        leftMax[i] = Math.max(height[i - 1], leftMax[i - 1]);
    for (int i = height.length - 2; i >= 0; i--)
        rightMax[i] = Math.max(height[i + 1], rightMax[i + 1]);
    int total = 0;
    for (int i = 1; i < height.length - 1; i++) {
        if (leftMax[i] > height[i] && rightMax[i] > height[i]) {
            total += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
    }
    return total;
}

//解法3（双指针法）
// 时间复杂度：O(n)，空间复杂度：O(1)



//解法4（栈）
// 时间复杂度：O(n)，空间复杂度：O(n)
```



**荷兰国旗问题/三色旗排序**（[75. Sort Colors](https://leetcode-cn.com/problems/sort-colors/)）**（有点意思~）**

给定一个包含红色、白色和蓝色，一共 n 个元素的数组，**原地(in-place)**对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。不能使用代码库中的排序函数来解决这道题。

思路：

做这一题的感觉就是，整体的思路很简单，很快就能想到，但是细节（也就是如何设置循环结束这一问题）死活写不出来！反映出：①代码能力确实差；②如何进行程序设计缺少相应的方法论。最后参考[这里](https://leetcode-cn.com/problems/sort-colors/solution/kuai-su-pai-xu-partition-guo-cheng-she-ji-xun-huan/)学到了该如何设计边界/循环结束条件。

根据题意，要求把所有0放在数组的最前面，所有1放中间，所有2放在最后，也就是把数组分解成3个区间（显然需要两个标记把数组分隔成3个区间，由此立马想到这很可能利用双指针解法）。在写代码前，进行如下设计：

* [0, left) 放置0
* [left, i)  放置1
* [right, length) 放置2

注意，我们把区间统一设计成左闭右开的形式，便于处理，这一点非常重要！

left 和 right 用于分隔数组，而 i 是当前遍历数组的指针。初始化 left = 0, right = length，很多人（包括我）根据以往的做题经验往往会把 right 初始化成 length-1，虽然不是不可以，但在本题的语境中就不统一了，有的区间左闭右开，有的区间左闭右闭，会增加编码的复杂性；而循环结束的条件是 i == right，这根据前面的设计应该也不难理解。

```java
// 时间复杂度：O(n) （一趟扫描）
// 空间复杂度：O(1)
public void sortColors(int[] nums) {
    if(nums.length == 1) return;
    int left = 0, right = nums.length;
    int i = 0;
    while (i < right) {
        if (nums[i] == 0) {
            swap(nums, left, i);
            left++;
            i++;
        } else if (nums[i] == 1) {
            i++;
        } else {
            right--;
            swap(nums, right, i);
        }
    }
}
private void swap(int[] nums, int i, int j){
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
}
```

这里需要注意 Java 中的swap()写法。在 Java 中，并不存在像C++那样通用的swap()函数，要交换两个数的值，也无法像C++那样写成`swap(int &a, int &b)`，因为 Java 对于基本数据类型不采用引用传递。但是对于数组是引用传递的，所以一般写成`swap(int[] nums, int i, int j)` 。

此外还有另外一种解法，在follow up有提示，使用**计数排序**的两趟扫描算法。首先，迭代计算出0、1 和 2 元素的个数，然后按照0、1、2的排序，重写当前数组。这种方法的时间复杂度也是O(n)，只不过需要两趟扫描；空间复杂度也是O(1)，只需要3个辅助变量。

```java
public void sortColors(int[] nums) {
    int[] count = new int[3];//count[i]表示数组中元素i的个数
    for (int num : nums)
        count[num]++;
    int len = 0;
    for(int i = 0; i < 3; i++){
        while (count[i] > 0){
            nums[len++] = i;
            count[i]--;
        }
    }
}
```



长度最小的子数组（[209. Minimum Size Subarray Sum](https://leetcode-cn.com/problems/minimum-size-subarray-sum/)）（常规题，比较简单）

给定一个含有 **n **个正整数的数组和一个正整数 **s ，**找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。

```
输入: s = 7, nums = [2,3,1,2,4,3]
输出: 2
解释: 子数组 [4,3] 是该条件下的长度最小的连续子数组。
```

本题采用双指针法，常规题。

```java
// 时间复杂度：O(n)
// 空间复杂度：O(1)
public int minSubArrayLen(int s, int[] nums) {
    int sum = 0, minLen = Integer.MAX_VALUE, left = 0;
    for (int right = 0; right < nums.length; right++) {
        sum += nums[right];
        while (sum >= s) {
            minLen = Math.min(minLen, right - left + 1);
            sum -= nums[left];
            left++;
        }
    }
    return minLen == Integer.MAX_VALUE ? 0 : minLen;
}
```





**实现strStr()**（[28. Implement strStr()](https://leetcode-cn.com/problems/implement-strstr/)）

要求实现类似 Java 中indexOf(String s)的功能，给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle 字符串出现的第一个位置 (从0开始)。如果不存在，则返回  -1。特别的，当 needle 为空字符串时，返回0。比如：

```
输入: haystack = "hello", needle = "ll"
输出: 2
输入: haystack = "aaaaa", needle = "bba"
输出: -1
```

思路：如果调库函数，只需要一行代码。问题本质上应该是考察**KMP算法**。

KMP算法：







