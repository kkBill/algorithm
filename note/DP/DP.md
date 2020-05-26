### 动态规划问题汇总

##### 说在前面

定义状态还是不算难的，但是如何推导状态转移方程，我觉得很难找到一个很好的切入点。网上也看了很多高赞回答，虽然他们的题解头头是道，讲的比较让初学者接收，但终究不能解决我心中的疑惑——“为什么他们会这么想，而我却毫无思路？”。事实上，很难有一篇题解能告诉你从无到有的这个思考过程。这个问题贯穿了我整个刷题过程，尤其是处理DP问题的时候，直到有一天我在查资料时看到了[这篇文章](https://oi-wiki.org/dp/basic/)，不知为何，我突然就想明白了：事实上我们根本不需要怀疑自己的智商，会的人之所以会，因为他们压根不是第一次遇到这类问题（这里的第一次是真正意义上的第一次，没有任何前置知识）。看到ACM选手整理的那篇文章，总结了如此之多的DP套路、模板，我们现在做的leetcode题，充其量只是ACM训练的入门题（当然也是经典题）。只有经过了大量的训练，才能对这一类问题形成自己的思路。现在我没有过这方面的训练，又怎么可能有思路呢？所以对于DP入门级选手，重要的不是自己绞劲脑汁想思路，而是记住这些经典问题的思路（当然了，能完全自己想出来那就更好了）。所以，刷题的思路要转变过来。



可以参考这个帖子的总结：<https://www.zhihu.com/collection/168876650>



题目列表

1. [5. Longest Palindromic Substring](./0005Longest_Palindromic_Substring.md) [五星]
2. [516. Longest Palindromic Subsequence](./0516Longest_Palindromic_Subsequence.md) [五星]
3. [300. Longest Increasing Subsequence](https://leetcode-cn.com/problems/longest-increasing-subsequence/) [五星]
4. [1143. Longest Common Subsequence](https://leetcode-cn.com/problems/longest-common-subsequence/) [五星]
5. [10. Regular Expression Matching](https://leetcode-cn.com/problems/regular-expression-matching/) [五星+++]
6. [44. Wildcard Matching](https://leetcode-cn.com/problems/wildcard-matching/) [五星+++]
7. [53. Maximum Subarray](https://leetcode-cn.com/problems/maximum-subarray/) [五星]
8. [152. Maximum Product Subarray](https://leetcode-cn.com/problems/maximum-product-subarray/) [五星++]
9. [62. Unique Paths](https://leetcode-cn.com/problems/unique-paths/)
10. [63. Unique Paths II](https://leetcode-cn.com/problems/unique-paths-ii/)
11. [64. Minimum Path Sum](https://leetcode-cn.com/problems/minimum-path-sum/) [和62题是一样的]
12. [174. Dungeon Game](https://leetcode-cn.com/problems/dungeon-game/) 
13. [741. Cherry Pickup](https://leetcode-cn.com/problems/cherry-pickup/) [round trip, 暂时放弃...]
14. [120. Triangle](https://leetcode-cn.com/problems/triangle/) [第64题的变形, 自底向上/自顶向下]
15. [509. Fibonacci Number](https://leetcode-cn.com/problems/fibonacci-number/)
16. [70. Climbing Stairs](https://leetcode-cn.com/problems/climbing-stairs/)
17. [746. Min Cost Climbing Stairs](https://leetcode-cn.com/problems/min-cost-climbing-stairs/)
18. [84. Largest Rectangle in Histogram](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/) [这一题不属于dp问题, 但是和85题是强相关的]
19. [85. Maximal Rectangle](https://leetcode-cn.com/problems/maximal-rectangle/) [五星++, 84题的follow-up]
20. [221. Maximal Square](https://leetcode-cn.com/problems/maximal-square/)
21. [32. Longest Valid Parentheses](https://leetcode-cn.com/problems/longest-valid-parentheses/) [这一题比较独立, 五星++]


