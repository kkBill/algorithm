## 二分法问题汇总

【写在前面】

二分查找问题虽然看起来很简单，但在编写代码时就是会出错，要么会漏个等号，要么少加个 1等等。不要气馁，因为二分查找其实并不简单。看看 Knuth 大佬（发明 KMP 算法的那位）是怎么说的：

> Although the basic idea of binary search is comparatively straightforward, the details can be surprisingly tricky...

So，关注细节，不断练习！

题目列表：
  - [704. 二分查找](#704-二分查找)
  - [34. 在排序数组中查找元素的第一个和最后一个位置](#34-在排序数组中查找元素的第一个和最后一个位置)
  - [35. 搜索插入位置](#35-搜索插入位置)
  - [33. 搜索旋转有序数组](#33-搜索旋转有序数组)
  - [81. 搜索旋转有序数组Ⅱ](#81-搜索旋转有序数组ⅱ)
  - [153. 寻找旋转有序数组中的最小值](#153-寻找旋转有序数组中的最小值)
  - [154. 寻找旋转有序数组中的最小值Ⅱ](#154-寻找旋转有序数组中的最小值ⅱ)
  - [74. 搜索二维矩阵](#74-搜索二维矩阵)
  - [240. 搜索二维矩阵 II](#240-搜索二维矩阵-ii)
  - [69. x 的平方根](#69-x-的平方根)
  - [367. 有效的完全平方数](#367-有效的完全平方数)
  - [50. Pow(x, n)](#50-powx-n)
  - [278. 第一个错误的版本](#278-第一个错误的版本)
  - [275. H指数 II](#275-h指数-ii)
  - [274. H指数](#274-h指数)
  - [162. 寻找峰值](#162-寻找峰值)
  - [4. 寻找两个有序数组的中位数](#4-寻找两个有序数组的中位数)
  - [29. 两数相除](#29-两数相除)

---

二分查找的前提

* 目标函数的单调性
* 存在上下界
* 能够通过索引访问

基础模板

```java
public int binarySearch(int[] array, int target) {
    int left = 0, right = array.length - 1, mid = 0;
    while (left <= right) { // “≤”还是“＜”，具体问题具体分析
        mid = (right - left) / 2 + left;
        if (array[mid] == target) {
            return mid;
        } else if (array[mid] > target) {
            right = mid - 1; // 具体问题具体分析
        } else {
            left = mid + 1; // 具体问题具体分析
        }
    }
    return -1;
}
```



### [704. 二分查找](https://leetcode-cn.com/problems/binary-search/)

给定一个 n 个元素**有序**的（升序）整型数组 nums 和一个**目标值 target**  ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。

```
示例 1:
输入: nums = [-1,0,3,5,9,12], target = 9
输出: 4
解释: 9 出现在 nums 中并且下标为 4

示例 2:
输入: nums = [-1,0,3,5,9,12], target = 2
输出: -1
解释: 2 不存在 nums 中因此返回 -1


提示：
你可以假设 nums 中的所有元素是不重复的。
n 将在 [1, 10000]之间。
nums 的每个元素都将在 [-9999, 9999]之间。
```

分析：这是二分查找最基础的模板题。但是模板也有不同的写法，当场景变得复杂之后，选择不同的模板可能对编码有不同的影响，因此需要深入学习。

* 模板1采用的是**左闭右闭**，即`[left, right]`

* 模板2采用的是**左闭右开**，即`[left, right)`

这两种方式的边界取值不一样，从而while循环的判断条件、边界更新不一样。两种方式在之后的习题中均有应用，需要结合题目理解。

```java
//模板1
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length-1, mid = 0;
        while (left <= right){
            mid = (right - left)/2 + left;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
//模板2
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length, mid = 0;
        while (left < right){
            mid = (right - left)/2 + left;
            if(nums[mid] == target) {
                return mid;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return -1;
    }
}
```



### [34. 在排序数组中查找元素的第一个和最后一个位置](https://leetcode-cn.com/problems/find-first-and-last-position-of-element-in-sorted-array/)

给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的**开始位置和结束位置**。你的算法时间复杂度必须是 **O(log n)** 级别。如果数组中不存在目标值，返回 [-1, -1]。

```
示例 1：
输入：nums = [5,7,7,8,8,10], target = 8
输出：[3,4]

示例 2：
输入：nums = [5,7,7,8,8,10], target = 6
输出：[-1,-1]
```

分析：首先按照常规的二分查找思路来查询目标值，当查询到目标值之后，先判断该位置是否是数组的边界，如果是，说明该位置就是目标值的开始位置（或结束位置）；如果不是，则继续向两边执行二分查找。这里分别使用两个函数`findFirst()` 和 `findLast()` 来查找数组的开始位置和结束位置。

```java
// 时间复杂度：O(log n) --> 两个子问题都是二分查找
// 空间复杂度：O(1)
class Solution {
    public int[] searchRange(int[] nums, int target) {
        int start = findFirst(nums, target);
        if(start == -1) {
            return new int[]{-1, -1};
        }
        int end = findLast(nums, target);
        return new int[]{start, end};
    }
    private int findFirst(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right){
            mid = (right - left)/2 + left;
            if(target == nums[mid]) {
                // 判断边界
                if(mid == 0 || (mid > 0 && nums[mid-1] != nums[mid])) {
                    return mid;
                }else {
                    right = mid - 1;
                }
            }else if(nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }
    private int findLast(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while (left <= right){
            mid = (right - left)/2 + left;
            if(target == nums[mid]) {
                // 判断边界
                if(mid == nums.length - 1 || (mid < nums.length - 1 && nums[mid+1] != nums[mid])) {
                    return mid;
                }else {
                    left = mid + 1;
                }
            }else if(nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
```



### [35. 搜索插入位置](https://leetcode-cn.com/problems/search-insert-position/)

给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。

你可以假设数组中无重复元素。

```
示例 1:
输入: [1,3,5,6], 5
输出: 2

示例 2:
输入: [1,3,5,6], 2
输出: 1

示例 3:
输入: [1,3,5,6], 7
输出: 4

示例 4:
输入: [1,3,5,6], 0
输出: 0
```

分析：略

```java
class Solution {
    public int searchInsert(int[] nums, int target) {
        int left = 0, right = nums.length-1, mid = 0;
        while (left <= right){
            mid = (right - left)/2 + left;
            if(target == nums[mid]) {
                return mid;
            }else if(nums[mid] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return left;
    }
}
```



### [33. 搜索旋转有序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)

给你一个整数数组 nums ，和一个整数 target 。

该整数数组原本是按升序排列，但输入时在预先未知的某个点上进行了旋转。（例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] ）。请你在数组中搜索 target ，如果数组中存在这个目标值，则返回它的索引，否则返回 -1 。假设数组中**不存在重复的元素**。算法时间复杂度必须是 **O(log n)** 级别。

```
示例 1：
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4

示例 2：
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```

分析：一般来说，时间复杂度要求O(log n)级别的，即暗示我们考虑二分法。

对于旋转有序数组 nums ，可分为两个有序数组 nums1 和 nums2，并且 nums1任一元素 >= nums2任一元素。采用二分法，令 left 和 right 初始化为0 和 length-1，mid 为每次二分的中点，显然，mid 不是落在区间 nums1 中，就是落在区间 nums2 中，这里以 nums[mid] 和 nums[right] 比较进行说明。

* 若 `nums[mid] < nums[right]`，说明`[mid, right]`是有序的。而**目标值可能落在 `[mid, right]`这个有序区间内，也可能落在区间`[left, mid-1]` 内**。我们先判断`target`是否落在有序区间` [mid, right] `内，因为这个判断条件好写；而区间 `[left, mid]` 内的值有可能是先增后减的，我们无法通过`target`与边界值进行比较来判断。
  * 如果 `target` 落在` [mid, right]`这个有序区间内（判断条件为 `nums[mid]<target && target<=nums[right]`），则调整 `left = mid+1`;
  * 反之，调整 `right = mid-1`;
* 反之，说明`[left, mid]`是有序的。而目标值` target` 可能落在 `[left, mid]`这个有序区间内，也可能落在区间 `[mid+1, right]`内。
  * 如果 `target` 落在 `[left, mid]` 内（判断条件为`nums[left]<=target && target<nums[mid]`），则调整`right = mid-1`；
  * 反之，`target` 落在` [mid+1, right]`内，调整 `left = mid+1`。

需要考虑如下几个case：

```
nums = [2,1], target = 1
nums = [2,1], target = 3
```

代码实现：

```java
// 时间复杂度：O(log n)
class Solution {
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while(left <= right) {
            mid = (right - left) / 2 + left;
            if(nums[mid] == target) {
                return mid;
            }
            if(nums[mid] < nums[right]) { // nums[mid...right]局部有序
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }else {                       // nums[left...mid]局部有序
                if(nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }
        return -1;
    }
}
```



### [81. 搜索旋转有序数组Ⅱ](https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] 

编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。

```
示例 1:
输入: nums = [2,5,6,0,0,1,2], target = 0
输出: true

示例 2:
输入: nums = [2,5,6,0,0,1,2], target = 3
输出: false
```

进阶：本题中的 nums  可能包含重复元素。
**这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？**

分析：按照第33题的逻辑，最开始，我是这么写的：

```java
class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while(left <= right) {
            mid = (right - left) / 2 + left;
            if(nums[mid] == target) {
                return true;
            }
            if(nums[mid] <= nums[right]) { // 语句1
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }else {                       
                if(nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }
        return false;
    }
}
```

我只在语句1中由原来的`nums[mid] < nums[right]`改为`nums[mid] <= nums[right]`，提交后出现如下这个case过不去：

```
nums   = [1,1,3,1]
target = 3
```

而理解这个case正是解决本题的关键。

由于数组中存在重复元素，因此，不能照搬第33题的逻辑（虽然我考虑了元素相等的情况，但考虑的还是不全面）。这个case表明，**即使`nums[mid] <= nums[right]`，也不能明确说区间`[mid, right]`是有序的**。由于我在做这类题时，总是优先考虑右半区间，因此在本题中，如果存在`nums[mid] == nums[right]`，先线性的缩小`right`的范围，从而写出如下代码。只有当`nums[mid]` 和`nums[right]`是不等的，才能保证区间`[mid, right]`是有序的。

```java
// 时间复杂度：best->O(logn); worst->O(n)
class Solution {
    public boolean search(int[] nums, int target) {
        int left = 0, right = nums.length - 1, mid = 0;
        while(left <= right) {
            mid = (right - left) / 2 + left;
            if(nums[mid] == target) {
                return true;
            }
            if(nums[mid] == nums[right]) { // 与第33题唯一的区别
                right--;
            }else if(nums[mid] < nums[right]) { // nums[mid...right]局部有序
                if(nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1;
            }else {                             // nums[left...mid]局部有序
                if(nums[left] <= target && target < nums[mid]) right = mid - 1;
                else left = mid + 1;
            }
        }
        return false;
    }
}
```



### [153. 寻找旋转有序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。假设数组中**不存在重复的元素**。请找出其中最小的元素。

```
示例 1：
输入：nums = [3,4,5,1,2]
输出：1

示例 2：
输入：nums = [4,5,6,7,0,1,2]
输出：0
```

分析：旋转排序数组 nums 可以被拆分为 2 个排序数组 nums1, nums2，并且 nums1任一元素 >= nums2任一元素；因此，考虑二分法寻找此两数组的分界点 nums[i] (即**第 2 个数组的首个元素**)。
设置`left`，`right`指针在 nums数组两端，`mid`为每次二分的中点：

- 当`nums[mid]<nums[right]`时，说明`[mid,right]`为部分递增序列，那么最小值肯定不会出现在区间`(mid,right]`内（这里是左开又闭区间哦~），注意，元素`nums[mid]`可能是最小值。因此更新右边界，即`right = mid`；
- 当`nums[mid]>nums[right]`时，说明`[left,mid]`为部分递增序列，而区间`(mid,right]`内必然会出现转折点，即最小值会出现在`(mid,right]`区间内。因此更新左边界，即`left = mid + 1`。

```java
// 时间复杂度：O(log n)
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1, mid = 0;
        while(left < right) {
            mid = (right - left) / 2 + left;
            if(nums[mid] < nums[right]) { // [mid,right]为递增序列
                right = mid;
            }else {                       // [left,mid]为递增序列
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
```



### [154. 寻找旋转有序数组中的最小值Ⅱ](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/)

假设按照升序排序的数组在预先未知的某个点上进行了旋转。例如，数组 [0,1,2,4,5,6,7] 可能变为 [4,5,6,7,0,1,2] 。

请找出其中最小的元素。注意数组中**可能存在重复的元素**。

```
示例 1：
输入: [2,2,2,0,1]
输出: 0
```

分析：154题对比与153题的修改，等价于81题对比与33题的修改。

```java
// 时间复杂度：best->O(log n); worst->O(n)
class Solution {
    public int findMin(int[] nums) {
        int left = 0, right = nums.length - 1, mid = 0;
        while(left < right) {
            mid = (right - left) / 2 + left;
            if(nums[mid] == nums[right]) {
                right--;
            }else if(nums[mid] < nums[right]) { // [mid,right]为递增序列
                right = mid;
            }else {                             // [left,mid]为递增序列
                left = mid + 1;
            }
        }
        return nums[left];
    }
}
```



### [74. 搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)

编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：

* 每行中的整数从左到右按升序排列。
* **每行的第一个整数大于前一行的最后一个整数。**

```
输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,50]], target = 3
输出：true
```

分析：由于“每行的第一个整数大于前一行的最后一个整数”，所以可以把二维矩阵当作一维数组来看，做法也一模一样，只是多了一步“一维坐标转化成二维坐标”的过程。

```java
// 时间复杂度：O(log(m*n))
// 空间复杂度：O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if(m == 0) return false;
        int n = matrix[0].length;
        int left = 0, right = m*n-1, mid = 0;
        while(left <= right) {
            mid = (right - left) / 2 + left;
            // 一维坐标转化为二维坐标
            int row = mid / n;
            int col = mid % n;
            if(matrix[row][col] == target) {
                return true;
            }else if(matrix[row][col] < target) {
                left = mid + 1;
            }else {
                right = mid - 1;
            }
        }
        return false;
    }
}
```



### [240. 搜索二维矩阵 II](https://leetcode-cn.com/problems/search-a-2d-matrix-ii/)

编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target。该矩阵具有以下特性：

* 每行的元素从左到右升序排列。
* **每列的元素从上到下升序排列。（区别于上一题）**

```
示例：
[
  [1,   4,  7, 11, 15],
  [2,   5,  8, 12, 19],
  [3,   6,  9, 16, 22],
  [10, 13, 14, 17, 24],
  [18, 21, 23, 26, 30]
]
```

分析：首先，我们初始化一个指向矩阵左下角的 (row，col) 指针。**如果当前指向的值大于目标值，则“向上” 移动一行；如果当前指向的值小于目标值，则“向右”移动一列**。时间复杂度为O(m+n)，虽然从时间复杂度来看不是最优的，但一定是最简洁的。（参考自官方题解）

```java
// 时间复杂度：O(m+n)
// 空间复杂度：O(1)
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        // 起点初始化为矩阵的左下角
        int row = m - 1, col = 0;
        while (row >= 0 && col < n) {
            if (matrix[row][col] == target) return true;
            else if (matrix[row][col] > target) row--;
            else col++;
        }
        return false;
    }
}
```

注：`O(m+n)`的时间复杂度并不是最优的，也没有利用“二分”的思想，但代码确实是最简洁的。

关于本题，更详尽的可参考：https://stackoverflow.com/questions/2457792/how-do-i-search-for-a-number-in-a-2d-array-sorted-left-to-right-and-top-to-botto/2458113



### [69. x 的平方根](https://leetcode-cn.com/problems/sqrtx/) 

实现 `int sqrt(int x)` 函数。

计算并返回 x 的平方根，其中 x 是非负整数。

由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。

```
示例 1:
输入: 4
输出: 2

示例 2:
输入: 8
输出: 2
说明: 8 的平方根是 2.82842..., 由于返回类型是整数，小数部分将被舍去。
```

分析：分别用二分法和牛顿法来计算。关注细节问题，比如在两个int 型整数相乘时，需要把`int`转换成`long`，以此防止结果溢出。 

方法1：牛顿迭代法

令`f(x)=x^2 - num` ，我们要设法找到一个整数根。我们假定从`x_k`开始，不断向最后的解逼近（逼近在编码时就是一次迭代）。怎么逼近呢？过程如下：

```
假设当前的值为 x_k，函数 f(x) 在该点 (x_k, f(x_k)) 处的切线与 x 轴交点的横坐标为 x_k+1
斜率等于：f(x_k)/(x_k - x_k+1) = f'(x_k)    -- 等式1
由于
f(x_k) = x_k^2 - num                       -- 等式2
f'(x_k) = 2*x_k                            -- 等式3
把等式2，3代入等式1，计算得到 x_k+1 和 x_k 的关系 （最简单的二元一次方程），如下：
x_k+1 = (x_k + num/x_k) / 2
而这，也就是迭代式。
```

![在这里插入图片描述](https://imgconvert.csdnimg.cn/aHR0cHM6Ly9waWMubGVldGNvZGUtY24uY29tL0ZpZ3VyZXMvMzY3L3BhcmFib2xhNC5wbmc?x-oss-process=image/format,png)

评论区找到一个动图

![img](https://pic.leetcode-cn.com/e6550b4a77fbe722a9a4634619ece70e8b7e60ef7eb2e5b7af7bba3037308879-file_1563817671864)

代码：

```java
// 如果是要返回尽量精确的值
public double mySqrt(int x) {
    if(x == 0) return 0;
    double x0 = x;
    double x1 = (x0 + x/x0)/2;
    // 1e-6 是科学计数法，表示 1*10^(-6)，也就是 0.000001
    // 用来抵消浮点运算中因为误差造成的相等无法判断的情况，它通常是一个非常小的数字，
    // 具体多小要根据要求的精度来设置
    double precision = 1e-6;
    while (Math.abs(x1 - x0) > precision){
        x0 = x1;
        x1 = (x0 + x/x0)/2;
    }
    return x1;
}

// 本题中，只需返回整数部分，因此没必要把精度设置得这么小，设精度为1即可
public int mySqrt(int x) {
    if(x == 0) return 0;
    double x0 = x;
    double x1 = (x0 + x/x0)/2;
    while (Math.abs(x1 - x0) >= 1){
        x0 = x1;
        x1 = (x0 + x/x0)/2;
    }
    return (int)x1;
}
```

方法2：二分法

```java
// 如果要求返回精确值
public double mySqrt(int x) {
    if (x == 0 || x == 1) return x;
    double left = 1.0, mid = 0.0, right = x, diff = 0.0000001;
    while (right - left > diff) {
        mid = (right - left) / 2 + left;
        double t = mid * mid;
        if (t - x > 0.0) right = mid;
        else if(t - x < 0.0) left = mid;
        else break;
    }
    return right;
}

// 本题
public int mySqrt(int x) {
    if (x == 0) return x;
    long tempSquare;
    int left = 1, right = x, mid;
    while (left <= right) {
        mid = (right - left) / 2 + left;
        tempSquare = (long)mid * mid; // 注意：写成 tempSquare = mid*mid; 会溢出，非常细节~
        if (tempSquare > x) right = mid-1;
        else if (tempSquare < x) left = mid+1;
        else return mid;
    }
    return right;
}
```



### [367. 有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)

给定一个正整数 *num*，编写一个函数，如果 *num* 是一个完全平方数，则返回 True，否则返回 False。

方法一：牛顿迭代法

```java
/**
 * 牛顿迭代法
 * 时间复杂度：O(log n)，这个时间复杂度怎么算的？
 */
class Solution {
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        long x = num / 2; // 写成int x=num/2 会出错，意识到这一点非常重要！
        while (x * x > num) {
            x = (x + num / x) / 2;
        }
        return x * x == num;
    }
}
```

方法二：二分法

```java
// 时间复杂度：O(log n)
class Solution {
    public boolean isPerfectSquare(int num) {
        // 使用 long 是为了在 mid * mid 时避免溢出 
        long left = 1, right = num, mid = 0, tempSquare = 0;
        while (left <= right) {
            mid = (right - left)/2 + left;
            tempSquare = mid * mid;
            if(tempSquare > num) right = mid - 1;
            else if(tempSquare < num) left = mid + 1;
            else return true;
        }
        return false;
    }
}
```



### [50. Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)

实现 `pow(x, n)` ，即计算 x 的 n 次幂函数。

说明:

* -100.0 < x < 100.0
* n 是 32 位有符号整数，其数值范围是 [−2^31, 2^31 − 1] 。

```
示例 1:
输入: 2.00000, 10
输出: 1024.00000

示例 2:
输入: 2.10000, 3
输出: 9.26100

示例 3:
输入: 2.00000, -2
输出: 0.25000
```

分析：本题也是二分法的一个典型例题。假设F(x,n)表示x^n，那么：

* 当n为偶数时，F(x, n) = F(x, n/2) * F(x, n/2)
* 当n为奇数时，F(x, n) = F(x, n/2) * F(x, n/2) * x

但需要特别注意，像下面这么写是会出错的！这种边界情况一定需要注意。

```java
// 错误示例~
class Solution {
    public double myPow(double x, int n) {
        if(x == 1.0 || n == 0) {
            return 1.0;
        }
        if(n < 0) {
            return 1.0 / myPow(x, -n); // 当n=-2147483648，取-n会发生溢出
        }
        double val = myPow(x, n / 2);
        if(n % 2 == 1) {
            return val * val * x;
        }else {
            return val * val;
        }
    }
}
```

方法1：递归写法

```java
// 时间复杂度：O(logn)
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        return N < 0 ? 1.0 / F(x, -N) : F(x, N);
    }

    private double F(double x, long n) {
        // 判断浮点数相等，不能直接使用 x == 1.0
        if(n == 0 || Math.abs(x - 1.0) < 0.00000001) return 1.0;
        double half = F(x, n/2);
        if(n % 2 == 0) {
            return half * half;
        }else {
            return half * half * x;
        }
    }
}
```

方法2：迭代写法（第2遍、第3遍做仍然不知道是如何想到的，😢）

```java
class Solution {
    public double myPow(double x, int n) {
        long N = n;
        if(N < 0) {
            x = 1 / x;
            N = -N;
        }
        double res = 1.0;
        double half = x;
        for(long i = N; i > 0; i /= 2) {
            if(i % 2 != 0) {
                res *= half;
            }
            half *= half;
        }
        return res;
    }
}
```

迭代写法的另一种实现

```java
class Solution {
    public double myPow(double x, int n) {
        double res = 1.0;
        double half = x;
        // 注意，这里的判断条件不能再是 i > 0，因为 i 可能是负数
        for(int i = n; i != 0; i /= 2) { 
            if(i % 2 != 0) {
                res *= half;
            }
            half *= half;
        }
        return n >= 0 ? res : 1.0 / res;
    }
}
```



### [278. 第一个错误的版本](https://leetcode-cn.com/problems/first-bad-version/)

你是产品经理，目前正在带领一个团队开发新的产品。不幸的是，你的产品的最新版本没有通过质量检测。**由于每个版本都是基于之前的版本开发的，所以错误的版本之后的所有版本都是错的**。

假设你有 n 个版本 [1, 2, ..., n]，你想**找出导致之后所有版本出错的第一个错误的版本**。

你可以通过调用`bool isBadVersion(version)`接口来判断版本号 version 是否在单元测试中出错。实现一个函数来查找第一个错误的版本。你应该尽量减少对调用 API 的次数。
```
示例:
给定 n = 5，并且 version = 4 是第一个错误的版本。

调用 isBadVersion(3) -> false
调用 isBadVersion(5) -> true
调用 isBadVersion(4) -> true
所以，4 是第一个错误的版本。
```
分析：根据题意理解后翻译即可。
```java
// 时间复杂度：O(log n)
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int left = 1, right = n, mid = 0;
        while(left < right) {
            mid = (right - left) / 2 + left;
            if(isBadVersion(mid)) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
}
```

### [275. H指数 II](https://leetcode-cn.com/problems/h-index-ii/)

给定一位研究者论文被引用次数的数组（被引用次数是**非负整数**），**数组已经按照升序排列**。编写一个方法，计算出研究者的 h 指数。h 指数的定义:  h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他的 N 篇论文中**至多有 h 篇论文分别被引用了至少 h 次**。（其余的 N - h 篇论文每篇被引用次数**不多于** h 次。），要求时间复杂度为O(log n)

```
输入: citations = [0,1,3,5,6]
输出: 3 
解释: 给定数组表示研究者总共有5篇论文，每篇论文相应的被引用了 0, 1, 3, 5, 6 次。由于研究者有3篇论文每篇至少被引用了3次，其余两篇论文每篇被引用不多于3次，所以她的h指数是3。

说明:
如果h有多有种可能的值,h指数是其中最大的那个。
```

思路：本题读起来有点绕，就是给定一个有序数组，要求找出符合要求的最大的h，这个h的含义是：**数组中最多有h个数，这h个数的值均大于等于h**。由于给定的数组已经是有序的，且要求对数级别的复杂度，很显然在暗示我们用二分法来解决此问题。

假设某一元素的值是`nums[i]`，由于数组是升序的，可以计算出元素值大于等于`nums[i]`的个数是 `nums.length - i`。根据h的定义，只需要找到**第一个尽量小的**元素值`nums[i]`，其满足`nums[i] >= nums.length - i`即可。

根据上述讨论，可以很快写出代码：

```java
// 时间复杂度：O(n)
public int hIndex(int[] citations) {
  	int n = citations.length;
    for (int i = 0; i < n; i++) {
        if (citations[i] >= n - i) return n - i;
    }
    return 0;
}
```

这个代码很简单，时间复杂度为`O(n)`，但是我们还可以将其优化到对数级别。

做二分法的问题，一定要多举几个例子来斧正自己的想法，普通的例子、特殊的边界例子等等。下面通过这3个例子来说明问题。

```
case 1:
[0,1,3,5,6]
 |   |   | 
nums[mid] == count, 大于等于3的个数正好恰有3个（个数=nums.length-mid），这个例子太巧了，貌似不好说明问题。
case 2:
[0,1,2,5,6]  
 |   |   |    
nums[mid] = 2，count=nums.length - mid=3
因为nums[mid] < count，因此不能说最多存在3个数，且这3个数的值均大于等于3，于是下一轮计算应该在后半部分进行。调整边界 left=mid+1
case 3:
[1,4,5,5,6]
 |   |   |
nums[mid] = 5，count=nums.length - mid=3
因为nums[mid] > count，说明数组中大于等于3的元素个数可能不止3个，因此下一轮计算应该在前半部分进行。调整边界 right=mid

然后再考虑一些特殊情况来验证代码的鲁棒性，比如：
nums = [0]
对应的H_index = 0
```
在这个问题中，**下一个分支往哪儿走取决于中间点(`nums[mid]`)的大小和数组右半边的个数**。通过二分法，我们要做的，就是找到**第一个尽量小的**元素值`nums[i]`，使其满足`nums[i] >= nums.length-i`，而`nums.length-i`就是本题的H指数。

代码如下：
```java
// 时间复杂度：O(log n)
class Solution {
    public int hIndex(int[] nums) {
        int left = 0, right = nums.length, mid = 0; // 采用左闭右开的方式
        while(left < right) {
            mid = (right - left) / 2 + left;
            if(nums[mid] >= nums.length - mid) {
                right = mid;
            }else {
                left = mid + 1;
            }
        }
        return nums.length - left;
    }
}
```

### [274. H指数](https://leetcode-cn.com/problems/h-index/) 

问题同上，只是给定的数组没有排序。因此不能采用二分法。

思路：由于做了之前那一题，首先想到的是**先把原数组排序**，然后使用顺序搜索（O(n)）或二分搜索（O(log n)）来做。但是不管采用哪种搜索，由于需要排序，时间复杂度的下限就是O(n*log n)。

基于比较的排序算法存在时间复杂度下界 O(n*log n)，如果要得到时间复杂度更低的算法，就必须考虑**不基于比较的排序**，比如计数排序（或称桶排序）。

由于论文的引用次数可能会非常多，这个数值很可能会超过论文的总数 n，因此使用计数排序是非常不合算的（会超出空间限制）。在这道题中，我们可以通过一个不难发现的结论来让计数排序变得有用，即：

> 如果一篇文章的引用次数超过论文的总数 n，那么将它的引用次数降低为 n 也不会改变 h 指数的值。

由于 h 指数一定小于等于 n，因此这样做是正确的。（分析参考自[这里](https://leetcode-cn.com/problems/h-index/solution/hzhi-shu-by-leetcode/)）

我们用一个例子来说明如何使用计数排序得到 hh 指数。首先，引用次数如下所示：

citations=[1,3,2,3,100]

将所有大于 n=5的引用次数变为 n，得到：

citations=[1,3,2,3,5]

计数排序得到的结果如下：

|   k    | 0    | 1    | 2    | 3    | 4    | 5    |
| :----: | ---- | ---- | ---- | ---- | ---- | ---- |
| bucket | 0    | 1    | 1    | 2    | 0    | 1    |
|  S_k   | 5    | 5    | 4    | 3    | 1    | 1    |

其中 S_k 表示至少有 k 次引用的论文数量，比如S_2表示至少有2次引用的论文数量，其值等于上表中bucket[k:n]的和。根据定义，最大的满足 k ≤ S_k 的 k 即为所求的h。在表中，这个 k 为 3，因此 h指数为 3。

```java
public int hIndex(int[] citations) {
    // 修正数据
    int n = citations.length;
    for (int i = 0; i < n; i++) {
        if (citations[i] > n) citations[i] = n;
    }
    // 计数排序
    int[] bucket = new int[n + 1];
    for (int c : citations) {
        bucket[c]++;
    }
    // sk 表示数组元素大于等于k的个数
    int sk = 0, k;
    for (k = n; k > 0; k--) {
        sk += bucket[k]; // bucket[k] 保存了数组中值为k的个数 
        if (sk >= k) break;
    }
    return k;
}
```

补充：

什么是计数排序（countn sort）（也叫桶排序（bucket sort））？

> 如果已知n个关键字，其取值范围在0~m-1之间，则桶排序算法将每个可能的取值建立一个桶，即建立m个桶，扫描n个关键字时，将值放入相应的桶内，然后按桶的顺序收集一遍就自然有序了。
>
> 基于“比较”的排序算法的时间复杂度至少是O(n*log n)，而桶排序并不是基于“比较”的排序算法，其时间复杂度是线性的，比任何基于“比较”的排序算法都快。
>
> 但是这种排序算法也是有限制的，也就是关键字的取值范围要确定，不然就不能建立相应的“桶”了，并且关键字取值的跨度不能太大，最好集中某一范围，否则非常浪费空间，当m远小于n的时候更能体现桶排序的优势。

示例：已知某公选课有1500名学生选修，该课程的成绩是[0,100]之间的整数。现在需要将学生信息按照成绩从低到高打印出来。

分析：这一场景就非常适合使用桶排序（因为取值范围确定，且比较集中不分散，数值还不大）。若先将成绩排序再打印学生信息，则至少需要O(n*log n)的时间复杂度，这里的n等于1500。而使用桶排序，只需要建立101个桶，然后扫描一遍n个人的成绩，将成绩为 i 的放入 i 号桶即可，最后顺序读取0~100个桶，整个过程只需要**O(n+m)**的时间复杂度，当m远小于n时，时间复杂度约等于O(n)。（**注：n是关键字的个数，m是关键字的取值范围**）

```java
public static void countSort(int[] nums) {
    if (nums.length < 2) return;
    int minVal = nums[0], maxVal = nums[0];
    for (int num : nums) {
        minVal = Math.min(minVal, num);
        maxVal = Math.max(maxVal, num);
    }
    // 优化，如果按照maxVal建立桶太浪费空间了
    // 比如出现[100, 93, 97, 92, 96, 99, 92, 89, 93, 97, 90, 94, 92, 95]这种情况
    int m = maxVal - minVal + 1;
    // 建立 m 个"桶"
    int[] bucket = new int[m]; // bucket[i] 实际上表示值为 i+minVal 的元素个数
    // 扫面一遍数组，把值放入相应的桶中
    for (int num : nums) {
        bucket[num - minVal]++; // 优化处理，减小了桶占用的空间
    }
    // 扫描一遍桶: O(n)
    int n = 0;
    for (int i = 0; i < bucket.length; i++) {
        while (bucket[i] > 0) {
            nums[n++] = i + minVal;
            bucket[i]--;
        }
    }
}
```

### [162. 寻找峰值](https://leetcode-cn.com/problems/find-peak-element/) 

峰值元素是指其值大于左右相邻值的元素。给定一个输入数组 nums，其中 nums[i] ≠ nums[i+1]，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个峰值所在位置即可。假设 nums[-1] = nums[n] = -∞。要求**时间复杂度为O(log n)**。
```
输入: nums = [1,2,1,3,5,6,4]
输出: 1 或 5 
解释: 你的函数可以返回索引 1，其峰值元素为 2；
     或者返回索引 5， 其峰值元素为 6。
```
分析：（参考官方题解，这一题的思路也是非常巧妙，值得借鉴）

最关键的思想是：把数组看成是多个局部有序的子数组。比如，nums = [1,2,1,3,5,6,4]，那么子数组[1,2]、[1,3,5,6]、[4]都是有序的。这么考虑是为什么呢？当采用二分法计算出中间节点mid后，可以通过判断`nums[mid]`与`nums[mid+1]`的值来确定该中间节点是否处于局部递增或递减的子序列中。

* 若`nums[mid] < nums[mid+1]`，说明 mid 处于局部递增序列中，那么峰值肯定出现在 mid 的右侧，调整`left = mid+1`。（事实上这么表述并不严谨，应该说，**由于 mid 处于局部递增序列中，那么在 mid 的右侧肯定存在峰值**。mid 的左侧也可能存在峰值，但是由于题目中已经说明若存在多个峰值只需返回任意一个，因此只考虑任意一侧即可）
* 若`nums[mid] > nums[mid+1]`，说明 mid 处于局部递减序列中，那么峰值肯定出现在 mid 的左侧，注意`nums[mid]`本身可能就是峰值点，因此调整`right = mid`。

```java
class Solution {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1, mid = 0;
        while(left < right) {
            mid = (right - left) / 2 + left;
            // 由于 while 判断条件是严格小于
            // 因此只要进入循环，则至少存在两个元素，
            // 而 mid = (left+right)/2 总是偏向左侧，
            // 因此一定不用担心 nums[mid+1] 会越界
            if(nums[mid] < nums[mid+1]) {
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left;
    }
}
```

### [4. 寻找两个有序数组的中位数](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/) 

给定两个大小为 m 和 n 的有序数组 nums1 和 nums2。找出这两个有序数组的中位数，假设 nums1 和 nums2 不会同时为空。要求尽可能高效。

```
示例1：
nums1 = [1, 3]
nums2 = [2]
则中位数是 2.0

示例2：
nums1 = [1, 2]
nums2 = [3, 4]
则中位数是 (2 + 3)/2 = 2.5
```

分析：这一题非常有价值，学到了全新的思路。

首先明确中位数的定义：
> 如果某个有序数组长度是奇数，那么其中位数就是最中间那个元素；如果数组长度是偶数，那么就是最中间两个元素的平均值。

方法1：直接根据“中位数”的定义来做，将两个有序数组合并成一个有序数组，然后直接根据下标返回结果即可。但是这种方法需要`O(m+n)`的额外空间，由于合并操作，需要完整的遍历的一边数组，时间复杂度为`O(m+n)`。

方法2：从“**寻找两个有序数组的第k个数**”这一角度出发，把问题泛化，更通用！

对于在两个有序数组中寻找第k个数，有这样一个算法（参考[这里](https://leetcode-cn.com/problems/median-of-two-sorted-arrays/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-w-2/)）。

假设我们要找第`k`个数，每次循环可以排除掉`k/2`个数。下面来看例子。

假设我们要找第7个数，即 `k=7`。那么比较两个数组中第 `k/2 = 7/2 = 3` 个数（在叙述中我们约定下标从1开始，在编码时再处理这些细节），可以看到`nums1[k/2] = 4 > nums2[k/2] = 3`，从而可以排除掉nums2中的前`k/2`个数（也就是1,2,3）。

![image.png](https://pic.leetcode-cn.com/735ea8129ab5b56b7058c6286217fa4bb5f8a198e4c8b2172fe0f75b29a966cd-image.png)

橙色的部分表示已经去掉的数字。经过一次比较，排除掉`k/2` 个数后，原先求第`k` 个数则转化成求第`k - k/2` 个数了。于是，下一轮计算要求的`k`等于4，和之前的一样，比较`nums1[k/2]`和`nums2[k/2]`的大小（这里需要注意的是，橙色部分只是理论上被排除，而不是真正的物理删除，所以在编码时，真正比较的是`nums1[k/2+上一轮被排除掉的个数]` 和 `nums2[k/2+上一轮被排除掉的个数]`），这一轮排除nums1的前`k/2`个数。

![image.png](https://pic.leetcode-cn.com/09b8649cd2b8bbea74f7f632b098fed5f8404530ff44b5a0b54a360b3cf7dd8f-image.png)

![image.png](https://pic.leetcode-cn.com/f2d72fd3dff109ad810895b9a0c8d8782f47df6b2f24f9de72704961bc547fcb-image.png)

这一轮过后，又排除掉2个数，于是转化成求第2个数（k=4-2=2）。这一轮出现了`nums1[k/2]==nums2[k/2]`的情况，可以排除任意一个数组，在这里，令`nums1[k/2]≥nums2[k/2]` 一起考虑，于是排除掉数组nums2中的一个数。

![image.png](https://pic.leetcode-cn.com/3c89a8ea29f2e19057b57242c8bc37c5f09b6796b96c30f3d42caea21c12f294-image.png)

当k等于1时，就可以直接返回答案了，也就是取两个数组中首个元素较小的那个。

我们每次都是取 `k/2` 的数进行比较，有时候可能会遇到数组长度**小于**`k/2`的时候。这个在编码时要尤为注意！

![image.png](https://pic.leetcode-cn.com/ad87d1f63a9bbd99e12605686290800ce61b03f9fb98d87f1d8c020d404421ac-image.png)

此时 `k/2` 等于 3，而上边的数组长度是 2，我们此时将箭头指向它的末尾就可以了。这样的话，由于 2 < 3，所以就会导致上边的数组 1，2 都被排除。造成下边的情况。

![image.png](https://pic.leetcode-cn.com/7ea1963f184b1dcaddf951326ccbe7aa09cfbb9ebee7fffb2ede131853b3d1de-image.png)

由于 2 个元素被排除，所以此时 k = 5，又由于上边的数组已经空了，我们只需要返回下边的数组的第 5 个数字就可以了。

从上述的讨论可以看到，无论是找第奇数个还是第偶数个数字，对算法并没有影响，而且在算法进行中，k 的值都有可能从奇数变为偶数，**最终都会变为 1 或者由于一个数组空了，直接返回结果**。

通过以上分析，即可写出如何求两个有序数组的第k个值。对于本题，求两个有序数组的中位数，假设数组`nums1`和`nums2`的元素个数分别为`m`, `n`。若`m+n`为奇数，则中位数取下标为`(m+n)/2`的值，如果`m+n`为偶数，则中位数取下标为`(m+n)/2`和`(m+n)/2 - 1`的平均值。根据上文的语境（第1个元素下标为0），可以通过以下技巧把奇数和偶数的情况合二为一：

* 假设 m+n=6，中位数为下标为 2 和 3 的两个数的平均值，也就是求第 3 个数和第 4 个数。此时，k1 = (6+1)/2=3，k2 = (6+2)/2 = 4，正好满足要求。
* 假设 m+n=5，中位数取下标为 2 的值，也就是第 3 个数。此时，k1 = (5+1)/2=3，k2 = (5+2)/2=3，即当奇数个数时，求两遍再除以2，结果一样。

这里记录一下这个技巧是因为自己第一次遇到，比较新奇。为了效率还是奇偶情况分开来写，避免重复计算。

* 时间复杂度：在求第 k 个数时，每一轮计算可以排除 k/2 个数，因此时间复杂度是 O(log(k))，本题中 k = (m+n)/2 ，因此时间复杂度为O(log(m+n))。


* 空间复杂度：虽然我们用到了递归，但是可以看到这个递归属于**尾递归**（什么是尾递归？），所以编译器不需要不停地堆栈，所以空间复杂度为 O(1)。

```java
// 时间复杂度：O(log(m+n))
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        if((n1+n2)%2 == 1) {
            return (double)findKthElement(nums1, 0, n1-1, nums2, 0, n2-1, (n1+n2)/2 + 1);
        }else {
            int a = findKthElement(nums1, 0, n1-1, nums2, 0, n2-1, (n1+n2)/2);
            int b = findKthElement(nums1, 0, n1-1, nums2, 0, n2-1, (n1+n2)/2 + 1);
            return (double)(a+b)/2;
        }
    }
    // 返回两个有序数组的第k个元素
    // 1 ≤ k ≤ n1+n2
    private int findKthElement(int[] nums1, int l1, int r1, 
                               int[] nums2, int l2, int r2, int k) {
        int n1 = r1-l1+1, n2 = r2-l2+1;
        if(n1 > n2) { // 确保nums1的长度总是小于nums2的个数
            return findKthElement(nums2, l2, r2, nums1, l1, r1, k);
        }
        // 递归边界
        if(n1 == 0) {
            return nums2[l2+k-1];
        }
        if(k == 1) {
            return Math.min(nums1[l1], nums2[l2]);
        }

        /* 
        这部分的处理非常关键，容易错误的认为是 i = l1 + k/2 - 1
        nums1 = [4]
        nums2 = [1,2,3,5,6]
        */
        int i = l1 + Math.min(n1, k/2) - 1; 
        int j = l2 + Math.min(n2, k/2) - 1;
        if(nums1[i] <= nums2[j]) {
            return findKthElement(nums1, l1+Math.min(n1, k/2), r1, nums2, l2, r2, k-Math.min(n1, k/2));
        }else {
            return findKthElement(nums1, l1, r1, nums2, l2+Math.min(n2, k/2), r2, k-Math.min(n2, k/2));
        }
    }
}

// 写法2
class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int n1 = nums1.length, n2 = nums2.length;
        int k1 = (n1+n2+1)/2, k2 = (n1+n2+2)/2; // trick
        int a = findKthElement(nums1, 0, n1-1, nums2, 0, n2-1, k1);
        int b = findKthElement(nums1, 0, n1-1, nums2, 0, n2-1, k2);
        return (double)(a+b)/2;
    }
    //...
}
```

### [29. 两数相除](https://leetcode-cn.com/problems/divide-two-integers/)

给定两个整数，被除数 dividend 和除数 divisor。将两数相除，要求不使用乘法、除法和 mod 运算符。

返回被除数 dividend 除以除数 divisor 得到的商。

整数除法的结果应当截去（truncate）其小数部分，例如：truncate(8.345) = 8 以及 truncate(-2.7335) = -2
```
示例 1:
输入: dividend = 10, divisor = 3
输出: 3
解释: 10/3 = truncate(3.33333..) = truncate(3) = 3

示例 2:
输入: dividend = 7, divisor = -3
输出: -2
解释: 7/-3 = truncate(-2.33333..) = -2
```
说明:

* 被除数和除数均为 32 位有符号整数。
* 除数不为 0。
* 假设我们的环境只能存储 32 位有符号整数，其数值范围是 [−2^31,  2^31 − 1]。本题中，如果除法结果溢出，则返回 2^31 − 1。

分析：本题考察的非常细节，通过这一题，要对计算机中如何表示数值，如何实现基本运算有一个了解，而不是为了简单的AC。

首先，既然不能使用乘除法，那么只能通过减法来模拟除法了。假设`dividend = 16`，`divisor = 3`，由于`dividend(16) > divisor(3)`，说明结果至少为1；于是，让`divisor`加倍（即变为6），由于16仍然大于6，说明结果至少为2；于是，继续让`divisor`加倍(即变为12)，由于16依旧大于12，说明结果至少为4；于是，继续让`divisor`加倍(即变为24)，此时，16小于24，不能继续这么猛干了，于是由**倍数增长**改为**线性增长**。

不考虑边界情况，简单实现如下：
```java
// 线性增长，不够快
class Solution {
    public int divide(int dividend, int divisor) {
        // 特例
        if(dividend == 0) return 0;
        // 判断符号
        boolean negative = false;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            negative = true;
            if(dividend < 0) dividend = Math.abs(dividend); // ???
            if(divisor < 0) divisor = Math.abs(divisor);
        }
        int count = 0;
        // 核心逻辑
        while(dividend > divisor) {
            count++;
            dividend -= divisor;
        }
        return negative ? -count : count;
    }
}
// 先倍数增长，再线性增长
class Solution {
    public int divide(int dividend, int divisor) {
        // 特例
        if(dividend == 0) return 0;
        // 判断符号
        boolean negative = false;
        if((dividend < 0 && divisor > 0) || (dividend > 0 && divisor < 0)) {
            negative = true;
            if(dividend < 0) dividend = Math.abs(dividend); // ???
            if(divisor < 0) divisor = Math.abs(divisor);
        }
        int count = 1;
        int originDividend = dividend, originDivisor = divisor;
        dividend -= divisor;
        // 核心逻辑
        // 倍数增长
        while(dividend >= divisor) {
            count = count + count;
            divisor += divisor;
            dividend -= divisor;
        }
        // 线性增长
        while(originDividend - divisor >= originDivisor) {
            count++;
            divisor += originDivisor;
        }
        return negative ? -count : count;
    }
    private int opposite(int x) {
        return ~x + 1;
    }
}
```
上述实现对于基本的case已经可以通过了，只是没考虑边界情况，本题的核心逻辑也就这些。BUT！如果了解`Math.abs()`的实现，会发现这其中就用了乘法运算。
```java
// from Math.java
public static int abs(int a) {
    return (a < 0) ? -a : a; // -a 不就相当于是 (-1) × a 么！
}
```
`-a` 不就相当于是`(-1) × a`么！因此这么做是不符合要求的，**我们需要知道计算机中如何表示负数**。以 Java 语言为例，在 Java 中，基本数据类型 int 由32位表示。

```
3 = 0000 0000 0000 0000 0000 0000 0000 0011
    |
  最高位：最高位为符号位，0代表正数，1代表负数
```

那么，-3 该如何表示呢？它是这样表示的：对 3 的二进制表示“**按位取反**”，然后“**末位加1**”。如下：

```
3 = 0000 0000 0000 0000 0000 0000 0000 0011
    1111 1111 1111 1111 1111 1111 1111 1100 （按位取反）
    1111 1111 1111 1111 1111 1111 1111 1101 （末位加1）
即：
-3= 1111 1111 1111 1111 1111 1111 1111 1101
如果给定一个二进制序列为：1111 1111 1111 1111 1111 1111 1111 1101，怎么人为的把它翻译成十进制数字呢？
由于最高位是 1 ，可知这个数一定是负数，然后再根据“按位取反，末位加1”的逆操作来运算，也就是“末位减1，按位取反”。
1111 1111 1111 1111 1111 1111 1111 1100 （末位减1）
0000 0000 0000 0000 0000 0000 0000 0011 （按位取反） 
```

可通过如下程序验证：

```java
System.out.println("x = " + x + ", binary string = " + Integer.toBinaryString(x));
System.out.println("-x = " + -x + ", binary string = " + Integer.toBinaryString(-x));
```

在本题中，由于存在正/负的情况，需要对结果乘以-1，但题目规定不能使用乘法运算，故可通过“按位取反，末位加1”的方式来计算`x`的相反数`-x`。

```
int opposite(int x){
	return ~x + 1;
}
```

但是，如果给定的 `x` 等于 `Integer.MIN_VALUE` 呢？

```
MAX_VALUE = 2147483647,  binary string = 01111111111111111111111111111111
MIN_VALUE = -2147483648, binary string = 10000000000000000000000000000000        

int 可表示的最大值
0111 1111 1111 1111 1111 1111 1111 1111 --> 2^31 - 1
int 可表示的最小值
1000 0000 0000 0000 0000 0000 0000 0000 --> -2^31
```
对于有符号整数，**负数要比整数多一个，因此当给定的 `x` 等于 `Integer.MIN_VALUE` 时，对其计算 `-x` 会溢出，因为并不存在这样一个整数来表示它**。

```java
// 写法1，有bug
/**
 * 把所有的数都转化为正数来处理，符号最后再添加
 */
public int divide(int dividend, int divisor) {
    // 特判：除数为0。（题目已经保证被除数不为0，故无需考虑）
    if(dividend == 0) return 0;

    // 特判：如果被除数是1（或-1），直接返回除数本身（除数的相反数）
    //if(divisor == 1) return dividend;
    //else if(divisor == -1) return opposite(dividend);

    // 但是这种情况有一个特例，即 MIN_VALUE / -1 ，由于对 int 型的最小值取相反数会发生溢出，
    // 根据题意，发生溢出时返回 MAX_VALUE。因此上面的写法修改如下：
    if(divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
    if(divisor == 1) return dividend;
    else if(divisor == -1) return opposite(dividend);


    // 调整，如果出现负数，将其转化为整数处理，最后返回结果时再处理符号
    boolean isPositive = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
    if(dividend < 0)
        dividend = opposite(dividend);
    if(divisor < 0)
        divisor = opposite(divisor);

    // 这里又会出现一个意外，比如 dividend = Integer.MIN_VALUE, divisor = 2,
    // 理应返回 -1073741824，但是根据这里的逻辑，会返回 0
    // 对于 Integer.MIN_VALUE，也就是 -2147483648，和其他数值不一样的是：
    // opposite(-2147483648)的结果仍然等于-2147483648，而不是正常逻辑下的2147483648，这是由于溢出造成的
    // 这个问题在这一版代码中似乎无法解决

    // 特判：除数的绝对值 小于 被除数的绝对值，结果为0。比如 1/3, -1/3, 1/(-3)
    if(dividend < divisor) return 0;

    int originalDividend = dividend, originalDivisor = divisor;
    // 二分法
    int ans = 1;
    dividend -= divisor;
    while (divisor <= dividend){
        ans = ans + ans;
        divisor += divisor;
        dividend -= divisor;
    }
    // 剩余的部分线性处理
    while (originalDividend - divisor >= originalDivisor){
        ans++;
        divisor += originalDivisor;
    }
    return isPositive ? ans : opposite(ans);
}

private int opposite(int x){
    return ~x + 1;
}
```

在上面的写法中，无法处理`dividend = -2147483648` 的情况。说到底，我们的逻辑是，不管给定的 dividend 和 divisor 是正数还是负数，都先将其转化为正数来处理，这样做当然是为了方便，但是会造成bug，这其实是因为在 int 型整数中，负数要比正数多一个，也就是 `-2147483648` 这个数，它在正数中没有一个值与其对应。既然负数的范围域比正数的范围域更广，我们何尝不换一种思路，也就是**将所有的数都先转换成负数来处理**，最后再解决符号的问题。有点不习惯，但总的逻辑是没有任何区别的。

```java
// 写法2，能通过，但耗时很长
// 时间复杂度：O(log n)
public int divide(int dividend, int divisor) {
    // 特判：除数为0。（题目已经保证被除数不为0，故无需考虑）
    if(dividend == 0) return 0;
    //
    if(divisor == -1 && dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
    if(divisor == 1) return dividend;
    else if(divisor == -1) return opposite(dividend);

    // 修改1
    // 调整，如果出现正数，将其转化为负数处理，最后返回结果时再处理符号
    boolean isPositive = (dividend < 0 && divisor < 0) || (dividend > 0 && divisor > 0);
    if(dividend > 0)
        dividend = opposite(dividend);
    if(divisor > 0)
        divisor = opposite(divisor);

    // 修改2
    // 特判：除数的绝对值 小于 被除数的绝对值，结果为0。比如 1/3, -1/3, 1/(-3)
    if(dividend > divisor) return 0;

    int originalDividend = dividend, originalDivisor = divisor;
    // 二分法
    int ans = 1;
    dividend -= divisor;
    while (divisor >= dividend){ // 修改3
        ans = ans + ans;
        divisor += divisor;
        dividend -= divisor;
    }
    // 剩余的部分线性处理
    while (originalDividend - divisor <= originalDivisor){ // 修改4
        ans++;
        divisor += originalDivisor;
    }
    return isPositive ? ans : opposite(ans);

}

private int opposite(int x){
    return ~x + 1;
}
```

---

几个不错的参考：

1. <https://leetcode-cn.com/problems/search-insert-position/solution/te-bie-hao-yong-de-er-fen-cha-fa-fa-mo-ban-python-/>