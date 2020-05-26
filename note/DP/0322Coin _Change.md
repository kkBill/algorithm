#### [322. Coin Change](https://leetcode-cn.com/problems/coin-change/)

给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来**计算可以凑成总金额所需的最少的硬币个数**。如果没有任何一种硬币组合能组成总金额，返回 -1。说明：**你可以认为每种硬币的数量是无限的**。

```
输入: coins = [1, 2, 5], amount = 11
输出: 3 
解释: 11 = 5 + 5 + 1

输入: coins = [2], amount = 3
输出: -1
```

分析：这是一个完全背包问题，对于每一件物品，都有选或不选两种可能，并且每一件物品都可以重复选择。

方法1：动态规划

状态：令`dp[i]`表示组成金额为`i`的所需要的最少硬币个数，最终的答案保存在`dp[amount]` 。

初始化：`dp[i] = amount + 1, (0 ≤ i ≤ amount)`，表示没有任何一种硬币组合能组成总金额`i`。 另外令`dp[0] = 0`，表示组成金额0需要0个硬币。

状态转移：以`coins = [1, 2, 5], amount = 11`为例，假设`dp[6]`已经计算好了，我们知道数额6可以由数组中的1+5组成，即`dp[6]=2`。现在要计算`dp[11]`了，该如何思考呢？由于6+5正好等于11，因此，组成数额11的硬币个数只需要`dp[6]+1`个。这里的逻辑是，如果计算`dp[i]`，考虑当前的硬币为coin，如果`dp[i - coin]`已经更新了，那么，就更新`dp[i] = dp[i - coin] + 1`。即：

```
dp[i] = min(dp[i], dp[i-coin] + 1)
```

代码：这里解释为什么要把dp数组初始化为`amount + 1`，而不是`Integer.MAX_VALUE`，因为在状态转移过程中，会计算`dp[j]+1`，如果用`MAX_VALUE`初始化，那么加1后就会发生溢出。

时间复杂度：O(n*amount)；空间复杂度：O(amount)

```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        // 初始化
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        // 状态转移
        for(int i = 1; i <= amount; i++) {
            for(int coin : coins) {
                if(i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i-coin] + 1);
                }
            }
        }
        return dp[amount] == amount+1 ? -1 : dp[amount];
    }
}
```



方法2：暴力递归，超时

对于数值amount，组成它最多需要amount个硬币（即amount个1元硬币）。因此初始化`minCount = amount+1`，其效果等价于`minCount = Integer.MAX_VALUE`。 为什么不直接初始化为`Integer.MAX_VALUE`呢？在这一种做法中体现不出来，不过在接下来的方法中就会明白的。



```java
class Solution {
    public int coinChange(int[] coins, int amount) {
        if(amount == 0) return 0;
        // 初始化
        int minCount = amount + 1;
        for(int coin : coins) {
            //
            if(amount - coin < 0) continue;
            //
            int subCount = coinChange(coins, amount - coin);
            if(subCount == -1) continue;
            
            minCount = Math.min(minCount, subCount + 1);
        }
        return minCount == amount + 1 ? -1 : minCount;
    }
}
```



方法3：暴力递归的另一种写法，仍然超时

```go
func coinChange(coins []int, amount int) int {
	sort.Slice(coins, func(a, b int) bool { return a > b })
	return helper(coins, 0, 0, amount)
}

func helper(coins []int, i int, depth int, amount int) int {
	if amount < 0 || i >= len(coins) {
		return -1
	}
	if amount == 0 {
		return depth
	}
	// 选择第i个硬币
	a := helper(coins, i, depth+1, amount-coins[i])
	// 不选择第i个硬币
	b := helper(coins, i+1, depth, amount)

	if a == -1 {
		return b
	}
	if b == -1 {
		return a
	}
	return min(a, b)
}

func min(a, b int) int {
	if a < b {
		return a
	}else {
		return b
	}
}
```

题外话：这里为什么用Go写呢？因为我以为先对`coins`数组进行逆序排序后，就不会超时了。而在Java中不支持对`int`数组进行自定义排序，只支持`Integer`对象数组。而leetcode给的接口是`int`类型，因此处理起来会比较麻烦。另外，[Go的math包居然没有`int`数据类型的max/min函数](https://www.cnblogs.com/kkbill/p/12710639.html)，也是晕...



方法4：dfs + 剪枝

```java
class Solution {
    int minCount = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        Arrays.sort(coins);
        helper(coins, coins.length-1, 0, amount);
        return minCount == Integer.MAX_VALUE ? -1 : minCount;
    }
    
    private void helper(int[] coins, int i, int curCount, int amount) {
        if(amount == 0) {
            minCount = Math.min(minCount, curCount);
            return;
        }    
        if(i < 0) {
            return;
        }
        for(int k = amount/coins[i]; k >= 0 && k + curCount < minCount; k--) {
            helper(coins, i - 1, curCount + k, amount - coins[i]*k);
        }
    }
}
```







