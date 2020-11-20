# TopK 问题详解

【问题描述】（本文代码以在[面试题40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)中可提交）

> 在无序数组 nums[] 中，找出最小（或最大）的 k 个数。例如，输入[4, 5, 1, 6, 2, 7, 3, 8]这8个数字，则最小的4个数字是1、2、3、4。

## 思路1：直接排序

直接将数组进行排序，然后取出前 k 个元素即可。这是最容易想到的。

代码略。

直接排序需要对整个数组 n 个元素都进行排序（全局操作），时间复杂度至少是 `O(n*logn)`，而我们只需要找出前 k 个元素即可（只需要局部元素），显然是小题大做了。我们能不能只进行局部排序，拿到我们想要的 k 个元素就及时停止呢？



## 思路2：冒泡排序

冒泡排序虽然平时很少用到，但我们知道它是一个全局排序，也就是说，每执行一次，就会有一个元素确定其最终位置。因此，我们可以通过冒泡排序，执行 k 次便可以确定最终结果，时间复杂度是  `O(n*k)`。当 `k << n` 时，`O(n*k)`的性能会比`O(n*logn)`好很多。

```java
class Solution {
    public int[] getLeastnumbers(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        for(int i = 0; i < k; i++) {
            for(int j = nums.length - 1; j > i; j--) {
                if(nums[j] < nums[j-1]) {
                    int t = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = t;
                }
            }
        }
        int[] res = new int[k];
        for(int i = 0; i < k; i++) {
            res[i] = nums[i];
        }
        return res;
    }
}
```

在[面试题40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)中，使用冒泡也是能通过的，只不过效率很低，这是因为题目中并未说明`k << n`，而可能`k == n`。这里仅作为一种思路。Anyway，就假定`k << n`吧，我们已经将全局排序优化成局部排序了，但是！通过冒泡排序我们拿到的 k 个元素仍然是有序的，题目只要求我们取出最小的 k 个元素，并未要求这 k 个数有序，因此，我们还有进一步优化的空间。



## 思路3：堆结构的应用

对于求最小的 k 个元素，我们建立一个**大顶堆**，保证堆中的元素不超过 k 个。**大顶堆中存放的元素是当前数组中前 k 个小的数**。当要往大顶堆中插入元素时，先跟堆顶元素（也就是当前的最大值）进行比较，如果待插入的元素比堆顶元素要小，那么堆顶元素不可能是前 k 个小的数了。于是替换掉堆顶元素，并调整堆，以保证堆内的 k 个元素，总是当前最小的 k 个元素。当遍历完数组，大顶堆中存留下来的 k 个元素就是所求结果。

时间复杂度为**`O(n*logk)`**，其中`O(n)`是因为要变遍历一趟数组，`O(logk)`是每次堆结构调整所需要的时间。

```java
class Solution {
    public int[] getLeastnumbers(int[] nums, int k) {
        // PriorityQueue<Integer> maxHeap =  new PriorityQueue<>(); // 默认为小顶堆
        PriorityQueue<Integer> maxHeap =  new PriorityQueue<>((x, y) -> y - x); // 大顶堆
        for(int num : nums) {
            if(maxHeap.size() < k) {
                maxHeap.add(num);
            }else if(!maxHeap.isEmpty() && num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(num);
            }
        }
        int[] res = new int[maxHeap.size()];
        int i = 0;
        while(!maxHeap.isEmpty()) {
            res[i++] = maxHeap.poll();
        }
        return res;
    }
}
```



## 思路4：随机选择

随机选择算在是《算法导论》中一个经典的算法，其时间复杂度为O(n)，是一个线性复杂度的方法。为了说明随机选择算法，需要先了解快速排序算法。

快速排序算法的伪代码实现如下：

```java
void QuickSort(int[] nums, int left, int right) {
    if (left >= right) return;
    // 选取主元
    int pivot = selectPivot(nums, left, right);
    // 根据主元进行划分
    int i = partition(nums, left, right, pivot); 
    // 递归处理左右子集
    QuickSort(nums, left, i-1);
    QuickSort(nums, i+1, right);
}
```

其核心思想是**分治法**。

【扩展】

 **分治法（Divide&Conquer）**：把一个大的问题，转化为若干个子问题（Divide），每个子问题**「都」**解决，大的问题便随之解决（Conquer）。这里的关键词是**「都」**。从伪代码里可以看到，快速排序递归时，先通过partition把数组分隔为两个部分，两个部分「都」要再次递归。

 分治法有一个特例，叫减治法。

 **减治法（Reduce&Conquer）**：把一个大的问题，转化为若干个子问题（Reduce），这些子问题中**「只」**解决一个，大的问题便随之解决（Conquer）。这里的关键词是**「只」**。

二分查找（Binary Search）就是一个典型的运用减治法的一种算法。其伪代码如下：

