#### [120. Triangle](https://leetcode-cn.com/problems/triangle/)

给定一个三角形，找出自顶向下的最小路径和。每一步只能移动到下一行中**相邻**的结点上。

例如，给定三角形：

```
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
```

自顶向下的最小路径和为 11（即，2 + 3 + 5 + 1 = 11）。

说明：如果你可以只使用 O(n) 的额外空间（n 为三角形的总行数）来解决这个问题，那么你的算法会很加分。

分析：本题要注意的是看清楚”相邻“这个条件。根据示例，假设当前层的位置为`(i,j)`，那么它在下一层的相邻位置为`(i+1, j)`和`(i+1, j+1)` 。

方法1：自顶向下写法，会改变原数组。写法粗糙。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0) return 0;
        if(n == 1) return triangle.get(0).get(0);
        
        int res = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                if(j == 0) {
                    triangle.get(i).set(j, triangle.get(i-1).get(j) + triangle.get(i).get(j));
                }else if(j == triangle.get(i).size()-1) {
                    triangle.get(i).set(j, triangle.get(i-1).get(j-1) + triangle.get(i).get(j));
                }else{
                    int minVal = Math.min(triangle.get(i-1).get(j), triangle.get(i-1).get(j-1));
                    triangle.get(i).set(j, minVal + triangle.get(i).get(j));
                }
                if(i == n-1) {
                    res = Math.min(res, triangle.get(i).get(j));
                }
            }    
        }
        return res;
    }
}
```



方法2：自底向上写法，会改变原数组

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0) return 0;
        
        for(int i = n - 2; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                int minVal = Math.min(triangle.get(i+1).get(j), triangle.get(i+1).get(j+1));
                triangle.get(i).set(j, minVal + triangle.get(i).get(j));
            }    
        }
        return triangle.get(0).get(0);
    }
}
```



方法3：自底向上写法，不改变原数组，O(n)辅助空间

这里辅助空间dp[]的使用有点像滚动数组的意思。

```java
class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();
        if(n == 0) return 0;
        
        int[] dp = new int[n];
        // 初始化dp[]为三角形最后一行的值
        for(int j = 0; j < n; j++) { // 第n行恰有n个元素
            dp[j] = triangle.get(n-1).get(j);
        }
        // 状态转移
        for(int i = n - 2; i >= 0; i--) {
            for(int j = 0; j < triangle.get(i).size(); j++) {
                dp[j] = Math.min(dp[j], dp[j+1]) + triangle.get(i).get(j);
            }
        }    
        return dp[0];
    }
}
```



