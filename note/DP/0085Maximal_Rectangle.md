#### [85. Maximal Rectangle](https://leetcode-cn.com/problems/maximal-rectangle/)

给定一个仅包含 0 和 1 的二维二进制矩阵，找出只包含 1 的最大矩形，并返回其面积。

```
输入:
[
  ["1","0","1","0","0"],
  ["1","0","1","1","1"],
  ["1","1","1","1","1"],
  ["1","0","0","1","0"]
]
输出: 6
```



分析：

方法1：直接整合第84的方法

```java
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) return 0;
        int n = matrix[0].length;
        
        int[] heights = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        int maxArea = 0;
        
        for(int i = 0; i < m; i++) {
            // 计算matrix[0...i]层构成的heights[]数组
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') heights[j] += 1;
                else heights[j] = 0;
            }
            
            // 更新left[] 和 right[]数组
            left[0] = -1;
            for(int j = 1; j < n; j++) {
                int t = j - 1;
                while(t >= 0 && heights[t] >= heights[j]) {
                    t = left[t];
                }
                left[j] = t;
            }
            
            right[n-1] = n;
            for(int j = n-2; j >= 0; j--) {
                int t = j + 1;
                while(t < n && heights[t] >= heights[j]) {
                    t = right[t]; 
                }
                right[j] = t;
            }
            
            // 计算面积
            for(int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, heights[j]*(right[j]-left[j]-1));
            }
        }
        return maxArea;
    }
}
```



方法2：优化

```java
// 时间复杂度：O(m*n)
// 空间复杂度：O(n)
class Solution {
    public int maximalRectangle(char[][] matrix) {
        int m = matrix.length;
        if(m == 0) return 0;
        int n = matrix[0].length;

        int[] heights = new int[n];
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right,n);
        int maxArea = 0;

        for(int i = 0; i < m; i++) {
            // 计算matrix[0...i]层构成的heights[]数组
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') heights[j] += 1;
                else heights[j] = 0;
            }

            // 更新left[] 和 right[]数组
            int boundary = -1;
            for(int j = 0; j < n; j++) {
                if(matrix[i][j] == '1') {
                    left[j] = Math.max(left[j], boundary);
                }
                else {
                    left[j] = -1;
                    boundary = j;
                }
            }

            boundary = n;
            for(int j = n-1; j >= 0; j--) {
                if(matrix[i][j] == '1') {
                    right[j] = Math.min(right[j], boundary);
                }else {
                    right[j] = n;
                    boundary = j;
                }
            }

            // 计算面积
            for(int j = 0; j < n; j++) {
                maxArea = Math.max(maxArea, heights[j]*(right[j]-left[j]-1));
            }
        }
        return maxArea;
    }
}
```