```java
int binarySearch(int[] nums, int target, int left, int right) {
		if(left > right) return -1;
  	int mid = (left + right) / 2;
  	if(nums[mid] > target) {
    		return binarySearch(nums, target, left, mid-1);
    }else if(nums[mid] < target) {
    		return binarySearch(nums, target, mid+1, right);
    }else {
    		return mid;  
    }
}
```

可以看到，每次查询时，通过mid把原数组分为左右两个子分区，根据和target的比较，**只需要进入其中一个分区就可解决问题**。这是和快速排序的最大不同。

通过上述说明，我们可以知道，减治法一般要比分治法的复杂度更低。

* 分治法：O(n*logn)

* 减治法：O(logn)



回到本题，解决Topk问题可以从排序算法中借鉴什么思想呢？排序算法的核心是划分操作，即partition。它的作用是根据主元pivot调整数组，把小于pivot的元素移到左侧，把大于pivot的元素移到右侧，从而确定pivot的最终位置。假设pivot的位置为k，也就是说，可以确定pivot是该数组第k小的元素（这里先假设下标从1开始）——这不就是Topk问题要解决的问题吗？

我们**设法找到数组中第k大的元素，那么在该元素之前的所有元素**，就是我们要求的Topk了。

代码实现如下：

```java
class Solution {
    public int[] getLeastnumbers(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        return quickSort(nums, 0, nums.length-1, k);
    }

    public int[] quickSort(int[] nums, int left, int right, int k) {
        int pivotIndex = partition(nums, left, right);
        if(pivotIndex == k - 1) {
            return Arrays.copyOfRange(nums, 0, k);
        }else if(pivotIndex > k - 1) {
            return quickSort(nums, left, pivotIndex - 1, k);
        }else {
            return quickSort(nums, pivotIndex + 1, right, k);
        }
    }

    // 划分操作，返回主元索引
    public int partition(int[] nums, int left, int right) {
        if(right - left == 0) return left;
        // 以数组的首个元素作为主元
        int pivot = nums[left];
        int i = left, j = right;
        while(i <= j) {
            while(i <= j && nums[i] <= pivot) i++;
            while(i <= j && nums[j] > pivot) j--;
            if(i < j) swap(nums, i, j);
            else break;
        }
        swap(nums, left, j);
        return j;
    }

    public void swap(int[] nums, int i, int j) {
        int t = nums[i];
        nums[i] = nums[j];
        nums[j] = t;
    }
}
```

根据之前的分析，这是一个典型的减治算法，递归的两个分支，每次只会执行其中一个。

时间复杂度分析： 因为我们是要找下标为k的元素，第一次切分的时候需要遍历整个数组 (0 ~ n) 找到了下标是 j 的元素，假如 k 比 j 小的话，那么我们下次切分只要遍历数组 (0~k-1)的元素就行了，反之如果 k 比 j 大的话，那下次切分只要遍历数组 (k+1～n) 的元素，总之可以看作每次调用 partition 遍历的元素数目都是上一次遍历的 1/2，因此时间复杂度是 n + n/2 + n/4 + ... + n/n = 2n，因此时间复杂度是 O(n)。



## 思路5：计数排序

当元素值域限定在一定范围内时，可以直接使用计数排序。比如，在[面试题40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)中，限定了元素的大小在[0, 10000]之间，那么，可以直接使用计数排序（也称桶排序）来解决。

```java
class Solution {
    public int[] getLeastnumbers(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        int[] count = new int[10010];
        for(int num : nums) {
            count[num]++;
        }
        int[] res = new int[k];
        int index = 0;
        for(int val = 0; val < count.length; val++) {
            while (count[val] > 0 && index < k) {
                res[index] = val;
                count[val]--;
                index++;
            }
        }
        return res;
    }
}
```

使用计数排序的时间复杂度是`O(n)`，也是很好的解法，只不过不是处理Topk问题的通用解法，该解法参考了[这篇题解](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/)。

 

## 总结

处理Topk问题，我们的思路演化过程是这样的：

1. 全局排序，O(n*logn)，直觉做法，不推荐
2. 局部排序，只排序Topk个元素，O(n*k)，只提供一种思路，不推荐
3. 堆的应用，Topk 个元素也不排序了，O(n*logk)，Topk问题的经典做法！
4. 分治思想，如何利用partition操作找出Topk元素，O(n)，Topk问题的经典做法！
5. 计数排序（或称桶排序），当元素的值域限定在一定范围时，也可以使用这种方法，也是O(n)的时间复杂度，但不是通用解法。



---

同类型题目集：

1. [面试题40. 最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/) 
2. [215. 数组中的第K个最大元素](https://leetcode-cn.com/problems/kth-largest-element-in-an-array/) 



---

参考：

1. https://blog.csdn.net/z50L2O08e2u4afToR9A/article/details/82837278
2. https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/3chong-jie-fa-miao-sha-topkkuai-pai-dui-er-cha-sou/

