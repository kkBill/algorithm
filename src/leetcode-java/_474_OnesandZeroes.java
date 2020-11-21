package LeetCode;

public class _474_OnesandZeroes {
    /*
    对于数组strs中的字符串，都有选或不选两种可能。而对于m个'0'和n个'1'，可以将其看成是背包问题中的容量V
    若选择strs[i]，那么就要消耗掉对应的空间v，在本题中，就要减去对应的 number_of_0_in_strs[i] 以及 number_of_1_in_strs[i]
    定义状态：
        dp[k][i][j] 表示前 k 个字符串，放入容量为 (i个0， j个1) 中所能获得的最大个数
    初始化：
        dp[0][][]

    状态转移：
        dp[k][i][j] = max{dp[k-1][i][j], dp[k-1][i-number_of_0_in_strs[k]][j-number_of_1_in_strs[k]] + 1}
    最终结果：
        dp[strs.length-1][m][n];
     */
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length+1][m+1][n+1];
        for(int k = 1; k <= strs.length; k++) {
            int[] count = countZerosAndOnes(strs[k-1]);

            // 注意，这里的zeros和ones的个数要从0个开始
            for(int zeros = 0; zeros <= m; zeros++) {
                for(int ones = 0; ones <= n; ones++) {
                    // 不选
                    dp[k][zeros][ones] = dp[k-1][zeros][ones];
                    // 选
                    if(zeros >= count[0] && ones >= count[1]) {
                        dp[k][zeros][ones] = Math.max(dp[k][zeros][ones], dp[k-1][zeros-count[0]][ones-count[1]] + 1);
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    private int[] countZerosAndOnes(String s) {
        int[] count = new int[2];
        for(char c : s.toCharArray()) {
            if(c == '0') count[0]++;
            else count[1]++;
        }
        return count;
    }

    public static void main(String[] args) {
        _474_OnesandZeroes obj = new _474_OnesandZeroes();
        String[] strs = {"10","0001","111001","1","0"};
        int m = 5, n = 3;
        System.out.println(obj.findMaxForm(strs, m, n));
    }
}
