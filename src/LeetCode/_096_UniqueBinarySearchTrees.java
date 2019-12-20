package LeetCode;

public class _096_UniqueBinarySearchTrees {
    /**
     * 如果知道Catalan数的话，就很快可以找到突破口
     *
     * 什么是卡特兰数（catalan）?
     * F(n) = F(0)*F(n-1) + F(1)*F(n-2) + ... + F(n-1)*F(0), 其中 F(0)=1, F(1)=1
     * 卡特兰数和斐波那契数列一样，都是常见的数学递推式，并且在算法题中经常出现，因此了解一下是必须的
     *
     * 假设n个节点可构成不同的二叉搜索树个数为F(n)，那么：
     * F(n) = f(1) + f(2) + ... + f(n), (公式1), 其中 f(i) 表示以 i 为根节点所构成的二叉搜索树的个数
     * 而 f(i) = F(i-1)*F(n-i)          (公式2)
     * 比如以 3 为根节点，那么根据二叉搜索树的特点，
     * 左子树有(3-1)个节点 ==> 对应有F(3-1)种子树
     * 右子树有(n-3)个节点 ==> 对应有F(n-3)种子树
     *
     * 结合公式1,2, 即可推出
     * F(n) = F(0)*F(n-1) + F(1)*F(n-2) + ... + F(n-1)*F(0)
     * 也就是卡特兰数递推公式，再转化成代码即可
     *
     * 时间复杂度: O(n^2)
     * 空间复杂度: O(n)
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }
        return dp[n];
    }

    public static void main(String[] args) {
        _096_UniqueBinarySearchTrees obj = new _096_UniqueBinarySearchTrees();
        System.out.println(obj.numTrees(3));
    }
}
